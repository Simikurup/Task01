package tests.Saucedemopackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutStepOnePage {
	private WebDriver driver;
	private WebDriverWait wait;
	private static final By FIRSTNAME_LOCATOR=By.id("first-name");
	private static final By LASTNAME_LOCATOR=By.id("last-name");
	private static final By POSTALCODE_LOCATOR=By.id("postal-code");
	private static final By CONTINUE_BUTTON_LOCATOR=By.id("continue");
	
	public CheckoutStepOnePage(WebDriver driver) {
		this.driver=driver;
		this.wait= new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	public void enterUserInfo(String firstName,String lastName,String postalCode) {
		typeFirstName(firstName);
		typeLastName(lastName);
		typePostcode(postalCode);
	}
	public CheckoutStepTwoPage continueToNextPage() {
		clickContinueButton();
		return new CheckoutStepTwoPage(this.driver);
	}
	private void typeFirstName(String name) {
		wait.until(ExpectedConditions.elementToBeClickable(FIRSTNAME_LOCATOR));
		WebElement firstNameTextBox =driver.findElement(FIRSTNAME_LOCATOR);
		firstNameTextBox.sendKeys(name);
	}
	private void typeLastName(String name) {
		wait.until(ExpectedConditions.elementToBeClickable(LASTNAME_LOCATOR));
		WebElement lastNameTextBox =driver.findElement(LASTNAME_LOCATOR);
		lastNameTextBox.sendKeys(name);
	}
	private void typePostcode(String postcode) {
		wait.until(ExpectedConditions.elementToBeClickable(POSTALCODE_LOCATOR));
		WebElement postCodeTextBox =driver.findElement(POSTALCODE_LOCATOR);
		postCodeTextBox.sendKeys(postcode);
	}
	private void clickContinueButton() {
		wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON_LOCATOR));
		WebElement continueButton =driver.findElement(CONTINUE_BUTTON_LOCATOR);
		continueButton.click();
	}
	
}
