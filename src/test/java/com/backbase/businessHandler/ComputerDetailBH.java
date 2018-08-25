package com.backbase.businessHandler;

import java.util.ArrayList;

import com.backbase.pageObject.ComputerDetailPage;
import com.backbase.pageObject.HomePage;

public class ComputerDetailBH {

	ComputerDetailPage computerDetailPage;
	HomePage homePage;

	public ComputerDetailBH(ComputerDetailPage computerDetailPage, HomePage homePage) {
		this.computerDetailPage = computerDetailPage;
		this.homePage = homePage;
	}

	public ComputerDetailBH(ComputerDetailPage computerDetailPage) {
		this.computerDetailPage = computerDetailPage;
	}

	/**
	 * Fill computer form with name , introduced , discontinued and company then
	 * save changes
	 * 
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company
	 */
	public void addNewComputer(String name, String introduced, String discontinued, String company) {

		computerDetailPage = computerDetailPage.setComputerName(name);
		if (!introduced.isEmpty()) {
			computerDetailPage = computerDetailPage.setIntroduced(introduced);
		}
		if (!discontinued.isEmpty()) {
			computerDetailPage = computerDetailPage.setdiscontinued(discontinued);
		}
		if (!company.isEmpty()) {
			computerDetailPage = computerDetailPage.setCompany(company);
		}
		computerDetailPage.submitComputer();
	}

	/**
	 * Fill computer form with name , introduced , discontinued and company then
	 * cancel
	 * 
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company
	 */
	public void addNewComputerThenCancel(String name, String introduced, String discontinued, String company) {
		computerDetailPage = computerDetailPage.setComputerName(name);
		if (!introduced.isEmpty()) {
			computerDetailPage = computerDetailPage.setIntroduced(introduced);
		}
		if (!discontinued.isEmpty()) {
			computerDetailPage = computerDetailPage.setdiscontinued(discontinued);
		}
		if (!company.isEmpty()) {
			computerDetailPage = computerDetailPage.setCompany(company);
		}
		computerDetailPage.cancel();
	}

	/**
	 * Click on delete button in computer form
	 */
	public void deleteEntry() {
		computerDetailPage.deleteComputer();
	}

	/**
	 * Clear computer form from data and enter new data then click save
	 * 
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company
	 */
	public void editComputerEntry(String name, String introduced, String discontinued, String company) {
		computerDetailPage.clearName().clearIntroduced().clearDiscontinued();
		addNewComputer(name, introduced, discontinued, company);

	}

	/**
	 * Clear computer form from data then enter new data and click cancel
	 * 
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company
	 */
	public void editThenCancel(String name, String introduced, String discontinued, String company) {
		computerDetailPage.clearName().clearIntroduced().clearDiscontinued();
		addNewComputerThenCancel(name, introduced, discontinued, company);
	}

	/**
	 * Gets all info in computer form and put it in an arrayList
	 * 
	 * @return arrayList with data from the computer form
	 */
	public ArrayList<String> getComputerInfoFromView() {
		ArrayList<String> computerInfo = new ArrayList<>();

		computerInfo.add(computerDetailPage.getComputerName());
		computerInfo.add(computerDetailPage.getIntroduced());
		computerInfo.add(computerDetailPage.getdiscontinued());
		computerInfo.add(computerDetailPage.getCompany());

		return computerInfo;

	}

}
