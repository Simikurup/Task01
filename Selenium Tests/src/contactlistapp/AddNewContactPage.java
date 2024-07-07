package contactlistapp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddNewContactPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private static final By ADD_NEW_CONTACT_BUTTON_ID=By.id("add-contact");
	private static final By NAME_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[2]");
	private static final By DOB_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[3]");
	private static final By EMAIL_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[4]");
	private static final By PHONE_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[5]");
	private static final By ADDRESS_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[6]");
	private static final By CITY_STATE_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[7]");
	private static final By COUNTRY_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[8]");
	

	public AddNewContactPage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	public String getURL() {
		return this.driver.getCurrentUrl();
		}
	
	public AddContactPage addANewContact() {
		this.wait.until(ExpectedConditions.elementToBeClickable(ADD_NEW_CONTACT_BUTTON_ID));
		WebElement addNewContactButton = this.driver.findElement(ADD_NEW_CONTACT_BUTTON_ID);
		addNewContactButton.click();
		return new AddContactPage(this.driver);
	}
	//Generate locator using variable
	private By pageLocator(String text) {
		String locatorText = "//td[text()='"+text+"']";
		return By.xpath(locatorText);
	}


	public boolean isNameDisplayed(String firstName,String lastName) {
		String name=firstName +" "+  lastName;
		By displayname=pageLocator (name);
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(displayname));
		return true;
	}

	public boolean isDOBDisplayed(String dateOfBirth) {
		By birthdate=pageLocator(dateOfBirth);
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(birthdate));
		return true;
	}

	public boolean isEmailDisplayed(String email) {
		By mail=pageLocator(email);
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(mail));
		return true;
	}

	public boolean isPhoneNoDisplayed(String phone) {
		By phoneNumber=pageLocator(phone);
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumber));
		return true;
	}

	public boolean isAddressDisplayed(String address1,String address2) {
		String address=address1+" " +address2;
		By fullAddress=pageLocator(address);
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(fullAddress));
		return true;
	}

	public boolean isCityStatePostalcodeDisplayed(String city,String state,String postalcode) {
		String addressdetails=city+" " +state+" "+postalcode;
		By completeAddress=pageLocator(addressdetails);
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(completeAddress));
		return true;
	}
	public boolean isCountryDisplayed(String country) {
		By countryName=pageLocator(country);
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(countryName));
		return true;
	}
	//Generate locator using variable
		private By dobLocator(String birthdate) {
		String locatorText="//td[text()='"+birthdate+"']";
		return By.xpath(locatorText);
	}
	public ContactDetailsPage clickContact(String birthDate)  {
		By dob= dobLocator(birthDate);
		this.wait.until(ExpectedConditions.elementToBeClickable(dob));
		WebElement nameLabel=driver.findElement(dob);
		nameLabel.click();
		return new ContactDetailsPage(driver);
	}
	public boolean isDateOfBirthDisplayed(String dob) {
		By birthdate=pageLocator(dob);
		try {
			this.wait.until(ExpectedConditions.elementToBeClickable(birthdate));
			return true;
		}
		catch (Exception e) {
			return false;
		}
		
	}
	public String getName() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(NAME_LOCATOR));
		WebElement nameLabel=driver.findElement(NAME_LOCATOR);
		return nameLabel.getText();
		
	}
	public String getDOB() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(DOB_LOCATOR));
		WebElement dobLabel=driver.findElement(DOB_LOCATOR);
		return dobLabel.getText();
	}
	public String getEmail() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_LOCATOR));
		WebElement emailLabel=driver.findElement(EMAIL_LOCATOR);
		return emailLabel.getText();
	}
	
	public String getPhoneNo() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(PHONE_LOCATOR));
		WebElement phoneLabel=driver.findElement(PHONE_LOCATOR);
		return phoneLabel.getText();
	}
	public String getAddress() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(ADDRESS_LOCATOR));
		WebElement addressLabel=driver.findElement(ADDRESS_LOCATOR);
		return addressLabel.getText();
	}
	public String getCityState() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(CITY_STATE_LOCATOR));
		WebElement cityLabel=driver.findElement(CITY_STATE_LOCATOR);
		return cityLabel.getText();
	}
	public String getCountry() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(COUNTRY_LOCATOR));
		WebElement countryLabel=driver.findElement(COUNTRY_LOCATOR);
		return countryLabel.getText();
	}
	
}

