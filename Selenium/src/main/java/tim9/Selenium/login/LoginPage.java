package tim9.Selenium.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
private WebDriver browser;
	
	public LoginPage(WebDriver browser) {
		this.browser = browser;
	}
	
	@FindBy(id = "username")
	private WebElement inputUsername;
	
	@FindBy(id = "password")
	private WebElement inputPassword;
	
	@FindBy(className = "toast-message")
	private WebElement toastr;
	
	@FindBy(xpath = "//button[text()=\"OK\"]")
	private WebElement OKButton;

	public WebElement getInputUsername() {
		return inputUsername;
	}

	public void setInputUsername(String inputUsername) {
		WebElement el = getInputUsername();
		el.clear();
		el.sendKeys(inputUsername);
	}

	public WebElement getInputPassword() {
		return inputPassword;
	}

	public void setInputPassword(String inputPassword) {
		WebElement el = getInputPassword();
		el.clear();
		el.sendKeys(inputPassword);
	}
	
	public WebElement getToastr() {
		return toastr;
	}

	public WebElement getOKButton() {
		return OKButton;
	}
	
	public void ensureIsDisplayed() {
		(new WebDriverWait(browser, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(
						  By.id("username")));
	}


}
