package tim9.realEstate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.dto.UserDTO;
import tim9.realEstate.dto.VerifierDTO;
import tim9.realEstate.mail.MailUtil;
import tim9.realEstate.model.Admin;
import tim9.realEstate.model.User;
import tim9.realEstate.model.Verifier;
import tim9.realEstate.security.UserUtils;
import tim9.realEstate.service.AuthorityService;
import tim9.realEstate.service.CompanyService;
import tim9.realEstate.service.UserService;
import tim9.realEstate.service.VerifierService;

/**
 * This class represents controller for Admin and manages with all Admin
 * functionalities.
 */
@RestController
@RequestMapping(value = "realEstate/admin")
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
	MailUtil mailUtil;

	@Autowired
	UserUtils userUtils;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Admin> getAdmin(ServletRequest request) {
		Admin admin = (Admin) userUtils.getLoggedUser(request);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	/**
	 * This method returns clerks that are not approved.
	 * 
	 * @return ResponseEntity with List of Users and HttpStatus OK
	 */
	@RequestMapping(value = "/unapproved/clerks", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getAllUnapprovedClerks(ServletRequest request) {

		List<User> users = userService.findUnapprovedClerks();

		List<UserDTO> userDTO = new ArrayList<>();
		for (User user : users)
			userDTO.add(new UserDTO(user));

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	/**
	 * This method does the registration of a verifier, sets authority and saves
	 * it to the database.
	 * 
	 * @param verifierDTO
	 *            DTO for verifier registration
	 * @return ResponseEntity with HttpStatus CREATED
	 */
	@RequestMapping(value = "/registrate/new", method = RequestMethod.POST)
	public ResponseEntity<Void> registrateVerifier(@RequestBody VerifierDTO verifierDTO) {
		if (verifierDTO.getEmail() == null || verifierDTO.getPassword() == null || verifierDTO.getUsername() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if (!userUtils.checkUniqueEmailAndUsername(verifierDTO.getEmail(), verifierDTO.getUsername())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		String password = verifierDTO.getPassword();
		verifierDTO.setPassword(passwordEncoder.encode(verifierDTO.getPassword()));
		verifierService.save(new Verifier(verifierDTO, authorityService.findByName("VERIFIER")));

		String email = verifierDTO.getEmail();
		String subject = "Join Our Site";
		String text = "Hello \nYou have been added as a verifier! \n" + "Your username : " + verifierDTO.getUsername()
				+ "\n " + "Your password : " + password;

		mailUtil.sendMail(email, subject, text);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * This method allows admin to accept clerk and his company. After that,
	 * mail is being sent to the user to notify him that his company is
	 * accepted.
	 * 
	 * @param id
	 *            id of an User
	 * @return HttpStatus OK if OK, else NOT_FOUND
	 */
	@RequestMapping(value = "/accept/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> acceptClerk(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User user = userService.findOne(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (user.isApproved() == true) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
	 * This method allows admin to deny clerk and his company. After that, mail
	 * is being sent to the user with the reason why is he denied.
	 * 
	 * @param id
	 *            id of an User
	 * @return HttpStatus OK if OK, else NOT_FOUND
	 */
	@RequestMapping(value = "/deny/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> denyClerk(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User user = userService.findOne(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (user.isApproved() == true) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		String email = user.getEmail();
		String subject = "Join Our Company";
		String text = "Hello \n We are sorry but your request has been rejected!";
		mailUtil.sendMail(email, subject, text);

		userService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
