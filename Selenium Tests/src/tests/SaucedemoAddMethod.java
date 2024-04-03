package tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SaucedemoAddMethod {
	private static final String POSTALCODE = "v4s 213";
	private static final String LASTNAME = "kurup";
	private static final String FIRSTNAME = "Simi";
	private static final String PASSWORD = "secret_sauce";
	private static final String USER_NAME = "standard_user";
	private static final String HOME_PAGE_URL = "https://www.saucedemo.com/";
	private ChromeDriver driver;
	private WebDriverWait wait;

	private static final By USERNAME_LOCATOR=By.id("user-name");
	private static final By PASSWORD_LOCATOR=By.id("password");
	private static final By LOGIN_BUTTON_LOCATOR=By.id("login-button");
	private static final By PRODUCT_TITLE_LOCATOR=By.id("item_4_title_link");
	private static final By PRODUCT_DESCRIPTION_LOCATOR=By.xpath("(//div[@class='inventory_item_desc'])[1]");
	private static final By PRODUCT_PRICE_LOCATOR=By.xpath("(//div[@class='inventory_item_price'])[1]");
	private static final By ADD_TOCART_LOCATOR=By.id("add-to-cart-sauce-labs-backpack");
	private static final By SHOPPING_CART_LOCATOR=By.xpath("//span[@class='shopping_cart_badge']");
	private static final By CHECKOUT_LOCATOR=By.id("checkout");
	private static final By FIRSTNAME_LOCATOR=By.id("first-name");
	private static final By LASTNAME_LOCATOR=By.id("last-name");
	private static final By POSTALCODE_LOCATOR=By.id("postal-code");
	private static final By CONTINUE_BUTTON_LOCATOR=By.id("continue");
	private static final By PAYMENTINFO_LOCATOR=By.xpath("(//div[@class='summary_value_label'])[1]");
	private static final By SHIPPINGINFO_LOCATOR=By.xpath("(//div[@class='summary_value_label'])[2]");
	private static final By ITEMTOTAL_LOCATOR=By.xpath("//div[@class='summary_subtotal_label']");
	private static final By TAX_LOCATOR=By.xpath("//div[@class='summary_tax_label']");
	private static final By TOTAL_LOCATOR=By.xpath("//div[@class='summary_total_label']");
	private static final By FINISH_LOCATOR=By.id("finish");
	private static final By CHECKOUT_DESCRIPTION_LOCATOR=By.xpath("//div[@class='complete-text']");


	//1.Login as standard user
	//2.Verify there are 6 products in the page
	//3.For first product, verify Title and description are not empty and price is positive
	//4.Add to cart
	//5.Cart should show 1
	//6.Click on Cart
	//7.On cart page verify Title and description are not empty and price is positive
	//8.Checkout
	//9.Check out Step1:Enter First name,Last name,postal code,Click continue
	//10.Check out Step2:Verify all the details are present ,Finish
	//11.Checkout complete:Verify Title




	@BeforeMethod
	public void setUp() {
	driver=new ChromeDriver();
	wait= new WebDriverWait(driver,Duration.ofSeconds(30));
	driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}

	@Test
	public void shopping() throws InterruptedException {
		homePageUrl(HOME_PAGE_URL );
		Assert.assertEquals(getHomePageUrl(), HOME_PAGE_URL);
		
		customerUsername(USER_NAME);
		
		customerPassword(PASSWORD);
		
		customerLogin();
		
		//Verify there are 6 products in the page
	/*	By inventorylist_Locator=By.xpath("//div[@class='inventory_list']");//2
		List <WebElement>Inventory=driver.findElements(inventorylist_Locator);
		int size=Inventory.size();
		System.out.println(size);*/
		
		
		Assert.assertTrue(getProductTitle().startsWith("Sauce Labs"));
		Assert.assertTrue(getProductDescription().startsWith("carry.allTheThings()"));
		Assert.assertTrue(getProductPrice()>0);
		
		addProductToCart();
			
		Assert.assertTrue(getNumberOfItemsInCart()==1);	
		
		goToShoppingCart();
		
		Assert.assertTrue(getProductTitle().startsWith("Sauce Labs"));
		Assert.assertTrue(getProductDescription().startsWith("carry.allTheThings()"));
		Assert.assertTrue(getProductPrice()>0);
		
		checkout();
		
		
		checkOutFirstName(FIRSTNAME);
		checkOutLastName(LASTNAME);
		checkOutPostcode(POSTALCODE);
		checkOutContinue();
		
		Assert.assertTrue(getProductTitle().startsWith("Sauce Labs"));
		Assert.assertTrue(getProductDescription().startsWith("carry.allTheThings()"));
		Assert.assertTrue(getProductPrice()>0);
	
		
		Assert.assertTrue(getPaymentInfo().startsWith("SauceCard"));
		Assert.assertTrue(getShippingInfo().startsWith("Free Pony"));
		Assert.assertTrue(getPriceTotal()>0);
		Assert.assertTrue(getTax()>0);
		Assert.assertTrue(getTotal()>0);
		finishCheckOut();
			
		Assert.assertTrue(getcheckOutompleteText().startsWith("Your order"));
	
		
		
	}
private void homePageUrl(String url) {
	driver.get(url);
}

private String getHomePageUrl() {
	String currentUrl=driver.getCurrentUrl();
	return currentUrl;
}

private void customerUsername(String uname) {
	wait.until(ExpectedConditions.elementToBeClickable(USERNAME_LOCATOR));
	WebElement usernameLabel=driver.findElement(USERNAME_LOCATOR);
	usernameLabel.sendKeys(uname);
}
	
private void customerPassword(String password) {	
	wait.until(ExpectedConditions.elementToBeClickable(PASSWORD_LOCATOR));
	WebElement passwordLabel=driver.findElement(PASSWORD_LOCATOR);
	passwordLabel.sendKeys(password);
}	
private void customerLogin(){	
	wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON_LOCATOR));
	WebElement loginButton=driver.findElement(LOGIN_BUTTON_LOCATOR);
	loginButton.click();
}
private String getProductTitle() {
	WebElement product_Title=driver.findElement(PRODUCT_TITLE_LOCATOR);
	String title=product_Title.getText();
	return title;
	
}

private String getProductDescription() {
WebElement product_Description=driver.findElement(PRODUCT_DESCRIPTION_LOCATOR);
String description=product_Description.getText();
return description;
}

private double getProductPrice() {
WebElement product_Price=driver.findElement(PRODUCT_PRICE_LOCATOR);
String priceValue=product_Price.getText();
String priceReplace$=priceValue.replace("$", "");
double price=Double.parseDouble(priceReplace$);
return price;
}
private void addProductToCart() {
	wait.until(ExpectedConditions.elementToBeClickable(ADD_TOCART_LOCATOR));
	WebElement addToCart = driver.findElement(ADD_TOCART_LOCATOR);
	addToCart.click();
}
private int getNumberOfItemsInCart() {
	WebElement shoppingCart=driver.findElement(SHOPPING_CART_LOCATOR);
	String cartValue=shoppingCart.getText();
	int cartItems=Integer.parseInt(cartValue);
	return cartItems;
	}
private void goToShoppingCart() {
	WebElement shoppingCart=driver.findElement(SHOPPING_CART_LOCATOR);
	shoppingCart.click();
}
private void checkout() {
	WebElement checkout=driver.findElement(CHECKOUT_LOCATOR);
	checkout.click();
}
private void checkOutFirstName(String firstname) {
	WebElement firstName =driver.findElement(FIRSTNAME_LOCATOR);
	firstName.sendKeys(firstname);
}
private void checkOutLastName(String lastname) {
WebElement lastName =driver.findElement(LASTNAME_LOCATOR);
lastName.sendKeys(lastname);
}
private void checkOutPostcode(String postcode) {
WebElement postCode =driver.findElement(POSTALCODE_LOCATOR);
postCode.sendKeys(postcode);
}
private void checkOutContinue() {
WebElement continueButton =driver.findElement(CONTINUE_BUTTON_LOCATOR);
continueButton.click();
}

private String getPaymentInfo() {
	WebElement paymentInfo=driver.findElement(PAYMENTINFO_LOCATOR);
	String paymentDetails=paymentInfo.getText();
	return paymentDetails;
	}
private String getShippingInfo() {
	WebElement shippingInfo=driver.findElement(SHIPPINGINFO_LOCATOR);
	String shippingDetails=shippingInfo.getText();
	return shippingDetails;
	}
private double getPriceTotal() {
	WebElement itemTotal=driver.findElement(ITEMTOTAL_LOCATOR);
	String itemValue=itemTotal.getText();
	String itemValueReplace$=itemValue.replace("Item total: $", "");
	double totalValue=Double.parseDouble(itemValueReplace$);
	return totalValue;
}
private double getTax() {
	WebElement tax=driver.findElement(TAX_LOCATOR);
	String taxValue=tax.getText();
	String taxValueReplace$=taxValue.replace("Tax: $", "");
	double taxToPay=Double.parseDouble(taxValueReplace$);
	return taxToPay;
	}
private double getTotal() {
	WebElement total=driver.findElement(TOTAL_LOCATOR);
	String totalVal=total.getText();
	String totalValReplace$=totalVal.replace("Total: $", "");
	double totalToPay=Double.parseDouble(totalValReplace$);
	return totalToPay;
}
private void finishCheckOut() {
	WebElement finishButton=driver.findElement(FINISH_LOCATOR);
	finishButton.click();
}
private String getcheckOutompleteText() {
WebElement checkOut=driver.findElement(CHECKOUT_DESCRIPTION_LOCATOR);
String checkOutText=checkOut.getText();
return checkOutText;
}

}
