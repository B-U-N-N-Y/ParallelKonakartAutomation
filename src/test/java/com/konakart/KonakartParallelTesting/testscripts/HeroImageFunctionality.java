package com.konakart.KonakartParallelTesting.testscripts;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.konakart.KonakartParallelTesting.constants.FilePath;
import com.konakart.KonakartParallelTesting.dataProvider.TestDataProvider;
import com.konakart.KonakartParallelTesting.helpers.Utility;
import com.konakart.KonakartParallelTesting.pages.HeroImageFunction;
import com.konakart.KonakartParallelTesting.testbase.TestBase;
import com.konakart.KonakartParallelTesting.utils.ReadPropertiesFile;

public class HeroImageFunctionality extends TestBase {
	public  Logger log;
	Properties loc = ReadPropertiesFile.loadProperty(FilePath.LOCATOR_FILE);
	HeroImageFunction heroImageFunction;
	Utility utility;
	@Test(priority = 2)
	public void heroImageSelect() {
		utility = new Utility(driver);
		heroImageFunction = new HeroImageFunction(driver);
		log = Logger.getLogger(HeroImageFunctionality.class);
		
		utility.clickElement(loc.getProperty("loc.konakart.heroimg.btn"));
	}

	@Test(priority = 3, dataProvider = "heroimage", dataProviderClass = TestDataProvider.class)
	public void heroImgContent(String[] data) {
		log = Logger.getLogger(HeroImageFunctionality.class);

		
		heroImageFunction.heroImgContentValidation(driver, data, log, loc);

	}

	@Test(priority = 4, dataProvider = "reviewdropdown", dataProviderClass = TestDataProvider.class)
	public void heroImgSortingDateFunctionality(String[] data) {
		log = Logger.getLogger(HeroImageFunctionality.class);

		heroImageFunction.heroImgSortOption(data[0], loc);
		heroImageFunction.sortingDateValidation(data[0], log, loc);
		heroImageFunction.heroImgSortOption(data[1], loc);
		heroImageFunction.sortingDateValidation(data[1], log, loc);
	}

	@Test(priority = 5, dataProvider = "reviewdropdown", dataProviderClass = TestDataProvider.class)
	public void heroImgSortingRatingFunctionality(String[] data) {
		log = Logger.getLogger(HeroImageFunctionality.class);

		heroImageFunction.heroImgSortOption(data[2], loc);
		heroImageFunction.starRatingValidation(data[2], log, loc);
		heroImageFunction.heroImgSortOption(data[3], loc);
		heroImageFunction.starRatingValidation(data[3], log, loc);
	}

}
