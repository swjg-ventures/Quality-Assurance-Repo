package com.autobrain.pages;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import au.com.bytecode.opencsv.CSVWriter;

public class MarkReplacedDeviceForReuse extends Login {
	String list_of_replaced_devices = "Files\\Device_replacement.xlsx";
	String mark_device_for_reuse = "Files\\Mark_for_reuse_devices.csv";
	ArrayList<String> excel_pending_devices;
	ArrayList<String> job_status;
	

	@Test
	public void markReplacedDeviceForReuse() throws Exception {

		int total_rows = ExcelGetNumberOfRows(list_of_replaced_devices);

		excel_pending_devices = new ArrayList<String>();
		for (int i = 1; i <= total_rows; i++) {
			if (!ExcelRead(i, 1, list_of_replaced_devices).contains("Marked")) {
				excel_pending_devices.add(ExcelRead(i, 0, list_of_replaced_devices));
			}
		}

		System.out.println("Total pending devices in excel sheet are:" + excel_pending_devices.size());

		if (!(excel_pending_devices.size() == 0)) {
			login("john@example.com", "welcome");
			getDriver().get("https://stg.autobrain.com/worker/device_cancellations/bulk_retail");

			// CSV file to add devices
			File csvFile = new File(mark_device_for_reuse);
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

			// Select all devices
			VisibilityOfElementByXpath("//input[@id='selectAllDevices']", 10).click();
			Thread.sleep(1500);

			// Click on Mark for Reuse button
			VisibilityOfElementByXpath("//button[@id='mark-reuse-btn']", 10).click();

			// Validate success alert after marked the devices for reuse
			Assert.assertTrue(
					VisibilityOfElementByXpath("//div[@class='ajax-alert alert alert-success']", 10).isDisplayed());

			// Wait unit the success message disappear
			while (getDriver().findElement(By.xpath("//div[@class='ajax-alert alert alert-success']")).isDisplayed()) {

			}

			// Again click on show devices in table
			VisibilityOfElementByXpath("//button[@id='bulk-mark-reuse-btn']", 15).click();

			Thread.sleep(1000);

			// Validate list of marked devices got disappeared
			List<WebElement> marked_devices = VisibilityOfAllElementsByXpath("//td[@data-jobstatus]", 10);

			job_status = new ArrayList<String>();

			for (int i = 0; i < marked_devices.size(); i++) {
				job_status.add(marked_devices.get(i).getText());
				Assert.assertTrue(job_status.get(i).contains("no job"),
						"Getting error: " + marked_devices.get(i).getText());

			}

			for (int i = 1; i <= total_rows; i++) {

				if (excel_pending_devices.contains(ExcelRead(i, 0, list_of_replaced_devices))) {
					ExcelGetRowCreateCellAndWrite(i, 1, "Marked On: " + GetCurrentDateTime(), list_of_replaced_devices);
				}

			}

		}
		
	}

}
