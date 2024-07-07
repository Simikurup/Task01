package contactlistapp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddContactPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private static final By FIRST_NAME_ID= By.id("firstName");
	private static final By LAST_NAME_ID=By.id("lastName");
	private static final By EMAIL_ID=By.id("email");
	private static final By BIRTHDATE_ID=By.id("birthdate");
	private static final By PHONE_ID=By.id("phone");
	private static final By STREET_ADDRESS1_ID=By.id("street1");
	private static final By STREET_ADDRESS2_ID=By.id("street2");
	private static final By CITY_ID=By.id("city");
	private static final By STATEPROVINCE_ID=By.id("stateProvince");
	private static final By POSTALCODE_ID=By.id("postalCode");
	private static final By COUNTRY_ID=By.id("country");
	private static final By SUBMITBUTTON_ID=By.id("submit");

	
	public AddContactPage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	 public String getURL() {
		return this.driver.getCurrentUrl();
		 }
	 
	public void enterContactInfo(String fname,String lname,String Birthdate,String emailid,String phone,String address1,String address2,String city,String state,String postalcode,String country) throws InterruptedException {
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
		 
	}
	public AddNewContactPage saveChanges() {
		 clickSubmit();
		 this.wait.until(ExpectedConditions.urlToBe(ContactListTest.CONTACTLIST_URL));
		 return new AddNewContactPage (this.driver);
	 }
	 private void enterFirstName(String fname){
			this.wait.until(ExpectedConditions.elementToBeClickable(FIRST_NAME_ID));
			WebElement TextBox = this.driver.findElement(FIRST_NAME_ID);
			TextBox.sendKeys(fname);
			}
			
	 private void enterLastName(String lname){
				this.wait.until(ExpectedConditions.elementToBeClickable(LAST_NAME_ID));
				WebElement TextBox = this.driver.findElement(LAST_NAME_ID);
				TextBox.sendKeys(lname);
			}
	 private void enterDateOfBirth(String birthDate) {
				this.wait.until(ExpectedConditions.elementToBeClickable(BIRTHDATE_ID));
				WebElement TextBox = this.driver.findElement(BIRTHDATE_ID);
				TextBox.sendKeys(birthDate);
			}
			
	 private void enterEmail(String emailid) {
				this.wait.until(ExpectedConditions.elementToBeClickable(EMAIL_ID));
				WebElement TextBox = this.driver.findElement(EMAIL_ID);
				TextBox.sendKeys(emailid);
			}
		
	 private void enterPhoneNo(String phone) {
				this.wait.until(ExpectedConditions.elementToBeClickable(PHONE_ID));
				WebElement TextBox = this.driver.findElement(PHONE_ID);
				TextBox.sendKeys(phone);
			}
			
	 private void enterStreetAddress1(String address1) {
				this.wait.until(ExpectedConditions.elementToBeClickable(STREET_ADDRESS1_ID));
				WebElement TextBox = this.driver.findElement(STREET_ADDRESS1_ID);
				TextBox.sendKeys(address1);
			}
			
	 private void enterStreetAddress2(String address2) {
				this.wait.until(ExpectedConditions.elementToBeClickable(STREET_ADDRESS2_ID));
				WebElement TextBox = this.driver.findElement(STREET_ADDRESS2_ID);
				TextBox.sendKeys(address2);
			}
			
	 private void enterCity(String city) {
				this.wait.until(ExpectedConditions.elementToBeClickable(CITY_ID));
				WebElement TextBox = this.driver.findElement(CITY_ID);
				TextBox.sendKeys(city);
			}
	 private void enterState(String state) {
				this.wait.until(ExpectedConditions.elementToBeClickable(STATEPROVINCE_ID));
				WebElement TextBox = this.driver.findElement(STATEPROVINCE_ID);
				TextBox.sendKeys(state);
			}
	 private void enterpostalcode(String postalcode) {
				this.wait.until(ExpectedConditions.elementToBeClickable(POSTALCODE_ID));
				WebElement TextBox = this.driver.findElement(POSTALCODE_ID);
				TextBox.sendKeys(postalcode);
			}
	 private void entercountry(String country) {
				this.wait.until(ExpectedConditions.elementToBeClickable(COUNTRY_ID));
				WebElement TextBox = this.driver.findElement(COUNTRY_ID);
				TextBox.sendKeys(country);
			}
	 private void clickSubmit() {
				this.wait.until(ExpectedConditions.elementToBeClickable(SUBMITBUTTON_ID));
				WebElement button = this.driver.findElement(SUBMITBUTTON_ID);
				button.click();
			}

}
