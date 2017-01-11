package tim9.realEstate.security;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import tim9.realEstate.dto.LoggedUserDTO;
import tim9.realEstate.model.Admin;
import tim9.realEstate.model.User;
import tim9.realEstate.model.Verifier;
import tim9.realEstate.service.AdminService;
import tim9.realEstate.service.UserService;
import tim9.realEstate.service.VerifierService;

/**
 * This class manages with logged user
 * @author bbozo
 *
 */
@Component
public class UserUtils {
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VerifierService verifierService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * This method returns logged user from token
	 * @param token
	 * @return User if exist, null if not
	 */
	public Object getLoggedUser(ServletRequest request) {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String token = httpRequest.getHeader("X-Auth-Token");
		
		UserDetails details = userDetailsService.loadUserByUsername(tokenUtils.getUsernameFromToken(token));
		if (details.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
			return adminService.findByUsername(details.getUsername());
		} else if (details.getAuthorities().contains(new SimpleGrantedAuthority("USER"))) {
			return userService.findByUsername(details.getUsername());
		} else if (details.getAuthorities().contains(new SimpleGrantedAuthority("VERIFIER"))) {
			return verifierService.findByUsername(details.getUsername());
		} else {
			return null;
		}
	}
	
	public LoggedUserDTO getLoggedUserData(ServletRequest request) {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String token = httpRequest.getHeader("X-Auth-Token");
		
		UserDetails details = userDetailsService.loadUserByUsername(tokenUtils.getUsernameFromToken(token));
		if (details.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
			Admin admin = adminService.findByUsername(details.getUsername());
			return new LoggedUserDTO(admin.getName(), admin.getSurname(), "ADMIN");
		} else if (details.getAuthorities().contains(new SimpleGrantedAuthority("USER"))) {
			User user = userService.findByUsername(details.getUsername());
			if (user.isClerk()) {
				return new LoggedUserDTO(user.getName(), user.getSurname(), "CLERK");
			}
			return new LoggedUserDTO(user.getName(), user.getSurname(), "USER");
		} else if (details.getAuthorities().contains(new SimpleGrantedAuthority("VERIFIER"))) {
			Verifier verifier = verifierService.findByUsername(details.getUsername());
			return new LoggedUserDTO(verifier.getUsername(), "", "VERIFIER");
		} else {
			return null;
		}
	}
	
	public boolean checkUniqueEmailAndUsername(String email, String username) {
		
		if (adminService.findByUsername(username) != null || adminService.findByEmail(email) != null) {
			return false;
		} else if (userService.findByUsername(username) != null || userService.findByEmail(email) != null) {
			return false;
		} else if (verifierService.findByUsername(username) != null || verifierService.findByEmail(email) != null) {
			return false;
		} else {
			return true;
		}
	}

}
