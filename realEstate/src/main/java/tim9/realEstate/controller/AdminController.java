package tim9.realEstate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.dto.InappropriateDTO;
import tim9.realEstate.dto.UserDTO;
import tim9.realEstate.dto.VerifierDTO;
import tim9.realEstate.mail.MailUtil;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Inappropriate;
import tim9.realEstate.model.User;
import tim9.realEstate.model.Verifier;
import tim9.realEstate.service.AdvertismentService;
import tim9.realEstate.service.AuthorityService;
import tim9.realEstate.service.CompanyService;
import tim9.realEstate.service.InappropriateService;
import tim9.realEstate.service.UserService;
import tim9.realEstate.service.VerifierService;

/**
 * This class represents controller for Admin
 * and manages with all Admin functionalities.
 */
@RestController
@RequestMapping(value="realEstate/admin")
public class AdminController {
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	VerifierService verifierService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	AdvertismentService advertismentService;
	
	@Autowired
	InappropriateService inappropriateService;
	
	@Autowired
	MailUtil mailUtil;
	
	/**
	 * This method returns clerks that are not approved.
	 * @return		ResponseEntity with List of Users and HttpStatus OK
	 */
	@RequestMapping(value="/unapproved/clerks", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getAllUnapprovedClerks() {
		List<User> users = userService.findUnapprovedClerks();

		List<UserDTO> userDTO = new ArrayList<>();
		for (User user : users)
			userDTO.add(new UserDTO(user));
		
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	/**
     * This method does the registration of a verifier,
     * sets authority and saves it to the database.
     * @param		verifierDTO DTO for verifier registration
     * @return      ResponseEntity with HttpStatus CREATED
     */
	@RequestMapping(value = "/registrate", method = RequestMethod.POST)
	public ResponseEntity<Void> registrateVerifier(@RequestBody VerifierDTO verifierDTO) {
		if(verifierDTO.getEmail() == null || verifierDTO.getPassword() == null || verifierDTO.getUsername() == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(verifierService.findByEmail(verifierDTO.getEmail()) != null || verifierService.findByUsername(verifierDTO.getEmail()) != null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String password = verifierDTO.getPassword();
		verifierDTO.setPassword(passwordEncoder.encode(verifierDTO.getPassword()));
    	verifierService.save(new Verifier(verifierDTO, authorityService.findByName("VERIFIER")));
    	
        String email = verifierDTO.getEmail();
        String subject = "Join Our Site";
        String text = "Hello \nYou have been added as a verifier! \n"
        		+ "Your username : " + verifierDTO.getUsername() + "\n "
        		+ "Your password : " + password;
        
        mailUtil.sendMail(email, subject, text);
    	return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	/**
     * This method allows admin to accept clerk and
     * his company.
     * After that, mail is being sent to the user to
     * notify him that his company is accepted.
     * @param		id id of an User
     * @return      HttpStatus OK if OK, else NOT_FOUND
     */
	@RequestMapping(value = "/accept", method = RequestMethod.PUT)
	public ResponseEntity<Void> acceptClerk(@RequestParam Long id) {
		if(id == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = userService.findOne(id);
		if(user == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		user.setApproved(true);
		userService.save(user);
		
		String email = user.getEmail();
        String subject = "Join Our Site";
        String text = "Hello \n Your have been accepted to our site! \n Welcome";
        mailUtil.sendMail(email, subject, text);
    	return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
     * This method allows admin to deny clerk and
     * his company.
     * After that, mail is being sent to the user
     * with the reason why is he denied.
     * @param		id id of an User
     * @return      HttpStatus OK if OK, else NOT_FOUND
     */
	@RequestMapping(value = "/deny", method = RequestMethod.DELETE)
	public ResponseEntity<Void> denyClerk(@RequestParam Long id) {
		if(id == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = userService.findOne(id);
		if(user == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		String email = user.getEmail();
        String subject = "Join Our Company";
        String text = "Hello \n We are sorry but your request has been rejected!";
        mailUtil.sendMail(email, subject, text);
       
		userService.remove(id);
    	return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
     * This method gets all Inappropriate advertisements from the database
     * and then creates a list of DTO objects.
     * @return      ResponseEntity List with all DTO Inappropriate comments and HttpStatus OK
     */
	@RequestMapping(value="/inappropriate", method = RequestMethod.GET)
	public ResponseEntity<List<InappropriateDTO>> getAllInappropriates() {
		List<Inappropriate> inappropriates = inappropriateService.findAll();
		
		List<InappropriateDTO> inappropriateDTO = new ArrayList<>();
		for(Inappropriate i : inappropriates){
			inappropriateDTO.add(new InappropriateDTO(i));
		}
		return new ResponseEntity<>(inappropriateDTO, HttpStatus.OK);
	}
	
	/**
     * This method rejects Inappropriate request and removes
     * it from the database, and sends mail to the user.
     * @return      id id of Inappropriate Advertisement
     */
	@RequestMapping(value="/inappropriate/reject", method = RequestMethod.DELETE)
	public ResponseEntity<List<Void>> rejectInappropriate(@RequestParam Long id) {
		if(id == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Inappropriate inappropriate = inappropriateService.findOne(id);
		if(inappropriate == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		inappropriateService.remove(id);
		
		String email = inappropriate.getUser().getEmail();
        String subject = "Inappropriate Advertisement Request";
        String text = "Hello \nYour request has been rejected!";
        mailUtil.sendMail(email, subject, text);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
     * This method accepts Inappropriate request and removes
     * it and advertisement from the database and sends mail
     * to all users that have reported this advertisement.
     * @return      id id of Inappropriate Advertisement
     */
	@RequestMapping(value="/inappropriate/accept", method = RequestMethod.DELETE)
	public ResponseEntity<List<Void>> acceptInappropriate(@RequestParam Long id) {
		if(id == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Inappropriate inappropriate = inappropriateService.findOne(id);
		if(inappropriate == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
		Advertisment advertisment = inappropriate.getAdvertisment();
		List<Inappropriate> inappropriates = inappropriateService.findByAdvertisement(advertisment);
		
		for (int i = 0; i < inappropriates.size(); i++) {
			String email = inappropriates.get(i).getUser().getEmail();
	        String subject = "Inappropriate Advertisement Request";
	        String text = "Hello \nYour request has been accepted!\nAdvertisement has been removed!";
	        mailUtil.sendMail(email, subject, text);
	        inappropriateService.remove(inappropriates.get(i).getId());
		}
		advertisment.setDeleted(true);
		advertismentService.save(advertisment);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
