package contactlistapp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddContact {
	private WebDriver driver;
	private WebDriverWait wait;
	private static final By FIRST_NAME_LOCATOR= By.id("firstName");
	private static final By LAST_NAME_LOCATOR=By.id("lastName");
	private static final By EMAIL_LOCATOR=By.id("email");
	private static final By BIRTHDATE_LOCATOR=By.id("birthdate");
	private static final By PHONE_LOCATOR=By.id("phone");
	private static final By STREET_ADDRESS1_LOCATOR=By.id("street1");
	private static final By STREET_ADDRESS2_LOCATOR=By.id("street2");
	private static final By CITY_LOCATOR=By.id("city");
	private static final By STATEPROVINCE_LOCATOR=By.id("stateProvince");
	private static final By POSTALCODE_LOCATOR=By.id("postalCode");
	private static final By COUNTRY_LOCATOR=By.id("country");
	private static final By SUBMITBUTTON_LOCATOR=By.id("submit");

	
	public AddContact(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	 public String getURL() {
		 String currenturl= this.driver.getCurrentUrl();
		 return currenturl;
	 }
	 
	public AddNewContact contactDetails(String fname,String lname,String Birthdate,String emailid,String phone,String address1,String address2,String city,String state,String postalcode,String country) throws InterruptedException {
		 enterFirstName(fname);
		 enterLastName(lname);
		 enterDateOfBirth(Birthdate);
		 enterEmail(emailid);
		 enterPhoneNo(phone);
		 enterStreetAddress1(address1);
		 enterStreetAddress2(address2);
		 enterCity(city);
		 enterState(state);
		 enterpostalcode(postalcode);
		 entercountry(country);
		 ClickSubmit();
		 this.wait.until(ExpectedConditions.urlToBe(ContactListTest.CONTACTLIST_URL));
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
			public void enterDateOfBirth(String Birthdate) {
				this.wait.until(ExpectedConditions.elementToBeClickable(BIRTHDATE_LOCATOR));
				WebElement birthDateTextBox = this.driver.findElement(BIRTHDATE_LOCATOR);
				birthDateTextBox.sendKeys(Birthdate);
			}
			
			public void enterEmail(String emailid) {
				this.wait.until(ExpectedConditions.elementToBeClickable(EMAIL_LOCATOR));
				WebElement emailTextBox = this.driver.findElement(EMAIL_LOCATOR);
				emailTextBox.sendKeys(emailid);
			}
		
			public void enterPhoneNo(String phone) {
				this.wait.until(ExpectedConditions.elementToBeClickable(PHONE_LOCATOR));
				WebElement phoneTextBox = this.driver.findElement(PHONE_LOCATOR);
				phoneTextBox.sendKeys(phone);
			}
			
			public void enterStreetAddress1(String address1) {
				this.wait.until(ExpectedConditions.elementToBeClickable(STREET_ADDRESS1_LOCATOR));
				WebElement address1TextBox = this.driver.findElement(STREET_ADDRESS1_LOCATOR);
				address1TextBox.sendKeys(address1);
			}
			
			public void enterStreetAddress2(String address2) {
				this.wait.until(ExpectedConditions.elementToBeClickable(STREET_ADDRESS2_LOCATOR));
				WebElement address2TextBox = this.driver.findElement(STREET_ADDRESS2_LOCATOR);
				address2TextBox.sendKeys(address2);
			}
			
			public void enterCity(String city) {
				this.wait.until(ExpectedConditions.elementToBeClickable(CITY_LOCATOR));
				WebElement cityTextBox = this.driver.findElement(CITY_LOCATOR);
				cityTextBox.sendKeys(city);
			}
			public void enterState(String state) {
				this.wait.until(ExpectedConditions.elementToBeClickable(STATEPROVINCE_LOCATOR));
				WebElement stateTextBox = this.driver.findElement(STATEPROVINCE_LOCATOR);
				stateTextBox.sendKeys(state);
			}
			public void enterpostalcode(String postalcode) {
				this.wait.until(ExpectedConditions.elementToBeClickable(POSTALCODE_LOCATOR));
				WebElement postalcodeTextBox = this.driver.findElement(POSTALCODE_LOCATOR);
				postalcodeTextBox.sendKeys(postalcode);
			}
			public void entercountry(String country) {
				this.wait.until(ExpectedConditions.elementToBeClickable(COUNTRY_LOCATOR));
				WebElement countryTextBox = this.driver.findElement(COUNTRY_LOCATOR);
				countryTextBox.sendKeys(country);
			}
			public void ClickSubmit() {
				this.wait.until(ExpectedConditions.elementToBeClickable(SUBMITBUTTON_LOCATOR));
				WebElement submitButton = this.driver.findElement(SUBMITBUTTON_LOCATOR);
				submitButton.click();
			}

}
