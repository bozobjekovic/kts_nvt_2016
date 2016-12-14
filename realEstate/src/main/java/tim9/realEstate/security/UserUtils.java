package tim9.realEstate.security;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

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

}
