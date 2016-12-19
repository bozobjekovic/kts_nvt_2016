package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static tim9.realEstate.constants.VerifierConstants.*;

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
import tim9.realEstate.model.Verifier;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class VerifierServiceTest {
	
	@Autowired
	VerifierService verifierService;

	/**
	 * <h1> Positive tests </h1>
	 */
	
	/**
	 * method tests if an certain element from the data base can be found
	 */
	@Test 
	public void testFindOne() {
		Verifier dbVerifier = verifierService.findOne(DB_ID);
		assertThat(dbVerifier).isNotNull();
		
		assertThat(dbVerifier.getId()).isEqualTo(DB_ID);
		assertThat(dbVerifier.getEmail()).isEqualTo(DB_EMAIL);
		assertThat(dbVerifier.getPassword()).isEqualTo(DB_PASSWORD_REAL);
        assertThat(dbVerifier.getUsername()).isEqualTo(DB_USERNAME);
	}
	
	/**
	 * method test if all of certain elements from the data base can be found
	 */
	@Test
	public void testFindAll() {
		List<Verifier> verifiers = verifierService.findAll();
		assertThat(verifiers).hasSize(DB_COUNT);
	}
	
	/**
	 * method tests if a new element can be saved into data base
	 */
	@Test
    @Transactional
    @Rollback(true)
	public void testSave() {
		Verifier verifier = new Verifier();
		verifier.setEmail(NEW_EMAIL);
		verifier.setPassword(NEW_PASSWORD);
		verifier.setUsername(NEW_USERNAME);
		
		int dbSizeBeforeAdd = verifierService.findAll().size();
		
		Verifier dbVerifier = verifierService.save(verifier);
		assertThat(dbVerifier).isNotNull();

        List<Verifier> verifiers = verifierService.findAll();
        assertThat(verifiers).hasSize(dbSizeBeforeAdd + 1);
        dbVerifier = verifiers.get(verifiers.size() - 1);
        assertThat(dbVerifier.getEmail()).isEqualTo(NEW_EMAIL);
        assertThat(dbVerifier.getPassword()).isEqualTo(NEW_PASSWORD);
        assertThat(dbVerifier.getUsername()).isEqualTo(NEW_USERNAME);
	}
	
	/**
	 * method tests if a certain element from the data base can be updated
	 */
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdate() {
		Verifier dbVerifier = verifierService.findOne(DB_ID);
		dbVerifier.setEmail(NEW_EMAIL);
		dbVerifier.setPassword(NEW_PASSWORD);
		dbVerifier.setUsername(NEW_USERNAME);
		
		dbVerifier = verifierService.save(dbVerifier);
		assertThat(dbVerifier).isNotNull();
		
		dbVerifier = verifierService.findOne(DB_ID);

        assertThat(dbVerifier.getEmail()).isEqualTo(NEW_EMAIL);
        assertThat(dbVerifier.getPassword()).isEqualTo(NEW_PASSWORD);
        assertThat(dbVerifier.getUsername()).isEqualTo(NEW_USERNAME);
	}
	
	/**
	 * <h1> Negative tests </h1>
	 */
	
	/**
	 * method tests if an certain element from data base,
	 * that should not be removed, can be removed,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 */
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testNegativeRemove() {
		int dbSizeBeforeRemove = verifierService.findAll().size();
		verifierService.remove(DB_ID);
		
		List<Verifier> verifiers = verifierService.findAll();
		assertThat(verifiers).hasSize(dbSizeBeforeRemove - 1);
		
		Verifier dbVerifier = verifierService.findOne(DB_ID);
		assertThat(dbVerifier).isNull();
	}
	
	/**
	 * method tests if an certain element can be added into data base
	 * without its primary key,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 */
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullEmail() {
		Verifier verifier = new Verifier();
		verifier.setPassword(NEW_PASSWORD);
		verifier.setUsername(NEW_USERNAME);
		
		verifierService.save(verifier);
	}
	
	/**
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 */
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullUsername() {
		Verifier verifier = new Verifier();
		verifier.setEmail(NEW_EMAIL);
		verifier.setPassword(NEW_PASSWORD);
		
		verifierService.save(verifier);
	}
	
	/**
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 */
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullPassword() {
		Verifier verifier = new Verifier();
		verifier.setEmail(NEW_EMAIL);
		verifier.setUsername(NEW_USERNAME);
		
		verifierService.save(verifier);
	}
	
	/**
	 * method tests if an certain element can be added into data base
	 * with primary key that already exist,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 */
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddUniqueEmail() {
		Verifier verifier = new Verifier();
		verifier.setEmail(DB_EMAIL);
		verifier.setPassword(NEW_PASSWORD);
		verifier.setUsername(NEW_USERNAME);
		
		verifierService.save(verifier);
	}
	
	/**
	 * method tests if an certain element, that must be unique,
	 * can be added into data base with value that already exist,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 */
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddUniqueUsername() {
		Verifier verifier = new Verifier();
		verifier.setEmail(NEW_EMAIL);
		verifier.setPassword(NEW_PASSWORD);
		verifier.setUsername(DB_USERNAME);
		
		verifierService.save(verifier);
	}
}
