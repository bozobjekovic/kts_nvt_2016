package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static tim9.realEstate.constants.LocationConstants.*;

import java.util.List;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.model.Location;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class LocationServiceTest {

	@Autowired
	LocationService locationService;

	/**
	 * method tests if an certain element from the data base can be found
	 **/
	@Test
	public void testFindOne() {
		Location dbLocation = locationService.findOne(DB_ID);
		assertThat(dbLocation).isNotNull();

		assertThat(dbLocation.getId()).isEqualTo(DB_ID);
		assertThat(dbLocation.getCity()).isEqualTo(DB_CITY);
		assertThat(dbLocation.getPartOfTheCity()).isEqualTo(DB_PART_OF_THE_CITY);
		assertThat(dbLocation.getZipCode()).isEqualTo(DB_ZIP_CODE);
	}

	/**
	 * method test if all of certain elements from the data base can be found
	 **/
	@Test
	public void testFindAll() {
		List<Location> locations = locationService.findAll();
		assertThat(locations).hasSize(DB_COUNT);
	}

	/**
	 * method tests if a new element can be saved into data base
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		Location location = new Location();
		location.setCity(NEW_CITY);
		location.setPartOfTheCity(NEW_PART_OF_THE_CITY);
		location.setZipCode(NEW_ZIP_CODE);

		int dbSizeBeforeAdd = locationService.findAll().size();

		Location dbLocation = locationService.save(location);
		assertThat(dbLocation).isNotNull();

		List<Location> locations = locationService.findAll();
		assertThat(locations).hasSize(dbSizeBeforeAdd + 1);

		dbLocation = locations.get(locations.size() - 1);
		assertThat(dbLocation.getCity()).isEqualTo(NEW_CITY);
		assertThat(dbLocation.getPartOfTheCity()).isEqualTo(NEW_PART_OF_THE_CITY);
		assertThat(dbLocation.getZipCode()).isEqualTo(NEW_ZIP_CODE);
	}

	/**
	 * method tests if a certain element from the data base can be updated
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		Location dbLocation = locationService.findOne(DB_ID);

		dbLocation.setCity(NEW_CITY);
		dbLocation.setPartOfTheCity(NEW_PART_OF_THE_CITY);
		dbLocation.setZipCode(NEW_ZIP_CODE);

		dbLocation = locationService.save(dbLocation);
		assertThat(dbLocation).isNotNull();

		dbLocation = locationService.findOne(DB_ID);

		assertThat(dbLocation.getCity()).isEqualTo(NEW_CITY);
		assertThat(dbLocation.getPartOfTheCity()).isEqualTo(NEW_PART_OF_THE_CITY);
		assertThat(dbLocation.getZipCode()).isEqualTo(NEW_ZIP_CODE);
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
	public void testNegativeRemove() {
		int dbSizeBeforeRemove = locationService.findAll().size();
		locationService.remove(DB_ID);

		List<Location> locations = locationService.findAll();
		assertThat(locations).hasSize(dbSizeBeforeRemove - 1);

		Location dbLocation = locationService.findOne(DB_ID);
		assertThat(dbLocation).isNull();
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
	public void testAddNullCity() {
		Location location = new Location();

		location.setPartOfTheCity(NEW_PART_OF_THE_CITY);
		location.setZipCode(NEW_ZIP_CODE);

		locationService.save(location);
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
	public void testAddNullPartOfTheCity() {
		Location location = new Location();

		location.setCity(NEW_CITY);
		location.setZipCode(NEW_ZIP_CODE);

		locationService.save(location);
	}

	/**
	 * method test if all of certain elements from the data base can be found by
	 * City, Zip Code and Part od the City
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByCityAndZipCodeAndPartOfTheCity() {
		Location location = locationService.findOne(DB_ID);

		Location loc = locationService.findByCityAndZipCodeAndPartOfTheCity
				(location.getCity(),location.getZipCode(), location.getPartOfTheCity());
		assertThat(loc).isNotNull();

		assertThat(loc.getId()).isEqualTo(DB_ID);
		assertThat(loc.getCity()).isEqualTo(DB_CITY);
		assertThat(loc.getZipCode()).isEqualTo(DB_ZIP_CODE);
		assertThat(loc.getPartOfTheCity()).isEqualTo(DB_PART_OF_THE_CITY);
	}

	/**
	 * method test if all Part of the Cities can be get from the data base
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testGetAllPartOfTheCities() {
		assertThat(locationService.getAllPartOfTheCities()).hasSize(DB_COUNT);
	}

	/**
	 * method test if all Cities can be get from the data base
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testGetAllCities() {
		assertThat(locationService.getAllCities()).hasSize(DB_CITY_COUNT);
	}

	/**
	 * method test if all of certain elements from the data base can be get by
	 * City
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testGetAllPartOfTheCitiesByCity() {
		Location location = locationService.findOne(DB_ID);

		assertThat(locationService.getAllPartOfTheCitiesByCity(location.getCity())).hasSize(DB_PART_OF_THE_CITY_COUNT);
	}
}
