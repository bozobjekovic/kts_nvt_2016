package tim9.Selenium;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tim9.Selenium.login.LoginPage;

public class ProfileAdminPageTest {

	private WebDriver browser;
	MainPage mainPage;
	ProfileAdminPage profileAdminPage;
	ProfileAdminRegisterVerifierPage profileAdminRegisterVerifierPage;
	LoginPage loginPage;
	
	@BeforeMethod
	public void setupSelenium() {
		System.setProperty("webdriver.chrome.driver", "C:/Program Files/chromedriver_win32/chromedriver.exe");
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.navigate().to("http://localhost:8080");

		mainPage = PageFactory.initElements(browser, MainPage.class);
		profileAdminPage = PageFactory.initElements(browser, ProfileAdminPage.class);
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
		
		mainPage.ensureIsDisplayed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profileAdmin", browser.getCurrentUrl());
	}
	
	@Test
	public void testUpdateProfileData() {
		login();
		
		assertTrue(profileAdminPage.getRegisterVerifierButton().isDisplayed());
		profileAdminPage.getRegisterVerifierButton().click();
		
		profileAdminRegisterVerifierPage.ensureIsDisplayed();
		
		assertTrue(profileAdminRegisterVerifierPage.getEmail().isDisplayed());
		assertTrue(profileAdminRegisterVerifierPage.getUsername().isDisplayed());
		assertTrue(profileAdminRegisterVerifierPage.getVerPassword().isDisplayed());

		// Empty data
		profileAdminRegisterVerifierPage.getOkButton().click();
		
		// Email not correctly inputted
		profileAdminRegisterVerifierPage.setEmail("m");
		profileAdminRegisterVerifierPage.setUsername("m");
		profileAdminRegisterVerifierPage.setVerPassword("m");
		profileAdminRegisterVerifierPage.getOkButton().click();

		// Verifier already exists
		profileAdminRegisterVerifierPage.setEmail("verifier2@gmail.com");
		profileAdminRegisterVerifierPage.setUsername("Verifier2");
		profileAdminRegisterVerifierPage.setVerPassword("v");
		profileAdminRegisterVerifierPage.getOkButton().click();
		
		// Successful Verifier registration
		profileAdminRegisterVerifierPage.setEmail("majamiljic2@gmail.com");
		profileAdminRegisterVerifierPage.setUsername("majami");
		profileAdminRegisterVerifierPage.setVerPassword("m");
		profileAdminRegisterVerifierPage.getOkButton().click();
	}

	@Test
	public void testAcceptClerk() {
		login();
		
		profileAdminPage.ensureCanAccept();
		
		int noOfClerkRequests = profileAdminPage.getClerkRequestListSize();
		profileAdminPage.getAcceptButton().click();
		profileAdminPage.ensureIsRemoved(noOfClerkRequests);
	}

	@Test
	public void testRejectClerk() {
		login();
		
		profileAdminPage.ensureCanAccept();
		
		int noOfClerkRequests = profileAdminPage.getClerkRequestListSize();
		profileAdminPage.getRejectButton().click();
		profileAdminPage.ensureIsRemoved(noOfClerkRequests);
	}
	
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}
}
