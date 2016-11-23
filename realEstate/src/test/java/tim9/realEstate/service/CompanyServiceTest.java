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
	
	/*****   1. TEST FIND ONE   *****/
	@Test
	public void testFindOne() {
		Company dbCompany = companyService.findOne(DB_ID);
		assertThat(dbCompany).isNotNull();
		
		assertThat(dbCompany.getId()).isEqualTo(DB_ID);
		assertThat(dbCompany.getName()).isEqualTo(DB_NAME);
		assertThat(dbCompany.getPhoneNumber()).isEqualTo(DB_PHONE_NUMBER);
		assertThat(dbCompany.getSite()).isEqualTo(DB_SITE);
	}
	
	/*****   2. TEST FIND ALL   *****/
	@Test
	public void testFindAll() {
		List<Company> companies = companyService.findAll();
		assertThat(companies).hasSize(DB_COUNT);
	}
	
	/*****   3. TEST SAVE   *****/
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
	
	/*****   4. TEST UPDATE   *****/
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
	
	/*****   5. TEST REMOVE   *****/
	@Test(expected = DataIntegrityViolationException.class)
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
}
