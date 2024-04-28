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
driver.manage().window().maximize();

}
@AfterMethod
public void TearDown() {
driver.quit();
}

@Test
public void purchaseSingleProduct() {
	LoginPage log=new LoginPage(driver);
	log.openHomePage();
	Assert.assertEquals(log.getHomePageUrl(),LoginPage.HOME_PAGE_URL );
	ProductsPage products=log.login(STD_USERNAME,STD_PASSWORD);
	Assert.assertEquals(driver.getCurrentUrl(), PRODUCTS_PAGE);
	
	products.getProductTitle();
	products.getProductDescription();
	products.getProductPrice();
	Assert.assertTrue(products.getProductTitle().startsWith(TITLE));
	Assert.assertTrue(products.getProductDescription().startsWith(PRODUCT_DESC_LABEL));
	Assert.assertTrue(products.getProductPrice()>0);
	
	products.addProductToCart();
	products.getNumberOfItemsInCart();
	Assert.assertEquals(products.getNumberOfItemsInCart(),1);
		
	CartPage cart=products.goToCart();
	Assert.assertEquals(driver.getCurrentUrl(), CART_PAGE);
	
	cart.getProductTitle();
	cart.getProductDescription();
	cart.getProductPrice();
	Assert.assertTrue(cart.getProductTitle().startsWith(TITLE));
	Assert.assertTrue(cart.getProductDescription().startsWith(PRODUCT_DESC_LABEL));
	Assert.assertTrue(cart.getProductPrice()>0);
	
	
	CheckoutStepOnePage checkoutStepOne =cart.checkout();
	Assert.assertEquals(driver.getCurrentUrl(), CHECHOUT_STEPONE);
	
	checkoutStepOne.enterUserInfo(FIRSTNAME,LASTNAME,POSTALCODE);
	CheckoutStepTwoPage checkoutSteptwo= checkoutStepOne.continueToNextPage();
	Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_STEPTWO);
	
	checkoutSteptwo.getProductTitle();
	checkoutSteptwo.getProductDescription();
	checkoutSteptwo.getProductPrice();
	checkoutSteptwo.getPaymentInfo();
	checkoutSteptwo.getShippingInfo();
	checkoutSteptwo.getPriceTotal();
	checkoutSteptwo.getTax();
	checkoutSteptwo.getTotal();
	Assert.assertTrue(checkoutSteptwo.getProductTitle().startsWith(TITLE));
	Assert.assertTrue(checkoutSteptwo.getProductDescription().startsWith(DESCRIPTION));
	Assert.assertTrue(checkoutSteptwo.getProductPrice()>0);
	Assert.assertTrue(checkoutSteptwo.getPaymentInfo().startsWith(PAYMENTINFO_TEXT));
	Assert.assertTrue(checkoutSteptwo.getShippingInfo().startsWith(SHIPPINGINFO_TEXT));
	Assert.assertTrue(checkoutSteptwo.getPriceTotal()>0);
	Assert.assertTrue(checkoutSteptwo.getTax()>0);
	Assert.assertTrue(checkoutSteptwo.getTotal()>0);
	
	CheckoutCompletePage complete=checkoutSteptwo.finishWorkFlow();
	Assert.assertEquals(driver.getCurrentUrl(), COMPLETE);
	complete.getcheckOutompleteText();
}
}




