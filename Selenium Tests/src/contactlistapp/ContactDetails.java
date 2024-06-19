package contactlistapp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactDetails {

	private WebDriver driver;
	private WebDriverWait wait;
	
	public ContactDetails(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait (driver,Duration.ofSeconds(30));
	}
public String getURL() {
	String currentURL=driver.getCurrentUrl();
	return currentURL;
}
public AddNewContact deleteContact()  {
	By DELETE_CONTACT_LOCATOR=By.id("delete");
	this.wait.until(ExpectedConditions.elementToBeClickable(DELETE_CONTACT_LOCATOR));
	WebElement deletecontactButton=driver.findElement(DELETE_CONTACT_LOCATOR);
	deletecontactButton.click();
	return new AddNewContact (driver);

}

}
