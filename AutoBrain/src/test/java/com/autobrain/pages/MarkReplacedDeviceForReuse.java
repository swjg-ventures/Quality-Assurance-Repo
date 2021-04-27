package com.autobrain.pages;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import au.com.bytecode.opencsv.CSVWriter;

public class MarkReplacedDeviceForReuse extends Login {
	String excel_file_path = "Files\\Device_replacement.xlsx";
	String csv_file_path = "Files\\Mark_for_reuse_devices.csv";
	ArrayList<String> excel_pending_devices;
	ArrayList<String> no_subscription_found;

	public void markReplacedDeviceForReuse() throws Exception {

		int total_rows = ExcelGetNumberOfRows(excel_file_path);

		excel_pending_devices = new ArrayList<String>();
		for (int i = 1; i <= total_rows; i++) {
			if (!ExcelRead(i, 1, excel_file_path).contains("Marked")) {
				excel_pending_devices.add(ExcelRead(i, 0, excel_file_path));
			}
		}

		System.out.println("Total pending devices in excel sheet are:" + excel_pending_devices.size());

		if (!(excel_pending_devices.size() == 0)) {
			login("john@example.com", "welcome");
			driver.get("https://stg.autobrain.com/worker/device_cancellations/bulk_retail");

			// CSV file to add devices
			File csvFile = new File(csv_file_path);
			Writer file = new FileWriter(csvFile);
			CSVWriter writer = new CSVWriter(file);

			// Create row title
			String[] header = { "device_number" };
			writer.writeNext(header);

			// Adding number of pending devices in csv file
			for (int i = 0; i < excel_pending_devices.size(); i++) {
				String number = excel_pending_devices.get(i);
				String[] num = { number };
				writer.writeNext(num);
			}

			// Close the writer
			writer.close();

			// Click on upload file button
			WebElement upload_file = PresenceOfElementByXpath("//input[@name='file']", 15);
			upload_file.sendKeys(csvFile.getAbsolutePath());
			Thread.sleep(2000);

			// Click on show devices in table
			VisibilityOfElementByXpath("//button[@id='bulk-mark-reuse-btn']", 15).click();

			// Validate Without Jobs Count
			String jobs = VisibilityOfElementByXpath("//button[@id='noJobsFilter']/span", 10).getText();
			int without_jobs = Integer.parseInt(jobs);
			Assert.assertTrue(excel_pending_devices.size() == without_jobs);

			// Select all devices
			VisibilityOfElementByXpath("//input[@id='selectAllDevices']", 10).click();
			Thread.sleep(1500);

			// Click on Mark for Reuse button
			VisibilityOfElementByXpath("//button[@id='mark-reuse-btn']", 10).click();

			// Validate success alert after marked the devices for reuse
			Assert.assertTrue(
					VisibilityOfElementByXpath("//div[@class='ajax-alert alert alert-success']", 10).isDisplayed());

			if(excel_pending_devices.size()>2) {
				Thread.sleep(7000);	
			}
			
			else {
				Thread.sleep(3000);
			}
			// Again click on show devices in table
			VisibilityOfElementByXpath("//button[@id='bulk-mark-reuse-btn']", 15).click();

			Thread.sleep(1000);

			// Validate list of marked devices got disappeared
			List<WebElement> marked_devices = VisibilityOfAllElementsByXpath("//td[14]", 10);

			no_subscription_found = new ArrayList<String>();

			for (int i = 0; i < marked_devices.size(); i++) {
				no_subscription_found.add(marked_devices.get(i).getText());
				Assert.assertTrue(no_subscription_found.get(i).contains("No Subscription Found"));

			}

			for (int i = 1; i <= total_rows; i++) {

				if (excel_pending_devices.contains(ExcelRead(i, 0, excel_file_path))) {
					ExcelGetRowCreateCellAndWrite(i, 1, "Marked On: " + GetCurrentDateTime(), excel_file_path);
				}

			}

		}

	}

}
