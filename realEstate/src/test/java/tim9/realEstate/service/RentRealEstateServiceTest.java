package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;

import static tim9.realEstate.constants.RentRealEstateConstants.*;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.model.RealEstate;
import tim9.realEstate.model.RentRealEstate;

/**
 * This class represents RentRealEstate Service Test
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class RentRealEstateServiceTest {

	@Autowired
	RentRealEstateService rentRealEstateService;
	@Autowired
	RealEstateService realEstateService;

	/**
	 * method test if all of certain elements from the data base can be find by
	 * RealEstate
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByRealEstate() {
		RealEstate realEstate = realEstateService.findOne(2L);

		assertThat(rentRealEstateService.findByRealEstate(realEstate)).hasSize(DB_COUNT);
	}

	/**
	 * method tests if a new element can be saved into data base
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		RentRealEstate rentRealEstate = new RentRealEstate();
		rentRealEstate.setRentedFrom(new Date(120120014));
		rentRealEstate.setRentedTo(new Date(120720014));

		int dbSizeBeforeAdd = rentRealEstateService.findAll().size();

		rentRealEstateService.save(rentRealEstate);

		List<RentRealEstate> list = rentRealEstateService.findAll();
		assertThat(list).hasSize(dbSizeBeforeAdd + 1);
	}
}
