package contactlistapp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;
public class AddUser {
	
	private static final By FIRST_NAME_LOCATOR= By.id("firstName");
	private static final By LAST_NAME_LOCATOR=By.id("lastName");
	private static final By EMAIL_LOCATOR=By.id("email");
	private static final By PASSWORD_LOCATOR=By.id("password");
	private static final By SUBMIT_LOCATOR=By.id("submit");
	private static final By NEWCONTACT_LOCATOR=By.id("add-contact");
	
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public AddUser(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait (driver,Duration.ofSeconds(30));
	}

	public String getUrl() {
		String currentUrl=this.driver.getCurrentUrl();
		return currentUrl;
	}
	
	public String makeEmailRandom (String email)
	  {
		String parts[] = email.split ("@");
		String name = parts[0];	
		String domain = parts[1];	 
		Random rand = new Random ();
		int rand_int1 = rand.nextInt (10000);
		 return name + rand_int1 +"@" + domain;
	  }
	
	public AddNewContact addUserdetails(String fname,String lname,String emailid,String password) {
		enterFirstName(fname);
		enterLastName(lname);
		enterEmail(makeEmailRandom(emailid));
		enterPassword(password);
		clickSubmit();
		this.wait.until(ExpectedConditions.elementToBeClickable(NEWCONTACT_LOCATOR));
		return new AddNewContact (this.driver);
	}
	
	public void enterFirstName(String fname){
	this.wait.until(ExpectedConditions.elementToBeClickable(FIRST_NAME_LOCATOR));
	WebElement firstNameTextBox = this.driver.findElement(FIRST_NAME_LOCATOR);
	firstNameTextBox.sendKeys(fname);
	}
	
	public void enterLastName(String lname){
		this.wait.until(ExpectedConditions.elementToBeClickable(LAST_NAME_LOCATOR));
		WebElement lastNameTextBox = this.driver.findElement(LAST_NAME_LOCATOR);
		lastNameTextBox.sendKeys(lname);
	}
	
	public void enterEmail(String emailid) {
		this.wait.until(ExpectedConditions.elementToBeClickable(EMAIL_LOCATOR));
		WebElement emailTextBox = this.driver.findElement(EMAIL_LOCATOR);
		emailTextBox.sendKeys(emailid);
	}
	
	public void enterPassword(String password) {
		this.wait.until(ExpectedConditions.elementToBeClickable(PASSWORD_LOCATOR));
		WebElement passwordTextBox = this.driver.findElement(PASSWORD_LOCATOR);
		passwordTextBox.sendKeys(password);
	
}
	public void clickSubmit() {
		this.wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_LOCATOR));
		WebElement submitButton = this.driver.findElement(SUBMIT_LOCATOR);
		submitButton.click();
	}
}




