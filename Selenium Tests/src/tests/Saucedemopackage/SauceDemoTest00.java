package tests.Saucedemopackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import tests.SauceDemoLogin;
import tests.SauceDemoProducts;

public class SauceDemoTest00 {
private static final String SHIPPINGINFO_TEXT = "Free Pony";
private static final String PAYMENTINFO_TEXT = "SauceCard";
private static final String DESCRIPTION = "carry.allTheThings()";
private static final String COMPLETE = "https://www.saucedemo.com/checkout-complete.html";
private static final String CHECKOUT_STEPTWO = "https://www.saucedemo.com/checkout-step-two.html";
private static final String CHECHOUT_STEPONE = "https://www.saucedemo.com/checkout-step-one.html";
private static final String PRODUCTS_PAGE = "https://www.saucedemo.com/inventory.html";
private static final String CART_PAGE ="https://www.saucedemo.com/cart.html";
private static final String PRODUCT_DESC_LABEL = "carry.allTheThings() with the sleek, ";
private static final String TITLE = "Sauce Labs";
private static final String POSTALCODE = "v4s 213";
private static final String LASTNAME = "kurup";
private static final String FIRSTNAME = "Simi";


private WebDriver driver;
private WebDriverWait wait;

private static final String STD_PASSWORD = "secret_sauce";
private static final String STD_USERNAME = "standard_user";

@BeforeMethod
public void SetUp() {
driver=new ChromeDriver();	
wait= new WebDriverWait(driver,Duration.ofSeconds(30));
driver.manage().window().maximize();

}
@AfterMethod
public void TearDown() {
driver.quit();
}
@Test
public void standardUserLogin() {
	SauceDemoLogin login=new SauceDemoLogin(driver,wait);
	login.saucedemoOpenHomepage();
	Assert.assertEquals(login.getHomePageUrl(),SauceDemoLogin.HOME_PAGE_URL );
	SauceDemoProducts products=login.sauceDemologin(STD_USERNAME,STD_PASSWORD);
	Assert.assertEquals(driver.getCurrentUrl(), PRODUCTS_PAGE);
}
@Test
public void verifyProduct() {
	SauceDemoLogin login=new SauceDemoLogin(driver,wait);
	login.saucedemoOpenHomepage();
	SauceDemoProducts products=login.sauceDemologin(STD_USERNAME,STD_PASSWORD);
	products.verifyproductDetails();
	Assert.assertTrue(products.getProductTitle().startsWith(TITLE));
	Assert.assertTrue(products.getProductDescription().startsWith(PRODUCT_DESC_LABEL));
	Assert.assertTrue(products.getProductPrice()>0);	
}
@Test
public void addToCart() {
	SauceDemoLogin login=new SauceDemoLogin(driver,wait);
	login.saucedemoOpenHomepage();
	SauceDemoProducts products=login.sauceDemologin(STD_USERNAME,STD_PASSWORD);
	products.verifyproductDetails();
	products.addProductToCart();
	products.getNumberOfItemsInCart();
	Assert.assertEquals(products.getNumberOfItemsInCart(),1);
}
@Test
public void clickCart() {
	SauceDemoLogin login=new SauceDemoLogin(driver,wait);
	login.saucedemoOpenHomepage();
	SauceDemoProducts products=login.sauceDemologin(STD_USERNAME,STD_PASSWORD);
	products.verifyproductDetails();
	products.addProductToCart();
	products.getNumberOfItemsInCart();
	SauceDemoCartPage cart=products.clickCart();
	Assert.assertEquals(driver.getCurrentUrl(), CART_PAGE);
}	
@Test
public void verifyCart() {
		SauceDemoLogin login=new SauceDemoLogin(driver,wait);
		login.saucedemoOpenHomepage();
		SauceDemoProducts products=login.sauceDemologin(STD_USERNAME,STD_PASSWORD);
		products.verifyproductDetails();
		products.addProductToCart();
		products.getNumberOfItemsInCart();
		SauceDemoCartPage cart=products.clickCart();
		cart.verifyproductDetails();
		Assert.assertTrue(cart.getProductTitle().startsWith(TITLE));
		Assert.assertTrue(cart.getProductDescription().startsWith(PRODUCT_DESC_LABEL));
		Assert.assertTrue(cart.getProductPrice()>0);
		
	}
@Test
public void checkout() {
	SauceDemoLogin login=new SauceDemoLogin(driver,wait);
	login.saucedemoOpenHomepage();
	SauceDemoProducts products=login.sauceDemologin(STD_USERNAME,STD_PASSWORD);
	products.verifyproductDetails();
	products.addProductToCart();
	products.getNumberOfItemsInCart();
	SauceDemoCartPage cart=products.clickCart();
	cart.verifyproductDetails();
	SauceDemoCheckoutStepOne checkoutStepOne =cart.checkout();
	Assert.assertEquals(driver.getCurrentUrl(), CHECHOUT_STEPONE);
	
}

@Test
public void checkoutStepOne() {
	SauceDemoLogin login=new SauceDemoLogin(driver,wait);
	login.saucedemoOpenHomepage();
	SauceDemoProducts products=login.sauceDemologin(STD_USERNAME,STD_PASSWORD);
	products.verifyproductDetails();
	products.addProductToCart();
	products.getNumberOfItemsInCart();
	SauceDemoCartPage cart=products.clickCart();
	cart.verifyproductDetails();
	SauceDemoCheckoutStepOne checkoutStepOne =cart.checkout();
	checkoutStepOne.checkoutStepFirst(FIRSTNAME,LASTNAME,POSTALCODE);
	SauceDemoCheckoutStepTwo checkoutSteptwo= checkoutStepOne.clickContinue();
	Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_STEPTWO);
}
@Test
public void checkoutStepTwo() {
	SauceDemoLogin login=new SauceDemoLogin(driver,wait);
	login.saucedemoOpenHomepage();
	SauceDemoProducts products=login.sauceDemologin(STD_USERNAME,STD_PASSWORD);
	products.verifyproductDetails();
	products.addProductToCart();
	products.getNumberOfItemsInCart();
	SauceDemoCartPage cart=products.clickCart();
	cart.verifyproductDetails();
	SauceDemoCheckoutStepOne checkoutStepOne =cart.checkout();
	checkoutStepOne.checkoutStepFirst(FIRSTNAME,LASTNAME,POSTALCODE);
	SauceDemoCheckoutStepTwo checkoutSteptwo= checkoutStepOne.clickContinue();
	Assert.assertTrue(checkoutSteptwo.getProductTitle().startsWith(TITLE));
	Assert.assertTrue(checkoutSteptwo.getProductDescription().startsWith(DESCRIPTION));
	Assert.assertTrue(checkoutSteptwo.getProductPrice()>0);
	Assert.assertTrue(checkoutSteptwo.getPaymentInfo().startsWith(PAYMENTINFO_TEXT));
	Assert.assertTrue(checkoutSteptwo.getShippingInfo().startsWith(SHIPPINGINFO_TEXT));
	Assert.assertTrue(checkoutSteptwo.getPriceTotal()>0);
	Assert.assertTrue(checkoutSteptwo.getTax()>0);
	Assert.assertTrue(checkoutSteptwo.getTotal()>0);
	SauceDemoCheckoutComplete complete=checkoutSteptwo.finish();
	Assert.assertEquals(driver.getCurrentUrl(), COMPLETE);
	
}
@Test
public void checkoutComplete() {
	SauceDemoLogin login=new SauceDemoLogin(driver,wait);
	login.saucedemoOpenHomepage();
	SauceDemoProducts products=login.sauceDemologin(STD_USERNAME,STD_PASSWORD);
	products.verifyproductDetails();
	products.addProductToCart();
	products.getNumberOfItemsInCart();
	SauceDemoCartPage cart=products.clickCart();
	cart.verifyproductDetails();
	SauceDemoCheckoutStepOne checkoutStepOne =cart.checkout();
	checkoutStepOne.checkoutStepFirst(FIRSTNAME,LASTNAME,POSTALCODE);
	SauceDemoCheckoutStepTwo checkoutSteptwo= checkoutStepOne.clickContinue();
	SauceDemoCheckoutComplete complete=checkoutSteptwo.finish();
	complete.getcheckOutompleteText();
}
}




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
