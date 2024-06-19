package contactlistapp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {

	private static final By SIGN_UP_BUTTON_LOCATOR=By.id("signup");
	private WebDriver driver;
	private WebDriverWait wait;

	public LogInPage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	

	public void openHomePage(String url) {
		this.driver.get(url);

	}
	public String getUrl() {
		String currentUrl=this.driver.getCurrentUrl();
		return currentUrl;
	}
	public AddUser clickSignup() {
		this.wait.until(ExpectedConditions.elementToBeClickable(SIGN_UP_BUTTON_LOCATOR));
		WebElement signUpButton =this.driver.findElement(SIGN_UP_BUTTON_LOCATOR);
		signUpButton.click();
		return new AddUser(this.driver);
	}


}