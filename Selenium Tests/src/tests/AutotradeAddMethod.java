package tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class AutotradeAddMethod {

	private ChromeDriver driver;
	private WebDriverWait wait;

	private static final String HOMEPAGE_URL="https://www.autotrader.ca/";
	private static final String CAR_MAKE_SELECTION="Audi";
	private static final String CAR_MODEL_SELECTION="A4";
	private static final String SEARCH_RESULTS_PAGE_URL="https://www.autotrader.ca/cars";
	private static final String POSTAL_CODE="v6l2y3";
	private static final String RESULTS_PAGE_TITLE="New & Used Audi A4 for sale in Vancouver | AutoTrader.ca";
	
	private static final By CAR_MAKE_LOCATOR=By.id("rfMakes");
	private static final By CAR_MODEL_LOCATOR=By.id("rfModel");
	private static final By POSTALCODE_LOCATOR=By.id("locationAddressV2");
	private static final By SHOW_ME_CARS_LOCATOR=By.id("SearchButton");
	private static final By RESULTS_COUNT_LOCATOR=By.id("sbCount");
	private static final By RESULTSPAGE_HEADER_LOCATOR=By.id("titleText");

	//1.open autotrader.ca
	//2. verify that home page is open
	//3. select a make like audi
	//4. select a model like a4
	//5. type a postal code like v6l2y3
	//6. search
	//7. verify that results page is open
	//8. Get header
	//9. Verify the selected make is included in the header
	//10.Verify the selected model is included in the header
	//11.Get total results count from header
	//12. verify that the total results count is positive

	@BeforeMethod	
	public void setUp()
	{
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

	@Test
	public void selectMake()  throws InterruptedException
	{
		openHomePage();

	//wait.until(ExpectedConditions.urlToBe(HOMEPAGE_URL));//2
		Assert.assertEquals(getHomePageUrl(), HOMEPAGE_URL);
		
		selectMake(CAR_MAKE_SELECTION); 
		
		Assert.assertEquals(getMakeSelection(), CAR_MAKE_SELECTION);

		selectModel(CAR_MODEL_SELECTION);

		Assert.assertEquals(getModelSelection(), CAR_MODEL_SELECTION);

		typePostalCode(POSTAL_CODE);
		// Do search using text box by clicking the enter key 
		executeSearch();
		
		//Clicking the Show cars button
		/* wait.until(ExpectedConditions.elementToBeClickable(SHOW_ME_CARS_LOCATOR));//6
 		WebElement showCarsButton=driver.findElement(SHOW_ME_CARS_LOCATOR);
 		showCarsButton.click();*/
	
	//	Assert.assertTrue(getResultsPageUrl().startsWith(SEARCH_RESULTS_PAGE_URL));//7

	//	Assert.assertTrue(getHeader().contains(CAR_MAKE_SELECTION));//9
	//	Assert.assertTrue(getHeader().contains(CAR_MODEL_SELECTION));//10


		//Get Title
		String resultsPageTitle=getTitle();//8
		Assert.assertEquals(resultsPageTitle, RESULTS_PAGE_TITLE);
		Assert.assertTrue(resultsPageTitle.contains(CAR_MAKE_SELECTION));//9
		Assert.assertTrue(resultsPageTitle.contains(CAR_MODEL_SELECTION));//10 

		Assert.assertTrue(getResultsCount()>0);
	}
	private void openHomePage() {
		driver.get(HOMEPAGE_URL);
	}

	private String getHomePageUrl() {
		String currentUrl=driver.getCurrentUrl();
		return currentUrl;
	}
	private void selectMake(String make) {
		wait.until(ExpectedConditions.elementToBeClickable(CAR_MAKE_LOCATOR));//3
		WebElement makeElement = driver.findElement(CAR_MAKE_LOCATOR);
		Select makeDropDown=new Select(makeElement);
		makeDropDown.selectByValue(make);
	}
	private String getMakeSelection() { 
		WebElement makeElement = driver.findElement(CAR_MAKE_LOCATOR);
		Select makeDropDown=new Select(makeElement);
		WebElement selectMake=makeDropDown.getFirstSelectedOption();
		String carMake=selectMake.getText();
		return carMake;
	}
	private void selectModel(String make) {
		wait.until(ExpectedConditions.elementToBeClickable(CAR_MODEL_LOCATOR));//4
		WebElement modelElement=driver.findElement(CAR_MODEL_LOCATOR);
		Select modelDropDown=new Select(modelElement);
		modelDropDown.selectByValue(make);
	}
	private String getModelSelection() {
		WebElement modelElement=driver.findElement(CAR_MODEL_LOCATOR);
		Select modelDropDown=new Select(modelElement);
		WebElement selectModel=modelDropDown.getFirstSelectedOption();
		String carModel=selectModel.getText();
		return carModel;
		
	}
	private void typePostalCode(String postcode) {
		wait.until(ExpectedConditions.elementToBeClickable(POSTALCODE_LOCATOR));//5
		WebElement postalCodeTextBox=driver.findElement(POSTALCODE_LOCATOR);
		postalCodeTextBox.sendKeys(postcode);
	}
	private void executeSearch() {
		WebElement postalCodeTextBox=driver.findElement(POSTALCODE_LOCATOR);
		postalCodeTextBox.sendKeys(POSTAL_CODE);
		postalCodeTextBox.sendKeys(Keys.ENTER);
	}
/*	private String getResultsPageUrl() {
		String resultsPageUrl=driver.getCurrentUrl();
		return resultsPageUrl;
		
	}
	private String getHeader() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(RESULTSPAGE_HEADER_LOCATOR));//8
		WebElement resultsPageHeader=driver.findElement(RESULTSPAGE_HEADER_LOCATOR);
		String headerText=resultsPageHeader.getText();
		return headerText;
	}*/
	private String getTitle() {
		String resultsPageTitle=driver.getTitle();
		return resultsPageTitle;
	}
	private int getResultsCount() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(RESULTS_COUNT_LOCATOR));//11
		WebElement searchCount=driver.findElement(RESULTS_COUNT_LOCATOR);
		String resultCountValue=searchCount.getText();
		int resultCount=Integer.parseInt(resultCountValue);//12
		return resultCount;
	}
}