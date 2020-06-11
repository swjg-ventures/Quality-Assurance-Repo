package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CancelSubscription extends Login {

	public void cancelSubscription() throws Exception {
		login("john@example.com", "welcome");

		driver.navigate().to("https://stg.autobrain.com/worker");

		// Validate worker panel page loaded
		boolean isWorkerPanelPageLoaded = VisibilityOfElementByXpath("//strong[contains(text(),'Customer Service')]",
				30).isDisplayed();
		Assert.assertEquals(isWorkerPanelPageLoaded, true, "Worker panel page is not loaded");

		// Click on Select a device to cancel tile
		VisibilityOfElementByXpath("//div[contains(text(),'Select a device to cancel')]", 20).click();

		// Validate worker panel page loaded
		boolean isCanceADevicePageOpened = VisibilityOfElementByXpath("//h1[contains(text(),'Cancel a device')]", 30)
				.isDisplayed();
		Assert.assertEquals(isCanceADevicePageOpened, true, "Cancel A Device page not opened!");

		// Check device cancellation needs shipping address
		boolean needShippingAddress;
		try {
			needShippingAddress = VisibilityOfElementByXpath(
					"//table[@class='table table-hover']/tbody/tr[1]/td[7]/span", 10).getText()
							.equalsIgnoreCase("Need Shipping Address");
		} catch (Exception e) {
			needShippingAddress = false;
		}

		// Click on edit button to enter shipping address details
		if (needShippingAddress) {
			VisibilityOfElementByXpath(
					"//table[@class='table table-hover']/tbody/tr[1]/td[7]/span/following-sibling::button", 15).click();

			// Validate shipping address form opened
			boolean shippingAddressFormDisplayed = VisibilityOfElementByXpath(
					"//h4[contains(text(),'Edit shipping address')]", 15).isDisplayed();
			Assert.assertEquals(shippingAddressFormDisplayed, true,
					"Shipping Address Form not displayed after click on edit button!");

			// Enter first name
			VisibilityOfElementByXpath("//form[@id='shipping_form']//input[@id='first_name']", 15).sendKeys("John");

			// Enter last name
			VisibilityOfElementByXpath("//form[@id='shipping_form']//input[@id='last_name']", 15).sendKeys("example");

			// Enter address
			VisibilityOfElementByXpath("//form[@id='shipping_form']//input[@id='street_address']", 15)
					.sendKeys("935 Gravier Place, Suite 1160, New Orleans, LA.");

			// Enter city
			VisibilityOfElementByXpath("//form[@id='shipping_form']//input[@id='city']", 15).sendKeys("Boca");

			// Select state
			Select s = new Select(VisibilityOfElementByXpath("//form[@id='shipping_form']//select[@id='state']", 15));
			s.selectByVisibleText("Florida");

			// Enter zip
			VisibilityOfElementByXpath("//form[@id='shipping_form']//input[@id='zip']", 15).sendKeys("70112");

			// Save
			List<WebElement> saveBtn = VisibilityOfAllElementsByXpath("//div[@id='editShipping']//button", 15);
			saveBtn.get(1).click();

			// Validate shipping address added successfully
			boolean isShippingAddressAdded;
			try {
				isShippingAddressAdded = VisibilityOfElementByXpath("//div[@id='flash_success']", 15).isDisplayed();

			} catch (Exception e) {
				isShippingAddressAdded = false;
			}

			Assert.assertEquals(isShippingAddressAdded, true, "Shipping Address not added successfully!");
		}

		// Click on option #1 to cancel subscription
		VisibilityOfElementByXpath("//table[@class='table table-hover']/tbody/tr[1]/td[16]/form", 15).click();

		// Check confirmation message
		boolean isConfirmationMsg = VisibilityOfElementByXpath("//h4[contains(text(),'Are you sure?')]", 15)
				.isDisplayed();
		Assert.assertEquals(isConfirmationMsg, true,
				"Confirmation message not found before cacelling device subscription!");

		// Click Yes to cancel device subscription
		VisibilityOfElementByXpath("//button[contains(text(),'Yes')]", 20).click();

		// Store message
		String msg = VisibilityOfElementByXpath("//div[contains(text(),'Success!  Canceled device')]", 30).getText();

		// Store cancelled device number
		String cancelledDevice = msg.replace("Success! Canceled device ", "");

		// Validate subscription cancelled
		Assert.assertEquals(msg.contains("Success!"), true,
				"Success message not found after cancel device subscription!");

		System.out.println(cancelledDevice + " Device subscription cancelled successfully!");

		// PROCESS TO UNCANCEL THE CANCELLED DEVICE
		driver.navigate().to("https://stg.autobrain.com/worker/device_cancellations/canceled_devices");

		// Enter cancelled device number in search
		VisibilityOfElementByXpath("//input[@id='query']", 15).sendKeys(cancelledDevice);

		// Click on search button
		VisibilityOfElementByXpath("//input[@name='commit']", 15).click();

		// Searched device number
		String searchedDevice = VisibilityOfElementByXpath(
				"//table[@class='table table-hover table-bordered tablesorter tablesorter-default']/tbody/tr[1]/td[5]",
				30).getText();

		// Validate cancelled device and searched device to uncancel needs to be same
		Assert.assertEquals(searchedDevice, cancelledDevice,
				"Cancelled device and Searched device to cancel should be same before uncancellation!");

		// Click on uncancel button
		VisibilityOfElementByXpath(
				"//table[@class='table table-hover table-bordered tablesorter tablesorter-default']/tbody/tr[1]/td[14]",
				15).click();

		// Validate device cancelled
		boolean isDeviceUncancelled = VisibilityOfElementByXpath("//div[@id='flash_success']", 30).isDisplayed();
		Assert.assertEquals(isDeviceUncancelled, true, "Unable to uncancelled the device!");
		System.out.println(cancelledDevice + " Device subscription uncancelled successfully!");
	}

}
