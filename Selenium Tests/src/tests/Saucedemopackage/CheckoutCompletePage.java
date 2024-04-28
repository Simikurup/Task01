package tests.Saucedemopackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutCompletePage {
	private WebDriver driver;
	private WebDriverWait wait;
	private static final By CHECKOUT_DESCRIPTION_LOCATOR=By.xpath("//div[@class='complete-text']");
	
	public CheckoutCompletePage(WebDriver driver) {
		this.driver=driver;
		this.wait= new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	public String getcheckOutompleteText() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_DESCRIPTION_LOCATOR));
		WebElement CheckOutDescriptionText=driver.findElement(CHECKOUT_DESCRIPTION_LOCATOR);
		return CheckOutDescriptionText.getText();
		
	}
}
