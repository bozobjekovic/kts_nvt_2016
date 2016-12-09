package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static tim9.realEstate.constants.AdminConstants.DB_ADDRESS;
import static tim9.realEstate.constants.AdminConstants.DB_CITY;
import static tim9.realEstate.constants.AdminConstants.DB_COUNT;
import static tim9.realEstate.constants.AdminConstants.DB_EMAIL;
import static tim9.realEstate.constants.AdminConstants.DB_ID;
import static tim9.realEstate.constants.AdminConstants.DB_IMAGE;
import static tim9.realEstate.constants.AdminConstants.DB_NAME;
import static tim9.realEstate.constants.AdminConstants.DB_PASSWORD;
import static tim9.realEstate.constants.AdminConstants.DB_PHONE_NUMBER;
import static tim9.realEstate.constants.AdminConstants.DB_SURNAME;
import static tim9.realEstate.constants.AdminConstants.DB_USERNAME;
import static tim9.realEstate.constants.AdminConstants.NEW_ADDRESS;
import static tim9.realEstate.constants.AdminConstants.NEW_CITY;
import static tim9.realEstate.constants.AdminConstants.NEW_EMAIL;
import static tim9.realEstate.constants.AdminConstants.NEW_IMAGE;
import static tim9.realEstate.constants.AdminConstants.NEW_NAME;
import static tim9.realEstate.constants.AdminConstants.NEW_PASSWORD;
import static tim9.realEstate.constants.AdminConstants.NEW_PHONE_NUMBER;
import static tim9.realEstate.constants.AdminConstants.NEW_SURNAME;
import static tim9.realEstate.constants.AdminConstants.NEW_USERNAME;

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
import tim9.realEstate.model.Admin;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class AdminServiceTest {
	
	@Autowired
	AdminService adminService;

	@Test 
	public void testFindOne() {
		Admin dbAdmin = adminService.findOne(DB_ID);
		assertThat(dbAdmin).isNotNull();
		
		assertThat(dbAdmin.getId()).isEqualTo(DB_ID);
		assertThat(dbAdmin.getAddress()).isEqualTo(DB_ADDRESS);
		assertThat(dbAdmin.getCity()).isEqualTo(DB_CITY);
		assertThat(dbAdmin.getEmail()).isEqualTo(DB_EMAIL);
		assertThat(dbAdmin.getImage()).isEqualTo(DB_IMAGE);
		assertThat(dbAdmin.getName()).isEqualTo(DB_NAME);
		assertThat(dbAdmin.getPassword()).isEqualTo(DB_PASSWORD);
        assertThat(dbAdmin.getPhoneNumber()).isEqualTo(DB_PHONE_NUMBER);
        assertThat(dbAdmin.getSurname()).isEqualTo(DB_SURNAME);
        assertThat(dbAdmin.getUsername()).isEqualTo(DB_USERNAME);
	}
	
	@Test
	public void testFindAll() {
		List<Admin> admins = adminService.findAll();
		assertThat(admins).hasSize(DB_COUNT);
	}

	@Test
    @Transactional
    @Rollback(true)
	public void testSave() {
		Admin admin = new Admin();
		
		admin.setAddress(NEW_ADDRESS);
		admin.setCity(NEW_CITY);
		admin.setEmail(NEW_EMAIL);
		admin.setImage(NEW_IMAGE);
		admin.setName(NEW_NAME);
		admin.setPassword(NEW_PASSWORD);
		admin.setPhoneNumber(NEW_PHONE_NUMBER);
		admin.setSurname(NEW_SURNAME);
		admin.setUsername(NEW_USERNAME);
		
		int dbSizeBeforeAdd = adminService.findAll().size();
		
		Admin dbAdmin = adminService.save(admin);
		assertThat(dbAdmin).isNotNull();

        List<Admin> admins = adminService.findAll();
        assertThat(admins).hasSize(dbSizeBeforeAdd + 1);
        dbAdmin = admins.get(admins.size() - 1);
        assertThat(dbAdmin.getAddress()).isEqualTo(NEW_ADDRESS);
        assertThat(dbAdmin.getCity()).isEqualTo(NEW_CITY);
        assertThat(dbAdmin.getEmail()).isEqualTo(NEW_EMAIL);
        assertThat(dbAdmin.getImage()).isEqualTo(NEW_IMAGE);
        assertThat(dbAdmin.getName()).isEqualTo(NEW_NAME);
        assertThat(dbAdmin.getPassword()).isEqualTo(NEW_PASSWORD);
        assertThat(dbAdmin.getPhoneNumber()).isEqualTo(NEW_PHONE_NUMBER);
        assertThat(dbAdmin.getSurname()).isEqualTo(NEW_SURNAME);
        assertThat(dbAdmin.getUsername()).isEqualTo(NEW_USERNAME);
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdate() {
		Admin dbAdmin = adminService.findOne(DB_ID);

		dbAdmin.setAddress(NEW_ADDRESS);
		dbAdmin.setCity(NEW_CITY);
		dbAdmin.setEmail(NEW_EMAIL);
		dbAdmin.setImage(NEW_IMAGE);
		dbAdmin.setName(NEW_NAME);
		dbAdmin.setPassword(NEW_PASSWORD);
		dbAdmin.setPhoneNumber(NEW_PHONE_NUMBER);
		dbAdmin.setSurname(NEW_SURNAME);
		dbAdmin.setUsername(NEW_USERNAME);
		
		dbAdmin = adminService.save(dbAdmin);
		assertThat(dbAdmin).isNotNull();
		
		dbAdmin = adminService.findOne(DB_ID);

        assertThat(dbAdmin.getAddress()).isEqualTo(NEW_ADDRESS);
        assertThat(dbAdmin.getCity()).isEqualTo(NEW_CITY);
        assertThat(dbAdmin.getEmail()).isEqualTo(NEW_EMAIL);
        assertThat(dbAdmin.getImage()).isEqualTo(NEW_IMAGE);
        assertThat(dbAdmin.getName()).isEqualTo(NEW_NAME);
        assertThat(dbAdmin.getPassword()).isEqualTo(NEW_PASSWORD);
        assertThat(dbAdmin.getPhoneNumber()).isEqualTo(NEW_PHONE_NUMBER);
        assertThat(dbAdmin.getSurname()).isEqualTo(NEW_SURNAME);
        assertThat(dbAdmin.getUsername()).isEqualTo(NEW_USERNAME);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testRemove() {
		int dbSizeBeforeRemove = adminService.findAll().size();
		adminService.remove(DB_ID);
		
		List<Admin> admins = adminService.findAll();
		assertThat(admins).hasSize(dbSizeBeforeRemove - 1);
		
		Admin dbAdmin = adminService.findOne(DB_ID);
		assertThat(dbAdmin).isNull();
	}
	
	/*
	 * Negative tests
	 */
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullEmail() {
		Admin admin = new Admin();
		
		admin.setAddress(NEW_ADDRESS);
		admin.setCity(NEW_CITY);
		admin.setImage(NEW_IMAGE);
		admin.setName(NEW_NAME);
		admin.setPassword(NEW_PASSWORD);
		admin.setPhoneNumber(NEW_PHONE_NUMBER);
		admin.setSurname(NEW_SURNAME);
		admin.setUsername(NEW_USERNAME);
		
		adminService.save(admin);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullUsername() {
		Admin admin = new Admin();
		
		admin.setEmail(NEW_EMAIL);
		admin.setAddress(NEW_ADDRESS);
		admin.setCity(NEW_CITY);
		admin.setImage(NEW_IMAGE);
		admin.setName(NEW_NAME);
		admin.setPassword(NEW_PASSWORD);
		admin.setPhoneNumber(NEW_PHONE_NUMBER);
		admin.setSurname(NEW_SURNAME);
		
		adminService.save(admin);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullPassword() {
		Admin admin = new Admin();
		
		admin.setEmail(NEW_EMAIL);
		admin.setAddress(NEW_ADDRESS);
		admin.setCity(NEW_CITY);
		admin.setImage(NEW_IMAGE);
		admin.setName(NEW_NAME);
		admin.setPhoneNumber(NEW_PHONE_NUMBER);
		admin.setSurname(NEW_SURNAME);
		admin.setUsername(NEW_USERNAME);
		
		adminService.save(admin);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullName() {
		Admin admin = new Admin();
		
		admin.setEmail(NEW_EMAIL);
		admin.setAddress(NEW_ADDRESS);
		admin.setCity(NEW_CITY);
		admin.setImage(NEW_IMAGE);
		admin.setSurname(NEW_SURNAME);
		admin.setPhoneNumber(NEW_PHONE_NUMBER);
		admin.setPassword(NEW_PASSWORD);
		admin.setUsername(NEW_USERNAME);
		
		adminService.save(admin);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullSurname() {
		Admin admin = new Admin();
		
		admin.setEmail(NEW_EMAIL);
		admin.setAddress(NEW_ADDRESS);
		admin.setCity(NEW_CITY);
		admin.setImage(NEW_IMAGE);
		admin.setName(NEW_NAME);
		admin.setPhoneNumber(NEW_PHONE_NUMBER);
		admin.setPassword(NEW_PASSWORD);
		admin.setUsername(NEW_USERNAME);
		
		adminService.save(admin);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddUniqueEmail() {
		Admin admin = new Admin();
		
		admin.setEmail(DB_EMAIL);
		admin.setAddress(NEW_ADDRESS);
		admin.setCity(NEW_CITY);
		admin.setImage(NEW_IMAGE);
		admin.setName(NEW_NAME);
		admin.setPhoneNumber(NEW_PHONE_NUMBER);
		admin.setPassword(NEW_PASSWORD);
		admin.setUsername(NEW_USERNAME);
		admin.setSurname(NEW_SURNAME);
		
		adminService.save(admin);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddUniqueUsername() {
		Admin admin = new Admin();
		
		admin.setEmail(NEW_EMAIL);
		admin.setAddress(NEW_ADDRESS);
		admin.setCity(NEW_CITY);
		admin.setImage(NEW_IMAGE);
		admin.setName(NEW_NAME);
		admin.setPhoneNumber(NEW_PHONE_NUMBER);
		admin.setPassword(NEW_PASSWORD);
		admin.setUsername(DB_USERNAME);
		admin.setSurname(NEW_SURNAME);
		
		adminService.save(admin);
	}

}