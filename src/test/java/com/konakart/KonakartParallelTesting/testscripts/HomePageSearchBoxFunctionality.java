package com.konakart.KonakartParallelTesting.testscripts;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.konakart.KonakartParallelTesting.constants.FilePath;
import com.konakart.KonakartParallelTesting.dataProvider.TestDataProvider;
import com.konakart.KonakartParallelTesting.helpers.Utility;
import com.konakart.KonakartParallelTesting.pages.SearchBoxFunction;
import com.konakart.KonakartParallelTesting.testbase.TestBase;
import com.konakart.KonakartParallelTesting.utils.ReadPropertiesFile;

public class HomePageSearchBoxFunctionality extends TestBase {
	public Logger log;
	Properties loc = ReadPropertiesFile.loadProperty(FilePath.LOCATOR_FILE);
	SearchBoxFunction searchBoxFunction;
	Utility utility;

	@Test(priority = 1, dataProvider = "searchbox", dataProviderClass = TestDataProvider.class)
	public void searchBox(String[] data) {
		searchBoxFunction = new SearchBoxFunction(driver);
		utility = new Utility(driver);
		log = Logger.getLogger(HomePageSearchBoxFunctionality.class);

		searchBoxFunction.isRedirection(driver, data[2], log);
		searchBoxFunction.serachBoxInput(data, loc);
		utility.clickElement(loc.getProperty("loc.konakart.search.btn"));
		searchBoxFunction.validateProduct(data, log, loc);
		utility.clickElement(loc.getProperty("loc.konakart.home.btn"));
		searchBoxFunction.negativeInputvalidtion(data, log, loc);
	}
}