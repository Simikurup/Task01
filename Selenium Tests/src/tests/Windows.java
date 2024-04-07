package tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Windows {

private static final String HOME_PAGE_URL = "https://the-internet.herokuapp.com/windows";
private ChromeDriver driver;
private WebDriverWait wait;

@BeforeMethod
public void setUp() {
driver =new ChromeDriver();
driver.manage().window().maximize();
wait=new WebDriverWait(driver,Duration.ofSeconds(30));
}

@AfterMethod
public void TearDown() {
	driver.quit();
}
@Test
public void selectElementFromWindow() throws InterruptedException {
	driver.get(HOME_PAGE_URL);
	Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL);
	
	//Each browser window has a unique identifier which is called a Window Handle.
	//The window handle is been used for switching from one window to another window.
	
	//Below code get handle of first page.
	String firstPageHandle=driver.getWindowHandle();
	System.out.println("First page handle: " +firstPageHandle);
	
	Thread.sleep(3000);
	
	//X-path for the link
	By linkXpathLocator= By.xpath("//a[@href='/windows/new']");
	WebElement link=driver.findElement(linkXpathLocator);
	//When we click we have two tabs
	link.click();
	
	Thread.sleep(3000);
	
	//driver.getWindowHandles() this method will return a collection that include the window handles of
	//all the open tabs.It will not return a list.It returns a different kind of collection called Set.
	//In a list we can have duplicate values but in a set we cannot have duplicate values.
	
	//Below code get list of all window handles .We have converted the Set to a list in the below code.
	List <String> pageHandles = new ArrayList<> (driver.getWindowHandles());
	
	//Verify whether pageHandles List in the above code has a size equal to 2.We have 2 tabs so we should 
	//have 2 window handles
	Assert.assertEquals(pageHandles.size(), 2);
	
	//remove first window handle from the above list
	pageHandles.remove(firstPageHandle);
	
	//Initially the list had 2 handles.The first handle has index 0 and second handle has index 1.
	//But we doesn't know which handle is in which position.Since we have removed the handle of first window from the list. 
	//we have only one window remaining in the list that will be handle for the second window.
	//To get the first element of any list we do pageHandles.get(0)
	
	//get handle of the second window
	String secondPageHandle=pageHandles.get(0);
	System.out.println("Second page handle: " +secondPageHandle);
	
	//Switch to second window
	driver.switchTo().window(secondPageHandle);
	
	//Find the element for the "new Window' (has h3 tab)
	By windowLocator= By.xpath("//h3");
	WebElement label=driver.findElement(windowLocator);
	
	Assert.assertTrue(label.getText().contains("New Window"));
	
	//driver.quit closes all the windows and tabs opened by the test.
	//To close the current tab we use driver.close().
	
	//close the second tab
	driver.close();
	
	Thread.sleep(3000);
	
	//Switch to first window
	driver.switchTo().window(firstPageHandle);
	label=driver.findElement(windowLocator);
	Assert.assertTrue(label.getText().contains("Opening a new window"));
	
}


}
