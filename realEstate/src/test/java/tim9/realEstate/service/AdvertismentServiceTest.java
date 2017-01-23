package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static tim9.realEstate.constants.AdvertismentConstants.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.model.Advertisment;

/**
 * This class represents Advertisement Service Test
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class AdvertismentServiceTest {

	@Autowired
	AdvertismentService advertismentService;

	/**
	 * method test if all of certain elements from the data base can be found
	 **/
	@Test
	public void testFindAll() {
		List<Advertisment> advertisements = advertismentService.findAll();
		assertThat(advertisements).hasSize(DB_COUNT);
	}

	/**
	 * method tests if an certain element from the data base can be found
	 **/
	@Test
	public void testFindOne() {
		Advertisment dbAdvertisment = advertismentService.findOne(DB_ID);
		assertThat(dbAdvertisment).isNotNull();

		assertThat(dbAdvertisment.getId()).isEqualTo(DB_ID);
		assertThat(dbAdvertisment.getPurpose()).isEqualTo(DB_PURPOSE);
		assertThat(dbAdvertisment.getPhoneNumber()).isEqualTo(DB_PHONE_NUMBER);
		assertThat(dbAdvertisment.getRate()).isEqualTo(DB_RATE);
	}

	/**
	 * method tests if a certain element can be inserted into data base
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testInsert() {
		Date date = new Date();
		Advertisment advertisment = new Advertisment();
		advertisment.setName(tim9.realEstate.constants.RealEstateConstants.NEW_NAME);
		advertisment.setPublicationDate(date);
		advertisment.setPurpose(NEW_PURPOSE);
		advertisment.setRate(NEW_RATE);
		advertisment.setNumberOfRates(NEW_NUM_OF_RATES);
		advertisment.setPhoneNumber(NEW_PHONE_NUMBER);

		int dbSizeBeforeAdd = advertismentService.findAll().size();

		Advertisment dbAdvertisment = advertismentService.save(advertisment);
		assertThat(dbAdvertisment).isNotNull();

		List<Advertisment> advertisments = advertismentService.findAll();
		assertThat(advertisments).hasSize(dbSizeBeforeAdd + 1);
		dbAdvertisment = advertisments.get(advertisments.size() - 1);
		assertThat(dbAdvertisment.getName()).isEqualTo(tim9.realEstate.constants.RealEstateConstants.NEW_NAME);
		assertThat(dbAdvertisment.getPublicationDate()).isEqualTo(date);
		assertThat(dbAdvertisment.getPurpose()).isEqualTo(NEW_PURPOSE);
		assertThat(dbAdvertisment.getRate()).isEqualTo(NEW_RATE);
		assertThat(dbAdvertisment.getNumberOfRates()).isEqualTo(NEW_NUM_OF_RATES);
		assertThat(dbAdvertisment.getPhoneNumber()).isEqualTo(NEW_PHONE_NUMBER);
	}

	/**
	 * method tests if a certain element from the data base can be updated
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		Advertisment dbAdvertisment = advertismentService.findOne(DB_ID);
		Date date = new Date();

		dbAdvertisment.setActiveUntil(date);
		dbAdvertisment.setPurpose(NEW_PURPOSE);
		dbAdvertisment.setRate(NEW_RATE);

		dbAdvertisment = advertismentService.save(dbAdvertisment);
		assertThat(dbAdvertisment).isNotNull();

		dbAdvertisment = advertismentService.findOne(DB_ID);
		assertThat(dbAdvertisment.getActiveUntil()).isEqualTo(date);
		assertThat(dbAdvertisment.getPurpose()).isEqualTo(NEW_PURPOSE);
		assertThat(dbAdvertisment.getRate()).isEqualTo(NEW_RATE);
	}

	/**
	 * method tests if an certain element, that must be unique, can be added
	 * into data base with value that already exist, and if can throws an
	 * exception
	 * 
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddUniquePhoneNumber() {
		Advertisment advertisment = new Advertisment();
		advertisment.setPublicationDate(new Date());
		advertisment.setModificationDate(new Date());
		advertisment.setActiveUntil(new Date());
		advertisment.setPurpose(NEW_PURPOSE);
		advertisment.setRate(NEW_RATE);
		advertisment.setNumberOfRates(NEW_NUM_OF_RATES);
		advertisment.setPhoneNumber(DB_PHONE_NUMBER);

		advertismentService.save(advertisment);
	}

	/**
	 * method tests if an certain element can be added into data base without
	 * field that is required, and if can throws an exception
	 * 
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullPurpose() {
		Advertisment advertisment = new Advertisment();
		advertisment.setPublicationDate(new Date());
		advertisment.setModificationDate(new Date());
		advertisment.setActiveUntil(new Date());
		advertisment.setRate(NEW_RATE);
		advertisment.setNumberOfRates(NEW_NUM_OF_RATES);
		advertisment.setPhoneNumber(NEW_PHONE_NUMBER);

		advertismentService.save(advertisment);
	}

	/**
	 * method tests if an certain element can be added into data base without
	 * field that is required, and if can throws an exception
	 * 
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullPublicationDate() {
		Advertisment advertisment = new Advertisment();
		advertisment.setModificationDate(new Date());
		advertisment.setActiveUntil(new Date());
		advertisment.setPurpose(NEW_PURPOSE);
		advertisment.setRate(NEW_RATE);
		advertisment.setNumberOfRates(NEW_NUM_OF_RATES);
		advertisment.setPhoneNumber(NEW_PHONE_NUMBER);

		advertismentService.save(advertisment);
	}

	/**
	 * method tests if an certain element from data base, that should not be
	 * removed, can be removed, and if can throws an exception
	 * 
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testRemoveNegative() {
		int dbSizeBeforeRemove = advertismentService.findAll().size();
		advertismentService.remove(DB_ID_REFERENCED);

		List<Advertisment> advertisments = advertismentService.findAll();
		assertThat(advertisments).hasSize(dbSizeBeforeRemove - 1);

		Advertisment dbAdvertisment = advertismentService.findOne(DB_ID_REFERENCED);
		assertThat(dbAdvertisment).isNull();
	}

	/**
	 * method test if all of certain elements from the data base can be found by
	 * Status and Publisher
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testFindBySatusAndPublisher() {
		Advertisment advertisment = advertismentService.findOne(DB_ID);

		assertThat(advertismentService.findBySatusAndPublisher(advertisment.getRealEstate().getStatus(),
				advertisment.getPublisher())).hasSize(DB_PUBLISHER_COUNT);
	}

	/**
	 * method test if all of certain elements from the data base can be found by
	 * Publisher
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByPublisherAndIsDeletedFalseOrderById() {
		Advertisment advertisment = advertismentService.findOne(DB_ID);

		assertThat(advertismentService.findByPublisherAndIsDeletedFalseOrderById(advertisment.getPublisher()))
				.hasSize(DB_PUBLISHER_COUNT);
	}

	/**
	 * method test if all of certain elements from the data base can be found by
	 * Company's ID
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByPublisherCompanyIdAndIsDeletedFalseOrderById() {
		Advertisment advertisment = advertismentService.findOne(DB_ID);

		assertThat(advertismentService
				.findByPublisherCompanyIdAndIsDeletedFalseOrderById(advertisment.getPublisher().getCompany().getId()))
						.hasSize(DB_PUBLISHER_COUNT);
	}

	/**
	 * method test if all of certain elements from the data base can be found by
	 * Status and Company's ID
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByPublisherCompanyIdAndIsDeletedFalseAndRealEstateStatusOrderById() {
		Advertisment advertisment = advertismentService.findOne(DB_ID);

		assertThat(advertismentService.findByPublisherCompanyIdAndIsDeletedFalseAndRealEstateStatusOrderById(
				advertisment.getRealEstate().getStatus(), advertisment.getPublisher().getCompany().getId()))
						.hasSize(DB_PUBLISHER_COUNT);
	}
}
