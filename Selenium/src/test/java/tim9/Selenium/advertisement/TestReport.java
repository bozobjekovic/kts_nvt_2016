package tim9.Selenium.advertisement;

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
import tim9.Selenium.ProfileVerifierPage;
import tim9.Selenium.configuration.DriverConfiguration;
import tim9.Selenium.login.LoginPage;

public class TestReport {
	
	private WebDriver browser;
	private MainPage mainPage;
	private LoginPage loginPage;
	private BuyPage buyPage;
	ProfileVerifierPage profileVerifierPage;
	private AdvertisementPage advertisementPage;
	
	DriverConfiguration driverConfiguration = new DriverConfiguration();
	
	@BeforeMethod
	public void setupSelenium() {
		System.setProperty("webdriver.chrome.driver", driverConfiguration.getDriverPath());
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.navigate().to("http://localhost:8080");
		
		mainPage = PageFactory.initElements(browser, MainPage.class);
		loginPage = PageFactory.initElements(browser, LoginPage.class);
		buyPage = PageFactory.initElements(browser, BuyPage.class);
		profileVerifierPage = PageFactory.initElements(browser, ProfileVerifierPage.class);
		advertisementPage = PageFactory.initElements(browser, AdvertisementPage.class);
	}
	
	void login(String username, String password) {
		mainPage.ensureIsDisplayed();
		assertTrue(mainPage.getLogInLink().isDisplayed());
		mainPage.getLogInLink().click();
		
		loginPage.ensureIsDisplayed();
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		assertTrue(loginPage.getOKButton().isDisplayed());
		
		loginPage.setInputUsername(username);
		loginPage.setInputPassword(password);
		loginPage.getOKButton().click();
		
		mainPage.ensureLoginIsClosed();
		mainPage.getProfileLink().click();
	}
	
	@Test
	public void testReport() {
		
		String username = "verifier";
		String password = "v";
		
		login(username, password);
		
		profileVerifierPage.ensureCanAccept();
		assertEquals("http://localhost:8080/#/profileVerifier", browser.getCurrentUrl());
		
		profileVerifierPage.ensureCanAccept();
		
		int noOfReportedAdvers = profileVerifierPage.getReportedAdversListSize();
		
		mainPage.getLogOutLink().click();
		
		mainPage.ensureIsDisplayed();
		mainPage.getBuyLink().click();
		assertEquals("http://localhost:8080/#/buy", browser.getCurrentUrl());
		
		buyPage.getAdvertisementLink().click();
		assertEquals("http://localhost:8080/#/advertisement/2", browser.getCurrentUrl());
		
		assertTrue(advertisementPage.getReportButton().isDisplayed());
		advertisementPage.getReportButton().click();
		advertisementPage.ensureModalIsDisplayed();
		
		assertTrue(advertisementPage.getInputTitle().isDisplayed());
		assertTrue(advertisementPage.getInputDescription().isDisplayed());
		
		advertisementPage.setInputTitle("Report Title");
		advertisementPage.setInputDescription("This is reports's description!");
		
		advertisementPage.getOKButton().click();

		//NOTIFIKACIJA
		
		advertisementPage.ensureModalIsClosed();
		
		username = "user";
		password = "u";
		
		login(username, password);
		
		mainPage.ensureIsDisplayed();
		mainPage.getBuyLink().click();
		assertEquals("http://localhost:8080/#/buy", browser.getCurrentUrl());
		
		buyPage.getAdvertisementLink().click();
		assertEquals("http://localhost:8080/#/advertisement/2", browser.getCurrentUrl());
		
		assertTrue(advertisementPage.getReportButton().isDisplayed());
		advertisementPage.getReportButton().click();
		advertisementPage.ensureModalIsDisplayed();
		
		assertTrue(advertisementPage.getInputTitle().isDisplayed());
		assertTrue(advertisementPage.getInputDescription().isDisplayed());
		
		assertTrue(advertisementPage.getOKButton().isDisplayed());
		advertisementPage.getOKButton().click();
		
		// STILL OPEN
		
		advertisementPage.setInputTitle("Report Title");
		advertisementPage.setInputDescription("This is reports's description!");
		
		advertisementPage.getOKButton().click();
		
		// NOW CLOSED
		
/*		advertisementPage.ensureIsDisplayed();
		advertisementPage.getReportButton().click();
		advertisementPage.ensureModalIsDisplayed();
		
		advertisementPage.setInputTitle("Report Title");
		advertisementPage.setInputDescription("This is reports's description!");
		
		advertisementPage.getOKButton().click();*/
		
		advertisementPage.ensureModalIsClosed();
		mainPage.getLogOutLink().click();
		
		username = "verifier";
		password = "v";
		
		login(username, password);
		
		profileVerifierPage.ensureCanAccept();
		assertEquals("http://localhost:8080/#/profileVerifier", browser.getCurrentUrl());
		
		profileVerifierPage.ensureCanAccept();
		assertEquals(profileVerifierPage.getReportedAdversListSize(), noOfReportedAdvers + 1);
	}
	
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}

}
