package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.AdminConstants.DB_COUNT_INAP;
import static tim9.realEstate.constants.AdminConstants.DB_EXADV_ID;
import static tim9.realEstate.constants.AdminConstants.DB_NONEXADV_ID;
import static tim9.realEstate.constants.AdminConstants.INAP_DESCRIPTION;
import static tim9.realEstate.constants.AdminConstants.INAP_ID;
import static tim9.realEstate.constants.AdminConstants.INAP_TITLE;

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

/**
 * 
 * This class tests Verifier controller
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class VerifierControllerTest {

	private static final String URL_PREFIX = "/realEstate/verifiers";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	LoginTest loginTest;

	/**
	 * This method sets up MockMvc object
	 */
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * This method should test getting all inappropriate advertisement from the
	 * database. Expected: method get, status OK, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetAllInappropriates() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/inappropriate")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_COUNT_INAP)))
				.andExpect(jsonPath("$.[*].id").value(hasItem(INAP_ID.intValue())))
				.andExpect(jsonPath("$.[*].title").value(hasItem(INAP_TITLE)))
				.andExpect(jsonPath("$.[*].description").value(hasItem(INAP_DESCRIPTION)));
	}

	/**
	 * This method should test rejecting inappropriate advertisement. Expecting
	 * request to be valid. Expected: method delete, status OK
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRejectInappropriate() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/inappropriate/reject/" + DB_EXADV_ID).contentType(contentType))
				.andExpect(status().isOk());
	}

	/**
	 * This method should test rejecting inappropriate advertisement. Expecting
	 * request to be invalid, with request parameter null. Expected: method
	 * delete, status BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRejectInappropriateNullParam() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/inappropriate/reject").contentType(contentType))
				.andExpect(status().isNotFound());
	}

	/**
	 * This method should test rejecting inappropriate advertisement. Expecting
	 * request to be valid, but advertisement to be non existing, or maybe
	 * someone else already rejected it. Expected: method delete, status
	 * BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRejectInappropriateInvalid() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/inappropriate/reject/" + DB_NONEXADV_ID).contentType(contentType))
				.andExpect(status().isNotFound());
	}

	/**
	 * This method should test accepting inappropriate advertisement. Expecting
	 * request to be valid. Expected: method delete, status OK
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptInappropriate() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/inappropriate/accept/" + DB_EXADV_ID).contentType(contentType))
				.andExpect(status().isOk());
	}

	/**
	 * This method should test accepting inappropriate advertisement. Expecting
	 * request to be invalid, with request parameter null. Expected: method
	 * delete, status BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptInappropriateNullParam() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/inappropriate/accept").contentType(contentType))
				.andExpect(status().isNotFound());
	}

	/**
	 * This method should test accepting inappropriate advertisement. Expecting
	 * request to be valid, but advertisement to be non existing, or maybe
	 * someone else already accepted it. Expected: method delete, status
	 * BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptInappropriateInvalid() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/inappropriate/accept/" + DB_NONEXADV_ID).contentType(contentType))
				.andExpect(status().isNotFound());
	}
}
