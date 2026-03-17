package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public FileInputStream fin;
	public FileOutputStream fout;
	public XSSFWorkbook workBook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public String filePath;
	
	public ExcelUtilities(String path) {
		filePath = path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fin = new FileInputStream(filePath);
		workBook = new XSSFWorkbook(fin);
		sheet = workBook.getSheet(sheetName);
		int r = sheet.getLastRowNum();
		fin.close();
		workBook.close();
		return r;
	}
	
	public int getColumnCount(String sheetName, int rowNumber) throws IOException {
		fin = new FileInputStream(filePath);
		workBook = new XSSFWorkbook(fin);
		sheet = workBook.getSheet(sheetName);
		int c = sheet.getRow(rowNumber).getLastCellNum();
		fin.close();
		workBook.close();
		return c;
	}
	
	public String getCellData(String sheetName, int rowNumber, int columnNumber) throws IOException {
		fin = new FileInputStream(filePath);
		workBook = new XSSFWorkbook(fin);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		cell = row.getCell(columnNumber);
		
		DataFormatter formatter = new DataFormatter();
		fin.close();
		workBook.close();
		return formatter.formatCellValue(cell);
	}

}
