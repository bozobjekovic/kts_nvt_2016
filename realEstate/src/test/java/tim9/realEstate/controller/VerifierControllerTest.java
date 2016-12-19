package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.AdvertismentConstants.DB_CITY_UNVERIFIED;
import static tim9.realEstate.constants.AdvertismentConstants.DB_COUNT_UNVERIFIED;
import static tim9.realEstate.constants.AdvertismentConstants.DB_ID_UNVERIFIED;
import static tim9.realEstate.constants.AdvertismentConstants.DB_IMAGE_UNVERIFIED;
import static tim9.realEstate.constants.AdvertismentConstants.DB_LAND_SIZE_UNVERIFIED;
import static tim9.realEstate.constants.AdvertismentConstants.DB_NAME_UNVERIFIED;
import static tim9.realEstate.constants.AdvertismentConstants.DB_NONEXISTING_ID;
import static tim9.realEstate.constants.AdvertismentConstants.DB_PRICE_UNVERIFIED;
import static tim9.realEstate.constants.AdvertismentConstants.DB_TYPE_UNVERIFIED;
import static tim9.realEstate.constants.ChangePasswordConstants.DB_ID;
import static tim9.realEstate.constants.ChangePasswordConstants.DB_NEW_CONFIRM_PASSWORD;
import static tim9.realEstate.constants.ChangePasswordConstants.DB_NEW_ID;
import static tim9.realEstate.constants.ChangePasswordConstants.DB_NEW_PASSWORD;
import static tim9.realEstate.constants.ChangePasswordConstants.DB_NEW_WRONG_CONFIRM_PASSWORD;
import static tim9.realEstate.constants.ChangePasswordConstants.DB_OLD_PASSWORD;
import static tim9.realEstate.constants.ChangePasswordConstants.DB_OLD_WRONG_PASSWORD;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import tim9.realEstate.LoginTest;
import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.TestUtil;
import tim9.realEstate.dto.ChangePasswordDTO;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class VerifierControllerTest {

	private static final String URL_PREFIX = "/realEstate/verifiers";
	
	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    LoginTest loginTest;
    
    @PostConstruct
    public void setup() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    /**
     * This method should return all advertisements from the
	 * database that are unverified
	 * Expected: Status OK and specified size
     * @throws Exception
     */
    @Test
    public void testGetAllUnverifiedAdvertisement() throws Exception{
    	this.mockMvc.perform(get(URL_PREFIX + "/advertisement/unverified"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(contentType))
	        .andExpect(jsonPath("$", hasSize(DB_COUNT_UNVERIFIED)))
	        .andExpect(jsonPath("$.[0].id").value(DB_ID_UNVERIFIED.intValue()))
            .andExpect(jsonPath("$.[0].name").value(DB_NAME_UNVERIFIED))
	    	.andExpect(jsonPath("$.[0].city").value(DB_CITY_UNVERIFIED))
	    	.andExpect(jsonPath("$.[0].price").value(DB_PRICE_UNVERIFIED))
	    	.andExpect(jsonPath("$.[0].landSize").value(DB_LAND_SIZE_UNVERIFIED))
	    	.andExpect(jsonPath("$.[0].image").value(DB_IMAGE_UNVERIFIED))
	    	.andExpect(jsonPath("$.[0].type").value(DB_TYPE_UNVERIFIED));
    }
    
    /**
     * This method should change password on 
     * existing verifier in database with valid 
     * input parameters.
     * Expected: Status OK
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testChangePassword() throws Exception {
    	ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
    	
    	changePasswordDTO.setId(DB_ID);
    	changePasswordDTO.setOldPassword(DB_OLD_PASSWORD);
    	changePasswordDTO.setNewPassword(DB_NEW_PASSWORD);
    	changePasswordDTO.setConfirmPassword(DB_NEW_CONFIRM_PASSWORD);
    	
    	String json = TestUtil.json(changePasswordDTO);
    	this.mockMvc.perform(put(URL_PREFIX)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isOk());
    }
    
    /**
     * This method should test change password on 
     * existing verifier in database with invalid 
     * input parameters like empty object, invalid 
     * verifier id, wrong old password, incorrect
     * new and confirm password.
     * Expected: Status Not Found if id is not valid
     * or Bad Request if input parameters are incorrect.
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testChangePasswordInvalid() throws Exception { // prazan objekat, ne postojeci id korisnika, ne poklapanje starih lozinki, ne poklapanje novih lozinki
    	ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
    	
    	String json = TestUtil.json(changePasswordDTO);							// empty object
    	this.mockMvc.perform(put(URL_PREFIX)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isBadRequest());
    	// TEST #########################################
    	
    	changePasswordDTO.setId(DB_ID);
    	changePasswordDTO.setOldPassword(DB_OLD_PASSWORD);	
    	changePasswordDTO.setNewPassword(DB_NEW_PASSWORD);
    	changePasswordDTO.setConfirmPassword(DB_NEW_WRONG_CONFIRM_PASSWORD);	// wrong new and confirm password
    	
    	json = TestUtil.json(changePasswordDTO);
    	this.mockMvc.perform(put(URL_PREFIX)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isBadRequest());
    	// TEST #########################################
    	
    	changePasswordDTO.setOldPassword(DB_OLD_WRONG_PASSWORD);				// wrong just old password
    	changePasswordDTO.setConfirmPassword(DB_NEW_CONFIRM_PASSWORD);			
    	
    	json = TestUtil.json(changePasswordDTO);
    	this.mockMvc.perform(put(URL_PREFIX)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isBadRequest());
    	// TEST #########################################
    	
    	changePasswordDTO.setId(DB_NEW_ID);										// invalid verifier id
    	changePasswordDTO.setOldPassword(DB_OLD_PASSWORD);
    	
    	json = TestUtil.json(changePasswordDTO);
    	this.mockMvc.perform(put(URL_PREFIX)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isNotFound());
    	// TEST #########################################
	}
    
    /**
     * This method should test accepting advertisement
     * with valid input parameters and registrated
     * verifier
     * Excepted: Status OK
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testAcceptAdvertisement() throws Exception {
    	String token = loginTest.login(
    			tim9.realEstate.constants.VerifierConstants.DB_USERNAME,
    			tim9.realEstate.constants.VerifierConstants.DB_PASSWORD);
    	
    	this.mockMvc.perform(put(URL_PREFIX + "/accept?id=" + DB_ID_UNVERIFIED)
                .header("X-Auth-Token", token)
    			.contentType(contentType))
                .andExpect(status().isOk());
    }
    
    /**
     * This method should test accepting advertisement
     * with wrong input parameters like empty params,
     * doesn't existing advertisement id and 
     * specified advertisement id that has verifier
     * Excepted: Not Found id advertisement with 
     * specified id doesn't exist or Bad Request 
     * if input parameters are wrong
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testAcceptAdvertisementInvalid() throws Exception {
    	String token = loginTest.login(
    			tim9.realEstate.constants.VerifierConstants.DB_USERNAME,
    			tim9.realEstate.constants.VerifierConstants.DB_PASSWORD);
    	
    	this.mockMvc.perform(put(URL_PREFIX + "/accept?id=")
                .header("X-Auth-Token", token)
    			.contentType(contentType))
                .andExpect(status().isBadRequest());
    	
    	this.mockMvc.perform(put(URL_PREFIX + "/accept?id=" + DB_NONEXISTING_ID)
                .header("X-Auth-Token", token)
    			.contentType(contentType))
                .andExpect(status().isNotFound());
    	
    	this.mockMvc.perform(put(URL_PREFIX + "/accept?id=" + tim9.realEstate.constants.AdvertismentConstants.DB_ID)
                .header("X-Auth-Token", token)
    			.contentType(contentType))
                .andExpect(status().isBadRequest());
    }
    
    /**
     * This method should test reject advertisement 
     * with valid input parameters.
     * Expected: Status OK
     * deleted
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testRejectAdvertisement() throws Exception {
    	this.mockMvc.perform(delete(URL_PREFIX + "/reject?id=" + tim9.realEstate.constants.VerifierConstants.DB_ID_ADVERTISEMENT)
                .contentType(contentType))
                .andExpect(status().isOk());
    }
    
    /**
     * This method should test rejecting advertisement 
     * with wrong input parameters, like empty parameter,
     * parameter that doesn't exists in database and
     * id advertisement which is already accepted.
     * Expected: Status Not Found if id is not valid and
     * if id already accepted in database
     * or Bad Request if input parameter is incorrect.
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testRejectAdvertisementInvalid() throws Exception {
    	this.mockMvc.perform(delete(URL_PREFIX + "/reject?id=")
                .contentType(contentType))
                .andExpect(status().isBadRequest());
    	// TEST ############################################
    	
    	this.mockMvc.perform(delete(URL_PREFIX + "/reject?id=" + tim9.realEstate.constants.VerifierConstants.DB_ID_WRONG_ADVERTISEMENT)		// doesn't existing id
                .contentType(contentType))
                .andExpect(status().isNotFound());
    	// TEST ############################################
    	
    	this.mockMvc.perform(delete(URL_PREFIX + "/reject?id=" + tim9.realEstate.constants.VerifierConstants.DB_ID_ACCEPTED_ADVERTISEMENT)		// accepted id
                .contentType(contentType))
                .andExpect(status().isNotFound());
    	// TEST ############################################
    }
}
