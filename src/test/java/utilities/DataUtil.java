package utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import base.BaseTest;

public class DataUtil extends BaseTest {

	@DataProvider(name = "dp")
	public static Object[][] getData(Method m) {
		String sheetName = m.getName(); 			// SHEET name and TEST METHOD name should be same
//		sheetName = "doLogin"; 						// hard-coded SheetName
		int rowCount = excel.getRowCount(sheetName);
		int colCount = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rowCount - 1][colCount];

		for (int rows = 2; rows <= rowCount; rows++) {

			for (int cols = 0; cols < colCount; cols++) {

				data[rows - 2][cols] = excel.getCellData(sheetName, cols, rows);

			}
		}
//		for(Object a[]:data) {
//
//			System.out.println("data====>"+Arrays.toString(a));
//		}
		return data;
	}
}
