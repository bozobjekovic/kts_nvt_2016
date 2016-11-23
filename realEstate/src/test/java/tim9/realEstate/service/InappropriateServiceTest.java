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
	
	/*****   1. TEST FIND ONE   *****/
	@Test
	public void testFindOne() {
		Inappropriate dbInappropriate = inappropriateService.findOne(DB_ID);
		assertThat(dbInappropriate).isNotNull();
		
		assertThat(dbInappropriate.getId()).isEqualTo(DB_ID);
		assertThat(dbInappropriate.getDescription()).isEqualTo(DB_DESCRIPTION);
		assertThat(dbInappropriate.getTitle()).isEqualTo(DB_TITLE);
	}
	
	/*****   2. TEST FIND ALL   *****/
	@Test
	public void testFindAll() {
		List<Inappropriate> inappropriates = inappropriateService.findAll();
		assertThat(inappropriates).hasSize(DB_COUNT);
	}
	
	/*****   3. TEST SAVE   *****/
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
	
	/*****   4. TEST UPDATE   *****/
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
	
	/*****   5. TEST REMOVE   *****/
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
}
