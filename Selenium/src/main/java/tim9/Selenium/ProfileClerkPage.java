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

	public void ensureIsDisplayed() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@ng-click=\"openModal()\"]")));
	}
	public void ensureCanAccept() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@ng-click=\"accept(applied.id)\"]")));
	}
	public void ensureCanReject() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@ng-click=\"deny(applied.id)\"]")));
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
	public int getUserRequestsListSize() {
		return driver.findElements(By.className("profile-listItem")).size();
	}
}
