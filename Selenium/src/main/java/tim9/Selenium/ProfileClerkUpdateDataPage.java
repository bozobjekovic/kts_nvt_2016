package tim9.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileClerkUpdateDataPage {

	private WebDriver driver;
	
	public ProfileClerkUpdateDataPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(id = "name")
	private WebElement name;
	
	@FindBy(id = "surname")
	private WebElement surname;
	
	@FindBy(id = "phoneNumber")
	private WebElement phoneNumber;

	@FindBy(id = "address")
	private WebElement address;

	@FindBy(id = "city")
	private WebElement city;

	@FindBy(id = "save")
	private WebElement saveButton;

	@FindBy(xpath = "//button[@ng-click=\"cancel()\"]")
	private WebElement cancelButton;

	public void ensureIsDisplayed() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
	}

	public WebElement getName() {
		return name;
	}
	public void setName(String value) {
		WebElement el = getName();
		el.clear();
		el.sendKeys(value);
	}
	public WebElement getSurname() {
		return surname;
	}
	public void setSurname(String value) {
		WebElement el = getSurname();
		el.clear();
		el.sendKeys(value);
	}
	public WebElement getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String value) {
		WebElement el = getPhoneNumber();
		el.clear();
		el.sendKeys(value);
	}
	public WebElement getAddress() {
		return address;
	}
	public void setAddress(String value) {
		WebElement el = getAddress();
		el.clear();
		el.sendKeys(value);
	}
	public WebElement getCity() {
		return city;
	}
	public void setCity(String value) {
		WebElement el = getCity();
		el.clear();
		el.sendKeys(value);
	}
	public WebElement getSaveButton() {
		return saveButton;
	}
	public WebElement getCancelButton() {
		return cancelButton;
	}
}
