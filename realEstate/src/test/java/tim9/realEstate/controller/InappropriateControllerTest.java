package tim9.realEstate.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.InappropriateConstants.NEW_DESCRIPTION;
import static tim9.realEstate.constants.InappropriateConstants.NEW_TITLE;

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

import tim9.realEstate.LoginTest;
import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.TestUtil;
import tim9.realEstate.dto.InappropriateDTO;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class InappropriateControllerTest {

	private static final String URL_PREFIX = "/realEstate/inappropriates";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private LoginTest loginTest;

	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * This method tests adding new Inappropriate and saving it to the database.
	 * Expected all input fields to be valid.
	 **/
	@Test
	@Transactional
	@Ignore
	@Rollback(true)
	public void testSaveInappropriate() throws Exception {
		InappropriateDTO inappropriate = new InappropriateDTO();
		inappropriate.setTitle(NEW_TITLE);
		inappropriate.setDescription(NEW_DESCRIPTION);

		String token = loginTest.login(tim9.realEstate.constants.UserConstants.DB_USERNAME,
				tim9.realEstate.constants.UserConstants.DB_PASSWORD);

		String json = TestUtil.json(inappropriate);
		this.mockMvc
				.perform(post(URL_PREFIX + tim9.realEstate.constants.AdvertismentConstants.DB_ID + "/new/")
						.header("X-Auth-Token", token).contentType(contentType).content(json))
				.andExpect(status().isCreated());
	}

	/**
	 * This method tests adding new Inappropriate and saving it to the database,
	 * but without set Description which has to be given.
	 **/
	@Test
	@Transactional
	@Ignore
	@Rollback(true)
	public void testSaveInappropriateNoDescriptionWithParam() throws Exception {
		InappropriateDTO inappropriate = new InappropriateDTO();
		inappropriate.setTitle(NEW_TITLE);

		String json = TestUtil.json(inappropriate);
		this.mockMvc.perform(post(URL_PREFIX + tim9.realEstate.constants.AdvertismentConstants.DB_ID + "/new")
				.contentType(contentType).content(json)).andExpect(status().isBadRequest());
	}

	/**
	 * This method tests adding new Inappropriate and saving it to the database,
	 * but without Description as ID parameter, which has to be given.
	 **/
	@Test
	@Transactional
	@Ignore
	@Rollback(true)
	public void testSaveInappropriateNoDescriptionNoParam() throws Exception {
		InappropriateDTO inappropriate = new InappropriateDTO();

		String json = TestUtil.json(inappropriate);
		this.mockMvc.perform(post(URL_PREFIX).contentType(contentType).content(json))
				.andExpect(status().isBadRequest());
	}

	/**
	 * This method tests adding new Inappropriate and saving it to the database,
	 * but with non existing Advertisement
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveInappropriateNoAdvertisement() throws Exception {
		InappropriateDTO inappropriate = new InappropriateDTO();
		inappropriate.setTitle(NEW_TITLE);
		inappropriate.setDescription(NEW_DESCRIPTION);

		String json = TestUtil.json(inappropriate);
		this.mockMvc
				.perform(post(URL_PREFIX + tim9.realEstate.constants.AdvertismentConstants.DB_NONEXISTING_ID + "/new")
						.contentType(contentType).content(json))
				.andExpect(status().isNotFound());
	}
}
