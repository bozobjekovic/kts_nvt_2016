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
@TestPropertySource(locations = "classpath:test.properties")
public class AuthorityServiceTest {

	@Autowired
	AuthorityService authorityService;

	/**
	 * method tests if an certain element from the data base can be found
	 **/
	@Test
	public void testFindOne() {
		Authority dbAuthority = authorityService.findOne(DB_ID);
		assertThat(dbAuthority).isNotNull();

		assertThat(dbAuthority.getId()).isEqualTo(DB_ID);
		assertThat(dbAuthority.getName()).isEqualTo(DB_NAME);
	}

	/**
	 * method test if all of certain elements from the data base can be found
	 **/
	@Test
	public void testFindAll() {
		List<Authority> authorities = authorityService.findAll();
		assertThat(authorities).hasSize(DB_COUNT);
	}

	/**
	 * method tests if a new element can be saved into data base
	 **/
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

	/**
	 * method tests if a certain element from the data base can be updated
	 **/
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
		int dbSizeBeforeRemove = authorityService.findAll().size();
		authorityService.remove(DB_ID);

		List<Authority> authorities = authorityService.findAll();
		assertThat(authorities).hasSize(dbSizeBeforeRemove - 1);

		Authority dbAuthority = authorityService.findOne(DB_ID);
		assertThat(dbAuthority).isNull();
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
	public void testAddNullName() {
		Authority authority = new Authority();

		authorityService.save(authority);
	}

	/**
	 * method tests if an certain element from the data base can be found by
	 * Name
	 **/
	@Test
	public void testFindByName() {
		Authority dbAuthority = authorityService.findByName(DB_NAME);
		assertThat(dbAuthority).isNotNull();

		assertThat(dbAuthority.getId()).isEqualTo(DB_ID);
		assertThat(dbAuthority.getName()).isEqualTo(DB_NAME);
	}
}
