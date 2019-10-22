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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.konakart.KonakartParallelTesting.constants.FilePath;
import com.konakart.KonakartParallelTesting.constants.GridConnection;
import com.konakart.KonakartParallelTesting.extentreports.ExtentReport;
import com.konakart.KonakartParallelTesting.utils.ReadPropertiesFile;

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
	@Parameters("browser")
	@BeforeClass
	public void intitailizeBrowser(String browser) throws IOException {
		this.browser = browser;
		baseClass = ReadPropertiesFile.loadProperty(FilePath.CONFIG_FILE);
		url = baseClass.getProperty("url");
		// browser = baseClass.getProperty("browser");
		con = baseClass.getProperty("connection");
		huburl = baseClass.getProperty("remoteconnectionurl");

		System.out.println("browser is " + browser);
		if (con.equals("normal")) {

			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", FilePath.CHROME_PATH);

				driver = new ChromeDriver();
				driver.get(url);

			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", FilePath.FIREFOX_PATH);
				driver = new FirefoxDriver();
				driver.get(url);
			} else if (browser.equalsIgnoreCase("internet explorer")) {
				System.setProperty("webdriver.ie.driver", FilePath.IE_PATH);
				DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
				ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, url);
				driver = new InternetExplorerDriver(ieCaps);
			}
		} else if (con.equals("grid")) {
			WebDriver drv = GridConnection.GridCon(driver, browser, huburl);
			ExtentReport.driver = drv;
			driver.get(url);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

	}

	@AfterClass()
	public void endClass(){
		driver.quit();
	}
}
