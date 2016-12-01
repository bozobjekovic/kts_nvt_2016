package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.service.AdvertismentService;

import static tim9.realEstate.constants.AdvertismentConstants.DB_COUNT;
import static tim9.realEstate.constants.AdvertismentConstants.DB_ID;
import static tim9.realEstate.constants.AdvertismentConstants.DB_NUM_OF_RATES;
import static tim9.realEstate.constants.AdvertismentConstants.DB_PHONE_NUMBER;
import static tim9.realEstate.constants.AdvertismentConstants.DB_PURPOSE;
import static tim9.realEstate.constants.AdvertismentConstants.DB_RATE;
import static tim9.realEstate.constants.AdvertismentConstants.*;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_NUM_OF_RATES;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_PHONE_NUMBER;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_PURPOSE;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_RATE;
import static tim9.realEstate.constants.AdvertismentConstants.PAGE_SIZE_CONTROLLER;

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
            //.andExpect(jsonPath("$.[*].publicationDate").value(hasItem(DB_PUBLICATION_DATE)))
            //.andExpect(jsonPath("$.[*].modificationDate").value(hasItem(DB_MODIFICATION_DATE)))
	    	//.andExpect(jsonPath("$.[*].activeUntil").value(hasItem(DB_ACTIVE_UNTIL)))
	    	.andExpect(jsonPath("$.[*].purpose").value(hasItem(DB_PURPOSE)))
	    	.andExpect(jsonPath("$.[*].rate").value(hasItem(DB_RATE)))
	    	.andExpect(jsonPath("$.[*].numberOfRates").value(hasItem(DB_NUM_OF_RATES)))
	    	.andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DB_PHONE_NUMBER)));
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
            //.andExpect(jsonPath("$.[*].publicationDate").value(hasItem(DB_PUBLICATION_DATE)))
            //.andExpect(jsonPath("$.[*].modificationDate").value(hasItem(DB_MODIFICATION_DATE)))
	    	//.andExpect(jsonPath("$.[*].activeUntil").value(hasItem(DB_ACTIVE_UNTIL)))
	    	.andExpect(jsonPath("$.[*].purpose").value(hasItem(DB_PURPOSE)))
	    	.andExpect(jsonPath("$.[*].rate").value(hasItem(DB_RATE)))
	    	.andExpect(jsonPath("$.[*].numberOfRates").value(hasItem(DB_NUM_OF_RATES)))
	    	.andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DB_PHONE_NUMBER)));	
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
    	advertisment.setPublicationDate(NEW_DATE);
		advertisment.setModificationDate(NEW_DATE);
		advertisment.setActiveUntil(NEW_DATE);
		advertisment.setPurpose(NEW_PURPOSE);
		advertisment.setRate(NEW_RATE);
		advertisment.setNumberOfRates(NEW_NUM_OF_RATES);
		advertisment.setPhoneNumber(NEW_PHONE_NUMBER);
    	
    	String json = TestUtil.json(advertisment);
        this.mockMvc.perform(post(URL_PREFIX)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isCreated());
    }
    
    //PURPOSE  ??
    
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfRates").value(oldNumberOfrates + 1));
        		//.andExpect(jsonPath("$.rate").value(RATE));
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
    @Transactional
    @Rollback(true)
    public void testProlongAdvertisment() throws Exception {
    	
    	Advertisment advertisment = advertismentService.findOne(DB_ID); // DA LI SME OVO ?
    	
    	String json = TestUtil.json(advertisment);
        this.mockMvc.perform(put(URL_PREFIX + "/prolong?idAdvertisment=" + DB_ID + "&date=" + NEW_DATE)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.activeUntil").value(NEW_DATE));
    }
 
}
