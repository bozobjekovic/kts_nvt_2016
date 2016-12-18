package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.AdminConstants.DB_NONEXISTING_ID;
import static tim9.realEstate.constants.UserConstants.DB_ADDRESS;
import static tim9.realEstate.constants.UserConstants.DB_BANK_ACCOUNT;
import static tim9.realEstate.constants.UserConstants.DB_CITY;
import static tim9.realEstate.constants.UserConstants.DB_COUNT;
import static tim9.realEstate.constants.UserConstants.DB_EMAIL;
import static tim9.realEstate.constants.UserConstants.DB_ID;
import static tim9.realEstate.constants.UserConstants.DB_ID_COMPANY;
import static tim9.realEstate.constants.UserConstants.DB_IMAGE;
import static tim9.realEstate.constants.UserConstants.DB_NAME;
import static tim9.realEstate.constants.UserConstants.DB_PHONE_NUMBER;
import static tim9.realEstate.constants.UserConstants.DB_RATE;
import static tim9.realEstate.constants.UserConstants.DB_SURNAME;
import static tim9.realEstate.constants.UserConstants.DB_USERNAME;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.junit.Ignore;
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
import org.springframework.web.context.WebApplicationContext;

import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.TestUtil;
import tim9.realEstate.constants.UserConstants;
import tim9.realEstate.model.User;
import tim9.realEstate.service.UserService;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class UserControllerTest {

	private static final String URL_PREFIX = "/realEstate/users";
	
	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    private UserService userService;
    
    @PostConstruct
    public void setup() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    /**
	* This method should return all users from the
	* database.
	* Expected: method get, status OK, specified size and
	* content
	* @throws Exception 
	**/
    @Test
    public void testGetAllUsers() throws Exception {
    	mockMvc.perform(get(URL_PREFIX + "/all"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(contentType))
	        .andExpect(jsonPath("$", hasSize(DB_COUNT)))
	        .andExpect(jsonPath("$.[*].id").value(hasItem(UserConstants.DB_ID.intValue())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DB_ADDRESS)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DB_CITY)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DB_EMAIL)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DB_IMAGE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DB_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DB_SURNAME)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DB_RATE)))
            .andExpect(jsonPath("$.[*].bankAccount").value(hasItem(DB_BANK_ACCOUNT)))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DB_USERNAME)));
    }
    
    /**
	* This method tests rating user.
	* It should calculate new rate, set it and save it to the 
	* database. 
	* Expecting request to be valid, with valid rate.
	* Expected: method put, status OK, specified content
	**/
    @Test
    @Ignore
    @Transactional
    @Rollback(true)
    public void testRateUser() throws Exception {
    	User user = userService.findOne(DB_ID);
    	
    	int oldNumberOfrates = user.getNumOfRates();
    	
    	double RATE = (user.getRate()*(oldNumberOfrates + 1) + tim9.realEstate.constants.AdvertismentConstants.NEW_GIVEN_RATE) / (oldNumberOfrates + 1);
    	user.setRate(RATE);
    	
    	String json = TestUtil.json(user);
        this.mockMvc.perform(put(URL_PREFIX + "/rate?id=" + DB_ID + "&rate=" + tim9.realEstate.constants.AdvertismentConstants.NEW_GIVEN_RATE)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(DB_COUNT)))
		        .andExpect(jsonPath("$.[*].id").value(hasItem(UserConstants.DB_ID.intValue())))
	            .andExpect(jsonPath("$.[*].address").value(hasItem(DB_ADDRESS)))
	            .andExpect(jsonPath("$.[*].city").value(hasItem(DB_CITY)))
	            .andExpect(jsonPath("$.[*].email").value(hasItem(DB_EMAIL)))
	            .andExpect(jsonPath("$.[*].image").value(hasItem(DB_IMAGE)))
	            .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
	            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DB_PHONE_NUMBER)))
	            .andExpect(jsonPath("$.[*].surname").value(hasItem(DB_SURNAME)))
	            .andExpect(jsonPath("$.[*].rate").value(hasItem(DB_RATE)))
	            .andExpect(jsonPath("$.[*].bankAccount").value(hasItem(DB_BANK_ACCOUNT)))
	            .andExpect(jsonPath("$.[*].username").value(hasItem(DB_USERNAME)));
    }
    
    /**
	* This method tests rating an user.
	* Expecting invalid request, without id parameter
	* Expected: method put, status BAD_REUEST
    * @throws Exception 
	**/
    @Test
    public void testRateUserNullParam() throws Exception {
    	mockMvc.perform(put(URL_PREFIX + "/rate?rate=" + tim9.realEstate.constants.AdvertismentConstants.NEW_GIVEN_RATE))
			.andExpect(status().isBadRequest());
    }
    
    /**
   	* This method tests rating an user.
   	* Expecting invalid request, with invalid rate
   	* Expected: method put, status BAD_REUEST
    * @throws Exception 
   	**/
    @Test
    public void testRateUserWrongRate() throws Exception {
    	mockMvc.perform(put(URL_PREFIX + "/rate?id=" + DB_ID + "&rate=" + 0))
			.andExpect(status().isBadRequest());
    }
    
    /**
   	* This method tests rating an user.
   	* Expecting ID that doesn't exist.
   	* Expected: method put, status NOT_FOUND
    * @throws Exception 
   	**/
    @Test
    public void testRateUserInvalid() throws Exception {
    	mockMvc.perform(put(URL_PREFIX + "/rate?id=" + tim9.realEstate.constants.AdvertismentConstants.DB_NONEXISTING_ID + "&rate=" + tim9.realEstate.constants.AdvertismentConstants.NEW_GIVEN_RATE))
			.andExpect(status().isNotFound());
    }
    
    /**
	* This method tests applying user to the company.
	* Expecting valid request.
	* Expected: method put, status OK
    * @throws Exception 
	**/
    @Test
    @Ignore
    public void testApplyToCompany() throws Exception {
    	mockMvc.perform(put(URL_PREFIX + "/apply?id_company=" + DB_ID_COMPANY))
			.andExpect(status().isOk());
    }
    
    /**
	* This method tests applying user to the company.
	* Expecting invalid request, without id parameter.
	* Expected: method put, status BAD_REQUEST
    * @throws Exception 
	**/
    @Test
    public void testApplyToCompanyNullParameters() throws Exception {
    	mockMvc.perform(put(URL_PREFIX + "/apply"))
			.andExpect(status().isBadRequest());
    }
    
    /**
	* This method tests applying user to the company.
	* Expecting valid request, but non existing id
	* of a company.
	* Expected: method put, status NOT_FOOUND
    * @throws Exception 
	**/
    @Test
    public void testApplyToCompanyInvalid() throws Exception {
    	mockMvc.perform(put(URL_PREFIX + "/apply" + tim9.realEstate.constants.AdvertismentConstants.DB_NONEXISTING_ID))
			.andExpect(status().isNotFound());
    }
    
    
    /**
	 * This method should test accepting user to the
	 * company.
	 * Expecting request to be valid.
	 * Expected: method put, status OK
	 * @throws Exception 
	 **/
	 @Test
	 @Transactional
	 @Rollback(true)
	 public void testAcceptUser() throws Exception{
	    this.mockMvc.perform(put(URL_PREFIX + "/accept?id=" + DB_ID + "&id_company=" + DB_ID_COMPANY)
            .contentType(contentType))
            .andExpect(status().isOk());
	}
 
	 /**
	 * This method should test accepting user to the
	 * company.
	 * Expecting request to be invalid, without request parameters.
	 * Expected: method put, status BAD_REQUEST
	 * @throws Exception 
	 **/
	 @Test
	 @Transactional
	 @Rollback(true)
	 public void testAcceptUserNullParam() throws Exception{
	    this.mockMvc.perform(put(URL_PREFIX + "/accept?id=" + DB_ID)
            .contentType(contentType))
            .andExpect(status().isBadRequest());
	    
	    this.mockMvc.perform(put(URL_PREFIX + "/accept?id_company=" + DB_ID_COMPANY)
	            .contentType(contentType))
	            .andExpect(status().isBadRequest());
	}
	 
	 /**
	 * This method should test accepting user to the
	 * company.
	 * Expecting request to be valid, but id of user to be
	 * non existing, or maybe someone else already approved
	 * him.
	 * Expected: method post, status NOT_FOUND
	 * @throws Exception 
	 **/
	 @Test
	 @Transactional
	 @Rollback(true)
	 public void testAcceptUserInvalid() throws Exception{
	    this.mockMvc.perform(put(URL_PREFIX + "/accept?id=" + DB_NONEXISTING_ID + "&id_company=" + DB_ID_COMPANY)
            .contentType(contentType))
            .andExpect(status().isNotFound());
	    
	    this.mockMvc.perform(put(URL_PREFIX + "/accept?id=" + DB_ID + "&id_company=" + DB_NONEXISTING_ID)
	            .contentType(contentType))
	            .andExpect(status().isNotFound());
	 }
	 
	 /**
	 * This method should test denying user to join the
	 * company.
	 * Expecting request to be valid.
	 * Expected: method put, status OK
	 * @throws Exception 
	 **/
	 @Test
	 @Transactional
	 @Rollback(true)
	 public void testDenyUser() throws Exception{
	    this.mockMvc.perform(put(URL_PREFIX + "/deny?id=" + DB_ID)
            .contentType(contentType))
            .andExpect(status().isOk());
	}
 
	 /**
	 * This method should test denying user to join the
	 * company.
	 * Expecting request to be invalid, without request parameter.
	 * Expected: method put, status BAD_REQUEST
	 * @throws Exception 
	 **/
	 @Test
	 @Transactional
	 @Rollback(true)
	 public void testDenyUserNullParam() throws Exception{
	    this.mockMvc.perform(put(URL_PREFIX + "/deny")
            .contentType(contentType))
            .andExpect(status().isBadRequest());
	}
	 
	 /**
	 * This method should test denying user to join the
	 * company.
	 * Expecting request to be valid, but id of user to be
	 * non existing, or maybe someone else already denied
	 * him.
	 * Expected: method post, status NOT_FOUND
	 * @throws Exception 
	 **/
	 @Test
	 @Transactional
	 @Rollback(true)
	 public void testDenyUserInvalid() throws Exception{
	    this.mockMvc.perform(put(URL_PREFIX + "/deny?id=" + DB_NONEXISTING_ID)
            .contentType(contentType))
            .andExpect(status().isNotFound());
	}


}
