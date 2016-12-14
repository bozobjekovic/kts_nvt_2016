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

import tim9.realEstate.dto.UserDTO;
import tim9.realEstate.dto.VerifierDTO;
import tim9.realEstate.model.User;
import tim9.realEstate.model.Verifier;
import tim9.realEstate.service.AuthorityService;
import tim9.realEstate.service.CompanyService;
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
	
	/**
	 * This method returns clerks that are not approved
	 * @return		ResponseEntity with HttpStatus OK
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
		verifierDTO.setPassword(passwordEncoder.encode(verifierDTO.getPassword()));
    	verifierService.save(new Verifier(verifierDTO, authorityService.findByName("VERIFIER")));
    	//TODO send mail
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
		User user = userService.findOne(id);
		if(user == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		user.setApproved(true);
		userService.save(user);
		//TODO SEND MAIL
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
	public ResponseEntity<Void> denyClerk(@RequestParam Long id, @RequestParam Long company_id) {
		//TODO SEND MAIL WITH REASON
		User user = userService.findOne(id);
		user.setCompany(null);
		userService.remove(id);
		
		companyService.remove(company_id);
    	return new ResponseEntity<>(HttpStatus.OK);
	}

}
