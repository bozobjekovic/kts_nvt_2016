package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.VerifierConstants.*;

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
import tim9.realEstate.constants.VerifierConstants;

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
    
    @Test
    public void testGetAllVerifiers() throws Exception {
    	mockMvc.perform(get(URL_PREFIX + "/all"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(contentType))
	        .andExpect(jsonPath("$", hasSize(DB_COUNT)))
	        .andExpect(jsonPath("$.[*].id").value(hasItem(VerifierConstants.DB_ID.intValue())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DB_ADDRESS)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DB_CITY)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DB_EMAIL)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DB_IMAGE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DB_PASSWORD)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DB_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DB_SURNAME)))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DB_USERNAME)));
    }
}
