package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookFlight {

WebDriver driver;
	
	By btnContinue = By.name("buyFlights");
	
	By txtFname = By.name("passFirst0");
	By txtLname = By.name("passLast0");
	By txtCnumber = By.name("creditnumber");
	By txtDepartureTripCost = By.xpath("(.//*[@class='data_center' and @bgcolor='#FFCC00'])[1]");
	By txtArrivalTripCost = By.xpath("(.//*[@class='data_center' and @bgcolor='#FFCC00'])[2]");
	By txtNumOfPassengers = By.xpath(".//font[text()='Passengers:']/../following-sibling::td");
	By txtTaxes = By.xpath(".//font[contains(.,'Taxes:')]/../following-sibling::td");
	By txtTotalPrice = By.xpath(".//font[contains(.,'Total Price')]/../following-sibling::td");
	
		
	public BookFlight(WebDriver driver){
		this.driver = driver;
	}
	
	public boolean checkTotalPrice(){
		
		if(this.getTotal() == ((this.getDepCost() + this.getArrCost()) * this.getNumOfPass())
				+ this.getTaxes()) return true;
		return false;
		
	}
	
	public int getTaxes(){
		return Integer.parseInt(driver.findElement(txtTaxes).getText().replace("$", ""));
	}
	
	public int getTotal(){
		return Integer.parseInt(driver.findElement(txtTotalPrice).getText().replace("$", ""));
	}
	
	public int getDepCost(){
		return Integer.parseInt(driver.findElement(txtDepartureTripCost).getText());
			
	}
	
	public int getArrCost(){
		return Integer.parseInt(driver.findElement(txtArrivalTripCost).getText());
	}
	
	public int getNumOfPass(){
		return Integer.parseInt(driver.findElement(txtNumOfPassengers).getText());

	}
	
	public void clickSecurePurchaseButton(){
		
		driver.findElement(btnContinue).click();
	}
	public void enterRequiredFields(String strFname, String strLname, String strCnumber){
		
		this.enterFirstName(strFname);
		this.enterLastName(strLname);
		this.enterCnumber(strCnumber);
	}
	
	public void enterFirstName(String strFname){
		
		driver.findElement(txtFname).sendKeys(strFname);
		
	}
	
	public void enterLastName(String strLname){
		
		driver.findElement(txtLname).sendKeys(strLname);
	}
	
	public void enterCnumber(String strCnumber){
		
		driver.findElement(txtCnumber).sendKeys(strCnumber);
	}
	
	public boolean checkIfSecurePurchaseButtonExists(){
		
		if(driver.findElements(btnContinue).size() != 0) return true;

		return false;
	}
	
	
	
	
}
