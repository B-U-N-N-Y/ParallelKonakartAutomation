package com.konakart.KonakartParallelTesting.pages;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.konakart.KonakartParallelTesting.extentreports.ExtentReport;
import com.konakart.KonakartParallelTesting.helpers.Utility;
import com.konakart.KonakartParallelTesting.logreports.LogReport;

/**
 * This class check computer peripheral option functionality.
 * 
 * @author arjun.santra
 *
 */
public class ComputerperipheralFunction {
	 LogReport logreport = new LogReport();
	 Utility utility ;
	 WebDriver driver;
	 public ComputerperipheralFunction(WebDriver driver) {
		 utility = new Utility(driver);
		 this.driver=driver;
	 }
	/**
	 * This method validate that if any wrong input given by the user that time web
	 * application throw a error message or not
	 * 
	 * @param validatedata
	 * @param log
	 * @param loc
	 */
	public  void erroMsgValidation(String validatedata, Logger log, Properties loc) {
		String erromsg = utility.getElement(loc.getProperty("loc.konakart.erromsg.txt")).getText();
		Assert.assertTrue(erromsg.contains(validatedata), "Erro message validation failed");
		ExtentReport.messagePrint("Error Message validate successfully");
		log.info("Error Message validate successfully");
	}

	/**
	 * This method validate the product name and price.
	 * 
	 * @param data
	 * @param log
	 * @param loc
	 */
	public  void productValidation(String[] data, Logger log, Properties loc) {
		List<WebElement> producttitlelist = utility.getElementsList(loc.getProperty("loc.konakart.product.title"));
		List<WebElement> productpricelist = utility.getElementsList(loc.getProperty("loc.konakart.product.price"));
		int loopcount = productpricelist.size();
		for (int index = 0; index < loopcount; index++) {
			String producttitle = producttitlelist.get(index).getText();
			String productprice = productpricelist.get(index).getText();
			Assert.assertEquals(producttitle, data[index], "Product title validation failed");
			ExtentReport.messagePrint("Product title validate successfully");
			log.info("Product title validate successfully");
			Assert.assertEquals(productprice, data[loopcount + index], "Product price validation failed");
			ExtentReport.messagePrint("Product price validate successfully");
			log.info("Product price validate successfully");

		}

	}
}
