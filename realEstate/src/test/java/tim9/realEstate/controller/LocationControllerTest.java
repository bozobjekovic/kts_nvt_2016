package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import static tim9.realEstate.constants.LocationConstants.*;
import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.TestUtil;
import tim9.realEstate.constants.LocationConstants;
import tim9.realEstate.model.Location;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class LocationControllerTest {
	
	private static final String URL_PREFIX = "/realEstate/locations";
	
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
    
    @Test
    public void testGetAllLocations() throws Exception {
    	mockMvc.perform(get(URL_PREFIX + "/all"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(contentType))
	        .andExpect(jsonPath("$", hasSize(DB_COUNT)))
	        .andExpect(jsonPath("$.[*].id").value(hasItem(LocationConstants.DB_ID.intValue())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DB_ADDRESS)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DB_CITY)))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DB_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].partOfTheCity").value(hasItem(DB_PART_OF_THE_CITY)));
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveLocation() throws Exception {
    	Location location = new Location();
    	location.setAddress(NEW_ADDRESS);
    	location.setCity(NEW_CITY);
    	location.setZipCode(NEW_ZIP_CODE);
    	location.setPartOfTheCity(NEW_PART_OF_THE_CITY);
    	
    	String json = TestUtil.json(location);
        this.mockMvc.perform(post(URL_PREFIX)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isCreated());
    }
}
