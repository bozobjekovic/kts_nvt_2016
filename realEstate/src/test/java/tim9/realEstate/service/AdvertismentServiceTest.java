package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static tim9.realEstate.constants.AdvertismentConstants.DB_CATEGORY;
import static tim9.realEstate.constants.AdvertismentConstants.DB_COUNT;
import static tim9.realEstate.constants.AdvertismentConstants.DB_ID;
import static tim9.realEstate.constants.AdvertismentConstants.DB_ID_REFERENCED;
import static tim9.realEstate.constants.AdvertismentConstants.DB_PHONE_NUMBER;
import static tim9.realEstate.constants.AdvertismentConstants.DB_PURPOSE;
import static tim9.realEstate.constants.AdvertismentConstants.DB_RATE;
import static tim9.realEstate.constants.AdvertismentConstants.DB_TYPE;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_DATE;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_NUM_OF_RATES;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_PHONE_NUMBER;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_PURPOSE;
import static tim9.realEstate.constants.AdvertismentConstants.NEW_RATE;
import static tim9.realEstate.constants.AdvertismentConstants.PAGE_SIZE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.model.Advertisment;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class AdvertismentServiceTest {
	
	@Autowired
	AdvertismentService advertismentService;
	
	@Test
	public void testFindAll() {
		List<Advertisment> advertisements = advertismentService.findAll();
		assertThat(advertisements).hasSize(DB_COUNT);
	}
	
	@Test
	public void testFindAllPageable() {
		PageRequest pageRequest = new PageRequest(0, PAGE_SIZE);
		Page<Advertisment> advertisements = advertismentService.findAll(pageRequest);
		assertThat(advertisements).hasSize(PAGE_SIZE); 
	}
	
	@Test 
	public void testFindOne() {
		Advertisment dbAdvertisment = advertismentService.findOne(DB_ID);
		assertThat(dbAdvertisment).isNotNull();
		
		assertThat(dbAdvertisment.getId()).isEqualTo(DB_ID);
		assertThat(dbAdvertisment.getPurpose()).isEqualTo(DB_PURPOSE);
        assertThat(dbAdvertisment.getPhoneNumber()).isEqualTo(DB_PHONE_NUMBER);
        assertThat(dbAdvertisment.getRate()).isEqualTo(DB_RATE);  
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void testInsert() {
		Advertisment advertisment = new Advertisment();
		advertisment.setPublicationDate(NEW_DATE);
		advertisment.setModificationDate(NEW_DATE);
		advertisment.setActiveUntil(NEW_DATE);
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
        assertThat(dbAdvertisment.getPublicationDate()).isEqualTo(NEW_DATE);
        assertThat(dbAdvertisment.getModificationDate()).isEqualTo(NEW_DATE);
        assertThat(dbAdvertisment.getActiveUntil()).isEqualTo(NEW_DATE);
        assertThat(dbAdvertisment.getPurpose()).isEqualTo(NEW_PURPOSE);
        assertThat(dbAdvertisment.getRate()).isEqualTo(NEW_RATE);
        assertThat(dbAdvertisment.getNumberOfRates()).isEqualTo(NEW_NUM_OF_RATES);  
        assertThat(dbAdvertisment.getPhoneNumber()).isEqualTo(NEW_PHONE_NUMBER);  
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdate() {
		Advertisment dbAdvertisment = advertismentService.findOne(DB_ID);
		
		dbAdvertisment.setActiveUntil(NEW_DATE);
		dbAdvertisment.setPurpose(NEW_PURPOSE);
		dbAdvertisment.setRate(NEW_RATE);
		
		dbAdvertisment = advertismentService.save(dbAdvertisment);
		assertThat(dbAdvertisment).isNotNull();
		
		dbAdvertisment = advertismentService.findOne(DB_ID);
		assertThat(dbAdvertisment.getActiveUntil()).isEqualTo(NEW_DATE);
        assertThat(dbAdvertisment.getPurpose()).isEqualTo(NEW_PURPOSE);
        assertThat(dbAdvertisment.getRate()).isEqualTo(NEW_RATE);
	}
	
	@SuppressWarnings("unused")
	@Test
	@Transactional
	@Rollback(true)
	public void testRemove() {
		int dbSizeBeforeRemove = advertismentService.findAll().size();
		advertismentService.remove(DB_ID);

		//List<Advertisment> advertisments = advertismentService.findAll();
		//assertThat(advertisments).hasSize(dbSizeBeforeRemove - 1);
		
		Advertisment dbAdvertisment = advertismentService.findOne(DB_ID);
		assertThat(dbAdvertisment).isNull();
	}
	
	@Test
	public void testFindByPurpose() {
		PageRequest pageRequest = new PageRequest(0, PAGE_SIZE);
		Page<Advertisment> advertisments = advertismentService.findByPurpose(DB_PURPOSE, pageRequest);
		assertThat(advertisments).hasSize(1);
		List<Advertisment> advertList = new ArrayList<>();
		for (Advertisment a : advertisments) {
			advertList.add(a);
		}
		assertThat(advertList.get(advertList.size() - 1).getPurpose()).isEqualTo(DB_PURPOSE);
	}
	
	@Test
	public void testFindByRealEstate_Category() {
		List<Advertisment> advertisments = advertismentService.findByRealEstate_Category(DB_CATEGORY);
		assertThat(advertisments.size()).isEqualTo(2);
		for (int i = 0; i < advertisments.size(); i++) {
			assertThat(advertisments.get(i).getRealEstate().getCategory()).isEqualTo(DB_CATEGORY);
		}
	}	
	
	@Test
	public void testFindByRealEstate_Type() {
		List<Advertisment> advertisments = advertismentService.findByRealEstate_Type(DB_TYPE);
		assertThat(advertisments.size()).isEqualTo(1);
		for (int i = 0; i < advertisments.size(); i++) {
			assertThat(advertisments.get(i).getRealEstate().getType()).isEqualTo(DB_TYPE);
		}
	}
	
	@Test
	public void testOrderByRate() {
		PageRequest pageRequest = new PageRequest(0, 3);
		List<Advertisment> advertisments = advertismentService.orderByRate(pageRequest);
		List<Advertisment> advertList = new ArrayList<>();
		for (Advertisment a : advertisments) {
			advertList.add(a);
		}
		assertThat(advertList.get(0).getRate()).isGreaterThan(advertList.get(1).getRate());
		assertThat(advertList.get(0).getRate()).isGreaterThan(advertList.get(2).getRate());
		assertThat(advertList.get(1).getRate()).isGreaterThan(advertList.get(2).getRate());
	}
	
	/*
	 * Negative tests
	 */
	
	@Test(expected = DataIntegrityViolationException.class)
    @Transactional
    @Rollback(true)
	public void testAddUniquePhoneNumber() {
		Advertisment advertisment = new Advertisment();
		advertisment.setPublicationDate(NEW_DATE);
		advertisment.setModificationDate(NEW_DATE);
		advertisment.setActiveUntil(NEW_DATE);
		advertisment.setPurpose(NEW_PURPOSE);
		advertisment.setRate(NEW_RATE);
		advertisment.setNumberOfRates(NEW_NUM_OF_RATES);
		advertisment.setPhoneNumber(DB_PHONE_NUMBER);
		
		advertismentService.save(advertisment);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullPurpose() {
		Advertisment advertisment = new Advertisment();
		advertisment.setPublicationDate(NEW_DATE);
		advertisment.setModificationDate(NEW_DATE);
		advertisment.setActiveUntil(NEW_DATE);
		advertisment.setRate(NEW_RATE);
		advertisment.setNumberOfRates(NEW_NUM_OF_RATES);
		advertisment.setPhoneNumber(NEW_PHONE_NUMBER);;
		
		advertismentService.save(advertisment);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullPublicationDate() {
		Advertisment advertisment = new Advertisment();
		advertisment.setModificationDate(NEW_DATE);
		advertisment.setActiveUntil(NEW_DATE);
		advertisment.setPurpose(NEW_PURPOSE);
		advertisment.setRate(NEW_RATE);
		advertisment.setNumberOfRates(NEW_NUM_OF_RATES);
		advertisment.setPhoneNumber(NEW_PHONE_NUMBER);;
		
		advertismentService.save(advertisment);
	}
	
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
}
