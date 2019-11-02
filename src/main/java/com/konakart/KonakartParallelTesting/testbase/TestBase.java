package com.konakart.KonakartParallelTesting.testbase;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.konakart.KonakartParallelTesting.constants.FilePath;
import com.konakart.KonakartParallelTesting.constants.GridConnection;
import com.konakart.KonakartParallelTesting.utils.ReadPropertiesFile;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class operate to choose the driver and choose that it run with grid
 * connection or without grid connection
 * 
 * @author arjun.santra
 *
 */
public class TestBase {
	public WebDriver driver;
	Properties baseClass;
	String url;
	public String browser;
	int downloadFile;
	String con;

	String huburl;

	/**
	 * This method choose which browser invoke and also choose normal execution
	 * happen or grid execution happen
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@Parameters({"browser","url"})
	@BeforeTest
	public void intitailizeBrowser(String browser,String url) throws IOException {
		this.browser = browser;
		baseClass = ReadPropertiesFile.loadProperty(FilePath.CONFIG_FILE);
	//	url = baseClass.getProperty("url");
		// browser = baseClass.getProperty("browser");
		con = baseClass.getProperty("connection");
		huburl = baseClass.getProperty("remoteconnectionurl");

		System.out.println("browser is " + browser);
		if (con.equals("normal")) {

			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().arch64().setup();

				driver = new ChromeDriver();
				driver.get(url);

			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().arch64().setup();
				driver = new FirefoxDriver();
				driver.get(url);
			} else if (browser.equalsIgnoreCase("internet explorer")) {
				WebDriverManager.iedriver().arch32().setup();
				DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
				ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, url);
				driver = new InternetExplorerDriver(ieCaps);
			}
		} else if (con.equals("grid")) {
			WebDriver drv = GridConnection.GridCon(driver, browser, huburl);
			//ExtentReport.driver = drv;
			driver.get(url);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

	}

	@AfterClass()
	public void endTest() {
		driver.quit();
	}
}
