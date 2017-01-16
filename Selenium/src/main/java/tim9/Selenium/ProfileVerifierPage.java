package tim9.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileVerifierPage {

	private WebDriver driver;
	
	public ProfileVerifierPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath="//button[@ng-click=\"accept(inapp.id)\"]")
	private WebElement acceptButton;
	
	@FindBy(xpath="//button[@ng-click=\"reject(inapp.id)\"]")
	private WebElement rejectButton;

	public void ensureCanAccept() {
		(new WebDriverWait(driver, 10))
		  .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-click=\"accept(inapp.id)\"]")));
	}

	public WebElement getAcceptButton() {
		return acceptButton;
	}
	public WebElement getRejectButton() {
		return rejectButton;
	}
	public int getReportedAdversListSize() {
		return driver.findElements(By.className("profile-listItem")).size();
	}
}
