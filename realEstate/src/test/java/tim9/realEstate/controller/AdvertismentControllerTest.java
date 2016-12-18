package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.AdvertismentConstants.DB_CITY;
import static tim9.realEstate.constants.AdvertismentConstants.DB_COUNT;
import static tim9.realEstate.constants.AdvertismentConstants.DB_COUNT_PURPOSE;
import static tim9.realEstate.constants.AdvertismentConstants.DB_ID;
import static tim9.realEstate.constants.AdvertismentConstants.DB_IMAGE;
import static tim9.realEstate.constants.AdvertismentConstants.DB_NAME;
import static tim9.realEstate.constants.AdvertismentConstants.DB_NONEXISTING_ID;
import static tim9.realEstate.constants.AdvertismentConstants.DB_PHONE_NUMBER;
import static tim9.realEstate.constants.AdvertismentConstants.DB_PRICE;
import static tim9.realEstate.constants.AdvertismentConstants.DB_PURPOSE;
import static tim9.realEstate.constants.AdvertismentConstants.DB_RATE;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_DATE;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_DATE_STR;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_GIVEN_RATE;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_PHONE_NUMBER;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_PURPOSE;
import static tim9.realEstate.constants.AdvertismentConstants.PAGE_SIZE_CONTROLLER;

import java.nio.charset.Charset;
import java.util.Date;

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

import tim9.realEstate.LoginTest;
import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.TestUtil;
import tim9.realEstate.dto.AdvertismentCreateDTO;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.RealEstate;
import tim9.realEstate.service.AdvertismentService;
import tim9.realEstate.service.LocationService;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class AdvertismentControllerTest {
	
private static final String URL_PREFIX = "/realEstate/advertisments";
	
	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private AdvertismentService advertismentService;
    
    @Autowired
    private LocationService locationService;
    
    @Autowired
    private LoginTest loginTest;
    
    @PostConstruct
    public void setup() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
	 * This method tests getting all advertisements from the
	 * database that are not deleted and that are verified.
	 * Expected: method get, status OK, specified size and
	 * content
     * @throws Exception 
	 **/
    @Test
    public void testGetAllAdvertisments() throws Exception {
    	mockMvc.perform(get(URL_PREFIX + "/all"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(contentType))
	        .andExpect(jsonPath("$", hasSize(DB_COUNT)))
	        .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DB_CITY)))
	    	.andExpect(jsonPath("$.[*].price").value(hasItem(DB_PRICE)))
	    	.andExpect(jsonPath("$.[*].landSize").value(hasItem(tim9.realEstate.constants.RealEstateConstants.DB_LAND_SIZE)))
	    	.andExpect(jsonPath("$.[*].image").value(hasItem(DB_IMAGE)))
	    	.andExpect(jsonPath("$.[*].type").value(hasItem(tim9.realEstate.constants.RealEstateConstants.DB_TYPE)));
    }
    
     /**
	 * This method tests getting a page of advertisements from the
	 * database that are not deleted and that are verified.
	 * Expected: method get, status OK, specified size and
	 * content
     * @throws Exception 
	 **/
    @Test
    public void testGetAdvertismentPage() throws Exception {
    	mockMvc.perform(get(URL_PREFIX + "?page=0&size=" + PAGE_SIZE_CONTROLLER))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType(contentType))
    		.andExpect(jsonPath("$", hasSize(PAGE_SIZE_CONTROLLER)))
    		.andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DB_CITY)))
	    	.andExpect(jsonPath("$.[*].price").value(hasItem(DB_PRICE)))
	    	.andExpect(jsonPath("$.[*].landSize").value(hasItem(tim9.realEstate.constants.RealEstateConstants.DB_LAND_SIZE)))
	    	.andExpect(jsonPath("$.[*].image").value(hasItem(DB_IMAGE)))
	    	.andExpect(jsonPath("$.[*].type").value(hasItem(tim9.realEstate.constants.RealEstateConstants.DB_TYPE)));
    }
    
    /**
	* This method tests getting an advertisements from the
	* database with a specified ID.
	* Expected: method get, status OK, specified size and
	* content
    * @throws Exception 
	**/
    @Test
    public void testGetAdvertisment() throws Exception {
    	mockMvc.perform(get(URL_PREFIX + "/" + DB_ID))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType(contentType))
    		.andExpect(jsonPath("$.advertismentId").value(DB_ID.intValue()))
            .andExpect(jsonPath("$.purpose").value(DB_PURPOSE))
	    	.andExpect(jsonPath("$.phoneNumber").value(DB_PHONE_NUMBER))
	    	.andExpect(jsonPath("$.rate").value(DB_RATE))
	    	.andExpect(jsonPath("$.realEstateId").value(tim9.realEstate.constants.RealEstateConstants.DB_ID))
	    	.andExpect(jsonPath("$.name").value(DB_NAME))
	    	.andExpect(jsonPath("$.price").value(DB_PRICE))
	    	.andExpect(jsonPath("$.landSize").value(tim9.realEstate.constants.RealEstateConstants.DB_LAND_SIZE))
	    	.andExpect(jsonPath("$.techEquipment").value(tim9.realEstate.constants.RealEstateConstants.DB_TEACH_EQUIPMENT))
	    	.andExpect(jsonPath("$.heatingType").value(tim9.realEstate.constants.RealEstateConstants.DB_HEATING_TYPE))
	    	.andExpect(jsonPath("$.image").value(DB_IMAGE))
	    	.andExpect(jsonPath("$.numOfBathRooms").value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_BATH_ROOMS))
	    	.andExpect(jsonPath("$.numOfBedRooms").value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_BED_ROOMS))
	    	.andExpect(jsonPath("$.numOfFlors").value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_FLORS))
	    	.andExpect(jsonPath("$.buildYear").value(tim9.realEstate.constants.RealEstateConstants.DB_BUILD_YEAR))
	    	.andExpect(jsonPath("$.type").value(tim9.realEstate.constants.RealEstateConstants.DB_TYPE));
    }
    
    /**
	* This method tests getting an advertisements from the
	* database with a specified ID.
	* Expecting non existing ID
	* Expected: method get, status BAD_REUEST, specified size and
	* content
    * @throws Exception 
	**/
    @Test
    public void testGetAdvertismentNullParam() throws Exception {
    	mockMvc.perform(get(URL_PREFIX + "/" + DB_NONEXISTING_ID))
			.andExpect(status().isNotFound());
    }
    
    
    
    /**
  	* This method tests adding new Advertisement and
  	* saving it to the database.
  	* Expected all input fields to be valid.
  	* Expected: method post, status CREATED, specified size and
	* content
    * @throws Exception
  	**/
    @Test
  	@Transactional
  	@Rollback(true)
  	public void testSaveAdvertisment() throws Exception {
		
	  	Advertisment advertisment = new Advertisment();
	  	RealEstate realEstate = new RealEstate();
	  	
    	advertisment.setName(tim9.realEstate.constants.RealEstateConstants.NEW_NAME);
    	advertisment.setPrice(tim9.realEstate.constants.RealEstateConstants.NEW_PRICE);
    	advertisment.setPublicationDate(new Date());
    	advertisment.setBackgroundImage(tim9.realEstate.constants.RealEstateConstants.NEW_IMAGE);
		advertisment.setActiveUntil(NEW_DATE);
		advertisment.setPurpose(NEW_PURPOSE);
		advertisment.setPhoneNumber(NEW_PHONE_NUMBER);
		
		realEstate.setLocation(locationService.findOne(tim9.realEstate.constants.LocationConstants.DB_ID));
		realEstate.setAddress(tim9.realEstate.constants.AdminConstants.NEW_ADDRESS);
		realEstate.setLandSize(tim9.realEstate.constants.RealEstateConstants.NEW_LAND_SIZE);
		realEstate.setTechEquipment(tim9.realEstate.constants.RealEstateConstants.NEW_TEACH_EQUIPMENT);
		realEstate.setNumOfBathRooms(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BATH_ROOMS);
		realEstate.setNumOfBedRooms(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BED_ROOMS);
		realEstate.setNumOfFlors(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_FLORS);
		realEstate.setBuildYear(tim9.realEstate.constants.RealEstateConstants.NEW_BUILD_YEAR);
		realEstate.setCategory(tim9.realEstate.constants.RealEstateConstants.NEW_CATEGORY);
		realEstate.setType(tim9.realEstate.constants.RealEstateConstants.NEW_TYPE);
		
		advertisment.setRealEstate(realEstate);
	  	
	  	String token = loginTest.login(tim9.realEstate.constants.UserConstants.DB_USERNAME, tim9.realEstate.constants.UserConstants.DB_PASSWORD);
	  	
	  	AdvertismentCreateDTO advertismentDTO = new AdvertismentCreateDTO(advertisment, realEstate);
	  	String json = TestUtil.json(advertismentDTO);
	  	this.mockMvc.perform(post(URL_PREFIX)
	  			  .header("X-Auth-Token", token)
	              .contentType(contentType)
	              .content(json))
	              .andExpect(status().isCreated());
    }
    
    /**
	* This method tests if a controller with URL_PREFIX and given page and size
	* returns all advertisements that are not deleted or unverified
	* with specified purpose.
	* Expected: method get, status OK, specified size and
	* content
    * @throws Exception
	**/
	@Test
	public void testGetAdvertismentsByPurpose() throws Exception {
	   	mockMvc.perform(get(URL_PREFIX + "/purpose/" + DB_PURPOSE + "?page=0&size=" + PAGE_SIZE_CONTROLLER))
	   		.andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$", hasSize(DB_COUNT_PURPOSE)))
	        .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DB_CITY)))
	    	.andExpect(jsonPath("$.[*].price").value(hasItem(DB_PRICE)))
	    	.andExpect(jsonPath("$.[*].landSize").value(hasItem(tim9.realEstate.constants.RealEstateConstants.DB_LAND_SIZE)))
	    	.andExpect(jsonPath("$.[*].image").value(hasItem(DB_IMAGE)))
	    	.andExpect(jsonPath("$.[*].type").value(hasItem(tim9.realEstate.constants.RealEstateConstants.DB_TYPE)));
	}
	
	/**
	* This method tests if a controller with URL_PREFIX and given page and size
	* returns expected status,
	* content type, and contains specified element.
	* Expecting invalid request.
	* Expected: method get, status BAD_REQUEST
	* @throws Exception
	**/
	@Test
	public void testGetAdvertismentsByPurposeInvalid() throws Exception {
	   	mockMvc.perform(get(URL_PREFIX + "/purpose"))
	   		.andExpect(status().isBadRequest());
	}
	
	private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
	/**
	* This method tests rating advertisement.
	* It should calculate new rate, set it and save it to the 
	* database. 
	* Expecting request to be valid, with valid rate.
	* Expected: method put, status OK, specified content
	**/
    @Test
    @Transactional
    @Rollback(true)
    public void testRateAdvertisment() throws Exception {
    	Advertisment advertisment = advertismentService.findOne(DB_ID);

    	advertisment.setNumberOfRates(advertisment.getNumberOfRates() + 1);
    	double RATE = round(
    			((advertisment.getRate()*(advertisment.getNumberOfRates()-1)) + NEW_GIVEN_RATE) / advertisment.getNumberOfRates(), 2);

        this.mockMvc.perform(put(URL_PREFIX + "/rate?id=" + DB_ID + "&rate=" + NEW_GIVEN_RATE)
                .contentType(contentType))
                .andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.advertismentId").value(DB_ID.intValue()))
		        .andExpect(jsonPath("$.purpose").value(DB_PURPOSE))
		    	.andExpect(jsonPath("$.phoneNumber").value(DB_PHONE_NUMBER))
		    	.andExpect(jsonPath("$.rate").value(RATE))
		    	.andExpect(jsonPath("$.realEstateId").value(tim9.realEstate.constants.RealEstateConstants.DB_ID))
		    	.andExpect(jsonPath("$.name").value(DB_NAME))
		    	.andExpect(jsonPath("$.price").value(DB_PRICE))
		    	.andExpect(jsonPath("$.landSize").value(tim9.realEstate.constants.RealEstateConstants.DB_LAND_SIZE))
		    	.andExpect(jsonPath("$.techEquipment").value(tim9.realEstate.constants.RealEstateConstants.DB_TEACH_EQUIPMENT))
		    	.andExpect(jsonPath("$.heatingType").value(tim9.realEstate.constants.RealEstateConstants.DB_HEATING_TYPE))
		    	.andExpect(jsonPath("$.image").value(DB_IMAGE))
		    	.andExpect(jsonPath("$.numOfBathRooms").value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_BATH_ROOMS))
		    	.andExpect(jsonPath("$.numOfBedRooms").value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_BED_ROOMS))
		    	.andExpect(jsonPath("$.numOfFlors").value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_FLORS))
		    	.andExpect(jsonPath("$.buildYear").value(tim9.realEstate.constants.RealEstateConstants.DB_BUILD_YEAR))
		    	.andExpect(jsonPath("$.type").value(tim9.realEstate.constants.RealEstateConstants.DB_TYPE));
    }
    
    /**
	* This method tests getting an advertisements from the
	* database with a specified ID.
	* Expecting invalid request, without id parameter
	* Expected: method put, status BAD_REUEST
    * @throws Exception 
	**/
    @Test
    public void testRateAdvertismentNullParam() throws Exception {
    	mockMvc.perform(put(URL_PREFIX + "/rate?rate=" + NEW_GIVEN_RATE))
			.andExpect(status().isBadRequest());
    }
    
    /**
   	* This method tests getting an advertisements from the
   	* database with a specified ID.
   	* Expecting invalid request, with invalid rate
   	* Expected: method put, status BAD_REUEST
    * @throws Exception 
   	**/
    @Test
    public void testRateAdvertismentWrongRate() throws Exception {
    	mockMvc.perform(put(URL_PREFIX + "/rate?id=" + DB_ID + "&rate=" + 0))
			.andExpect(status().isBadRequest());
    }
    
    /**
   	* This method tests getting an advertisements from the
   	* database with a specified ID.
   	* Expecting ID that doesn't exist.
   	* Expected: method put, status NOT_FOUND
    * @throws Exception 
   	**/
    @Test
    public void testRateAdvertismentInvalid() throws Exception {
    	mockMvc.perform(put(URL_PREFIX + "/rate?id=" + DB_NONEXISTING_ID + "&rate=" + NEW_GIVEN_RATE))
			.andExpect(status().isNotFound());
    }
    
    /**
	* This method tests prolonging advertisement.
	* Expecting valid request, with valid parameters.
	* Expected: method put, status OK
    * @throws Exception 
	**/
    @Test
    @Transactional
    @Rollback(true)
    public void testProlongAdvertisment() throws Exception {
        this.mockMvc.perform(put(URL_PREFIX + "/prolong?idAdvertisment=" + DB_ID + "&date=" + NEW_DATE_STR)
            .contentType(contentType))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$.advertismentId").value(DB_ID.intValue()))
	        .andExpect(jsonPath("$.purpose").value(DB_PURPOSE))
	    	.andExpect(jsonPath("$.phoneNumber").value(DB_PHONE_NUMBER))
	    	.andExpect(jsonPath("$.rate").value(DB_RATE))
	    	.andExpect(jsonPath("$.realEstateId").value(tim9.realEstate.constants.RealEstateConstants.DB_ID))
	    	.andExpect(jsonPath("$.name").value(DB_NAME))
	    	.andExpect(jsonPath("$.price").value(DB_PRICE))
	    	.andExpect(jsonPath("$.landSize").value(tim9.realEstate.constants.RealEstateConstants.DB_LAND_SIZE))
	    	.andExpect(jsonPath("$.techEquipment").value(tim9.realEstate.constants.RealEstateConstants.DB_TEACH_EQUIPMENT))
	    	.andExpect(jsonPath("$.heatingType").value(tim9.realEstate.constants.RealEstateConstants.DB_HEATING_TYPE))
	    	.andExpect(jsonPath("$.image").value(DB_IMAGE))
	    	.andExpect(jsonPath("$.numOfBathRooms").value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_BATH_ROOMS))
	    	.andExpect(jsonPath("$.numOfBedRooms").value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_BED_ROOMS))
	    	.andExpect(jsonPath("$.numOfFlors").value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_FLORS))
	    	.andExpect(jsonPath("$.buildYear").value(tim9.realEstate.constants.RealEstateConstants.DB_BUILD_YEAR))
	    	.andExpect(jsonPath("$.type").value(tim9.realEstate.constants.RealEstateConstants.DB_TYPE));
    }
    
    /**
	* This method tests prolonging advertisement.
	* Expecting invalid request, without Date.
	* Expected: method put, status BAD_REQUEST
    * @throws Exception 
	**/
    @Test
    public void testProlongAdvertismentNullParam() throws Exception {
        this.mockMvc.perform(put(URL_PREFIX + "/prolong?idAdvertisment=" + DB_ID)
            .contentType(contentType))
            .andExpect(status().isBadRequest());
    }
    
    /**
	* This method tests prolonging advertisement.
	* Expecting invalid request, without ID.
	* Expected: method put, status BAD_REQUEST
    * @throws Exception 
	**/
    @Test
    public void testProlongAdvertismentNullParamDate() throws Exception {
        this.mockMvc.perform(put(URL_PREFIX + "/prolong?date=" + NEW_DATE)
            .contentType(contentType))
            .andExpect(status().isBadRequest());
    }
    
    /**
	* This method tests prolonging advertisement.
	* Expecting valid request, but non existing
	* advertisement ID.
	* Expected: method put, status NOT_FOUND
    * @throws Exception 
	**/
    @Test
    public void testProlongAdvertismentInvalid() throws Exception {
        this.mockMvc.perform(put(URL_PREFIX + "/prolong?idAdvertisment=" + DB_NONEXISTING_ID + "&date=" + NEW_DATE_STR)
            .contentType(contentType))
            .andExpect(status().isNotFound());
    }
    
    /**
	* This method tests prolonging advertisement.
	* Expecting valid request, but invalid date
	* (date before todays date)
	* Expected: method put, status BAD_REQUEST
    * @throws Exception 
	**/
    @Test
    public void testProlongAdvertismentInvalidDate() throws Exception {
        this.mockMvc.perform(put(URL_PREFIX + "/prolong?idAdvertisment=" + DB_ID + "&date=2004/04/04")
            .contentType(contentType))
            .andExpect(status().isBadRequest());
    }
    
    /**
    * This method should test 'deleting' (setting isDeleted true)
	* advertisement.
	* Expecting request to be valid, with valid parameters.
	* Expected: method put, status OK
	* @throws Exception 
	**/
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteAdvertisement() throws Exception{
		this.mockMvc.perform(put(URL_PREFIX + "/delete?id=" + DB_ID)
	        .contentType(contentType))
	        .andExpect(status().isOk());
	}
	
	/**
    * This method should test 'deleting' (setting isDeleted true)
	* advertisement.
	* Expecting request to be valid, but advertisement
	* is already deleted
	* Expected: method put, status OK
	* @throws Exception 
	**/
	@Test
	@Ignore
	@Transactional
	@Rollback(true)
	public void testDeleteAdvertisementDeleted() throws Exception{
		this.mockMvc.perform(put(URL_PREFIX + "/delete?id=" + DB_NONEXISTING_ID)
	        .contentType(contentType))
	        .andExpect(status().isBadRequest());
	}
	 
	/**
	* This method should test 'deleting' (setting isDeleted true)
	* advertisement.
	* Expecting request to be invalid, without ID parameter.
	* Expected: method put, status BAD_REQUEST
	* @throws Exception 
	**/
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteAdvertisementNullParam() throws Exception{
		 this.mockMvc.perform(put(URL_PREFIX + "/delete")
            .contentType(contentType))
            .andExpect(status().isBadRequest());
	}
	 
	/**
	* This method should test 'deleting' (setting isDeleted true)
	* advertisement.
	* Expecting request to be valid, but ID to be
	* non existing.
	* Expected: method put, status NOT_FOUND
	* @throws Exception 
	**/
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteAdvertisementIndalid() throws Exception{
		this.mockMvc.perform(put(URL_PREFIX + "/delete?id=" + DB_NONEXISTING_ID)
	        .contentType(contentType))
	        .andExpect(status().isNotFound());
	}
 
}
