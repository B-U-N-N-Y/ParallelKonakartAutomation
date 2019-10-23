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
 * This class check the price slide bar functionality.
 * 
 * @author arjun.santra
 *
 */
public class PriceSlideBar {
	LogReport logreport = new LogReport();
	Utility utility;
	WebDriver driver;

	public PriceSlideBar(WebDriver driver) {
		this.driver = driver;
		utility = new Utility(driver);

	}

	/**
	 * This method move the price slide bar according to given input value.
	 * 
	 * @param xoffset
	 * @param loc
	 */
	public void priceSlideBar(String xoffset, Properties loc) {
		utility.clickElement(loc.getProperty("loc.konakart.hearder.computerperipherals"));
		utility.scrollDownPage(0, 400);
		utility.setAttributeValue(loc.getProperty("loc.konakart.priceslider"), Integer.parseInt(xoffset));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method validate that all the available products prices are in that price
	 * slide bar range.
	 * 
	 * @param log
	 * @param loc
	 */
	public void priceValidation(Logger log, Properties loc) {
		List<WebElement> prices = utility.getElementsList(loc.getProperty("loc.koanakart.pricesacordingslidebar"));
		String[] pricerange = utility.getElement(loc.getProperty("loc.konakart.pricerange")).getText().split("-");
		String minprice = pricerange[0].replace("$", "");
		String maxprice = pricerange[1].replace("$", "");
		for (WebElement price : prices) {
			String productprice = price.getText().replace("$", "");
			Assert.assertTrue(
					Float.parseFloat(productprice) >= Float.parseFloat(minprice)
							&& Float.parseFloat(productprice) <= Float.parseFloat(maxprice),
					"Price slidebar fuctionality validation failed");
		//	ExtentReport.messagePrint("Price slidebar fuctionality validate successfully");
			log.info("Price slidebar fuctionality validate successfully");
		}

	}
}
