package com.backbase.pageObject;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.backbase.pageObject.BasePage;

public class HomePage extends BasePage {

	// Locate Elements

	@FindBy(id = "searchbox")
	private WebElement searchTextField; // search text field

	@FindBy(id = "searchsubmit")
	private WebElement searchBtn; // search button

	@FindBy(id = "add")
	private WebElement addNewComputerBtn; // add new computer button

	@FindBy(css = "li[class='prev disabled']")
	private WebElement previousBtnDisabled; // previous button in pagination
											// disabled

	@FindBy(xpath = "//*[@id='pagination']/ul/li[1]/a")
	private WebElement previousBtn; // previous button in pagination enabled

	@FindBy(css = "li[class='current']")
	private WebElement current; // current tab in pagination

	@FindBy(css = "li[class='next disabled']")
	private WebElement nextBtnDisabled; // next button in pagination disabled

	@FindBy(xpath = "//*[@id='pagination']/ul/li[3]/a")
	private WebElement nextBtn; // next button in pagination enabled

	@FindBy(css = "a[href = '/']")
	private WebElement navigateToHomePage; // URL at the top of the page

	@FindBy(xpath = "//tr[1]/td")
	private List<WebElement> firstEntry; // first row in table

	@FindBy(xpath = "//tr[1]/td/a")
	private WebElement firstEntryName; // first computer name in table

	@FindBy(xpath = "//div[@class='alert-message warning']")
	private WebElement deleteAlertMessage; // delete confirmation message after
											// deletion

	@FindBy(xpath = "//section[@id='main']/h1")
	private WebElement numberOfComputersPresented; // number of computer
													// presented in title

	@FindBy(xpath = "//tbody/tr/td[1]")
	private List<WebElement> computerNames; // computer names in table displayed

	@FindBy(css = "div[class='well']")
	private WebElement getEmptyTable; // text that appears when table is empty

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public HomePage setSearchText(String searchText) {
		waitForVisibilityOf(searchTextField);
		setText(searchTextField, searchText);
		return this;
	}

	/**
	 * Click on search button
	 * 
	 * @return home page with filtered data according to search
	 */
	public HomePage clickOnSearchBtn() {
		waitForVisibilityOf(searchBtn);
		searchBtn.click();
		return this;
	}

	/**
	 * Click on add new computer button
	 */
	public void addNewComputer() {
		waitForVisibilityOf(addNewComputerBtn);
		addNewComputerBtn.click();
	}

	/**
	 * Click on link to navigate to first page of home page
	 * 
	 * @return home page
	 */
	public HomePage navigateToHome() {
		waitForVisibilityOf(navigateToHomePage);
		navigateToHomePage.click();
		return this;
	}

	/**
	 * 
	 * click on first computer in table
	 * 
	 * @return computer form with displayed details
	 */
	public String getFirstEntryName() {
		waitForVisibilityOf(firstEntry.get(0));
		return firstEntry.get(0).getText();
	}

	/**
	 * get introduced date of first row in table
	 * 
	 * @return introduced date
	 */
	public String getFirstEntryIntroduced() {
		waitForVisibilityOf(firstEntry.get(1));
		if (firstEntry.get(1).getText().contains("-"))
			return null;
		return firstEntry.get(1).getText();
	}

	/**
	 * get discontinued date of first row in table
	 * 
	 * @return discontinued date
	 */
	public String getFirstEntryDiscontinued() {
		waitForVisibilityOf(firstEntry.get(2));
		if (firstEntry.get(2).getText().contains("-"))
			return null;
		return firstEntry.get(2).getText();
	}

	/**
	 * Get company of first row in table
	 * 
	 * @return company
	 */
	public String getFirstEntryCompany() {
		waitForVisibilityOf(firstEntry.get(3));
		if (firstEntry.get(3).getText().contains("-"))
			return null;
		return firstEntry.get(3).getText();
	}

	/**
	 * Click on first row to edit
	 * 
	 * @return
	 */
	public ComputerDetailPage editFirstEntry() {
		waitForVisibilityOf(firstEntryName);
		firstEntryName.click();
		return new ComputerDetailPage(driver);
	}

	/**
	 * check if message is displayed after deletion
	 * 
	 * @return true if message displayed, false otherwise
	 */
	public boolean checkDeleteAlertMsg() {
		waitForVisibilityOf(deleteAlertMessage);
		return true;
	}

	/**
	 * Get number of computers displayed in title
	 * 
	 * @return number of computers in title
	 */
	public int getNumberOfComputers() {
		waitForVisibilityOf(numberOfComputersPresented);
		String message = numberOfComputersPresented.getText();
		String[] messagelist = message.split(" ");
		return Integer.parseInt(messagelist[0]);
	}

	/**
	 * Get text in current tab in pagination
	 * 
	 * @return current
	 */
	public String getCurrent() {
		waitForVisibilityOf(current);
		return current.getText();
	}

	/**
	 * Check previous button is disabled
	 * 
	 * @return true if disabled, false otherwise
	 */
	public boolean checkPrevIsDisabled() {
		try {
			waitForVisibilityOf(previousBtnDisabled);
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			if (e.getMessage().contains("waiting for visibility of")) {
				return false;
			} else
				throw e;
		}

		return true;
	}

	/**
	 * Check next button is disabled
	 * 
	 * @return true if disabled, false otherwise
	 */
	public boolean checkNextIsDisabled() {

		try {
			waitForVisibilityOf(nextBtnDisabled);
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			if (e.getMessage().contains("waiting for visibility of")) {
				return false;
			} else
				throw e;

		}

		return true;
	}

	/**
	 * Click on previous button
	 * 
	 * @return home page
	 */
	public HomePage clickOnPrev() {
		waitForVisibilityOf(previousBtn);
		previousBtn.click();
		return this;
	}

	/**
	 * click on next button
	 * 
	 * @return home page
	 */
	public HomePage clickOnNext() {
		waitForVisibilityOf(nextBtn);
		nextBtn.click();
		return this;
	}

	/**
	 * Get computer names presented on table
	 * 
	 * @return arraylist with names
	 */
	public ArrayList<String> getComputerNames() {
		waitForVisibilityOf(computerNames.get(0));
		ArrayList<String> computerNamesList = new ArrayList<>();
		for (WebElement computer : computerNames) {
			computerNamesList.add(computer.getText());
		}
		return computerNamesList;
	}

	/**
	 * get message presented after deletion
	 * 
	 * @return message
	 */
	public String getDeleteAlertMsg() {
		waitForVisibilityOf(deleteAlertMessage);
		return deleteAlertMessage.getText();
	}

	/**
	 * Get message displayed if table is empty
	 * 
	 * @return message
	 */
	public String getEmptyTableText() {
		waitForVisibilityOf(getEmptyTable);
		return getEmptyTable.findElement(By.xpath("//em")).getText();
	}

}
