package tim9.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
	
	private WebDriver browser;
	
	public MainPage(WebDriver browser) {
		this.browser = browser;
	}
	
	@FindBy(xpath = "//a[@ng-href=\"/#\"]")
	private WebElement homeLink;
	
	@FindBy(xpath = "//a[@ng-href=\"/#/buy\"]")
	private WebElement buyLink;
	
	@FindBy(xpath = "//a[@ng-href=\"/#/rent\"]")
	private WebElement rentLink;
	
	@FindBy(xpath = "//a[@ng-href=\"/#/sell\"]")
	private WebElement sellLink;
	
	@FindBy(xpath = "//a[@ng-href=\"/#/about\"]")
	private WebElement aboutLink;
	
	@FindBy(xpath = "//a[@ng-href=\"/#/contact\"]")
	private WebElement contactLink;
	
	@FindBy(xpath = "//a[text()=\"Log In\"]")
	private WebElement logInLink;
	
	@FindBy(xpath = "//a[text()=\"Register\"]")
	private WebElement registrateLink;
	
	@FindBy(xpath = "//a[@id=\"profile\"]")
	private WebElement profileLink;
	
	@FindBy(xpath = "//a[text()=\"Log Out\"]")
	private WebElement logOutLink;

	public WebElement getHomeLink() {
		return homeLink;
	}

	public WebElement getBuyLink() {
		return buyLink;
	}

	public WebElement getRentLink() {
		return rentLink;
	}

	public WebElement getSellLink() {
		return sellLink;
	}

	public WebElement getAboutLink() {
		return aboutLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getLogInLink() {
		return logInLink;
	}

	public WebElement getProfileLink() {
		return profileLink;
	}
	
	public WebElement getRegistrateLink() {
		return registrateLink;
	}

	public WebElement getLogOutLink() {
		return logOutLink;
	}
	
	public void ensureIsDisplayed() {
		(new WebDriverWait(browser, 2))
				  .until(ExpectedConditions.visibilityOfElementLocated(
						  By.xpath("//a[@ng-href=\"/#/buy\"]")));
	}
	
	public void ensureIsLogged() {
		(new WebDriverWait(browser, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(
						  By.xpath("//a[text()=\"Log Out\"]")));
	}
	
	public void ensureLoginIsClosed() {
		(new WebDriverWait(browser, 2))
				  .until(ExpectedConditions.invisibilityOfElementLocated(
						  By.id("username")));
	}
}
