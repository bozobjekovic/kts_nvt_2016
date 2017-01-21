package tim9.realEstate.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.AdminConstants.DB_NONEXISTING_ID;
import static tim9.realEstate.constants.RealEstateConstants.DB_ID_SOLD;
import static tim9.realEstate.constants.RealEstateConstants.DB_ID_TO_RENT;
import static tim9.realEstate.constants.UserConstants.DATE_RENT_FROM;
import static tim9.realEstate.constants.UserConstants.DATE_RENT_FROM_INV;
import static tim9.realEstate.constants.UserConstants.DATE_RENT_FROM_INVALID;
import static tim9.realEstate.constants.UserConstants.DATE_RENT_TO;
import static tim9.realEstate.constants.UserConstants.DB_ID;
import static tim9.realEstate.constants.UserConstants.DB_ID_COMPANY;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

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
import org.springframework.web.context.WebApplicationContext;

import tim9.realEstate.LoginTest;
import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.service.UserService;

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
	private UserService userService;

	@Autowired
	private LoginTest loginTest;

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
				.header("X-Auth-Token", token))
				.andExpect(status().isNotFound());
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
		this.mockMvc.perform(
				put(URL_PREFIX + "/accept/" + DB_ID + "/company/" + DB_ID_COMPANY).contentType(contentType))
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
		this.mockMvc.perform(put(URL_PREFIX + "/accept/" + DB_NONEXISTING_ID + "/company/" + DB_ID_COMPANY)
				.contentType(contentType)).andExpect(status().isNotFound());

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
		this.mockMvc.perform(put(URL_PREFIX + "/deny?id=" + DB_NONEXISTING_ID).contentType(contentType))
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
	@Ignore
	@Rollback(true)
	public void testRentRealestate() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/rent/" + DB_ID_TO_RENT + "/from/" + DATE_RENT_FROM
				+ "/to/" + DATE_RENT_TO).contentType(contentType)).andExpect(status().isOk());
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
	@Ignore
	@Rollback(true)
	public void testRentRealestateNullParam() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/rent").contentType(contentType)).andExpect(status().isBadRequest());
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
	@Ignore
	@Rollback(true)
	public void testRentRealestateInvalid() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/rent?id=" + DB_NONEXISTING_ID + "&rentDateFrom=" + DATE_RENT_FROM
				+ "&rentDateTo=" + DATE_RENT_TO).contentType(contentType)).andExpect(status().isNotFound());
	}

	/**
	 * This method should test renting specified real estate. Expecting request
	 * to be valid, but real estate that is sold Expected: method put, status
	 * BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Ignore
	@Transactional
	@Rollback(true)
	public void testRentRealestateSold() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/rent?id=" + DB_ID_SOLD + "&rentDateFrom=" + DATE_RENT_FROM
				+ "&rentDateTo=" + DATE_RENT_TO).contentType(contentType)).andExpect(status().isBadRequest());
	}

	/**
	 * This method should test renting specified real estate. Expecting request
	 * to be invalid, invalid dates or rented period Expected: method put,
	 * status BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Ignore
	@Transactional
	@Rollback(true)
	public void testRentRealestateInvalidDate() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/rent/" + DB_ID_TO_RENT + "/from/" + DATE_RENT_FROM_INVALID
				+ "/to/" + DATE_RENT_TO).contentType(contentType)).andExpect(status().isBadRequest());
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
	@Ignore
	@Rollback(true)
	public void testRentRealestateInvalidDates() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/rent/" + DB_ID_TO_RENT + "/from/" + DATE_RENT_TO
				+ "/to/" + DATE_RENT_FROM).contentType(contentType)).andExpect(status().isBadRequest());

		this.mockMvc.perform(put(URL_PREFIX + "/rent/" + DB_ID_TO_RENT + "/from/" + DATE_RENT_FROM_INV
				+ "/to/" + DATE_RENT_TO).contentType(contentType)).andExpect(status().isBadRequest());
	}

}
