package utils;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "login-data")
	public Object[][] loginData() throws IOException{
		
		String path = ".\\testdata\\logindata.xlsx";
		ExcelUtilities xlutil = new ExcelUtilities(path);
		
		int rows = xlutil.getRowCount("Sheet1");
		int cols = xlutil.getColumnCount("Sheet1", 0);
		Object data[][] = new Object[rows][cols];
		
		for(int i = 1; i <= rows; i++) {
			for(int j = 0; j < cols; j++) {
				data[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		return data;
	}
}
