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
import tim9.Selenium.configuration.DriverConfiguration;

public class TestFilterAdvertisements {
	
	private WebDriver browser;
	private MainPage mainPage;
	private BuyPage buyPage;
	
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
		buyPage = PageFactory.initElements(browser, BuyPage.class);
	}
	
	/**
	 * This method tests filtering advertisement
	 */
	@Test
	public void testFilter(){
		
		mainPage.ensureIsDisplayed();
		mainPage.getBuyLink().click();
		assertEquals("http://localhost:8080/#/buy", browser.getCurrentUrl());
		
		assertTrue(buyPage.getApartmentsLink().isDisplayed());
		buyPage.getApartmentsLink().click();
		buyPage.ensureIsDisplayed();
		assertEquals(buyPage.getFilterSize(), 7);
		
		buyPage.getCityLink().click();
		buyPage.ensureIsDisplayed();
		assertEquals(buyPage.getFilterSize(), 2);
		
		buyPage.getOtherCityLink().click();
		buyPage.ensureIsDisplayed();
		assertEquals(buyPage.getFilterSize(), 3);
		
		buyPage.getPriceColapse().click();
		buyPage.setPriceFrom("33");
		buyPage.setPriceTo("5000000");
		buyPage.getPriceFilter().click();
		buyPage.ensureIsDisplayed();
		assertEquals(buyPage.getFilterSize(), 2);
		
		buyPage.getLandSizeColapse().click();
		buyPage.setLandSizeFrom("50");
		buyPage.setLandSizeTo("480");
		buyPage.getLandFilter().click();
		buyPage.ensureIsDisplayed();
		assertEquals(buyPage.getFilterSize(), 2);
		
		buyPage.getPartOfTheCityLink().click();
		buyPage.ensureIsDisplayed();
		assertEquals(buyPage.getFilterSize(), 2);
		
		buyPage.getOtherPartOfTheCityLink().click();
		buyPage.ensureIsDisplayed();
		assertEquals(buyPage.getFilterSize(), 2);
		
		buyPage.getHeatingColapse().click();
		buyPage.getHeatingType().click();
		buyPage.ensureIsDisplayed();
		assertEquals(buyPage.getFilterSize(), 0);
		buyPage.getHeatingType().click();
		buyPage.ensureIsDisplayed();
		assertEquals(buyPage.getFilterSize(), 2);
		
		buyPage.getFloorsColapse().click();
		buyPage.setFloorsFrom("6");
		buyPage.getFllorsFilter().click();
		buyPage.ensureIsDisplayed();
		assertEquals(buyPage.getFilterSize(), 0);
		
		buyPage.getLandFilter().click();
		
	}
	
	/**
	 * This method closes browser after test
	 */
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}

}
