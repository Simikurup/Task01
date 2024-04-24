package tests.Saucedemopackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceDemoCheckoutStepTwo {
	private WebDriver driver;
	private WebDriverWait wait;
	
	private static final By PAYMENTINFO_LOCATOR=By.xpath("(//div[@class='summary_value_label'])[1]");
	private static final By SHIPPINGINFO_LOCATOR=By.xpath("(//div[@class='summary_value_label'])[2]");
	private static final By ITEMTOTAL_LOCATOR=By.xpath("//div[@class='summary_subtotal_label']");
	private static final By TAX_LOCATOR=By.xpath("//div[@class='summary_tax_label']");
	private static final By TOTAL_LOCATOR=By.xpath("//div[@class='summary_total_label']");
	private static final By FINISH_LOCATOR=By.id("finish");
	private static final By PRODUCTNAME_LOCATOR=By.xpath("//div[@class='inventory_item_name']");
	private static final By PRODUCT_DESCRIPTION_LOCATOR=By.xpath("//div[@class='inventory_item_desc']");
	private static final By PRODUCT_PRICE_LOCATOR=By.xpath("//div[@class='inventory_item_price']");
	public SauceDemoCheckoutStepTwo(WebDriver driver, WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}

	public void checkoutStepSecond() {
		getProductTitle();
		getProductDescription();
		getProductPrice();
		getPaymentInfo();
		getShippingInfo();
		getPriceTotal();
		getTax();
		getTotal();
	}
	
		public SauceDemoCheckoutComplete finish() {
		finishCheckOut();
		return new SauceDemoCheckoutComplete(this.driver,this.wait);
	}
	
	public String getProductTitle() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTNAME_LOCATOR));
		WebElement productTitleLabel=this.driver.findElement(PRODUCTNAME_LOCATOR);
		return productTitleLabel.getText();
		
	}
	public String getProductDescription() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_DESCRIPTION_LOCATOR));
		WebElement productDescLabel=this.driver.findElement(PRODUCT_DESCRIPTION_LOCATOR);
		return productDescLabel.getText();
	}
	public double getProductPrice() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_PRICE_LOCATOR));
		WebElement productPriceText=this.driver.findElement(PRODUCT_PRICE_LOCATOR);
		String PriceValue= productPriceText.getText();
		String PriceValueReplace$=PriceValue.replace("$", "");
		double productPrice=Double.parseDouble(PriceValueReplace$);
		return productPrice;
	}
		
	public String getPaymentInfo() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PAYMENTINFO_LOCATOR));
		WebElement paymentInfoLabel=driver.findElement(PAYMENTINFO_LOCATOR);
		return paymentInfoLabel.getText();
	}
	public String getShippingInfo() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPINGINFO_LOCATOR));
		WebElement shippingInfoLabel=driver.findElement(SHIPPINGINFO_LOCATOR);
		return shippingInfoLabel.getText();
	}
	public double getPriceTotal() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ITEMTOTAL_LOCATOR));
		WebElement itemTotalLabel=driver.findElement(ITEMTOTAL_LOCATOR);
		String itemValue=itemTotalLabel.getText();
		String itemValueReplace$=itemValue.replace("Item total: $", "");
		double totalValue=Double.parseDouble(itemValueReplace$);
		return totalValue;
	}
	public double getTax() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(TAX_LOCATOR));
		WebElement taxLabel=driver.findElement(TAX_LOCATOR);
		String taxValue=taxLabel.getText();
		String taxValueReplace$=taxValue.replace("Tax: $", "");
		double taxToPay=Double.parseDouble(taxValueReplace$);
		return taxToPay;
	}
	public double getTotal() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(TOTAL_LOCATOR));
		WebElement totalLabel=driver.findElement(TOTAL_LOCATOR);
		String totalVal=totalLabel.getText();
		String totalValReplace$=totalVal.replace("Total: $", "");
		double totalToPay=Double.parseDouble(totalValReplace$);
		return totalToPay;
	}
	private void finishCheckOut() {
		wait.until(ExpectedConditions.elementToBeClickable(FINISH_LOCATOR));
		WebElement finishButton=driver.findElement(FINISH_LOCATOR);
		finishButton.click();
	}
}
