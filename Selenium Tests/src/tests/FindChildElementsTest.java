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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FindChildElementsTest {
private static final String RESULT_PAGE_URL = "https://vpl.bibliocommons.com/";
private static final String INPUT_TEXT = "Java";
private static final String HOME_PAGE_URL = "https://www.vpl.ca/";
private ChromeDriver driver;
private WebDriverWait wait;
private static final By SEARCH_BOX_LOCATOR=By.id("edit-keyword");
private static final By SEARCH_BUTTON_LOCATOR=By.id("edit-submit");



@BeforeMethod
public void setUp() {
 driver=new ChromeDriver();
 driver.manage().window().maximize();
 wait=new WebDriverWait(driver,Duration.ofSeconds(30));
}
@AfterMethod
public void teatDown() {
	driver.quit();
	
}
@Test
public void canGetFieldsOfFirstResultTest() {
	driver.get(HOME_PAGE_URL);
	Assert.assertEquals(driver.getCurrentUrl(),HOME_PAGE_URL);

	wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BOX_LOCATOR));
	WebElement searchboxTextBox=driver.findElement(SEARCH_BOX_LOCATOR);
	searchboxTextBox.sendKeys(INPUT_TEXT);
	
	wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON_LOCATOR));
	WebElement searchButton=driver.findElement(SEARCH_BUTTON_LOCATOR);
	searchButton.click();
	
	Assert.assertTrue(driver.getCurrentUrl().contains(RESULT_PAGE_URL));
	
	//The x-path has different index.In this case how can I split the locator that contains the full result in this case?
	By xpathTitle=By.xpath("(//li[@data-key='search-result-item']//span[@class='title-content'])[2]");
	By XpathSubTitle=By.xpath("(//li[@data-key='search-result-item']//span[@class='cp-subtitle'])[1]");
	By xpathAuthor=By.xpath("(//li[@data-key='search-result-item']//span[@class='cp-by-author-block --block'])[4]");
	
	WebElement titleSpan=driver.findElement(xpathTitle);
	System.out.println("Title Name"+ titleSpan.getText());
	
	WebElement subTitleSpan=driver.findElement(XpathSubTitle);
	System.out.println("Sub title name" + subTitleSpan.getText());
	
	WebElement authorSpan=driver.findElement(xpathAuthor);
	System.out.println("Author details" + authorSpan.getText());
	
	By xpathTitle2=By.xpath("(//li[@data-key='search-result-item']//span[@class='title-content'])[1]"); 
	By xpathAuthor2=By.xpath("(//li[@data-key='search-result-item']//span[@class='cp-by-author-block --block'])[1]");
	
	WebElement titleSpan2=driver.findElement(xpathTitle2);
	System.out.println(titleSpan2.getText());
	
	WebElement authorSpan2=driver.findElement(xpathAuthor2);
	System.out.println(authorSpan2.getText());
	
}

}
