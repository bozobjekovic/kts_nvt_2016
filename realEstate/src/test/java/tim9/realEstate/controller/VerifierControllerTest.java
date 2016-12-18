package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasSize;
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
import static tim9.realEstate.constants.AdvertismentConstants.DB_PRICE_UNVERIFIED;
import static tim9.realEstate.constants.AdvertismentConstants.DB_TYPE_UNVERIFIED;
import static tim9.realEstate.constants.ChangePasswordConstants.*;

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
    public void testInvalidChangePassword() throws Exception { // prazan objekat, ne postojeci id korisnika, ne poklapanje starih lozinki, ne poklapanje novih lozinki
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
    
    
    
}
