package tim9.Selenium.advertisement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdvertisementPage {
	
	private WebDriver browser;
	
	public AdvertisementPage(WebDriver browser) {
		this.browser = browser;
	}
	
	@FindBy(xpath = "//input[@value=\"Report\"]")
	private WebElement reportButton;
	
	@FindBy(id = "title")
	private WebElement inputTitle;
	
	@FindBy(id = "description")
	private WebElement inputDescription;
	
	@FindBy(xpath = "//input[@value=\"Send\"]")
	private WebElement OKButton;

	public WebElement getReportButton() {
		return reportButton;
	}	
	
	public WebElement getInputTitle() {
		return inputTitle;
	}

	public void setInputTitle(WebElement inputTitle) {
		this.inputTitle = inputTitle;
	}

	public WebElement getInputDescription() {
		return inputDescription;
	}

	public void setInputDescription(WebElement inputDescription) {
		this.inputDescription = inputDescription;
	}

	public WebElement getOKButton() {
		return OKButton;
	}

	public void ensureIsDisplayed() {
		(new WebDriverWait(browser, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(
						  By.id("title")));
	}
	
}
