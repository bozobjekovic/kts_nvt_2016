package tim9.Selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
	
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

	

}
