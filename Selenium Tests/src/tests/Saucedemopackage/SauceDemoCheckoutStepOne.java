package tests.Saucedemopackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceDemoCheckoutStepOne {
	private WebDriver driver;
	private WebDriverWait wait;
	private static final By FIRSTNAME_LOCATOR=By.id("first-name");
	private static final By LASTNAME_LOCATOR=By.id("last-name");
	private static final By POSTALCODE_LOCATOR=By.id("postal-code");
	private static final By CONTINUE_BUTTON_LOCATOR=By.id("continue");
	
	public SauceDemoCheckoutStepOne(WebDriver driver, WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
	public void checkoutStepFirst(String firstname,String lastname,String postcode) {
		typeFirstName(firstname);
		typeLastName(lastname);
		typePostcode(postcode);
	}
	public SauceDemoCheckoutStepTwo clickContinue() {
		continueToNextPage();
		return new SauceDemoCheckoutStepTwo(this.driver,this.wait);
	}
	private void typeFirstName(String firstname) {
		wait.until(ExpectedConditions.elementToBeClickable(FIRSTNAME_LOCATOR));
		WebElement firstNameTextBox =driver.findElement(FIRSTNAME_LOCATOR);
		firstNameTextBox.sendKeys(firstname);
	}
	private void typeLastName(String lastname) {
		wait.until(ExpectedConditions.elementToBeClickable(LASTNAME_LOCATOR));
		WebElement lastNameTextBox =driver.findElement(LASTNAME_LOCATOR);
		lastNameTextBox.sendKeys(lastname);
	}
	private void typePostcode(String postcode) {
		wait.until(ExpectedConditions.elementToBeClickable(POSTALCODE_LOCATOR));
		WebElement postCodeTextBox =driver.findElement(POSTALCODE_LOCATOR);
		postCodeTextBox.sendKeys(postcode);
	}
	private void continueToNextPage() {
		wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON_LOCATOR));
		WebElement continueButton =driver.findElement(CONTINUE_BUTTON_LOCATOR);
		continueButton.click();
	}
	
}
