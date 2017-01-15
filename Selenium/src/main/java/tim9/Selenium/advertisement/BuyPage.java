package tim9.Selenium.advertisement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuyPage {
	
	@FindBy(xpath = "//figure[@id=\"2\"]")
	private WebElement advertisementLink;

	public WebElement getAdvertisementLink() {
		return advertisementLink;
	}
	
}
