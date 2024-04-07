package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Alerts {
	private static final String HOME_PAGE_URL = "https://the-internet.herokuapp.com/javascript_alerts";
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
	public void canCloseAlerts() throws InterruptedException{

		driver.get(HOME_PAGE_URL);
		Assert.assertTrue(driver.getCurrentUrl().equals(HOME_PAGE_URL));	

		Thread.sleep(3000);
		By jsAlertLocator =By.xpath("//button[@onclick='jsAlert()']");
		WebElement  jsAlertButton=driver.findElement(jsAlertLocator);
		jsAlertButton.click();
		
		Thread.sleep(3000);
		
		driver.switchTo().alert().accept();
				
}
@Test
public void canConfirmAlerts() throws InterruptedException{
	
	driver.get(HOME_PAGE_URL);
	Assert.assertTrue(driver.getCurrentUrl().equals(HOME_PAGE_URL));	

	Thread.sleep(3000);
	
	By jsConfirmLocator =By.xpath("//button[@onclick='jsConfirm()']");
	WebElement  AlertButton=driver.findElement(jsConfirmLocator);
	AlertButton.click();
	
	Thread.sleep(3000);
	
	driver.switchTo().alert().dismiss();
	
	Thread.sleep(3000);
	
}
@Test
public void canClickAlerts() throws InterruptedException{
	
	driver.get(HOME_PAGE_URL);
	Assert.assertTrue(driver.getCurrentUrl().equals(HOME_PAGE_URL));	

	Thread.sleep(3000);
	
	By jsClickLocator =By.xpath("//button[@onclick='jsPrompt()']");
	WebElement  jsButton=driver.findElement(jsClickLocator);
	jsButton.click();
	
	
	Thread.sleep(3000);
	
	driver.switchTo().alert().sendKeys("Sample text");
	driver.switchTo().alert().accept();
	
	Thread.sleep(3000);
	
}
}
