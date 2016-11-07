package tim9.realEstate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tim9.realEstate.model.User;
import tim9.realEstate.service.UserService;

@Controller
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

}
