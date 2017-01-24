package tim9.Selenium;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tim9.Selenium.configuration.DriverConfiguration;
import tim9.Selenium.login.RegistrationClerkPage;
import tim9.Selenium.login.RegistrationUserPage;

public class RegistratePageTest {
	
	private WebDriver browser;
	private MainPage mainPage;
	private RegistrationUserPage registrationUserPage;
	private RegistrationClerkPage registrationClerkPage;
	
	DriverConfiguration driverConfiguration = new DriverConfiguration();
	
	@BeforeMethod
	public void setupSelenium() {
		System.setProperty("webdriver.chrome.driver", driverConfiguration.getDriverPath());
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.navigate().to("http://localhost:8080");
		
		mainPage = PageFactory.initElements(browser, MainPage.class);
		registrationUserPage = PageFactory.initElements(browser, RegistrationUserPage.class);
		registrationClerkPage = PageFactory.initElements(browser, RegistrationClerkPage.class);
	}
	
	@Test
	public void testRegistrateUser() {
		
		mainPage.getRegistrateLink().click();
		registrationUserPage.ensureIsDisplayed();
		
		assertTrue(registrationUserPage.getOkButton().isDisplayed());
		
		assertTrue(registrationUserPage.getInputName().isDisplayed());
		assertTrue(registrationUserPage.getInputSurname().isDisplayed());
		assertTrue(registrationUserPage.getInputEmail().isDisplayed());
		assertTrue(registrationUserPage.getInputUsername().isDisplayed());
		assertTrue(registrationUserPage.getInputPassword().isDisplayed());
		assertTrue(registrationUserPage.getInputPhoneNumber().isDisplayed());
		assertTrue(registrationUserPage.getInputAddress().isDisplayed());
		assertTrue(registrationUserPage.getInputCity().isDisplayed());
		assertTrue(registrationUserPage.getInputBankAccount().isDisplayed());
		
		// WITH EMPTY INPUT DATA
		
		registrationUserPage.setInputName("");
		registrationUserPage.setInputSurname("");
		registrationUserPage.setInputEmail("");
		registrationUserPage.setInputUsername("");
		registrationUserPage.setInputPassword("");
		registrationUserPage.setInputPhoneNumber("");
		registrationUserPage.setInputAddress("");
		registrationUserPage.setInputCity("");
		registrationUserPage.setInputBankAccount("");
		
		registrationUserPage.getOkButton().click();
		
		registrationUserPage.ensureIsDisplayed();
		assertTrue(registrationUserPage.getInputName().isDisplayed());
		assertTrue(registrationUserPage.getInputSurname().isDisplayed());
		assertTrue(registrationUserPage.getInputEmail().isDisplayed());
		assertTrue(registrationUserPage.getInputUsername().isDisplayed());
		assertTrue(registrationUserPage.getInputPassword().isDisplayed());
		assertTrue(registrationUserPage.getInputPhoneNumber().isDisplayed());
		assertTrue(registrationUserPage.getInputAddress().isDisplayed());
		assertTrue(registrationUserPage.getInputCity().isDisplayed());
		assertTrue(registrationUserPage.getInputBankAccount().isDisplayed());
		
		// WITH WRONG/EXISTING INPUT DATA
		
		registrationUserPage.setInputName("TestName");
		registrationUserPage.setInputSurname("TestSurname");
		registrationUserPage.setInputEmail("user@gmail.com");
		registrationUserPage.setInputUsername("user");
		registrationUserPage.setInputPassword("TestPassword");
		registrationUserPage.setInputPhoneNumber("0000000");
		registrationUserPage.setInputAddress("TestAddress");
		registrationUserPage.setInputCity("Novi Sad");
		registrationUserPage.setInputBankAccount("555555");
		
		registrationUserPage.getOkButton().click();
		
		registrationUserPage.ensureIsDisplayed();
		assertTrue(registrationUserPage.getInputName().isDisplayed());
		assertTrue(registrationUserPage.getInputSurname().isDisplayed());
		assertTrue(registrationUserPage.getInputEmail().isDisplayed());
		assertTrue(registrationUserPage.getInputUsername().isDisplayed());
		assertTrue(registrationUserPage.getInputPassword().isDisplayed());
		assertTrue(registrationUserPage.getInputPhoneNumber().isDisplayed());
		assertTrue(registrationUserPage.getInputAddress().isDisplayed());
		assertTrue(registrationUserPage.getInputCity().isDisplayed());
		assertTrue(registrationUserPage.getInputBankAccount().isDisplayed());
		assertEquals("Username or email already exists!", registrationUserPage.getToastr().getText());
		
		// WITH CORRECT INPUT DATA
		
		registrationUserPage.setInputEmail("testEmail@gmail.com");
		registrationUserPage.setInputUsername("testUser");
		
		registrationUserPage.getOkButton().click();
		
		mainPage.ensureIsDisplayed();
	}
	
	@Test
	public void testRegistrateClerk() {
		
		mainPage.getRegistrateLink().click();
		registrationUserPage.ensureIsDisplayed();
		
		registrationUserPage.getClerkTab().click();
		
		registrationClerkPage.ensureIsDisplayed();
		
		assertTrue(registrationClerkPage.getOkButton().isDisplayed());
		
		assertTrue(registrationClerkPage.getInputName().isDisplayed());
		assertTrue(registrationClerkPage.getInputSurname().isDisplayed());
		assertTrue(registrationClerkPage.getInputEmail().isDisplayed());
		assertTrue(registrationClerkPage.getInputUsername().isDisplayed());
		assertTrue(registrationClerkPage.getInputPassword().isDisplayed());
		assertTrue(registrationClerkPage.getInputPhoneNumber().isDisplayed());
		assertTrue(registrationClerkPage.getInputAddress().isDisplayed());
		assertTrue(registrationClerkPage.getInputCity().isDisplayed());
		assertTrue(registrationClerkPage.getInputBankAccount().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyName().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyPhone().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanySite().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyAddress().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyCity().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyZipCode().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyPartOfTheCity().isDisplayed());
		
		// WITH EMPTY INPUT DATA
		
		registrationClerkPage.setInputName("");
		registrationClerkPage.setInputSurname("");
		registrationClerkPage.setInputEmail("");
		registrationClerkPage.setInputUsername("");
		registrationClerkPage.setInputPassword("");
		registrationClerkPage.setInputPhoneNumber("");
		registrationClerkPage.setInputAddress("");
		registrationClerkPage.setInputCity("");
		registrationClerkPage.setInputBankAccount("");
		registrationClerkPage.setInputCompanyName("");
		registrationClerkPage.setInputCompanyPhone("");
		registrationClerkPage.setInputCompanySite("");
		registrationClerkPage.setInputCompanyAddress("");
		registrationClerkPage.setInputCompanyCity("");
		registrationClerkPage.setInputCompanyZipCode("");
		registrationClerkPage.setInputCompanyPartOfTheCity("");
		
		registrationClerkPage.getOkButton().click();
		
		registrationClerkPage.ensureIsDisplayed();
		
		assertTrue(registrationClerkPage.getOkButton().isDisplayed());
		
		assertTrue(registrationClerkPage.getInputName().isDisplayed());
		assertTrue(registrationClerkPage.getInputSurname().isDisplayed());
		assertTrue(registrationClerkPage.getInputEmail().isDisplayed());
		assertTrue(registrationClerkPage.getInputUsername().isDisplayed());
		assertTrue(registrationClerkPage.getInputPassword().isDisplayed());
		assertTrue(registrationClerkPage.getInputPhoneNumber().isDisplayed());
		assertTrue(registrationClerkPage.getInputAddress().isDisplayed());
		assertTrue(registrationClerkPage.getInputCity().isDisplayed());
		assertTrue(registrationClerkPage.getInputBankAccount().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyName().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyPhone().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanySite().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyAddress().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyCity().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyZipCode().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyPartOfTheCity().isDisplayed());
		
		// WITH WRONG/EXISTING INPUT DATA
		
		registrationClerkPage.setInputName("testName");
		registrationClerkPage.setInputSurname("testSurname");
		registrationClerkPage.setInputEmail("testEmail@gmail.com");
		registrationClerkPage.setInputUsername("testUserName");
		registrationClerkPage.setInputPassword("testPassword");
		registrationClerkPage.setInputPhoneNumber("00000000");
		registrationClerkPage.setInputAddress("testAddress");
		registrationClerkPage.setInputCity("Novi Sad");
		registrationClerkPage.setInputBankAccount("0000000");
		registrationClerkPage.setInputCompanyName("Company");
		registrationClerkPage.setInputCompanyPhone("+38164564");
		registrationClerkPage.setInputCompanySite("testSite");
		registrationClerkPage.setInputCompanyAddress("testAddress");
		registrationClerkPage.setInputCompanyCity("Novi Sad");
		registrationClerkPage.setInputCompanyZipCode("21000");
		registrationClerkPage.setInputCompanyPartOfTheCity("");
		
		registrationClerkPage.getOkButton().click();
		
		registrationClerkPage.ensureIsDisplayed();
		
		assertTrue(registrationClerkPage.getOkButton().isDisplayed());
		
		assertTrue(registrationClerkPage.getInputName().isDisplayed());
		assertTrue(registrationClerkPage.getInputSurname().isDisplayed());
		assertTrue(registrationClerkPage.getInputEmail().isDisplayed());
		assertTrue(registrationClerkPage.getInputUsername().isDisplayed());
		assertTrue(registrationClerkPage.getInputPassword().isDisplayed());
		assertTrue(registrationClerkPage.getInputPhoneNumber().isDisplayed());
		assertTrue(registrationClerkPage.getInputAddress().isDisplayed());
		assertTrue(registrationClerkPage.getInputCity().isDisplayed());
		assertTrue(registrationClerkPage.getInputBankAccount().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyName().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyPhone().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanySite().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyAddress().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyCity().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyZipCode().isDisplayed());
		assertTrue(registrationClerkPage.getInputCompanyPartOfTheCity().isDisplayed());
		assertEquals("Username or email or name of phone number already exists!", registrationUserPage.getToastr().getText());
		
		// WITH CORRECT INPUT DATA
		
		registrationClerkPage.setInputCompanyName("testCompany");
		registrationClerkPage.setInputCompanyPhone("111111111");
		
		registrationClerkPage.getOkButton().click();
		
		mainPage.ensureIsDisplayed();
		
	}
	
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}	

}
