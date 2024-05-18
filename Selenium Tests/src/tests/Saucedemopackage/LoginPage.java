package tests.Saucedemopackage;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {
	
private	WebDriver driver;
private WebDriverWait wait;
private static final By STD_USERNAME_LOCATOR	=By.id("user-name");	
private static final By STD_PASSWORD_LOCATOR=By.id("password");
private static final By LOGIN_BUTTON_LOCATOR=By.id("login-button");


//Constructor
public LoginPage(WebDriver driver) {
	this.driver=driver;
	this.wait= new WebDriverWait(driver,Duration.ofSeconds(30));
}
//public void saucedemoOpenHomepage() {
//	openHomePage();
		
//}
public ProductsPage login(String username,String password) {
	enterUserName(username);	
	enterPassword(password);
	clickLogin();
	return new ProductsPage(this.driver);
}
public void openLoginPage(String url) {
	this.driver.get(url);;
	
}
public String getUrl() {
	String currentUrl=this.driver.getCurrentUrl();
	return currentUrl;
	
}
private void enterUserName(String username) {
	this.wait.until(ExpectedConditions.elementToBeClickable(STD_USERNAME_LOCATOR));
	WebElement usernameTextbox=this.driver.findElement(STD_USERNAME_LOCATOR);
	usernameTextbox.sendKeys(username);		
}
private void enterPassword(String password) {
	this.wait.until(ExpectedConditions.elementToBeClickable(STD_PASSWORD_LOCATOR));
	WebElement PasswordTextbox=this.driver.findElement(STD_PASSWORD_LOCATOR);
	PasswordTextbox.sendKeys(password);
}
private void clickLogin() {
	this.wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON_LOCATOR));
	WebElement loginButton=this.driver.findElement(LOGIN_BUTTON_LOCATOR);
	loginButton.click();
}
}
