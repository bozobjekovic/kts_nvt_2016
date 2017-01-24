package tim9.Selenium.profileClerk;

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
import tim9.Selenium.ProfileUserPage;
import tim9.Selenium.configuration.DriverConfiguration;
import tim9.Selenium.login.LoginPage;

public class TestRejectUserRequest {

	private WebDriver browser;
	MainPage mainPage;
	ProfileClerkPage profileClerkPage;
	ProfileUserPage profileUserPage;
	LoginPage loginPage;
	
	DriverConfiguration driverConfiguration = new DriverConfiguration();
	
	/**
	 * This method sets up selenium
	 */
	@BeforeMethod
	public void setupSelenium() {
		System.setProperty("webdriver.chrome.driver", driverConfiguration.getDriverPath());
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.navigate().to("http://localhost:8080");

		mainPage = PageFactory.initElements(browser, MainPage.class);
		profileClerkPage = PageFactory.initElements(browser, ProfileClerkPage.class);
		profileUserPage = PageFactory.initElements(browser, ProfileUserPage.class);
		loginPage = PageFactory.initElements(browser, LoginPage.class);
	}
	
	/**
	 * This method represents login
	 */
	public void login() {
		assertTrue(mainPage.getLogInLink().isDisplayed());
		mainPage.getLogInLink().click();
		
		loginPage.ensureIsDisplayed();
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		assertTrue(loginPage.getOKButton().isDisplayed());
	}
	
	/**
	 * This method test rejecting user to join company
	 */
	@Test
	public void testRejectUserRequest() {
		login();
		loginPage.setInputUsername("user3");
		loginPage.setInputPassword("u");
		loginPage.getOKButton().click();
		
		mainPage.ensureLoginIsClosed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profile", browser.getCurrentUrl());
		int askToJoinListSize = profileUserPage.getAskToJoinListSize();
		mainPage.ensureLoginIsClosed();
		mainPage.getLogOutLink().click();
		
		login();
		loginPage.setInputUsername("clerk2");
		loginPage.setInputPassword("c");
		loginPage.getOKButton().click();

		mainPage.ensureLoginIsClosed();
		mainPage.ensureIsDisplayed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profileClerk", browser.getCurrentUrl());
		
		profileClerkPage.ensureCanAccept();

		int noOfUserRequests = profileClerkPage.getUserRequestsListSize();
		
		profileClerkPage.getRejectButton().click();
		profileClerkPage.ensureIsRejected(noOfUserRequests);

		mainPage.ensureLoginIsClosed();
		mainPage.getLogOutLink().click();
		
		login();
		loginPage.setInputUsername("user3");
		loginPage.setInputPassword("u");
		loginPage.getOKButton().click();
		
		mainPage.ensureLoginIsClosed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profile", browser.getCurrentUrl());
		assertEquals(profileUserPage.getAskToJoinListSize(), askToJoinListSize);
		mainPage.ensureLoginIsClosed();		
	}
	
	/**
	 * This method closes browser after test
	 */
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}
}
