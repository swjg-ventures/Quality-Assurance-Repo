package com.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import au.com.bytecode.opencsv.CSVWriter;

public class SignupWithManualInvoice extends Signup {
	boolean error, choose_plan_page;
	String invoice_name, invoice_id_before, email;

	// Used for create device
	ArrayList<String> All_Devices_No = new ArrayList<String>();

	// CSV file for devices
	File csvFile = new File("Files\\mark_bulk_devices_as_sold.csv");

	// Property method
	private Properties property() throws Exception {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src\\main\\java\\Library\\Create_Invoice.properties");
		prop.load(fis);
		return prop;
	}

	// Main Methods
	public void signupWithManualInvoice() throws Exception {
		Writer file = new FileWriter(csvFile);
		CSVWriter writer = new CSVWriter(file);

		// Create record
		String[] header = { "device_number" };
		writer.writeNext(header);

		// Create device
		CreateDevicesByPanel();

		for (int i = 0; i < All_Devices_No.size(); i++) {
			String number = All_Devices_No.get(i);
			String[] num = { number };
			writer.writeNext(num);
		}

		// Close the writer
		writer.close();
		System.out.println("Created devices added successfully in CSV file.");
		CreateInvoice();
		SubmitCsvFile();
		ChooseInvoicePricingPlanAndDistributionChannel();
		Signup();
	}

	// Select file to import
	private void SubmitCsvFile() throws Exception {

		// Redirect to devices page in panel
		driver.get("https://stg.autobrain.com/worker/retail_fulfillment/ready_for_distribution");

		// Click on upload file button
		WebElement upload_file = PresenceOfElementByXpath("//input[@name='file']", 15);

		upload_file.sendKeys(csvFile.getAbsolutePath());

		Thread.sleep(2000);

	}

	// Choose Invoice, Pricing Plan and Distribution Channel (Step 2)
	private void ChooseInvoicePricingPlanAndDistributionChannel() throws Exception {

		// This will select the top invoice means the latest one
		VisibilityOfElementByXpath("//select[@name='invoice']/option[2]", 15).click();

		// Pricing plan
		Select pricing_plan = new Select(VisibilityOfElementByXpath("//select[@name='pricing_plan']", 15));

		// Choose which pricing plan we want to select
		pricing_plan.selectByVisibleText(property().getProperty("pricing_plan_from_mark_device_sold")); 

		// Distribution channel
		Select distribution_channel = new Select(
				VisibilityOfElementByXpath("//select[@name='distribution_channel']", 15));

		// Choose which distribution channel we want to select
		distribution_channel.selectByVisibleText(property().getProperty("distribution_channel_name"));

		// Submit Form
		VisibilityOfElementByXpath("//input[@name='commit']", 15).click();

		// Check error exist
		try {
			error = wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='flash_alert']")))
					.isDisplayed();
		}

		catch (Exception e) {
			System.out.println("Device Added successfully!");
		}

		Assert.assertEquals(error, false, "Device not added!");

		// Logout
		VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]", 15).click();

	}

	
	private void Signup() throws Exception {
		email = GenerateRandomEmail();
		driver.navigate().to(url);

		// Clicking on sign-up button
		VisibilityOfElementByXpath("//a[contains(text(),'SIGN UP')]", 15).click();

		// Entering first-name
		VisibilityOfElementByID("user_first_name", 15).clear();
		VisibilityOfElementByID("user_first_name", 15).sendKeys("Temp");

		// Entering last-name
		VisibilityOfElementByID("user_last_name", 15).clear();
		VisibilityOfElementByID("user_last_name", 15).sendKeys("" + randomInt);

		// Entering email
		VisibilityOfElementByID("user_email", 15).clear();

		System.out.println(email);
		VisibilityOfElementByID("user_email", 15).sendKeys(email);

		// Store entered email in variable
		entered_email = driver.findElement(By.xpath("//input[@placeholder='Email']")).getAttribute("value");

		// Entering phone number
		VisibilityOfElementByID("user_contacts_attributes_0_data", 15).clear();
		VisibilityOfElementByID("user_contacts_attributes_0_data", 15).sendKeys("1234567890");

		// Entering password
		VisibilityOfElementByID("user_password", 15).clear();
		VisibilityOfElementByID("user_password", 15).sendKeys("welcome");

		// Entering confirm password
		VisibilityOfElementByID("user_password_confirmation", 15).clear();
		VisibilityOfElementByID("user_password_confirmation", 15).sendKeys("welcome");

		// Clicking on sign-up button to register
		VisibilityOfElementByXpath("//div[contains(text(),'SIGN UP')]", 15).click();
		Thread.sleep(2500);

		for (int i = 0; i < driver.findElements(By.xpath("//span[@class='help-block']")).size(); i++) {
			boolean email_alert = driver.findElements(By.xpath("//span[contains(text(),'has already been taken')]"))
					.size() != 0;
			boolean firstN_alert = driver
					.findElements(By.xpath("//input[@placeholder='First Name']/following-sibling::span")).size() != 0;
			boolean lastN_alert = driver
					.findElements(By.xpath("//input[@placeholder='Last Name']/following-sibling::span")).size() != 0;

			// First name already exist
			if (firstN_alert == true) {
				VisibilityOfElementByID("user_first_name", 15).clear();
				VisibilityOfElementByID("user_first_name", 15).sendKeys("demouser" + randomInt);
			}

			// Last name already exist
			if (lastN_alert == true) {
				VisibilityOfElementByID("user_last_name", 15).clear();
				VisibilityOfElementByID("user_last_name", 15).sendKeys("Last" + randomInt);
			}

			// Email already exist
			if (email_alert == true) {
				VisibilityOfElementByID("user_email", 15).clear();
				new_entered_email = GenerateRandomEmail();
				System.out.println();
				System.out.println("Email Already exist! New Entered Email-->" + " " + new_entered_email);
				VisibilityOfElementByID("user_email", 15).sendKeys(new_entered_email);

				VisibilityOfElementByID("user_password", 15).sendKeys("welcome");
				VisibilityOfElementByID("user_password_confirmation", 15).sendKeys("welcome");

				// Clicking on sign-up button to register
				VisibilityOfElementByXpath("//div[contains(text(),'SIGN UP')]", 15).click();
			}

		}

		// Validating confirmation key page found
		String expected_next_page = "Resend Confirmation Email";
		try {
			Is_Next_Page_Correct = VisibilityOfElementByXpath("//a[contains(text(),'Resend Confirmation Email')]", 15)
					.getText().contains(expected_next_page);
		} catch (Exception e) {
			Is_Next_Page_Correct = false;
		}

		Assert.assertEquals(Is_Next_Page_Correct, true, "Confirmation key page not found!");

		// Open new tab
		Thread.sleep(2000);
		driver.get("https://www.mailinator.com");

		Thread.sleep(2000);

		// Enter registered email id
		if (new_entered_email == null) {
			VisibilityOfElementByXpath("//input[@placeholder='Enter Public Mailinator Inbox']", 15)
					.sendKeys(entered_email);
			;
			System.out.println("Trying to input entered_email in field " + entered_email);
		} else {
			VisibilityOfElementByXpath("//input[@placeholder='Enter Public Mailinator Inbox']", 15)
					.sendKeys(new_entered_email);
			;
			System.out.println("Trying to input new_entered_email in field " + new_entered_email);
		}

		Thread.sleep(2000);

		// Click on Go button
		VisibilityOfElementByID("go-to-public", 15).click();
		Thread.sleep(2000);

		// Click on the first email
		VisibilityOfElementByXpath("//a[contains(text(),'Autobrain account')]", 15).click();

		// Switch to frame
		driver.switchTo().frame(driver.findElement(By.id("msg_body")));

		// Get confirmation code
		code = VisibilityOfElementByXpath("//td[contains(text(),'To get started')]/strong", 15).getText();

		driver.get(url);

		// Input verification code
		VisibilityOfElementByXpath("//input[@placeholder='Confirmation Code']", 15).sendKeys(code);

		// Click on submit button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
		Thread.sleep(2000);

		if (p_load == false) {
			try {
				p_load = VisibilityOfElementByXpath("//div[contains(text(),'Is Your')]/following-sibling::div//span",
						15).isDisplayed();
			}

			catch (Exception e) {
				System.out.println("Step 1 page not found after confirmation code.");
			}
		}

		// ESF_Exemptions
		if (ESFExemptions()) {
			boolean check_box;
			driver.navigate().refresh();
			Thread.sleep(4000);
			try {
				check_box = wait(driver, 10)
						.until(ExpectedConditions
								.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'Upon canceling')]")))
						.size() == 1;
			}

			catch (Exception e) {
				check_box = false;
			}

			softassert.assertEquals(check_box, false, "ESF Exemptions check-box should not appear!");
			softassert.assertAll();
		}

		Step1(All_Devices_No.get(0));

		Step2();

		Step3();

		Step4();

		Done();

		if (!reg_success) {

			try {
				Thread.sleep(3000);
				List<WebElement> el = PresenceOfAllElementsByXpath(
						"//h4[contains(text(),'Welcome')]/following-sibling::div/button", 15);
				el.get(1).click();
			}

			catch (Exception e) {
				System.out.println("Welcome to autobrain popup not found");
				isDesktopNotificationAlert();
			}

			try {
				reg_success = PresenceOfAllElementsByXpath("//span[contains(text(),'Searching')]", 15).size() == 1;

			}

			catch (Exception e) {
				reg_success = false;
			}

			Assert.assertEquals(reg_success, true, "Unable to find searching after welcome popup!");

		}

		Thread.sleep(2000);

		ActivateNewDevice();
		softassert.assertAll();

	}

	private void ActivateNewDevice() throws Exception {

		for (int i = 1; i < All_Devices_No.size(); i++) {
			// Missing one condition always there should be a 2 devices. In in case of 1
			// device, this function will not work
			StepOneActivateNewDevice(All_Devices_No.get(i));
			Step2();
			Step3();
			Step4();
			Done();

		}

	}

	// Step 1 to activate new device after sign-up
	private void StepOneActivateNewDevice(String device_no) throws Exception {

		// Open navigation menu
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.className("hamburger-container")))
				.click();
		Thread.sleep(2000);

		// Activate new device
		VisibilityOfElementByXpath("//span[contains(text(),'Activate New Device')]", 15).click();
		Thread.sleep(2000);

		// Create a Name for Your Car
		VisibilityOfElementByXpath(
				"//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[1]/input", 15)
						.sendKeys(DeviceName);

		// Enter device number
		VisibilityOfElementByXpath(
				"//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[2]/input", 15)
						.sendKeys(device_no);

		// Re-enter device number
		VisibilityOfElementByXpath(
				"//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[3]/input", 15)
						.sendKeys(device_no);

		// Enter Your VIN
		VisibilityOfElementByXpath(
				"//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[4]/input", 15)
						.sendKeys(VIN);

		// Expanding Car Icon drop-down
		VisibilityOfElementByXpath("//div[@class='select-dropdown']/div", 15).click();
		Thread.sleep(3000);

		// Selecting Car Icon from drop-down
		VisibilityOfElementByXpath(caricon, 15).click();

		// Car Year
		Select y = new Select(VisibilityOfElementByXpath("//div[@class='secondary-input'][1]/div[2]/select", 15));
		y.selectByVisibleText(year);
		Thread.sleep(2000);

		// Car Make
		Select m = new Select(VisibilityOfElementByXpath("//div[@class='secondary-input'][2]/div[2]/select", 15));
		m.selectByVisibleText(make);
		Thread.sleep(2000);

		// Car Model
		Select mo = new Select(VisibilityOfElementByXpath("//div[@class='secondary-input'][3]/div[2]/select", 15));
		mo.selectByIndex(model);
		model_selected = mo.getFirstSelectedOption().getText().trim();
		Thread.sleep(1000);

		// Fill Up Insurance and Registration Forms
//					VehicleProfile v = new VehicleProfile();
//					v.Ins_Reg_Forms(); 

		// Check terms and conditions
		List<WebElement> el = VisibilityOfAllElementsByXpath("//div[@class='esf']/div[1]", 15);
		el.get(0).click();
		Thread.sleep(2000);
		try {
			el.get(1).click();
		}

		catch (Exception e) {
			System.out.println("Excemption not found! Because the check-box is hidden for this customer.");
		}

		Thread.sleep(4000);

		try {
			// Submit
			wait(driver, 4).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Continue')]")))
					.click();

		}

		catch (Exception e) {
			// Submit
			wait(driver, 2).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]")))
					.click();
		}

		// VALIDATE CHOOSE PLAN PAGE
		try {
			choose_plan_page = wait(driver, 10)
					.until(ExpectedConditions
							.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Choose Plan')]")))
					.size() == 1;
		}

		catch (Exception e) {
			choose_plan_page = false;
		}

		Assert.assertEquals(choose_plan_page, true, "Choose plan page not found!");

		// BUSINESS PLAN
		if (prop().get("account_type").equals("Autobrain Business")) {

			// Choose Billing Interval
			WebElement billing_interval = PresenceOfElementByXpath(prop().getProperty("business_plan_interval"), 15);
			billing_interval.click();
			Thread.sleep(2000);

		}

		// FAMILY PLAN
		if (prop().get("account_type").equals("Autobrain Family")) {

			// Choose Plan
			List<WebElement> choose_plan = PresenceOfAllElementsByXpath(
					"//div[contains(text(),'see full list')]/following-sibling::button", 15);
			String num = property().getProperty("choose_plan");
			int number = Integer.parseInt(num);
			Thread.sleep(2000);

			switch (number)

			{
			case 0: // VIP Plan
				VisibilityOfElementByXpath("//div[@class='hooper-pagination']//li[1]/button", 15).click();
				Thread.sleep(1500);
				choose_plan.get(number).click();
				break;

			case 1: // ESSENTIAL Plan
				VisibilityOfElementByXpath("//div[@class='hooper-pagination']//li[2]/button", 15).click();
				Thread.sleep(1500);
				choose_plan.get(number).click();
				break;

			case 2: // MONEY SAVER Plan
				VisibilityOfElementByXpath("//div[@class='hooper-pagination']//li[3]/button", 15).click();
				Thread.sleep(1500);
				choose_plan.get(number).click();
				break;

			}

			// Choose Billing Interval
			WebElement duration = VisibilityOfElementByXpath(property().getProperty("choose_billing_interval"), 15);
			duration.click();

			// Submit
			VisibilityOfElementByXpath("//button[@class='submit-btn HSKg29-lwI4BPWKQPVBps_0']", 15).click();

		}
	}

	// This will just create a invoice from worker panel
	private String CreateInvoice() throws Exception {

		driver.get("https://stg.autobrain.com/worker/retail_fulfillment/new_invoice");

		// Add Quantity
		String s = property().getProperty("quantity");
		int quantity = Integer.parseInt(s);

		for (int i = 0; i < quantity; i++) {
			VisibilityOfElementByXpath("//div[@class='input-group number-picker']/span[2]/button", 15).click();
			Thread.sleep(500);
		}

		// Get invoice auto-generated number
		invoice_id_before = VisibilityOfElementByXpath("//code[@class='name-preview']", 15).getText();
		invoice_id_before = invoice_id_before.replace("#", "");

		// Description
		VisibilityOfElementByID("invoice_description", 15).sendKeys("testing_" + invoice_id_before);

		// Select account type
		Select select = new Select(VisibilityOfElementByID("invoice_account_type", 15));

		select.selectByVisibleText(property().getProperty("create_invoice_account_type"));

		// Submit button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();

		// Validate invoice created
		// Get invoice auto-generated number
		String invoice_id_after = VisibilityOfElementByXpath("//code[@class='name-preview']", 15).getText();

		if (!invoice_id_before.equals(invoice_id_after)) {
			System.out.println("Invoice created successfully!");
		}

		else if (invoice_id_before.equals(invoice_id_after)) {
			Assert.assertEquals(true, false, "Invoice not created successfully!");
		}

		return invoice_name = "testing_" + invoice_id_before;

	}

	// This will create number of devices from worker panel
	private ArrayList<String> CreateDevicesByPanel() throws Exception {
		String device_num = null;

		// LOGIN
		// Entering email
		VisibilityOfElementByID("user_email", 15).clear();
		VisibilityOfElementByID("user_email", 15).sendKeys("john@example.com");

		// Entering password
		VisibilityOfElementByID("user_password", 15).clear();
		VisibilityOfElementByID("user_password", 15).sendKeys("welcome");

		// Click on login button
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();

		// Redirect to devices page in panel
		driver.navigate().to("https://stg.autobrain.com/worker/devices/");

		String total_bought_devices = property().getProperty("quantity");
		int Total_bought_devices = Integer.parseInt(total_bought_devices);

		for (int i = 0; i < Total_bought_devices; i++) {

			// Navigate to add device page
			driver.navigate().to("https://stg.autobrain.com/worker/devices/new");

			// Validating add device page opened or not
			while (!isNewDevicePageFound) {
				isNewDevicePageFound = driver.findElements(By.xpath("//h1[contains(text(),'Add a new device')]"))
						.size() == 1;
			}

			// Enter phone number
			VisibilityOfElementByXpath("//textarea[@placeholder='2125550123,2125554567']", 15)
					.sendKeys(GeneratePhoneNumber());

			// Check-box - Phone number is not from United state or Canada
			VisibilityOfElementByXpath("//input[@id='device_international_phone_number']", 15).click();

			// Enter Cellular service type
			wait(driver, 10)
					.until(ExpectedConditions.visibilityOfElementLocated(By.name("device[cellular_service_type]")))
					.sendKeys("WYLESS");

			// Select Model
			Select sel = new Select(driver.findElement(By.name("device[model]")));
			sel.selectByVisibleText("Standard");

			// Enter Device UID (ESN)
			VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']", 15)
					.sendKeys(GenerateDeviceNumber());
			device_num = VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']", 15)
					.getAttribute("value");
			// Select ESN Format
			Select sel2 = new Select(driver.findElement(By.name("device[uid_format]")));
			sel2.selectByVisibleText("Decimal");

			// Click on create device button
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();

			// VALIDATE ERROR
			while (driver.findElements(By.id("flash_alert")).size() > 0) {
				if (driver.findElements(By.xpath("//div[contains(text(),'Uid has already been taken')]"))
						.size() == 1 == true) {
					VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']", 15).clear();
					VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']", 15)
							.sendKeys(GenerateDeviceNumber());
					device_num = VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']", 15)
							.getAttribute("value");
					// Click on create device button
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
				}

				if (driver.findElements(By.xpath("//div[contains(text(),' Phone number has already been taken')]"))
						.size() == 1 == true) {
					VisibilityOfElementByXpath("//textarea[@placeholder='2125550123,2125554567']", 5).clear();
					VisibilityOfElementByXpath("//textarea[@placeholder='2125550123,2125554567']", 15)
							.sendKeys(GeneratePhoneNumber());

					// Click on create device button
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
				}
			}

			// Adding device one by one
			All_Devices_No.add(device_num);
			System.out.println(device_num);
		}

		return All_Devices_No;

	}

}
