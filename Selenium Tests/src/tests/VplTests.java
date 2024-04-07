

//Webex meeting recording: WebElement - 1 and 2

package tests;

import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v119.browser.Browser;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver.Navigation;


public class VplTests {
	
	//Defining driver in class level
	private ChromeDriver driver;
	
	//Locators can be reused it is moved outside of the test to the beginning of the class.If multiple test need same locator 
	//they can share the locator thats the benefit of moving the locator here.Also we can make the test shorter by moving the locator.
	private By searchBoxLocator=By.id("edit-keyword");
	private By searchButtonLocator=By.name("op");
	private By topPaginattionLocator =By.xpath("(//span[@data-key='pagination-text'])[1]");
	
	//Creating constants -variables declared with static and final
	private static final String HOME_PAGE_URL="https://www.vpl.ca/";
	private static final String HOME_PAGE_TITLE="Home | Vancouver Public Library";
	private static final String SEARCH_STRING="java";
	private static final String RESULTS_PAGE_DOMAIN="https://vpl.bibliocommons.com";
	private static final String RESULTS_PAGE_TITLE="Search | Vancouver Public Library | BiblioCommons";
	private static final String LOGIN_PAGE_URL= "https://vpl.bibliocommons.com/user/login";
	private static final String LOGIN_ERROR_TEXT="The username or PIN is incorrect. Please try again";
	
	//Static is better for locators
	private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//a[text()='Login / My VPL']");
	private static final By USERNAME_LABEL_LOCATOR=By.xpath("//label[contains(@for ,'user_name')]");
	private static final By USERNAME_TEXTBOX_LOCATOR = By.name("name");
	private static final By PIN_LABEL_LOCATOR = By.xpath("//label[contains(@for ,'user_pin')]");
	private static final By PIN_TEXTBOX_LOCATOR=By.name("user_pin");
	private static final By FORGOT_PIN_LOCATOR = By.xpath("//a[@testid='link_forgotpin']");
	private static final By REMEMBER_ME_LOCATOR =By.xpath(("//label[contains(text(),'Remember me on this device')]"));
	private static final By SUBMIT_BUTTON_LOCATOR = By.xpath("//input[@title='Log In']");
	private static final By LOGIN_ERROR_MESSAGE_LOCATOR=By.xpath("//p[@data-test-id='top-message']");
	
	
	//Fixtures-Will be executed before and after each test
	@BeforeMethod
	public void setup()
	{
		//driver will open the browser
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
		
	@AfterMethod
	public void teardown()
	{
	//Quit driver and close browser.If the test opens multiple tabs/windows driver.quit()closes all the opened windows.
	//Where as driver.close()closes only the current tab.
	driver.quit();
	}
	
	
	@Test
	public void searchReturnsResultsTest()
	{
		driver.get(HOME_PAGE_URL);
	
		String homePageUrl=driver.getCurrentUrl();
		Assert.assertEquals(homePageUrl, HOME_PAGE_URL);
		
		String homePageTitle=driver.getTitle();
		Assert.assertEquals(homePageTitle,HOME_PAGE_TITLE );
		
		WebElement searchBox = driver.findElement(searchBoxLocator);
	
		searchBox.sendKeys(SEARCH_STRING);

		WebElement searchButton = driver.findElement(searchButtonLocator);
	
		searchButton.click();
	
		String searchResulturl=driver.getCurrentUrl();
		Assert.assertTrue(searchResulturl.startsWith(RESULTS_PAGE_DOMAIN));
	
		String resultPageTitle=driver.getTitle();
		Assert.assertEquals(resultPageTitle, RESULTS_PAGE_TITLE);
	
		WebElement topPagination=driver.findElement(topPaginattionLocator);
		String topPaginationtext =topPagination.getText();
		
		Assert.assertTrue(topPaginationtext.startsWith("1 to 10"));
		

		}
	
	//Test for WebElement
	@Test
	public void userNameFieldsAreDisplayedCorrectly() throws InterruptedException
	{
		driver.get(HOME_PAGE_URL);
		
		Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL);
		
	//The below two line of code can be simplified as driver.findElement(LOGIN_BUTTON_LOCATOR).click(); 
	//as the variable is used only once.But better to use as below for web elements.	
		WebElement loginButton = driver.findElement(LOGIN_BUTTON_LOCATOR);
		loginButton.click();

				
	//	String loginPageurl=driver.getCurrentUrl();
		Assert.assertTrue(driver.getCurrentUrl().startsWith(LOGIN_PAGE_URL));
		
		//USERNAME LABEL
		
		WebElement userNameLabel =driver.findElement(USERNAME_LABEL_LOCATOR);
		
		Assert.assertTrue(userNameLabel.isDisplayed());
	
	// VISUAL TESTING	- UI 
	//	Verify whether the username label and the username textbox is alligned.We work on X coordinate.
	//The result of getLocation() method is a point object
	//getLocation() method is returning an object of a class point, because location is made of horizontal and vertical coordinates that need to put together into an object.
		Point userNameLabelLocation =userNameLabel.getLocation();
	//getX()method is the X coordinate (horizontal)
		int userNameLabelLocationX=userNameLabelLocation.getX();
	//Check whether the label is above the text box.We work on Y coordinate.
		int userNameLabelLocationY=userNameLabelLocation.getY();
		Assert.assertTrue(userNameLabelLocationX >0);
		Assert.assertTrue(userNameLabelLocationY >0);
		
	//Check whether the label is fully displayed.Means verifying the height and width of the label is fully displayed.
	//getSize() method returns the object dimension.
		Dimension userNameLabelSize=userNameLabel.getSize();
		int userNameLabelWidth = userNameLabelSize.getWidth();
		int userNameLabelHeight = userNameLabelSize.getHeight();
		Assert.assertEquals(userNameLabelWidth,207);
		Assert.assertEquals(userNameLabelHeight,21);

//Verify whether the label is Bold.Check for CSS.We can also check font colour.
		Assert.assertEquals(userNameLabel.getCssValue("font-size"),"14px");
	//	Assert.assertTrue(userNameLabel.getCssValue("font-family").contains("Arial"));
	
		//USERNAME TEXTBOX		
		
		WebElement usernameTextBox=driver.findElement(USERNAME_TEXTBOX_LOCATOR);
		
		
	//We need to check whether a link/button etc (anything clickable and that click make a change in the page) is enabled.
		Assert.assertTrue(usernameTextBox.isDisplayed());
		Assert.assertTrue(usernameTextBox.isEnabled());
		

		//	Verify whether the username label and the username textbox is alligned. We work on X coordinate.This is cooordinates of WebElement
		Point userNameTextBoxLocation =usernameTextBox.getLocation();
		int userNameTextBoxLocationX=userNameTextBoxLocation.getX();
		//Check whether the label is above the text box.We work on Y coordinate.
		int userNameTextBoxLocationY=userNameTextBoxLocation.getY();
		Assert.assertTrue(userNameTextBoxLocationX >0);
		Assert.assertTrue(userNameTextBoxLocationY >0);
		Assert.assertEquals(userNameLabelLocationX, userNameTextBoxLocationX);
		
		Assert.assertTrue(userNameLabelLocationY < userNameTextBoxLocationY);
		
		//Check whether the label is fully displayed
		//getSize() method returns the object dimension.
		Dimension userNameTextBoxSize=usernameTextBox.getSize();
		int userNameTextBoxWidth = userNameTextBoxSize.getWidth();
		int userNameTextBoxHeight = userNameTextBoxSize.getHeight();
			
		Assert.assertEquals(userNameTextBoxWidth,200);
		Assert.assertEquals(userNameTextBoxHeight,30);
		
//To verify whether the username field is a textbox/Check the type of username field.We need to get the tag ("input" tag) and attribute (type attribute)
//We can use ANY attribute name value combination for testing.	
//If the tag is input and type is text then its a textbox
//If the tag is input and type is submit its a button	
		
		Assert.assertEquals(usernameTextBox.getTagName(), "input");
		Assert.assertEquals(usernameTextBox.getAttribute("type"), "text");
		
	}
	
	@Test
	public void pinLabelIsDisplayedCorrectly()
	{
		driver.get(HOME_PAGE_URL);
		Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL);
		
		WebElement loginButton=driver.findElement(LOGIN_BUTTON_LOCATOR);
		loginButton.click();
		
		Assert.assertTrue(driver.getCurrentUrl().startsWith(LOGIN_PAGE_URL));
		
		WebElement pinLabel= driver.findElement(PIN_LABEL_LOCATOR);
		
		Assert.assertTrue(pinLabel.isDisplayed());
		
		Dimension pinLabelSize=pinLabel.getSize();
		Assert.assertEquals(pinLabelSize.getHeight(), 21);
		Assert.assertEquals(pinLabelSize.getWidth(), 207);
		
		Assert.assertEquals(pinLabel.getCssValue("font-size"), "14px");
					
	}
	
	
	@Test
	public void pinTextboxIsDisplayedCorrectly()
	{
		driver.get(HOME_PAGE_URL);
		Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL);
		
		WebElement loginButton=driver.findElement(LOGIN_BUTTON_LOCATOR);
		loginButton.click();
		
		Assert.assertTrue(driver.getCurrentUrl().startsWith(LOGIN_PAGE_URL));
		
		WebElement pinTextbox= driver.findElement(PIN_TEXTBOX_LOCATOR);
				
		Assert.assertTrue(pinTextbox.isDisplayed());
		Assert.assertTrue(pinTextbox.isEnabled());	
		
		Assert.assertEquals(pinTextbox.getTagName(), "input");
		Assert.assertEquals(pinTextbox.getAttribute("type"), "password");
		
	}
	
	@Test
	public void pinLabelAndTextboxIsDisplayedCorrectly()
	{
		driver.get(HOME_PAGE_URL);
		Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL);
		
		WebElement loginButton=driver.findElement(LOGIN_BUTTON_LOCATOR);
		loginButton.click();
		
		Assert.assertTrue(driver.getCurrentUrl().startsWith(LOGIN_PAGE_URL));
		
		
		WebElement pinLabel= driver.findElement(PIN_LABEL_LOCATOR);
		
		WebElement pinTextbox= driver.findElement(PIN_TEXTBOX_LOCATOR);
		
		Point pinLabelLocation = pinLabel.getLocation();
		int pinLabelX=pinLabelLocation.getX();
		int pinLabelY=pinLabelLocation.getY();
			
		Point pinTextboxLocation = pinTextbox.getLocation();
		int pinTextboxX=pinTextboxLocation.getX();
		int pinTextboxY=pinTextboxLocation.getY();
		
		Assert.assertTrue(pinLabelX>0);
		Assert.assertTrue(pinTextboxX>0);
		Assert.assertEquals(pinLabelX, pinTextboxX);
		
		Assert.assertTrue(pinLabelY>0);
		Assert.assertTrue(pinTextboxY>0);
		Assert.assertTrue(pinTextboxY>pinLabelY);
		
		}
	
	@Test
	public void forgotYourPinIsDisplayedCorrectly()
	{
		driver.get(HOME_PAGE_URL);
		Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL);
		
		WebElement loginButton=driver.findElement(LOGIN_BUTTON_LOCATOR);
		loginButton.click();
		
		Assert.assertTrue(driver.getCurrentUrl().startsWith(LOGIN_PAGE_URL));
		
		WebElement forgotPin =driver.findElement(FORGOT_PIN_LOCATOR);
		Assert.assertTrue(forgotPin.isDisplayed());
		Assert.assertTrue(forgotPin.isEnabled());
		
		Dimension forgotPinSize=forgotPin.getSize();
		
		Assert.assertEquals(forgotPinSize.getHeight(), 18);
		Assert.assertEquals(forgotPinSize.getWidth(), 103);
		
		Assert.assertEquals(forgotPin.getCssValue("font-size"), "13.3px");
		
		
	}
	
	@Test
	public void remembermeOnThisDeviceIsDisplayedCorrectly()
	{
		driver.get(HOME_PAGE_URL);
		Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL);
		
		WebElement loginButton=driver.findElement(LOGIN_BUTTON_LOCATOR);
		loginButton.click();
		
		Assert.assertTrue(driver.getCurrentUrl().startsWith(LOGIN_PAGE_URL));
		
		WebElement rememberMe=driver.findElement(REMEMBER_ME_LOCATOR);
	
		rememberMe.click();
		
		Dimension rememberMeLink=rememberMe.getSize();
		Assert.assertEquals(rememberMeLink.getHeight(), 19);
		Assert.assertEquals(rememberMeLink.getWidth(), 190);
		
		Assert.assertEquals(rememberMe.getCssValue("font-size"), "14px");
	
	}
	
	@Test
	public void loginButtonIsDisplayedCorrectly() throws InterruptedException
	{
		driver.get(HOME_PAGE_URL);
		Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL);
		
		WebElement loginButton=driver.findElement(LOGIN_BUTTON_LOCATOR);
		loginButton.click();
		
		Assert.assertTrue(driver.getCurrentUrl().startsWith(LOGIN_PAGE_URL));
		
		WebElement SubmitButton =driver.findElement(SUBMIT_BUTTON_LOCATOR);
		
		Assert.assertTrue(SubmitButton.isDisplayed());
		Assert.assertTrue(SubmitButton.isEnabled());
		SubmitButton.click();
		
		Dimension buttonSize = SubmitButton.getSize();
		Assert.assertEquals(buttonSize.getHeight(), 37);
		Assert.assertEquals(buttonSize.getWidth(), 65);
		
		String backGr=SubmitButton.getCssValue("color");
		System.out.println("backGr"+backGr);
		String color=SubmitButton.getCssValue("background-color");
		System.out.println("color"+color);
		//Convert RGBA value to hex code
				
		Thread.sleep(5000);
	
	}
		
	@Test
	public void ResultsPageIsDisplayedAfterSearchTest()
	{
		
		driver.get(HOME_PAGE_URL);
						
		String homePageUrl=driver.getCurrentUrl();
		Assert.assertEquals(homePageUrl, HOME_PAGE_URL);
		
		String homePageTitle=driver.getTitle();
		Assert.assertEquals(homePageTitle,HOME_PAGE_TITLE );
		
		By searchBoxLocator=By.id("edit-keyword");
		WebElement searchBox = driver.findElement(searchBoxLocator);
	
		searchBox.sendKeys(SEARCH_STRING);

		By searchButtonLocator=By.name("op");
		WebElement searchButton = driver.findElement(searchButtonLocator);
	
		searchButton.click();
	
		String searchResulturl=driver.getCurrentUrl();
		Assert.assertTrue(searchResulturl.startsWith(RESULTS_PAGE_DOMAIN));
	
		String resultPageTitle=driver.getTitle();
		Assert.assertEquals(resultPageTitle, RESULTS_PAGE_TITLE);
						

		}
	
	//Test for WebDriver
	@Test
	public void getMethodTest()
	{
	//method1 to open a page -> Get the page from the server and displays in the browser.
	driver.get(HOME_PAGE_URL);
	Assert.assertEquals (driver.getCurrentUrl(),HOME_PAGE_URL);
	
	
	}
	
	@Test
	public void navigateMethodTest()
	{
	//method2 to open a page 
	//Navigation navigation= driver.navigate();	
	//Select .to(String url)
	//navigation.to(HOME_PAGE_URL);
	//The above code can be written as
	driver.navigate().to(HOME_PAGE_URL);

	Assert.assertEquals (driver.getCurrentUrl(),HOME_PAGE_URL);
	
	
	
	}
	
	//Go forward and backward of the page
	@Test
	public void forwardAndBackwardMethodsTest() throws InterruptedException
	{
		
		driver.get(HOME_PAGE_URL);
		Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL);
		
			
		WebElement searchBox = driver.findElement(searchBoxLocator);
		searchBox.sendKeys(SEARCH_STRING);

		WebElement searchButton = driver.findElement(searchButtonLocator);
		searchButton.click();
			
		Assert.assertTrue(driver.getCurrentUrl().startsWith(RESULTS_PAGE_DOMAIN));
	
		driver.navigate().back();
		Thread.sleep(3000);
		Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL);
		
		driver.navigate().forward();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().startsWith(RESULTS_PAGE_DOMAIN));

		}
	
	//Reload the page will help to load the elements which are not loaded properly before / page loading is slow. 
	//IT IS NOT A GOOD IDEA TO REFRESH.
	@Test
	public void refreshMethodTest()throws InterruptedException
	{
	//method2 to open a page 
	//Navigation navigation= driver.navigate();	
	//navigation.to(HOME_PAGE_URL);
		
	//The above code can be written as
	driver.navigate().to(HOME_PAGE_URL);
	
	Assert.assertEquals (driver.getCurrentUrl(),HOME_PAGE_URL);
	
	Thread.sleep(3000);
	
	driver.navigate().refresh();
	
	Thread.sleep(3000);
	
	Assert.assertEquals (driver.getCurrentUrl(),HOME_PAGE_URL);
	}
	
	//provides an id that is defining the current window.
	@Test
	public void getWindowHandleMethodTest()
	{
	//method1 to open a page -> Get the page from the server and displays in the browser.
	driver.navigate().to(HOME_PAGE_URL);
	
	Assert.assertEquals (driver.getCurrentUrl(),HOME_PAGE_URL);
	
	String windowHandle=driver.getWindowHandle();
	
	System.out.println(windowHandle);
		
	Assert.assertFalse(windowHandle.isEmpty());
	
		}
	
	//Get the position of the window
	@Test
	public void getPositionMethodTest()
	{
	//method1 to open a page -> Get the page from the server and displays in the browser.
	driver.navigate().to(HOME_PAGE_URL);
	
	Assert.assertEquals (driver.getCurrentUrl(),HOME_PAGE_URL);
	
	Point windowPosition=driver.manage().window().getPosition();
	//coordinates of browser
	
	Assert.assertTrue(windowPosition.getX()<=0);
	Assert.assertTrue(windowPosition.getY()<=0);
	System.out.println(windowPosition.getX());
	System.out.println(windowPosition.getY());
		
}
	//Get the position of the window
		@Test
		public void getSizeMethodTest()
		{
		//method1 to open a page -> Get the page from the server and displays in the browser.
		driver.navigate().to(HOME_PAGE_URL);
		
		Assert.assertEquals (driver.getCurrentUrl(),HOME_PAGE_URL);
		
		Dimension windowSize=driver.manage().window().getSize();
		//coordinates of browser
		
		Assert.assertEquals(windowSize.getHeight(),832);
		Assert.assertEquals(windowSize.getWidth(),1552);
	
		System.out.println("Height"+windowSize.getHeight());
		System.out.println("Width"+windowSize.getWidth());
		}
		
		//Synchronisation/the waiting
		
		@Test
		public void loginFailsForIncorrectCredentials() throws InterruptedException
		{
			//We are passing 2 parameters driver and maximum waiting time
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
					
			driver.get(HOME_PAGE_URL);
			
//This is the Synchronisation at page level meaning wait until the url is equal to something or contains something,Wait for page title
			wait.until(ExpectedConditions.urlToBe(HOME_PAGE_URL));
		//	We replace below assertions with wait
		//	Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL);

//This is the synchronisation at the element level.Other synchronisation include frame,window level	etc		
			wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON_LOCATOR));	
			WebElement loginButton = driver.findElement(LOGIN_BUTTON_LOCATOR);
			loginButton.click();
			
//			We replace below assertions with wait
			wait.until(ExpectedConditions.urlContains(LOGIN_PAGE_URL));
	//		Assert.assertTrue(driver.getCurrentUrl().startsWith(LOGIN_PAGE_URL));
			
			wait.until(ExpectedConditions.elementToBeClickable(USERNAME_TEXTBOX_LOCATOR));		
			WebElement usernameTextBox=driver.findElement(USERNAME_TEXTBOX_LOCATOR);
			//Enter username in textbox field
			usernameTextBox.sendKeys("11111");
			
			wait.until(ExpectedConditions.elementToBeClickable(PIN_TEXTBOX_LOCATOR));
			WebElement pinTextBox=driver.findElement(PIN_TEXTBOX_LOCATOR);
			pinTextBox.sendKeys("22222");
		
			wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BUTTON_LOCATOR));
			WebElement submitButton =driver.findElement(SUBMIT_BUTTON_LOCATOR);
			submitButton.click();
			
			//It will wait until the element matched by that locator (LOGIN_ERROR_MESSAGE_LOCATOR) is in the page and it is visible.
			//Waiting is for 2 conditions first element/label should be in the page and second the state should be visible.
			//The wait will go for maximum 30seconds.If the wait can find element in 2 seconds then it will not wait until 30sec
			wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_ERROR_MESSAGE_LOCATOR));
			WebElement errorMessageLabel =driver.findElement(LOGIN_ERROR_MESSAGE_LOCATOR);
			String errorMessage=errorMessageLabel.getText();
			Assert.assertEquals(errorMessageLabel.getText(), LOGIN_ERROR_TEXT);
			
		}
		
}