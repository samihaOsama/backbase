package com.backbase.businessHandler;

import java.util.ArrayList;

import com.backbase.pageObject.ComputerDetailPage;
import com.backbase.pageObject.HomePage;

public class HomeBH {

	HomePage homePage;

	public HomeBH(HomePage homePage) {
		this.homePage = homePage;
	}

	/**
	 * set search text with value and click search
	 * 
	 * @param searchText
	 */
	public void search(String searchText) {
		homePage.setSearchText(searchText).clickOnSearchBtn();
	}

	/**
	 * Get computer names in table
	 * 
	 * @return array of computer names
	 */
	public ArrayList<String> getComputersPerPage() {

		ArrayList<String> computerNames = homePage.getComputerNames();
		return computerNames;
	}

	/**
	 * Navigate forward if applicable
	 * 
	 * @return true if navigated , false otherwise
	 */
	public boolean navigateForward() {

		if (!homePage.checkNextIsDisabled()) {
			homePage.clickOnNext();
			return true;
		}
		return false;
	}

	/**
	 * navigate backward if applicable
	 * 
	 * @return true if navigated, false otherwise
	 */
	public boolean navigateBackward() {

		if (!homePage.checkPrevIsDisabled()) {
			homePage.clickOnPrev();
			return true;
		}
		return false;
	}

	/**
	 * compare number of title and in current tab
	 * 
	 * @return true if matched, false otherwise
	 */
	public boolean checkNumComputerTotalRetrieved() {
		int upperNumber = homePage.getNumberOfComputers();
		String[] currentText = homePage.getCurrent().split(" ");
		int currenttextLength = currentText.length;
		int currentNumber = Integer.parseInt(currentText[currenttextLength - 1]);

		if (upperNumber == currentNumber)
			return true;
		else
			return false;
	}

	/**
	 * Get numbers presented in current tab
	 * 
	 * @return list of 3 number From, To, OF
	 */
	public ArrayList<Integer> getCurrent() {
		String[] currentText = homePage.getCurrent().split(" ");
		ArrayList<Integer> number = new ArrayList<>();
		number.add(Integer.parseInt(currentText[1]));
		number.add(Integer.parseInt(currentText[3]));
		number.add(Integer.parseInt(currentText[5]));

		return number;
	}

	/**
	 * Get name of computer on first row of table
	 * 
	 * @return
	 */
	public String getfirstEntryName() {
		return homePage.getFirstEntryName();
	}

	/**
	 * Get number of computer in Title
	 * 
	 * @return
	 */
	public int getComputerNumber() {
		return homePage.getNumberOfComputers();
	}

	/**
	 * Click on add new computer button
	 */
	public void addNewComputer() {
		homePage.addNewComputer();
	}

	/**
	 * click on Play sample application — Computer database title to get to home
	 * 
	 * @return home page
	 */
	public HomePage navigateToHome() {
		homePage = homePage.navigateToHome();
		return homePage;
	}

	/**
	 * Click on first entry in table to view
	 * 
	 * @return computer detail page with displayed items
	 */
	public ComputerDetailPage clickOnfirstEntry() {
		return homePage.editFirstEntry();

	}

	/**
	 * Collect details of first computer in table
	 * 
	 * @return array of details of computer
	 */
	public ArrayList<String> getFirstEntryDetails() {
		ArrayList<String> details = new ArrayList<>();
		details.add(homePage.getFirstEntryName());
		details.add(homePage.getFirstEntryIntroduced());
		details.add(homePage.getFirstEntryDiscontinued());
		details.add(homePage.getFirstEntryCompany());
		return details;
	}

	/**
	 * Get delete confirmation message after deleting a computer
	 * 
	 * @return message
	 */
	public String getDeleteConfirmationMsg() {
		return homePage.getDeleteAlertMsg();
	}

	/**
	 * Get message presented when table is empty
	 * 
	 * @return message
	 */
	public String getEmptyTableText() {
		return homePage.getEmptyTableText();
	}
}
