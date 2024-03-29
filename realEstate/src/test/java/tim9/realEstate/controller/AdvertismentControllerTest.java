package tim9.realEstate.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.AdvertismentConstants.DB_ID;
import static tim9.realEstate.constants.AdvertismentConstants.DB_NAME;
import static tim9.realEstate.constants.AdvertismentConstants.DB_NONEXISTING_ID;
import static tim9.realEstate.constants.AdvertismentConstants.DB_PHONE_NUMBER;
import static tim9.realEstate.constants.AdvertismentConstants.DB_PRICE;
import static tim9.realEstate.constants.AdvertismentConstants.DB_PURPOSE;
import static tim9.realEstate.constants.AdvertismentConstants.DB_RATE;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_GIVEN_RATE;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_PHONE_NUMBER;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_PURPOSE;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;

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
import tim9.realEstate.TestUtil;
import tim9.realEstate.constants.AdvertismentConstants;
import tim9.realEstate.constants.LocationConstants;
import tim9.realEstate.constants.UserConstants;
import tim9.realEstate.dto.AdvertismentCreateDTO;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.RealEstate;
import tim9.realEstate.service.AdvertismentService;
import tim9.realEstate.service.LocationService;

/**
 * 
 * This class tests Advertisement controller
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class AdvertismentControllerTest {

	private static final String URL_PREFIX = "/realEstate/advertisments";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private AdvertismentService advertismentService;

	@Autowired
	private LocationService locationService;

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
	 * This method tests getting an advertisements from the database with a
	 * specified ID. Expected: method get, status OK, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetAdvertisment() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + DB_ID)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.advertismentId").value(DB_ID.intValue()))
				.andExpect(jsonPath("$.purpose").value(DB_PURPOSE))
				.andExpect(jsonPath("$.phoneNumber").value(DB_PHONE_NUMBER))
				.andExpect(jsonPath("$.rate").value(DB_RATE))
				.andExpect(jsonPath("$.realEstateId").value(tim9.realEstate.constants.RealEstateConstants.DB_ID))
				.andExpect(jsonPath("$.name").value(DB_NAME)).andExpect(jsonPath("$.price").value(DB_PRICE))
				.andExpect(jsonPath("$.landSize").value(tim9.realEstate.constants.RealEstateConstants.DB_LAND_SIZE))
				.andExpect(jsonPath("$.techEquipment")
						.value(tim9.realEstate.constants.RealEstateConstants.DB_TEACH_EQUIPMENT))
				.andExpect(
						jsonPath("$.heatingType").value(tim9.realEstate.constants.RealEstateConstants.DB_HEATING_TYPE))
				.andExpect(jsonPath("$.numOfBathRooms")
						.value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_BATH_ROOMS))
				.andExpect(jsonPath("$.numOfBedRooms")
						.value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_BED_ROOMS))
				.andExpect(
						jsonPath("$.numOfFlors").value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_FLORS))
				.andExpect(jsonPath("$.buildYear").value(tim9.realEstate.constants.RealEstateConstants.DB_BUILD_YEAR))
				.andExpect(jsonPath("$.type").value(tim9.realEstate.constants.RealEstateConstants.DB_TYPE));
	}

	/**
	 * This method tests getting an advertisements from the database with a
	 * specified ID. Expecting non existing ID Expected: method get, status
	 * BAD_REUEST, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetAdvertismentNullParam() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + DB_NONEXISTING_ID)).andExpect(status().isNotFound());
	}

	/**
	 * This method tests getting an publisher from the database with a specified
	 * ID. Expected: method get, status OK, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetPublisher() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/publisher/" + 1L)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id").value(UserConstants.DB_ID.intValue()))
				.andExpect(jsonPath("$.address").value(UserConstants.DB_ADDRESS))
				.andExpect(jsonPath("$.city").value(UserConstants.DB_CITY))
				.andExpect(jsonPath("$.email").value(UserConstants.DB_EMAIL))
				.andExpect(jsonPath("$.image").value(UserConstants.DB_IMAGE))
				.andExpect(jsonPath("$.name").value(tim9.realEstate.constants.UserConstants.DB_NAME))
				.andExpect(jsonPath("$.phoneNumber").value(tim9.realEstate.constants.UserConstants.DB_PHONE_NUMBER))
				.andExpect(jsonPath("$.surname").value(UserConstants.DB_SURNAME))
				.andExpect(jsonPath("$.bankAccount").value(UserConstants.DB_BANK_ACCOUNT))
				.andExpect(jsonPath("$.username").value(UserConstants.DB_USERNAME));
	}

	/**
	 * This method tests getting an publisher from the database with a specified
	 * ID. Expected: method get, status OK, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetPublisherInvalid() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/publisher/" + DB_NONEXISTING_ID)).andExpect(status().isNotFound());
	}

	/**
	 * This method tests getting an publishers company from the database with a
	 * specified ID. Expected: method get, status OK, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetPublisherCompany() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/company/" + 1L)).andExpect(status().isOk());
	}

	/**
	 * This method tests getting an publishers company from the database with a
	 * specified ID. Expected: method get, status OK, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetPublisherCompanyInvalid() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/company/" + DB_NONEXISTING_ID)).andExpect(status().isNotFound());
	}

	/**
	 * This method tests adding new Advertisement and saving it to the database.
	 * Expected all input fields to be valid. Expected: method post, status
	 * CREATED, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveAdvertisment() throws Exception {

		Advertisment advertisment = new Advertisment();
		RealEstate realEstate = new RealEstate();

		advertisment.setName(tim9.realEstate.constants.RealEstateConstants.NEW_NAME);
		advertisment.setPrice(tim9.realEstate.constants.RealEstateConstants.NEW_PRICE);
		advertisment.setPublicationDate(new Date());
		advertisment.setBackgroundImage(tim9.realEstate.constants.RealEstateConstants.NEW_IMAGE);
		advertisment.setActiveUntil(new Date());
		advertisment.setPurpose(NEW_PURPOSE);
		advertisment.setPhoneNumber(NEW_PHONE_NUMBER);
		advertisment.setImages(new ArrayList<String>());

		realEstate.setLocation(locationService.findOne(tim9.realEstate.constants.LocationConstants.DB_ID));
		realEstate.setAddress(tim9.realEstate.constants.AdminConstants.NEW_ADDRESS);
		realEstate.setLandSize(tim9.realEstate.constants.RealEstateConstants.NEW_LAND_SIZE);
		realEstate.setHeatingType("Radiators");
		realEstate.setTechEquipment(tim9.realEstate.constants.RealEstateConstants.NEW_TEACH_EQUIPMENT);
		realEstate.setNumOfBathRooms(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BATH_ROOMS);
		realEstate.setNumOfBedRooms(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BED_ROOMS);
		realEstate.setNumOfFlors(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_FLORS);
		realEstate.setBuildYear(tim9.realEstate.constants.RealEstateConstants.NEW_BUILD_YEAR);
		realEstate.setCategory(tim9.realEstate.constants.RealEstateConstants.NEW_CATEGORY);
		realEstate.setType(tim9.realEstate.constants.RealEstateConstants.NEW_TYPE);

		advertisment.setRealEstate(realEstate);

		String token = loginTest.login(tim9.realEstate.constants.UserConstants.DB_USERNAME,
				tim9.realEstate.constants.UserConstants.DB_PASSWORD);

		AdvertismentCreateDTO advertismentDTO = new AdvertismentCreateDTO(advertisment, realEstate);
		String json = TestUtil.json(advertismentDTO);
		this.mockMvc.perform(post(URL_PREFIX).header("X-Auth-Token", token).contentType(contentType).content(json))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.purpose").value(NEW_PURPOSE))
				.andExpect(jsonPath("$.phoneNumber").value(NEW_PHONE_NUMBER))
				.andExpect(jsonPath("$.name").value(tim9.realEstate.constants.RealEstateConstants.NEW_NAME))
				.andExpect(jsonPath("$.price").value(tim9.realEstate.constants.RealEstateConstants.NEW_PRICE))
				.andExpect(jsonPath("$.landSize").value(tim9.realEstate.constants.RealEstateConstants.NEW_LAND_SIZE))
				.andExpect(jsonPath("$.techEquipment")
						.value(tim9.realEstate.constants.RealEstateConstants.NEW_TEACH_EQUIPMENT))
				.andExpect(jsonPath("$.numOfBathRooms")
						.value(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BATH_ROOMS))
				.andExpect(jsonPath("$.numOfBedRooms")
						.value(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BED_ROOMS))
				.andExpect(
						jsonPath("$.numOfFlors").value(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_FLORS))
				.andExpect(jsonPath("$.buildYear").value(tim9.realEstate.constants.RealEstateConstants.NEW_BUILD_YEAR))
				.andExpect(jsonPath("$.type").value(tim9.realEstate.constants.RealEstateConstants.NEW_TYPE));
	}

	/**
	 * This method tests adding new Advertisement and saving it to the database.
	 * Expected invalid input fields. Expected: method post, status BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveAdvertismentInvalid() throws Exception {
		Advertisment advertisment = new Advertisment();
		RealEstate realEstate = new RealEstate();

		advertisment.setName(tim9.realEstate.constants.RealEstateConstants.NEW_NAME);
		advertisment.setPrice(tim9.realEstate.constants.RealEstateConstants.NEW_PRICE);
		advertisment.setPublicationDate(new Date());
		advertisment.setBackgroundImage(tim9.realEstate.constants.RealEstateConstants.NEW_IMAGE);
		advertisment.setActiveUntil(new Date());
		advertisment.setPurpose(NEW_PURPOSE);
		advertisment.setPhoneNumber(DB_PHONE_NUMBER);
		advertisment.setImages(new ArrayList<String>());

		realEstate.setLocation(locationService.findOne(tim9.realEstate.constants.LocationConstants.DB_ID));
		realEstate.setAddress(tim9.realEstate.constants.AdminConstants.NEW_ADDRESS);
		realEstate.setLandSize(tim9.realEstate.constants.RealEstateConstants.NEW_LAND_SIZE);
		realEstate.setTechEquipment(tim9.realEstate.constants.RealEstateConstants.NEW_TEACH_EQUIPMENT);
		realEstate.setNumOfBathRooms(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BATH_ROOMS);
		realEstate.setNumOfBedRooms(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BED_ROOMS);
		realEstate.setNumOfFlors(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_FLORS);
		realEstate.setBuildYear(tim9.realEstate.constants.RealEstateConstants.NEW_BUILD_YEAR);
		realEstate.setCategory(tim9.realEstate.constants.RealEstateConstants.NEW_CATEGORY);
		realEstate.setType(tim9.realEstate.constants.RealEstateConstants.NEW_TYPE);
		realEstate.setHeatingType("Radiators");

		advertisment.setRealEstate(realEstate);

		String token = loginTest.login(tim9.realEstate.constants.UserConstants.DB_USERNAME,
				tim9.realEstate.constants.UserConstants.DB_PASSWORD);

		AdvertismentCreateDTO advertismentDTO = new AdvertismentCreateDTO(advertisment, realEstate);
		String json = TestUtil.json(advertismentDTO);
		this.mockMvc.perform(post(URL_PREFIX).header("X-Auth-Token", token).contentType(contentType).content(json))
				.andExpect(status().isConflict());
	}

	/**
	 * This method tests adding new Advertisement and saving it to the database.
	 * Expected invalid input fields, unique phone number. Expected: method
	 * post, status BAD_REQUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveAdvertismentUnique() throws Exception {
		Advertisment advertisment = new Advertisment();
		RealEstate realEstate = new RealEstate();

		advertisment.setPrice(tim9.realEstate.constants.RealEstateConstants.NEW_PRICE);
		advertisment.setPublicationDate(new Date());
		advertisment.setBackgroundImage(tim9.realEstate.constants.RealEstateConstants.NEW_IMAGE);
		advertisment.setActiveUntil(new Date());
		advertisment.setPurpose(NEW_PURPOSE);
		advertisment.setPhoneNumber(NEW_PHONE_NUMBER);

		realEstate.setLocation(locationService.findOne(tim9.realEstate.constants.LocationConstants.DB_ID));
		realEstate.setAddress(tim9.realEstate.constants.AdminConstants.NEW_ADDRESS);
		realEstate.setLandSize(tim9.realEstate.constants.RealEstateConstants.NEW_LAND_SIZE);
		realEstate.setTechEquipment(tim9.realEstate.constants.RealEstateConstants.NEW_TEACH_EQUIPMENT);
		realEstate.setNumOfBathRooms(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BATH_ROOMS);
		realEstate.setNumOfBedRooms(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BED_ROOMS);
		realEstate.setNumOfFlors(tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_FLORS);
		realEstate.setBuildYear(tim9.realEstate.constants.RealEstateConstants.NEW_BUILD_YEAR);
		realEstate.setCategory(tim9.realEstate.constants.RealEstateConstants.NEW_CATEGORY);
		realEstate.setType(tim9.realEstate.constants.RealEstateConstants.NEW_TYPE);

		advertisment.setRealEstate(realEstate);

		String token = loginTest.login(tim9.realEstate.constants.UserConstants.DB_USERNAME,
				tim9.realEstate.constants.UserConstants.DB_PASSWORD);

		AdvertismentCreateDTO advertismentDTO = new AdvertismentCreateDTO(advertisment, realEstate);
		String json = TestUtil.json(advertismentDTO);
		this.mockMvc.perform(post(URL_PREFIX).header("X-Auth-Token", token).contentType(contentType).content(json))
				.andExpect(status().isBadRequest());
	}

	/**
	 * This method rounds passed number on two decimals
	 * 
	 * @param value
	 * @param places
	 * @return number with two decimals
	 */
	public double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);

		long tmp = Math.round(value * factor);

		return (double) tmp / factor;
	}

	/**
	 * This method tests rating advertisement. It should calculate new rate, set
	 * it and save it to the database. Expecting request to be valid, with valid
	 * rate. Expected: method put, status OK, specified content
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRateAdvertisment() throws Exception {
		Advertisment advertisment = advertismentService.findOne(DB_ID);

		advertisment.setNumberOfRates(advertisment.getNumberOfRates() + 1);
		double rate = round(((advertisment.getRate() * (advertisment.getNumberOfRates() - 1)) + NEW_GIVEN_RATE)
				/ advertisment.getNumberOfRates(), 2);

		this.mockMvc
				.perform(
						put(URL_PREFIX + "/advertisment/" + DB_ID + "/rate/" + NEW_GIVEN_RATE).contentType(contentType))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.advertismentId").value(DB_ID.intValue()))
				.andExpect(jsonPath("$.purpose").value(DB_PURPOSE))
				.andExpect(jsonPath("$.phoneNumber").value(DB_PHONE_NUMBER)).andExpect(jsonPath("$.rate").value(rate))
				.andExpect(jsonPath("$.realEstateId").value(tim9.realEstate.constants.RealEstateConstants.DB_ID))
				.andExpect(jsonPath("$.name").value(DB_NAME)).andExpect(jsonPath("$.price").value(DB_PRICE))
				.andExpect(jsonPath("$.landSize").value(tim9.realEstate.constants.RealEstateConstants.DB_LAND_SIZE))
				.andExpect(jsonPath("$.techEquipment")
						.value(tim9.realEstate.constants.RealEstateConstants.DB_TEACH_EQUIPMENT))
				.andExpect(
						jsonPath("$.heatingType").value(tim9.realEstate.constants.RealEstateConstants.DB_HEATING_TYPE))
				.andExpect(jsonPath("$.numOfBathRooms")
						.value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_BATH_ROOMS))
				.andExpect(jsonPath("$.numOfBedRooms")
						.value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_BED_ROOMS))
				.andExpect(
						jsonPath("$.numOfFlors").value(tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_FLORS))
				.andExpect(jsonPath("$.buildYear").value(tim9.realEstate.constants.RealEstateConstants.DB_BUILD_YEAR))
				.andExpect(jsonPath("$.type").value(tim9.realEstate.constants.RealEstateConstants.DB_TYPE));
	}

	/**
	 * This method tests getting an advertisements from the database with a
	 * specified ID. Expecting invalid request, without id parameter Expected:
	 * method put, status BAD_REUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testRateAdvertismentNullParam() throws Exception {
		mockMvc.perform(put(URL_PREFIX + "/advertisment/" + "/rate/" + NEW_GIVEN_RATE))
				.andExpect(status().isNotFound());
	}

	/**
	 * This method tests getting an advertisements from the database with a
	 * specified ID. Expecting invalid request, with invalid rate Expected:
	 * method put, status BAD_REUEST
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testRateAdvertismentWrongRate() throws Exception {
		mockMvc.perform(put(URL_PREFIX + "/advertisment/" + DB_ID + "/rate/" + 0)).andExpect(status().isBadRequest());
	}

	/**
	 * This method tests getting an advertisements from the database with a
	 * specified ID. Expecting ID that doesn't exist. Expected: method put,
	 * status NOT_FOUND
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testRateAdvertismentInvalid() throws Exception {
		mockMvc.perform(put(URL_PREFIX + DB_NONEXISTING_ID + "/rate/" + NEW_GIVEN_RATE))
				.andExpect(status().isNotFound());
	}

	/**
	 * This method should test 'deleting' (setting isDeleted true)
	 * advertisement. Expecting request to be valid, with valid parameters.
	 * Expected: method put, status OK
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteAdvertisement() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/delete/" + DB_ID).contentType(contentType)).andExpect(status().isOk());
	}

	/**
	 * This method should test 'deleting' (setting isDeleted true)
	 * advertisement. Expecting request to be valid, but advertisement is
	 * already deleted Expected: method put, status OK
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteAdvertisementDeleted() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/delete/" + DB_NONEXISTING_ID).contentType(contentType))
				.andExpect(status().isNotFound());
	}

	/**
	 * This method should test 'deleting' (setting isDeleted true)
	 * advertisement. Expecting request to be valid, but ID to be non existing.
	 * Expected: method put, status NOT_FOUND
	 * 
	 * @throws Exception
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteAdvertisementIndalid() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + "/delete/" + DB_NONEXISTING_ID).contentType(contentType))
				.andExpect(status().isNotFound());
	}

	/**
	 * This method tests filtering advertisements with given parameters.
	 * Expected: method get, status OK, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetFilter() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/category/" + tim9.realEstate.constants.RealEstateConstants.DB_CATEGORY
				+ "/filters?" + "filter=city:" + AdvertismentConstants.DB_CITY + "&page=0,size=5"))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.count").value(2));

	}

	/**
	 * This method tests filtering advertisements with given parameters.
	 * Expecting no results. Expected: method get, status OK, specified size and
	 * content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetFilterNoResult() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/category/" + tim9.realEstate.constants.RealEstateConstants.DB_CATEGORY
				+ "/filters?" + "filter=city:" + AdvertismentConstants.DB_CITY + ",partOfTheCity:"
				+ LocationConstants.DB_PART_OF_THE_CITY + ", price<" + 50 + "&page=0,size=5"))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.count").value(0));

	}

	/**
	 * This method tests filtering advertisements with given parameters
	 * (multiple cities). Expected: method get, status OK, specified size and
	 * content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetFilterMultipleCity() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/category/" + tim9.realEstate.constants.RealEstateConstants.DB_CATEGORY
				+ "/filters?" + "filter=price>50,city:" + AdvertismentConstants.DB_CITY + "_"
				+ LocationConstants.DB_ANOTHER_CITY + "&page=0,size=5")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$.count").value(3));

	}

	/**
	 * This method tests filtering advertisements with given parameters
	 * (multiple cities). Expected: method get, status BAD REQUEST, specified
	 * size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetFilterInvalid() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/category/" + tim9.realEstate.constants.RealEstateConstants.DB_CATEGORY
				+ "/filters" + "?page=0,size=5")).andExpect(status().isBadRequest());

	}

	/**
	 * This method tests filtering advertisements with given parameters
	 * (multiple cities), but without page and size. Default size will be set.
	 * Expected: method get, status BAD REQUEST, specified size and content
	 * 
	 * @throws Exception
	 **/
	@Test
	public void testGetFilterNoPage() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/category/" + tim9.realEstate.constants.RealEstateConstants.DB_CATEGORY
				+ "/filters?" + "filter=price>50")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$.count").value(3));

	}

}
