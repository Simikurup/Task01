package tests.Saucedemopackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import tests.Saucedemopackage.SauceDemoCartPage;

public class SauceDemoProducts {

private WebDriver driver;
private WebDriverWait wait;

private static final By PRODUCTNAME_LOCATOR=By.xpath("(//div[@class='inventory_item_name '])[1]");
private static final By PRODUCT_DESCRIPTION_LOCATOR=By.xpath("(//div[@class='inventory_item_desc'])[1]");
private static final By PRODUCT_PRICE_LOCATOR=By.xpath("(//div[@class='inventory_item_price'])[1]");
private static final By ADD_TO_CART=By.id("add-to-cart-sauce-labs-backpack");
private static final By CART_ITEMS_LOCATOR=By.xpath("//span[@class='shopping_cart_badge']");

public SauceDemoProducts(WebDriver driver, WebDriverWait wait) {
	this.driver=driver;
	this.wait= wait;
}

public void verifyproductDetails() {
	getProductTitle();
	getProductDescription();
	getProductPrice();
	
}

public SauceDemoCartPage clickCart() {
	goToCart();
	return new SauceDemoCartPage (this.driver,this.wait);
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
public void addProductToCart() {
	this.wait.until(ExpectedConditions.elementToBeClickable(ADD_TO_CART));
	WebElement addToCart=this.driver.findElement(ADD_TO_CART);
	addToCart.click();
}
public int getNumberOfItemsInCart() {
this.wait.until(ExpectedConditions.visibilityOfElementLocated(CART_ITEMS_LOCATOR));
WebElement totalCartLabel=this.driver.findElement(CART_ITEMS_LOCATOR);
String totalCartValue=totalCartLabel.getText();
int cartValue=Integer.parseInt(totalCartValue);
return cartValue;
}
private void goToCart() {
	this.wait.until(ExpectedConditions.elementToBeClickable(CART_ITEMS_LOCATOR));
	WebElement totalCartLabel=this.driver.findElement(CART_ITEMS_LOCATOR);
	totalCartLabel.click();
}
}
