package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Popup {


	private static final String HOME_PAGE_URL = "https://the-internet.herokuapp.com/entry_ad";
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
	public void selectElementFromPopup() throws InterruptedException{

		driver.get(HOME_PAGE_URL);
		Assert.assertTrue(driver.getCurrentUrl().equals(HOME_PAGE_URL));	

		Thread.sleep(3000);

		//Modal window should be treated like a page. The main distinction of a modal window is it doesn’t have a url and a title.
		//How we check whether a model window is displayed or not is by verifying an element in the popup is there or not
		//Something like the popup is displayed if the title is there.

		//Title of the model window Locator
		By modalTitleLocator =By.xpath("//div[@class='modal-title']/h3");

		//The below 2 lines of code can be rewritten as
		WebElement modalWindowTitle=driver.findElement(modalTitleLocator);
		Assert.assertTrue(modalWindowTitle.isEnabled());

		//Above 2 line of code can be replaced by the below code
		//wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitleLocator));

		By modalBodyLocator =By.xpath("//div[@class='modal-body']/p");
		WebElement ModalBody=driver.findElement(modalBodyLocator);
		Assert.assertFalse(ModalBody.getText().isEmpty());


		By modalWindowCloseLocator=By.xpath("//div[@class='modal-footer']/p");
		WebElement modalWindowClose=driver.findElement(modalWindowCloseLocator);
		modalWindowClose.click();

	}
}

