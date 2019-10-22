package com.konakart.KonakartParallelTesting.pages;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.konakart.KonakartParallelTesting.extentreports.ExtentReport;
import com.konakart.KonakartParallelTesting.helpers.Utility;
import com.konakart.KonakartParallelTesting.logreports.LogReport;

/**
 * This method check the search box functionality
 * 
 * @author arjun.santra
 *
 */
public class SearchBoxFunction {

	Utility utility;
	LogReport logreport;

	public SearchBoxFunction(WebDriver driver) {
		 logreport = new LogReport();
		utility = new Utility(driver);
	}

	/**
	 * This method takes input as string array and select and click drop down option
	 * dynamically
	 * 
	 * @param data
	 * @param loc
	 */
	public void serachBoxInput(String[] data, Properties loc) {
		utility.selectDropdownByText(loc.getProperty("loc.konakart.homepage.dpdn"), data[0]);
		utility.clickAndSendText(loc.getProperty("loc.konakart.search.txtbox"), data[1]);
	}

	/**
	 * This method validate the web page redirect to proper page or not
	 * 
	 * @param driver
	 * @param validatedata
	 * @param log
	 */
	public void isRedirection(WebDriver driver, String validatedata, Logger log) {
		String title = driver.getTitle();
		Assert.assertEquals(title, validatedata, "Page is not redirect properly");
		//ExtentReport.messagePrint("Page is redirecting to proper web page");
		log.info("Page is redirecting to proper web page");
	}

	/**
	 * This method validate that selected product is available or not
	 * 
	 * @param data
	 * @param log
	 * @param loc
	 */
	public void validateProduct(String[] data, Logger log, Properties loc) {

		String[] title = utility.getElement(loc.getProperty("loc.konakart.producttitle.txt")).getText().split("\n");
		for (int index = 0; index < title.length; index++) {
			Assert.assertEquals(title[index], data[5 + index], "Product title validationfailed");
			//ExtentReport.messagePrint("Product title validate successfully");
			log.info("Product title validate successfully");
		}
	}

	/**
	 * By giving negative input validating that Web application working properly or
	 * not
	 * 
	 * @param data
	 * @param log
	 * @param loc
	 */
	public void negativeInputvalidtion(String[] data, Logger log, Properties loc) {
		utility.selectDropdownByText(loc.getProperty("loc.konakart.homepage.dpdn"), data[0]);
		utility.clickAndSendText(loc.getProperty("loc.konakart.search.txtbox"), data[3]);
		utility.clickElement(loc.getProperty("loc.konakart.search.btn"));
		String errormsg = utility.getElement(loc.getProperty("loc.konakart.erromsg.txt")).getText();
		Assert.assertEquals(errormsg, data[4], "Negative input validation failed");
	//	ExtentReport.messagePrint("Negative input validation passed");
		log.info("Negative input validation passed");
	}
}
