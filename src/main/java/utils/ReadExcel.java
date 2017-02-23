package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class ReadExcel {
	
	WebDriver driver;
	
	public ReadExcel(WebDriver driver){
		this.driver = driver;
	}
  
  public String getTestDataValue(int intCol, int intRow) throws IOException{
	  
	  XSSFWorkbook srcBook = new XSSFWorkbook("./src/test/java/testscripts/TestData.xlsx");     
      XSSFSheet sourceSheet = srcBook.getSheetAt(0);
      XSSFRow sourceRow = sourceSheet.getRow(intRow);
      return sourceRow.getCell(intCol).toString();	  
      
	  
  }	

}
