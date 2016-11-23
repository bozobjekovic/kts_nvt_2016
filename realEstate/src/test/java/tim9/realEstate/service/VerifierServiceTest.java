package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static tim9.realEstate.constants.VerifierConstants.*;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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

	/*****   1. TEST FIND ONE   *****/
	@Test 
	public void testFindOne() {
		Verifier dbVerifier = verifierService.findOne(DB_ID);
		assertThat(dbVerifier).isNotNull();
		
		assertThat(dbVerifier.getId()).isEqualTo(DB_ID);
		assertThat(dbVerifier.getAddress()).isEqualTo(DB_ADDRESS);
		assertThat(dbVerifier.getCity()).isEqualTo(DB_CITY);
		assertThat(dbVerifier.getEmail()).isEqualTo(DB_EMAIL);
		assertThat(dbVerifier.getImage()).isEqualTo(DB_IMAGE);
		assertThat(dbVerifier.getName()).isEqualTo(DB_NAME);
		assertThat(dbVerifier.getPassword()).isEqualTo(DB_PASSWORD);
        assertThat(dbVerifier.getPhoneNumber()).isEqualTo(DB_PHONE_NUMBER);
        assertThat(dbVerifier.getSurname()).isEqualTo(DB_SURNAME);
        assertThat(dbVerifier.getUsername()).isEqualTo(DB_USERNAME);
	}
	
	/*****   2. TEST FIND ALL   *****/
	@Test
	public void testFindAll() {
		List<Verifier> verifiers = verifierService.findAll();
		assertThat(verifiers).hasSize(DB_COUNT);
	}
	
	/*****   3. TEST SAVE   *****/
	@Test
    @Transactional
    @Rollback(true)
	public void testSave() {
		Verifier verifier = new Verifier();
		
		verifier.setAddress(NEW_ADDRESS);
		verifier.setCity(NEW_CITY);
		verifier.setEmail(NEW_EMAIL);
		verifier.setImage(NEW_IMAGE);
		verifier.setName(NEW_NAME);
		verifier.setPassword(NEW_PASSWORD);
		verifier.setPhoneNumber(NEW_PHONE_NUMBER);
		verifier.setSurname(NEW_SURNAME);
		verifier.setUsername(NEW_USERNAME);
		
		int dbSizeBeforeAdd = verifierService.findAll().size();
		
		Verifier dbVerifier = verifierService.save(verifier);
		assertThat(dbVerifier).isNotNull();

        List<Verifier> verifiers = verifierService.findAll();
        assertThat(verifiers).hasSize(dbSizeBeforeAdd + 1);
        dbVerifier = verifiers.get(verifiers.size() - 1);
        assertThat(dbVerifier.getAddress()).isEqualTo(NEW_ADDRESS);
        assertThat(dbVerifier.getCity()).isEqualTo(NEW_CITY);
        assertThat(dbVerifier.getEmail()).isEqualTo(NEW_EMAIL);
        assertThat(dbVerifier.getImage()).isEqualTo(NEW_IMAGE);
        assertThat(dbVerifier.getName()).isEqualTo(NEW_NAME);
        assertThat(dbVerifier.getPassword()).isEqualTo(NEW_PASSWORD);
        assertThat(dbVerifier.getPhoneNumber()).isEqualTo(NEW_PHONE_NUMBER);
        assertThat(dbVerifier.getSurname()).isEqualTo(NEW_SURNAME);
        assertThat(dbVerifier.getUsername()).isEqualTo(NEW_USERNAME);
	}
	
	/*****   4. TEST UPDATE   *****/
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdate() {
		Verifier dbVerifier = verifierService.findOne(DB_ID);

		dbVerifier.setAddress(NEW_ADDRESS);
		dbVerifier.setCity(NEW_CITY);
		dbVerifier.setEmail(NEW_EMAIL);
		dbVerifier.setImage(NEW_IMAGE);
		dbVerifier.setName(NEW_NAME);
		dbVerifier.setPassword(NEW_PASSWORD);
		dbVerifier.setPhoneNumber(NEW_PHONE_NUMBER);
		dbVerifier.setSurname(NEW_SURNAME);
		dbVerifier.setUsername(NEW_USERNAME);
		
		dbVerifier = verifierService.save(dbVerifier);
		assertThat(dbVerifier).isNotNull();
		
		dbVerifier = verifierService.findOne(DB_ID);

        assertThat(dbVerifier.getAddress()).isEqualTo(NEW_ADDRESS);
        assertThat(dbVerifier.getCity()).isEqualTo(NEW_CITY);
        assertThat(dbVerifier.getEmail()).isEqualTo(NEW_EMAIL);
        assertThat(dbVerifier.getImage()).isEqualTo(NEW_IMAGE);
        assertThat(dbVerifier.getName()).isEqualTo(NEW_NAME);
        assertThat(dbVerifier.getPassword()).isEqualTo(NEW_PASSWORD);
        assertThat(dbVerifier.getPhoneNumber()).isEqualTo(NEW_PHONE_NUMBER);
        assertThat(dbVerifier.getSurname()).isEqualTo(NEW_SURNAME);
        assertThat(dbVerifier.getUsername()).isEqualTo(NEW_USERNAME);
	}
	
	/*****   5. TEST REMOVE   *****/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testRemove() {
		int dbSizeBeforeRemove = verifierService.findAll().size();
		verifierService.remove(DB_ID_REFERENCED);
		
		List<Verifier> verifiers = verifierService.findAll();
		assertThat(verifiers).hasSize(dbSizeBeforeRemove - 1);
		
		Verifier dbVerifier = verifierService.findOne(DB_ID_REFERENCED);
		assertThat(dbVerifier).isNull();
	}
}
