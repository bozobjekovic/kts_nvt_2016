package tim9.realEstate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import tim9.realEstate.security.TokenUtils;

@Component
public class LoginTest {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	/**
	 * This method simulate login with specified
	 * user name and password
	 * @param username
	 * @param password
	 * @return Generated token
	 */
	public String login(String username, String password) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
    			username, password);
    	
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        UserDetails details = userDetailsService.loadUserByUsername(username);

        return tokenUtils.generateToken(details); 
	}

}
