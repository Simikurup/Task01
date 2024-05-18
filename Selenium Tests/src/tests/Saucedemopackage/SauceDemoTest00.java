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

public class SauceDemoTest00 {
private static final String CHECKOUT_COMPLETE_TEXT = "Your order has been dispatched";
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
private static final String STD_PASSWORD = "secret_sauce";
private static final String STD_USERNAME = "standard_user";
private static final String HOME_PAGE_URL = "https://www.saucedemo.com/";
private WebDriver driver;
//private WebDriverWait wait;

@BeforeMethod
public void SetUp() {
driver=new ChromeDriver();	
driver.manage().window().maximize();

}
@AfterMethod
public void TearDown() {
driver.quit();
}

@Test
public void purchaseSingleProductTest() {
	//Create object of the class for Login page.This will create a constructor in Login Page.
	LoginPage loginPage=new LoginPage(driver);
	loginPage.openLoginPage(HOME_PAGE_URL);
	Assert.assertEquals(loginPage.getUrl(),HOME_PAGE_URL );
	ProductsPage productsPage=loginPage.login(STD_USERNAME,STD_PASSWORD);
	Assert.assertEquals(productsPage.getUrl(), PRODUCTS_PAGE);
	
	Assert.assertTrue(productsPage.getProductTitle().startsWith(TITLE));
	Assert.assertTrue(productsPage.getProductDescription().startsWith(PRODUCT_DESC_LABEL));
	Assert.assertTrue(productsPage.getProductPrice()>0);
	
	productsPage.addProductToCart();
	Assert.assertEquals(productsPage.getNumberOfItemsInCart(),1);
		
	CartPage cartPage=productsPage.goToCart();
	Assert.assertEquals(cartPage.getUrl(), CART_PAGE);
	
	Assert.assertTrue(cartPage.getProductTitle().startsWith(TITLE));
	Assert.assertTrue(cartPage.getProductDescription().startsWith(PRODUCT_DESC_LABEL));
	Assert.assertTrue(cartPage.getProductPrice()>0);
	
	
	CheckoutStepOnePage checkoutStepOnePage =cartPage.checkout();
	Assert.assertEquals(checkoutStepOnePage.getUrl(), CHECHOUT_STEPONE);
	
	checkoutStepOnePage.enterUserInfo(FIRSTNAME,LASTNAME,POSTALCODE);
	CheckoutStepTwoPage checkoutSteptwoPage= checkoutStepOnePage.continueToNextPage();
	Assert.assertEquals(checkoutSteptwoPage.getUrl(), CHECKOUT_STEPTWO);
	
	Assert.assertTrue(checkoutSteptwoPage.getProductTitle().startsWith(TITLE));
	Assert.assertTrue(checkoutSteptwoPage.getProductDescription().startsWith(DESCRIPTION));
	Assert.assertTrue(checkoutSteptwoPage.getProductPrice()>0);
	Assert.assertTrue(checkoutSteptwoPage.getPaymentInfo().startsWith(PAYMENTINFO_TEXT));
	Assert.assertTrue(checkoutSteptwoPage.getShippingInfo().startsWith(SHIPPINGINFO_TEXT));
	Assert.assertTrue(checkoutSteptwoPage.getPriceTotal()>0);
	Assert.assertTrue(checkoutSteptwoPage.getTax()>0);
	Assert.assertTrue(checkoutSteptwoPage.getTotal()>0);
	
	CheckoutCompletePage completePage=checkoutSteptwoPage.finishWorkFlow();
	Assert.assertEquals(completePage.getUrl(), COMPLETE);
	Assert.assertTrue(completePage.getcheckOutCompleteText().startsWith(CHECKOUT_COMPLETE_TEXT));
	
}
}




