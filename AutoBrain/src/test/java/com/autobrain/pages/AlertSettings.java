package com.autobrain.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


public class AlertSettings extends Login {
	boolean car_ins_form, car_reg_form;
	String licenceno = "TY78",
			effectivedate = "//div[@class='text-input'][2]/div/div[2]/div/span[contains(text(),'25')]",
			expiredate = "//div[@class='text-input'][3]/div/div[2]/div/span[contains(text(),'28')]",
			insurancecmpy = "Demo", policyno = "55512";

	int i, j;
	ArrayList<String> arr = new ArrayList<String>();

	public void alertSettings() throws Exception {

		login("lib1@mailinator.com", "welcome");
		isDesktopNotificationAlert();

		// Sliding to next page
		if (VisibilityOfElementByXpath("//button[@class='hooper-indicator is-active']", 10).isDisplayed()) {
			List<WebElement> btn = VisibilityOfAllElementsByXpath("//button[@class='hooper-indicator']", 15);
			btn.get(0).click();
		}

		// Click on alert settings button
		VisibilityOfElementByXpath("//li[5]/div[4]/a/div[2]", 15).click();

		// Storing all status
		List<WebElement> toggleBtn = VisibilityOfAllElementsByXpath("//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']", 15);

		// Storing all status ON / OFF name
		List<WebElement> toggleBtnStatus = VisibilityOfAllElementsByXpath(
				"//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']//span", 15);

		System.out.println("Actual result");
		for (i = 0; i < toggleBtn.size(); i++) {

			toggleBtn.get(i).click();

			try {
				car_ins_form = VisibilityOfAllElementsByXpath("//div[contains(text(),'Add Your Car Insurance Info')]",
						2).size() == 1;
				car_ins();

			} catch (Exception e) {
				car_ins_form = false;
			}
			try {
				car_reg_form = VisibilityOfAllElementsByXpath(
						"//div[contains(text(),'Add Your Car Registration Info')]", 2).size() == 1;
				car_regis();
			} catch (Exception e) {
				car_reg_form = false;
			}

			for (j = i; j == i; j++) {
				if (j==9 || j==10) {
					Thread.sleep(1500);
				}
				arr.add(toggleBtnStatus.get(j).getText());

				for (int s = j; s == j; s++) {
					System.out.println(j + "." + " " + arr.get(s));
				}

			}

		}

		System.out.println("----------------------------------------------------");

		// Refreshing the page
		driver.navigate().refresh();

		while(!VisibilityOfElementByXpath("//h4[contains(text(),'Alert Settings')]", 20).isDisplayed()){
			System.out.println("Loading afte refresh!");
		}

		// Validating all status as per last click
		status();

		// Validating Other Modes Settings button
		other_modes_settings_btn();

		softassert.assertAll();
	}

	// ADD CAR INSURANCE INFO METHOD
	public void car_ins() throws Exception {
		// NEW FORM CAR INSURANCE (Click on Edit button)

		// License Plate No
		VisibilityOfElementByXpath("//input[@placeholder='TYP013']", 15).clear();
		VisibilityOfElementByXpath("//input[@placeholder='TYP013']", 15).sendKeys(licenceno);

		// Effective Date (Clicking to open date calendar)
		VisibilityOfElementByXpath("//div[@class='text-input'][2]/div/div[1]/input", 15).click();
		Thread.sleep(1000);
		
		// Selecting date from opened calendar
		VisibilityOfElementByXpath(effectivedate, 15).click();
		Thread.sleep(1000);
		
		// Expiration Date (Clicking to open date calendar)
		VisibilityOfElementByXpath("//div[@class='text-input'][3]/div/div[1]/input", 15).click();

		// Selecting expire date from opened calendar
		VisibilityOfElementByXpath(expiredate, 15).click();

		// Enter insurance company name
		VisibilityOfElementByXpath("//input[@placeholder='Name']", 15).clear();
		VisibilityOfElementByXpath("//input[@placeholder='Name']", 15).sendKeys(insurancecmpy);

		// Enter policy number
		VisibilityOfElementByXpath("//input[@placeholder='X00 0000-000-000']", 15).clear();
		VisibilityOfElementByXpath("//input[@placeholder='X00 0000-000-000']", 15).sendKeys(policyno);

		// Clicking on Save button
		WebElement ele = VisibilityOfElementByXpath("//button[contains(text(),'Save')]", 15);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(2000);
		ele.click();
	}

	// ADD CAR REGISTRATION INFO METHOD
	public void car_regis() throws Exception {
		// NEW FORM CAR REGISTRATION (Click on Edit button)
		// License Plate No
		VisibilityOfElementByXpath("//input[@placeholder='TYP013']", 15).clear();
		VisibilityOfElementByXpath("//input[@placeholder='TYP013']", 15).sendKeys(licenceno);

		// Expiration Date (Clicking to open date calendar)
		VisibilityOfElementByXpath("//input[@placeholder='Pick A Date']", 15).click();
		Thread.sleep(1500);
		
		// Selecting expire date from opened calendar
		VisibilityOfElementByXpath("//div[@class='vdp-datepicker__calendar'][1]/div/span[contains(text(),'25')]", 15)
				.click();

		// Save button
		VisibilityOfElementByXpath("//button[contains(text(),'Save')]", 15).click();
	}

	// VALIDATE ALL STATUS (ON / OFF) METHOD
	public void status() throws Exception {
		String err_msg = "Status not matching before refreshing the page. Status should be same before or after update the page.";
		List<WebElement> exp_Status = VisibilityOfAllElementsByXpath("//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']//span",
				20);
		System.out.println("Expected result");
		for (int f = 0; f < exp_Status.size(); f++) {
			System.out.println(f + "." + " " + exp_Status.get(f).getText());
		}
		System.out.println("----------------------------------------------------");

		// CAR HEALTH ALERTS
		String carHealthAlerts = exp_Status.get(0).getText();
		softassert.assertEquals(arr.get(0), carHealthAlerts, err_msg);
		System.out.println("CAR HEALTH ALERTS" + "-->" + carHealthAlerts + "=" + arr.get(0));

		// LOW FUEL NOTIFICATIONS
		String lowFuelNotifications = exp_Status.get(1).getText();
		softassert.assertEquals(arr.get(1), lowFuelNotifications, err_msg);
		System.out.println("CAR HEALTH ALERTS" + "-->" + lowFuelNotifications + "=" + arr.get(0));

		// LOW BATTERY ALERTS
		String lowBatteryAlerts = exp_Status.get(2).getText();
		softassert.assertEquals(arr.get(2), lowBatteryAlerts, err_msg);
		System.out.println("LOW BATTERY ALERTS" + "-->" + lowBatteryAlerts + "=" + arr.get(1));

		// TOW ALERTS
		String towAlerts = exp_Status.get(3).getText();
		softassert.assertEquals(arr.get(3), towAlerts, err_msg);
		System.out.println("TOW ALERTS" + "-->" + towAlerts + "=" + arr.get(2));

		// CAR "LEFT ON" ALERTS
		String carLeftOnAlerts = exp_Status.get(4).getText();
		softassert.assertEquals(arr.get(4), carLeftOnAlerts, err_msg);
		System.out.println("CAR \"LEFT ON\" ALERTS" + "-->" + carLeftOnAlerts + "=" + arr.get(3));

		// DEVICE DISCONNECTED ALERTS
		String deviceDisconnectedAlerts = exp_Status.get(5).getText();
		softassert.assertEquals(arr.get(5), deviceDisconnectedAlerts, err_msg);
		System.out.println("DEVICE DISCONNECTED ALERTS" + "-->" + deviceDisconnectedAlerts + "=" + arr.get(4));

		// MOTION DETECTION
		String motionDetection = exp_Status.get(6).getText();
		softassert.assertEquals(arr.get(6), motionDetection, err_msg);
		System.out.println("MOTION DETECTION" + "-->" + motionDetection + "=" + arr.get(5));

		// MOBILE PHONE NOTIFICATIONS
		String mobilePhoneNotifications = exp_Status.get(7).getText();
		softassert.assertEquals(arr.get(7), mobilePhoneNotifications, err_msg);
		System.out.println("MOBILE PHONE NOTIFICATIONS" + "-->" + mobilePhoneNotifications + "=" + arr.get(6));

		// INSURANCE RENEWAL (Storing elements for insurance and registration renewal)
		String insuranceRenewal = exp_Status.get(8).getText();
		softassert.assertEquals(arr.get(8), insuranceRenewal, err_msg);
		System.out.println("INSURANCE RENEWAL" + "-->" + insuranceRenewal + "=" + arr.get(7));

		// VEHICLE REGISTATION RENEWAL
		String vehicleRegistrationRenewal = exp_Status.get(9).getText();
		softassert.assertEquals(arr.get(9), vehicleRegistrationRenewal, err_msg);
		System.out.println("VEHICLE REGISTATION RENEWAL" + "-->" + vehicleRegistrationRenewal + "=" + arr.get(8));

		// DESKTOP NOTIFICATIONS
//		String desktopNotifications = exp_Status.get(10).getText();
//		softassert.assertEquals(arr.get(10), desktopNotifications, err_msg);
//		System.out.println("VEHICLE REGISTATION RENEWAL" + "-->" + desktopNotifications + "=" + arr.get(8));

	}

	// ALERT SETTINS- OTHER MODESS SETTINGS
	public void other_modes_settings_btn() {
		// Click on other mode settings button
		VisibilityOfElementByXpath("//button[contains(text(),'Safety Mode Settings')]", 15).click();

		// Storing page status (Opened / Not Opened)
		boolean mode_page = VisibilityOfAllElementsByXpath("//h4[contains(text(),'Mode Settings')]", 15).size() != 0;

		// Validate other modes setting page opened or not
		softassert.assertEquals(mode_page, true);
	}

}
