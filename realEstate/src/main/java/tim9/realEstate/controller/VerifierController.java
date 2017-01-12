package tim9.realEstate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tim9.realEstate.dto.AdvertismentDTO;
import tim9.realEstate.dto.ChangePasswordDTO;
import tim9.realEstate.dto.InappropriateDTO;
import tim9.realEstate.dto.VerifierDTO;
import tim9.realEstate.mail.MailUtil;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Inappropriate;
import tim9.realEstate.model.Verifier;
import tim9.realEstate.security.UserUtils;
import tim9.realEstate.service.AdvertismentService;
import tim9.realEstate.service.InappropriateService;
import tim9.realEstate.service.VerifierService;


/**
 * This class represents controller for Verifier
 * and manages with all Verifier functionalities.
 */
@Controller
@RequestMapping(value="realEstate/verifiers")
public class VerifierController {
	
	@Autowired
	VerifierService verifierService;
	
	@Autowired
	AdvertismentService advertisementService;
	
	@Autowired
	InappropriateService inappropriateService;
	
	@Autowired
	UserUtils userUtils;
	
	@Autowired
	MailUtil mailUtil;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	@RequestMapping(value="/verifier", method = RequestMethod.GET)
	public ResponseEntity<VerifierDTO> getVerifier(ServletRequest request) {
		Verifier verifier = (Verifier)userUtils.getLoggedUser(request);
		
    	if (verifier == null) {
    		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(new VerifierDTO(verifier), HttpStatus.OK);
	}
	
	/**
	 * This method return all unverified advertisement
	 * @return  List of advertisementsDTO
	 */
	@RequestMapping(value="/advertisement/unverified", method = RequestMethod.GET)
	public ResponseEntity<List<AdvertismentDTO>> getAllUnverifiedAdvertisement() {
		List<Advertisment> advertisements = advertisementService.findAllUnverified(); 
		
		List<AdvertismentDTO> advertisementsDTO = new ArrayList<>();
		for (Advertisment advertisment : advertisements) {
			advertisementsDTO.add(new AdvertismentDTO(advertisment));
		}
		
		return new ResponseEntity<>(advertisementsDTO, HttpStatus.OK);
	}
	
	/**
	 * This method changes password for verifiers
	 * @param modifiedVerifier
	 * @return HttpStatus OK if exists, NOT FOUND if not
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
		if (!checkInputParams(changePasswordDTO)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Verifier verifier = verifierService.findOne(changePasswordDTO.getId());

		if (verifier == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), verifier.getPassword())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		verifier.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
		verifierService.save(verifier);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * This method enables verifier to accepts advertisements
	 * @param id
	 * @param request
	 * @return HTTPSTATUS OK if advertisement exists, HTTPSTATUS NOT_FOUND if not
	 */
	@RequestMapping(value="/accept/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> acceptAdvertisement(@PathVariable Long id, ServletRequest request) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Advertisment advertisement = advertisementService.findOne(id);
		
		if (advertisement == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (advertisement.getVerifier() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		advertisement.setVerifier((Verifier)userUtils.getLoggedUser(request));
		advertisementService.save(advertisement);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * This method enables verifier to reject advertisements
	 * @param id
	 * @return HTTPSTATUS OK if advertisement exists, HTTPSTATUS NOT_FOUND if not
	 */
	@RequestMapping(value="/reject/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> rejectAdvertisement(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Advertisment advertisement = advertisementService.findOne(id);
		
		if (advertisement == null || advertisement.getVerifier() != null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		advertisementService.remove(advertisement.getId());
		
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
	@RequestMapping(value="/inappropriate/reject/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List<Void>> rejectInappropriate(@PathVariable Long id) {
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
	@RequestMapping(value="/inappropriate/accept/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List<Void>> acceptInappropriate(@PathVariable Long id) {
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
		advertisementService.save(advertisment);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	/**
	 * This method checks input parameters
	 * @param changePasswordDTO
	 * @return True if parameters are OK, false if not
	 */
	private boolean checkInputParams(ChangePasswordDTO changePasswordDTO) {
		if (changePasswordDTO.getId() == null) {
			return false;
		} else if (changePasswordDTO.getNewPassword() == null || changePasswordDTO.getNewPassword().trim().equals("")) {
			return false;
		} else if (changePasswordDTO.getOldPassword() == null || changePasswordDTO.getOldPassword().trim().equals("")) {
			return false;
		} else if (changePasswordDTO.getConfirmPassword() == null || changePasswordDTO.getConfirmPassword().trim().equals("")) {
			return false;
		} else if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
			return false;
		}
		return true;
	}
	
}
