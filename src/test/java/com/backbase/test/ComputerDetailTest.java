package com.backbase.test;

import java.util.ArrayList;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.backbase.businessHandler.ComputerDetailBH;
import com.backbase.businessHandler.HomeBH;
import com.backbase.pageObject.ComputerDetailPage;
import com.backbase.pageObject.HomePage;
import com.backbase.utilities.Log;

public class ComputerDetailTest extends TestBase {

	ComputerDetailPage computerDetailPage;
	HomePage homePage;

	ComputerDetailBH computerDetailBH;
	HomeBH homeBH;

	java.sql.Timestamp timestamp;

	/**
	 * This method is responsible for initializing used objects
	 */
	@BeforeClass
	public void init() {

		computerDetailPage = new ComputerDetailPage(driver);
		homePage = new HomePage(driver);

		computerDetailBH = new ComputerDetailBH(computerDetailPage, homePage);
		homeBH = new HomeBH(homePage);

		Log.info("objects are initialized successfully");
	}

	/**
	 * Add a new computer with only mandatory fields
	 */
	@Test(priority = 1)
	public void addNewComputerMin() {
		Log.info("Starting TestCase: addNewComputerMin");
		
		homeBH.navigateToHome(); //Go to homepage
		homeBH.addNewComputer(); //click on add new computer button
		String timeNow = new java.sql.Timestamp(System.currentTimeMillis()).toString();
		String computerName = jsonTestData.getData("addComputerMin").get("name") + timeNow; //unique name
		//fill form with data and submit
		computerDetailBH.addNewComputer(computerName, jsonTestData.getData("addComputerMin").get("introduced"),
				jsonTestData.getData("addComputerMin").get("discontinued"),
				jsonTestData.getData("addComputerMin").get("company")); 
		homeBH.search(computerName); //search for computer added
		ArrayList<String> computerNames = homeBH.getComputersPerPage();
		assertEquals(computerNames.size(), 1, "Computer Name is not Unique");
		assertEquals(computerNames.get(0), computerName);
		Log.info("TestCase: addNewComputerMin passed successfully");
	}

	/**
	 * Add new computer with all fields
	 */
	@Test(priority = 2)
	public void addNewComputerMax() {
		Log.info("Starting TestCase: addNewComputerMax");
		homeBH.navigateToHome(); //Go to homePage
		homeBH.addNewComputer(); // Click on add new computer button
		String timeNow = new java.sql.Timestamp(System.currentTimeMillis()).toString(); 
		String computerName = jsonTestData.getData("addComputerMax").get("name") + timeNow; //unique name
		//Fill form with data and submit
		computerDetailBH.addNewComputer(computerName, jsonTestData.getData("addComputerMax").get("introduced"),
				jsonTestData.getData("addComputerMax").get("discontinued"),
				jsonTestData.getData("addComputerMax").get("company"));
		homeBH.search(computerName); //search for computer added
		ArrayList<String> computerNames = homeBH.getComputersPerPage();
		assertEquals(computerNames.size(), 1, "Computer Name is not Unique");
		assertEquals(computerNames.get(0), computerName);
		Log.info("TestCase: addNewComputerMax passed successfully");
	}

	/**
	 * Add new computer where introduced date is later than discontinued date
	 */
	@Test(priority = 3)
	public void addNewComputerWrongDate() {
		Log.info("Starting TestCase: addNewComputerWrongDate");
		homeBH.navigateToHome(); //Go to homePage
		homeBH.addNewComputer(); // Click on add new computer button
		//fill form with data and submit
		computerDetailBH.addNewComputer(jsonTestData.getData("addComputerWrongdate").get("name"),
				jsonTestData.getData("addComputerWrongdate").get("introduced"),
				jsonTestData.getData("addComputerWrongdate").get("discontinued"),
				jsonTestData.getData("addComputerWrongdate").get("company"));
		assertEquals(driver.getCurrentUrl(), jsonTestData.getData("addComputerURL").get("URL"));
		Log.info("TestCase: addNewComputerWrongDate passed successfully");
	}

	/**
	 * View computer details from the form
	 */
	@Test(priority = 4)
	public void viewComputerDetail() {
		Log.info("Starting TestCase: viewComputerDetail");
		homeBH.navigateToHome(); //Go to home page
		ArrayList<String> entryDetails = homeBH.getFirstEntryDetails(); //get data of first raw from the table
		String introducedDate = entryDetails.get(1);
		String discontinuedDate = entryDetails.get(2);

		if (introducedDate != null) {
			entryDetails.remove(1);
			entryDetails.add(1, reformateDate(introducedDate)); //reformatting data as it will be displayed on the form
		}

		if (discontinuedDate != null) {
			entryDetails.remove(2);
			entryDetails.add(2, reformateDate(discontinuedDate)); //reformatting data as it will be displayed on the form
		}

		computerDetailPage = homeBH.clickOnfirstEntry(); //click on First entry on Table to view
		ArrayList<String> computerDetailFromView = computerDetailBH.getComputerInfoFromView(); //Containing data collected from the view

		for (int i = 0; i < entryDetails.size(); i++) {
			//asserting data from table and the form are the same
			assertEquals(entryDetails.get(i), computerDetailFromView.get(i));
		}
		Log.info("TestCase: viewComputerDetail passed successfully");
	}

	/**
	 * Edit a computer that already exist
	 */
	@Test(priority = 5)
	public void editComputerDetail() {
		Log.info("Starting TestCase: editComputerDetail");
		homeBH.navigateToHome(); //navigate to homepage
		computerDetailPage = homeBH.clickOnfirstEntry(); //click on first entry in table
		String uniqueName = jsonTestData.getData("editComputer").get("name")
				+ new java.sql.Timestamp(System.currentTimeMillis()).toString(); //unique name
		//changing details in the form with the new ones
		computerDetailBH.editComputerEntry(uniqueName, jsonTestData.getData("editComputer").get("introduced"),
				jsonTestData.getData("editComputer").get("discontinued"),
				jsonTestData.getData("editComputer").get("company"));
		homeBH.search(uniqueName); //search for the entry with new details
		ArrayList<String> updatedEntryDetails = homeBH.getFirstEntryDetails();
		assertEquals(updatedEntryDetails.get(0), uniqueName);
		assertEquals(reformateDate(updatedEntryDetails.get(1)), jsonTestData.getData("editComputer").get("introduced"));
		assertEquals(reformateDate(updatedEntryDetails.get(2)),
				jsonTestData.getData("editComputer").get("discontinued"));
		assertEquals(updatedEntryDetails.get(3), jsonTestData.getData("editComputer").get("company"));
		Log.info("TestCase: editComputerDetail passed successfully");
	}

	/**
	 * Edit computer's details from the form then cancel instead of saving
	 */
	@Test(priority = 6)
	public void editThenCancel() {
		Log.info("Starting TestCase: editThenCancel");
		homeBH.navigateToHome(); //Go to home page
		computerDetailPage = homeBH.clickOnfirstEntry(); //click on first entry to edit
		String uniqueName = jsonTestData.getData("editComputer").get("name") + new java.sql.Timestamp(System.currentTimeMillis()).toString();
		//change all data in the form with different details then cancel
		computerDetailBH.editThenCancel(uniqueName, jsonTestData.getData("editComputer").get("introduced"),
				jsonTestData.getData("editComputer").get("discontinued"),
				jsonTestData.getData("editComputer").get("company"));
		homeBH.search(uniqueName); 
		assertEquals(homeBH.getEmptyTableText(), jsonTestData.getData("emptyTableText").get("message"));
		Log.info("TestCase: editThenCancel passed successfully");
	}

	/**
	 * Delete computer from the database
	 */
	@Test(priority = 7)
	public void deleteEntry() {
		Log.info("Starting TestCase: deleteEntry");
		homeBH.navigateToHome(); //navigate to homepage
		ArrayList<String> firstEntryDetails = homeBH.getFirstEntryDetails(); //get details of computer on first row
		computerDetailPage = homeBH.clickOnfirstEntry(); //click on computer of first row to view
		computerDetailBH.deleteEntry();
		ArrayList<String> secondEntryDetails = homeBH.getFirstEntryDetails(); //get details of computer on first row
		boolean deleteHappened = compare(firstEntryDetails, secondEntryDetails);
		assertTrue(deleteHappened);
		assertEquals(jsonTestData.getData("deleteAlertMessage").get("message"), homeBH.getDeleteConfirmationMsg());
		Log.info("TestCase: deleteEntry passed successfully");

	}

	/**
	 * Adding a new computer with exact name as an existing one
	 */
	@Test(priority = 8)
	public void addNewComputerDublicateName() {
		Log.info("Starting TestCase: addNewComputerDublicateName");
		homeBH.navigateToHome(); //go to homepage
		String name = homeBH.getfirstEntryName(); //name of first computer in the table
		homeBH.addNewComputer(); // click on the add button
		//fill form with data including the same name 
		computerDetailBH.addNewComputer(name, jsonTestData.getData("addComputerMax").get("introduced"),
				jsonTestData.getData("addComputerMax").get("discontinued"),
				jsonTestData.getData("addComputerMax").get("company"));
		assertEquals(driver.getCurrentUrl(), jsonTestData.getData("addComputerURL").get("URL"));
		Log.info("TestCase: addNewComputerDublicateName passed successfully");
	}

	/**
	 * This method compares two arrays and check they march
	 * @param firstEntry
	 * @param secondEntry
	 * @return true if matched, false otherwirse
	 */
	public boolean compare(ArrayList<String> firstEntry, ArrayList<String> secondEntry) {
		int min = Math.min(firstEntry.size(), secondEntry.size());
		for (int i = 0; i < min; i++) {
			if (firstEntry.get(i) != secondEntry.get(i))
				return true;
		}

		return false;
	}

	/**
	 * Reformate date presented on the table to match one on the form
	 * @param date
	 * @return reformatted date
	 */
	public String reformateDate(String date) {
		String[] dateListed = date.split(" ");
		switch (dateListed[1]) {
		case "Jan":
			date = dateListed[2] + "-01-" + dateListed[0];
			break;
		case "Feb":
			date = dateListed[2] + "-02-" + dateListed[0];
			break;
		case "Mar":
			date = dateListed[2] + "-03-" + dateListed[0];
			break;
		case "Apr":
			date = dateListed[2] + "-04-" + dateListed[0];
			break;
		case "May":
			date = dateListed[2] + "-05-" + dateListed[0];
			break;
		case "Jun":
			date = dateListed[2] + "-06-" + dateListed[0];
			break;
		case "Jul":
			date = dateListed[2] + "-07-" + dateListed[0];
			break;
		case "Aug":
			date = dateListed[2] + "-08-" + dateListed[0];
			break;
		case "Sep":
			date = dateListed[2] + "-09-" + dateListed[0];
			break;
		case "Oct":
			date = dateListed[2] + "-10-" + dateListed[0];
			break;
		case "Nov":
			date = dateListed[2] + "-11-" + dateListed[0];
			break;
		case "Dec":
			date = dateListed[2] + "-12-" + dateListed[0];
			break;

		}

		return date;

	}

}
