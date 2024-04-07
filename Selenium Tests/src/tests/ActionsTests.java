package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ActionsTests {
		private static final String HOME_PAGE_URL = "https://the-internet.herokuapp.com/jqueryui/menu";
		private ChromeDriver driver;
		private WebDriverWait wait;
	//	actions is an object that is created for the Actions class.Actions is a class from selenium.
		private Actions actions;

		@BeforeMethod
		public void setUp() {
			driver =new ChromeDriver();
			driver.manage().window().maximize();
			wait=new WebDriverWait(driver,Duration.ofSeconds(30));
			actions=new Actions(driver);
		}

		@AfterMethod
		public void TearDown() {
			driver.quit();
		}
	@Test
	public void canMouseOverMenuTest() throws InterruptedException{

			driver.get(HOME_PAGE_URL);
			Assert.assertTrue(driver.getCurrentUrl().equals(HOME_PAGE_URL));	

			Thread.sleep(3000);
			
			//Writing the Locator and find element in single line.Avoid using a variable which is used once.
			WebElement menuItem=driver.findElement(By.id("ui-id-3"));
			//To mouse hover the element we need the below line of code '.perform()' implement the mouse hover action
			actions.moveToElement(menuItem).perform();
			Thread.sleep(3000);
			
			WebElement downloadsItem=driver.findElement(By.id("ui-id-4"));
			Assert.assertTrue(downloadsItem.isDisplayed());
			
			WebElement BackToJQueryItem=driver.findElement(By.id("ui-id-8"));
			Assert.assertTrue(BackToJQueryItem.isDisplayed());
			
			actions.moveToElement(downloadsItem).perform();
			Thread.sleep(3000);
			
			WebElement pdfItem=driver.findElement(By.id("ui-id-5"));
			Assert.assertTrue(pdfItem.isDisplayed());
			
			WebElement csvItem=driver.findElement(By.id("ui-id-6"));
			Assert.assertTrue(csvItem.isDisplayed());
			
			WebElement excelItem=driver.findElement(By.id("ui-id-7")); 
			Assert.assertTrue(excelItem.isDisplayed());
			
			
}
	}
