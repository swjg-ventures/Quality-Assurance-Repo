package Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.base.Base;


public class Testing extends Base {

	public static void main(String[] args) throws Exception {
	int total_rows = ExcelGetNumberOfRows();
	int newt = total_rows + 1;
	
	ArrayList<Date> dd = new ArrayList<Date>();
	
	for (int i=1; i<newt; i++) {
		
		dd.add(ExcelDate(i, 2));

	}
	
	Date minDate = Collections.min(dd);
	System.out.println(minDate);
	
	}

	

	public static void ExcelWrite(int row, int column, String webelement) throws Exception {
		File file = new File("C:\\Users\\Rajesh\\Desktop\\data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		sheet.createRow(row).createCell(column).setCellValue(webelement);
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);

	}

	public static void ExcelGetRowCreateCellAndWrite(int row, int column, String input_data) throws Exception {
		File file = new File("C:\\Users\\Rajesh\\Desktop\\data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		sheet.getRow(row).createCell(column).setCellValue(input_data);
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
	}

	public static String ExcelRead(int row, int column) throws Exception {
		File file = new File("C:\\Users\\Rajesh\\Desktop\\data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		DataFormatter dataformatter = new DataFormatter();

		String cell_value = dataformatter.formatCellValue(sheet.getRow(row).getCell(column));
//		String cell_value = sheet.getRow(row).getCell(column).getStringCellValue().trim();
		return cell_value;
	}

	public static int ExcelGetNumberOfRows() throws Exception {
		File file = new File("C:\\Users\\Rajesh\\Desktop\\data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int total_rows = sheet.getLastRowNum();
		return total_rows;
	}

	public static void ExcelGetRowDeleteCell() throws Exception {
		File file = new File("C:\\Users\\Rajesh\\Desktop\\data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		sheet.removeRow(sheet.getRow(1));
		OutputStream out = new FileOutputStream("C:\\Users\\Rajesh\\Desktop\\data.xlsx");
		workbook.write(out);
		out.flush();
		out.close();

	}

	
	public static Date ExcelDate(int row, int column) throws Exception {
		File file = new File("C:\\Users\\Rajesh\\Desktop\\data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		DataFormatter dataformatter = new DataFormatter();

		String cell_value = dataformatter.formatCellValue(sheet.getRow(row).getCell(column));
		 Date date1=new SimpleDateFormat("dd/MM/yyyy hh:mm a").parse(cell_value);  
		
		
		
		return date1;
	}

}
