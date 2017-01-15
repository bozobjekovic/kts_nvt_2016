package tim9.Selenium;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tim9.Selenium.MainPage;
import tim9.Selenium.advertisement.AdvertisementPage;
import tim9.Selenium.advertisement.BuyPage;

public class AdvertisementPageTest {
	
	private WebDriver browser;
	private MainPage mainPage;
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
		buyPage = PageFactory.initElements(browser, BuyPage.class);
		advertisementPage = PageFactory.initElements(browser, AdvertisementPage.class);
	}
	
	@Test
	public void test() {
		
		mainPage.getBuyLink().click();
		assertEquals("http://localhost:8080/#/buy", browser.getCurrentUrl());
		
		buyPage.getAdvertisementLink().click();
		assertEquals("http://localhost:8080/#/advertisement/2", browser.getCurrentUrl());
		
		assertTrue(advertisementPage.getReportButton().isDisplayed());
		advertisementPage.getReportButton().click();
		advertisementPage.ensureIsDisplayed();
		
		assertTrue(advertisementPage.getInputTitle().isDisplayed());
		assertTrue(advertisementPage.getInputDescription().isDisplayed());
		
		assertTrue(advertisementPage.getOKButton().isDisplayed());
		advertisementPage.getOKButton().click();
		
	}

}
