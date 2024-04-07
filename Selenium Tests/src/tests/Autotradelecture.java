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



public class Autotradelecture {

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
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));	
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	@Test
	public void selectMake() throws InterruptedException
	{
		openHomePage();
	
		Assert.assertEquals(gethomePageUrl(), HOMEPAGE_URL);

		makeSelection(CAR_MAKE_SELECTION);

		Assert.assertEquals(getSelectedMake(), CAR_MAKE_SELECTION);
		
		modelSelection(CAR_MODEL_SELECTION);
		
		Assert.assertEquals(getSelectedModel(), CAR_MODEL_SELECTION);
		
		typeLocation(POSTAL_CODE);
		
		// Do search using text box by clicking the enter key 
		executeSearch();
		
		Assert.assertTrue( getresultsPageUrl().contains(SEARCH_RESULTS_PAGE_URL));

		String headerText=getHeader();
		Assert.assertTrue(headerText.contains(CAR_MAKE_SELECTION));//9
		Assert.assertTrue(headerText.contains(CAR_MODEL_SELECTION));//10

		//Wrong code
		Assert.assertTrue(getResultsCount()>0);
	}

	private void openHomePage(){
		driver.get(HOMEPAGE_URL);//1
	}
	
	private String gethomePageUrl() {
		String homePageURL=driver.getCurrentUrl();
		return homePageURL;
	}

	private void makeSelection(String make) {
		WebElement makeElement = driver.findElement(CAR_MAKE_LOCATOR);
		Select makeDropDown=new Select(makeElement);
		makeDropDown.selectByValue(make);

	}
	
	private String getSelectedMake() {
		WebElement makeElement = driver.findElement(CAR_MAKE_LOCATOR);
		Select makeDropDown=new Select(makeElement);
		
		WebElement selectMake=makeDropDown.getFirstSelectedOption();
		String selectedMake=selectMake.getText();
		return selectedMake;

	}
	
	private void modelSelection(String model) {
		wait.until(ExpectedConditions.elementToBeClickable(CAR_MODEL_LOCATOR));//4
		WebElement modelElement=driver.findElement(CAR_MODEL_LOCATOR);
		Select modelDropDown=new Select(modelElement);
		modelDropDown.selectByValue(model);
	}
	
	private String getSelectedModel() {
		wait.until(ExpectedConditions.elementToBeClickable(CAR_MODEL_LOCATOR));//4
		WebElement modelElement=driver.findElement(CAR_MODEL_LOCATOR);
		Select modelDropDown=new Select(modelElement);
		
		WebElement selectModel=modelDropDown.getFirstSelectedOption();
		String modelSelected=selectModel.getText();
		return modelSelected;
	}
	
	private void typeLocation(String location) {
		wait.until(ExpectedConditions.elementToBeClickable(POSTALCODE_LOCATOR));//5
		WebElement postalCodeTextBox=driver.findElement(POSTALCODE_LOCATOR);
		postalCodeTextBox.sendKeys(location);
	}
	
	private void executeSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(POSTALCODE_LOCATOR));//5
		WebElement postalCodeTextBox=driver.findElement(POSTALCODE_LOCATOR);
				
		postalCodeTextBox.sendKeys(Keys.ENTER);
	
	}
	private String getresultsPageUrl() {
		String resultPageUrl=driver.getCurrentUrl();
		return resultPageUrl;
	}
	private String getHeader() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(RESULTSPAGE_HEADER_LOCATOR));//8
		WebElement resultsPageHeader=driver.findElement(RESULTSPAGE_HEADER_LOCATOR);
		String headerText=resultsPageHeader.getText();
		return headerText;
		
	}
	private int getResultsCount() {
		WebElement resultsPageHeader=driver.findElement(RESULTSPAGE_HEADER_LOCATOR);
		String headerText=resultsPageHeader.getText();
		
		int indexOfLabel=headerText.indexOf(" ");
		String resultCountExtracted=headerText.substring(0, indexOfLabel);
		int count=Integer.parseInt(resultCountExtracted);
		return count;
	}
}
