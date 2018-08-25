package com.backbase.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;

	public int timeInMillis = 50;

	public int timeInSecs = 10;

	final WebDriverWait wait;

	Select select;

	BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		wait = new WebDriverWait(driver, timeInSecs, timeInMillis);
	}

	/**
	 * Click method that takes a web element and perform a click on it
	 * 
	 * @param element
	 */
	public void click(WebElement element) {
		element.click();
	}

	/**
	 * setText method that takes a web element and write down the given text in
	 * it
	 * 
	 * @param element
	 * @param text
	 */
	public void setText(WebElement element, String text) {
		element.sendKeys(text);
	}

	/**
	 * waitForVisibilityof method, takes a web element and wait until it shows
	 * on browser
	 * 
	 * @param element
	 */
	public void waitForVisibilityOf(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Select action on web element
	 * 
	 * @param element
	 *            web element
	 * @param text
	 *            desired text to select on
	 */
	public void selectByVisibleText(WebElement element, String text) {
		select = new Select(element);
		select.selectByVisibleText(text);
	}
}
