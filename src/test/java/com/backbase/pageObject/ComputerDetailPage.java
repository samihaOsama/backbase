package com.backbase.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComputerDetailPage extends BasePage {

	@FindBy(id = "name")
	private WebElement computerName; // computerName text field

	@FindBy(id = "introduced")
	private WebElement introduced; // introduced date text field

	@FindBy(id = "discontinued")
	private WebElement discontinued; // discontinued date text field

	@FindBy(id = "company")
	private WebElement company; // company dropdown list

	@FindBy(css = "option[selected]")
	private WebElement selectedCompany; // company value on view form

	@FindBy(css = "input[type='submit']")
	private WebElement submitBtn; // save new computer or edited on

	@FindBy(css = "a[class='btn']")
	private WebElement cancelBtn; // cancel button on view or add new computer
									// form

	@FindBy(css = "input[value='Delete this computer']")
	private WebElement deleteComputerBtn; // delete computer button on view form

	public ComputerDetailPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Waits for computerName text is visible and setText
	 * 
	 * @param name
	 * @return Form with added text
	 */
	public ComputerDetailPage setComputerName(String name) {

		waitForVisibilityOf(computerName);
		setText(computerName, name);
		return this;
	}

	/**
	 * Waits for introduced date text field to be visible and setText
	 * 
	 * @param introducedDate
	 * @return Form with added text
	 */
	public ComputerDetailPage setIntroduced(String introducedDate) {
		waitForVisibilityOf(introduced);
		setText(introduced, introducedDate);
		return this;
	}

	/**
	 * Waits for discontinued date text field to be visible and setText
	 * 
	 * @param discontinuedDate
	 * @return Form with added text
	 */
	public ComputerDetailPage setdiscontinued(String discontinuedDate) {

		waitForVisibilityOf(discontinued);
		setText(discontinued, discontinuedDate);
		return this;
	}

	/**
	 * Select a company name
	 * 
	 * @param companyName
	 * @return Form with selected company
	 */
	public ComputerDetailPage setCompany(String companyName) {

		waitForVisibilityOf(company);
		selectByVisibleText(company, companyName);
		return this;

	}

	/**
	 * Get computer name from form
	 * 
	 * @return computer name
	 */
	public String getComputerName() {
		waitForVisibilityOf(computerName);
		return computerName.getAttribute("value");
	}

	/**
	 * Get introduced date field from form
	 * 
	 * @return introduced date
	 */
	public String getIntroduced() {
		waitForVisibilityOf(introduced);
		if (introduced.getAttribute("value").isEmpty())
			return null;
		return introduced.getAttribute("value");
	}

	/**
	 * Get discontinued date from form
	 * 
	 * @return discontinued date
	 */
	public String getdiscontinued() {
		waitForVisibilityOf(discontinued);
		if (discontinued.getAttribute("value").isEmpty())
			return null;
		return discontinued.getAttribute("value");
	}

	/**
	 * Get company selected
	 * 
	 * @return company name
	 */
	public String getCompany() {
		waitForVisibilityOf(company);
		if (company.getAttribute("value").isEmpty()) {
			return null;
		}
		waitForVisibilityOf(selectedCompany);
		return selectedCompany.getText();
	}

	/**
	 * Clicks on save computer button
	 */
	public void submitComputer() {
		waitForVisibilityOf(submitBtn);
		submitBtn.click();

	}

	/**
	 * Clicks on cancel button
	 */
	public void cancel() {
		waitForVisibilityOf(cancelBtn);
		cancelBtn.click();
	}

	/**
	 * Click on delete button on the form
	 * 
	 * @return HomePage
	 */
	public HomePage deleteComputer() {
		waitForVisibilityOf(deleteComputerBtn);
		deleteComputerBtn.click();
		return new HomePage(driver);
	}

	/**
	 * clear name from edited form
	 * 
	 * @return computer form
	 */
	public ComputerDetailPage clearName() {
		waitForVisibilityOf(computerName);
		computerName.clear();
		return this;
	}

	/**
	 * clear introduced date from form
	 * 
	 * @return computer form
	 */
	public ComputerDetailPage clearIntroduced() {
		waitForVisibilityOf(introduced);
		introduced.clear();
		return this;
	}

	/**
	 * clear discontinued date from form
	 * 
	 * @return computer form
	 */
	public ComputerDetailPage clearDiscontinued() {
		waitForVisibilityOf(discontinued);
		discontinued.clear();
		return this;
	}

}
