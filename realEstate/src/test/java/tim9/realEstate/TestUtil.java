package tim9.realEstate;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import tim9.realEstate.security.TokenUtils;

/**
 * This class represents test utils
 */
public class TestUtil {
	
	@Autowired
	static AuthenticationManager authenticationManager;
	
	@Autowired
	static UserDetailsService userDetailsService;
	
	@Autowired
	static TokenUtils tokenUtils;
	
	/**
	 * This method convert specified object to json 
	 * @param object
	 * @return JSON
	 * @throws IOException
	 */
	public static String json(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        return mapper.writeValueAsString(object);
    }
	
	/**
	 * This method simulate login with specified
	 * user name and password
	 * @param username
	 * @param password
	 * @return Generated token
	 */
	public static String login(String username, String password) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
    			username, password);
    	
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        UserDetails details = userDetailsService.loadUserByUsername(username);

        return tokenUtils.generateToken(details); 
	}
}
