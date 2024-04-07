package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Frames {

	private static final String NESTED_FRAMES = "https://the-internet.herokuapp.com/nested_frames";
	private ChromeDriver driver;
	private WebDriverWait wait;
	
		
@BeforeMethod
public void setup() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	wait=new WebDriverWait(driver,Duration.ofSeconds(30));
}

@AfterMethod
public void tearDown() {
	driver.quit();
}

@Test
public void selectElementsFromFramesUsingName() {
	driver.get(NESTED_FRAMES);
	String currentUrl=driver.getCurrentUrl();
	Assert.assertEquals(currentUrl, NESTED_FRAMES);
	
	driver.switchTo().frame("frame-top");//Parent frame. The preferred way to switch to a frame is ‘id’ and ‘name’
	driver.switchTo().frame("frame-left");//first child
	
	//Create locator for the body element
	By leftFrameLocator=By.xpath("//body");
	WebElement leftFrame=driver.findElement(leftFrameLocator);
	
	Assert.assertTrue(leftFrame.getText().contains("LEFT"));
	
	driver.switchTo().parentFrame();//Leave left frame and go to parent frame
	driver.switchTo().frame("frame-middle");//Second child
	
	//To find the div element for the middle frame
	By middleFrameLocator=By.id("content");
	WebElement middleFrame=driver.findElement(middleFrameLocator);
	
	Assert.assertTrue(middleFrame.getText().contains("MIDDLE"));
	
	driver.switchTo().parentFrame();
	driver.switchTo().frame("frame-right");
	
	//Get the body element
	leftFrame=driver.findElement(leftFrameLocator);
	Assert.assertTrue(leftFrame.getText().contains("RIGHT"));
}

@Test
public void selectElementsFromFramesUsingIndex() { 
	driver.get(NESTED_FRAMES);
	String currentUrl=driver.getCurrentUrl();
	Assert.assertEquals(currentUrl, NESTED_FRAMES);
	
	driver.switchTo().frame(0);//Parent frame Not the ideal way of switching to frame.
	driver.switchTo().frame(0);//first child
	
	By leftFrameLocator=By.xpath("//body");
	WebElement leftFrame=driver.findElement(leftFrameLocator);
	
	Assert.assertTrue(leftFrame.getText().contains("LEFT"));
	
	driver.switchTo().parentFrame();//Leave left frame and go to parent frame
	driver.switchTo().frame(1);//Second child
	
	By middleFrameLocator=By.id("content");
	WebElement middleFrame=driver.findElement(middleFrameLocator);
	
	Assert.assertTrue(middleFrame.getText().contains("MIDDLE"));
	
	driver.switchTo().parentFrame();
	driver.switchTo().frame(2);
	
	leftFrame=driver.findElement(leftFrameLocator);
	Assert.assertTrue(leftFrame.getText().contains("RIGHT"));
}

@Test
public void selectElementsFromFramesUsingWebElement() {
	driver.get(NESTED_FRAMES);
	String currentUrl=driver.getCurrentUrl();
	Assert.assertEquals(currentUrl, NESTED_FRAMES);
	
	By topFrameBy=By.xpath("//frame[@src='/frame_top']"); //This is the 3rd ideal way to switch to a frame ie, using ‘WebElement’
	WebElement topFrame=driver.findElement(topFrameBy);
	driver.switchTo().frame(topFrame);
		
	By leftFrameBy=By.xpath("//frame[@src='/frame_left']");
	WebElement leftFrame=driver.findElement(leftFrameBy);
	driver.switchTo().frame(leftFrame);
	
	By leftFrameLocator=By.xpath("//body");
	WebElement leftFrameElement=driver.findElement(leftFrameLocator);
	Assert.assertTrue(leftFrameElement.getText().contains("LEFT"));
	
	driver.switchTo().parentFrame();//Leave left frame and go to parent frame
	
	By middleFrameBy=By.xpath("//frame[@src='/frame_middle']");
	WebElement middleFrameElement=driver.findElement(middleFrameBy);
	driver.switchTo().frame(middleFrameElement);
	
	By middleFrameLocator=By.id("content");
	WebElement middleFrame=driver.findElement(middleFrameLocator);
	
	Assert.assertTrue(middleFrame.getText().contains("MIDDLE"));
	
	driver.switchTo().parentFrame();
	
	By rightframeBy=By.xpath("//frame[@src='/frame_right']");
	WebElement rightFrame=driver.findElement(rightframeBy);
	driver.switchTo().frame(rightFrame);
	
	leftFrameElement=driver.findElement(leftFrameLocator);
	Assert.assertTrue(leftFrameElement.getText().contains("RIGHT"));
}

	
	
}
