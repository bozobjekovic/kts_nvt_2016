package tim9.realEstate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<User> saveUser(User user){
		user = userService.save(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/rate", method = RequestMethod.GET)
    public ResponseEntity<Void> rateUser(@RequestParam Long id, @RequestParam double rate){
    	User user = userService.findOne(id);
    	user.setNumOfRates(user.getNumOfRates() + 1);
    	user.setRate((user.getRate()*user.getNumOfRates() - 1 + rate) / user.getNumOfRates());
    	userService.save(user);
    	return new ResponseEntity<>(HttpStatus.OK);
    }

}
