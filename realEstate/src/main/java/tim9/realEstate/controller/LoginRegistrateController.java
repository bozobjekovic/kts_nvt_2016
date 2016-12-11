package tim9.realEstate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

/**
 * This class represents controller for login and registration.
 *
 */
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
	
	/**
     * This method logs out an user.
     * @param		request HttpServletRequest request
     * @param		response HttpServletResponse response
     * @return      ResponseEntity with HttpStatus OK
     */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
     * This method represents login for user.
 	 * @param		loginUser DTO for logging
     * @return      ResponseEntity with HttpStatus OK if OK,
     * 				else BAD_REQUEST
     */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody LoginUserDTO loginUser) {
        try {
        	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
        			loginUser.getUsername(), loginUser.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(loginUser.getUsername());
            return new ResponseEntity<>(tokenUtils.generateToken(details), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid login", HttpStatus.BAD_REQUEST);
        }
	}
	
	/**
     * This method does the registration of a new user.
     * @param		registrateUser DTO for user registration
     * @return      ResponseEntity with HttpStatus CREATED
     */
	@RequestMapping(value = "/registrate", method = RequestMethod.POST)
	public ResponseEntity<Void> registrate(@RequestBody RegistrateUserDTO registrateUser) {
		registrateUser.setPassword(passwordEncoder.encode(registrateUser.getPassword()));
        if ("user".equals(registrateUser.getAuthority())) {
        	userService.save(new User(registrateUser, "user", authorityService.findByName("USER")));
        	return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			userService.save(new User(registrateUser, "clerk", authorityService.findByName("USER")));
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}

}
