package com.konakart.KonakartParallelTesting.testscripts;

import java.util.Properties;

import org.testng.annotations.Test;

import com.konakart.KonakartParallelTesting.constants.FilePath;
import com.konakart.KonakartParallelTesting.dataProvider.TestDataProvider;
import com.konakart.KonakartParallelTesting.helpers.Utility;
import com.konakart.KonakartParallelTesting.helpers.Waits;
import com.konakart.KonakartParallelTesting.testbase.TestBase;
import com.konakart.KonakartParallelTesting.utils.ReadPropertiesFile;

public class PhpTravelsTest extends TestBase {
	//WebDriver driver;
	Utility utility;
	Properties loc;
	Waits waits;
	//Properties config;

//	@BeforeTest
//	public void intialization() {
//		WebDriverManager.chromedriver().arch64().setup();
//		driver = new ChromeDriver();
//		utility = new Utility(driver);
//		loc = ReadPropertiesFile.loadProperty(FilePath.LOCATOR_FILE);
//		waits = new Waits();
//		config=ReadPropertiesFile.loadProperty(FilePath.CONFIG_FILE);
//	}

	@Test(dataProvider = "flightInput", dataProviderClass = TestDataProvider.class)
	public void flightBookinf(String[] flightData) {
		utility = new Utility(driver);
		loc = ReadPropertiesFile.loadProperty(FilePath.LOCATOR_FILE);
//		driver.get(config.getProperty("phptravelsurl"));
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().pageLoadTimeout(FilePath.TIMEOUT_INSECONDS, TimeUnit.SECONDS);
//		utility.clickElement("css,a.text-center.flights");

		utility.clickElement(loc.getProperty("loc.php.flights.btn"));
		utility.clickElement(loc.getProperty("loc.php.from.btn"));
		utility.clickAndSendText(loc.getProperty("loc.php.from.inputbox"), flightData[0]);
		waits.waitElementToBeClickable(driver,
				loc.getProperty("loc.php.from.dropdown").replace("index", flightData[1]));
		utility.clickElement(loc.getProperty("loc.php.from.dropdown").replace("index", flightData[1]));
		utility.clickElement(loc.getProperty("loc.php.to.btn"));
		utility.clickAndSendText(loc.getProperty("loc.php.to.inputbox"), flightData[2]);
		waits.waitElementToBeClickable(driver, loc.getProperty("loc.php.to.dropdown").replace("index", flightData[3]));
		utility.clickElement(loc.getProperty("loc.php.from.dropdown").replace("index", flightData[3]));

		String adultNumber = utility.getElement(loc.getProperty("loc.php.adultscount.txt")).getAttribute("value");

		utility.clickElementMultipleTime(Integer.parseInt(flightData[4]) - Integer.parseInt(adultNumber),
				loc.getProperty("loc.php.adultnumberincrease.btn"));
		String childrenNumber = utility.getElement(loc.getProperty("loc.php.childrencount.txt")).getAttribute("value");
		utility.clickElementMultipleTime(Integer.parseInt(flightData[5]) - Integer.parseInt(childrenNumber),
				loc.getProperty("loc.php.childrennumberincrease.btn"));
		String infantNumber = utility.getElement(loc.getProperty("loc.php.infantcount.txt")).getAttribute("value");
		utility.clickElementMultipleTime(Integer.parseInt(flightData[6]) - Integer.parseInt(infantNumber),
				loc.getProperty("loc.php.infantnumberincrease.btn"));
		utility.clickElement(loc.getProperty("loc.php.search.btn"));
	}
}
