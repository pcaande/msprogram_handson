package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FindFlight {

	WebDriver driver;
	
	By btnContinue = By.name("findFlights");
	
	By rbtnRoundTrip = By.xpath(".//*[@value='roundtrip']");
	By rbtnOneWay = By.xpath(".//*[@value='oneway']");
	By rbtnEconomy = By.xpath(".//*[@value='Coach']");
	By rbtnBusiness = By.xpath(".//*[@value='Business']");
	By rbtnFirst = By.xpath(".//*[@value='First']");
	
	By ddownPassCount = By.name("passCount");
	By ddownDepartFrom = By.name("fromPort");
	By ddownDepartMonth = By.name("fromMonth");
	By ddownDepartDay = By.name("fromDay");
	By ddownArrivingIn = By.name("toPort");
	By ddownReturnMonth = By.name("toMonth");
	By ddownReturnDay = By.name("toDay");
	By ddownListDepartFrom = By.xpath(".//*[@name='fromPort']//option");
	
	
	public FindFlight(WebDriver driver){
		this.driver = driver;
	}
	
	public int getCountofDepartingFromCities(){
		
		List<WebElement> fromCities = driver.findElements(ddownListDepartFrom);
		
		return fromCities.size();
	}
	
	public String[] getListDepartingFromCities(){
		
		List<WebElement> fromCities = driver.findElements(ddownListDepartFrom);		
		List<String> strarrCities = new ArrayList<String>();
		
		for(WebElement city : fromCities){
			strarrCities.add(city.getAttribute("value").toString());
		}
		
		return strarrCities.toArray(new String[0]);
	}
	
	
	public void clickContinue(){
		driver.findElement(btnContinue).click();
	}
	
	public void selectServiceClass(String strService){
		
		if(strService.equalsIgnoreCase("Economy class"))
			driver.findElement(rbtnEconomy).click();
		else if(strService.equalsIgnoreCase("Business class"))
			driver.findElement(rbtnBusiness).click();
		else
			driver.findElement(rbtnFirst).click();
	}
	
	
	public void selectWorkAround(By element, String strValue){
		
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }"
				, driver.findElement(element), strValue);

	}
	
	public void selectReturnDate(String strMonth, String strDay){
		
		/*Select selectMonth = new Select(driver.findElement(ddownReturnMonth));
		selectMonth.selectByValue(strMonth);
		
		Select selectDay = new Select(driver.findElement(ddownReturnDay));
		selectDay.selectByValue(strDay);*/
		
		this.selectWorkAround(ddownReturnMonth, strMonth);
		this.selectWorkAround(ddownReturnDay, strDay);
		
	}
	
	
	public void selectDepartingDate(String strMonth, String strDay){
		
		/*Select selectMonth = new Select(driver.findElement(ddownDepartMonth));
		selectMonth.selectByValue(strMonth);
		
		Select selectDay = new Select(driver.findElement(ddownDepartDay));
		selectDay.selectByValue(strDay);*/
		
		this.selectWorkAround(ddownDepartMonth, strMonth);
		this.selectWorkAround(ddownDepartDay, strDay);
	}
	
		
	public void selectArrivingIn(String strCity){
		
		/*Select select = new Select(driver.findElement(ddownArrivingIn));
		select.selectByValue(strCity);*/
		
		this.selectWorkAround(ddownArrivingIn, strCity);
		
	}
	
	
	public void selectDepartingFrom(String strCity){
		
	/*	Select select = new Select(driver.findElement(ddownDepartFrom));
		//select.selectByVisibleText(strCity);
		select.selectByValue(strCity);*/
		
		this.selectWorkAround(ddownDepartFrom, strCity);
		
	}
	
	public void selectPassengerCount(String strPassNum){
		
		Select select = new Select(driver.findElement(ddownPassCount));
		select.selectByValue(strPassNum);
		
	}
	
	public void selectTripType(String strTripType){
		
		if(strTripType.equalsIgnoreCase("Round Trip"))
			driver.findElement(rbtnRoundTrip).click();
		else
			driver.findElement(rbtnOneWay).click();
		
	}
	
	public boolean checkIfContinueButtonExists(){
		
		if(driver.findElements(btnContinue).size() != 0) return true;

		return false;
	}
		
	
}
