package tim9.realEstate.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tim9.realEstate.RealEstateApplication;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class VerifierServiceTest {
	
/*	@Autowired
	VerifierService verifierService;

	*//**
	 * <h1> Positive tests </h1>
	 *//*
	
	*//**
	 * <b>testFindOne()</b>
	 * method tests if an certain element from the data base can be found
	 **//*
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
	
	*//**
	 * <b>testFindAll()</b>
	 * method test if all of certain elements from the data base can be found
	 **//*
	@Test
	public void testFindAll() {
		List<Verifier> verifiers = verifierService.findAll();
		assertThat(verifiers).hasSize(DB_COUNT);
	}
	
	*//**
	 * <b>testSave()</b>
	 * method tests if a new element can be saved into data base
	 **//*
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
	
	*//**
	 * <b>testUpdate()</b>
	 * method tests if a certain element from the data base can be updated
	 **//*
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
	
	*//**
	 * <b>testRemove()</b>
	 * method tests if a certain element from the data base can be removed
	 **//*
	@Test
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
	
	*//**
	 * <h1> Negative tests </h1>
	 *//*
	
	*//**
	 * <b>testNegativeRemove()</b>
	 * method tests if an certain element from data base,
	 * that should not be removed, can be removed,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **//*
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
	
	*//**
	 * <b>testAddNullEmail()</b>
	 * method tests if an certain element can be added into data base
	 * without its primary key,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **//*
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullEmail() {
		Verifier verifier = new Verifier();
		
		verifier.setAddress(NEW_ADDRESS);
		verifier.setCity(NEW_CITY);
		verifier.setImage(NEW_IMAGE);
		verifier.setName(NEW_NAME);
		verifier.setPassword(NEW_PASSWORD);
		verifier.setPhoneNumber(NEW_PHONE_NUMBER);
		verifier.setSurname(NEW_SURNAME);
		verifier.setUsername(NEW_USERNAME);
		
		verifierService.save(verifier);
	}
	
	*//**
	 * <b>testAddNullUsername()</b>
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **//*
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullUsername() {
		Verifier verifier = new Verifier();
		
		verifier.setEmail(NEW_EMAIL);
		verifier.setAddress(NEW_ADDRESS);
		verifier.setCity(NEW_CITY);
		verifier.setImage(NEW_IMAGE);
		verifier.setName(NEW_NAME);
		verifier.setPassword(NEW_PASSWORD);
		verifier.setPhoneNumber(NEW_PHONE_NUMBER);
		verifier.setSurname(NEW_SURNAME);
		
		verifierService.save(verifier);
	}
	
	*//**
	 * <b>testAddNullPassword()</b>
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **//*
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullPassword() {
		Verifier verifier = new Verifier();
		
		verifier.setEmail(NEW_EMAIL);
		verifier.setAddress(NEW_ADDRESS);
		verifier.setCity(NEW_CITY);
		verifier.setImage(NEW_IMAGE);
		verifier.setName(NEW_NAME);
		verifier.setPhoneNumber(NEW_PHONE_NUMBER);
		verifier.setSurname(NEW_SURNAME);
		verifier.setUsername(NEW_USERNAME);
		
		verifierService.save(verifier);
	}
	
	*//**
	 * <b>testAddNullName()</b>
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **//*
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullName() {
		Verifier verifier = new Verifier();
		
		verifier.setEmail(NEW_EMAIL);
		verifier.setAddress(NEW_ADDRESS);
		verifier.setCity(NEW_CITY);
		verifier.setImage(NEW_IMAGE);
		verifier.setSurname(NEW_SURNAME);
		verifier.setPhoneNumber(NEW_PHONE_NUMBER);
		verifier.setPassword(NEW_PASSWORD);
		verifier.setUsername(NEW_USERNAME);
		
		verifierService.save(verifier);
	}
	
	*//**
	 * <b>testAddNullSurname()</b>
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **//*
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullSurname() {
		Verifier verifier = new Verifier();
		
		verifier.setEmail(NEW_EMAIL);
		verifier.setAddress(NEW_ADDRESS);
		verifier.setCity(NEW_CITY);
		verifier.setImage(NEW_IMAGE);
		verifier.setName(NEW_NAME);
		verifier.setPhoneNumber(NEW_PHONE_NUMBER);
		verifier.setPassword(NEW_PASSWORD);
		verifier.setUsername(NEW_USERNAME);
		
		verifierService.save(verifier);
	}
	
	*//**
	 * <b>testAddUniqueEmail()</b>
	 * method tests if an certain element can be added into data base
	 * with primary key that already exist,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **//*
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddUniqueEmail() {
		Verifier verifier = new Verifier();
		
		verifier.setEmail(DB_EMAIL);
		verifier.setAddress(NEW_ADDRESS);
		verifier.setCity(NEW_CITY);
		verifier.setImage(NEW_IMAGE);
		verifier.setName(NEW_NAME);
		verifier.setPhoneNumber(NEW_PHONE_NUMBER);
		verifier.setPassword(NEW_PASSWORD);
		verifier.setUsername(NEW_USERNAME);
		verifier.setSurname(NEW_SURNAME);
		
		verifierService.save(verifier);
	}
	
	*//**
	 * <b>testAddUniqueUsername()</b>
	 * method tests if an certain element, that must be unique,
	 * can be added into data base with value that already exist,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **//*
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddUniqueUsername() {
		Verifier verifier = new Verifier();
		
		verifier.setEmail(NEW_EMAIL);
		verifier.setAddress(NEW_ADDRESS);
		verifier.setCity(NEW_CITY);
		verifier.setImage(NEW_IMAGE);
		verifier.setName(NEW_NAME);
		verifier.setPhoneNumber(NEW_PHONE_NUMBER);
		verifier.setPassword(NEW_PASSWORD);
		verifier.setUsername(DB_USERNAME);
		verifier.setSurname(NEW_SURNAME);
		
		verifierService.save(verifier);
	}*/
}
