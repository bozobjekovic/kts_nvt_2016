package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static tim9.realEstate.constants.InappropriateConstants.*;

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
import tim9.realEstate.model.Inappropriate;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class InappropriateServiceTest {
	
	@Autowired
	InappropriateService inappropriateService;
	
	/**
	 * <h1> Positive tests </h1>
	 */
	
	/**
	 * method tests if an certain element from the data base can be found
	 **/
	@Test
	public void testFindOne() {
		Inappropriate dbInappropriate = inappropriateService.findOne(DB_ID);
		assertThat(dbInappropriate).isNotNull();
		
		assertThat(dbInappropriate.getId()).isEqualTo(DB_ID);
		assertThat(dbInappropriate.getDescription()).isEqualTo(DB_DESCRIPTION);
		assertThat(dbInappropriate.getTitle()).isEqualTo(DB_TITLE_REAL);
	}
	
	/**
	 * method test if all of certain elements from the data base can be found
	 **/
	@Test
	public void testFindAll() {
		List<Inappropriate> inappropriates = inappropriateService.findAll();
		assertThat(inappropriates).hasSize(DB_COUNT_REAL);
	}
	
	/**
	 * method tests if a new element can be saved into data base
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		Inappropriate inappropriate = new Inappropriate();
		inappropriate.setDescription(NEW_DESCRIPTION);
		inappropriate.setTitle(NEW_TITLE);
		
		int dbSizeBeforeAdd = inappropriateService.findAll().size();
		
		Inappropriate dbInappropriate = inappropriateService.save(inappropriate);
		assertThat(dbInappropriate).isNotNull();
		
		List<Inappropriate> inappropriates = inappropriateService.findAll();
		assertThat(inappropriates).hasSize(dbSizeBeforeAdd + 1);
		
		dbInappropriate = inappropriates.get(inappropriates.size() - 1);
		assertThat(dbInappropriate.getDescription()).isEqualTo(NEW_DESCRIPTION);
		assertThat(dbInappropriate.getTitle()).isEqualTo(NEW_TITLE);
	}
	
	/**
	 * method tests if a certain element from the data base can be updated
	 **/
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdate() {
		Inappropriate dbInappropriate = inappropriateService.findOne(DB_ID);
		
		dbInappropriate.setDescription(NEW_DESCRIPTION);
		dbInappropriate.setTitle(NEW_TITLE);
		
		dbInappropriate = inappropriateService.save(dbInappropriate);
		assertThat(dbInappropriate).isNotNull();
		
		dbInappropriate = inappropriateService.findOne(DB_ID);

		assertThat(dbInappropriate.getDescription()).isEqualTo(NEW_DESCRIPTION);
		assertThat(dbInappropriate.getTitle()).isEqualTo(NEW_TITLE);
	}
	
	/**
	 * method tests if a certain element from the data base can be removed
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRemove() {
		int dbSizeBeforeRemove = inappropriateService.findAll().size();
		inappropriateService.remove(DB_ID_REFERENCED);
		
		List<Inappropriate> inappropriates = inappropriateService.findAll();
		assertThat(inappropriates).hasSize(dbSizeBeforeRemove - 1);
		
		Inappropriate dbInappropriate = inappropriateService.findOne(DB_ID_REFERENCED);
		assertThat(dbInappropriate).isNull();
	}

	/**
	 * <h1> Negative tests </h1>
	 */
	
	/**
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullDescription() {
		Inappropriate inappropriate = new Inappropriate();
		
		inappropriate.setTitle(NEW_TITLE);
		
		inappropriateService.save(inappropriate);
	}
}
