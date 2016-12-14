package tim9.realEstate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.dto.UserDTO;
import tim9.realEstate.model.Company;
import tim9.realEstate.model.User;
import tim9.realEstate.security.UserUtils;
import tim9.realEstate.service.CompanyService;
import tim9.realEstate.service.UserService;

/**
 * This class represents controller for User
 * and manages with all User functionalities.
 */
@RestController
@RequestMapping(value="realEstate/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	UserUtils userUtils;

	/**
     * This method gets all Users from the database
     * and then creates a list of DTO objects.
     * @return      ResponseEntity List with all DTO Users and HttpStatus OK
     */
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> users = userService.findAll();

		List<UserDTO> userDTO = new ArrayList<>();
		for (User user : users)
			userDTO.add(new UserDTO(user));
		
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	/**
     * This method sets a new rate for an User.
     * It gets a given rate as a parameter and then calculates
     * the new average rate and saves it in the database.
     * @param		id		User's id
     * @param		rate	given rate
     * @return      ResponseEntity DTO User and HttpStatus OK if OK,
     * 				else NOT_FOUND status
     */
	@RequestMapping(value="/rate", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> rateUser(@RequestParam Long id, @RequestParam double rate){
    	User user = userService.findOne(id);
    	if(user == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	user.setNumOfRates(user.getNumOfRates() + 1);
    	user.setRate((user.getRate()*user.getNumOfRates() + rate) / user.getNumOfRates());
    	userService.save(user);
    	return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }
	
	/**
     * This method allows user to apply to be
     * part of a company.
     * Sending request to the clerk, clerk decides if user
     * will be accepted or denied.
     * @param		id_company id of Company
     * @return      HttpStatus OK if OK, else NOT_FOUND
     */
	@RequestMapping(value = "/apply", method = RequestMethod.PUT)
	public ResponseEntity<Void> applyToCompany(@RequestParam Long id_company, ServletRequest request) {
		User user = (User)userUtils.getLoggedUser(request);
		Company company = companyService.findOne(id_company);
		if(company == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		user.setAppliedCompany(company);
		userService.save(user);
    	return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
     * This method allows clerk to accept user to join
     * the company.
     * After that, mail is being sent to the user.
     * @param		id id of an User
     * @param		id_company id of a Company
     * @return      HttpStatus OK if OK, else NOT_FOUND
     */
	@RequestMapping(value = "/accept", method = RequestMethod.PUT)
	public ResponseEntity<Void> acceptClerk(@RequestParam Long id, @RequestParam Long id_company) {
		User user = userService.findOne(id);
		Company company = companyService.findOne(id_company);
		if(user == null || company == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		user.setAppliedCompany(null);
		user.setCompany(company);
		userService.save(user);
		//TODO SEND MAIL
    	return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
     * This method allows clerk to deny user to join the company.
     * After that, mail is being sent to the user
     * with the reason why he is denied.
     * @param		id id of an User
     * @return      HttpStatus OK if OK, else NOT_FOUND
     */
	@RequestMapping(value = "/deny", method = RequestMethod.PUT)
	public ResponseEntity<Void> denyClerk(@RequestParam Long id) {
		User user = userService.findOne(id);
		user.setAppliedCompany(null);
		userService.save(user);
		//TODO SEND MAIL WITH REASON
    	return new ResponseEntity<>(HttpStatus.OK);
	}

}
