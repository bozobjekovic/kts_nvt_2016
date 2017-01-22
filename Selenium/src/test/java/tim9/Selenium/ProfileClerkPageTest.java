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
	ProfileClerkUpdateAdverPage updateAdverPage;
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
		updateAdverPage = PageFactory.initElements(browser, ProfileClerkUpdateAdverPage.class);
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

		mainPage.ensureLoginIsClosed();
		mainPage.ensureIsDisplayed();
		mainPage.getProfileLink().click();
		assertEquals("http://localhost:8080/#/profileClerk", browser.getCurrentUrl());
	}
	
	public void openModal() {
		assertTrue(profileClerkPage.getChangeButton().isDisplayed());
		profileClerkPage.getChangeButton().click();
		
		profileClerkUpdateDataPage.ensureIsDisplayed();
		assertTrue(profileClerkUpdateDataPage.getName().isDisplayed());
	}
	
	public void openUpdateAdverModal() {
		assertTrue(profileClerkPage.getUpdateButton().isDisplayed());
		profileClerkPage.getUpdateButton().click();
		
		updateAdverPage.ensureIsDisplayed();
		assertTrue(updateAdverPage.getName().isDisplayed());
	}
	
	@Test
	public void testUpdateProfileData() {
		login();
		profileClerkPage.ensureModalIsClosed();
		
		assertEquals(profileClerkPage.getClerkName().getText(), "Clerk2");
		assertEquals(profileClerkPage.getClerkSurname().getText(), "Clerkan2");
		assertEquals(profileClerkPage.getClerkPNumber().getText(), "+15232");
		assertEquals(profileClerkPage.getClerkAddress().getText(), "Novosadska");
		assertEquals(profileClerkPage.getClerkCity().getText(), "Novi Sad");
		profileClerkPage.ensureModalIsClosed();
		
		openModal();
		updateAdverPage.ensureCanSave();
		profileClerkUpdateDataPage.getSaveButton().click();
		profileClerkPage.ensureModalIsClosed();

		openModal();
		profileClerkUpdateDataPage.setPhoneNumber("+44440");
		updateAdverPage.ensureCanSave();
		profileClerkUpdateDataPage.getSaveButton().click();
		profileClerkPage.ensureModalIsClosed();
		
		openModal();
		profileClerkUpdateDataPage.setName("");
		profileClerkUpdateDataPage.setSurname("");
		profileClerkUpdateDataPage.setPhoneNumber("");
		profileClerkUpdateDataPage.setAddress("");
		profileClerkUpdateDataPage.setCity("");
		profileClerkUpdateDataPage.getSaveButton().click();

		profileClerkUpdateDataPage.setName("Maja");
		profileClerkUpdateDataPage.setSurname("Miljic");
		profileClerkUpdateDataPage.setPhoneNumber("+24242424");
		profileClerkUpdateDataPage.setAddress("Balzakova 20");
		profileClerkUpdateDataPage.setCity("Novi Sad");
		profileClerkUpdateDataPage.getSaveButton().click();
		profileClerkPage.ensureModalIsClosed();
		
		assertEquals(profileClerkPage.getClerkName().getText(), "Maja");
		assertEquals(profileClerkPage.getClerkSurname().getText(), "Miljic");
		assertEquals(profileClerkPage.getClerkPNumber().getText(), "+24242424");
		assertEquals(profileClerkPage.getClerkAddress().getText(), "Balzakova 20");
		assertEquals(profileClerkPage.getClerkCity().getText(), "Novi Sad");
		profileClerkPage.ensureModalIsClosed();
	}
	/*
	@Test
	public void testAcceptUserRequest() {
		login();
		
		profileClerkPage.ensureCanAccept();

		int noOfUserRequests = profileClerkPage.getUserRequestsListSize();
		
		profileClerkPage.getAcceptButton().click();
		profileClerkPage.ensureIsRejected(noOfUserRequests);
	}

	@Test
	public void testRejectUserRequest() {
		login();
		
		profileClerkPage.ensureCanAccept();

		int noOfUserRequests = profileClerkPage.getUserRequestsListSize();
		
		profileClerkPage.getRejectButton().click();
		profileClerkPage.ensureIsRejected(noOfUserRequests);
	}

	@Test
	public void testRemoveAdver() {
		login();
		
		profileClerkPage.ensureCanRemove();

		int noOfAdvers = profileClerkPage.getAdverListSize();
		
		profileClerkPage.getRemoveButton().click();
		profileClerkPage.ensureIsRemoved(noOfAdvers);
	}
	
	@Test
	public void testUpdateAdver() {
		login();

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
	}
	*/
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}
}
