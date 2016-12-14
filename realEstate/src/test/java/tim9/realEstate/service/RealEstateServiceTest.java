package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static tim9.realEstate.constants.RealEstateConstants.DB_BUILD_YEAR;
import static tim9.realEstate.constants.RealEstateConstants.DB_CATEGORY;
import static tim9.realEstate.constants.RealEstateConstants.DB_COUNT;
import static tim9.realEstate.constants.RealEstateConstants.DB_HEATING_TYPE;
import static tim9.realEstate.constants.RealEstateConstants.DB_ID;
import static tim9.realEstate.constants.RealEstateConstants.DB_ID_REFERENCED;
import static tim9.realEstate.constants.RealEstateConstants.DB_LAND_SIZE;
import static tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_BATH_ROOMS;
import static tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_BED_ROOMS;
import static tim9.realEstate.constants.RealEstateConstants.DB_NUM_OF_FLORS;
import static tim9.realEstate.constants.RealEstateConstants.DB_TEACH_EQUIPMENT;
import static tim9.realEstate.constants.RealEstateConstants.DB_TYPE;
import static tim9.realEstate.constants.RealEstateConstants.NEW_BUILD_YEAR;
import static tim9.realEstate.constants.RealEstateConstants.NEW_CATEGORY;
import static tim9.realEstate.constants.RealEstateConstants.NEW_HEATING_TYPE;
import static tim9.realEstate.constants.RealEstateConstants.NEW_LAND_SIZE;
import static tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BATH_ROOMS;
import static tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_BED_ROOMS;
import static tim9.realEstate.constants.RealEstateConstants.NEW_NUM_OF_FLORS;
import static tim9.realEstate.constants.RealEstateConstants.NEW_TEACH_EQUIPMENT;
import static tim9.realEstate.constants.RealEstateConstants.NEW_TYPE;

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

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class RealEstateServiceTest {
	
	@Autowired
	RealEstateService realEstateService;
	
	@Test
	public void testFindOne() {
		RealEstate dbRealEstate = realEstateService.findOne(DB_ID);
		assertThat(dbRealEstate).isNotNull();
		
		assertThat(dbRealEstate.getId()).isEqualTo(DB_ID);
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

	@Test
	public void testFindAll() {	
		List<RealEstate> realEstates = realEstateService.findAll();
		assertThat(realEstates).hasSize(DB_COUNT);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		RealEstate realEstate = new RealEstate();
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
	
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdate() {
		RealEstate dbRealEstate = realEstateService.findOne(DB_ID);
		
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

}
