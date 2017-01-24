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

import tim9.Selenium.configuration.DriverConfiguration;
import tim9.Selenium.login.LoginPage;

public class ProfileUserPageTest {

	private WebDriver browser;
	MainPage mainPage;
	ProfileUserPage profileUserPage;
	LoginPage loginPage;
	ProfileClerkPage profileClerkPage;
	
	DriverConfiguration driverConfiguration = new DriverConfiguration();
	
	@BeforeMethod
	public void setupSelenium() {
		System.setProperty("webdriver.chrome.driver", driverConfiguration.getDriverPath());
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.navigate().to("http://localhost:8080");

		mainPage = PageFactory.initElements(browser, MainPage.class);
		profileUserPage = PageFactory.initElements(browser, ProfileUserPage.class);
		loginPage = PageFactory.initElements(browser, LoginPage.class);
		profileClerkPage = PageFactory.initElements(browser, ProfileClerkPage.class);
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
	public void testAskToJoin() {
		login();
		loginPage.setInputUsername("clerk");
		loginPage.setInputPassword("c");
		loginPage.getOKButton().click();
		
		mainPage.ensureLoginIsClosed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profileClerk", browser.getCurrentUrl());
		
		int noUserRequests = profileClerkPage.getUserRequestsListSize();
		
		mainPage.ensureLoginIsClosed();
		mainPage.getLogOutLink().click();
		
		login();
		loginPage.setInputUsername("user");
		loginPage.setInputPassword("u");
		loginPage.getOKButton().click();

		mainPage.ensureLoginIsClosed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profile", browser.getCurrentUrl());
		
		mainPage.ensureLoginIsClosed();
		profileUserPage.ensureCanAskToJoin();
		profileUserPage.getAskToJoinButton().click();
		
		mainPage.getLogOutLink().click();
		
		login();
		loginPage.setInputUsername("clerk");
		loginPage.setInputPassword("c");
		loginPage.getOKButton().click();

		mainPage.ensureLoginIsClosed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profileClerk", browser.getCurrentUrl());
		
		assertEquals(profileClerkPage.getUserRequestsListSize(), noUserRequests+1);
		profileClerkPage.ensureCanAccept();
		profileClerkPage.getAcceptButton().click();
		mainPage.ensureLoginIsClosed();
		mainPage.getLogOutLink().click();
		
		login();
		loginPage.setInputUsername("user");
		loginPage.setInputPassword("u");
		loginPage.getOKButton().click();

		mainPage.ensureLoginIsClosed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profile", browser.getCurrentUrl());
		mainPage.ensureLoginIsClosed();
		
		assertEquals(profileUserPage.getUsersCompanyName(), "Kompanija Neka");
		mainPage.ensureLoginIsClosed();
	}
	
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}
}
