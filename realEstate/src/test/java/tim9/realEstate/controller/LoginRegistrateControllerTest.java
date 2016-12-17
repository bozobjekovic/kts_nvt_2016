package tim9.realEstate.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.UserConstants.DB_PASSWORD;
import static tim9.realEstate.constants.UserConstants.DB_USERNAME;
import static tim9.realEstate.constants.UserConstants.NEW_PASSWORD;
import static tim9.realEstate.constants.UserConstants.NEW_USERNAME;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.TestUtil;
import tim9.realEstate.dto.LoginUserDTO;
import tim9.realEstate.security.TokenUtils;
import tim9.realEstate.service.AuthorityService;
import tim9.realEstate.service.UserService;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class LoginRegistrateControllerTest {

	private static final String URL_PREFIX = "/realEstate";
	
	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
	AuthenticationManager authenticationManager;
	
	//@Autowired
	//private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@PostConstruct
    public void setup() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	/**
	 * <b>testLogin()</b>
	 * method tests if a controller with URL_PREFIX returns token of logged user
	 * and expected status
	 * @throws Exception
	 */
	@Test
	public void testLogin() throws Exception {
		LoginUserDTO loginUser = new LoginUserDTO();
		loginUser.setUsername(DB_USERNAME);
		loginUser.setPassword(DB_PASSWORD);
		
		String json = TestUtil.json(loginUser);
		mockMvc.perform(post(URL_PREFIX + "/login")
				.contentType(contentType)
				.content(json))
				.andExpect(status().isOk());
	}
	
	/**
	 * <b>testInvalidLogin()</b>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInvalidLogin() throws Exception {
		LoginUserDTO loginUser = new LoginUserDTO();
		loginUser.setUsername(NEW_USERNAME);
		loginUser.setPassword(NEW_PASSWORD);
		
		String json = TestUtil.json(loginUser);
		mockMvc.perform(post(URL_PREFIX + "/login")
				.contentType(contentType)
				.content(json))
				.andExpect(status().isBadRequest());
		
		String json1 = TestUtil.json(new LoginUserDTO());
		mockMvc.perform(post(URL_PREFIX + "/login")
				.contentType(contentType)
				.content(json1))
				.andExpect(status().isBadRequest());
		
	}
	
	
	
}
