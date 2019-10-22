package com.konakart.KonakartParallelTesting.testscripts;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.konakart.KonakartParallelTesting.constants.FilePath;
import com.konakart.KonakartParallelTesting.dataProvider.TestDataProvider;
import com.konakart.KonakartParallelTesting.pages.PriceSlideBar;
import com.konakart.KonakartParallelTesting.testbase.TestBase;
import com.konakart.KonakartParallelTesting.utils.ReadPropertiesFile;

public class PriceSlideBarFunctionality extends TestBase {
	public static Logger log;
	Properties loc = ReadPropertiesFile.loadProperty(FilePath.LOCATOR_FILE);
	PriceSlideBar priceSlideBar;

	@Test(priority = 9, dataProvider = "priceslidebar", dataProviderClass = TestDataProvider.class)
	public void priceSlideBar(String xoffset) {
		priceSlideBar = new PriceSlideBar(driver);
		log = Logger.getLogger(PriceSlideBarFunctionality.class);
		priceSlideBar.priceSlideBar(xoffset, loc);
		priceSlideBar.priceValidation(log, loc);

	}
}
