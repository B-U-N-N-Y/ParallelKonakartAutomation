package com.konakart.KonakartParallelTesting.testscripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

import com.konakart.KonakartParallelTesting.constants.FilePath;
import com.konakart.KonakartParallelTesting.dataProvider.TestDataProvider;
import com.konakart.KonakartParallelTesting.extentreports.ExtentReporterNG;
import com.konakart.KonakartParallelTesting.utils.ReadPropertiesFile;

public class DynamicXML {

	@Test(dataProvider = "dynamic_class", dataProviderClass = TestDataProvider.class)
	public void runTestFile(String classname, String statusofclass,String url) throws ClassNotFoundException {
		Properties props = ReadPropertiesFile.loadProperty(FilePath.CONFIG_FILE);

		List<String> browsernames = new ArrayList<String>();
		String[] array = props.getProperty("browser").split(",");
		for (String browser : array) {
			browsernames.add(browser);
		}

		
		if (statusofclass.equalsIgnoreCase("Y")) {
			browsernames.forEach(browser -> System.out.println(browser));
			XmlSuite xmlSuite = new XmlSuite();
			xmlSuite.setName("suite");
			xmlSuite.setVerbose(1);
			xmlSuite.setParallel(ParallelMode.TESTS);
			xmlSuite.setThreadCount(browsernames.size());

			List<XmlSuite> suites = new ArrayList<XmlSuite>();

			for (String browser : browsernames) {

				XmlTest xmlTest1 = new XmlTest(xmlSuite);
				Map<String, String> parameter = new HashMap<String, String>();
				parameter.put("browser", browser);
				parameter.put("url", url);
				xmlTest1.setParameters(parameter);
				xmlTest1.setName("Test validate in " + browser);

				// code to read your testNg file

				XmlClass Class = new XmlClass();
				Class.setName(classname);

				List<XmlClass> xmlClassList = new ArrayList<XmlClass>();
				xmlClassList.add(Class);

				xmlTest1.setXmlClasses(xmlClassList);

			}

			suites.add(xmlSuite);

			TestNG testng = new TestNG();
			List<Class<? extends ITestNGListener>>listenerClasses=new ArrayList<Class<? extends ITestNGListener>>();
			listenerClasses.add(ExtentReporterNG.class);
			testng.setListenerClasses(listenerClasses);

			testng.setXmlSuites(suites);

			testng.run();
		}

	}
}
