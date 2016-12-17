package tim9.realEstate.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.UserConstants.DB_EMAIL;
import static tim9.realEstate.constants.UserConstants.DB_PASSWORD;
import static tim9.realEstate.constants.UserConstants.DB_USERNAME;
import static tim9.realEstate.constants.UserConstants.NEW_ADDRESS;
import static tim9.realEstate.constants.UserConstants.NEW_BANK_ACCOUNT;
import static tim9.realEstate.constants.UserConstants.NEW_CITY;
import static tim9.realEstate.constants.UserConstants.NEW_EMAIL;
import static tim9.realEstate.constants.UserConstants.NEW_NAME;
import static tim9.realEstate.constants.UserConstants.NEW_PASSWORD;
import static tim9.realEstate.constants.UserConstants.NEW_PHONE_NUMBER;
import static tim9.realEstate.constants.UserConstants.NEW_SURNAME;
import static tim9.realEstate.constants.UserConstants.NEW_USERNAME;
import static tim9.realEstate.constants.CompanyConstants.*;
import static tim9.realEstate.constants.LocationConstants.*;

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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.TestUtil;
import tim9.realEstate.dto.LocationDTO;
import tim9.realEstate.dto.LoginUserDTO;
import tim9.realEstate.dto.RegistrateUserDTO;
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
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
    @Rollback(true)
	public void testRegistrateInvalidForBoth() throws Exception { /////// polja obavezna da se ne proslede #################### JOS KOD KOMPANIJE
		RegistrateUserDTO registrateUser = new RegistrateUserDTO();
		
		String json = TestUtil.json(registrateUser);
		mockMvc.perform(post(URL_PREFIX + "/registrate")
				.contentType(contentType)
				.content(json))
				.andExpect(status().isBadRequest());
		
		registrateUser.setAuthority("adsasd");
		
		json = TestUtil.json(registrateUser);
		mockMvc.perform(post(URL_PREFIX + "/registrate")
				.contentType(contentType)
				.content(json))
				.andExpect(status().isBadRequest());
		
		registrateUser.setPassword(NEW_PASSWORD);				// doen't have email and user name
		registrateUser.setName(NEW_NAME);
		registrateUser.setSurname(NEW_SURNAME);
		registrateUser.setPhoneNumber(NEW_PHONE_NUMBER);
		registrateUser.setAddress(NEW_ADDRESS);
		registrateUser.setCity(NEW_CITY);
		registrateUser.setAuthority("user");
		registrateUser.setBankAccount(NEW_BANK_ACCOUNT);
		
		json = TestUtil.json(registrateUser);
		mockMvc.perform(post(URL_PREFIX + "/registrate")
				.contentType(contentType)
				.content(json))
				.andExpect(status().isBadRequest());
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
    @Rollback(true)
	public void testRegistrateUser() throws Exception {
		RegistrateUserDTO registrateUser = new RegistrateUserDTO();
		
		registrateUser.setEmail(NEW_EMAIL);
		registrateUser.setUsername(NEW_USERNAME);
		registrateUser.setPassword(NEW_PASSWORD);
		registrateUser.setName(NEW_NAME);
		registrateUser.setSurname(NEW_SURNAME);
		registrateUser.setPhoneNumber(NEW_PHONE_NUMBER);
		registrateUser.setAddress(NEW_ADDRESS);
		registrateUser.setCity(NEW_CITY);
		registrateUser.setAuthority("user");
		registrateUser.setBankAccount(NEW_BANK_ACCOUNT);
		
		String json = TestUtil.json(registrateUser);
		mockMvc.perform(post(URL_PREFIX + "/registrate")
				.contentType(contentType)
				.content(json))
				.andExpect(status().isCreated());
	}
	
	@Test
	@Transactional
    @Rollback(true)
	public void testRegistrateInvalidUser() throws Exception {
		RegistrateUserDTO registrateUser = new RegistrateUserDTO();
		
		registrateUser.setEmail(DB_EMAIL);                 // existing email
		registrateUser.setUsername(NEW_USERNAME);
		registrateUser.setPassword(NEW_PASSWORD);
		registrateUser.setName(NEW_NAME);
		registrateUser.setSurname(NEW_SURNAME);
		registrateUser.setPhoneNumber(NEW_PHONE_NUMBER);
		registrateUser.setAddress(NEW_ADDRESS);
		registrateUser.setCity(NEW_CITY);
		registrateUser.setAuthority("user");
		registrateUser.setBankAccount(NEW_BANK_ACCOUNT);
		
		String json = TestUtil.json(registrateUser);
		mockMvc.perform(post(URL_PREFIX + "/registrate")
				.contentType(contentType)
				.content(json))
				.andExpect(status().isBadRequest());
		
		registrateUser.setEmail(NEW_EMAIL);
		registrateUser.setUsername(DB_USERNAME);		  // existing user name
		registrateUser.setPassword(NEW_PASSWORD);
		registrateUser.setName(NEW_NAME);
		registrateUser.setSurname(NEW_SURNAME);
		registrateUser.setPhoneNumber(NEW_PHONE_NUMBER);
		registrateUser.setAddress(NEW_ADDRESS);
		registrateUser.setCity(NEW_CITY);
		registrateUser.setAuthority("user");
		registrateUser.setBankAccount(NEW_BANK_ACCOUNT);
		
		json = TestUtil.json(registrateUser);
		mockMvc.perform(post(URL_PREFIX + "/registrate")
				.contentType(contentType)
				.content(json))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	@Transactional
    @Rollback(true)
	public void testRegistrateClerk() throws Exception {
		RegistrateUserDTO registrateUser = new RegistrateUserDTO();
		
		registrateUser.setEmail(NEW_EMAIL);
		registrateUser.setUsername(NEW_USERNAME);
		registrateUser.setPassword(NEW_PASSWORD);
		registrateUser.setName(NEW_NAME);
		registrateUser.setSurname(NEW_SURNAME);
		registrateUser.setPhoneNumber(NEW_PHONE_NUMBER);
		registrateUser.setAddress(NEW_ADDRESS);
		registrateUser.setCity(NEW_CITY);
		registrateUser.setAuthority("clerk");
		registrateUser.setBankAccount(NEW_BANK_ACCOUNT);
		
		// Company
		registrateUser.setCompanyName(tim9.realEstate.constants.CompanyConstants.NEW_NAME);
		registrateUser.setCompanyPhoneNumber(tim9.realEstate.constants.CompanyConstants.NEW_PHONE_NUMBER);
		registrateUser.setSite(NEW_SITE);
		
		// Company location
		registrateUser.setCompanyAddress(NEW_ADDRESS);
		
		// Location
		LocationDTO companyLocationDTO = new LocationDTO();
		companyLocationDTO.setCity(tim9.realEstate.constants.LocationConstants.DB_CITY);
		companyLocationDTO.setZipCode(DB_ZIP_CODE);
		companyLocationDTO.setPartOfTheCity(DB_PART_OF_THE_CITY);
		
		registrateUser.setCompanyLocation(companyLocationDTO);
		
		String json = TestUtil.json(registrateUser);
		mockMvc.perform(post(URL_PREFIX + "/registrate")
				.contentType(contentType)
				.content(json))
				.andExpect(status().isCreated());
	}
	
	public void testRegistrateInvalidClerk() {
		
	}
	
}
