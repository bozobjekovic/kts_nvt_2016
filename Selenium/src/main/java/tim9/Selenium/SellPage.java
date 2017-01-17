package tim9.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SellPage {
	
	private WebDriver driver;
	
	public SellPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(name = "name")
	private WebElement name;
	
	@FindBy(name = "address")
	private WebElement address;
	
	@FindBy(name = "city")
	private WebElement city;
	
	@FindBy(name = "zipcode")
	private WebElement zipCode;
	
	@FindBy(name = "partofthecity")
	private WebElement partOfTheCity;

	@FindBy(name = "landsize")
	private WebElement landSize;
	
	@FindBy(name = "equipment")
	private WebElement equipment;
	
	@FindBy(name="heatingtype")
	private WebElement heatingType;
	
	@FindBy(name = "bathrooms")
	private WebElement bathrooms;
	
	@FindBy(name = "bedrooms")
	private WebElement bedrooms;
	
	@FindBy(name = "floors")
	private WebElement floors;
	
	@FindBy(name = "buildyear")
	private WebElement buildYear;
	
	@FindBy(name = "category")
	private WebElement category;
	
	@FindBy(name = "type")
	private WebElement type;
	
	@FindBy(name = "price")
	private WebElement price;
	
	@FindBy(name = "phonenumber")
	private WebElement phoneNumber;
	
	@FindBy(name = "purpose")
	private WebElement purpose;
	
	@FindBy(id = "submit")
	private WebElement submitButton;
	
	public void ensureIsDisplayed() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("name")));
	}
	public void ensureCanSubmit() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id("submit")));
	}

	public WebElement getName() {
		return name;
	}

	public void setName(String value) {
		WebElement el = getName();
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

	public WebElement getZipCode() {
		return zipCode;
	}

	public void setZipCode(String value) {
		WebElement el = getZipCode();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getPartOfTheCity() {
		return partOfTheCity;
	}

	public void setPartOfTheCity(String value) {
		WebElement el = getPartOfTheCity();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getLandSize() {
		return landSize;
	}

	public void setLandSize(String value) {
		WebElement el = getLandSize();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getEquipment() {
		return equipment;
	}

	public void setEquipment(String value) {
		WebElement el = getEquipment();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getHeatingType() {
		return heatingType;
	}

	public void setHeatingType(int value) {
		Select el = new Select(heatingType);
		el.selectByIndex(value);
	}

	public WebElement getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(String value) {
		WebElement el = getBathrooms();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(String value) {
		WebElement el = getBedrooms();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getFloors() {
		return floors;
	}

	public void setFloors(String value) {
		WebElement el = getFloors();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String value) {
		WebElement el = getBuildYear();
		el.clear();
		el.sendKeys(value);
	}

	public WebElement getCategory() {
		return category;
	}

	public void setCategory(int value) {
		Select el = new Select(category);
		el.selectByIndex(value);
	}

	public WebElement getType() {
		return type;
	}

	public void setType(int value) {
		Select el = new Select(type);
		el.selectByIndex(value);
	}

	public WebElement getPrice() {
		return price;
	}

	public void setPrice(String value) {
		WebElement el = getPrice();
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

	public WebElement getPurpose() {
		return purpose;
	}

	public void setPurpose(int value) {
		Select el = new Select(purpose);
		el.selectByIndex(value);
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}
}
