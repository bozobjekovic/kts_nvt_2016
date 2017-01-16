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
import tim9.Selenium.update.UpdateProfileDataPage;

public class ProfileClerkPageTest {

	private WebDriver browser;
	MainPage mainPage;
	ProfileClerkPage profileClerkPage;
	UpdateProfileDataPage profileClerkUpdateDataPage;
	LoginPage loginPage;
	
	@BeforeMethod
	public void setupSelenium() {
		System.setProperty("webdriver.chrome.driver", "C:/Program Files/chromedriver_win32/chromedriver.exe");
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.navigate().to("http://localhost:8080");

		mainPage = PageFactory.initElements(browser, MainPage.class);
		profileClerkPage = PageFactory.initElements(browser, ProfileClerkPage.class);
		profileClerkUpdateDataPage = PageFactory.initElements(browser, UpdateProfileDataPage.class);
		loginPage = PageFactory.initElements(browser, LoginPage.class);
	}
	
	public void login() {
		assertTrue(mainPage.getLogInLink().isDisplayed());
		mainPage.getLogInLink().click();
		
		loginPage.ensureIsDisplayed();
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		assertTrue(loginPage.getOKButton().isDisplayed());
		
		loginPage.setInputUsername("clerk2");
		loginPage.setInputPassword("c");
		loginPage.getOKButton().click();
		
		mainPage.ensureIsDisplayed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profileClerk", browser.getCurrentUrl());
	}
	
	@Test
	public void testUpdateProfileData() {
		 login();
		
		assertTrue(profileClerkPage.getChangeButton().isDisplayed());
		profileClerkPage.getChangeButton().click();
		
		profileClerkUpdateDataPage.ensureIsDisplayed();
		
		assertTrue(profileClerkUpdateDataPage.getName().isDisplayed());
		assertTrue(profileClerkUpdateDataPage.getSurname().isDisplayed());
		assertTrue(profileClerkUpdateDataPage.getPhoneNumber().isDisplayed());
		assertTrue(profileClerkUpdateDataPage.getAddress().isDisplayed());
		assertTrue(profileClerkUpdateDataPage.getCity().isDisplayed());

		profileClerkUpdateDataPage.setName("Maja");
		profileClerkUpdateDataPage.setSurname("Miljic");
		profileClerkUpdateDataPage.setCity("Novi Sad");

		profileClerkUpdateDataPage.getSaveButton().click();
	}

	@Test
	public void testAcceptUserRequest() {
		login();
		
		profileClerkPage.ensureCanAccept();

		int noOfUserRequests = profileClerkPage.getUserRequestsListSize();
		
		profileClerkPage.getAcceptButton().click();
		profileClerkPage.ensureIsRemoved(noOfUserRequests);
	}

	@Test
	public void testRejectUserRequest() {
		login();
		
		profileClerkPage.ensureCanAccept();

		int noOfUserRequests = profileClerkPage.getUserRequestsListSize();
		
		profileClerkPage.getRejectButton().click();
		profileClerkPage.ensureIsRemoved(noOfUserRequests);
	}
	
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}
}
