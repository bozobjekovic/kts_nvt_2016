package tim9.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileClerkPage {

	private WebDriver driver;
	
	public ProfileClerkPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath="//button[@ng-click=\"openModal()\"]")
	private WebElement changeButton;

	@FindBy(xpath="//button[@ng-click=\"accept(applied.id)\"]")
	private WebElement acceptButton;
	
	@FindBy(xpath="//button[@ng-click=\"deny(applied.id)\"]")
	private WebElement rejectButton;
	
	@FindBy(id="removeAdver")
	private WebElement removeButton;
	
	@FindBy(id="update")
	private WebElement updateButton;

	@FindBy(id="clerkName")
	private WebElement clerkName;

	@FindBy(id="clerkSurname")
	private WebElement clerkSurname;

	@FindBy(id="clerkPNumber")
	private WebElement clerkPNumber;

	@FindBy(id="clerkAddress")
	private WebElement clerkAddress;

	@FindBy(id="clerkCity")
	private WebElement clerkCity;

	public void ensureIsDisplayed() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@ng-click=\"openModal()\"]")));
	}
	public void ensureCanAccept() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@ng-click=\"accept(applied.id)\"]")));
	}
	public void ensureCanReject() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@ng-click=\"deny(applied.id)\"]")));
	}
	public void ensureCanRemove() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("removeAdver")));
	}
	public void ensureCanUpdate() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("update")));
	}
	public void ensureIsRejected(int numberOfUserRequests) {
		(new WebDriverWait(driver, 10))
		  .until(ExpectedConditions.invisibilityOfElementLocated(
				  By.xpath("//div[@class=\"profile-listItem\"])[" + (numberOfUserRequests+1) + "]")));
	}
	public void ensureIsRemoved(int numberOfAdvers) {
		(new WebDriverWait(driver, 10))
		  .until(ExpectedConditions.invisibilityOfElementLocated(
				  By.xpath("//div[@class=\"profile-listItem-text\"])[" + (numberOfAdvers+1) + "]")));
	}
	public void ensureModalIsClosed() {
		(new WebDriverWait(driver, 2))
				  .until(ExpectedConditions.invisibilityOfElementLocated(
						  By.name("name")));
	}

	public WebElement getChangeButton() {
		return changeButton;
	}
	public WebElement getAcceptButton() {
		return acceptButton;
	}
	public WebElement getRejectButton() {
		return rejectButton;
	}
	public WebElement getRemoveButton() {
		return removeButton;
	}
	public WebElement getUpdateButton() {
		return updateButton;
	}
	public WebElement getClerkName() {
		return clerkName;
	}
	public WebElement getClerkSurname() {
		return clerkSurname;
	}
	public WebElement getClerkPNumber() {
		return clerkPNumber;
	}
	public WebElement getClerkAddress() {
		return clerkAddress;
	}
	public WebElement getClerkCity() {
		return clerkCity;
	}
	public int getAdverListSize() {
		return driver.findElements(By.className("profile-listItem-text")).size();
	}
	public int getUserRequestsListSize() {
		return driver.findElements(By.className("profile-listItem")).size();
	}
}
