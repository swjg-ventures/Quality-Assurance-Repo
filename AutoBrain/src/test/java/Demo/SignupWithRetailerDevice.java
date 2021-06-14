package Demo;
//package com.autobrain.pages;
//
//import java.io.FileWriter;
//import java.io.Writer;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.testng.Assert;
//import com.autobrain.base.SignUpBase;
//import com.autobrain.models.SignupModel;
//import au.com.bytecode.opencsv.CSVWriter;
//
//public class SignupWithRetailerDevice extends SignUpBase {
//
//	public SignupWithRetailerDevice(SignupModel signupModel) {
//		super(signupModel);
//	}
//
//	public void signupWithRetailerDevice() throws Exception {
//
//		synchronized (LockObject) {
//			addDevicesInCsvFile();
//
//			CreateInvoice();
//
//			SubmitCsvFile();
//
//			ChooseInvoicePricingPlanAndDistributionChannel();
//		}
//
//		signup();
//
//		EsfExemptionsSetup();
//
//		step1Setup(signupModel.getAll_Devices_No().get(0));
//
//		choosePricingPlanAndAddCardDetails();
//
//		step2Setup();
//
//		step3Setup();
//
//		step4Setup();
//
//		step5FinishSetup();
//
//		Thread.sleep(2500);
//
//		// If more than one device then activate new device
//		if (signupModel.getAll_Devices_No().size() > 1) {
//
//			for (int i = 1; i < signupModel.getAll_Devices_No().size(); i++) {
//				activateNewDevice(signupModel.getAll_Devices_No().get(i));
//			}
//		}
//		softassert.assertAll();
//	}
//
////	public void addDevicesInCsvFile() throws Exception {
////
////		Writer file = new FileWriter(SignupModel.csvFile);
////		CSVWriter writer = new CSVWriter(file);
////
////		// Create record
////		String[] header = { "device_number" };
////		writer.writeNext(header);
////
////		// Create device
////		login.login("john@example.com", "welcome");
////		createDeviceFromPanel();
////
////		for (int i = 0; i < signupModel.getAll_Devices_No().size(); i++) {
////			String number = signupModel.getAll_Devices_No().get(i);
////			String[] num = { number };
////			writer.writeNext(num);
////		}
////
////		// Close the writer
////		writer.close();
////		System.out.println("Device added successfully in CSV file.");
////
////	}
////
////	public void CreateInvoice() throws Exception {
////
////		getDriver().get("https://stg.autobrain.com/worker/retail_fulfillment/new_invoice");
////
////		// Add Quantity
////		for (int i = 0; i < signupModel.getTotal_bought_devices(); i++) {
////			VisibilityOfElementByXpath("//div[@class='input-group number-picker']/span[2]/button", 15).click();
////			Thread.sleep(500);
////		}
////
////		// Get invoice auto-generated number
////		String invoice_id_before;
////		invoice_id_before = VisibilityOfElementByXpath("//code[@class='name-preview']", 15).getText();
////		invoice_id_before = invoice_id_before.replace("#", "");
////
////		// Description
////		VisibilityOfElementByID("invoice_description", 15).sendKeys("testing_" + invoice_id_before);
////
////		// Select account type
////		Select select = new Select(VisibilityOfElementByID("invoice_account_type", 15));
////
////		String invoice_account_type = null;
////		if (signupModel.getAccount_type().contains("personal")) {
////			invoice_account_type = "personal";
////		}
////
////		else if (signupModel.getAccount_type().contains("business")) {
////			invoice_account_type = "business";
////		}
////
////		select.selectByVisibleText(invoice_account_type);
////
////		// Submit button
////		wait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
////
////		// Validate invoice created
////		// Get invoice auto-generated number
////		String invoice_id_after = VisibilityOfElementByXpath("//code[@class='name-preview']", 15).getText();
////
////		if (!invoice_id_before.equals(invoice_id_after)) {
////			System.out.println("Invoice created successfully!");
////		}
////
////		else if (invoice_id_before.equals(invoice_id_after)) {
////			Assert.assertEquals(true, false, "Invoice not created successfully!");
////		}
////
////	}
////
////	public void SubmitCsvFile() throws Exception {
////
////		// Redirect to devices page in panel
////		getDriver().get("https://stg.autobrain.com/worker/retail_fulfillment/ready_for_distribution");
////
////		// Click on upload file button
////		WebElement upload_file = PresenceOfElementByXpath("//input[@name='file']", 15);
////
////		upload_file.sendKeys(SignupModel.csvFile.getAbsolutePath());
////		Thread.sleep(2000);
////	}
////
////	public void ChooseInvoicePricingPlanAndDistributionChannel() throws Exception {
////
////		// This will select the top invoice means the latest one
////		VisibilityOfElementByXpath("//select[@name='invoice']/option[2]", 15).click();
////
////		// Pricing plan
////		Select choose_pricing_plan = new Select(VisibilityOfElementByXpath("//select[@name='pricing_plan']", 15));
////
////		// Choose which pricing plan we want to select
////		choose_pricing_plan.selectByVisibleText(signupModel.getPricing_plan());
////
////		// Distribution channel
////		Select distribution_channel = new Select(
////				VisibilityOfElementByXpath("//select[@name='distribution_channel']", 15));
////
////		// Choose which distribution channel we want to select
////		distribution_channel.selectByVisibleText("amazon");
////
////		// Submit Form
////		VisibilityOfElementByXpath("//input[@name='commit']", 15).click();
////
////		// Check error exist
////		boolean error = true;
////		try {
////			error = wait(getDriver(), 5)
////					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='flash_alert']")))
////					.isDisplayed();
////		}
////
////		catch (Exception e) {
////			error = false;
////			System.out.println("Invoice added successfully for devices!");
////		}
////
////		Assert.assertEquals(error, false, "Device not added!");
////
////		// Logout
////		VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]", 15).click();
////		getDriver().navigate().to(url);
////
////	}
//}
