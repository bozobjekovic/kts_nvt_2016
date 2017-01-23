package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.AdminConstants.DB_NONEXISTING_ID;
import static tim9.realEstate.constants.RealEstateConstants.DB_ID_SOLD;
import static tim9.realEstate.constants.RealEstateConstants.DB_ID_TO_RENT;
import static tim9.realEstate.constants.UserConstants.*;
import static tim9.realEstate.constants.UserConstants.DATE_RENT_FROM_INVALID;
import static tim9.realEstate.constants.UserConstants.DATE_RENT_TO;
import static tim9.realEstate.constants.UserConstants.DB_ID;
import static tim9.realEstate.constants.UserConstants.DB_ID_COMPANY;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

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
import org.springframework.web.context.WebApplicationContext;

import tim9.realEstate.LoginTest;
import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.constants.AdvertismentConstants;
import tim9.realEstate.constants.CompanyConstants;

/**
 * 
 * This class tests User controller
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class UserControllerTest {

	private static final String URL_PREFIX = "/realEstate/users";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private LoginTest loginTest;

	/**
	 * This method sets up MockMvc object
	 */
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * This method tests applying user to the company. Expecting valid request.
	 * Expected: method put, status OK
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testApplyToCompany() throws Exception {
		String token = loginTest.login(tim9.realEstate.constants.UserConstants.DB_USERNAME,
				tim9.realEstate.constants.UserConstants.DB_PASSWORD);

		mockMvc.perform(put(URL_PREFIX + "/apply/" + DB_ID_COMPANY).header("X-Auth-Token", token))
				.andExpect(status().isOk());
	}

	/**
	 * This method tests applying user to the company. Expecting invalid
	 * request, without id parameter. Expected: method put, status BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testApplyToCompanyNullParameters() throws Exception {
		mockMvc.perform(put(URL_PREFIX + "/apply/")).andExpect(status().isNotFound());
	}

	/**
	 * This method tests applying user to the company. Expecting valid request,
	 * but non existing id of a company. Expected: method put, status NOT_FOOUND
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testApplyToCompanyInvalid() throws Exception {
		String token = loginTest.login(tim9.realEstate.constants.UserConstants.DB_USERNAME,
				tim9.realEstate.constants.UserConstants.DB_PASSWORD);
		mockMvc.perform(put(URL_PREFIX + "/apply/" + tim9.realEstate.constants.AdvertismentConstants.DB_NONEXISTING_ID)
				.header("X-Auth-Token", token)).andExpect(status().isNotFound());
	}

	/**
	 * This method should return all users publications from the database.
	 * Expected: method get, status OK, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetAllPublications() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/published/" + 3L)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$.[0].id").value(AdvertismentConstants.DB_ID.intValue()))
				.andExpect(jsonPath("$.[0].name").value(AdvertismentConstants.DB_NAME))
				.andExpect(jsonPath("$.[0].city").value(AdvertismentConstants.DB_CITY))
				.andExpect(jsonPath("$.[0].price").value(AdvertismentConstants.DB_PRICE))
				.andExpect(jsonPath("$.[0].landSize").value(tim9.realEstate.constants.RealEstateConstants.DB_LAND_SIZE))
				.andExpect(jsonPath("$.[0].image").value(AdvertismentConstants.DB_IMAGE))
				.andExpect(jsonPath("$.[0].type").value(tim9.realEstate.constants.RealEstateConstants.DB_TYPE));
	}

	/**
	 * This method should return all users publications from the database
	 * without path variable. Expected: method get, status NOT FOUND, specified
	 * size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetAllPublicationsNull() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/published/")).andExpect(status().isNotFound());
	}

	/**
	 * This method should return all users publications from the database with
	 * path variable but non existing. Expected: method get, status NOT FOUND,
	 * specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetAllPublicationsInvalid() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/published/" + DB_NONEXISTING_ID)).andExpect(status().isNotFound());
	}

	/**
	 * This method should return all users publications that matches specified
	 * status from the database. Expected: method get, status OK, specified size
	 * and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetAllPublicationsByStatus() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/published/Active/user/" + 3L)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$.[0].id").value(AdvertismentConstants.DB_ID.intValue()))
				.andExpect(jsonPath("$.[0].name").value(AdvertismentConstants.DB_NAME))
				.andExpect(jsonPath("$.[0].city").value(AdvertismentConstants.DB_CITY))
				.andExpect(jsonPath("$.[0].price").value(AdvertismentConstants.DB_PRICE))
				.andExpect(jsonPath("$.[0].landSize").value(tim9.realEstate.constants.RealEstateConstants.DB_LAND_SIZE))
				.andExpect(jsonPath("$.[0].image").value(AdvertismentConstants.DB_IMAGE))
				.andExpect(jsonPath("$.[0].type").value(tim9.realEstate.constants.RealEstateConstants.DB_TYPE));
	}

	/**
	 * This method should return all users publications that matches specified
	 * status from the database without path variable. Expected: method get,
	 * status NOT FOUND, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetAllPublicationsByStatusNull() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/published/Active/user/")).andExpect(status().isNotFound());
	}

	/**
	 * This method should return all users publications from the database with
	 * path variable but non existing. Expected: method get, status NOT FOUND,
	 * specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetAllPublicationsByStatusInvalid() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/published/Active/user" + DB_NONEXISTING_ID))
				.andExpect(status().isNotFound());
	}

	/**
	 * This method should return users company from the database. Expected:
	 * method get, status OK, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetUsersCompany() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/company/" + 3L)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id").value(CompanyConstants.DB_ID.intValue()))
				.andExpect(jsonPath("$.name").value(CompanyConstants.DB_NAME));
	}

	/**
	 * This method should return users company from the database. Expected:
	 * method get, status NOT FOUND, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetUsersCompanyNull() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/company/")).andExpect(status().isNotFound());
	}

	/**
	 * This method should return users company from the database. Expected:
	 * method get, status NOT FOUND, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetUsersCompanyInvalid() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/company/" + DB_NONEXISTING_ID)).andExpect(status().isNotFound());
	}

	/**
	 * This method should test accepting user to the company. Expecting request
	 * to be valid. Expected: method put, status OK
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptUser() throws Exception {
		this.mockMvc
				.perform(put(URL_PREFIX + "/accept/" + DB_ID + "/company/" + DB_ID_COMPANY).contentType(contentType))
				.andExpect(status().isOk());
	}

	/**
	 * This method should test accepting user to the company. Expecting request
	 * to be invalid, without request parameters. Expected: method put, status
	 * BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptUserNullParam() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/accept/" + DB_ID + "company/").contentType(contentType))
				.andExpect(status().isNotFound());

		this.mockMvc.perform(put(URL_PREFIX + "/accept/" + "/company/" + DB_ID_COMPANY).contentType(contentType))
				.andExpect(status().isNotFound());
	}

	/**
	 * This method should test accepting user to the company. Expecting request
	 * to be valid, but id of user to be non existing, or maybe someone else
	 * already approved him. Expected: method post, status NOT_FOUND
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptUserInvalid() throws Exception {
		this.mockMvc.perform(
				put(URL_PREFIX + "/accept/" + DB_NONEXISTING_ID + "/company/" + DB_ID_COMPANY).contentType(contentType))
				.andExpect(status().isNotFound());

		this.mockMvc.perform(
				put(URL_PREFIX + "/accept?id=" + DB_ID + "&id_company=" + DB_NONEXISTING_ID).contentType(contentType))
				.andExpect(status().isNotFound());
	}

	/**
	 * This method should test denying user to join the company. Expecting
	 * request to be valid. Expected: method put, status OK
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testDenyUser() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/deny/" + DB_ID).contentType(contentType)).andExpect(status().isOk());
	}

	/**
	 * This method should test denying user to join the company. Expecting
	 * request to be invalid, without request parameter. Expected: method put,
	 * status BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testDenyUserNullParam() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/deny").contentType(contentType)).andExpect(status().isNotFound());
	}

	/**
	 * This method should test denying user to join the company. Expecting
	 * request to be valid, but id of user to be non existing, or maybe someone
	 * else already denied him. Expected: method post, status NOT_FOUND
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testDenyUserInvalid() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/deny/" + DB_NONEXISTING_ID).contentType(contentType))
				.andExpect(status().isNotFound());
	}

	/**
	 * This method should test renting specified real estate. Expecting request
	 * to be valid Expected: method put, status OK
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRentRealestate() throws Exception {
		this.mockMvc
				.perform(put(URL_PREFIX + "/rent/" + DB_ID_TO_RENT + "/from/" + startDateCalendar.getTime() + "/to/" + endDateCalendar.getTime())
						.contentType(contentType))
				.andExpect(status().isBadRequest());
	}

	/**
	 * This method should test renting specified real estate. Expecting request
	 * to be invalid, without parameters. Expected: method put, status
	 * BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRentRealestateNullParam() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/rent").contentType(contentType)).andExpect(status().isNotFound());
	}

	/**
	 * This method should test renting specified real estate. Expecting request
	 * to be valid, but non existing real estate id Expected: method put, status
	 * NOT_FOUND
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRentRealestateInvalid() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/rent/" + DB_NONEXISTING_ID + "/from/" + startDateCalendar.getTime()
				+ "/to/" + endDateCalendar.getTime()).contentType(contentType)).andExpect(status().isBadRequest());
	}

	/**
	 * This method should test renting specified real estate. Expecting request
	 * to be valid, but real estate that is sold Expected: method put, status
	 * BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRentRealestateSold() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/rent/" + DB_ID_SOLD + "/from/" + startDateCalendar.getTime()
				+ "/to/" + endDateCalendar.getTime()).contentType(contentType)).andExpect(status().isBadRequest());
	}

	/**
	 * This method should test renting specified real estate. Expecting request
	 * to be invalid, invalid dates or rented period Expected: method put,
	 * status BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRentRealestateInvalidDate() throws Exception {
		this.mockMvc.perform(
				put(URL_PREFIX + "/rent/" + DB_ID_TO_RENT + "/from/" + endDateCalendar.getTime() + "/to/" + startDateCalendar.getTime())
						.contentType(contentType))
				.andExpect(status().isBadRequest());
	}

	/**
	 * This method should test renting specified real estate. Expecting request
	 * to be invalid, invalid dates or rented period Expected: method put,
	 * status BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRentRealestateInvalidDates() throws Exception {
		this.mockMvc
				.perform(put(URL_PREFIX + "/rent/" + DB_ID_TO_RENT + "/from/" + startDateCalendar.getTime() + "/to/" + endDateCalendar.getTime())
						.contentType(contentType))
				.andExpect(status().isBadRequest());

		this.mockMvc
				.perform(put(URL_PREFIX + "/rent/" + DB_ID_TO_RENT + "/from/" + startDateCalendar.getTime() + "/to/" + endDateCalendar.getTime())
						.contentType(contentType))
				.andExpect(status().isBadRequest());
	}

}
