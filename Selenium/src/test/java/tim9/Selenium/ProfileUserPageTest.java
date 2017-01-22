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
		assertTrue(mainPage.getLogInLink().isDisplayed());
		mainPage.getLogInLink().click();
		
		loginPage.ensureIsDisplayed();
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		assertTrue(loginPage.getOKButton().isDisplayed());
		
		loginPage.setInputUsername("user");
		loginPage.setInputPassword("u");
		loginPage.getOKButton().click();
		
		mainPage.ensureIsDisplayed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profile", browser.getCurrentUrl());
	}
	
	@Test
	public void testAskToJoin() {
		login();
		profileUserPage.ensureCanAskToJoin();
		profileUserPage.getAskToJoinButton().click();
	}
	
	@Test
	public void testRemoveAdvertisement() {
		login();
		
		int adverListSize = profileUserPage.getAdverListSize();
		profileUserPage.ensureCanRemove();
		profileUserPage.getRemoveButton().click();
		profileUserPage.ensureIsRemoved(adverListSize);
	}
	
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}
}
