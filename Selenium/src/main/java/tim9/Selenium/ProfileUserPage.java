package tim9.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileUserPage {

	private WebDriver driver;
	
	public ProfileUserPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath="//button[@ng-click=\"openModal()\"]")
	private WebElement changeButton;

	@FindBy(xpath="//button[@ng-click=\"apply(company.id)\"]")
	private WebElement askToJoinButton;

	@FindBy(id="removeAdver")
	private WebElement removeButton;

	public void ensureIsDisplayed() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@ng-click=\"openModal()\"]")));
	}
	public void ensureCanAskToJoin() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@ng-click=\"apply(company.id)\"]")));
	}
	public void ensureCanRemove() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("removeAdver")));
	}
	
	public WebElement getChangeButton() {
		return changeButton;
	}
	public WebElement getAskToJoinButton() {
		return askToJoinButton;
	}
	public WebElement getRemoveButton() {
		return removeButton;
	}
	public int getAdverListSize() {
		return driver.findElements(By.className("profile-listItem")).size();
	}
}
