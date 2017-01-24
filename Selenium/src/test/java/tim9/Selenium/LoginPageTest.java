package tim9.Selenium;

import static org.junit.Assert.assertEquals;
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

public class LoginPageTest {
	
	private WebDriver browser;
	private MainPage mainPage;
	private LoginPage loginPage;
	
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
		loginPage = PageFactory.initElements(browser, LoginPage.class);
	}
	
	/**
	 * This method tests login
	 */
	@Test
	public void testLogIn() {
		
		mainPage.getLogInLink().click();
		loginPage.ensureIsDisplayed();
		
		assertTrue(loginPage.getOKButton().isDisplayed());
		
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		
		// WITH EMPTY INPUT DATA
		
		loginPage.setInputUsername("");
		loginPage.setInputPassword("");
		
		loginPage.getOKButton().click();
		
		loginPage.ensureIsDisplayed();
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		
		// WITH WRONG INPUT DATA
		
		loginPage.setInputUsername("wronginput");
		loginPage.setInputPassword("wronginput");
		
		loginPage.getOKButton().click();
		
		loginPage.ensureIsDisplayed();
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		assertEquals("Username or password are incorrect!", loginPage.getToastr().getText());
		
		// WITH CORRECT INPUT DATA
		
		loginPage.setInputUsername("user");
		loginPage.setInputPassword("u");
		
		loginPage.getOKButton().click();
		
		mainPage.ensureIsDisplayed();
		assertTrue(mainPage.getProfileLink().isDisplayed());
		assertTrue(mainPage.getLogOutLink().isDisplayed());
		
	}
	
	/**
	 * This method tests log out
	 */
	@Test
	public void testLogOut() {
		
		mainPage.getLogInLink().click();
		loginPage.ensureIsDisplayed();
		
		assertTrue(loginPage.getOKButton().isDisplayed());
		
		assertTrue(loginPage.getInputUsername().isDisplayed());
		assertTrue(loginPage.getInputPassword().isDisplayed());
		
		loginPage.setInputUsername("user");
		loginPage.setInputPassword("u");
		
		loginPage.getOKButton().click();
		
		mainPage.ensureLoginIsClosed();
		mainPage.ensureIsLogged();
		
		mainPage.getLogOutLink().click();
		
		mainPage.ensureIsDisplayed();
		assertTrue(mainPage.getLogInLink().isDisplayed());
		assertTrue(mainPage.getRegistrateLink().isDisplayed());
		
	}
	
	/**
	 * This method closes browser after test
	 */
	@AfterMethod
	public void closeSelenium() {
		browser.quit();
	}	

}
