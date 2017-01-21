package tim9.Selenium.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationClerkPage {
	
	private WebDriver driver;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='name']")
	private WebElement inputName;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='surname']")
	private WebElement inputSurname;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='email']")
	private WebElement inputEmail;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='username']")
	private WebElement inputUsername;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='password']")
	private WebElement inputPassword;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='phonenumber']")
	private WebElement inputPhoneNumber;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='address']")
	private WebElement inputAddress;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='city']")
	private WebElement inputCity;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='bankaccount']")
	private WebElement inputBankAccount;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='companyname']")
	private WebElement inputCompanyName;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='companyphone']")
	private WebElement inputCompanyPhone;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='companysite']")
	private WebElement inputCompanySite;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='companyaddress']")
	private WebElement inputCompanyAddress;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='companycity']")
	private WebElement inputCompanyCity;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='zipcode']")
	private WebElement inputCompanyZipCode;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//input[@id='partCity']")
	private WebElement inputCompanyPartOfTheCity;
	
	@FindBy(xpath = "//form[@name='registrateClerkForm']//button[text()='OK']")
	private WebElement okButton;
	
	public RegistrationClerkPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void ensureIsDisplayed() {
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//form[@name='registrateUserForm']//input[@id='bankaccount']")));
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

	public WebElement getInputCompanyName() {
		return inputCompanyName;
	}

	public void setInputCompanyName(String value) {
		WebElement el = getInputCompanyName();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputCompanyPhone() {
		return inputCompanyPhone;
	}

	public void setInputCompanyPhone(String value) {
		WebElement el = getInputCompanyPhone();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputCompanySite() {
		return inputCompanySite;
	}

	public void setInputCompanySite(String value) {
		WebElement el = getInputCompanySite();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputCompanyAddress() {
		return inputCompanyAddress;
	}

	public void setInputCompanyAddress(String value) {
		WebElement el = getInputCompanyAddress();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputCompanyCity() {
		return inputCompanyCity;
	}

	public void setInputCompanyCity(String value) {
		WebElement el = getInputCompanyCity();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputCompanyZipCode() {
		return inputCompanyZipCode;
	}

	public void setInputCompanyZipCode(String value) {
		WebElement el = getInputCompanyZipCode();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getInputCompanyPartOfTheCity() {
		return inputCompanyPartOfTheCity;
	}

	public void setInputCompanyPartOfTheCity(String value) {
		WebElement el = getInputCompanyPartOfTheCity();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getOkButton() {
		return okButton;
	}
	
}
