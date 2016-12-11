package tim9.realEstate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.dto.UserDTO;
import tim9.realEstate.model.User;
import tim9.realEstate.service.UserService;

@RestController
@RequestMapping(value="realEstate/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
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

}
