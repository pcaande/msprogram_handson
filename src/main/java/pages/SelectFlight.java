package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectFlight {

	WebDriver driver;
	
	By btnContinue = By.name("reserveFlights");
	
	By rbtnDepart = By.name("outFlight");
	By rbtnReturn = By.name("inFlight");
	
	public SelectFlight(WebDriver driver){
		this.driver = driver;
	}
	
	public void clickContinue(){
		
		driver.findElement(btnContinue).click();
	}
	
	public boolean checkIfContinueButtonExists(){
		
		if(driver.findElements(btnContinue).size() != 0) return true;

		return false;
	}
	
	public void selectDepartFlight(String strAirline, String strFlightNumber){
		
		driver.findElement(By.xpath(".//*[@name='outFlight'][contains(@value, '"+strAirline+"') and contains(@value, '"+strFlightNumber+"')]")).click();
		
	}
	
	public void selectReturnFlight(String strAirline, String strFlightNumber){
		
		driver.findElement(By.xpath(".//*[@name='inFlight'][contains(@value, '"+strAirline+"') and contains(@value, '"+strFlightNumber+"')]")).click();
		
	}
	
}
