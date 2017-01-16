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

public class ProfileUserPageTest {

	private WebDriver browser;
	MainPage mainPage;
	ProfileUserPage profileUserPage;
	UpdateProfileDataPage profileUserUpdateDataPage;
	LoginPage loginPage;
	
	@BeforeMethod
	public void setupSelenium() {
		System.setProperty("webdriver.chrome.driver", "C:/Program Files/chromedriver_win32/chromedriver.exe");
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.navigate().to("http://localhost:8080");

		mainPage = PageFactory.initElements(browser, MainPage.class);
		profileUserPage = PageFactory.initElements(browser, ProfileUserPage.class);
		profileUserUpdateDataPage = PageFactory.initElements(browser, UpdateProfileDataPage.class);
		loginPage = PageFactory.initElements(browser, LoginPage.class);
	}
	
	public void login() {

		// Login
		assertTrue(mainPage.getLogInLink().isDisplayed());
		mainPage.getLogInLink().click();
		
		loginPage.ensureIsDisplayed();
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		assertTrue(loginPage.getOKButton().isDisplayed());
		
		loginPage.setInputUsername("user");
		loginPage.setInputPassword("u");
		loginPage.getOKButton().click();
		
		// Go to profile
		mainPage.ensureIsDisplayed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profile", browser.getCurrentUrl());
	}
	
	@Test
	public void testUpdateProfileData() {
		
		login();
		
		// Open modal
		assertTrue(profileUserPage.getChangeButton().isDisplayed());
		profileUserPage.getChangeButton().click();
		
		profileUserUpdateDataPage.ensureIsDisplayed();
		
		assertTrue(profileUserUpdateDataPage.getName().isDisplayed());
		assertTrue(profileUserUpdateDataPage.getSurname().isDisplayed());
		assertTrue(profileUserUpdateDataPage.getPhoneNumber().isDisplayed());
		assertTrue(profileUserUpdateDataPage.getAddress().isDisplayed());
		assertTrue(profileUserUpdateDataPage.getCity().isDisplayed());

		profileUserUpdateDataPage.setName("Maja");
		profileUserUpdateDataPage.setSurname("Miljic");
		profileUserUpdateDataPage.setCity("Novi Sad");
		profileUserUpdateDataPage.getSaveButton().click();
	}
	
	@Test
	public void testAskToJoin() {
		
		login();
		
		// Ask to Join
		profileUserPage.ensureCanAskToJoin();
		profileUserPage.getAskToJoinButton().click();
	}
	
	@Test
	public void testRemoveAdvertisement() {
		
		login();
		
		// Remove Advertisement
		// int adverListSize = profileUserPage.getAdverListSize();
		
		profileUserPage.ensureCanRemove();
		profileUserPage.getRemoveButton().click();
		// assertEquals(profileUserPage.getAdverListSize(), adverListSize-1);
	}
	
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}
}
