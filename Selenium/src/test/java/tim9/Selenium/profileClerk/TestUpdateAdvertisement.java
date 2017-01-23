package tim9.Selenium.profileClerk;

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
import tim9.Selenium.ProfileClerkPage;
import tim9.Selenium.ProfileClerkUpdateAdverPage;
import tim9.Selenium.login.LoginPage;

public class TestUpdateAdvertisement {

	private WebDriver browser;
	MainPage mainPage;
	ProfileClerkPage profileClerkPage;
	ProfileClerkUpdateAdverPage updateAdverPage;
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
		updateAdverPage = PageFactory.initElements(browser, ProfileClerkUpdateAdverPage.class);
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

		mainPage.ensureLoginIsClosed();
		mainPage.ensureIsDisplayed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profileClerk", browser.getCurrentUrl());
	}
	
	public void openUpdateAdverModal() {
		assertTrue(profileClerkPage.getUpdateButton().isDisplayed());
		profileClerkPage.getUpdateButton().click();
		
		updateAdverPage.ensureIsDisplayed();
		assertTrue(updateAdverPage.getName().isDisplayed());
	}
	
	@Test
	public void testUpdateAdver() {
		login();
		assertEquals(profileClerkPage.getClerkAdverName().getText(), "Kuca sa dvoristem");
		assertEquals(profileClerkPage.getClerkAdverPrice().getText(), "$4,444,424.00");
		
		profileClerkPage.ensureModalIsClosed();
		openUpdateAdverModal();
		updateAdverPage.getSaveButton().click();
		
		profileClerkPage.ensureModalIsClosed();
		openUpdateAdverModal();
		updateAdverPage.setPhoneNumber("+504982");
		updateAdverPage.ensureCanSave();
		updateAdverPage.getSaveButton().click();
		
		profileClerkPage.ensureModalIsClosed();
		openUpdateAdverModal();
		updateAdverPage.setName("");
		updateAdverPage.setLandSize("");
		updateAdverPage.setEquipment("");
		updateAdverPage.setBathrooms("");
		updateAdverPage.setBedrooms("");
		updateAdverPage.setFloors("");
		updateAdverPage.setBuildYear("");
		updateAdverPage.setPrice("");
		updateAdverPage.setPhoneNumber("");
		updateAdverPage.ensureCanSave();
		updateAdverPage.getSaveButton().click();
		
		updateAdverPage.setName("Vikendica");
		updateAdverPage.setLandSize("80");
		updateAdverPage.setEquipment("Internet");
		updateAdverPage.setBathrooms("2");
		updateAdverPage.setBedrooms("3");
		updateAdverPage.setFloors("2");
		updateAdverPage.setBuildYear("2006");
		updateAdverPage.setPrice("1000000");
		updateAdverPage.setPhoneNumber("+380011223");
		updateAdverPage.ensureCanSave();
		updateAdverPage.getSaveButton().click();
		profileClerkPage.ensureModalIsClosed();
		
		profileClerkPage.ensureCanUpdate();
		assertEquals(profileClerkPage.getClerkAdverName().getText(), "Vikendica");
		assertEquals(profileClerkPage.getClerkAdverPrice().getText(), "$1,000,000.00");
	}
	
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}
}
