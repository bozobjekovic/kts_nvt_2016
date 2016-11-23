package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static tim9.realEstate.constants.AuthorityConstants.*;

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
import tim9.realEstate.model.Authority;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class AuthorityServiceTest {

	@Autowired
	AuthorityService authorityService;
	
	/*****   1. TEST FIND ONE   *****/
	@Test
	public void testFindOne() {
		Authority dbAuthority = authorityService.findOne(DB_ID);
		assertThat(dbAuthority).isNotNull();
		
		assertThat(dbAuthority.getId()).isEqualTo(DB_ID);
		assertThat(dbAuthority.getName()).isEqualTo(DB_NAME);
	}
	
	/*****   2. TEST FIND ALL   *****/
	@Test
	public void testFindAll() {
		List<Authority> authorities = authorityService.findAll();
		assertThat(authorities).hasSize(DB_COUNT);
	}
	
	/*****   3. TEST SAVE   *****/
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		Authority authority = new Authority();
		authority.setName(NEW_NAME);
		
		int dbSizeBeforeAdd = authorityService.findAll().size();
		
		Authority dbAuthority = authorityService.save(authority);
		assertThat(dbAuthority).isNotNull();
		
		List<Authority> authorities = authorityService.findAll();
		assertThat(authorities).hasSize(dbSizeBeforeAdd + 1);
		
		dbAuthority = authorities.get(authorities.size() - 1);
		assertThat(dbAuthority.getName()).isEqualTo(NEW_NAME);
	}
	
	/*****   4. TEST UPDATE   *****/
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdate() {
		Authority dbAuthority = authorityService.findOne(DB_ID);
		
		dbAuthority.setName(NEW_NAME);
		
		dbAuthority = authorityService.save(dbAuthority);
		assertThat(dbAuthority).isNotNull();
		
		dbAuthority = authorityService.findOne(DB_ID);

		assertThat(dbAuthority.getName()).isEqualTo(NEW_NAME);
	}
	
	/*****   5. TEST REMOVE   *****/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testRemove() {
		int dbSizeBeforeRemove = authorityService.findAll().size();
		authorityService.remove(DB_ID_REFERENCED);
		
		List<Authority> authorities = authorityService.findAll();
		assertThat(authorities).hasSize(dbSizeBeforeRemove - 1);
		
		Authority dbAuthority = authorityService.findOne(DB_ID_REFERENCED);
		assertThat(dbAuthority).isNull();
	}
}
