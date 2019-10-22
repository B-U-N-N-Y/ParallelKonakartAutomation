package com.konakart.KonakartParallelTesting.testscripts;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.konakart.KonakartParallelTesting.constants.FilePath;
import com.konakart.KonakartParallelTesting.dataProvider.TestDataProvider;
import com.konakart.KonakartParallelTesting.helpers.Utility;
import com.konakart.KonakartParallelTesting.pages.ComputerperipheralFunction;
import com.konakart.KonakartParallelTesting.pages.SearchBoxFunction;
import com.konakart.KonakartParallelTesting.testbase.TestBase;
import com.konakart.KonakartParallelTesting.utils.ReadPropertiesFile;

public class ComuterPeripheralsFunctionality extends TestBase {

	public static Logger log;
	Properties loc = ReadPropertiesFile.loadProperty(FilePath.LOCATOR_FILE);
	SearchBoxFunction searchBoxFunction;
	ComputerperipheralFunction computerperipheralFunction;
	Utility utility;

	@Test(priority = 6, dataProvider = "graphicsoption", dataProviderClass = TestDataProvider.class)
	public void searchBoxWithoutSearchOption(String[] data) {
		utility = new Utility(driver);
		computerperipheralFunction = new ComputerperipheralFunction(driver);
		searchBoxFunction = new SearchBoxFunction(driver);
		computerperipheralFunction = new ComputerperipheralFunction(driver);
		log = Logger.getLogger(ComuterPeripheralsFunctionality.class);
		searchBoxFunction.serachBoxInput(data, loc);
		utility.clickElement(loc.getProperty("loc.konakart.search.btn"));
		computerperipheralFunction.erroMsgValidation(data[2], log, loc);
	}

	@Test(priority = 7, dataProvider = "graphicsoption", dataProviderClass = TestDataProvider.class)
	public void searchBoxWithSearchOption(String[] data) {
		log = Logger.getLogger(ComuterPeripheralsFunctionality.class);

		searchBoxFunction.serachBoxInput(data, loc);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Utility.selectDropdownByIndex(loc.getProperty("loc.konakart.search.txtbox"),
		// 1);
		utility.clickElement(loc.getProperty("loc.konakart.searchbox.options"));
	}

	@Test(priority = 8, dataProvider = "product details", dataProviderClass = TestDataProvider.class)
	public void productDetailsVerify(String[] data) {
		log = Logger.getLogger(ComuterPeripheralsFunctionality.class);

		computerperipheralFunction.productValidation(data, log, loc);
	}

}
