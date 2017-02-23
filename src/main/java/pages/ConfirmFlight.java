package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ConfirmFlight {

WebDriver driver;
	
	
	By txtConfirmMsg = By.xpath(".//font[contains(text(),'itinerary has been booked')]");
	By txtTotalPrice = By.xpath(".//font[contains(text(),' Price')]");
	
	By lnkSignOff = By.linkText("SIGN-OFF");
	By lnkLogOut = By.xpath("(.//a[@href='mercurysignoff.php'])[2]");
	By imgLogOut = By.xpath(".//img[@src='/images/forms/Logout.gif']");
	By imgLogOut2 = By.cssSelector("img[src='/images/forms/Logout.gif']");
	
	public ConfirmFlight(WebDriver driver){
		this.driver = driver;
	}
	
	public void clickLogout(){
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		//wait.until(ExpectedConditions.elementToBeClickable(imgLogOut));
		//driver.findElement(txtTotalPrice).click();
		//driver.findElement(imgLogOut).click();
		
		//driver.findElement(imgLogOut).sendKeys(Keys.ENTER);
		//driver.findElement(lnkLogOut).click();
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
	    executor.executeScript("arguments[0].click();", driver.findElement(imgLogOut));
		
	}
	
	public boolean checkIfConfirmationMsgExists(){
		
		if(driver.findElements(txtConfirmMsg).size() != 0) return true;

		return false;
	}
}
