package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

public class ResultFile {
	
	WebDriver driver;
	
	public ResultFile(WebDriver driver){
		this.driver = driver;
	}

	public String createFile(String strFileName) throws IOException{
		  
		  String TestFile = "./Result/"+strFileName +"_"+new SimpleDateFormat("YYYYMMMdd_HHMMSS").format(new Date()) + ".txt";
		  File FC = new File(TestFile);
		  FC.createNewFile();
		  
		  return TestFile;
	}
	
	public void writeToFile(String strFilename, String msg) throws IOException{
		  //Writing In to file.
		  //Create Object of java FileWriter and BufferedWriter class.
		  FileWriter FW = new FileWriter(strFilename, true);
		  BufferedWriter BW = new BufferedWriter(FW);
		  BW.append(msg); //Writing In To File.
		  BW.append(System.getProperty("line.separator"));
		  BW.flush();
		  BW.close();		  
	}

}
