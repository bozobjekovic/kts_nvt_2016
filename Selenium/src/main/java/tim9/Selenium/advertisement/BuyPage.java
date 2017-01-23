package tim9.Selenium.advertisement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyPage {
	
	private WebDriver browser;
	
	public BuyPage(WebDriver browser) {
		this.browser = browser;
	}
	
	@FindBy(xpath = "//figure[@id=\"2\"]")
	private WebElement advertisementLink;
	
	@FindBy(xpath = "//a[@ng-click=\"setType('Room'); filterAdvertisements()\"]")
	private WebElement officeLink;
	
	@FindBy(xpath = "(//input[@ng-model='cityFilter[city]'])[1]")
	private WebElement cityLink;
	
	@FindBy(xpath = "(//input[@ng-model='cityFilter[city]'])[2]")
	private WebElement otherCityLink;
	
	private WebElement partOfTheCityLink;

	@FindBy(xpath = "//a[@ng-click='priceCollapsed=!priceCollapsed']")
	private WebElement priceColapse;

	@FindBy(xpath = "//input[@ng-model='filterForm.priceTo']")
	private WebElement priceTo;
	
	@FindBy(xpath = "//input[@ng-model='filterForm.priceFrom']")
	private WebElement priceFrom;
	
	@FindBy(id = "priceBtn")
	private WebElement priceFilter;
	
	@FindBy(xpath = "//a[@ng-click='landSizeCollapsed=!landSizeCollapsed']")
	private WebElement landSizeColapse;
	
	@FindBy(xpath = "//input[@ng-model='filterForm.landSizeTo']")
	private WebElement landSizeTo;
	
	@FindBy(xpath = "//input[@ng-model='filterForm.landSizeFrom']")
	private WebElement landSizeFrom;
	
	@FindBy(id = "landBtn")
	private WebElement landFilter;

	public WebElement getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(String priceTo) {
		WebElement el = getPriceTo();
		el.clear();
		el.sendKeys(priceTo);
	}

	public WebElement getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(String priceFrom) {
		WebElement el = getPriceFrom();
		el.clear();
		el.sendKeys(priceFrom);
	}

	public WebElement getLandSizeTo() {
		return landSizeTo;
	}

	public void setLandSizeTo(String landSizeTo) {
		WebElement el = getLandSizeTo();
		el.clear();
		el.sendKeys(landSizeTo);
	}

	public WebElement getLandSizeFrom() {
		return landSizeFrom;
	}

	public void setLandSizeFrom(String landSizeFrom) {
		WebElement el = getLandSizeFrom();
		el.clear();
		el.sendKeys(landSizeFrom);
	}

	public WebElement getAdvertisementLink() {
		return advertisementLink;
	}

	public WebElement getApartmentsLink() {
		return officeLink;
	}

	public WebElement getCityLink() {
		return cityLink;
	}

	public WebElement getPartOfTheCityLink() {
		return partOfTheCityLink;
	}

	public WebElement getPriceColapse() {
		return priceColapse;
	}

	public WebElement getLandSizeColapse() {
		return landSizeColapse;
	}

	public WebElement getPriceFilter() {
		return priceFilter;
	}

	public WebElement getLandFilter() {
		return landFilter;
	}

	public WebElement getOtherCityLink() {
		return otherCityLink;
	}

	public void ensureIsDisplayed() {
		(new WebDriverWait(browser, 2))
				  .until(ExpectedConditions.presenceOfElementLocated(
						  By.xpath("//div[@ng-show='priceCollapsed']")));
	}
	
	public int getFilterSize() {
		return browser.findElements(By.xpath("//figure[@ng-click='getAdvertisement(advertisement)']")).size();
	}
	
}
