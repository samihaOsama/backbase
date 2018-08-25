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

public class HomeTest extends TestBase {

	HomePage homePage;
	ComputerDetailPage computerDetailPage;

	HomeBH homeBH;
	ComputerDetailBH computerDetailBH;

	@BeforeClass
	public void init() {
		homePage = new HomePage(driver);
		computerDetailPage = new ComputerDetailPage(driver);

		homeBH = new HomeBH(homePage);
		computerDetailBH = new ComputerDetailBH(computerDetailPage);
		Log.info("objects are initialized successfully");
	}

	/**
	 * Checks number displayed in title and in pagination are the same
	 */
	@Test(priority = 1)
	public void checkNumberOfComputers() {
		Log.info("Starting TestCase: checkNumberOfComputers");
		boolean flag = homeBH.checkNumComputerTotalRetrieved(); // return true if matches
		assertTrue(flag);
		Log.info("TestCase: checkNumberOfComputers passed successfully");
	}

	/**
	 * Check user can view next items in table using next button
	 */
	@Test(priority = 2)
	public void checkPaginationForward() {
		Log.info("Starting TestCase: checkPaginationForward");
		ArrayList<Integer> numberAfterNavigation; // number displayed in current tab after pagination
		ArrayList<Integer> number = homeBH.getCurrent(); // number in current before pagination
		boolean flag = false; // flag for clicking next and navigating successfully

		if (number.get(1) < number.get(2)) { // can navigate
			flag = homeBH.navigateForward();
			numberAfterNavigation = homeBH.getCurrent();
			assertNotEquals(number.get(1), numberAfterNavigation.get(1));
		} else {
			boolean canGoBack = homeBH.navigateBackward(); // if on last page go back one page to check next button
			if (!canGoBack) {// only one page displayed
				flag = true;

			} else {
				flag = homeBH.navigateForward();
				numberAfterNavigation = homeBH.getCurrent();
				assertNotEquals(number.get(1), numberAfterNavigation.get(1));
			}
		}

		assertTrue(flag);
		Log.info("Starting TestCase: checkPaginationForward passed successfully");
	}

	@Test(priority = 3)
	public void checkPaginationBackward() {
		Log.info("Starting TestCase: checkPaginationBackward");
		ArrayList<Integer> numberAfterNavigation;// number displayed in current tab after pagination
		ArrayList<Integer> number = homeBH.getCurrent(); // number in current before pagination
		boolean flag = false; // flag for clicking previous and navigating successfully

		if (number.get(0) > 1) { // can navigate
			flag = homeBH.navigateBackward();
			numberAfterNavigation = homeBH.getCurrent();
			assertNotEquals(number.get(1), numberAfterNavigation.get(1));
		} else {
			boolean canGoforward = homeBH.navigateForward(); // if on first page, go to next page to check privious button
			number = homeBH.getCurrent();
			if (!canGoforward) {// only one page displayed
				flag = true;
			} else {
				flag = homeBH.navigateBackward();
				numberAfterNavigation = homeBH.getCurrent();
				assertNotEquals(number.get(1), numberAfterNavigation.get(1));
			}
		}
		assertTrue(flag);
		Log.info("Starting TestCase: checkPaginationBackward passed successfully");
	}

	/**
	 * Check if previous button is clickable when on first page
	 */
	@Test(priority = 4)
	public void checkCantNavigateBackward() {
		Log.info("Starting TestCase: checkCantNavigateBackward");
		homeBH.navigateToHome();
		boolean flag = homeBH.navigateBackward();
		assertFalse(flag);
		Log.info("Starting TestCase: checkCantNavigateBackward passed successfully");
	}

	/**
	 * Check if next button is clickable when on last page
	 */
	@Test(priority = 5)
	public void checkCantNavigateForward() {
		Log.info("Starting TestCase: checkCantNavigateForward");
		ArrayList<Integer> number = homeBH.getCurrent();
		if (number.get(1) != number.get(2)) {
			int numberPerPage = number.get(1) - number.get(0) + 1;
			int numberToAppendToURL = number.get(2) / numberPerPage;
			driver.navigate().to(BaseURl + "?p=" + numberToAppendToURL); // navigate to last page
		}
		boolean flag = homeBH.navigateForward();
		assertFalse(flag);
		Log.info("Starting TestCase: checkCantNavigateForward passed successfully");

	}

	/**
	 * add a computer to make sure testing search func is working fine
	 */
	@Test(priority = 6)
	public void checkSearchFunc() {
		Log.info("Starting TestCase: checkSearchFunc");
		ArrayList<String> searchResult;

		homeBH.navigateToHome();
		homeBH.addNewComputer();
		String timeNow = new java.sql.Timestamp(System.currentTimeMillis()).toString();
		String computerName = jsonTestData.getData("addComputerMin").get("name") + timeNow; // unique name
		// fill form with data and submit
		computerDetailBH.addNewComputer(computerName, jsonTestData.getData("addComputerMin").get("introduced"),
				jsonTestData.getData("addComputerMin").get("discontinued"),
				jsonTestData.getData("addComputerMin").get("company"));
		homeBH.search(computerName);
		searchResult = homeBH.getComputersPerPage(); // Get computer names per page
		boolean flag = true;

		if (searchResult.size() == 0)
			flag = false;

		for (String name : searchResult) {
			if (!name.toLowerCase().contains(computerName))
				flag = false;
		}

		assertTrue(flag);
		Log.info("Starting TestCase: checkSearchFunc passed successfully");

	}

}
