package tim9.Selenium.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationUserPage {

	private WebDriver driver;
	
	@FindBy(xpath = "//a[text()=\"Clerk\"]")
	private WebElement clerkTab;
	
	@FindBy(xpath = "//form[@name='registrateUserForm']//input[@id='name']")
	private WebElement inputName;
	
	@FindBy(xpath = "//form[@name='registrateUserForm']//input[@id='surname']")
	private WebElement inputSurname;
	
	@FindBy(xpath = "//form[@name='registrateUserForm']//input[@id='email']")
	private WebElement inputEmail;
	
	@FindBy(xpath = "//form[@name='registrateUserForm']//input[@id='username']")
	private WebElement inputUsername;
	
	@FindBy(xpath = "//form[@name='registrateUserForm']//input[@id='password']")
	private WebElement inputPassword;
	
	@FindBy(xpath = "//form[@name='registrateUserForm']//input[@id='phonenumber']")
	private WebElement inputPhoneNumber;
	
	@FindBy(xpath = "//form[@name='registrateUserForm']//input[@id='address']")
	private WebElement inputAddress;
	
	@FindBy(xpath = "//form[@name='registrateUserForm']//input[@id='city']")
	private WebElement inputCity;
	
	@FindBy(xpath = "//form[@name='registrateUserForm']//input[@id='bankaccount']")
	private WebElement inputBankAccount;
	
	@FindBy(xpath = "//form[@name='registrateUserForm']//button[text()='OK']")
	private WebElement okButton;

	public RegistrationUserPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void ensureIsDisplayed() {
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//form[@name='registrateUserForm']//input[@id='bankaccount']")));
	}
	
	public WebElement getClerkTab() {
		return clerkTab;
	}

	public WebElement getInputName() {
		return inputName;
	}

	public void setInputName(String value) {
		WebElement el = getInputName();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputSurname() {
		return inputSurname;
	}

	public void setInputSurname(String value) {
		WebElement el = getInputSurname();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputEmail() {
		return inputEmail;
	}

	public void setInputEmail(String value) {
		WebElement el = getInputEmail();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputUsername() {
		return inputUsername;
	}

	public void setInputUsername(String value) {
		WebElement el = getInputUsername();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputPassword() {
		return inputPassword;
	}

	public void setInputPassword(String value) {
		WebElement el = getInputPassword();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputPhoneNumber() {
		return inputPhoneNumber;
	}

	public void setInputPhoneNumber(String value) {
		WebElement el = getInputPhoneNumber();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputAddress() {
		return inputAddress;
	}

	public void setInputAddress(String value) {
		WebElement el = getInputAddress();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputCity() {
		return inputCity;
	}

	public void setInputCity(String value) {
		WebElement el = getInputCity();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputBankAccount() {
		return inputBankAccount;
	}

	public void setInputBankAccount(String value) {
		WebElement el = getInputBankAccount();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getOkButton() {
		return okButton;
	}
	
}
