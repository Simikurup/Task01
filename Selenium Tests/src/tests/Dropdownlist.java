package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Dropdownlist {
private ChromeDriver driver;

private static final String HOMEPAGE_URL="https://the-internet.herokuapp.com/dropdown";
private static final String OPTION_1="Option 1";
private static final String OPTION_2="Option 2";


private static final By DROPDOWN_LIST= By.xpath("//select[@id='dropdown']");

@BeforeMethod
public void setUp()
{
	driver=new ChromeDriver();
	driver.manage().window().maximize();
}

@AfterMethod
public void tearDown() 
{
	driver.quit();
}

//Dropdown list
	//Dropdown list has a select tag and option tag.Option tag has a value attribute and visible text (What we see in the dropdown list)
	//textbox has input tag
	//link has a tag
	//button has input/button tag
	//NOT THE IDEAL WAY TO INTERACT WITH DROPDOWN

@Test
public void selectDropDownOptionBYIndex() throws InterruptedException
{
	driver.get(HOMEPAGE_URL);
	Assert.assertEquals(driver.getCurrentUrl(), HOMEPAGE_URL);
	
	WebElement element= driver.findElement(DROPDOWN_LIST);
//To work with dropdown list we need to use the select class, which is a class from selenium library.
	Select dropdown = new Select(element);
	
//Index means the position of option inside the dropdown list.Selecting the option
	dropdown.selectByIndex(2);
	Thread.sleep(3000);
	
//To get the selected option.Here we are verifying whether the selected option works
	WebElement selectedOption=dropdown.getFirstSelectedOption();
//get.Text provides the value of the element.
	Assert.assertEquals(selectedOption.getText(), OPTION_2);
	
	dropdown.selectByIndex(1);
	
	Thread.sleep(3000);
	WebElement selection=dropdown.getFirstSelectedOption();
	Assert.assertEquals (selection.getText(), OPTION_1);
	}

//NOT THE IDEAL WAY TO INTERACT WITH DROPDOWN
@Test
public void selectDropDownOptionBYVisibleText() throws InterruptedException
{
	driver.get(HOMEPAGE_URL);
	Assert.assertEquals(driver.getCurrentUrl(), HOMEPAGE_URL);
	
	WebElement element= driver.findElement(DROPDOWN_LIST);
//To work with dropdown list we need to use the select class, which is a class from selenium library.
	Select dropdown = new Select(element);
	
//Index means the position of option inside the dropdown list.Selecting the option
	dropdown.selectByVisibleText(OPTION_2);
	Thread.sleep(3000);
	
//To get the selected option.Here we are verifying whether the selected option works
	WebElement selectedOption=dropdown.getFirstSelectedOption();
//get.Text provides the value of the element.
	Assert.assertEquals(selectedOption.getText(), OPTION_2);
	
	dropdown.selectByVisibleText(OPTION_1);
	
	Thread.sleep(3000);
	WebElement selection=dropdown.getFirstSelectedOption();
	Assert.assertEquals (selection.getText(), OPTION_1);
	
}
//THIS IS THE IDEAL WAY TO INTERACT WITH DROPDOWN
@Test
public void selectDropDownOptionBYValue() throws InterruptedException
{
	driver.get(HOMEPAGE_URL);
	Assert.assertEquals(driver.getCurrentUrl(), HOMEPAGE_URL);
	
	WebElement element= driver.findElement(DROPDOWN_LIST);
//To work with dropdown list we need to use the select class, which is a class from selenium library.
	Select dropdown = new Select(element);
	
//Selecting the option.Index means the position of option inside the dropdown list.
	dropdown.selectByValue("2");
	Thread.sleep(3000);
	
//To get the selected option.Here we are verifying whether the selected option works/The selection is successful.
	WebElement selectedOption=dropdown.getFirstSelectedOption();
//get.Text provides the value of the element.
	Assert.assertEquals(selectedOption.getText(), OPTION_2);
	
	dropdown.selectByValue("1");
	
	Thread.sleep(3000);
	WebElement selection=dropdown.getFirstSelectedOption();
	Assert.assertEquals (selection.getText(), OPTION_1);
	
}

}
