package tim9.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileAdminPage {

	private WebDriver driver;
	
	public ProfileAdminPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath="//button[@ng-click=\"acceptClerk(clerk.id)\"]")
	private WebElement acceptButton;

	@FindBy(xpath="//button[@ng-click=\"denyClerk(clerk.id)\"]")
	private WebElement rejectButton;

	@FindBy(xpath="//button[@ng-click=\"openModal()\"]")
	private WebElement registerVerifierButton;

	public void ensureCanAccept() {
		(new WebDriverWait(driver, 10))
		  .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-click=\"acceptClerk(clerk.id)\"]")));
	}

	public WebElement getAcceptButton() {
		return acceptButton;
	}
	public WebElement getRejectButton() {
		return rejectButton;
	}
	public WebElement getRegisterVerifierButton() {
		return registerVerifierButton;
	}
	public int getClerkRequestListSize() {
		return driver.findElements(By.className("profile-listItem")).size();
	}
}
