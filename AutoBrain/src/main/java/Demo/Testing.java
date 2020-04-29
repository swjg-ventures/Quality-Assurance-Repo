package Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.pages.Login;

public class Testing extends Login{	
	
	
	
	
	
	public void CarNameList() throws Exception {
		
		System.out.println(ExcelRead(0, 0));
			
	}
	
	
	public void ExcelWrite(int row, int column, String webelement) throws Exception 
	{
		File file = new File ("C:\\Users\\Rajesh\\Desktop\\data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet  = workbook.getSheetAt(0);
		sheet.createRow(row).createCell(column).setCellValue(webelement);		
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);	
	}
	
	

	public String ExcelRead(int row, int column) throws Exception
	{
		File file = new File ("C:\\Users\\Rajesh\\Desktop\\data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet  = workbook.getSheetAt(0);
		int total_rows= sheet.getLastRowNum();
		System.out.println(total_rows);
		String cell_value = sheet.getRow(row).getCell(column).getStringCellValue().trim();
		return cell_value;
	}
	
}
