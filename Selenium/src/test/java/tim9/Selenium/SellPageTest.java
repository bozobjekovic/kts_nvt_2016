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

public class SellPageTest {

	private WebDriver browser;
	MainPage mainPage;
	SellPage sellPage;
	LoginPage loginPage;
	ProfileUserPage profileUserPage;
	
	DriverConfiguration driverConfiguration = new DriverConfiguration();
	
	@BeforeMethod
	public void setupSelenium() {
		System.setProperty("webdriver.chrome.driver", driverConfiguration.getDriverPath());
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.navigate().to("http://localhost:8080");

		mainPage = PageFactory.initElements(browser, MainPage.class);
		sellPage = PageFactory.initElements(browser, SellPage.class);
		loginPage = PageFactory.initElements(browser, LoginPage.class);
		profileUserPage = PageFactory.initElements(browser, ProfileUserPage.class);
	}
	
	void login() {
		assertTrue(mainPage.getLogInLink().isDisplayed());
		mainPage.getLogInLink().click();
		
		loginPage.ensureIsDisplayed();
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		assertTrue(loginPage.getOKButton().isDisplayed());
		
		loginPage.setInputUsername("user");
		loginPage.setInputPassword("u");
		loginPage.getOKButton().click();

		mainPage.ensureLoginIsClosed();
		mainPage.ensureIsDisplayed();
	}
	
	@Test
	public void testSellPage() {
		login();

		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profile", browser.getCurrentUrl());

		mainPage.ensureLoginIsClosed();
		int noOfAdvers = profileUserPage.getAdverListSize();
		
		mainPage.getSellLink().click();
		assertEquals("http://localhost:8080/#/sell", browser.getCurrentUrl());
		
		sellPage.ensureIsDisplayed();
		assertTrue(sellPage.getName().isDisplayed());
		
		sellPage.getSubmitButton().click();
		
		sellPage.setPartOfTheCity("Liman");
		sellPage.setEquipment("Internet");
		sellPage.setBathrooms("2");
		sellPage.setBedrooms("3");
		sellPage.setFloors("2");
		sellPage.setBuildYear("2006");
		sellPage.ensureCanSubmit();
		sellPage.getSubmitButton().click();

		sellPage.setName("Kuca sa bazenom");
		sellPage.setAddress("Bulevar cara Lazara 108");;
		sellPage.setCity("Novi Sad");
		sellPage.setZipCode("21000");
		sellPage.setLandSize("80");
		sellPage.setHeatingType(1);
		sellPage.setCategory(1);
		sellPage.setType(1);
		sellPage.setPrice("1000000");
		sellPage.setPhoneNumber("+504982");
		sellPage.setPurpose(1);
		sellPage.getSubmitButton().click();
		mainPage.ensureLoginIsClosed();
		
		sellPage.setPhoneNumber("+3811111111");
		sellPage.ensureCanSubmit();
		sellPage.getSubmitButton().click();
		mainPage.ensureLoginIsClosed();

		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profile", browser.getCurrentUrl());
		mainPage.ensureLoginIsClosed();
		assertEquals(profileUserPage.getAdverListSize(), noOfAdvers+1);
	}
	
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}
}
