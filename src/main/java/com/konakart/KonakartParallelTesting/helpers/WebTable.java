package com.konakart.KonakartParallelTesting.helpers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebTable {

	Utility utility;
	List<WebElement> tableRow;
	List<WebElement> tableColumn;
	List<Integer> colnum;
	WebDriver driver;
	String cellData = null;

	public WebTable(WebDriver driver) {
		this.driver = driver;
		utility = new Utility(driver);
	}

	public int getTableRowNum(String tableLocator) {

		tableRow = utility.getElement(tableLocator).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		return tableRow.size();
	}

	public int getTableColumnNum(String tableLocator, int rowNumber) {
		tableColumn = utility.getElement(tableLocator).findElement(By.tagName("tbody")).findElements(By.tagName("tr"))
				.get(rowNumber).findElements(By.tagName("td"));
		return tableColumn.size();

	}

	public String getCellDataByColumnAndRowNumber(String tableLocator, int rowNumber, int columnnumber) {

		for (int index = 0; index < getTableRowNum(tableLocator); index++) {
			cellData = utility.getElement(tableLocator).findElement(By.tagName("tbody")).findElements(By.tagName("tr"))
					.get(rowNumber - 1).findElements(By.tagName("td")).get(columnnumber - 1).getText();
		}
		return cellData;
	}

	public String getPositionOfData(String tableLocator, String cellData) {

		int rowCount = getTableRowNum(tableLocator);
		for (int rowindex = 0; rowindex < rowCount; rowindex++) {
			int columnCount = getTableColumnNum(tableLocator, rowindex);
			for (int colIndex = 0; colIndex < columnCount; colIndex++) {
				String ActualcellData = utility.getElement(tableLocator).findElement(By.tagName("tbody"))
						.findElements(By.tagName("tr")).get(rowindex).findElements(By.tagName("td")).get(colIndex)
						.getText();
				if (cellData.equals(ActualcellData)) {
					return "The position of " + cellData + " is row=" + (rowindex + 1) + "column= " + (colIndex + 1);
				}
			}

		}
		return "No data found";
	}

	public void getTableData(String tableLocator) {

		int rowCount = getTableRowNum(tableLocator);
		for (int rowindex = 0; rowindex < rowCount; rowindex++) {
			int columnCount = getTableColumnNum(tableLocator, rowindex);
			for (int colIndex = 0; colIndex < columnCount; colIndex++) {
				String ActualcellData = utility.getElement(tableLocator).findElement(By.tagName("tbody"))
						.findElements(By.tagName("tr")).get(rowindex).findElements(By.tagName("td")).get(colIndex)
						.getText();
				System.out.print(ActualcellData+" ");
			}
			System.out.println();
		}

	}

}
