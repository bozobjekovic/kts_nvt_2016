package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.CompanyConstants.DB_COUNT;
import static tim9.realEstate.constants.CompanyConstants.DB_NAME;
import static tim9.realEstate.constants.CompanyConstants.DB_PHONE_NUMBER;
import static tim9.realEstate.constants.CompanyConstants.NEW_ADDRESS;
import static tim9.realEstate.constants.CompanyConstants.NEW_NAME;
import static tim9.realEstate.constants.CompanyConstants.NEW_PHONE_NUMBER;
import static tim9.realEstate.constants.CompanyConstants.NEW_SITE;

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
import tim9.realEstate.dto.CompanyDTO;
import tim9.realEstate.dto.LocationDTO;
import tim9.realEstate.model.Location;
import tim9.realEstate.service.LocationService;

/**
 * 
 * This class tests Company controller
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class CompanyControllerTest {

	private static final String URL_PREFIX = "/realEstate/companies";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	LocationService locationService;

	/**
	 * This method sets up MockMvc object
	 */
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * This method tests getting all companies from the database. Expected:
	 * method get, status OK, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetAllCompanies() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/all")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_COUNT)));
	}

	/**
	 * This method tests adding new Company and saving it to the database.
	 * Expected all input fields to be valid.
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveCompany() throws Exception {
		CompanyDTO company = new CompanyDTO();
		Location location = locationService.findOne(2L);

		company.setAddress(NEW_ADDRESS);
		company.setName(NEW_NAME);
		company.setPhoneNumber(NEW_PHONE_NUMBER);
		company.setSite(NEW_SITE);
		company.setLocation(new LocationDTO(location));
		;

		String json = TestUtil.json(company);
		this.mockMvc.perform(post(URL_PREFIX).contentType(contentType).content(json)).andExpect(status().isCreated());
	}

	/**
	 * This method tests adding new Company and saving it to the database, but
	 * without Name which has to be given.
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveCompanyNoName() throws Exception {
		CompanyDTO company = new CompanyDTO();
		Location location = locationService.findOne(2L);

		company.setAddress(NEW_ADDRESS);
		company.setPhoneNumber(NEW_PHONE_NUMBER);
		company.setSite(NEW_SITE);
		company.setLocation(new LocationDTO(location));
		;

		String json = TestUtil.json(company);
		this.mockMvc.perform(post(URL_PREFIX).contentType(contentType).content(json))
				.andExpect(status().isBadRequest());
	}

	/**
	 * This method tests adding new Company and saving it to the database but
	 * with Name/Phone Number that already exits, but has to be unique.
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveCompanyNameOrPhNumberExists() throws Exception {
		CompanyDTO company = new CompanyDTO();
		Location location = locationService.findOne(2L);

		company.setAddress(NEW_ADDRESS);
		company.setName(DB_NAME);
		company.setPhoneNumber(NEW_PHONE_NUMBER);
		company.setSite(NEW_SITE);
		company.setLocation(new LocationDTO(location));
		;

		String json = TestUtil.json(company);
		this.mockMvc.perform(post(URL_PREFIX).contentType(contentType).content(json)).andExpect(status().isConflict());

		company = new CompanyDTO();

		company.setAddress(NEW_ADDRESS);
		company.setName(NEW_NAME);
		company.setPhoneNumber(DB_PHONE_NUMBER);
		company.setSite(NEW_SITE);
		company.setLocation(new LocationDTO(location));
		;

		json = TestUtil.json(company);
		this.mockMvc.perform(post(URL_PREFIX).contentType(contentType).content(json)).andExpect(status().isConflict());
	}
}
