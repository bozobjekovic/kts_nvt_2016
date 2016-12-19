package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static tim9.realEstate.constants.UserConstants.*;

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
import tim9.realEstate.model.User;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	/**
	 * <h1> Positive tests </h1>
	 */
	
	/**
	 * method tests if an certain element from the data base can be found
	 **/
	@Test 
	public void testFindOne() {
		User dbUser = userService.findOne(DB_ID);
		assertThat(dbUser).isNotNull();
		
		assertThat(dbUser.getId()).isEqualTo(DB_ID);
		assertThat(dbUser.getAddress()).isEqualTo(DB_ADDRESS);
		assertThat(dbUser.getCity()).isEqualTo(DB_CITY);
		assertThat(dbUser.getEmail()).isEqualTo(DB_EMAIL);
		assertThat(dbUser.getImage()).isEqualTo(DB_IMAGE);
		assertThat(dbUser.getName()).isEqualTo(DB_NAME);
		assertThat(dbUser.getPassword()).isEqualTo(DB_PASSWORD_REAL);
        assertThat(dbUser.getPhoneNumber()).isEqualTo(DB_PHONE_NUMBER);
        assertThat(dbUser.getSurname()).isEqualTo(DB_SURNAME);
        assertThat(dbUser.getUsername()).isEqualTo(DB_USERNAME);
        assertThat(dbUser.getNumOfRates()).isEqualTo(DB_NUM_OF_RATES);
        assertThat(dbUser.getRate()).isEqualTo(DB_RATE);
        assertThat(dbUser.getBankAccount()).isEqualTo(DB_BANK_ACCOUNT);
        assertThat(dbUser.isClerk()).isEqualTo(DB_IS_CLERK);
        assertThat(dbUser.isApproved()).isEqualTo(DB_IS_APPROVED);
	}
	
	/**
	 * method test if all of certain elements from the data base can be found
	 **/
	@Test
	public void testFindAll() {
		List<User> users = userService.findAll();
		assertThat(users).hasSize(DB_COUNT);
	}
	
	/**
	 * method tests if a new element can be saved into data base
	 **/
	@Test
    @Transactional
    @Rollback(true)
	public void testSave() {
		User user = new User();
		
		user.setAddress(NEW_ADDRESS);
		user.setCity(NEW_CITY);
		user.setEmail(NEW_EMAIL);
		user.setImage(NEW_IMAGE);
		user.setName(NEW_NAME);
		user.setPassword(NEW_PASSWORD);
		user.setPhoneNumber(NEW_PHONE_NUMBER);
		user.setSurname(NEW_SURNAME);
		user.setUsername(NEW_USERNAME);
		user.setNumOfRates(NEW_NUM_OF_RATES);
		user.setRate(NEW_RATE);
		user.setBankAccount(NEW_BANK_ACCOUNT);
		user.setClerk(NEW_IS_CLERK);
		user.setApproved(NEW_IS_APPROVED);
		
		int dbSizeBeforeAdd = userService.findAll().size();
		
		User dbUser = userService.save(user);
		assertThat(dbUser).isNotNull();

        List<User> users = userService.findAll();
        assertThat(users).hasSize(dbSizeBeforeAdd + 1);
        dbUser = users.get(users.size() - 1);
        assertThat(dbUser.getAddress()).isEqualTo(NEW_ADDRESS);
        assertThat(dbUser.getCity()).isEqualTo(NEW_CITY);
        assertThat(dbUser.getEmail()).isEqualTo(NEW_EMAIL);
        assertThat(dbUser.getImage()).isEqualTo(NEW_IMAGE);
        assertThat(dbUser.getName()).isEqualTo(NEW_NAME);
        assertThat(dbUser.getPassword()).isEqualTo(NEW_PASSWORD);
        assertThat(dbUser.getPhoneNumber()).isEqualTo(NEW_PHONE_NUMBER);
        assertThat(dbUser.getSurname()).isEqualTo(NEW_SURNAME);
        assertThat(dbUser.getUsername()).isEqualTo(NEW_USERNAME);
        assertThat(dbUser.getNumOfRates()).isEqualTo(NEW_NUM_OF_RATES);
        assertThat(dbUser.getRate()).isEqualTo(NEW_RATE);
        assertThat(dbUser.getBankAccount()).isEqualTo(NEW_BANK_ACCOUNT);
        assertThat(dbUser.isClerk()).isEqualTo(NEW_IS_CLERK);
        assertThat(dbUser.isApproved()).isEqualTo(NEW_IS_APPROVED);
	}
	
	/**
	 * method tests if a certain element from the data base can be updated
	 **/
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdate() {
		User dbUser = userService.findOne(DB_ID);

		dbUser.setAddress(NEW_ADDRESS);
		dbUser.setCity(NEW_CITY);
		dbUser.setEmail(NEW_EMAIL);
		dbUser.setImage(NEW_IMAGE);
		dbUser.setName(NEW_NAME);
		dbUser.setPassword(NEW_PASSWORD);
		dbUser.setPhoneNumber(NEW_PHONE_NUMBER);
		dbUser.setSurname(NEW_SURNAME);
		dbUser.setUsername(NEW_USERNAME);
		dbUser.setNumOfRates(NEW_NUM_OF_RATES);
		dbUser.setRate(NEW_RATE);
		dbUser.setBankAccount(NEW_BANK_ACCOUNT);
		dbUser.setClerk(NEW_IS_CLERK);
		dbUser.setApproved(NEW_IS_APPROVED);
		
		dbUser = userService.save(dbUser);
		assertThat(dbUser).isNotNull();
		
		dbUser = userService.findOne(DB_ID);

        assertThat(dbUser.getAddress()).isEqualTo(NEW_ADDRESS);
        assertThat(dbUser.getCity()).isEqualTo(NEW_CITY);
        assertThat(dbUser.getEmail()).isEqualTo(NEW_EMAIL);
        assertThat(dbUser.getImage()).isEqualTo(NEW_IMAGE);
        assertThat(dbUser.getName()).isEqualTo(NEW_NAME);
        assertThat(dbUser.getPassword()).isEqualTo(NEW_PASSWORD);
        assertThat(dbUser.getPhoneNumber()).isEqualTo(NEW_PHONE_NUMBER);
        assertThat(dbUser.getSurname()).isEqualTo(NEW_SURNAME);
        assertThat(dbUser.getUsername()).isEqualTo(NEW_USERNAME);
        assertThat(dbUser.getNumOfRates()).isEqualTo(NEW_NUM_OF_RATES);
        assertThat(dbUser.getRate()).isEqualTo(NEW_RATE);
        assertThat(dbUser.getBankAccount()).isEqualTo(NEW_BANK_ACCOUNT);
        assertThat(dbUser.isClerk()).isEqualTo(NEW_IS_CLERK);
        assertThat(dbUser.isApproved()).isEqualTo(NEW_IS_APPROVED);
	}
	
	/**
	 * method tests if a certain element from the data base can be removed
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRemove() {
		int dbSizeBeforeRemove = userService.findAll().size();
		userService.remove(DB_ID_REAL);
		
		List<User> users = userService.findAll();
		assertThat(users).hasSize(dbSizeBeforeRemove - 1);
		
		User dbUser = userService.findOne(DB_ID_REAL);
		assertThat(dbUser).isNull();
	}
	
	/**
	 * <h1> Negative tests </h1>
	 */
	
	/**
	 * method tests if an certain element from data base,
	 * that should not be removed, can be removed,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testNegativeRemove() {
		int dbSizeBeforeRemove = userService.findAll().size();
		userService.remove(DB_ID);
		
		List<User> users = userService.findAll();
		assertThat(users).hasSize(dbSizeBeforeRemove - 1);
		
		User dbUser = userService.findOne(DB_ID);
		assertThat(dbUser).isNull();
	}
	
	/**
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullEmail() {
		User user = new User();
		
		user.setAddress(NEW_ADDRESS);
		user.setCity(NEW_CITY);
		user.setImage(NEW_IMAGE);
		user.setName(NEW_NAME);
		user.setPassword(NEW_PASSWORD);
		user.setPhoneNumber(NEW_PHONE_NUMBER);
		user.setSurname(NEW_SURNAME);
		user.setUsername(NEW_USERNAME);
		user.setNumOfRates(NEW_NUM_OF_RATES);
		user.setRate(NEW_RATE);
		user.setBankAccount(NEW_BANK_ACCOUNT);
		user.setClerk(NEW_IS_CLERK);
		user.setApproved(NEW_IS_APPROVED);
		
		userService.save(user);
	}
	
	/**
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullUsername() {
		User user = new User();
		
		user.setEmail(NEW_EMAIL);
		user.setAddress(NEW_ADDRESS);
		user.setCity(NEW_CITY);
		user.setImage(NEW_IMAGE);
		user.setName(NEW_NAME);
		user.setPassword(NEW_PASSWORD);
		user.setPhoneNumber(NEW_PHONE_NUMBER);
		user.setSurname(NEW_SURNAME);
		user.setNumOfRates(NEW_NUM_OF_RATES);
		user.setRate(NEW_RATE);
		user.setBankAccount(NEW_BANK_ACCOUNT);
		user.setClerk(NEW_IS_CLERK);
		user.setApproved(NEW_IS_APPROVED);
		
		userService.save(user);
	}
	
	/**
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullPassword() {
		User user = new User();
		
		user.setEmail(NEW_EMAIL);
		user.setAddress(NEW_ADDRESS);
		user.setCity(NEW_CITY);
		user.setImage(NEW_IMAGE);
		user.setName(NEW_NAME);
		user.setPhoneNumber(NEW_PHONE_NUMBER);
		user.setSurname(NEW_SURNAME);
		user.setUsername(NEW_USERNAME);
		user.setNumOfRates(NEW_NUM_OF_RATES);
		user.setRate(NEW_RATE);
		user.setBankAccount(NEW_BANK_ACCOUNT);
		user.setClerk(NEW_IS_CLERK);
		user.setApproved(NEW_IS_APPROVED);
		
		userService.save(user);
	}
	
	/**
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullName() {
		User user = new User();
		
		user.setEmail(NEW_EMAIL);
		user.setAddress(NEW_ADDRESS);
		user.setCity(NEW_CITY);
		user.setImage(NEW_IMAGE);
		user.setSurname(NEW_SURNAME);
		user.setPhoneNumber(NEW_PHONE_NUMBER);
		user.setPassword(NEW_PASSWORD);
		user.setUsername(NEW_USERNAME);
		user.setNumOfRates(NEW_NUM_OF_RATES);
		user.setRate(NEW_RATE);
		user.setBankAccount(NEW_BANK_ACCOUNT);
		user.setClerk(NEW_IS_CLERK);
		user.setApproved(NEW_IS_APPROVED);
		
		
		userService.save(user);
	}
	
	/**
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullSurname() {
		User user = new User();
		
		user.setEmail(NEW_EMAIL);
		user.setAddress(NEW_ADDRESS);
		user.setCity(NEW_CITY);
		user.setImage(NEW_IMAGE);
		user.setName(NEW_NAME);
		user.setPhoneNumber(NEW_PHONE_NUMBER);
		user.setPassword(NEW_PASSWORD);
		user.setUsername(NEW_USERNAME);
		user.setNumOfRates(NEW_NUM_OF_RATES);
		user.setRate(NEW_RATE);
		user.setBankAccount(NEW_BANK_ACCOUNT);
		user.setClerk(NEW_IS_CLERK);
		user.setApproved(NEW_IS_APPROVED);
		
		userService.save(user);
	}
	
	/**
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddUniqueEmail() {
		User user = new User();
		
		user.setEmail(DB_EMAIL);
		user.setAddress(NEW_ADDRESS);
		user.setCity(NEW_CITY);
		user.setImage(NEW_IMAGE);
		user.setName(NEW_NAME);
		user.setPhoneNumber(NEW_PHONE_NUMBER);
		user.setPassword(NEW_PASSWORD);
		user.setUsername(NEW_USERNAME);
		user.setSurname(NEW_SURNAME);
		user.setNumOfRates(NEW_NUM_OF_RATES);
		user.setRate(NEW_RATE);
		user.setBankAccount(NEW_BANK_ACCOUNT);
		user.setClerk(NEW_IS_CLERK);
		user.setApproved(NEW_IS_APPROVED);
		
		userService.save(user);
	}
	
	/**
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddUniqueUsername() {
		User user = new User();
		
		user.setEmail(NEW_EMAIL);
		user.setAddress(NEW_ADDRESS);
		user.setCity(NEW_CITY);
		user.setImage(NEW_IMAGE);
		user.setName(NEW_NAME);
		user.setPhoneNumber(NEW_PHONE_NUMBER);
		user.setPassword(NEW_PASSWORD);
		user.setUsername(DB_USERNAME);
		user.setSurname(NEW_SURNAME);
		user.setNumOfRates(NEW_NUM_OF_RATES);
		user.setRate(NEW_RATE);
		user.setBankAccount(NEW_BANK_ACCOUNT);
		user.setClerk(NEW_IS_CLERK);
		user.setApproved(NEW_IS_APPROVED);
		
		userService.save(user);
	}
	
	/**
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddUniqueBankAccount() {
		User user = new User();
		
		user.setEmail(NEW_EMAIL);
		user.setAddress(NEW_ADDRESS);
		user.setCity(NEW_CITY);
		user.setImage(NEW_IMAGE);
		user.setName(NEW_NAME);
		user.setPhoneNumber(NEW_PHONE_NUMBER);
		user.setPassword(NEW_PASSWORD);
		user.setUsername(NEW_USERNAME);
		user.setSurname(NEW_SURNAME);
		user.setNumOfRates(NEW_NUM_OF_RATES);
		user.setRate(NEW_RATE);
		user.setBankAccount(DB_BANK_ACCOUNT);
		user.setClerk(NEW_IS_CLERK);
		user.setApproved(NEW_IS_APPROVED);
		
		userService.save(user);
	}
}
