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
	private static final String SHIPPINGINFO_TEXT = "Free Pony";
	private static final String PAYMENTINFO_TEXT = "SauceCard";
	private static final String CHECKOUT_COMPLETE = "https://www.saucedemo.com/checkout-complete.html";
	private static final String CHECKOUT_STEPTWO = "https://www.saucedemo.com/checkout-step-two.html";
	private static final String CHECKOUT_STEPONE = "https://www.saucedemo.com/checkout-step-one.html";
	private static final String SHOPPINGCART_PAGE = "https://www.saucedemo.com/cart.html";
	private static final String DESCRIPTION = "carry.allTheThings()";
	private static final String TITLE = "Sauce Labs";
	private static final String PRODUCTS_PAGE = "https://www.saucedemo.com/inventory.html";
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
	public void saucedemoShopping() {
		openHomePage();
		Assert.assertEquals(getHomePageUrl(), HOME_PAGE_URL);

		typeCustomerUsername(USER_NAME);
		typeCustomerPassword(PASSWORD);
		login();

		//Verify there are 6 products in the page
		/*	By inventorylist_Locator=By.xpath("//div[@class='inventory_list']");//2
		List <WebElement>Inventory=driver.findElements(inventorylist_Locator);
		int size=Inventory.size();
		System.out.println(size);*/

		Assert.assertTrue(driver.getCurrentUrl().startsWith(PRODUCTS_PAGE));
		Assert.assertTrue(getProductTitle().startsWith(TITLE));
		Assert.assertTrue(getProductDescription().startsWith(DESCRIPTION));
		Assert.assertTrue(getProductPrice()>0);
		addProductToCart();
		
		Assert.assertEquals(getNumberOfItemsInCart(), 1);
		
		goToShoppingCart();

		Assert.assertEquals(driver.getCurrentUrl(), SHOPPINGCART_PAGE);
		Assert.assertTrue(getProductTitle().startsWith(TITLE));
		Assert.assertTrue(getProductDescription().startsWith(DESCRIPTION));
		Assert.assertTrue(getProductPrice()>0);
		checkout();

		Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_STEPONE);
		typeFirstName(FIRSTNAME);
		typeLastName(LASTNAME);
		typePostcode(POSTALCODE);
		continueToNextPage();
		
		Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_STEPTWO);
		Assert.assertTrue(getProductTitle().startsWith(TITLE));
		Assert.assertTrue(getProductDescription().startsWith(DESCRIPTION));
		Assert.assertTrue(getProductPrice()>0);
		Assert.assertTrue(getPaymentInfo().startsWith(PAYMENTINFO_TEXT));
		Assert.assertTrue(getShippingInfo().startsWith(SHIPPINGINFO_TEXT));
		Assert.assertTrue(getPriceTotal()>0);
		Assert.assertTrue(getTax()>0);
		Assert.assertTrue(getTotal()>0);
		finishCheckOut();
		
		Assert.assertEquals(driver.getCurrentUrl(), CHECKOUT_COMPLETE);
		Assert.assertTrue(getcheckOutompleteText().startsWith("Your order"));



	}
	private void openHomePage() {
		driver.get(HOME_PAGE_URL );
	}

	private String getHomePageUrl() {
		String currentUrl=driver.getCurrentUrl();
		return currentUrl;
	}

	private void typeCustomerUsername(String uname) {
		wait.until(ExpectedConditions.elementToBeClickable(USERNAME_LOCATOR));
		WebElement usernameTextBox=driver.findElement(USERNAME_LOCATOR);
		usernameTextBox.sendKeys(uname);
	}

	private void typeCustomerPassword(String password) {	
		wait.until(ExpectedConditions.elementToBeClickable(PASSWORD_LOCATOR));
		WebElement passwordTextBox=driver.findElement(PASSWORD_LOCATOR);
		passwordTextBox.sendKeys(password);
	}	
	private void login(){	
		wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON_LOCATOR));
		WebElement loginButton=driver.findElement(LOGIN_BUTTON_LOCATOR);
		loginButton.click();
	}
	private String getProductTitle() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE_LOCATOR));
		WebElement productTitleLabel=driver.findElement(PRODUCT_TITLE_LOCATOR);
		return productTitleLabel.getText();
	}
	private String getProductDescription() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_DESCRIPTION_LOCATOR));
		WebElement productDescriptionText=driver.findElement(PRODUCT_DESCRIPTION_LOCATOR);
		return productDescriptionText.getText();
	}

	private double getProductPrice() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_PRICE_LOCATOR));
		WebElement productPriceText=driver.findElement(PRODUCT_PRICE_LOCATOR);
		String priceValue=productPriceText.getText();
		String priceReplace$=priceValue.replace("$", "");
		double price=Double.parseDouble(priceReplace$);
		return price;
	}
	private void addProductToCart() {
		wait.until(ExpectedConditions.elementToBeClickable(ADD_TOCART_LOCATOR));
		WebElement addToCartButton = driver.findElement(ADD_TOCART_LOCATOR);
		addToCartButton.click();
	}
	private int getNumberOfItemsInCart() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHOPPING_CART_LOCATOR));
		WebElement shoppingCartLabel=driver.findElement(SHOPPING_CART_LOCATOR);
		String cartValue=shoppingCartLabel.getText();
		int cartItems=Integer.parseInt(cartValue);
		return cartItems;
				
	}
	private void goToShoppingCart() {
		wait.until(ExpectedConditions.elementToBeClickable(SHOPPING_CART_LOCATOR));
		WebElement shoppingCartLabel=driver.findElement(SHOPPING_CART_LOCATOR);
		shoppingCartLabel.click();
	}
	private void checkout() {
		wait.until(ExpectedConditions.elementToBeClickable(CHECKOUT_LOCATOR));
		WebElement checkoutButton=driver.findElement(CHECKOUT_LOCATOR);
		checkoutButton.click();
	}
	private void typeFirstName(String firstname) {
		wait.until(ExpectedConditions.elementToBeClickable(FIRSTNAME_LOCATOR));
		WebElement firstNameTextBox =driver.findElement(FIRSTNAME_LOCATOR);
		firstNameTextBox.sendKeys(firstname);
	}
	private void typeLastName(String lastname) {
		wait.until(ExpectedConditions.elementToBeClickable(LASTNAME_LOCATOR));
		WebElement lastNameTextBox =driver.findElement(LASTNAME_LOCATOR);
		lastNameTextBox.sendKeys(lastname);
	}
	private void typePostcode(String postcode) {
		wait.until(ExpectedConditions.elementToBeClickable(FIRSTNAME_LOCATOR));
		WebElement postCodeTextBox =driver.findElement(POSTALCODE_LOCATOR);
		postCodeTextBox.sendKeys(postcode);
	}
	private void continueToNextPage() {
		wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON_LOCATOR));
		WebElement continueButton =driver.findElement(CONTINUE_BUTTON_LOCATOR);
		continueButton.click();
	}

	private String getPaymentInfo() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PAYMENTINFO_LOCATOR));
		WebElement paymentInfoLabel=driver.findElement(PAYMENTINFO_LOCATOR);
		return paymentInfoLabel.getText();
	}
	private String getShippingInfo() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPINGINFO_LOCATOR));
		WebElement shippingInfoLabel=driver.findElement(SHIPPINGINFO_LOCATOR);
		return shippingInfoLabel.getText();
	}
	private double getPriceTotal() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ITEMTOTAL_LOCATOR));
		WebElement itemTotalLabel=driver.findElement(ITEMTOTAL_LOCATOR);
		String itemValue=itemTotalLabel.getText();
		String itemValueReplace$=itemValue.replace("Item total: $", "");
		double totalValue=Double.parseDouble(itemValueReplace$);
		return totalValue;
	}
	private double getTax() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(TAX_LOCATOR));
		WebElement taxLabel=driver.findElement(TAX_LOCATOR);
		String taxValue=taxLabel.getText();
		String taxValueReplace$=taxValue.replace("Tax: $", "");
		double taxToPay=Double.parseDouble(taxValueReplace$);
		return taxToPay;
	}
	private double getTotal() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(TOTAL_LOCATOR));
		WebElement totalLabel=driver.findElement(TOTAL_LOCATOR);
		String totalVal=totalLabel.getText();
		String totalValReplace$=totalVal.replace("Total: $", "");
		double totalToPay=Double.parseDouble(totalValReplace$);
		return totalToPay;
	}
	private void finishCheckOut() {
		wait.until(ExpectedConditions.elementToBeClickable(FINISH_LOCATOR));
		WebElement finishButton=driver.findElement(FINISH_LOCATOR);
		finishButton.click();
	}
	private String getcheckOutompleteText() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_DESCRIPTION_LOCATOR));
		WebElement CheckOutDescriptionText=driver.findElement(CHECKOUT_DESCRIPTION_LOCATOR);
		return CheckOutDescriptionText.getText();
		
	}

}
