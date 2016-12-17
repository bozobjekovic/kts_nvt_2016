package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.AdvertismentConstants.DB_COUNT;
import static tim9.realEstate.constants.AdvertismentConstants.DB_ID;
import static tim9.realEstate.constants.AdvertismentConstants.DB_PURPOSE;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_DATE;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_GIVEN_RATE;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_PHONE_NUMBER;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_PURPOSE;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_RATE;
import static tim9.realEstate.constants.AdvertismentConstants.PAGE_SIZE_CONTROLLER;
import static tim9.realEstate.constants.RealEstateConstants.DB_IMAGE;
import static tim9.realEstate.constants.RealEstateConstants.DB_LAND_SIZE;
import static tim9.realEstate.constants.RealEstateConstants.DB_NAME;
import static tim9.realEstate.constants.RealEstateConstants.DB_PRICE;
import static tim9.realEstate.constants.RealEstateConstants.NEW_BUILD_YEAR;
import static tim9.realEstate.constants.RealEstateConstants.NEW_CATEGORY;
import static tim9.realEstate.constants.RealEstateConstants.NEW_HEATING_TYPE;
import static tim9.realEstate.constants.RealEstateConstants.NEW_IMAGE;
import static tim9.realEstate.constants.RealEstateConstants.NEW_LAND_SIZE;
import static tim9.realEstate.constants.RealEstateConstants.NEW_NAME;
import static tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BATH_ROOMS;
import static tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BED_ROOMS;
import static tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_FLORS;
import static tim9.realEstate.constants.RealEstateConstants.NEW_PRICE;
import static tim9.realEstate.constants.RealEstateConstants.NEW_TEACH_EQUIPMENT;
import static tim9.realEstate.constants.RealEstateConstants.NEW_TYPE;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.TestUtil;
import tim9.realEstate.dto.AdvertismentCreateDTO;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.RealEstate;
import tim9.realEstate.service.AdvertismentService;

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
    
    @PostConstruct
    public void setup() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	/**
	 * <b>testGetAllAdvertisments()</b>
	 * method tests if a controller with URL_PREFIX returns expected status,
	 * content type, and contains given element
	 **/
    @Test
    public void testGetAllAdvertisments() throws Exception {
    	mockMvc.perform(get(URL_PREFIX + "/all"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(contentType))
	        .andExpect(jsonPath("$", hasSize(DB_COUNT)))
	        .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
	    	.andExpect(jsonPath("$.[*].price").value(hasItem(DB_PRICE)))
	    	.andExpect(jsonPath("$.[*].landSize").value(hasItem(DB_LAND_SIZE)))
	    	.andExpect(jsonPath("$.[*].image").value(hasItem(DB_IMAGE)))
	    	.andExpect(jsonPath("$.[*].type").value(hasItem(tim9.realEstate.constants.RealEstateConstants.DB_TYPE)));
    }
    
    /**
	 * <b>testGetAdvertismentPage()</b>
	 * method tests if a controller with URL_PREFIX and given page and size
	 * returns expected status,
	 * content type, and contains given element
	 **/
    @Test
    public void testGetAdvertismentPage() throws Exception {
    	mockMvc.perform(get(URL_PREFIX + "?page=0&size=" + PAGE_SIZE_CONTROLLER))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType(contentType))
    		.andExpect(jsonPath("$", hasSize(PAGE_SIZE_CONTROLLER)))
    		.andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
	    	.andExpect(jsonPath("$.[*].price").value(hasItem(DB_PRICE)))
	    	.andExpect(jsonPath("$.[*].landSize").value(hasItem(DB_LAND_SIZE)))
	    	.andExpect(jsonPath("$.[*].image").value(hasItem(DB_IMAGE)))
	    	.andExpect(jsonPath("$.[*].type").value(hasItem(tim9.realEstate.constants.RealEstateConstants.DB_TYPE)));
    }
    
    /**
	 * <b>testSaveAdvertisment()</b>
	 * method tests if a controller with URL_PREFIX
	 * saves element properly
	 **/
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveAdvertisment() throws Exception {
    	Advertisment advertisment = new Advertisment();
    	RealEstate realEstate = new RealEstate();
    	
    	advertisment.setName(NEW_NAME);
    	advertisment.setPrice(NEW_PRICE);
    	advertisment.setPublicationDate(NEW_DATE);
    	advertisment.setBackgroundImage(NEW_IMAGE);
		advertisment.setActiveUntil(NEW_DATE);
		advertisment.setPurpose(NEW_PURPOSE);
		advertisment.setRate(NEW_RATE);
		advertisment.setPhoneNumber(NEW_PHONE_NUMBER);
    	
    	realEstate.setLandSize(NEW_LAND_SIZE);
    	realEstate.setTechEquipment(NEW_TEACH_EQUIPMENT);
    	realEstate.setHeatingType(NEW_HEATING_TYPE);
    	realEstate.setNumOfBathRooms(NEW_NUM_OF_BATH_ROOMS);
    	realEstate.setNumOfBedRooms(NEW_NUM_OF_BED_ROOMS);
    	realEstate.setNumOfFlors(NEW_NUM_OF_FLORS);
    	realEstate.setBuildYear(NEW_BUILD_YEAR);
    	realEstate.setCategory(NEW_CATEGORY);
    	realEstate.setType(NEW_TYPE);
    	
    	advertisment.setRealEstate(realEstate);
    	AdvertismentCreateDTO advertismentDTO = new AdvertismentCreateDTO(advertisment, realEstate);
    	String json = TestUtil.json(advertismentDTO);
        this.mockMvc.perform(post(URL_PREFIX)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isCreated());
    }
        
    /**
   	 * <b>testGetAdvertismentPage()</b>
   	 * method tests if a controller with URL_PREFIX and given page and size
   	 * returns expected status,
   	 * content type, and contains given element
   	 **/
       @Test
       @Ignore
       public void testGetAdvertismentsByPurpose() throws Exception {
       	mockMvc.perform(get(URL_PREFIX + "/purpose/ + " + DB_PURPOSE + "?page=0&size=1"))
       		.andExpect(status().isOk())
       		.andExpect(content().contentType(contentType))
       		.andExpect(jsonPath("$", hasSize(1)))
       		.andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
               .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
   	    	.andExpect(jsonPath("$.[*].price").value(hasItem(DB_PRICE)))
   	    	.andExpect(jsonPath("$.[*].landSize").value(hasItem(DB_LAND_SIZE)))
   	    	.andExpect(jsonPath("$.[*].image").value(hasItem(DB_IMAGE)))
   	    	.andExpect(jsonPath("$.[*].type").value(hasItem(tim9.realEstate.constants.RealEstateConstants.DB_TYPE)));
       }
    
    /**
	 * <b>testRateAdvertisment()</b>
	 * method tests if a controller with URL_PREFIX
	 * calculates and sets properly new rate
	 **/
    @Test
    @Transactional
    @Rollback(true)
    public void testRateAdvertisment() throws Exception {
    	
    	Advertisment advertisment = advertismentService.findOne(DB_ID); // DA LI SME OVO ?
    	
    	int oldNumberOfrates = advertisment.getNumberOfRates();
    	
    	double RATE = (advertisment.getRate()*(oldNumberOfrates + 1) + NEW_GIVEN_RATE) / (oldNumberOfrates + 1);
    	advertisment.setRate(RATE);
    	
    	String json = TestUtil.json(advertisment);
        this.mockMvc.perform(put(URL_PREFIX + "/rate?id=" + DB_ID + "&rate=" + NEW_GIVEN_RATE)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isOk());
    }
    
    /**
	 * <b>testVerifyAdvertisment()</b>
	 * method tests if a controller with URL_PREFIX
	 * updates Advertisement's verifier
	 **/
    @Test
    @Transactional
    @Rollback(true)
    public void testVerifyAdvertisment() throws Exception {

    	Advertisment advertisment = advertismentService.findOne(DB_ID); // DA LI SME OVO ?
    	
    	String json = TestUtil.json(advertisment);
        this.mockMvc.perform(put(URL_PREFIX + "/rate?id=" + DB_ID + "&rate=" + NEW_GIVEN_RATE)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isOk());
    }
    
    /**
	 * <b>testProlongAdvertisment()</b>
	 * method tests if a controller with URL_PREFIX
	 * prolongs Advertisement
	 **/
    @Test
    @Ignore
    @Transactional
    @Rollback(true)
    public void testProlongAdvertisment() throws Exception {
    	
    	Advertisment advertisment = advertismentService.findOne(DB_ID); // DA LI SME OVO ?
    	
    	String json = TestUtil.json(advertisment);
        this.mockMvc.perform(put(URL_PREFIX + "/prolong?idAdvertisment=" + DB_ID + "&date=" + NEW_DATE)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isOk());
    }
 
}
