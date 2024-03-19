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

public class AutotradeRVsCategoryEdited {
	private static final String POSTAL_CODE = "T9E 7L1";
	private static final String MAXPRICE = "1000000";
	private static final String RVS_MAKE_SELECTION = "Alpine";
	private static final String RVS_TYPE_SELECTION = "Fifth Wheel";
	private static final String HOME_PAGE_URL = "https://wwwa.autotrader.ca/";
	private static final String RESULTS_PAGE_URL="https://www.autotrader.ca/rv";
	private ChromiumDriver driver;
	private WebDriverWait wait;


	private static final By RVS_LOCATOR =By.xpath("(//a[text()='RVs'])[2]");
	private static final By TYPE_LOCATOR=By.id("rfTypes");
	private static final By MAKE_LOCATOR=By.id("rfMakes");
	private static final By MAXPRICE_LOCATOR=By.id("rfPriceHigh");
	private static final By POSTAL_CODE_LOCATOR=By.id("locationAddress");
	private static final By SEARCH_BUTTON_LOCATOR=By.id("SearchButton");
	private static final By RESULT_COUNT_LOCATOR=By.id("sbCount");
	private static final By RESULTPAGE_MAKEFILTER_LOCATOR=By.id("faceted-Make");
	private static final By RESULTPAGE_TYPEFILTER_LOCATOR=By.id("faceted-VehicleType");
	private static final By RESULTPAGE_CONDITIONFILTER_LOCATOR=By.id("faceted-condition");


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
		openHomePage();

		Assert.assertEquals(getHomePageUrl(), HOME_PAGE_URL);

		selectCategoryRVs();

		selectType(RVS_TYPE_SELECTION);

		selectMake(RVS_MAKE_SELECTION);

		selectMaxPrice(MAXPRICE);

		Assert.assertEquals(getMaxPriceSelected(),MAXPRICE);

		enterPostalCode(POSTAL_CODE);

		executeSearch();

		Assert.assertTrue(getSearchResultCount()>0);

		Assert.assertEquals(getResultsPageMakeFilter(), RVS_MAKE_SELECTION);

		Assert.assertEquals(getResultsPageTypeFilter(), RVS_TYPE_SELECTION );

		Assert.assertTrue(getResultsPageConditionFilter().contains("New"));
		Assert.assertTrue(getResultsPageConditionFilter().contains("Used"));
	}
	private void openHomePage(){
		driver.get(HOME_PAGE_URL);

	}
	private String getHomePageUrl() {
		String currentURL=driver.getCurrentUrl();
		return currentURL;
	}
	private void selectCategoryRVs(){
		WebElement categoryRVs=driver.findElement(RVS_LOCATOR);//2
		categoryRVs.click();
	}

	private void selectType(String type)  {
		wait.until(ExpectedConditions.elementToBeClickable(TYPE_LOCATOR));//3
		WebElement typeElement=driver.findElement(TYPE_LOCATOR);
		Select typeDropDown= new Select(typeElement);
		typeDropDown.selectByValue(type);
	}

	private void selectMake(String make) {
		wait.until(ExpectedConditions.elementToBeClickable(MAKE_LOCATOR));//4
		WebElement makeElement=driver.findElement(MAKE_LOCATOR);
		Select makeDropDown=new Select (makeElement);
		makeDropDown.selectByValue(make);
	}
	private void selectMaxPrice(String maxPrice) {
		wait.until(ExpectedConditions.elementToBeClickable(MAXPRICE_LOCATOR));
		WebElement maxPriceElement=driver.findElement(MAXPRICE_LOCATOR);//5
		Select maxPriceDropDown=new Select(maxPriceElement);
		maxPriceDropDown.selectByValue(maxPrice);
	}
	private String getMaxPriceSelected() {
		WebElement maxPriceElement=driver.findElement(MAXPRICE_LOCATOR);//5
		Select maxPriceDropDown=new Select(maxPriceElement);
		WebElement selectMaxPrice= maxPriceDropDown.getFirstSelectedOption();
		String maxPriceValue= selectMaxPrice.getText();
		String replaceDollar=maxPriceValue.replace("$", "");
		String replaceComma=replaceDollar.replace(",", "");
		return replaceComma;
	}
	private void enterPostalCode(String postalCode) {
		wait.until(ExpectedConditions.elementToBeClickable(POSTAL_CODE_LOCATOR));//6
		WebElement postalCodeTextBox=driver.findElement(POSTAL_CODE_LOCATOR);
		postalCodeTextBox.sendKeys(postalCode);
	}

	private void executeSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON_LOCATOR));
		WebElement searchButtonElement=driver.findElement(SEARCH_BUTTON_LOCATOR);//8
		searchButtonElement.click();
	}
	private int getSearchResultCount() {
		WebElement resultCountElement=driver.findElement(RESULT_COUNT_LOCATOR);//9 
		String resultCountValue=resultCountElement.getText();
		int resultCount=Integer.parseInt(resultCountValue);
		return resultCount;
	}
	private String getResultsPageMakeFilter() {
		WebElement resultPageMakeFilterElement=driver.findElement(RESULTPAGE_MAKEFILTER_LOCATOR);//10
		String resultPageMakeFilterValue=resultPageMakeFilterElement.getText();
		return resultPageMakeFilterValue;
	}
	private String getResultsPageTypeFilter(){
		WebElement resultPageTypeFilterElement=driver.findElement(RESULTPAGE_TYPEFILTER_LOCATOR);//11
		String resultPageTypeFilterValue=resultPageTypeFilterElement.getText();
		return resultPageTypeFilterValue;
	}
	private String getResultsPageConditionFilter(){
		WebElement resultPageConditionFilterElement=driver.findElement(RESULTPAGE_CONDITIONFILTER_LOCATOR);//12
		String resultPageConditionFilterValue=resultPageConditionFilterElement.getText();
		return resultPageConditionFilterValue;
	}

}	










