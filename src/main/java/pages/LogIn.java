package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogIn {
	
	WebDriver driver;
	
	//.//*[@name='userName'] - xpath
	By txtuserName = By.name("userName");
	By txtpassWord = By.name("password");
	
	By btnlogIn = By.name("login");
	
	
	public LogIn(WebDriver driver){
		this.driver = driver;
	}

	public String getURL(){
		
		return driver.getCurrentUrl().toString();
	}
	
	public String getTitle(){
		
		return driver.getTitle().toString();
	}
	
	public boolean checkUserNameExists(){
		
		if(driver.findElements(txtuserName).size() != 0) return true;

		return false;
	}
	
	
	public void enterUserName(String strUserName){

        driver.findElement(txtuserName).sendKeys(strUserName);;
    }
	
	public void enterPassword(String strPassword){

        driver.findElement(txtpassWord).sendKeys(strPassword);
	}
	
	public void clickLogin(){

        driver.findElement(btnlogIn).click();
	}
	
	public void loginTo(String strUserName,String strPasword){

        this.enterUserName(strUserName);
        this.enterPassword(strPasword);
        this.clickLogin();        
    }
	
	
	
}
