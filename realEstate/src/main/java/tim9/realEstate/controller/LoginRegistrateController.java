package tim9.realEstate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.dto.LoginUserDTO;
import tim9.realEstate.dto.RegistrateUserDTO;
import tim9.realEstate.model.User;
import tim9.realEstate.security.TokenUtils;
import tim9.realEstate.service.AuthorityService;
import tim9.realEstate.service.UserService;

@RestController
@RequestMapping(value="realEstate")
public class LoginRegistrateController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody LoginUserDTO loginUser) {
        try {
        	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
        			loginUser.getUsername(), loginUser.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(loginUser.getUsername());
            return new ResponseEntity<String>(tokenUtils.generateToken(details), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
        }
	}
	
	@RequestMapping(value = "/registrate", method = RequestMethod.POST)
	public ResponseEntity<Void> registrate(@RequestBody RegistrateUserDTO registrateUser) {
		registrateUser.setPassword(passwordEncoder.encode(registrateUser.getPassword()));
        if (registrateUser.getAuthority().equals("user")) {
        	userService.save(new User(registrateUser, "user", authorityService.findByName("USER")));
        	return new ResponseEntity<>(HttpStatus.OK);
		} else {
			userService.save(new User(registrateUser, "clerk", authorityService.findByName("USER")));
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

}
