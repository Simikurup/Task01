package contactlistapp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddNewContact {
	private WebDriver driver;
	private WebDriverWait wait;
	private static final By ADD_NEW_CONTACT_BUTTON_LOCATOR=By.id("add-contact");
	


	public AddNewContact(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	public String getURL() {
		String currenturl= this.driver.getCurrentUrl();
		return currenturl;
	}
	public AddContact addANewContact() {
		this.wait.until(ExpectedConditions.elementToBeClickable(ADD_NEW_CONTACT_BUTTON_LOCATOR));
		WebElement addNewContactButton = this.driver.findElement(ADD_NEW_CONTACT_BUTTON_LOCATOR);
		addNewContactButton.click();
		return new AddContact(this.driver);
	}
	//Generate locator using variable
	private By pageLocator(String text) {
		String locatorText = "//td[text()='"+text+"']";
		return By.xpath(locatorText);
	}


	public boolean isNameDisplayed(String firstName,String lastName) {
		String name=firstName +" "+  lastName;
		By NAME_LOCATOR=pageLocator (name);
		this.wait.until(ExpectedConditions.elementToBeClickable(NAME_LOCATOR));
		WebElement nameLabel = this.driver.findElement(NAME_LOCATOR);
		return nameLabel.isDisplayed();
	}

	public boolean isDOBDisplayed(String dateOfBirth) {
		By DOB_LOCATOR=pageLocator(dateOfBirth);
		this.wait.until(ExpectedConditions.elementToBeClickable(DOB_LOCATOR));
		WebElement dobLabel = this.driver.findElement(DOB_LOCATOR);
		return dobLabel.isDisplayed();
	}

	public boolean isEmailDisplayed(String email) {
		By EMAIL_LOCATOR=pageLocator(email);
		this.wait.until(ExpectedConditions.elementToBeClickable(EMAIL_LOCATOR));
		WebElement emailLabel = this.driver.findElement(EMAIL_LOCATOR);
		return emailLabel.isDisplayed();
	}

	public boolean isPhoneNoDisplayed(String phone) {
		By PHONE_LOCATOR=pageLocator(phone);
		this.wait.until(ExpectedConditions.elementToBeClickable(PHONE_LOCATOR));
		WebElement phoneLabel = this.driver.findElement(PHONE_LOCATOR);
		return phoneLabel.isDisplayed();
	}

	public boolean isAddressDisplayed(String address1,String address2) {
		String address=address1+" " +address2;
		By ADDRESS_LOCATOR=pageLocator(address);
		this.wait.until(ExpectedConditions.elementToBeClickable(ADDRESS_LOCATOR));
		WebElement addressLabel = this.driver.findElement(ADDRESS_LOCATOR);
		return addressLabel.isDisplayed();
	}

	public boolean isCityStatePostalcodeDisplayed(String city,String state,String postalcode) {
		String addressdetails=city+" " +state+" "+postalcode;
		By ADDRESSDETAILS_LOCATOR=pageLocator(addressdetails);
		this.wait.until(ExpectedConditions.elementToBeClickable(ADDRESSDETAILS_LOCATOR));
		WebElement addressLabel = this.driver.findElement(ADDRESSDETAILS_LOCATOR);
		return addressLabel.isDisplayed();
	}
	public boolean isCountryDisplayed(String country) {
		By COUNTRY_LOCATOR=pageLocator(country);
		this.wait.until(ExpectedConditions.elementToBeClickable(COUNTRY_LOCATOR));
		WebElement countryLabel = this.driver.findElement(COUNTRY_LOCATOR);
		return countryLabel.isDisplayed();
	}

	public ContactDetails clickContact()  {
		By DOB_LOCATOR=By.xpath("//td[text()='2000/03/10']");
		this.wait.until(ExpectedConditions.elementToBeClickable(DOB_LOCATOR));
		WebElement nameLabel=driver.findElement(DOB_LOCATOR);
		nameLabel.click();
		return new ContactDetails(driver);
	}
	public boolean isDateOfBirthDisplayed(String dob) {
		By DOB_LOCATOR=pageLocator(dob);
		WebElement dobLabel;
		try {
			this.wait.until(ExpectedConditions.elementToBeClickable(DOB_LOCATOR));
			dobLabel = this.driver.findElement(DOB_LOCATOR);
			return dobLabel.isDisplayed();
			} 
		catch (Exception e) {
			return false;
		}
		
	}
	public String verifyName() {
		By NAME_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[2]");
		WebElement nameLabel=driver.findElement(NAME_LOCATOR);
		return nameLabel.getText();
		
	}
	public String verifyDOB() {
		By DOB_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[3]");
		WebElement dobLabel=driver.findElement(DOB_LOCATOR);
		return dobLabel.getText();
	}
	public String verifyEmail() {
		By EMAIL_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[4]");
		WebElement emailLabel=driver.findElement(EMAIL_LOCATOR);
		return emailLabel.getText();
	}
	
	public String verifyPhoneNo() {
		By PHONE_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[5]");
		WebElement phoneLabel=driver.findElement(PHONE_LOCATOR);
		return phoneLabel.getText();
	}
	public String verifyAddress() {
		By ADDRESS_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[6]");
		WebElement addressLabel=driver.findElement(ADDRESS_LOCATOR);
		return addressLabel.getText();
	}
	public String verifyCityState() {
		By CITY_STATE_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[7]");
		WebElement cityLabel=driver.findElement(CITY_STATE_LOCATOR);
		return cityLabel.getText();
	}
	public String verifyCountry() {
		By COUNTRY_LOCATOR=By.xpath("//tr[@class='contactTableBodyRow'][1]//td[8]");
		WebElement countryLabel=driver.findElement(COUNTRY_LOCATOR);
		return countryLabel.getText();
	}
	
}

