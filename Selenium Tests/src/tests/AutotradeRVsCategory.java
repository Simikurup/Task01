package tests;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutotradeRVsCategory {
private static final String POSTAL_CODE = "T9E 7L1";
private static final String MAXPRICE = "1000000";
private static final String RVS_MAKE_SELECTION = "Alpine";
private static final String RVS_TYPE_SELECTION = "Fifth Wheel";
private static final String HOME_PAGE_URL = "https://wwwa.autotrader.ca/";
private ChromiumDriver driver;
private WebDriverWait wait;


private static final By RVS_LOCATOR =By.xpath("(//a[text()='RVs'])[2]");
private static final By TYPE_LOCATOR=By.id("rfTypes");
private static final By MAKE_LOCATOR=By.id("rfMakes");
private static final By MAXPRICE_LOCATOR=By.id("rfPriceHigh");
private static final By POSTAL_CODE_LOCATOR=By.id("locationAddress");
private static final By NEW_CHECKBOX_LOCATOR=By.id("rfNew");
private static final By SEARCH_BUTTON_LOCATOR=By.id("SearchButton");
private static final By RESULT_COUNT_LOCATOR=By.id("sbCount");
private static final By RESULTPAGE_MAKEFILTER_LOCATOR=By.id("faceted-Make");
private static final By RESULTPAGE_TYPEFILTER_LOCATOR=By.id("faceted-VehicleType");
private static final By RESULTPAGE_CONDITIONFILTER_LOCATOR=By.id("faceted-condition");
private static final By RESULTPAGE_LOCATIONFILTER_LOCATOR=By.id("faceted-Location");

/*
1. open autotrader.ca
2. select RVs category
3. select FIFTH WHEEL sub category
4. select ALPINE as make
5. select 100.000 as price
6. select T9E 7L1 as postal code
7. un-check NEW
8. click SEARCH
9. verify that there are results on the next page
10. verify that the make filter on left side is ALPINE
11. verify that the type filter on the left side is FIFTH WHEEL
12. verify that the condition filter on the left side is USED
13. verify that the location filter on the left side is T9E 7L1	
*/

@BeforeMethod	
public void setUp() {
	driver =new ChromeDriver();
	wait= new WebDriverWait (driver,Duration.ofSeconds(30));
	driver.manage().window().maximize();
}

@AfterMethod
public void tearDown() {
	driver.quit();
}
@Test
public void testCategoryRVs() throws InterruptedException {
	driver.get(HOME_PAGE_URL);//1
	String currentURL=driver.getCurrentUrl();
	Assert.assertEquals(currentURL, HOME_PAGE_URL);
	
	WebElement categoryRVs=driver.findElement(RVS_LOCATOR);//2
	categoryRVs.click();
	
	wait.until(ExpectedConditions.elementToBeClickable(TYPE_LOCATOR));//3
	WebElement typeElement=driver.findElement(TYPE_LOCATOR);
	Select typeDropDown= new Select(typeElement);
	typeDropDown.selectByValue(RVS_TYPE_SELECTION);
			
	WebElement selectType=typeDropDown.getFirstSelectedOption();
	Assert.assertEquals(selectType.getText(), RVS_TYPE_SELECTION);
	
	wait.until(ExpectedConditions.elementToBeClickable(MAKE_LOCATOR));//4
	
	WebElement makeElement=driver.findElement(MAKE_LOCATOR);
	Select makeDropDown=new Select (makeElement);
	Thread.sleep(3000);//Explicit wait to be provided need to discuss
	makeDropDown.selectByValue(RVS_MAKE_SELECTION);
	
	makeElement=driver.findElement(MAKE_LOCATOR);
	WebElement selectMake=makeDropDown.getFirstSelectedOption();
	Assert.assertEquals(selectMake.getText(),RVS_MAKE_SELECTION);
	
	WebElement maxPriceElement=driver.findElement(MAXPRICE_LOCATOR);//5
	Select maxPriceDropDown=new Select(maxPriceElement);
	maxPriceDropDown.selectByValue(MAXPRICE);
	
	maxPriceElement=driver.findElement(MAXPRICE_LOCATOR);
	WebElement selectMaxPrice= maxPriceDropDown.getFirstSelectedOption();
	String maxPriceValue= selectMaxPrice.getText();
	String replaceDollar=maxPriceValue.replace("$", "");
	String replaceComma=replaceDollar.replace(",", "");
	Assert.assertEquals(replaceComma,MAXPRICE);
	
	wait.until(ExpectedConditions.elementToBeClickable(POSTAL_CODE_LOCATOR));//6
	WebElement postalCodeTextBox=driver.findElement(POSTAL_CODE_LOCATOR);
	Thread.sleep(3000);//Explicit wait to be provided need to discuss
	postalCodeTextBox.sendKeys(POSTAL_CODE);
	
	wait.until(ExpectedConditions.elementToBeClickable(NEW_CHECKBOX_LOCATOR));//7 This script failed
	WebElement checkBoxElement=driver.findElement(NEW_CHECKBOX_LOCATOR);
	if (checkBoxElement.isSelected()) {
		checkBoxElement.click();
	}
	
	WebElement searchButtonElement=driver.findElement(SEARCH_BUTTON_LOCATOR);//8
	Thread.sleep(3000);
	searchButtonElement.click();//Explicit wait to be provided need to discuss
	
	WebElement resultCountElement=driver.findElement(RESULT_COUNT_LOCATOR);//9 
	String resultCountValue=resultCountElement.getText();
	int resultCount=Integer.parseInt(resultCountValue);
	Assert.assertTrue(resultCount>0);
	
	WebElement resultPageMakeFilterElement=driver.findElement(RESULTPAGE_MAKEFILTER_LOCATOR);//10
	String resultPageMakeFilterValue=resultPageMakeFilterElement.getText();
	Assert.assertEquals(resultPageMakeFilterValue, RVS_MAKE_SELECTION);
	
	WebElement resultPageTypeFilterElement=driver.findElement(RESULTPAGE_TYPEFILTER_LOCATOR);//11
	String resultPageTypeFilterValue=resultPageTypeFilterElement.getText();
	Assert.assertEquals(resultPageTypeFilterValue, RVS_TYPE_SELECTION );
	
	WebElement resultPageConditionFilterElement=driver.findElement(RESULTPAGE_CONDITIONFILTER_LOCATOR);//12
	String resultPageConditionFilterValue=resultPageConditionFilterElement.getText();
	Assert.assertTrue(resultPageConditionFilterValue.contains("New"));
	Assert.assertTrue(resultPageConditionFilterValue.contains("Used"));
	
	WebElement resultPageLocationFilterElement=driver.findElement(RESULTPAGE_LOCATIONFILTER_LOCATOR);//13
	String resultPageLocationFilterValue=resultPageLocationFilterElement.getText();
	Assert.assertEquals(resultPageLocationFilterValue, POSTAL_CODE );
	
	
	
}
	
	
}	
	
	
	
	
	
	
	
	
	

