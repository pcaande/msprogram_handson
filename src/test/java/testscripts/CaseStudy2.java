package testscripts;

import java.io.IOException;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

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
 * ' Case Study: Case Study 2
 * ' Script Name: CaseStudy2
 * ' Brief Functional Description: Book a return Journey
 * ' Created On: 17-Feb-2017
 * ' Created By: philip.j.m.caande
 * ' Comments/Remmark: 
 * '-------------------------------------------------------------------------------
 * '###############################################################################
 * '###############################################################################
 */

public class CaseStudy2 extends BaseTest{
	
	@Test
	public void BookAReturnFlightForAPassenger() throws IOException {
		
		LogIn Login = new LogIn(driver);
		FindFlight FindFlight = new FindFlight(driver); 
		SelectFlight SelFlight = new SelectFlight(driver);
		BookFlight BookFlight = new BookFlight(driver);
		ConfirmFlight ConfirmFlight = new ConfirmFlight(driver);
		ResultFile ResultFile = new ResultFile(driver);
		ReadExcel ReadExcel = new ReadExcel(driver);
		
		String strFilename = ResultFile.createFile("CaseStudy2");
		int DataRow = 1;
		//Check if Username Box exists
		Assert.assertTrue(Login.checkUserNameExists(), "Username text box not detected!");
		ResultFile.writeToFile(strFilename, "Assert: Username Exists");
		
		//**Add URL and TITLE to Notepad
		ResultFile.writeToFile(strFilename, "URL: "+Login.getURL());
		ResultFile.writeToFile(strFilename, "TITLE: "+Login.getTitle());
		
		//Login
		Login.loginTo(ReadExcel.getTestDataValue(1, DataRow), ReadExcel.getTestDataValue(2, DataRow));
		
		
		//Verify that application navigates to "Flight Finder" page by checking presence of any relevant object
		Assert.assertTrue(FindFlight.checkIfContinueButtonExists(), "Flight Finder page did not load.");
		ResultFile.writeToFile(strFilename, "Assert: Flight Finder page has loaded");
		
		//**Add list of cities in Departing From to Notepad
		ResultFile.writeToFile(strFilename, "Count of Departing Cities: "+FindFlight.getCountofDepartingFromCities());
		ResultFile.writeToFile(strFilename, "Departing Cities: "+Arrays.toString(FindFlight.getListDepartingFromCities()));
		
		
		
		//Select Flight Details
		FindFlight.selectTripType("Round Trip");
		FindFlight.selectPassengerCount("1");
		FindFlight.selectDepartingFrom(ReadExcel.getTestDataValue(3, DataRow));
		FindFlight.selectDepartingDate(ReadExcel.getTestDataValue(4, 1), ReadExcel.getTestDataValue(5, DataRow));
		FindFlight.selectArrivingIn(ReadExcel.getTestDataValue(6, DataRow));
		FindFlight.selectReturnDate(ReadExcel.getTestDataValue(7, DataRow), ReadExcel.getTestDataValue(8, DataRow));
		FindFlight.selectServiceClass("Economy class");
		FindFlight.clickContinue();
		
		//Verify that the "Select Flight" page is displayed
		Assert.assertTrue(SelFlight.checkIfContinueButtonExists(), "Select Flight page did not load.");
		ResultFile.writeToFile(strFilename, "Assert: Select Flight page loaded");
		
		//Select Depart Flight and Return Flight and click continue
		SelFlight.selectDepartFlight("Blue Skies Airlines", "360");
		SelFlight.selectReturnFlight("Blue Skies Airlines", "630");
		SelFlight.clickContinue();
		
		//Verify that the "Book A Flight" page is displayed
		BookFlight.checkIfSecurePurchaseButtonExists();
		Assert.assertEquals("Book a Flight: Mercury Tours",Login.getTitle());
		ResultFile.writeToFile(strFilename, "Assert: Title Matches");
		
		Assert.assertTrue(BookFlight.checkTotalPrice(), "Total Price Calculation is wrong.");
		ResultFile.writeToFile(strFilename, "Assert: Total Price has correct calculation");
		
		BookFlight.enterRequiredFields("FirstName","LastName","123456789");
		BookFlight.clickSecurePurchaseButton();
		
		//Verify that "Your itinerary has been booked!" text is displayed
		Assert.assertTrue(ConfirmFlight.checkIfConfirmationMsgExists(), "Itinerary was not booked!");
		ResultFile.writeToFile(strFilename, "Assert: Your itinerary has been booked!");
		//Logout & Close Browser
		ConfirmFlight.clickLogout();
		Assert.assertTrue(Login.checkUserNameExists());
		ResultFile.writeToFile(strFilename, "Assert: Log out confirmed");
		
	}

}
