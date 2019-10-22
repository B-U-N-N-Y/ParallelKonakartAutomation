package com.konakart.KonakartParallelTesting.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.konakart.KonakartParallelTesting.extentreports.ExtentReport;
import com.konakart.KonakartParallelTesting.helpers.Utility;
import com.konakart.KonakartParallelTesting.logreports.LogReport;

/**
 * This class check the functionality of hero image.
 * 
 * @author arjun.santra
 *
 */
public class HeroImageFunction {

	 LogReport logreport;
	 Utility utility ;
	 WebDriver driver;
	 public HeroImageFunction(WebDriver driver) {
		 logreport = new LogReport();
		 utility = new Utility(driver);
		 this.driver=driver;
	 }

	/**
	 * This method validate the which hero image is selected dynamically and also
	 * validate the of content hero image.
	 * 
	 * @param driver
	 * @param data
	 * @param log
	 */
	public  void heroImgContentValidation(WebDriver driver, String[] data, Logger log, Properties loc) {

		if (driver.getTitle().equalsIgnoreCase(data[0])) {
			pageDescriptionvalidation(data[1], log, loc);
			specificationValidation(data[2], log, loc);
			utility.clickElement(loc.getProperty("loc.konakart.review.btn"));
		}

	}

	/**
	 * This method validate the product specification
	 * 
	 * @param validatedata
	 * @param log
	 * @param loc
	 */
	private  void specificationValidation(String validatedata, Logger log, Properties loc) {
		utility.clickElement(loc.getProperty("loc.konakart.heroimg.specification.btn"));
		// utility.scrollDownPage(0, 300);
		String specification = utility.getElement(loc.getProperty("loc.konakart.heroimg.specification.txt")).getText();
		// System.out.println(specification);
		Assert.assertTrue(specification.contains(validatedata), "Failed");
		ExtentReport.messagePrint("specification validate successfully");
		log.info("specification validate successfully");
	}

	/**
	 * This method validate the product description
	 * 
	 * @param validatedata
	 * @param log
	 * @param loc
	 */
	private  void pageDescriptionvalidation(String validatedata, Logger log, Properties loc) {
		utility.scrollDownPage(0, 300);
		String description = utility.getElement(loc.getProperty("loc.konakart.heroimg.description.txt")).getText();
		// System.out.println(description);
		Assert.assertTrue(description.contains(validatedata), "Failed");
		ExtentReport.messagePrint("description validate successfully");
		log.info("description validate successfully");
	}

	/**
	 * This method validate the sorting the date functionality
	 * 
	 * @param sortingorder
	 * @param log
	 * @param loc
	 */
	public  void sortingDateValidation(String sortingoption, Logger log, Properties loc) {

		List<WebElement> elements = utility.getElementsList(loc.getProperty("loc.konakart.reviewcount"));
		int loopCount = elements.size();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
		for (int index = 1; index < loopCount; index++) {
			String string1 = utility
					.getElement(loc.getProperty("loc.konakart.reviewdates").replace("index", index + "")).getText();
			String string2 = utility
					.getElement(loc.getProperty("loc.konakart.reviewdates").replace("index", (index + 1) + ""))
					.getText();
			System.out.println(string1+"   "+string2);
			String split1[] = string1.split(" ", 2);
			String split2[] = string2.split(" ", 2);
			if (split1[1].substring(1, 2).equals(" ")) {
				split1[1] = "0" + split1[1];
			}
			if (split2[1].substring(1, 2).equals(" ")) {
				split2[1] = "0" + split2[1];
			}
			LocalDate date1 = LocalDate.parse(split1[1], formatter);
			LocalDate date2 = LocalDate.parse(split2[1], formatter);
			int difference = date1.compareTo(date2);
			if (sortingoption.equalsIgnoreCase(
					utility.getElement(loc.getProperty("loc.konakart.sortbydatemostrecent.txt")).getText())) {
				Assert.assertTrue(difference >= 0, "Sorting Functionality Failed");
				ExtentReport.messagePrint("Sorting by date Functionality Working Properly");
				log.info("Sorting by date Functionality Working Properly");
			} else if (sortingoption.equalsIgnoreCase(
					utility.getElement(loc.getProperty("loc.konakart.sortbydateoldest.txt")).getText())) {
				Assert.assertTrue(difference <= 0, "Sorting Functionality Failed");
				ExtentReport.messagePrint("Sorting by date Functionality Working Properly");
				log.info("Sorting by date Functionality Working Properly");
			}

		}
	}

	/**
	 * This method to select drop down option by index
	 * 
	 * @param index
	 * @param loc
	 */
	public  void heroImgSortOption(String sortingoption, Properties loc) {
		utility.scrollDownPage(0, 200);
		utility.selectDropdownByText(loc.getProperty("loc.konakart.review.sort.dpdn"), sortingoption);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// utility.explicitWait(loc.getProperty("loc.konakart.heroimgtitle.txt"),
		// 30);
	}

	/**
	 * This method validate the sorting functionality of review rating
	 * 
	 * @param sortOrder
	 * @param log
	 * @param loc
	 */
	public  void starRatingValidation(String sortingoption, Logger log, Properties loc) {
		List<WebElement> elements = utility.getElementsList(loc.getProperty("loc.konakart.reviewcount"));
		int loopCount = elements.size();
		for (int index = 1; index < loopCount; index++) {
			int count1 = utility
					.getElementsList(loc.getProperty("loc.konakart.ratingcount.txt").replace("index", index + ""))
					.size();
			int count2 = utility
					.getElementsList(loc.getProperty("loc.konakart.ratingcount.txt").replace("index", (index + 1) + ""))
					.size();
			int difference = count1 - count2;
			if (sortingoption.equalsIgnoreCase(
					utility.getElement(loc.getProperty("loc.konakart.sortbyratinghightolow.txt")).getText())) {
				Assert.assertTrue(difference >= 0, "Sorting Functionality Failed");
				ExtentReport.messagePrint("Sorting by rating Functionality Working Properly");
				log.info("Sorting by rating Functionality Working Properly");
			} else if (sortingoption.equalsIgnoreCase(
					utility.getElement(loc.getProperty("loc.konakart.sortbyratinglowtohigh.txt")).getText())) {
				Assert.assertTrue(difference <= 0, "Sorting Functionality Failed");
				ExtentReport.messagePrint("Sorting by rating Functionality Working Properly");
				log.info("Sorting by rating Functionality Working Properly");
			}
		}
	}

}
