package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static tim9.realEstate.constants.RealEstateConstants.*;

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
import tim9.realEstate.model.RealEstate;

/**
 * This class represents RealEstate Service Test
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class RealEstateServiceTest {

	@Autowired
	RealEstateService realEstateService;

	/**
	 * method tests if an certain element from the data base can be found
	 **/
	@Test
	public void testFindOne() {
		RealEstate dbRealEstate = realEstateService.findOne(DB_ID);
		assertThat(dbRealEstate).isNotNull();

		assertThat(dbRealEstate.getId()).isEqualTo(DB_ID);
		assertThat(dbRealEstate.getAddress()).isEqualTo(DB_ADDRESS);
		assertThat(dbRealEstate.getLandSize()).isEqualTo(DB_LAND_SIZE);
		assertThat(dbRealEstate.getTechEquipment()).isEqualTo(DB_TEACH_EQUIPMENT);
		assertThat(dbRealEstate.getHeatingType()).isEqualTo(DB_HEATING_TYPE);
		assertThat(dbRealEstate.getNumOfBathRooms()).isEqualTo(DB_NUM_OF_BATH_ROOMS);
		assertThat(dbRealEstate.getNumOfBedRooms()).isEqualTo(DB_NUM_OF_BED_ROOMS);
		assertThat(dbRealEstate.getNumOfFlors()).isEqualTo(DB_NUM_OF_FLORS);
		assertThat(dbRealEstate.getBuildYear()).isEqualTo(DB_BUILD_YEAR);
		assertThat(dbRealEstate.getCategory()).isEqualTo(DB_CATEGORY);
		assertThat(dbRealEstate.getType()).isEqualTo(DB_TYPE);
	}

	/**
	 * method test if all of certain elements from the data base can be found
	 **/
	@Test
	public void testFindAll() {
		List<RealEstate> realEstates = realEstateService.findAll();
		assertThat(realEstates).hasSize(DB_COUNT_REAL);
	}

	/**
	 * method test if all of certain elements from the data base can be found by
	 * Address
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByAddress() {
		RealEstate re = realEstateService.findByAddress(DB_ADDRESS);
		assertThat(re).isNotNull();

		assertThat(re.getId()).isEqualTo(DB_ID);
		assertThat(re.getAddress()).isEqualTo(DB_ADDRESS);
		assertThat(re.getBuildYear()).isEqualTo(DB_BUILD_YEAR);
		assertThat(re.getCategory()).isEqualTo(DB_CATEGORY);
		assertThat(re.getLandSize()).isEqualTo(DB_LAND_SIZE);
		assertThat(re.getHeatingType()).isEqualTo(DB_HEATING_TYPE);
		assertThat(re.getNumOfBathRooms()).isEqualTo(DB_NUM_OF_BATH_ROOMS);
		assertThat(re.getNumOfBedRooms()).isEqualTo(DB_NUM_OF_BED_ROOMS);
		assertThat(re.getNumOfFlors()).isEqualTo(DB_NUM_OF_FLORS);
	}

	/**
	 * method test if all of certain elements from the data base can be get by
	 * Address and City
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByAddressAndCity() {
		RealEstate realEstate = realEstateService.findOne(DB_ID);

		assertThat(realEstateService.findByAddressAndCity(realEstate.getAddress(), realEstate.getLocation().getCity()))
				.hasSize(DB_CITY_ADDRESS_COUNT);
	}

	/**
	 * method tests if a new element can be saved into data base
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		RealEstate realEstate = new RealEstate();
		realEstate.setAddress(NEW_ADDRESS);
		realEstate.setLandSize(NEW_LAND_SIZE);
		realEstate.setTechEquipment(NEW_TEACH_EQUIPMENT);
		realEstate.setHeatingType(NEW_HEATING_TYPE);
		realEstate.setNumOfBathRooms(NEW_NUM_OF_BATH_ROOMS);
		realEstate.setNumOfBedRooms(NEW_NUM_OF_BED_ROOMS);
		realEstate.setNumOfFlors(NEW_NUM_OF_FLORS);
		realEstate.setBuildYear(NEW_BUILD_YEAR);
		realEstate.setCategory(NEW_CATEGORY);
		realEstate.setType(NEW_TYPE);

		int dbSizeBeforeAdd = realEstateService.findAll().size();

		RealEstate dbRealEstate = realEstateService.save(realEstate);
		assertThat(dbRealEstate).isNotNull();

		List<RealEstate> realEstates = realEstateService.findAll();
		assertThat(realEstates).hasSize(dbSizeBeforeAdd + 1);

		dbRealEstate = realEstates.get(realEstates.size() - 1);
		assertThat(dbRealEstate.getAddress()).isEqualTo(NEW_ADDRESS);
		assertThat(dbRealEstate.getLandSize()).isEqualTo(NEW_LAND_SIZE);
		assertThat(dbRealEstate.getTechEquipment()).isEqualTo(NEW_TEACH_EQUIPMENT);
		assertThat(dbRealEstate.getHeatingType()).isEqualTo(NEW_HEATING_TYPE);
		assertThat(dbRealEstate.getNumOfBathRooms()).isEqualTo(NEW_NUM_OF_BATH_ROOMS);
		assertThat(dbRealEstate.getNumOfBedRooms()).isEqualTo(NEW_NUM_OF_BED_ROOMS);
		assertThat(dbRealEstate.getNumOfFlors()).isEqualTo(NEW_NUM_OF_FLORS);
		assertThat(dbRealEstate.getBuildYear()).isEqualTo(NEW_BUILD_YEAR);
		assertThat(dbRealEstate.getCategory()).isEqualTo(NEW_CATEGORY);
		assertThat(dbRealEstate.getType()).isEqualTo(NEW_TYPE);
	}

	/**
	 * method tests if a certain element from the data base can be updated
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		RealEstate dbRealEstate = realEstateService.findOne(DB_ID);

		dbRealEstate.setAddress(NEW_ADDRESS);
		dbRealEstate.setLandSize(NEW_LAND_SIZE);
		dbRealEstate.setTechEquipment(NEW_TEACH_EQUIPMENT);
		dbRealEstate.setHeatingType(NEW_HEATING_TYPE);
		dbRealEstate.setNumOfBathRooms(NEW_NUM_OF_BATH_ROOMS);
		dbRealEstate.setNumOfBedRooms(NEW_NUM_OF_BED_ROOMS);
		dbRealEstate.setNumOfFlors(NEW_NUM_OF_FLORS);
		dbRealEstate.setBuildYear(NEW_BUILD_YEAR);
		dbRealEstate.setCategory(NEW_CATEGORY);
		dbRealEstate.setType(NEW_TYPE);

		dbRealEstate = realEstateService.save(dbRealEstate);
		assertThat(dbRealEstate).isNotNull();

		dbRealEstate = realEstateService.findOne(DB_ID);

		assertThat(dbRealEstate.getAddress()).isEqualTo(NEW_ADDRESS);
		assertThat(dbRealEstate.getLandSize()).isEqualTo(NEW_LAND_SIZE);
		assertThat(dbRealEstate.getTechEquipment()).isEqualTo(NEW_TEACH_EQUIPMENT);
		assertThat(dbRealEstate.getHeatingType()).isEqualTo(NEW_HEATING_TYPE);
		assertThat(dbRealEstate.getNumOfBathRooms()).isEqualTo(NEW_NUM_OF_BATH_ROOMS);
		assertThat(dbRealEstate.getNumOfBedRooms()).isEqualTo(NEW_NUM_OF_BED_ROOMS);
		assertThat(dbRealEstate.getNumOfFlors()).isEqualTo(NEW_NUM_OF_FLORS);
		assertThat(dbRealEstate.getBuildYear()).isEqualTo(NEW_BUILD_YEAR);
		assertThat(dbRealEstate.getCategory()).isEqualTo(NEW_CATEGORY);
		assertThat(dbRealEstate.getType()).isEqualTo(NEW_TYPE);
	}

	/**
	 * method tests if a certain element from the data base can be removed
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testRemove() {
		int dbSizeBeforeRemove = realEstateService.findAll().size();
		realEstateService.remove(DB_ID_REFERENCED);

		List<RealEstate> realEstates = realEstateService.findAll();
		assertThat(realEstates).hasSize(dbSizeBeforeRemove - 1);

		RealEstate dbRealEstate = realEstateService.findOne(DB_ID_REFERENCED);
		assertThat(dbRealEstate).isNull();
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
	public void testAddNullAddress() {
		RealEstate re = new RealEstate();

		re.setLandSize(NEW_LAND_SIZE);
		re.setTechEquipment(NEW_TEACH_EQUIPMENT);
		re.setHeatingType(NEW_HEATING_TYPE);
		re.setNumOfBathRooms(NEW_NUM_OF_BATH_ROOMS);
		re.setNumOfBedRooms(NEW_NUM_OF_BED_ROOMS);
		re.setNumOfFlors(NEW_NUM_OF_FLORS);
		re.setBuildYear(NEW_BUILD_YEAR);
		re.setCategory(NEW_CATEGORY);
		re.setType(NEW_TYPE);

		realEstateService.save(re);
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
	public void testAddNullType() {
		RealEstate re = new RealEstate();

		re.setAddress(NEW_ADDRESS);
		re.setLandSize(NEW_LAND_SIZE);
		re.setTechEquipment(NEW_TEACH_EQUIPMENT);
		re.setHeatingType(NEW_HEATING_TYPE);
		re.setNumOfBathRooms(NEW_NUM_OF_BATH_ROOMS);
		re.setNumOfBedRooms(NEW_NUM_OF_BED_ROOMS);
		re.setNumOfFlors(NEW_NUM_OF_FLORS);
		re.setBuildYear(NEW_BUILD_YEAR);
		re.setCategory(NEW_CATEGORY);

		realEstateService.save(re);
	}
}
