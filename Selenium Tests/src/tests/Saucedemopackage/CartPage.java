package tests.Saucedemopackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private static final By PRODUCTNAME_LOCATOR=By.xpath("//div[@class='inventory_item_name']");
	private static final By PRODUCT_DESCRIPTION_LOCATOR=By.xpath("//div[@class='inventory_item_desc']");
	private static final By PRODUCT_PRICE_LOCATOR=By.xpath("//div[@class='inventory_item_price']");
	private static final By CHECKOUT_LOCATOR=By.id("checkout");
	
	public CartPage(WebDriver driver) {
		this.driver=driver;
		this.wait= new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	
	public String getUrl() {
		String currentUrl=this.driver.getCurrentUrl();
		return currentUrl;
	}

	public CheckoutStepOnePage checkout() {
		clickCheckOut();
		return new CheckoutStepOnePage(this.driver);
	}
	public String getProductTitle() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTNAME_LOCATOR));
		WebElement productTitleLabel=this.driver.findElement(PRODUCTNAME_LOCATOR);
		return productTitleLabel.getText();
		
	}
	public String getProductDescription() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_DESCRIPTION_LOCATOR));
		WebElement productDescLabel=this.driver.findElement(PRODUCT_DESCRIPTION_LOCATOR);
		return productDescLabel.getText();
	}
	public double getProductPrice() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_PRICE_LOCATOR));
		WebElement productPriceText=this.driver.findElement(PRODUCT_PRICE_LOCATOR);
		String PriceValue= productPriceText.getText();
		String PriceValueReplace$=PriceValue.replace("$", "");
		double productPrice=Double.parseDouble(PriceValueReplace$);
		return productPrice;
	}
	
	private void clickCheckOut() {
	this.wait.until(ExpectedConditions.elementToBeClickable(CHECKOUT_LOCATOR));
	WebElement checkoutButton=this.driver.findElement(CHECKOUT_LOCATOR);
	checkoutButton.click();
	}
}
