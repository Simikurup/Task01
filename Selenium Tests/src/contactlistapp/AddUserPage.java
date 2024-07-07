package contactlistapp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;
public class AddUserPage {
	
	private static final By FIRST_NAME_ID= By.id("firstName");
	private static final By LAST_NAME_ID=By.id("lastName");
	private static final By EMAIL_ID=By.id("email");
	private static final By PASSWORD_ID=By.id("password");
	private static final By SUBMIT_ID=By.id("submit");
	private static final By NEWCONTACT_ID=By.id("add-contact");
	
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public AddUserPage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait (driver,Duration.ofSeconds(30));
	}

	public String getUrl() {
		return this.driver.getCurrentUrl();
		
	}
	
	private String makeEmailRandom (String email)
	  {
		String parts[] = email.split ("@");
		String name = parts[0];	
		String domain = parts[1];	 
		Random rand = new Random ();
		int rand_int1 = rand.nextInt (10000);
		 return name + rand_int1 +"@" + domain;
	  }
	
	public AddNewContactPage addUserdetails(String fname,String lname,String emailid,String password) {
		enterFirstName(fname);
		enterLastName(lname);
		enterEmail(makeEmailRandom(emailid));
		enterPassword(password);
		clickSubmit();
		this.wait.until(ExpectedConditions.elementToBeClickable(NEWCONTACT_ID));
		return new AddNewContactPage (this.driver);
	}
	
	private void enterFirstName(String fname){
	this.wait.until(ExpectedConditions.elementToBeClickable(FIRST_NAME_ID));
	WebElement firstNameTextBox = this.driver.findElement(FIRST_NAME_ID);
	firstNameTextBox.sendKeys(fname);
	}
	
	private void enterLastName(String lname){
		this.wait.until(ExpectedConditions.elementToBeClickable(LAST_NAME_ID));
		WebElement lastNameTextBox = this.driver.findElement(LAST_NAME_ID);
		lastNameTextBox.sendKeys(lname);
	}
	
	private void enterEmail(String emailid) {
		this.wait.until(ExpectedConditions.elementToBeClickable(EMAIL_ID));
		WebElement emailTextBox = this.driver.findElement(EMAIL_ID);
		emailTextBox.sendKeys(emailid);
	}
	
	private void enterPassword(String password) {
		this.wait.until(ExpectedConditions.elementToBeClickable(PASSWORD_ID));
		WebElement passwordTextBox = this.driver.findElement(PASSWORD_ID);
		passwordTextBox.sendKeys(password);
	
}
	private void clickSubmit() {
		this.wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_ID));
		WebElement submitButton = this.driver.findElement(SUBMIT_ID);
		submitButton.click();
	}
}




