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

import tim9.Selenium.advertisement.BuyPage;

public class FilterPageTest {
	
	private WebDriver browser;
	private MainPage mainPage;
	private BuyPage buyPage;
	
	@BeforeMethod
	public void setupSelenium() {
		System.setProperty("webdriver.chrome.driver", "D:/Documents/Downloads/chromedriver_win32/chromedriver.exe");
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.navigate().to("http://localhost:8080");
		
		mainPage = PageFactory.initElements(browser, MainPage.class);
		buyPage = PageFactory.initElements(browser, BuyPage.class);
	}
	
	@Test
	public void testFilter(){
		
		mainPage.ensureIsDisplayed();
		mainPage.getBuyLink().click();
		assertEquals("http://localhost:8080/#/buy", browser.getCurrentUrl());
		
		assertTrue(buyPage.getApartmentsLink().isDisplayed());
		buyPage.getApartmentsLink().click();
		
	}
	
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}


}
