package com.konakart.KonakartParallelTesting.testscripts;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.konakart.KonakartParallelTesting.constants.FilePath;
import com.konakart.KonakartParallelTesting.dataProvider.TestDataProvider;
import com.konakart.KonakartParallelTesting.helpers.Utility;
import com.konakart.KonakartParallelTesting.helpers.WebTable;
import com.konakart.KonakartParallelTesting.pages.SearchBoxFunction;
import com.konakart.KonakartParallelTesting.testbase.TestBase;
import com.konakart.KonakartParallelTesting.utils.ReadPropertiesFile;

public class TableTest extends TestBase{
	Properties loc = ReadPropertiesFile.loadProperty(FilePath.LOCATOR_FILE);
	Utility utility;
	WebTable webTable;

	@Test
	public void test() {
		webTable=new WebTable(driver);
		utility = new Utility(driver);
		utility.clickElement(loc.getProperty("loc.departure.btn"));
		webTable.getTableData(loc.getProperty("loc.presentmonthtable"));
		webTable.getPositionOfData(loc.getProperty("loc.presentmonthtable"), "30");
		
		
		
	
	}
	
}
	