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

import tim9.realEstate.dto.LocationDTO;
import tim9.realEstate.dto.LoginUserDTO;
import tim9.realEstate.dto.RegistrateUserDTO;
import tim9.realEstate.model.Location;
import tim9.realEstate.model.User;
import tim9.realEstate.security.TokenUtils;
import tim9.realEstate.service.AuthorityService;
import tim9.realEstate.service.CompanyService;
import tim9.realEstate.service.LocationService;
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
	CompanyService companySevice;
	
	@Autowired
	LocationService locationService;
	
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
	public ResponseEntity<StringResponse> login(@RequestBody LoginUserDTO loginUser) {
		if (checkInputParams(loginUser)) {
			try {
				UserDetails details = userDetailsService.loadUserByUsername(loginUser.getUsername());

	            if (details.getAuthorities().size() == 0) {
	            	return new ResponseEntity<>(new StringResponse(""), HttpStatus.UNPROCESSABLE_ENTITY);
				}
	            
	        	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
	        			loginUser.getUsername(), loginUser.getPassword());

	        	Authentication authentication = authenticationManager.authenticate(token);
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            
	            return new ResponseEntity<>(new StringResponse(tokenUtils.generateToken(details)), HttpStatus.OK);
	        } catch (Exception ex) {
	            return new ResponseEntity<>(new StringResponse("Invalid login"), HttpStatus.BAD_REQUEST);
	        }
		} else {
			return new ResponseEntity<>(new StringResponse("Invalid login"), HttpStatus.BAD_REQUEST);
		}
	}

	/**
     * This method does the registration of a new user.
     * @param		registrateUser DTO for user registration
     * @return      ResponseEntity with HttpStatus CREATED
     */
	@RequestMapping(value = "/registrate", method = RequestMethod.POST)
	public ResponseEntity<Void> registrate(@RequestBody RegistrateUserDTO registrateUser) {
		if (!checkInputParamsRegistrate(registrateUser) ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (!checkExistingInputParamsRegistrate(registrateUser)) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		registrateUser.setPassword(passwordEncoder.encode(registrateUser.getPassword()));
        if ("user".equals(registrateUser.getAuthority())) {
        	userService.save(new User(registrateUser, "user", authorityService.findByName("USER")));
        	return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			Location location = locationService.findByCityAndZipCodeAndPartOfTheCity(
					registrateUser.getCompanyLocation().getCity(), 
					registrateUser.getCompanyLocation().getZipCode(), 
					registrateUser.getCompanyLocation().getPartOfTheCity());
			
			if (location == null) {
				return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
			registrateUser.setCompanyLocation(new LocationDTO(location));
			userService.save(new User(registrateUser, "clerk", authorityService.findByName("USER")));
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * This method checks input parameters for registration new user or clerk
	 * @param registrateUser
	 * @return  True if parameters are OK or False if NOT
	 */
	private boolean checkInputParamsRegistrate(RegistrateUserDTO registrateUser) {
		if (registrateUser.getAuthority() == null || registrateUser.getAuthority().equals("")) { return false; }
		
		if (registrateUser.getAuthority().equals("user")) {
			if (!checkUserParams(registrateUser)) { return false; }
		} else if (registrateUser.getAuthority().equals("clerk")) {
			if (!checkUserParams(registrateUser)) { return false; }
			if (!checkCompanyParams(registrateUser)) { return false; } 
		} else {
			return false;
		}
		
		return true;
	}
	
	private boolean checkExistingInputParamsRegistrate(RegistrateUserDTO registrateUser) {
		if (registrateUser.getAuthority().equals("user")) {
			if (!checkExistingUserParams(registrateUser)) { return false; }
		} else if (registrateUser.getAuthority().equals("clerk")) {
			if (!checkExistingUserParams(registrateUser)) { return false; }
			if (!checkExistingCompanyParams(registrateUser)) { return false; } 
		} else {
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method checks input parameters for login
	 * @param loginUser
	 * @return True if parameters are OK or False if NOT
	 */
	private boolean checkInputParams(LoginUserDTO loginUser) {
		if (loginUser.getUsername() == null || loginUser.getUsername().trim().equals("")) {
			return false;
		}
		if (loginUser.getPassword() == null || loginUser.getPassword().trim().equals("")) {
			return false;
		}
		return true;
	}
	
	/**
	 * This method checks input parameters for registration new user 
	 * @param registrateUser
	 * @return True if parameters are OK or False if NOT
	 */
	private boolean checkUserParams(RegistrateUserDTO registrateUser) {
		if (registrateUser.getEmail() == null || registrateUser.getEmail().equals("")) { return false; }
		if (registrateUser.getUsername() == null || registrateUser.getUsername().equals("")) { return false; }
		if (registrateUser.getPassword() == null || registrateUser.getPassword().equals("")) { return false; }
		if (registrateUser.getName() == null || registrateUser.getName().equals("")) { return false; }
		if (registrateUser.getSurname() == null || registrateUser.getSurname().equals("")) { return false; }
		return true;
	}
	
	private boolean checkExistingUserParams(RegistrateUserDTO registrateUser) { 
		if (userService.findByEmail(registrateUser.getEmail()) != null) { return false; }
		if (userService.findByUsername(registrateUser.getUsername()) != null) { return false; }
		return true;
	}
	
	/**
	 *  This method checks input parameters for registration clerk/company
	 * @param registrateUser
	 * @return True if parameters are OK or False if NOT
	 */
	private boolean checkCompanyParams(RegistrateUserDTO registrateUser) {
		if (registrateUser.getCompanyName() == null || registrateUser.getCompanyName().equals("")) { return false; }
		if (registrateUser.getCompanyPhoneNumber() == null || registrateUser.getCompanyPhoneNumber().equals("")) { return false; }
		if (registrateUser.getCompanyAddress() == null || registrateUser.getCompanyAddress().equals("")) { return false; }
		if (registrateUser.getCompanyLocation().getCity() == null || registrateUser.getCompanyLocation().getCity().equals("")) { return false; }
		return true;
	}
	
	private boolean checkExistingCompanyParams(RegistrateUserDTO registrateUser) {
		if (companySevice.findByName(registrateUser.getCompanyName()) != null) { return false; }
		if (companySevice.findByPhoneNumber(registrateUser.getCompanyPhoneNumber()) != null) { return false; }
		return true;
	}

}
