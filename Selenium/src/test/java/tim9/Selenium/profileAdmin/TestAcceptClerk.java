package tim9.Selenium.profileAdmin;

import static org.testng.AssertJUnit.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tim9.Selenium.MainPage;
import tim9.Selenium.ProfileAdminPage;
import tim9.Selenium.configuration.DriverConfiguration;
import tim9.Selenium.login.LoginPage;

public class TestAcceptClerk {

	private WebDriver browser;
	MainPage mainPage;
	ProfileAdminPage profileAdminPage;
	LoginPage loginPage;
	
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
		profileAdminPage = PageFactory.initElements(browser, ProfileAdminPage.class);
		loginPage = PageFactory.initElements(browser, LoginPage.class);
	}
	
	/**
	 * This method represents login
	 */
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

	/**
	 * This method tests accepting clerk
	 */
	@Test
	public void testAcceptClerk() {
		String username = "admin";
		String password = "a";
		login(username, password);
		
		profileAdminPage.ensureCanAccept();
		
		int noOfClerkRequests = profileAdminPage.getClerkRequestListSize();
		profileAdminPage.getAcceptButton().click();
		profileAdminPage.ensureIsRemoved(noOfClerkRequests);
		
		mainPage.getLogOutLink().click();
		
		username = "clerk3";
		password = "c";
		
		mainPage.ensureLoginIsClosed();
		login(username, password);
	}
	
	/**
	 * This method closes browser after test
	 */
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}
}
