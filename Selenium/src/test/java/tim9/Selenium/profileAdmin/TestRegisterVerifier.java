package tim9.Selenium.profileAdmin;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tim9.Selenium.MainPage;
import tim9.Selenium.ProfileAdminPage;
import tim9.Selenium.ProfileAdminRegisterVerifierPage;
import tim9.Selenium.ProfileClerkPage;
import tim9.Selenium.configuration.DriverConfiguration;
import tim9.Selenium.login.LoginPage;

public class TestRegisterVerifier {

	private WebDriver browser;
	MainPage mainPage;
	ProfileAdminPage profileAdminPage;
	ProfileClerkPage profileClerkPage;
	ProfileAdminRegisterVerifierPage profileAdminRegisterVerifierPage;
	LoginPage loginPage;
	
	DriverConfiguration driverConfiguration = new DriverConfiguration();
	
	@BeforeMethod
	public void setupSelenium() {
		System.setProperty("webdriver.chrome.driver", driverConfiguration.getDriverPath());
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.navigate().to("http://localhost:8080");

		mainPage = PageFactory.initElements(browser, MainPage.class);
		profileAdminPage = PageFactory.initElements(browser, ProfileAdminPage.class);
		profileClerkPage = PageFactory.initElements(browser, ProfileClerkPage.class);
		profileAdminRegisterVerifierPage = PageFactory.initElements(browser, ProfileAdminRegisterVerifierPage.class);
		loginPage = PageFactory.initElements(browser, LoginPage.class);
	}
	
	void login() {
		assertTrue(mainPage.getLogInLink().isDisplayed());
		mainPage.getLogInLink().click();
		
		loginPage.ensureIsDisplayed();
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		assertTrue(loginPage.getOKButton().isDisplayed());
		
		loginPage.setInputUsername("admin");
		loginPage.setInputPassword("a");
		loginPage.getOKButton().click();

		mainPage.ensureLoginIsClosed();
		mainPage.ensureIsDisplayed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profileAdmin", browser.getCurrentUrl());
	}
	
	@Test
	public void testRegisterVerifier() {
		login();

		mainPage.ensureLoginIsClosed();
		assertTrue(profileAdminPage.getRegisterVerifierButton().isDisplayed());
		profileAdminPage.getRegisterVerifierButton().click();
		
		profileAdminRegisterVerifierPage.ensureIsDisplayed();
		assertTrue(profileAdminRegisterVerifierPage.getEmail().isDisplayed());
		assertTrue(profileAdminRegisterVerifierPage.getUsername().isDisplayed());
		assertTrue(profileAdminRegisterVerifierPage.getVerPassword().isDisplayed());

		profileAdminRegisterVerifierPage.getOkButton().click();
		
		profileAdminRegisterVerifierPage.setEmail("m");
		profileAdminRegisterVerifierPage.setUsername("m");
		profileAdminRegisterVerifierPage.setVerPassword("m");
		profileAdminRegisterVerifierPage.getOkButton().click();
		
		profileAdminRegisterVerifierPage.setEmail("verifier2@gmail.com");
		profileAdminRegisterVerifierPage.setUsername("Verifier2");
		profileAdminRegisterVerifierPage.setVerPassword("v");
		profileAdminRegisterVerifierPage.getOkButton().click();
		assertEquals("Username or email already exists!", profileClerkPage.getToastr().getText());

		profileAdminRegisterVerifierPage.setEmail("majamiljic2@gmail.com");
		profileAdminRegisterVerifierPage.setUsername("majami");
		profileAdminRegisterVerifierPage.setVerPassword("m");
		profileAdminRegisterVerifierPage.getOkButton().click();
	}
	
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}
}
