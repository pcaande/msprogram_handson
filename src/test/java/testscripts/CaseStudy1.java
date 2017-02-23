package testscripts;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import junit.framework.Assert;
import pages.BookFlight;
import pages.ConfirmFlight;
import pages.FindFlight;
import pages.LogIn;
import pages.SelectFlight;
import utils.ReadExcel;
import utils.ResultFile;

/*
 * '###############################################################################
 * '-------------------------------------------------------------------------------
 * ' Case Study: Case Study 1
 * ' Script Name: CaseStudy1
 * ' Brief Functional Description: Book a return Journey
 * ' Created On: 17-Feb-2017
 * ' Created By: philip.j.m.caande
 * ' Comments/Remmark: 
 * '-------------------------------------------------------------------------------
 * '###############################################################################
 * '###############################################################################
 */

public class CaseStudy1 extends BaseTest{
  
	@Test
	public void BookAReturnFlightForAPassenger() throws IOException {
		
		LogIn Login = new LogIn(driver);
		FindFlight FindFlight = new FindFlight(driver); 
		SelectFlight SelFlight = new SelectFlight(driver);
		BookFlight BookFlight = new BookFlight(driver);
		ConfirmFlight ConfirmFlight = new ConfirmFlight(driver);
		ResultFile ResultFile = new ResultFile(driver);
		ReadExcel ReadExcel = new ReadExcel(driver);
		
		String strFilename = ResultFile.createFile("CaseStudy1");
		int DataRow = 1;
		//Check if Username Box exists
		Assert.assertTrue(Login.checkUserNameExists());
		
		
		//**Add URL and TITLE to Notepad
		ResultFile.writeToFile(strFilename, "URL: "+Login.getURL());
		ResultFile.writeToFile(strFilename, "TITLE: "+Login.getTitle());
		
		//Login
		Login.loginTo(ReadExcel.getTestDataValue(1, DataRow), ReadExcel.getTestDataValue(2, DataRow));
		
		
		//Verify that application navigates to "Flight Finder" page by checking presence of any relevant object
		Assert.assertTrue(FindFlight.checkIfContinueButtonExists());
		
		//**Add list of cities in Departing From to Notepad
		ResultFile.writeToFile(strFilename, "Count of Departing Cities: "+FindFlight.getCountofDepartingFromCities());
		ResultFile.writeToFile(strFilename, "Departing Cities: "+Arrays.toString(FindFlight.getListDepartingFromCities()));
		
		
		
		//Select Flight Details
		FindFlight.selectTripType("Round Trip");
		FindFlight.selectPassengerCount("1");
		FindFlight.selectDepartingFrom(ReadExcel.getTestDataValue(3, DataRow));
		FindFlight.selectDepartingDate(ReadExcel.getTestDataValue(4, DataRow), ReadExcel.getTestDataValue(5, DataRow));
		FindFlight.selectArrivingIn(ReadExcel.getTestDataValue(6, DataRow));
		FindFlight.selectReturnDate(ReadExcel.getTestDataValue(7, DataRow), ReadExcel.getTestDataValue(8, DataRow));
		FindFlight.selectServiceClass("Economy class");
		FindFlight.clickContinue();
		
		//Verify that the "Select Flight" page is displayed
		Assert.assertTrue(SelFlight.checkIfContinueButtonExists());
		
		//Select Depart Flight and Return Flight and click continue
		SelFlight.selectDepartFlight("Unified Airlines", "363");
		SelFlight.selectReturnFlight("Blue Skies Airlines", "631");
		SelFlight.clickContinue();
		
		//Verify that the "Book A Flight" page is displayed
		Assert.assertTrue(BookFlight.checkIfSecurePurchaseButtonExists());
		BookFlight.enterRequiredFields("FirstName","LastName","123456789");
		BookFlight.clickSecurePurchaseButton();
		
		//Verify that "Your itinerary has been booked!" text is displayed
		Assert.assertTrue(ConfirmFlight.checkIfConfirmationMsgExists());
		//Logout & Close Browser
		ConfirmFlight.clickLogout();
		Assert.assertTrue(Login.checkUserNameExists());
		
	}

}
