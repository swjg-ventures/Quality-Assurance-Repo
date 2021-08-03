package com.autobrain.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.autobrain.base.Base;
import com.autobrain.base.SignUpBase;
import com.autobrain.models.SignupModel;

public class DeviceReplacement extends Base {
	String excel_file_path = "Files\\Device_replacement.xlsx";
	SignupModel signupModel;
	SignUpBase signUpBase;
	public static boolean is_device_rep=false;

	@Test
	public void deviceReplacement() throws Exception {
		signupModel = new SignupModel();
		signupModel.setTotal_bought_devices(1);
		signupModel.setAccount_type("personal");
		signupModel.setPersonal_plan("moneysaver");
		signupModel.setChoose_personal_billing_interval("monthly");
		signupModel.setPricing_plan("90 days personal plan");
		signupModel.setSet_esf(false);

		signUpBase = new SignUpBase(signupModel);

		// Signup new user
		synchronized (signUpBase.LockObject) {
			signUpBase.addDevicesInCsvFile();

			signUpBase.createInvoice();

			signUpBase.submitCsvFile();

			signUpBase.chooseInvoicePricingPlanAndDistributionChannel();
		}

		signUpBase.signup();

		signUpBase.esfExemptionsSetup();

		signUpBase.step1Setup(signupModel.getAll_Devices_No().get(0));

		signUpBase.choosePricingPlanAndAddCardDetails();

		signUpBase.step2Setup();

		signUpBase.step3Setup();

		signUpBase.step4Setup();

		signUpBase.step5FinishSetup();

		// Plan information
		getDriver().get("https://stg.autobrain.com/cars/plan_information");
		
		// Capture old plan info
		VisibilityOfElementByXpath("//input[@placeholder='Search For Car']", 10).sendKeys(signupModel.getAll_Devices_No().get(0));
		Thread.sleep(1000);
		String old_unit_plan = VisibilityOfElementByXpath("//div[@class='plan-info-container']", 10).getText();
		System.out.println("Old unit plan: "+old_unit_plan);
		
		
		// Logout registered account
		signUpBase.login.logout();

		// Login worker panel for replacement process
		signUpBase.login.login("john@example.com", "welcome");

		// Generate new device number
		signUpBase.createDeviceFromPanel();

		synchronized (signUpBase.LockObject) {
			// Device replacement process
			deviceReplacement(signupModel.getAll_Devices_No().get(0), signupModel.getAll_Devices_No().get(1));
		}
		
		// Plan information
		getDriver().get("https://stg.autobrain.com/cars/plan_information");
		// Capture replaced unit plan information
		VisibilityOfElementByXpath("//input[@placeholder='Search For Car']", 10).sendKeys(signupModel.getAll_Devices_No().get(1));
		Thread.sleep(1000);
		String replaced_unit_plan = VisibilityOfElementByXpath("//div[@class='plan-info-container']", 10).getText();
		System.out.println("Replaced unit plan: "+replaced_unit_plan);
		
		// Validate both old and replaced unit plan
		Assert.assertEquals(replaced_unit_plan, old_unit_plan, "Replaced unit should get old plan!");
	}

	public void deviceReplacement(String old_device_no, String replaced_device_no) throws Exception {

		// Select a device to be replaced
		getDriver().get("https://stg.autobrain.com/worker/device_replacements");

		// Validate device replacement page opened
		Assert.assertTrue(VisibilityOfElementByXpath("//h1[contains(text(),'Select a device to be replaced')]", 15)
				.getText().contains("Select a device to be replaced"));

		// Search registered device
		VisibilityOfElementByXpath("//input[@id='search_query']", 10).sendKeys(old_device_no);
		VisibilityOfElementByXpath("//input[@name='commit']", 10).click();

		// Check device replacement need shipping address
		boolean needShippingAddress;
		try {
			needShippingAddress = VisibilityOfElementByXpath("//td[7]/span", 10).getText()
					.equalsIgnoreCase("Need Shipping Address");
		} catch (Exception e) {
			needShippingAddress = false;
		}

		// Click on edit button to enter shipping address details
		if (needShippingAddress) {
			VisibilityOfElementByXpath("//td[7]/span/following-sibling::a", 15).click();

			// Validate shipping address form opened
			boolean shippingAddressFormDisplayed = VisibilityOfElementByXpath(
					"//h4[contains(text(),'Update shipping address')]", 15).isDisplayed();
			Assert.assertEquals(shippingAddressFormDisplayed, true,
					"Shipping Address Form not displayed after click on edit button!");
			Thread.sleep(1000);
			// Enter first name
			VisibilityOfElementByXpath("//input[@id='shipping_address-detail-first-name']", 15).sendKeys("John");

			// Enter last name
			VisibilityOfElementByXpath("//input[@id='shipping_address-detail-last-name']", 15).sendKeys("example");

			// Enter address
			VisibilityOfElementByXpath("//input[@id='shipping_address-detail-address']", 15)
					.sendKeys("935 Gravier Place, Suite 1160, New Orleans, LA.");

			// Enter city
			VisibilityOfElementByXpath("//input[@id='shipping_address-detail-city']", 15).sendKeys("Boca");

			// Select state
			Select s = new Select(VisibilityOfElementByXpath("//select[@id='state']", 15));
			s.selectByVisibleText("Florida");

			// Enter zip
			VisibilityOfElementByXpath("//input[@id='shipping_address-detail-zip']", 15).sendKeys("70112");

			// Save
			VisibilityOfElementByXpath("//button[contains(text(),'Save changes')]", 10).click();

			// Validate shipping address added successfully
			boolean isShippingAddressAdded;
			try {
				isShippingAddressAdded = VisibilityOfElementByXpath("//div[@id='flash_success']", 15).isDisplayed();

			} catch (Exception e) {
				isShippingAddressAdded = false;
			}

			Assert.assertEquals(isShippingAddressAdded, true, "Shipping Address not added successfully!");
		}

		// Click on Replace Device button
		VisibilityOfElementByXpath("//a[contains(text(),'Replace Device')]", 10).click();

		// Validate confirmation message
		Assert.assertTrue(
				VisibilityOfElementByXpath("//h4[contains(text(),'Are you sure you want to replace this User')]", 10)
						.isDisplayed());

		// Click on yes button
		VisibilityOfElementByXpath("//button[contains(text(),'Yes')]", 10).click();

		// Validate success message
		Assert.assertTrue(VisibilityOfElementByXpath("//div[@class='ajax-alert alert alert-success']", 10).getText()
				.contains("Device replaced"));

		// Select a new device to send to the user as a replacement
		getDriver().get("https://stg.autobrain.com/worker/device_replacements/select_new_device");

		// Validate Select a new device to send to the user as a replacement page opened
		Assert.assertTrue(VisibilityOfElementByXpath(
				"//h1[contains(text(),'Select a new device to replace the old device with')]", 15).isDisplayed());

		// Validate replace device showing
		Assert.assertTrue(VisibilityOfElementByXpath("//td[2][contains(text(),\"" + old_device_no + "\")]", 10)
				.getText().contains(old_device_no));

		// Generate new device for replacement
//		signup.createDeviceFromPanel();

		// Select a new device to send to the user as a replacement
		getDriver().get("https://stg.autobrain.com/worker/device_replacements/select_new_device");

		// Input new device number to replace
		VisibilityOfElementByXpath("//td[contains(text(),\"" + signupModel.getAll_Devices_No().get(0)
				+ "\")]/following-sibling::td[8]//input[@id='device_replacement_new_device_number']", 5)
						.sendKeys(replaced_device_no);

		Thread.sleep(2500);

		// Click on Enter button
		VisibilityOfElementByXpath(
				"//td[2][contains(text(),\"" + old_device_no + "\")]/following-sibling::td[10]//input[@name='commit']",
				10).click();

		// Validate device added
		Assert.assertTrue(VisibilityOfElementByXpath("//div[@id='flash_success']", 10).isDisplayed());

		// Print Shipping Label for Replacement Device
		getDriver().get("https://stg.autobrain.com/worker/device_replacements/print_shipping_label");

		// Click on print shipping label button
		VisibilityOfElementByXpath("//td[contains(text(),\"" + replaced_device_no + "\")]/following-sibling::td[6]/a",
				10).click();

		// Waiting until the message box not opened
		Assert.assertTrue(VisibilityOfElementByXpath("//h4[contains(text(),'Shipping Label')]", 15).isDisplayed(),
				"Print/View shipping label prompt box not opened!");

		// Select Purchase Label
		List<WebElement> select_purchase_label = PresenceOfAllElementsByXpath(
				"//button[contains(text(),'Purchase Label')]", 15);

		Thread.sleep(3000);
		select_purchase_label.get(0).click();
		Thread.sleep(6000);

		// Mark replacement device and return envelope as sent
		getDriver().get("https://stg.autobrain.com/worker/device_replacements/need_envelope");

		// Validate page opened
		Assert.assertTrue(VisibilityOfElementByXpath(
				"//h1[contains(text(),'Mark replacement device and return envelope as sent')]", 10).getText()
						.contains("Mark replacement device and return envelope as sent"));

		// Validate replaced device number shown
		Assert.assertTrue(VisibilityOfElementByXpath("//td[9][contains(text(),\"" + replaced_device_no + "\")]", 10)
				.getText().contains(replaced_device_no));

		// Click on mark as shipped
		VisibilityOfElementByXpath(
				"//td[9][contains(text(),\"" + replaced_device_no + "\")]/following-sibling::td[3]/a", 10).click();

		// Validate success
		Assert.assertTrue(VisibilityOfElementByXpath("//div[@id='flash_success']", 10).isDisplayed());

		// Validate customer receive replacement email
		getDriver().get("http://mailcatch.com");
		Thread.sleep(2000);
		VisibilityOfElementByXpath("//input[@name='box']", 15).sendKeys(signupModel.getOwner_email());

		// Click on go to check new email
		VisibilityOfElementByXpath("//input[@value='Go!']", 15).click();
		Thread.sleep(2000);

		// Open replacement email
		List<WebElement> rep_email = VisibilityOfAllElementsByXpath("//a[contains(text(),'Device Replacement Return')]",
				10);
		rep_email.get(0).click();

		// Switch to frame
		getDriver().switchTo().frame(getDriver().findElement(By.id("emailframe")));

		// Validate email received
		Assert.assertTrue(
				VisibilityOfElementByXpath("//td[contains(text(),'shipped the replacement device you ordered')]", 10)
						.isDisplayed());

		// Logout worker panel
		getDriver().get("https://stg.autobrain.com/worker");
		VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]", 15).click();

		// Login registered user
		getDriver().get("https://stg.autobrain.com/users/sign_in");
		signUpBase.login.login(signupModel.getOwner_email(), "welcome");

		// Using this status to skip tiers while activating replacement unit
		is_device_rep = true;
		signUpBase.activateNewDevice(replaced_device_no);

		Assert.assertTrue(VisibilityOfElementByXpath(
				"//li[@class='hooper-slide column is-active is-current']//div[contains(text(),'CAR FINDER')]", 15)
						.isDisplayed());
		// Expand list of cars
		VisibilityOfElementByXpath("//span[@class='active-car-title']", 5).click();

		if (VisibilityOfElementByXpath("//span[@class='active-car-title']", 5).getText().contains(replaced_device_no)) {
			System.out.println("Replcement device found in active car title");
		}

		else {

			List<WebElement> all_cars = VisibilityOfAllElementsByXpath("//span[@class='car-title']", 5);

			int i = 0;
			while (!all_cars.get(i).getText().equals(replaced_device_no)) {
				i++;
			}
			System.out.println("Replacement unit added successfully in the account!");

		}

		int totalrows = ExcelGetNumberOfRows(excel_file_path);
		int newtotalrows = totalrows + 1;

		try {
			ExcelRead(newtotalrows, 0, excel_file_path).isEmpty();
			System.out.println("Unable to add device number bc cell is not empty!");
		}

		catch (Exception e) {
			ExcelCreateRowCreateCellAndWrite(newtotalrows, 0, old_device_no, excel_file_path);
		}

		try {
			ExcelRead(newtotalrows, 1, excel_file_path).isEmpty();
			System.out.println("Unable to add device status in excel sheet bc cell is not empty!");
		}

		catch (Exception e) {
			ExcelGetRowCreateCellAndWrite(newtotalrows, 1, "Pending", excel_file_path);
			System.out.println("Old device added in the excel sheet.");
		}

	}
}
