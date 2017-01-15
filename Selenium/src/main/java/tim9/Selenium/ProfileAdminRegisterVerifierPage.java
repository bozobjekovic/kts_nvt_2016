package tim9.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileAdminRegisterVerifierPage {

	private WebDriver driver;
	
	public ProfileAdminRegisterVerifierPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(id = "email")
	private WebElement email;
	
	@FindBy(id = "username")
	private WebElement username;
	
	@FindBy(id = "verPassword")
	private WebElement verPassword;
	
	@FindBy(id = "regVerifierButton")
	private WebElement okButton;

	@FindBy(xpath = "//button[@ng-click=\"cancel()\"]")
	private WebElement cancelButton;

	public void ensureIsDisplayed() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}

	public WebElement getEmail() {
		return email;
	}
	public void setEmail(String value) {
		WebElement el = getEmail();
		el.clear();
		el.sendKeys(value);
	}
	public WebElement getUsername() {
		return username;
	}
	public void setUsername(String value) {
		WebElement el = getUsername();
		el.clear();
		el.sendKeys(value);
	}
	public WebElement getVerPassword() {
		return verPassword;
	}
	public void setVerPassword(String value) {
		WebElement el = getVerPassword();
		el.clear();
		el.sendKeys(value);
	}
	public WebElement getOkButton() {
		return okButton;
	}
	public WebElement getCancelButton() {
		return cancelButton;
	}
}
