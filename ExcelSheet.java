package Week5Day2Assignment;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheet {

	public static String[][] excelSheet(String excelFilepath) throws IOException {
		
        //To open the excel file
		XSSFWorkbook book = new XSSFWorkbook(excelFilepath);
		//To open a sheet from the Excel
		XSSFSheet sheet = book.getSheetAt(0);
		//To find the no. of active rows in the Sheet
		int rowCount = sheet.getLastRowNum();
		System.out.println("Row Count " +rowCount );
		//To find the no. of active columns in the Sheet
		short ColCount = sheet.getRow(0).getLastCellNum();
		System.out.println("Column Count " +ColCount);
		

		//Create 2d Arrays to store data
		String[][] data = new String[rowCount][ColCount];
		
		//to retrieve data using "for" loop
				//to get to the each row
		for(int i=1;i<=rowCount;i++)
		{
			XSSFRow eachRow = sheet.getRow(i);
		
		
		//to read each column data
		for (int j=0;j<ColCount;j++)
		{
			
			XSSFCell eachCell = eachRow.getCell(j);
			String cellValue = eachCell.getStringCellValue();
			data[i-1][j] =cellValue;
			System.out.println(cellValue);
		}
		
		}
		book.close();
		return data;
	}

}
