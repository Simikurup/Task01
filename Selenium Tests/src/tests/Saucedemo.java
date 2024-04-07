package tests;

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

public class Saucedemo {
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
	driver.get(HOME_PAGE_URL );
	Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL);
	
	wait.until(ExpectedConditions.elementToBeClickable(USERNAME_LOCATOR));//1
	WebElement usernameLabel=driver.findElement(USERNAME_LOCATOR);
	usernameLabel.sendKeys(USER_NAME);
	
	wait.until(ExpectedConditions.elementToBeClickable(PASSWORD_LOCATOR));
	WebElement passwordLabel=driver.findElement(PASSWORD_LOCATOR);
	passwordLabel.sendKeys(PASSWORD);
	
	wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON_LOCATOR));
	WebElement loginButton=driver.findElement(LOGIN_BUTTON_LOCATOR);
	loginButton.click();
	
	//Verify there are 6 products in the page
/*	By inventorylist_Locator=By.xpath("//div[@class='inventory_list']");//2
	List <WebElement>Inventory=driver.findElements(inventorylist_Locator);
	int size=Inventory.size();
	System.out.println(size);*/
	
	
	WebElement product_Title=driver.findElement(PRODUCT_TITLE_LOCATOR);//3
	Assert.assertTrue(product_Title.isDisplayed());
	
	
	WebElement product_Description=driver.findElement(PRODUCT_DESCRIPTION_LOCATOR);
	Assert.assertTrue(product_Description.isDisplayed());
	
	
	WebElement product_Price=driver.findElement(PRODUCT_PRICE_LOCATOR);
	String priceValue=product_Price.getText();
	String priceReplace$=priceValue.replace("$", "");
	double price=Double.parseDouble(priceReplace$);
	Assert.assertTrue(price>0);
	
	wait.until(ExpectedConditions.elementToBeClickable(ADD_TOCART_LOCATOR));//4
	WebElement addToCart = driver.findElement(ADD_TOCART_LOCATOR);
	addToCart.click();
		
	WebElement shoppingCart=driver.findElement(SHOPPING_CART_LOCATOR);//5
	String cartValue=shoppingCart.getText();
	int cartItems=Integer.parseInt(cartValue);
	Assert.assertTrue(cartItems==1);
	
	shoppingCart.click();//6
	
	
	WebElement product_TitleCart=driver.findElement(PRODUCT_TITLE_LOCATOR);//7
	Assert.assertTrue(product_TitleCart.isDisplayed());
	
	WebElement product_DescriptionCart=driver.findElement(PRODUCT_DESCRIPTION_LOCATOR);
	Assert.assertTrue(product_DescriptionCart.isDisplayed());
	
	
	WebElement product_PriceCart=driver.findElement(PRODUCT_PRICE_LOCATOR);
	String priceValueCart=product_PriceCart.getText();
	String priceReplace$Cart=priceValueCart.replace("$", "");
	double priceCart=Double.parseDouble(priceReplace$Cart);
	Assert.assertTrue(priceCart>0);
	
	WebElement checkout=driver.findElement(CHECKOUT_LOCATOR);//8
	checkout.click();
	
	
	WebElement firstName =driver.findElement(FIRSTNAME_LOCATOR);//9
	firstName.sendKeys(FIRSTNAME);
	
	WebElement lastName =driver.findElement(LASTNAME_LOCATOR);
	lastName.sendKeys(LASTNAME);
	
	WebElement postCode =driver.findElement(POSTALCODE_LOCATOR);
	postCode.sendKeys(POSTALCODE);
	
	
	WebElement continueButton =driver.findElement(CONTINUE_BUTTON_LOCATOR);
	continueButton.click();
		
	WebElement product_TitleCheckout=driver.findElement(PRODUCT_TITLE_LOCATOR);//10
	Assert.assertTrue(product_TitleCheckout.isDisplayed());
	
	WebElement product_DescriptionCartCheckout=driver.findElement(PRODUCT_DESCRIPTION_LOCATOR);
	Assert.assertTrue(product_DescriptionCartCheckout.isDisplayed());
	
	
	WebElement product_PriceCheckout=driver.findElement(PRODUCT_PRICE_LOCATOR);
	String priceValueCheckout=product_PriceCheckout.getText();
	String priceReplace$Checkout=priceValueCheckout.replace("$", "");
	double priceCheckOut=Double.parseDouble(priceReplace$Checkout);
	Assert.assertTrue(priceCheckOut>0);
	
	WebElement paymentInfo=driver.findElement(PAYMENTINFO_LOCATOR);
	Assert.assertTrue(paymentInfo.isDisplayed());
	
	WebElement shippingInfo=driver.findElement(SHIPPINGINFO_LOCATOR);
	Assert.assertTrue(shippingInfo.isDisplayed());
	
	WebElement itemTotal=driver.findElement(ITEMTOTAL_LOCATOR);
	String itemValue=itemTotal.getText();
	String itemValueReplace$=itemValue.replace("Item total: $", "");
	System.out.println(itemValueReplace$);
	double totalValue=Double.parseDouble(itemValueReplace$);
	Assert.assertTrue(totalValue>0);
	Thread.sleep(5000);
	
	WebElement tax=driver.findElement(TAX_LOCATOR);
	String taxValue=tax.getText();
	String taxValueReplace$=taxValue.replace("Tax: $", "");
	double taxToPay=Double.parseDouble(taxValueReplace$);
	Assert.assertTrue(taxToPay>0);
	
	WebElement total=driver.findElement(TOTAL_LOCATOR);
	String totalVal=total.getText();
	String totalValReplace$=totalVal.replace("Total: $", "");
	double totalToPay=Double.parseDouble(totalValReplace$);
	Assert.assertTrue(totalToPay>0);
	
	WebElement finishButton=driver.findElement(FINISH_LOCATOR);
	finishButton.click();
		
	WebElement checkOut=driver.findElement(CHECKOUT_DESCRIPTION_LOCATOR);//11
	Assert.assertTrue(checkOut.isDisplayed());
	
	
	
}


}