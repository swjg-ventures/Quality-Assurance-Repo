package Demo;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class bb {

	
	
public static void main(String[] args) throws Exception {
	excel_reader();
}




public static void excel_reader() throws Exception {
	
	File file = new File("C:\\Users\\Rajesh\\Desktop\\ab.xlsx");
	FileInputStream fis = new FileInputStream(file);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet sh = wb.getSheetAt(0);
	DataFormatter formatter = new DataFormatter();
	

	
	
	
	int a = sh.getLastRowNum();
	

	for(int i=0; i<a; i++) {
	try {		
	System.out.println(formatter.formatCellValue(sh.getRow(i).getCell(1)));	
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Null");
	}
	
	
	
	}
	
	
}


}
	
	
	