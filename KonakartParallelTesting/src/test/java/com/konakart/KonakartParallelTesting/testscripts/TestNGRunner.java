package com.konakart.KonakartParallelTesting.testscripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.konakart.KonakartParallelTesting.constants.FilePath;
import com.konakart.KonakartParallelTesting.utils.ReadPropertiesFile;

public class TestNGRunner {
	@SuppressWarnings("deprecation")
	@Test
	public void runTestFile() throws ClassNotFoundException {
		Properties props = ReadPropertiesFile.loadProperty(FilePath.CONFIG_FILE);

		
		List<String> names = new ArrayList<String>();
		String[] arr = props.getProperty("browser").split(",");
		for (String name : arr) {
			names.add(name);
		}

		names.forEach(name -> System.out.println(name));

		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName("suite");
		xmlSuite.setVerbose(1);
		xmlSuite.setParallel("tests");
		xmlSuite.setThreadCount(3);

		List<XmlSuite> suites = new ArrayList<XmlSuite>();

		for (String name : names) {

			XmlTest xmlTest1 = new XmlTest(xmlSuite);
			Map<String, String> parameter1 = new HashMap<String, String>();
			parameter1.put("browser", name);
			xmlTest1.setParameters(parameter1);
			xmlTest1.setName("Test validate in " + name);

			// code to read your testNg file

			XmlClass login1Class = null;
			login1Class = new XmlClass(HomePageSearchBoxFunctionality.class);
			XmlClass login2Class = null;
			login2Class = new XmlClass(HeroImageFunctionality.class);
			XmlClass login3Class = null;
			login3Class = new XmlClass(ComuterPeripheralsFunctionality.class);
			XmlClass login4Class = null;
			login4Class = new XmlClass(PriceSlideBarFunctionality.class);
			
			List<XmlClass> xmlClassList1 = new ArrayList<XmlClass>();
			xmlClassList1.add(login1Class);
			xmlClassList1.add(login2Class);
			xmlClassList1.add(login3Class);
			xmlClassList1.add(login4Class);
			
			
			xmlTest1.setXmlClasses(xmlClassList1);

		}

		suites.add(xmlSuite);

		TestNG testng = new TestNG();

		testng.setXmlSuites(suites);

		testng.run();

	}
}
