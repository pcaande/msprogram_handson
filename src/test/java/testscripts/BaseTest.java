package testscripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import utils.ReadExcel;

public class BaseTest {

	protected WebDriver driver;
		
	
	@BeforeTest
	public void Setup() throws IOException
	{
		ReadExcel ReadExcel = new ReadExcel(driver);
		int DataRow = 1;
		
		System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("app.update.auto", false);
		profile.setPreference("app.update.enabled", false);
		driver = new FirefoxDriver(profile);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    driver.get(ReadExcel.getTestDataValue(0, DataRow));
		driver.manage().window().maximize();
		
	}
	
	@AfterTest
	public void TearDown()
	{	
		if(driver != null)
		driver.quit();	
	}
	
	
}
