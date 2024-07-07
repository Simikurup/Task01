package contactlistapp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {

	private static final By SIGN_UP_BUTTON_ID=By.id("signup");
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
		return this.driver.getCurrentUrl();
		}
	
	public AddUserPage goToAddUserPage() {
		this.wait.until(ExpectedConditions.elementToBeClickable(SIGN_UP_BUTTON_ID));
		WebElement signUpButton =this.driver.findElement(SIGN_UP_BUTTON_ID);
		signUpButton.click();
		return new AddUserPage(this.driver);
	}


}