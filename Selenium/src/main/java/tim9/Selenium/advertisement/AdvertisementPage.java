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
	
	@FindBy(xpath = "//input[@id=\"rprt\"]")
	private WebElement reportButton;
	
	@FindBy(id = "title")
	private WebElement inputTitle;
	
	@FindBy(id = "description")
	private WebElement inputDescription;
	
	@FindBy(xpath = "//button[@id=\"reportButton\"]")
	private WebElement OKButton;
	
	@FindBy(id = "comment")
	private WebElement inputComment;
	
	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement submitButton;
	
	@FindBy(xpath = "//span[@ng-model=\"rate\"]")
	private WebElement rate;
	
	@FindBy(xpath = "//span[@ng-model=\"rateUser\"]")
	private WebElement userRate;

	public WebElement getReportButton() {
		return reportButton;
	}	
	
	public WebElement getInputTitle() {
		return inputTitle;
	}

	public void setInputTitle(String inputTitle) {
		WebElement el = getInputTitle();
		el.clear();
		el.sendKeys(inputTitle);
	}

	public WebElement getInputDescription() {
		return inputDescription;
	}

	public void setInputDescription(String inputDescription) {
		WebElement el = getInputDescription();
		el.clear();
		el.sendKeys(inputDescription);
	}

	public WebElement getOKButton() {
		return OKButton;
	}

	public WebElement getInputComment() {
		return inputComment;
	}

	public void setInputComment(String inputComment) {
		WebElement el = getInputComment();
		el.clear();
		el.sendKeys(inputComment);
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getRate() {
		return rate;
	}

	public WebElement getUserRate() {
		return userRate;
	}

	public void ensureModalIsDisplayed() {
		(new WebDriverWait(browser, 2))
				  .until(ExpectedConditions.presenceOfElementLocated(
						  By.id("title")));
	}
	
	public void ensureModalIsClosed() {
		(new WebDriverWait(browser, 2))
				  .until(ExpectedConditions.invisibilityOfElementLocated(
						  By.id("title")));
	}
	
	public void ensureIsDisplayed() {
		(new WebDriverWait(browser, 2))
				  .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						  By.xpath("//input[@id=\"rprt\"]")));
	}
	
	public void ensureIsAdded(int numberOfComments) {
		(new WebDriverWait(browser, 2))
		  .until(ExpectedConditions.visibilityOfElementLocated(
				  By.xpath("(//div[@class='comment-listItem'])[" + (numberOfComments + 1) + "]")));
		
	}
	
	public int getCommentsSize() {
		return browser.findElements(By.className("comment-listItem")).size();
	}
	
}
