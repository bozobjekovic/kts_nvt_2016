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

import tim9.Selenium.advertisement.AdvertisementPage;
import tim9.Selenium.advertisement.BuyPage;
import tim9.Selenium.login.LoginPage;

public class AdvertisementPageTest {
	
	private WebDriver browser;
	private MainPage mainPage;
	private LoginPage loginPage;
	private BuyPage buyPage;
	private AdvertisementPage advertisementPage;
	
	@BeforeMethod
	public void setupSelenium() {
		System.setProperty("webdriver.chrome.driver", "D:/Documents/Downloads/chromedriver_win32/chromedriver.exe");
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.navigate().to("http://localhost:8080");
		
		mainPage = PageFactory.initElements(browser, MainPage.class);
		loginPage = PageFactory.initElements(browser, LoginPage.class);
		buyPage = PageFactory.initElements(browser, BuyPage.class);
		advertisementPage = PageFactory.initElements(browser, AdvertisementPage.class);
	}
	
	@Test
	public void testReport() {
		
		mainPage.getLogInLink().click();
		loginPage.ensureIsDisplayed();
		
		assertTrue(loginPage.getOKButton().isDisplayed());
		
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		
		loginPage.setInputUsername("user");
		loginPage.setInputPassword("u");
		
		loginPage.getOKButton().click();
		
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
		
		advertisementPage.ensureIsDisplayed();
		advertisementPage.getReportButton().click();
		advertisementPage.ensureModalIsDisplayed();
		
		advertisementPage.setInputTitle("Report Title");
		advertisementPage.setInputDescription("This is reports's description!");
		
		advertisementPage.getOKButton().click();		

	}

	@Test
	public void testComment() {
		
		mainPage.getLogInLink().click();
		loginPage.ensureIsDisplayed();
		
		assertTrue(loginPage.getOKButton().isDisplayed());
		
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		
		loginPage.setInputUsername("user");
		loginPage.setInputPassword("u");
		
		loginPage.getOKButton().click();
		
		mainPage.ensureIsDisplayed();
		
		mainPage.getBuyLink().click();
		assertEquals("http://localhost:8080/#/buy", browser.getCurrentUrl());
		
		buyPage.getAdvertisementLink().click();
		assertEquals("http://localhost:8080/#/advertisement/2", browser.getCurrentUrl());
		
		int commentsSize = advertisementPage.getCommentsSize();
		
		advertisementPage.setInputComment("I'm leaving this comment");
		advertisementPage.getSubmitButton().click();

		advertisementPage.ensureIsAdded(commentsSize);
		assertEquals(advertisementPage.getCommentsSize(), commentsSize + 1);
		
	}
	
	@Test
	public void testRate() {
		
		mainPage.getLogInLink().click();
		loginPage.ensureIsDisplayed();
		
		assertTrue(loginPage.getOKButton().isDisplayed());
		
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		
		loginPage.setInputUsername("user");
		loginPage.setInputPassword("u");
		
		loginPage.getOKButton().click();
		
		mainPage.ensureIsDisplayed();
		
		mainPage.getBuyLink().click();
		assertEquals("http://localhost:8080/#/buy", browser.getCurrentUrl());
		
		buyPage.getAdvertisementLink().click();
		assertEquals("http://localhost:8080/#/advertisement/2", browser.getCurrentUrl());
		
		assertTrue(advertisementPage.getReportButton().isDisplayed());
		
		advertisementPage.getRate().click();
		
		// CHECK RATE ?
		
	}
	
	
	
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}

}
