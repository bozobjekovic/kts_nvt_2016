package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import static tim9.realEstate.constants.VerifierConstants.*;
import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.TestUtil;
import tim9.realEstate.constants.VerifierConstants;
import tim9.realEstate.model.Verifier;

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
    
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveVerifier() throws Exception {
    	Verifier verifier = new Verifier();
    	verifier.setAddress(NEW_ADDRESS);
    	verifier.setCity(NEW_CITY);
    	verifier.setEmail(NEW_EMAIL);
    	verifier.setImage(NEW_IMAGE);
    	verifier.setName(NEW_NAME);
    	verifier.setPassword(NEW_PASSWORD);
    	verifier.setPhoneNumber(NEW_PHONE_NUMBER);
    	verifier.setSurname(NEW_SURNAME);
    	verifier.setUsername(NEW_USERNAME);
    	
    	String json = TestUtil.json(verifier);
        this.mockMvc.perform(post(URL_PREFIX)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isCreated());
    }
}
