package tim9.realEstate.controller;

import javax.servlet.ServletRequest;
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
import tim9.realEstate.dto.LoggedUserDTO;
import tim9.realEstate.dto.LoginUserDTO;
import tim9.realEstate.dto.RegistrateUserDTO;
import tim9.realEstate.dto.StringResponse;
import tim9.realEstate.model.Location;
import tim9.realEstate.model.User;
import tim9.realEstate.security.TokenUtils;
import tim9.realEstate.security.UserUtils;
import tim9.realEstate.service.AuthorityService;
import tim9.realEstate.service.CompanyService;
import tim9.realEstate.service.LocationService;
import tim9.realEstate.service.UserService;

/**
 * This class represents controller for login and registration.
 *
 */
@RestController
@RequestMapping(value = "realEstate")
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
	UserUtils userUtils;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	/**
	 * This method logs out an user.
	 * 
	 * @param request
	 *            HttpServletRequest request
	 * @param response
	 *            HttpServletResponse response
	 * @return ResponseEntity with HttpStatus OK
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * This method returns data for logged user or verifier or admin or cler
	 * 
	 * @param request
	 * @return returns DTO object
	 */
	@RequestMapping(value = "/user/data", method = RequestMethod.GET)
	public ResponseEntity<LoggedUserDTO> getUserData(ServletRequest request) {
		return new ResponseEntity<>(userUtils.getLoggedUserData(request), HttpStatus.OK);
	}

	/**
	 * This method represents login for user.
	 * 
	 * @param loginUser
	 *            DTO for logging
	 * @return ResponseEntity with HttpStatus OK if OK, else BAD_REQUEST
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
	 * 
	 * @param registrateUser
	 *            DTO for user registration
	 * @return ResponseEntity with HttpStatus CREATED
	 */
	@RequestMapping(value = "/registrate", method = RequestMethod.POST)
	public ResponseEntity<Void> registrate(@RequestBody RegistrateUserDTO registrateUser) {
		if (!checkInputParamsRegistrate(registrateUser)) {
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
					registrateUser.getCompanyLocation().getCity(), registrateUser.getCompanyLocation().getZipCode(),
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
	 * 
	 * @param registrateUser
	 * @return True if parameters are OK or False if NOT
	 */
	private boolean checkInputParamsRegistrate(RegistrateUserDTO registrateUser) {
		if (registrateUser.getAuthority() == null || "".equals(registrateUser.getAuthority())) {
			return false;
		}

		if ("user".equals(registrateUser.getAuthority())) {
			if (!checkUserParams(registrateUser)) {
				return false;
			}
		} else if ("clerk".equals(registrateUser.getAuthority())) {
			if (!checkUserParams(registrateUser)) {
				return false;
			}
			if (!checkCompanyParams(registrateUser)) {
				return false;
			}
		} else {
			return false;
		}

		return true;
	}

	private boolean checkExistingInputParamsRegistrate(RegistrateUserDTO registrateUser) {
		if ("user".equals(registrateUser.getAuthority())) {
			if (!checkExistingUserParams(registrateUser)) {
				return false;
			}
		} else if ("clerk".equals(registrateUser.getAuthority())) {
			if (!checkExistingUserParams(registrateUser)) {
				return false;
			}
			if (!checkExistingCompanyParams(registrateUser)) {
				return false;
			}
		} else {
			return false;
		}

		return true;
	}

	/**
	 * This method checks input parameters for login
	 * 
	 * @param loginUser
	 * @return True if parameters are OK or False if NOT
	 */
	private boolean checkInputParams(LoginUserDTO loginUser) {
		if (loginUser.getUsername() == null || "".equals(loginUser.getUsername().trim())) {
			return false;
		}
		if (loginUser.getPassword() == null || "".equals(loginUser.getPassword().trim())) {
			return false;
		}
		return true;
	}

	/**
	 * This method checks input parameters for registration new user
	 * 
	 * @param registrateUser
	 * @return True if parameters are OK or False if NOT
	 */
	private boolean checkUserParams(RegistrateUserDTO registrateUser) {
		if (registrateUser.getEmail() == null || "".equals(registrateUser.getEmail())) {
			return false;
		}
		if (registrateUser.getUsername() == null || "".equals(registrateUser.getUsername())) {
			return false;
		}
		if (registrateUser.getPassword() == null || "".equals(registrateUser.getPassword())) {
			return false;
		}
		if (registrateUser.getName() == null || "".equals(registrateUser.getName())) {
			return false;
		}
		if (registrateUser.getSurname() == null || "".equals(registrateUser.getSurname())) {
			return false;
		}
		return true;
	}

	private boolean checkExistingUserParams(RegistrateUserDTO registrateUser) {
		if (userService.findByEmail(registrateUser.getEmail()) != null) {
			return false;
		}
		if (userService.findByUsername(registrateUser.getUsername()) != null) {
			return false;
		}
		return true;
	}

	/**
	 * This method checks input parameters for registration clerk/company
	 * 
	 * @param registrateUser
	 * @return True if parameters are OK or False if NOT
	 */
	private boolean checkCompanyParams(RegistrateUserDTO registrateUser) {
		if (registrateUser.getCompanyName() == null || "".equals(registrateUser.getCompanyName())) {
			return false;
		}
		if (registrateUser.getCompanyPhoneNumber() == null || "".equals(registrateUser.getCompanyPhoneNumber())) {
			return false;
		}
		if (registrateUser.getCompanyAddress() == null || "".equals(registrateUser.getCompanyAddress())) {
			return false;
		}
		if (registrateUser.getCompanyLocation().getCity() == null
				|| "".equals(registrateUser.getCompanyLocation().getCity())) {
			return false;
		}
		return true;
	}

	private boolean checkExistingCompanyParams(RegistrateUserDTO registrateUser) {
		if (companySevice.findByName(registrateUser.getCompanyName()) != null) {
			return false;
		}
		if (companySevice.findByPhoneNumber(registrateUser.getCompanyPhoneNumber()) != null) {
			return false;
		}
		return true;
	}

}
