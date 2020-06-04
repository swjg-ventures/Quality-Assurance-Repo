package Demo;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xls_Reader {
	
 	XSSFWorkbook wb;
	XSSFSheet sheet;
	
	public Xls_Reader(String excelPath) throws Exception
	{
		File file = new File(excelPath);
		FileInputStream fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		
	}
	
	
	public String getData(int sheetNo, int rowNo, int colNo)
	{	
		sheet = wb.getSheetAt(sheetNo);
		String data = sheet.getRow(rowNo).getCell(colNo).getStringCellValue();
		return data;	
	}
	
	
	
	
	
}

