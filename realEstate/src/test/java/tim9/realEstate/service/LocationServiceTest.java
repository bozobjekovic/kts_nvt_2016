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
@TestPropertySource(locations="classpath:test.properties")
public class LocationServiceTest {

	@Autowired
	LocationService locationService;
	
	/**
	 * <h1> Positive tests </h1>
	 */
	
	/**
	 * <b>testFindOne()</b>
	 * method tests if an certain element from the data base can be found
	 **/
	@Test
	public void testFindOne() {
		Location dbLocation = locationService.findOne(DB_ID);
		assertThat(dbLocation).isNotNull();
		
		assertThat(dbLocation.getId()).isEqualTo(DB_ID);
		assertThat(dbLocation.getAddress()).isEqualTo(DB_ADDRESS);
		assertThat(dbLocation.getCity()).isEqualTo(DB_CITY);
		assertThat(dbLocation.getPartOfTheCity()).isEqualTo(DB_PART_OF_THE_CITY);
		assertThat(dbLocation.getZipCode()).isEqualTo(DB_ZIP_CODE);
	}
	
	/**
	 * <b>testFindAll()</b>
	 * method test if all of certain elements from the data base can be found
	 **/
	@Test
	public void testFindAll() {
		List<Location> locations = locationService.findAll();
		assertThat(locations).hasSize(DB_COUNT);
	}
	
	/**
	 * <b>testSave()</b>
	 * method tests if a new element can be saved into data base
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		Location location = new Location();
		location.setAddress(NEW_ADDRESS);
		location.setCity(NEW_CITY);
		location.setPartOfTheCity(NEW_PART_OF_THE_CITY);
		location.setZipCode(NEW_ZIP_CODE);
		
		int dbSizeBeforeAdd = locationService.findAll().size();
		
		Location dbLocation = locationService.save(location);
		assertThat(dbLocation).isNotNull();
		
		List<Location> locations = locationService.findAll();
		assertThat(locations).hasSize(dbSizeBeforeAdd + 1);
		
		dbLocation = locations.get(locations.size() - 1);
		assertThat(dbLocation.getAddress()).isEqualTo(NEW_ADDRESS);
		assertThat(dbLocation.getCity()).isEqualTo(NEW_CITY);
		assertThat(dbLocation.getPartOfTheCity()).isEqualTo(NEW_PART_OF_THE_CITY);
		assertThat(dbLocation.getZipCode()).isEqualTo(NEW_ZIP_CODE);
	}
	
	/**
	 * <b>testUpdate()</b>
	 * method tests if a certain element from the data base can be updated
	 **/
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdate() {
		Location dbLocation = locationService.findOne(DB_ID);

		dbLocation.setAddress(NEW_ADDRESS);
		dbLocation.setCity(NEW_CITY);
		dbLocation.setPartOfTheCity(NEW_PART_OF_THE_CITY);
		dbLocation.setZipCode(NEW_ZIP_CODE);
		
		dbLocation = locationService.save(dbLocation);
		assertThat(dbLocation).isNotNull();
		
		dbLocation = locationService.findOne(DB_ID);

        assertThat(dbLocation.getAddress()).isEqualTo(NEW_ADDRESS);
        assertThat(dbLocation.getCity()).isEqualTo(NEW_CITY);
        assertThat(dbLocation.getPartOfTheCity()).isEqualTo(NEW_PART_OF_THE_CITY);
        assertThat(dbLocation.getZipCode()).isEqualTo(NEW_ZIP_CODE);
	}
	
	/**
	 * <b>testRemove()</b>
	 * method tests if a certain element from the data base can be removed
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRemove() {
		int dbSizeBeforeRemove = locationService.findAll().size();
		locationService.remove(DB_ID_REFERENCED);
		
		List<Location> locations = locationService.findAll();
		assertThat(locations).hasSize(dbSizeBeforeRemove - 1);
		
		Location dbLocation = locationService.findOne(DB_ID_REFERENCED);
		assertThat(dbLocation).isNull();
	}
	
	/**
	 * <h1> Negative tests </h1>
	 */
	
	/**
	 * <b>testNegativeRemove()</b>
	 * method tests if an certain element from data base,
	 * that should not be removed, can be removed,
	 * and if can throws an exception
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
	 * <b>testAddNullAddress()</b>
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullAddress() {
		Location location = new Location();
		
		location.setCity(NEW_CITY);
		location.setPartOfTheCity(NEW_PART_OF_THE_CITY);
		location.setZipCode(NEW_ZIP_CODE);
		
		locationService.save(location);
	}
	
	/**
	 * <b>testAddNullCity()</b>
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullCity() {
		Location location = new Location();
		
		location.setAddress(NEW_ADDRESS);
		location.setPartOfTheCity(NEW_PART_OF_THE_CITY);
		location.setZipCode(NEW_ZIP_CODE);
		
		locationService.save(location);
	}
	
	/**
	 * <b>testAddNullZipCode()</b>
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	
	/*
	 * 
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullZipCode() {
		Location location = new Location();

		location.setAddress(NEW_ADDRESS);
		location.setCity(NEW_CITY);
		location.setPartOfTheCity(NEW_PART_OF_THE_CITY);
		
		locationService.save(location);
	}
	
	*/
}
