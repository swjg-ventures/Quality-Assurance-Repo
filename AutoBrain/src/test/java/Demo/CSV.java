package Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.compress.utils.Iterators;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.autobrain.base.Base;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CSV {

	public static void main(String[] args) throws Exception {
		read_csv();
	}	

	
	
	
	
	public static void read_csv() throws Exception {
		
		Reader file = new FileReader("C:\\Users\\Rajesh\\Downloads\\mark.csv");
		CSVReader reader = new CSVReader(file);
		
		String[] nextLine;
	      while ((nextLine = reader.readNext()) != null) {
	          
	           System.out.println(Arrays.toString(nextLine));	        
	       }
		
	      System.out.println();
	}
	
	
	public static void write_csv() throws Exception 
	{
		Writer file = new FileWriter("C:\\Users\\Rajesh\\Downloads\\mark.csv");
		 CSVWriter writer = new CSVWriter(file);
		
		 //Create record
	      String [] header = {"device_number"};
	      String [] device_number = {"Apple", "Grapes", "Banana"};
	            
	      //Write the record to file
	      writer.writeNext(header);   
	      writer.writeNext(device_number);
	      

	      //close the writer
	      writer.close();
	      System.out.println("Done");   
	      
	}
	
	
	
	//Excel function
	public static void add_devices_in_excel() throws Exception 
	{
		File file = new File("C:\\Users\\Rajesh\\Desktop\\ab.xlsx");
		FileInputStream fis = new FileInputStream(file);
			
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);
		DataFormatter df = new DataFormatter();
		String ab =  df.formatCellValue(sh.getRow(0).getCell(0));		
		System.out.println(ab);
		
		sh.getRow(1).getCell(0).setCellValue("new entry");
		FileOutputStream fos = new FileOutputStream(file);
		
		wb.write(fos);
		
		
	}

}