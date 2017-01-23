package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.AdminConstants.CLERK_ADDRESS;
import static tim9.realEstate.constants.AdminConstants.CLERK_BANK_ACCOUNT;
import static tim9.realEstate.constants.AdminConstants.CLERK_CITY;
import static tim9.realEstate.constants.AdminConstants.CLERK_EMAIL;
import static tim9.realEstate.constants.AdminConstants.CLERK_ID;
import static tim9.realEstate.constants.AdminConstants.CLERK_IMAGE;
import static tim9.realEstate.constants.AdminConstants.CLERK_NAME;
import static tim9.realEstate.constants.AdminConstants.CLERK_PHONE_NUMBER;
import static tim9.realEstate.constants.AdminConstants.CLERK_SURNAME;
import static tim9.realEstate.constants.AdminConstants.CLERK_USERNAME;
import static tim9.realEstate.constants.AdminConstants.DB_APPROVED_ID;
import static tim9.realEstate.constants.AdminConstants.DB_COUNT_UNAPPROVED;
import static tim9.realEstate.constants.AdminConstants.DB_EXISTING_ID;
import static tim9.realEstate.constants.AdminConstants.DB_NONEXISTING_ID;
import static tim9.realEstate.constants.AdminConstants.NEW_EMAIL;
import static tim9.realEstate.constants.AdminConstants.NEW_PASSWORD;
import static tim9.realEstate.constants.AdminConstants.NEW_USERNAME;

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
import tim9.realEstate.dto.VerifierDTO;

/**
 * 
 * This class tests Admin controller
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class AdminControllerTest {

	private static final String URL_PREFIX = "/realEstate/admin";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	/**
	 * This method sets up MockMvc object
	 */
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * This method should return all clerks from the database that are not
	 * approved Expected: method get, status OK, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetAllUnapprovedClerks() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/unapproved/clerks")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_COUNT_UNAPPROVED)))
				.andExpect(jsonPath("$.[0].id").value(CLERK_ID.intValue()))
				.andExpect(jsonPath("$.[0].email").value(CLERK_EMAIL))
				.andExpect(jsonPath("$.[0].username").value(CLERK_USERNAME))
				.andExpect(jsonPath("$.[0].name").value(CLERK_NAME))
				.andExpect(jsonPath("$.[0].surname").value(CLERK_SURNAME))
				.andExpect(jsonPath("$.[0].phoneNumber").value(CLERK_PHONE_NUMBER))
				.andExpect(jsonPath("$.[0].address").value(CLERK_ADDRESS))
				.andExpect(jsonPath("$.[0].city").value(CLERK_CITY))
				.andExpect(jsonPath("$.[0].bankAccount").value(CLERK_BANK_ACCOUNT))
				.andExpect(jsonPath("$.[0].image").value(CLERK_IMAGE));
	}

	/**
	 * This method should registrate new Verifier. Expecting all valid input
	 * fields. Expected: method post, status CREATED, and specified content
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRegistrateVerifier() throws Exception {
		VerifierDTO verifierDTO = new VerifierDTO();

		verifierDTO.setEmail(NEW_EMAIL);
		verifierDTO.setPassword(NEW_PASSWORD);
		verifierDTO.setUsername(NEW_USERNAME);

		String json = TestUtil.json(verifierDTO);
		this.mockMvc.perform(post(URL_PREFIX + "/registrate/new").contentType(contentType).content(json))
				.andExpect(status().isCreated());
	}

	/**
	 * This method should test registrating new Verifier with invalid input.
	 * Expect to miss some not nullable fields. Expected: method post, status
	 * BAD_REQUEST, and specified content
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRegistrateVerifierInvalid() throws Exception {
		VerifierDTO verifierDTO = new VerifierDTO();

		String json = TestUtil.json(verifierDTO);
		this.mockMvc.perform(post(URL_PREFIX + "/registrate/new").contentType(contentType).content(json))
				.andExpect(status().isBadRequest());

		verifierDTO = new VerifierDTO();
		verifierDTO.setEmail(NEW_EMAIL);
		verifierDTO.setPassword(NEW_PASSWORD);

		json = TestUtil.json(verifierDTO);
		this.mockMvc.perform(post(URL_PREFIX + "/registrate/new").contentType(contentType).content(json))
				.andExpect(status().isBadRequest());

		verifierDTO = new VerifierDTO();
		verifierDTO.setEmail(NEW_EMAIL);
		verifierDTO.setPassword(NEW_USERNAME);

		json = TestUtil.json(verifierDTO);
		this.mockMvc.perform(post(URL_PREFIX + "/registrate/new").contentType(contentType).content(json))
				.andExpect(status().isBadRequest());

		verifierDTO = new VerifierDTO();
		verifierDTO.setEmail(NEW_USERNAME);
		verifierDTO.setPassword(NEW_PASSWORD);

		json = TestUtil.json(verifierDTO);
		this.mockMvc.perform(post(URL_PREFIX + "/registrate/new").contentType(contentType).content(json))
				.andExpect(status().isBadRequest());
	}

	/**
	 * This method should test registrating new Verifier with data that already
	 * exist. Expected: method post, status BAD_REQUEST, and specified content
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRegistrateVerifierUnique() throws Exception {
		VerifierDTO verifierDTO = new VerifierDTO();

		String json = TestUtil.json(verifierDTO);
		this.mockMvc.perform(post(URL_PREFIX + "/registrate/new").contentType(contentType).content(json))
				.andExpect(status().isBadRequest());

		verifierDTO.setEmail(NEW_EMAIL);
		verifierDTO.setPassword(NEW_PASSWORD);
		verifierDTO.setUsername(tim9.realEstate.constants.VerifierConstants.DB_USERNAME);

		json = TestUtil.json(verifierDTO);
		this.mockMvc.perform(post(URL_PREFIX + "/registrate/new").contentType(contentType).content(json))
				.andExpect(status().isConflict());

		verifierDTO = new VerifierDTO();
		verifierDTO.setEmail(tim9.realEstate.constants.VerifierConstants.DB_EMAIL);
		verifierDTO.setPassword(NEW_PASSWORD);
		verifierDTO.setUsername(NEW_USERNAME);

		json = TestUtil.json(verifierDTO);
		this.mockMvc.perform(post(URL_PREFIX + "/registrate/new").contentType(contentType).content(json))
				.andExpect(status().isConflict());

	}

	/**
	 * This method should test accepting clerk and his company. Expecting
	 * request to be valid. Expected: method put, status OK
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptClerk() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/accept/" + DB_EXISTING_ID).contentType(contentType))
				.andExpect(status().isOk());
	}

	/**
	 * This method should test accepting clerk and his company. Expecting
	 * request to be invalid, without request parameter. Expected: method put,
	 * status BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptClerkNullParam() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/accept/").contentType(contentType)).andExpect(status().isNotFound());
	}

	/**
	 * This method should test accepting clerk and his company. Expecting
	 * request to be valid, but id of user to be non existing, or maybe someone
	 * else already approved him. Expected: method post, status NOT_FOUND
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptClerkInvalid() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/accept/" + DB_NONEXISTING_ID).contentType(contentType))
				.andExpect(status().isNotFound());
	}

	/**
	 * This method should test accepting clerk and his company. Expecting
	 * request to be valid, but user is already approved. Expected: method post,
	 * status BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptClerkApproved() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/accept/" + DB_APPROVED_ID).contentType(contentType))
				.andExpect(status().isBadRequest());
	}

	/**
	 * This method should test denying clerk and his company. Expecting request
	 * to be valid. Expected: method put, status OK
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testDenyClerk() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/deny/" + DB_EXISTING_ID).contentType(contentType))
				.andExpect(status().isOk());
	}

	/**
	 * This method should test denying clerk and his company. Expecting request
	 * to be invalid, without request parameter. Expected: method put, status
	 * BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testDenyClerkNullParam() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/deny").contentType(contentType)).andExpect(status().isNotFound());
	}

	/**
	 * This method should test denying clerk and his company. Expecting request
	 * to be valid, but id of user to be non existing, or maybe someone else
	 * denied approved him. Expected: method post, status NOT_FOUND
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testDenyingClerkInvalid() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/deny/" + DB_NONEXISTING_ID).contentType(contentType))
				.andExpect(status().isNotFound());
	}

	/**
	 * This method should test denying clerk and his company. Expecting request
	 * to be valid, but user is already approved. Expected: method post, status
	 * BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testDenyClerkApproved() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/deny/" + DB_APPROVED_ID).contentType(contentType))
				.andExpect(status().isBadRequest());
	}

}
