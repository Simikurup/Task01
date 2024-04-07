package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class VplTestsSampleToRead {
	/*
	 Open the browser
	 1.Open home page
	 2.get home page url
	 	3.check that home page url is correct
	 4.Get home page title
	 	5.Check the home page title is correct
	 6.Find search box
	 7.Type the keyword in search box
	 8.Find the search button
	 9.Click the search button (This displays results page)
	 10.get results page url
	 	11.check the results page url is correct
	 12.Get results page title
	 	13.check the results page title is correct
	 14.Find the pagination label
	 15.get pagination label text
	 	16.check the pagination label text is correct
	 Close the browser
	 */
	

	@Test
	public void searchReturnsResultsTest() throws InterruptedException
	{
		//Opening the browser is done when we create a driver for the Selenium library.driver is an object that we use to interact with the website.
		//ChromeDriver is a class from the selenium library.
		ChromeDriver driver= new ChromeDriver();
		//1.To open the home page: driver.get() opens the site .The parameter of get method should be the url of the page.
		driver.get("https://www.vpl.ca/");
		//Maximizing the browser window
		driver.manage().window().maximize();
		//2.get the home page url:driver.getCurrentUrl() get the url of the page and returns it as a string variable.
		String homePageUrl=driver.getCurrentUrl();
		//Displays url in the console
		System.out.println("Home page url : " +homePageUrl);
		//3.check that home page url is correct:We use assertion
		Assert.assertEquals(homePageUrl, "https://www.vpl.ca/");
		//4.Get home page title
		String homePageTitle=driver.getTitle();
		//Displays title in the console
		System.out.println("Home page title : " +homePageTitle);
		//5.Check the home page title is correct
		Assert.assertEquals(homePageTitle,"Home | Vancouver Public Library" );
		//It is possible for this method to generate run time exception.We want the people who is using it to know it thats why we put "throws InterruptedException" for the method.
		Thread.sleep(5000);
	//6.Find search box:First we need to find the HTML code of search box.Its an element with input 'tag' it has the attribute 'id'.Copy the value of the id/name of the attribute.
	//Every time we need to find an element,In selenium we need to create the locator (similar to address).
	//First we will create the locator.We use the class named 'By'to create the locator.By is a class from the selenium library.There are different kind of locators eg)id,name,xpath.Parameter will be the value of the id/name.
		By searchBoxLocator=By.id("edit-keyword");
	//Next step is to find the element.The driver would be finding the element.The driver will look for the locator in the page.The result of it would be saved into a WebElement type.
	//The result of driver.findElement will be a variable and is saved to 'searchBox'.Web element is a class from Selenium library.
		WebElement searchBox = driver.findElement(searchBoxLocator);
	//7. Type the keyword in the search box :The WebElement variable has the methods that operates on the element.The driver has methods that operates at the page level.sendKeys type the keyword in the search box.
	//The parameter is the keyword.
		searchBox.sendKeys("java");
	//8.Find the search button	
		By searchButtonLocator=By.name("op");
		WebElement searchButton = driver.findElement(searchButtonLocator);
	// 9.Click the search button (This displays results page)
		searchButton.click();
	//10.get results page url
		String searchResulturl=driver.getCurrentUrl();
		System.out.println("Search page url: "+searchResulturl);
	//11.check the results page url is correct:Here we would be checking if the url is correct until .com part.Here we can use 'startsWith'/'Contains'
		Assert.assertTrue(searchResulturl.startsWith("https://vpl.bibliocommons.com"));
	//12.Get results page title
		String resultPageTitle=driver.getTitle();
		System.out.println("Search results page title : "+resultPageTitle);
	//13.check the results page title is correct.Unlike printing in the above step and copying.We can copy from the HTML>Head>Expand head>link data>Title
		Assert.assertEquals(resultPageTitle, "Search | Vancouver Public Library | BiblioCommons");
	// 14.Find the pagination label	
	By topPaginattionLocator =By.xpath("(//span[@data-key='pagination-text'])[1]");
	WebElement topPagination=driver.findElement(topPaginattionLocator);
// 15.get pagination label text
	String topPaginationtext =topPagination.getText();
	System.out.println(topPaginationtext);
//16.check the pagination label text is correct		
	Assert.assertTrue(topPaginationtext.startsWith("1 to 10"));
		
	//It will take 5 seconds or 5000ms waiting period to close the browser.
		Thread.sleep(5000);
	//Close the browser.
		driver.quit();
		
	}
}
