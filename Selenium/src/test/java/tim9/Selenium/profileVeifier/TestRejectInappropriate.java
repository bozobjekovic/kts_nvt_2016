package tim9.Selenium.profileVeifier;

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
import tim9.Selenium.ProfileClerkPage;
import tim9.Selenium.ProfileVerifierPage;
import tim9.Selenium.configuration.DriverConfiguration;
import tim9.Selenium.login.LoginPage;

public class TestRejectInappropriate {

	private WebDriver browser;
	MainPage mainPage;
	ProfileVerifierPage profileVerifierPage;
	ProfileClerkPage profileClerkPage;
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
		profileVerifierPage = PageFactory.initElements(browser, ProfileVerifierPage.class);
		profileClerkPage = PageFactory.initElements(browser, ProfileClerkPage.class);
		loginPage = PageFactory.initElements(browser, LoginPage.class);
	}
	
	public void login() {
		assertTrue(mainPage.getLogInLink().isDisplayed());
		mainPage.getLogInLink().click();
		
		loginPage.ensureIsDisplayed();
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		assertTrue(loginPage.getOKButton().isDisplayed());
	}
	
	@Test
	public void testRejectAdverRequest() {
		login();
		
		loginPage.setInputUsername("clerk");
		loginPage.setInputPassword("c");
		loginPage.getOKButton().click();
		
		mainPage.ensureLoginIsClosed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profileClerk", browser.getCurrentUrl());
		int noAdvers = profileClerkPage.getAdverListSize();
		mainPage.ensureLoginIsClosed();
		mainPage.getLogOutLink().click();
		
		login();
		
		loginPage.setInputUsername("verifier");
		loginPage.setInputPassword("v");
		loginPage.getOKButton().click();
		
		mainPage.ensureLoginIsClosed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profileVerifier", browser.getCurrentUrl());

		int noOfReportedAdvers = profileVerifierPage.getReportedAdversListSize();
		profileVerifierPage.getRejectButton().click();
		profileVerifierPage.ensureIsRemoved(noOfReportedAdvers);
		mainPage.ensureLoginIsClosed();
		mainPage.getLogOutLink().click();
		
		login();
		
		loginPage.setInputUsername("clerk");
		loginPage.setInputPassword("c");
		loginPage.getOKButton().click();
		
		mainPage.ensureLoginIsClosed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profileClerk", browser.getCurrentUrl());
		assertEquals(profileClerkPage.getAdverListSize(), noAdvers);
		mainPage.ensureLoginIsClosed();
	}
	
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}
}
