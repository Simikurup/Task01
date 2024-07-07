package contactlistapp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactDetailsPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private static final By DELETE_CONTACT_ID=By.id("delete");
	
	public ContactDetailsPage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait (driver,Duration.ofSeconds(30));
	}
public String getURL() {
	return driver.getCurrentUrl();
	
}
public AddNewContactPage deleteContact()  {
	this.wait.until(ExpectedConditions.elementToBeClickable(DELETE_CONTACT_ID));
	WebElement deletecontactButton=driver.findElement(DELETE_CONTACT_ID);
	deletecontactButton.click();
	return new AddNewContactPage (driver);

}

}
