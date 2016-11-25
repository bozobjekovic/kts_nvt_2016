package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static tim9.realEstate.constants.CompanyConstants.*;

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
import tim9.realEstate.model.Company;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class CompanyServiceTest {

	@Autowired
	CompanyService companyService;
	
	/**
	 * <h1> Positive tests </h1>
	 */
	
	/**
	 * <b>testFindOne()</b>
	 * method tests if an certain element from the data base can be found
	 **/
	@Test
	public void testFindOne() {
		Company dbCompany = companyService.findOne(DB_ID);
		assertThat(dbCompany).isNotNull();
		
		assertThat(dbCompany.getId()).isEqualTo(DB_ID);
		assertThat(dbCompany.getName()).isEqualTo(DB_NAME);
		assertThat(dbCompany.getPhoneNumber()).isEqualTo(DB_PHONE_NUMBER);
		assertThat(dbCompany.getSite()).isEqualTo(DB_SITE);
	}
	
	/**
	 * <b>testFindAll()</b>
	 * method test if all of certain elements from the data base can be found
	 **/
	@Test
	public void testFindAll() {
		List<Company> companies = companyService.findAll();
		assertThat(companies).hasSize(DB_COUNT);
	}
	
	/**
	 * <b>testSave()</b>
	 * method tests if a new element can be saved into data base
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		Company company = new Company();
		company.setName(NEW_NAME);
		company.setPhoneNumber(NEW_PHONE_NUMBER);
		company.setSite(NEW_SITE);
		
		int dbSizeBeforeAdd = companyService.findAll().size();
		
		Company dbCompany = companyService.save(company);
		assertThat(dbCompany).isNotNull();
		
		List<Company> companies = companyService.findAll();
		assertThat(companies).hasSize(dbSizeBeforeAdd + 1);
		
		dbCompany = companies.get(companies.size() - 1);
		assertThat(dbCompany.getName()).isEqualTo(NEW_NAME);
		assertThat(dbCompany.getPhoneNumber()).isEqualTo(NEW_PHONE_NUMBER);
		assertThat(dbCompany.getSite()).isEqualTo(NEW_SITE);
	}
	
	/**
	 * <b>testUpdate()</b>
	 * method tests if a certain element from the data base can be updated
	 **/
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdate() {
		Company dbCompany = companyService.findOne(DB_ID);
		
		dbCompany.setName(NEW_NAME);
		dbCompany.setPhoneNumber(NEW_PHONE_NUMBER);
		dbCompany.setSite(NEW_SITE);
		
		dbCompany = companyService.save(dbCompany);
		assertThat(dbCompany).isNotNull();
		
		dbCompany = companyService.findOne(DB_ID);

		assertThat(dbCompany.getName()).isEqualTo(NEW_NAME);
		assertThat(dbCompany.getPhoneNumber()).isEqualTo(NEW_PHONE_NUMBER);
		assertThat(dbCompany.getSite()).isEqualTo(NEW_SITE);
	}
	
	/**
	 * <b>testRemove()</b>
	 * method tests if a certain element from the data base can be removed
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRemove() {
		int dbSizeBeforeRemove = companyService.findAll().size();
		companyService.remove(DB_ID_REFERENCED);
		
		List<Company> companies = companyService.findAll();
		assertThat(companies).hasSize(dbSizeBeforeRemove - 1);
		
		Company dbCompany = companyService.findOne(DB_ID_REFERENCED);
		assertThat(dbCompany).isNull();
	}
	
	/**
	 * <h1> Negative tests </h1>
	 */
	
	/**
	 * <b>testNegativeRemove()</b>
	 * method tests if an certain element from data base,
	 * that should not be removed, can be removed,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testNegativeRemove() {
		int dbSizeBeforeRemove = companyService.findAll().size();
		companyService.remove(DB_ID);
		
		List<Company> companies = companyService.findAll();
		assertThat(companies).hasSize(dbSizeBeforeRemove - 1);
		
		Company dbCompany = companyService.findOne(DB_ID);
		assertThat(dbCompany).isNull();
	}
	
	/**
	 * <b>testAddNullName()</b>
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullName() {
		Company company = new Company();
		
		company.setPhoneNumber(NEW_PHONE_NUMBER);
		company.setSite(NEW_SITE);
		
		companyService.save(company);
	}
	
	/**
	 * <b>testAddUniqueName()</b>
	 * method tests if an certain element, that must be unique,
	 * can be added into data base with value that already exist,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddUniqueName() {
		Company company = new Company();
		
		company.setName(DB_NAME);
		company.setPhoneNumber(NEW_PHONE_NUMBER);
		company.setSite(NEW_SITE);
		
		companyService.save(company);
	}
	
	/**
	 * <b>testAddUniquePhoneNumber()</b>
	 * method tests if an certain element, that must be unique,
	 * can be added into data base with value that already exist,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddUniquePhoneNumber() {
		Company company = new Company();
		
		company.setName(NEW_NAME);
		company.setPhoneNumber(DB_PHONE_NUMBER);
		company.setSite(NEW_SITE);
		
		companyService.save(company);
	}
}
