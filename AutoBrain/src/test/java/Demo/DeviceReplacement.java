package Demo;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DeviceReplacement extends SignupWithPrepaidDevice {
	String excel_file_path = "Files\\Device_replacement.xlsx";

	public void Device_replacement() throws Exception {
		signupWithPrepaidDevice("personal");
		System.out.println("Signup done. Now requesting for replacement.");

		// Logout registered account
		logout();

		// Login worker panel for replacement process
		login("john@example.com", "welcome");

		// Select a device to be replaced
		driver.get("https://stg.autobrain.com/worker/device_replacements");

		// Validate device replacement page opened
		Assert.assertTrue(VisibilityOfElementByXpath("//h1[contains(text(),'Select a device to be replaced')]", 15)
				.getText().contains("Select a device to be replaced"));

		// Search registered device
		VisibilityOfElementByXpath("//input[@id='search_query']", 10).sendKeys(All_Devices_No.get(0));
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
		driver.get("https://stg.autobrain.com/worker/device_replacements/select_new_device");

		// Validate Select a new device to send to the user as a replacement page opened
		Assert.assertTrue(VisibilityOfElementByXpath(
				"//h1[contains(text(),'Select a new device to replace the old device with')]", 15).isDisplayed());

		// Validate replace device showing
		Assert.assertTrue(VisibilityOfElementByXpath("//td[2][contains(text(),\"" + All_Devices_No.get(0) + "\")]", 10)
				.getText().contains(All_Devices_No.get(0)));

		// Generate new device for replacement
		CreateDevicesByPanel();

		// Select a new device to send to the user as a replacement
		driver.get("https://stg.autobrain.com/worker/device_replacements/select_new_device");

		// Input new device number to replace
		VisibilityOfElementByXpath("//td[contains(text(),\"" + All_Devices_No.get(0)
				+ "\")]/following-sibling::td[8]//input[@id='device_replacement_new_device_number']", 5)
						.sendKeys(All_Devices_No.get(1));

		Thread.sleep(2500);

		// Click on Enter button
		VisibilityOfElementByXpath("//td[2][contains(text(),\"" + All_Devices_No.get(0)
				+ "\")]/following-sibling::td[10]//input[@name='commit']", 10).click();

		// Validate device added
		Assert.assertTrue(VisibilityOfElementByXpath("//div[@id='flash_success']", 10).isDisplayed());

		// Print Shipping Label for Replacement Device
		driver.get("https://stg.autobrain.com/worker/device_replacements/print_shipping_label");

		// Click on print shipping label button
		VisibilityOfElementByXpath(
				"//td[contains(text(),\"" + All_Devices_No.get(1) + "\")]/following-sibling::td[6]/a", 10).click();

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
		driver.get("https://stg.autobrain.com/worker/device_replacements/need_envelope");

		// Validate page opened
		Assert.assertTrue(VisibilityOfElementByXpath(
				"//h1[contains(text(),'Mark replacement device and return envelope as sent')]", 10).getText()
						.contains("Mark replacement device and return envelope as sent"));

		// Validate replaced device number shown
		Assert.assertTrue(VisibilityOfElementByXpath("//td[9][contains(text(),\"" + All_Devices_No.get(1) + "\")]", 10)
				.getText().contains(All_Devices_No.get(1)));

		// Click on mark as shipped
		VisibilityOfElementByXpath(
				"//td[9][contains(text(),\"" + All_Devices_No.get(1) + "\")]/following-sibling::td[3]/a", 10).click();

		// Validate success
		Assert.assertTrue(VisibilityOfElementByXpath("//div[@id='flash_success']", 10).isDisplayed());

		// Validate customer receive replacement email
		driver.get("http://www.yopmail.com/en/");
		Thread.sleep(2000);
		VisibilityOfElementByXpath("//input[@id='login']", 15).sendKeys(registered_email);

		// Click on check for new email button
		VisibilityOfElementByXpath("//input[@class='sbut']", 15).click();
		Thread.sleep(2000);

		// Switch to frame
		driver.switchTo().frame("ifmail");

		// Validate email received
		Assert.assertTrue(
				VisibilityOfElementByXpath("//td[contains(text(),'shipped the replacement device you ordered')]", 10)
						.isDisplayed());

		// Logout worker panel
		driver.get("https://stg.autobrain.com/worker");
		VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]", 15).click();

		// Login registered user
		driver.get("https://stg.autobrain.com/users/sign_in");
		login(registered_email, "welcome");

		// Activating replaced device
		StepOneActivateNewDevice(All_Devices_No.get(1));

		Assert.assertTrue(VisibilityOfElementByXpath(
				"//li[@class='hooper-slide column is-active is-current']//div[contains(text(),'CAR FINDER')]", 15)
						.isDisplayed());
		// Expand list of cars
		VisibilityOfElementByXpath("//span[@class='active-car-title']", 5).click();

		if (VisibilityOfElementByXpath("//span[@class='active-car-title']", 5).getText()
				.contains(All_Devices_No.get(1))) {
			System.out.println("Replcement device found in active car title");
		}

		else {

			List<WebElement> all_cars = VisibilityOfAllElementsByXpath("//span[@class='car-title']", 5);

			int i = 0;
			while (!all_cars.get(i).getText().equals(All_Devices_No.get(1))) {
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
			ExcelCreateRowCreateCellAndWrite(newtotalrows, 0, All_Devices_No.get(0), excel_file_path);
		}

		try {
			ExcelRead(newtotalrows, 1, excel_file_path).isEmpty();
			System.out.println("Unable to add device status in excel sheet bc cell is not empty!");
		}

		catch (Exception e) {
			ExcelGetRowCreateCellAndWrite(newtotalrows, 1, "Pending", excel_file_path);
			System.out.println("Defective device added in the excel sheet.");
		}

	}
}
