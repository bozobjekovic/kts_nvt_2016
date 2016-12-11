package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.RealEstateConstants.*;

import java.nio.charset.Charset;
import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.constants.RealEstateConstants;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class RealEstateControllerTest {

	private static final String URL_PREFIX = "/realEstate/realestates";
	
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
    public void testGetAllRealEstates() throws Exception {
    	mockMvc.perform(get(URL_PREFIX + "/all"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(contentType))
	        .andExpect(jsonPath("$", hasSize(DB_COUNT)))
	        .andExpect(jsonPath("$.[*].id").value(hasItem(RealEstateConstants.DB_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DB_PRICE)))
            .andExpect(jsonPath("$.[*].landSize").value(hasItem(DB_LAND_SIZE)))
            .andExpect(jsonPath("$.[*].techEquipment").value(hasItem(DB_TEACH_EQUIPMENT)))
            .andExpect(jsonPath("$.[*].heatingType").value(hasItem(DB_HEATING_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DB_IMAGE)))
            .andExpect(jsonPath("$.[*].numOfBathRooms").value(hasItem(DB_NUM_OF_BATH_ROOMS)))
            .andExpect(jsonPath("$.[*].numOfBedRooms").value(hasItem(DB_NUM_OF_BED_ROOMS)))
            .andExpect(jsonPath("$.[*].numOfFlors").value(hasItem(DB_NUM_OF_FLORS)))
            .andExpect(jsonPath("$.[*].buildYear").value(hasItem(DB_BUILD_YEAR)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DB_TYPE)));
    }
}
