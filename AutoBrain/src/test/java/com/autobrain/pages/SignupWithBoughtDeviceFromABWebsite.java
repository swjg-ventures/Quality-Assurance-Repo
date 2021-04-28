package com.autobrain.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.autobrain.base.Base;
import com.autobrain.models.SignupModel;

public class SignupWithBoughtDeviceFromABWebsite extends Base {
	com.autobrain.models.SignupModel ref;
	com.autobrain.pages.Login login;

	public SignupWithBoughtDeviceFromABWebsite() {
		// Initializing
		login = new Login();
		ref = PageFactory.initElements(driver, SignupModel.class);
		SignupModel.setAll_Devices_No(new ArrayList<String>());
	}

	// MAIN METHOD
	public void signupWithBoughtDeviceFromABWebsite(int no_of_devices, String account_type, String set_plan,
			String set_billing_interval, boolean set_esf) throws Exception {

		// Set-up
		SignupModel.setTotal_bought_devices(no_of_devices);
		SignupModel.setAccount_type(account_type);
		SignupModel.setPersonal_plan(set_plan);
		SignupModel.setChoose_personal_billing_interval(set_billing_interval);
		SignupModel.setChoose_business_billing_interval(set_billing_interval);
		SignupModel.setSet_esf(set_esf);

		orderDeviceFromWebSite();

		signup();

		EsfExemptionsSetup();

		step1Setup(SignupModel.getAll_Devices_No().get(0));

		choosePricingPlanAndAddCardDetails();

		step2Setup();

		step3Setup();

		step4Setup();

		step5FinishSetup();

		Thread.sleep(2500);

		// If more than one device then activate new device
		if (SignupModel.getAll_Devices_No().size() > 1) {

			for (int i = 1; i < SignupModel.getAll_Devices_No().size(); i++) {
				activateNewDevice(SignupModel.getAll_Devices_No().get(i));
			}
		}
		softassert.assertAll();

	}

	public void signup() throws Exception {

		// Check if the sign-up email is empty
		if ((SignupModel.getOwner_email() == null)) {
			SignupModel.setOwner_email(GenerateRandomEmail());
		}

		// Clicking on sign-up button
		VisibilityOfElementByXpath("//a[contains(text(),'SIGN UP')]", 15).click();

		// Entering first-name
		VisibilityOfElementByID("user_first_name", 15).clear();
		VisibilityOfElementByID("user_first_name", 15).sendKeys("Test");

		// Entering last-name
		VisibilityOfElementByID("user_last_name", 15).clear();
		VisibilityOfElementByID("user_last_name", 15).sendKeys("" + SignupModel.getRandom_int());

		// Entering email
		VisibilityOfElementByID("user_email", 15).clear();

		// Sign-up email
		VisibilityOfElementByID("user_email", 15).sendKeys(SignupModel.getOwner_email());

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

		Thread.sleep(3500);
		for (int i = 0; i < driver.findElements(By.xpath("//span[@class='help-block']")).size(); i++) {
			boolean email_alert = driver.findElements(By.xpath("//span[contains(text(),'has already been taken')]"))
					.size() != 0;
			boolean firstN_alert = driver
					.findElements(By.xpath("//input[@placeholder='First Name']/following-sibling::span")).size() != 0;
			boolean lastN_alert = driver
					.findElements(By.xpath("//input[@placeholder='Last Name']/following-sibling::span")).size() != 0;

			// First name already exist
			if (firstN_alert) {
				VisibilityOfElementByID("user_first_name", 15).clear();
				VisibilityOfElementByID("user_first_name", 15).sendKeys("demouser" + SignupModel.getRandom_int());
			}

			// Last name already exist
			if (lastN_alert) {
				VisibilityOfElementByID("user_last_name", 15).clear();
				VisibilityOfElementByID("user_last_name", 15).sendKeys("Last" + SignupModel.getRandom_int());
			}

			// Email already exist
			if (email_alert) {

				VisibilityOfElementByID("user_email", 15).clear();
				SignupModel.setOwner_email(GenerateRandomEmail());
				System.out.println("Email Already exist! New Entered Email-->" + " " + SignupModel.getOwner_email());
				VisibilityOfElementByID("user_email", 15).sendKeys(SignupModel.getOwner_email());

				VisibilityOfElementByID("user_password", 15).sendKeys("welcome");
				VisibilityOfElementByID("user_password_confirmation", 15).sendKeys("welcome");

				// Clicking on sign-up button to register
				VisibilityOfElementByXpath("//div[contains(text(),'SIGN UP')]", 15).click();
			}

		}

		if (SignupModel.getTotal_bought_devices() == 1 || SignupModel.isConfirmation_email()) {

			// Validating confirmation key page found
			Assert.assertTrue(VisibilityOfElementByXpath("//a[contains(text(),'Resend Confirmation Email')]", 15)
					.getText().contains("Resend Confirmation Email"), "Confirmation key page not found!");

			// YOPMAIL.COM
			Thread.sleep(2000);
			driver.get("http://www.yopmail.com/en/");
			Thread.sleep(2000);

			// Input email
			VisibilityOfElementByXpath("//input[@id='login']", 15).sendKeys(SignupModel.getOwner_email());
			System.out.println("Owner email: " + SignupModel.getOwner_email());

			// Click on check for new email button
			VisibilityOfElementByXpath("//input[@class='sbut']", 15).click();
			Thread.sleep(1000);

			// Switch to frame
			driver.switchTo().frame(driver.findElement(By.id("ifinbox")));

			// Open confirmation email
			VisibilityOfElementByXpath("//span[contains(text(),'Autobrain account confirmation email')]", 10).click();
			Thread.sleep(1000);

			// Back to parent frame
			driver.switchTo().parentFrame();

			// Switch to frame
			driver.switchTo().frame(driver.findElement(By.id("ifmail")));

			// Get confirmation code
			String confirmation_code = VisibilityOfElementByXpath("//td[contains(text(),'To get started')]/strong", 15)
					.getText();

			driver.get(url);

			// Input verification code
			VisibilityOfElementByXpath("//input[@placeholder='Confirmation Code']", 15).sendKeys(confirmation_code);

			// Click on submit button
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
			Thread.sleep(2000);
		}

		// Validate next page Step 1
		Assert.assertTrue(
				VisibilityOfElementByXpath("//div[contains(text(),'Is Your')]/following-sibling::div//span", 15)
						.isDisplayed());

	}

	private void orderDeviceFromWebSite() throws Exception {

		String account_type = null;
		if (SignupModel.getAccount_type().equalsIgnoreCase("personal")) {
			account_type = "Autobrain Family";
		}

		if (SignupModel.getAccount_type().equalsIgnoreCase("business")) {
			account_type = "Autobrain Business";

		}
		// Generating random email
		SignupModel.setOwner_email(GenerateRandomEmail());

		driver.get("https://stg.autobrain.com/buy");

		// Open drop-down to select number of devices
		Select s = new Select(VisibilityOfElementByXpath("//div[@class='quanities-container']/select", 15));
		s.selectByVisibleText(String.valueOf(SignupModel.getTotal_bought_devices()));

		// Select Account Type BUSINESS OR PERSONAL
		Select select = new Select(VisibilityOfElementByXpath("//div[@class='account-types-container']/select", 15));
		select.selectByVisibleText(account_type);

		Thread.sleep(2000);
		// Store expected product price
		String expected_product_price = VisibilityOfElementByXpath("//span[@class='price-cost']", 15).getText();

		// Click on add to cart button
		VisibilityOfElementByXpath("//button[contains(text(),'Add To Cart')]", 15).click();

		// Click on continue to shopping info button
		VisibilityOfElementByXpath("//button[contains(text(),'Continue to Shipping Info')]", 15).click();

		// (SHIPPING INFO) Verify new page opened or not
		boolean nextpage = VisibilityOfAllElementsByXpath("//h5[contains(text(),'Shipping  Information')]", 15)
				.size() == 1;
		softassert.assertEquals(nextpage, true);

		// Enter first name
		VisibilityOfElementByXpath("//input[@placeholder='First Name']", 15).sendKeys(SignupModel.getF_name());

		// Enter last name
		VisibilityOfElementByXpath("//input[@placeholder='Last Name']", 15).sendKeys(SignupModel.getL_name());

		// Enter email address
		VisibilityOfElementByXpath("//input[@placeholder='Email Address']", 15).sendKeys(SignupModel.getOwner_email());
		System.out.println("Entered Email During Bought The Product " + "--> " + SignupModel.getOwner_email());

		// Enter street address
		VisibilityOfElementByXpath("//input[@placeholder='Street Address']", 15).sendKeys(SignupModel.getStreet());

		// Enter city
		VisibilityOfElementByXpath("//input[@placeholder='City']", 15).sendKeys(SignupModel.getCity());

		// Select state
		Select state = new Select(VisibilityOfElementByXpath("//select[@placeholder='State']", 15));
		state.selectByVisibleText(SignupModel.getState());

		// Enter zip code
		VisibilityOfElementByXpath("//input[@placeholder='Zip Code']", 15).sendKeys(SignupModel.getZip());

		// Click on continue to billing info button
		VisibilityOfElementByXpath("//button[contains(text(),'Continue to Billing')]", 15).click();

		// BILLING ADDRESS (Verify new page opened or not)
		boolean nextpage2 = VisibilityOfAllElementsByXpath("//h5[contains(text(),'Billing  Address')]", 15).size() == 1;
		Assert.assertEquals(nextpage2, true);

		// Click on Same as shipping check-box
		VisibilityOfElementByXpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']", 15).click();

		// Get street address and verify
		String A_address = VisibilityOfElementByXpath("//input[@placeholder='Street Address']", 15)
				.getAttribute("value");
		Assert.assertEquals(A_address, SignupModel.getStreet());

		// Get city name and verify
		String A_City = VisibilityOfElementByXpath("//input[@placeholder='City']", 15).getAttribute("value");
		Assert.assertEquals(A_City, SignupModel.getCity());

		// Get state name and verify
		String A_state = VisibilityOfElementByXpath("//Select[@placeholder='State']/option[13]", 15).getText().trim();
		Assert.assertEquals(A_state, SignupModel.getState());

		// Get city name and verify
		VisibilityOfElementByXpath("//input[@placeholder='Phone Number']", 15).sendKeys(SignupModel.getPhone());

		// Click on continue to card button
		VisibilityOfElementByXpath("//button[contains(text(),'Continue to Card Info')]", 15).click();

		// PAYMENT METHOD (Verify new page opened or not)
		boolean nextpage3 = VisibilityOfAllElementsByXpath("//h5[contains(text(),'Payment  Method')]", 15).size() == 1;
		Assert.assertEquals(nextpage3, true);

		// Enter card name
		VisibilityOfElementByXpath("//input[@placeholder='Name On Card']", 15).sendKeys(SignupModel.getCard_name());

		// Enter card number
		VisibilityOfElementByXpath("//input[@placeholder='Card Number']", 15).sendKeys(SignupModel.getCard_no());

		// Enter card CVV
		VisibilityOfElementByXpath("//input[@placeholder='CVV']", 15).sendKeys(SignupModel.getCard_cvv());

		// Select card month
		Select month = new Select(VisibilityOfElementByXpath("//select[@placeholder='Month']", 15));
		month.selectByVisibleText("12");

		// Select card year
		Select year = new Select(VisibilityOfElementByXpath("//select[@placeholder='Year']", 15));
		year.selectByVisibleText("2035");

		// Enter zip code
		boolean isZipCodeFound = VisibilityOfElementByXpath("//input[@placeholder='Zip Code']", 15)
				.getAttribute("value").equalsIgnoreCase(SignupModel.getZip());
		Assert.assertEquals(isZipCodeFound, true, "Zip code not fetch automatically!");

		// Click on submit my order button
		VisibilityOfElementByXpath("//button[contains(text(),'Submit My Order')]", 15).click();

		Thread.sleep(2000);

		// Verify order placed or not
		Assert.assertTrue(
				VisibilityOfAllElementsByXpath("//h2[contains(text(),'Thank You For Your Order')]", 30).size() == 1);

		// Verify order quantity
		Assert.assertTrue(
				VisibilityOfAllElementsByXpath(
						"//p[contains(text(),'Your package will include " + SignupModel.getTotal_bought_devices()
								+ " Autobrain device as well as a 5 step quick start guide.')]",
						15).size() == 1,
				"Ordered quantity not matching with order receipt!");

		// Verify actual product price according to selected quantity
		String actual_product_price = VisibilityOfElementByXpath(
				"//div[contains(text(),'Total:')]/following-sibling::div", 15).getText();

		Assert.assertEquals(actual_product_price, expected_product_price,
				"Expected product price not matching with actual product price!");

		// Closing current window
		// driver.switchTo().window(tabs.get(1)).close();
		// Thread.sleep(1000);

		// Switching to main window
		// driver.switchTo().window(tabs.get(0));
		Thread.sleep(1000);
		orderToShip();
	}

	public void orderToShip() throws Exception {
		driver.navigate().to(url);
		// Calling login method
		login.login("john@example.com", "welcome");

		// Navigate to devices page
		driver.navigate().to("https://stg.autobrain.com/worker/devices");

		// Store user buy device ID
		String user_buy_device = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();

		// Create device number for customer
		createDeviceFromPanel();

		// Switch to Orders To Ship page
		driver.navigate().to("https://stg.autobrain.com/worker/online_fulfillment/invoices_to_ship");

		// Click on Add Device button to add the manually created device
		ref.add_device_btn.get(0).click();

		// Validate enter field box opened
		boolean msg_prompt = PresenceOfAllElementsByXpath(
				"//h4[contains(text(),'Add a device number to the following cars')]", 15).size() != 0;

		Assert.assertEquals(msg_prompt, true, "Message alert not prompted!");

		// Enter Device number
		Thread.sleep(2500);

		for (int i = 0; i < ref.no_of_input_devices.size(); i++) {
			if (SignupModel.getBluetooth_is().equals("upgraded_device")) {
				TypeInFieldSlowly(ref.no_of_input_devices.get(i), SignupModel.getAll_Devices_No().get(1));
			}

			else {
				TypeInFieldSlowly(ref.no_of_input_devices.get(i), SignupModel.getAll_Devices_No().get(i));
			}
			Thread.sleep(3000);

			// Check if the error exist after enter custom created device number
			while (driver.findElements(By.xpath("//div[@class='form-group form-inline col-xs-12 has-error']"))
					.size() > 0) {
				// Closed opened message box
				List<WebElement> close_btn = driver.findElements(By.xpath("//div[@class='modal fade in']//button[1]"));
				close_btn.get(1).click();
				Thread.sleep(1000);

				// Refresh the page
				driver.navigate().refresh();

				// Time to reload the complete page
				while (driver.findElements(By.xpath("//h3[contains(text(),'Orders to ship')]")).size() == 0) {
					System.out.println("Loading...Orders to ship page!");
				}

				// Again open the message box
				ref.add_device_btn.get(0).click();
				Thread.sleep(1500);

				// Enter the custom created device number
				// Reset i to 0 and start entering the device ID from first index
				i = 0;
				if (SignupModel.getBluetooth_is().equals("upgraded_device")) {
					TypeInFieldSlowly(ref.no_of_input_devices.get(i), SignupModel.getAll_Devices_No().get(1));
				}

				else {
					TypeInFieldSlowly(ref.no_of_input_devices.get(i), SignupModel.getAll_Devices_No().get(i));
				}
				Thread.sleep(3000);

			}

		}

		// Click on Save button
		List<WebElement> btn = driver.findElements(By.xpath("//button[contains(text(),'Save changes')]"));
		btn.get(0).click();

		// Validate device added
		boolean device_added;
		try {
			device_added = VisibilityOfAllElementsByXpath("//tbody/tr[1]//td[8]/a[contains(text(),'Device #')]", 15)
					.size() == 1;
		} catch (Exception e) {
			device_added = false;
			e.getStackTrace();
		}

		Assert.assertEquals(device_added, true, "Device not added!");

		Thread.sleep(1500);

		// Click on Print/View shipping label
		VisibilityOfElementByXpath("//tbody/tr[1]//td[9]/a", 15).click();

		// Waiting until the message box not opened
		boolean shipping_label_box;
		try {
			shipping_label_box = VisibilityOfElementByXpath("//h4[contains(text(),'Shipping Label')]", 15)
					.isDisplayed();
		} catch (Exception e) {
			shipping_label_box = false;
			e.printStackTrace();
		}

		Assert.assertEquals(shipping_label_box, true, "Print/View shipping label prompt box not opened!");

		// Select Purchase Label
		List<WebElement> select_purchase_label = PresenceOfAllElementsByXpath(
				"//button[contains(text(),'Purchase Label')]", 15);

		Thread.sleep(4000);
		select_purchase_label.get(1).click();
		Thread.sleep(4500);

		// Click on Mark As Shipped
		List<WebElement> mark_as_shipped_btn = VisibilityOfAllElementsByXpath("//a[contains(text(),'Mark as shipped')]",
				10);
		Thread.sleep(4000);
		mark_as_shipped_btn.get(0).click();

		// Refresh the page
		driver.navigate().refresh();

		Thread.sleep(2500);

		// After marked as shipped device should disappear from the list
		boolean user_disappear_from_list = driver
				.findElements(By.xpath("//a[contains(text(),'Device #" + "Device_Num" + " added')]")).size() == 0;
		Assert.assertEquals(user_disappear_from_list, true,
				"User still exist in the list which should not after marked the device as shipped!");

		// Navigate to devices page
		driver.navigate().to("https://stg.autobrain.com/worker/devices");

		String device_no = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();

		boolean user_device_updated = user_buy_device != device_no;
		Assert.assertEquals(user_device_updated, true, "User bought device not updated!");

		VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]", 15).click();
		Thread.sleep(2000);
		driver.navigate().to("https://stg.autobrain.com");
	}

	// Step 1 will add all car info
	public void step1Setup(String device_no) throws Exception {
		// Create a Name for Your Car
		VisibilityOfElementByXpath(
				"//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[1]/input", 15)
						.sendKeys(SignupModel.getAll_Devices_No().get(0));

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
						.sendKeys(SignupModel.getVIN());

		// Expanding Car Icon drop-down
		VisibilityOfElementByXpath("//div[@class='select-dropdown']/div", 15).click();
		Thread.sleep(3000);

		// Selecting Car Icon from drop-down
		VisibilityOfElementByXpath(SignupModel.getCaricon(), 15).click();

		// Car Year
		Select y = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][1]/div[2]/select")));
		y.selectByVisibleText(SignupModel.getYear());
		Thread.sleep(2000);

		// Car Make
		Select m = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][2]/div[2]/select")));
		m.selectByVisibleText(SignupModel.getMake());
		Thread.sleep(2000);

		// Car Model
		Select mo = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][3]/div[2]/select")));
		mo.selectByIndex(SignupModel.getModel());
		Thread.sleep(1000);

		// Fill Up Insurance and Registration Forms
		// VehicleProfile v = new VehicleProfile();
		// v.Ins_Reg_Forms();

		// Check off terms and conditions
		List<WebElement> terms_conditions_btn = VisibilityOfAllElementsByXpath("//div[@class='esf']/div[1]", 15);
		terms_conditions_btn.get(0).click();
		Thread.sleep(2000);

		if (!(SignupModel.getBluetooth_is().contains("free"))) {
			if (!(SignupModel.isSet_esf())) {
				terms_conditions_btn.get(1).click();
			}

			if (SignupModel.isSet_esf()) {
				System.out.println("Part of Esf excemption!");
			}
			Thread.sleep(1000);
		}

		try {
			// Submit
			VisibilityOfElementByXpath("//span[contains(text(),'Continue')]", 15).click();

		}

		catch (Exception e) {
			// Submit
			VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 15).click();
		}

		// VALIDATE NEXT PAGE (ADD CREDIT CARD PAGE OPENED)
		if (SignupModel.getAccount_type().contains("personal")) {
			Assert.assertTrue(PresenceOfElementByXpath("//h4[contains(text(),'Choose Plan')]", 30).isDisplayed(),
					"Billing interval page not found!");
		}

		if (SignupModel.getAccount_type().contains("business")) {
			Assert.assertTrue(
					PresenceOfElementByXpath("//h4[contains(text(),'Choose Billing Interval')]", 30).isDisplayed(),
					"Billing interval page not found!");
		}

	}

	// This step will choose plan and add all card details
	public void choosePricingPlanAndAddCardDetails() throws Exception {

		// FAMILY PLAN
		if (SignupModel.getAccount_type().equals("personal")) {

			// Choose Plan
			WebElement choose_plan = VisibilityOfElementByXpath(SignupModel.getPersonal_plan(), 15);
			choose_plan.click();

			// Choose Billing Interval
			WebElement duration = VisibilityOfElementByXpath(SignupModel.getPersonal_billing_interval(), 15);
			duration.click();
			Thread.sleep(4000);
		}

		// BUSINESS PLAN
		if (SignupModel.getAccount_type().equals("business")) {

			// Choose Billing Interval
			WebElement billing_interval = PresenceOfElementByXpath(SignupModel.getChoose_business_billing_interval(),
					15);
			billing_interval.click();
			Thread.sleep(2000);
		}

		if (SignupModel.getBluetooth_is().equals("free")) {
			System.out.println("This is Bluetooth free device!");

			// Click on try for free button
			VisibilityOfElementByXpath("//button[contains(text(),'Try For Free')]", 10).click();

			// Validate alert found
			boolean isFound = VisibilityOfElementByXpath("//h4[contains(text(),'Are You Sure?')]", 10).isDisplayed();
			Assert.assertEquals(isFound, true, "Try for free, Alert not found!");

			// Click on Yes
			VisibilityOfElementByXpath("//button[contains(text(),'Yes')]", 10).click();

			// Validate user redirected to home page or not
			Assert.assertTrue(VisibilityOfElementByXpath("//h4[contains(text(),'Bluetooth Instructions')]", 15)
					.getText().contains("Bluetooth Instructions"));

			VisibilityOfElementByXpath("//button[contains(text(),'GOT IT')]", 10).click();

		}

		if (SignupModel.getBluetooth_is().equals("free_plus_paid")) {

			System.out.println("This is bluetooth free_plus_paid device!");
			// Click on continue button
			VisibilityOfElementByXpath("//button[text()='Continue']", 2).click();

			// Add credit card
			AddCreditCard();

			// Same as billing check box
			VisibilityOfElementByXpath("//div[contains(text(),'Same as Billing')]/div//span", 10).click();

			// Enter email address
			VisibilityOfElementByXpath("//input[@placeholder='Email Address']", 15)
					.sendKeys(SignupModel.getOwner_email());

			// Check terms and conditions check-box
			VisibilityOfElementByXpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']", 10).click();

			// Click on continue to billing info button
			VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 10).click();

			// Validate bluetooth HomePage
			Assert.assertTrue(VisibilityOfElementByXpath("//h4[contains(text(),'Bluetooth Instructions')]", 15)
					.getText().contains("Bluetooth Instructions"));

			VisibilityOfElementByXpath("//button[contains(text(),'GOT IT')]", 10).click();

		}

		if (!(SignupModel.getBluetooth_is().contains("free"))) {

			try {
				// Check continue button is available
				VisibilityOfElementByXpath("//button[text()='Continue']", 2).click();
			}

			catch (Exception e) {
				System.out.println("Continue button not found!");
			}

			AddCreditCard();

			// Validate next page (Step 2)
			Assert.assertTrue(VisibilityOfElementByXpath("//h4[contains(text(),'Driver Setup')]", 20).isDisplayed(),
					"Monitor and Driver page not found!");
			Thread.sleep(2000);
		}
	}

	// Step 2 will add monitor driver details
	public void step2Setup() throws Exception {

		if (SignupModel.getAccount_type().contains("business")) {
			System.out.println("This is Business Plan");
		}

		if (SignupModel.getAccount_type().contains("personal")) {

			// Click on Add new monitor button
			VisibilityOfElementByXpath("//button[contains(text(),'Add New Monitor')]", 15).click();

			// Validate form open
			Assert.assertTrue(
					VisibilityOfElementByXpath("//label[contains(text(),'Monitor First Name')]", 15).isDisplayed(),
					"Add Monitor form not opened!");

			// Storing all elements to fill up Monitor form
			List<WebElement> mon_ele = VisibilityOfAllElementsByXpath("//div[@class='gWHzj82vXtHhEXQk0erkB_0']/input",
					15);

			// Enter Monitor First Name
			mon_ele.get(0).sendKeys("Test_Monitor");

			// Enter Monitor Last Name
			mon_ele.get(1).sendKeys("Demo");

			// Monitor Email
			mon_ele.get(2).sendKeys("demomonitor123@mailinator.com");

			// Monitor phone number
			mon_ele.get(3).sendKeys("8527419632");

			// Check-box (Phone number is not from US)
			VisibilityOfElementByXpath("//div[@class='form-group'][5]//span", 10).click();

			boolean pick_one_car;
			try {
				pick_one_car = VisibilityOfElementByXpath("//div[@class='pickOne']/small", 2).isDisplayed();
			} catch (Exception e) {
				pick_one_car = false;
			}

			if (pick_one_car) {
				// Pick at least one car
				List<WebElement> pick_car = VisibilityOfAllElementsByXpath("//div[@class='form-group'][6]//span", 10);
				pick_car.get(0).click();
			}

			// Submit button
			VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 15).click();

			// Verify success message after click on submit button
			softassert.assertTrue(
					VisibilityOfElementByXpath("//div[@class='flash-message text-center success']", 15).isDisplayed(),
					"Success not found!");

			// Verify Monitor added in drop-down list (Who Drives This Car The Most?)
			boolean is_monitor_added = false;
			Select sel = new Select(driver.findElement(By.xpath("//select[@class='select']")));
			try {
				sel.selectByVisibleText("Test_Monitor Demo");
				is_monitor_added = true;
			} catch (Exception e) {

			}
			softassert.assertEquals(is_monitor_added, true, "Added Monitor not found in drop-down list!");

			// Added Monitor should also appear in List Of All Monitors And Drivers
			boolean list_mon = VisibilityOfAllElementsByXpath("//h4[contains(text(),'Test_Monitor Demo')]", 15)
					.size() == 1;
			softassert.assertEquals(list_mon, true, "Added Monitor not found in List Of All Monitors And Drivers!");

			// Closing the Monitor form
			VisibilityOfElementByXpath("//button[contains(text(),'Cancel')]", 15).click();

			// ADD DRIVER
			// Click on Add New Driver button
			Thread.sleep(1000);
			VisibilityOfElementByXpath("//button[contains(text(),'Add New Driver')]", 15).click();

			// Validate form open
			Assert.assertTrue(
					VisibilityOfElementByXpath("//label[contains(text(),'Driver First Name')]", 15).isDisplayed(),
					"Add Driver form not opened!");

			// Storing all elements to fill up Monitor form
			List<WebElement> mon_ele2 = VisibilityOfAllElementsByXpath("//div[@class='gWHzj82vXtHhEXQk0erkB_0']/input",
					15);

			// Enter Driver First Name
			mon_ele2.get(0).sendKeys("Test_Driver");

			// Enter Driver Last Name
			mon_ele2.get(1).sendKeys("Demo");

			// Driver Email
			mon_ele2.get(2).sendKeys("demodriver123@mailinator.com");

			// Driver phone number
			mon_ele2.get(3).sendKeys("7418529632");

			// Check-box (Phone number is not from US)
			VisibilityOfElementByXpath("//div[@class='form-group'][5]//span", 15).click();

			try {
				pick_one_car = VisibilityOfElementByXpath("//div[@class='pickOne']/small", 2).isDisplayed();
			} catch (Exception e) {
				pick_one_car = false;
			}

			if (pick_one_car) {
				// Pick at least one car
				List<WebElement> pick_car = VisibilityOfAllElementsByXpath("//div[@class='form-group'][6]//span", 10);
				pick_car.get(0).click();
			}
			// Submit button
			VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 15).click();
			Thread.sleep(1000);
			// Verify success message after click on submit button
			boolean success_msg2 = VisibilityOfAllElementsByXpath("//div[@class='flash-message text-center success']",
					15).size() == 1;
			Assert.assertEquals(success_msg2, true, "Succes message not appear!");

			// Verify Monitor added in drop-down list (Who Drives This Car The Most?)
			Select sel2 = new Select(driver.findElement(By.xpath("//select[@class='select']")));
			Thread.sleep(2000);
			try {
				sel2.selectByVisibleText("Test_Driver Demo");
			} catch (Exception e) {
				softassert.assertEquals(false, true, "Added Driver not found in drop-down list!");
			}

			// Added Monitor should also appear in List Of All Monitors And Drivers
			boolean list_mon2 = VisibilityOfAllElementsByXpath("//h4[contains(text(),'Test_Driver Demo')]", 15)
					.size() == 1;
			softassert.assertEquals(list_mon2, true, "Added Driver not found in List Of All Monitors And Drivers!");
		}

		// Click on next button
		Thread.sleep(2000);

		try {
			VisibilityOfElementByXpath("//span[contains(text(),'Save and Go To Next Step')]", 5).click();
		}

		catch (Exception e) {
			VisibilityOfElementByXpath("//span[contains(text(),'Continue')]", 15).click();
		}

		// Validate next page (Step 3)
		if (SignupModel.getAccount_type().contains("business")) {
			Assert.assertTrue(
					VisibilityOfElementByXpath("//h4[contains(text(),'Introduction to Modes')]", 15).isDisplayed(),
					"Step 3 page not found!");
		}

		if (SignupModel.getAccount_type().contains("personal")) {
			Assert.assertTrue(VisibilityOfElementByXpath("//h4[contains(text(),'Introduction to Safety Modes')]", 15)
					.isDisplayed(), "Step 3 page not found!");
		}

	}

	public void step3Setup() throws Exception {
		Thread.sleep(2000);
		VisibilityOfElementByXpath("//span[contains(text(),'Skip')]", 15).click();

		// Validate next page (Step 4)
		Assert.assertTrue(VisibilityOfElementByXpath("//h4[contains(text(),'Alert Settings')]", 20).isDisplayed(),
				"Alert setting page not found!");
	}

	public void step4Setup() throws Exception {
		Thread.sleep(2000);

		// TURN ON LOW FUEL NOTIFICATIONS ALERTS
//			VisibilityOfElementByXpath("//span[text()='Low Fuel Notifications']/following-sibling::div/div/div", 15)
//					.click();
//			Thread.sleep(2000);
		//
//			// Toggle status
//			String fuel_noti_toggle_status = VisibilityOfElementByXpath(
//					"//span[text()='Low Fuel Notifications']/following-sibling::div/div/div/span", 15).getText();
		//
//			// Validate notification toggle has turned ON
//			Assert.assertEquals(fuel_noti_toggle_status, "ON", "Unable to turn ON fuel notification toggle!");
		//
//			// Click on advance setting to set fuel percentage notification
//			VisibilityOfElementByXpath("//span[text()='Low Fuel Notifications']/following-sibling::div//i", 10).click();
		//
//			// Validate low fuel notifications settings page opened
//			boolean low_fuel_noti_settings_page_opened = VisibilityOfAllElementsByXpath(
//					"//h4[text()='Low Fuel Notifications Settings']", 10).size() == 1;
		//
//			Assert.assertEquals(low_fuel_noti_settings_page_opened, true,
//					"Low fuel notifications settings page not opened!");
		//
//			// Set fuel percentage to 50%
//			VisibilityOfElementByXpath("//span[contains(text(),'50%')]/following-sibling::div/div", 15).click();
//			Thread.sleep(2000);
		//
//			// Validate percentage set to 50 or not
//			boolean is_percentage_set_to_50 = VisibilityOfElementByXpath(
//					"//span[contains(text(),'50%')]/following-sibling::div/div/span", 20).getText().contains("ON");
		//
//			Assert.assertEquals(is_percentage_set_to_50, true,
//					"Unable to set fuel percentage to 50! Status not turned ON.");
		//
//			// Click on all alert settings button (Going back)
//			VisibilityOfElementByXpath("//button[text()='All Alert Settings']", 10).click();

		// Click on Next page button
		try {
			wait(driver, 2).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Save and Go To')]"))).click();
		}

		catch (Exception e) {
			VisibilityOfElementByXpath("//span[contains(text(),'Continue')]", 15).click();
		}

		// Validate next page (Step 5)
		Assert.assertTrue(
				VisibilityOfElementByXpath("//h4[contains(text(),'Roadside Emergency Card')]", 15).isDisplayed(),
				"Step 5, Roadside Emergency Card page not found!");
	}

	public void step5FinishSetup() throws Exception {
		Thread.sleep(2000);

		// Click on Finish button
		VisibilityOfElementByXpath("//a[contains(text(),'Finish')]", 15).click();
		Assert.assertTrue(Validate_HomePage_Landing(), "HomePage Landing failed! Not Found. Searching vehicle trip...");

	}

	public boolean Validate_HomePage_Landing() {
		// Verify user redirected to home page or not
		Assert.assertTrue(
				VisibilityOfElementByXpath("//li[contains(text(),'You are now ready to drive!')]", 15).isDisplayed(),
				"Home page not found!");

		try {
			Thread.sleep(3000);
			List<WebElement> el = PresenceOfAllElementsByXpath(
					"//h4[contains(text(),'Welcome')]/following-sibling::div/button", 15);
			el.get(1).click();
		}

		catch (Exception e) {
			System.out.println("Welcome to autobrain popup not found");
		}

		boolean reg_success = false;
		try {
			reg_success = VisibilityOfAllElementsByXpath("//span[contains(text(),'Searching')]", 15).size() == 1;
		}

		catch (Exception e) {
			System.out.println(
					"NOT FOUND--> Your Autobrain device may take up to 12 hours and 3 drives to sync with your car.");
		}

		return reg_success;
	}

	// Step 1 for Activating new device
	public void activateNewDevice(String device_no) throws Exception {

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
						.sendKeys(device_no);

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
						.sendKeys(SignupModel.getVIN());

		// Expanding Car Icon drop-down
		VisibilityOfElementByXpath("//div[@class='select-dropdown']/div", 15).click();
		Thread.sleep(3000);

		// Selecting Car Icon from drop-down
		VisibilityOfElementByXpath(SignupModel.getCaricon(), 15).click();

		// Car Year
		Select y = new Select(VisibilityOfElementByXpath("//div[@class='secondary-input'][1]/div[2]/select", 15));
		y.selectByVisibleText(SignupModel.getYear());
		Thread.sleep(2000);

		// Car Make
		Select m = new Select(VisibilityOfElementByXpath("//div[@class='secondary-input'][2]/div[2]/select", 15));
		m.selectByVisibleText(SignupModel.getMake());
		Thread.sleep(2000);

		// Car Model
		Select mo = new Select(VisibilityOfElementByXpath("//div[@class='secondary-input'][3]/div[2]/select", 15));
		mo.selectByIndex(SignupModel.getModel());
		Thread.sleep(1000);

		// Fill Up Insurance and Registration Forms
//		VehicleProfile v = new VehicleProfile();
//		v.Ins_Reg_Forms();

		// Check terms and conditions
		List<WebElement> el = VisibilityOfAllElementsByXpath("//div[@class='esf']/div[1]", 15);
		el.get(0).click();
		Thread.sleep(2000);

		if (!(SignupModel.isSet_esf())) {
			el.get(1).click();
		}

		Thread.sleep(1500);

		WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'Continue')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(1500);
		ele.click();

//		try {
//			// Submit
//			VisibilityOfElementByXpath("//span[contains(text(),'Continue')]", 5).click();
//		}
//
//		catch (Exception e) {
//			// Submit
//			VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 2).click();
//		}

		// VALIDATE CHOOSE PLAN PAGE
		Assert.assertTrue(VisibilityOfElementByXpath("//h4[contains(text(),'Choose Plan')]", 10).isDisplayed(),
				"Choose plan page not found!");

		// BUSINESS PLAN
		if (SignupModel.getAccount_type().contains("business")) {

			// Choose Billing Interval
			WebElement billing_interval = PresenceOfElementByXpath(SignupModel.getChoose_business_billing_interval(),
					15);
			billing_interval.click();

			// Submit
			VisibilityOfElementByXpath("//div[@class='submit-container']/button", 10).click();
			Thread.sleep(2000);

		}

		// FAMILY PLAN
		if (SignupModel.getAccount_type().contains("personal")) {
			int num = 5;

			// Choose Plan
			List<WebElement> choose_plan = PresenceOfAllElementsByXpath(
					"//div[contains(text(),'see full list')]/following-sibling::button", 15);

			Thread.sleep(2000);

			if (SignupModel.getPersonal_plan().contains("//div[@class='_1nPLChEwNgDH5KMyzoXBEb_0']/div[3]//button")) {
				num = 0;
			}
			if (SignupModel.getPersonal_plan().contains("//div[@class='_1nPLChEwNgDH5KMyzoXBEb_0']/div[3]//button")) {
				num = 1;
			}
			if (SignupModel.getPersonal_plan().contains("//div[@class='_1nPLChEwNgDH5KMyzoXBEb_0']/div[3]//button")) {
				num = 2;
			}

			switch (num)

			{
			case 0: // VIP Plan
				VisibilityOfElementByXpath("//div[@class='hooper-pagination']//li[1]/button", 15).click();
				Thread.sleep(1500);
				choose_plan.get(num).click();
				break;

			case 1: // ESSENTIAL Plan
				VisibilityOfElementByXpath("//div[@class='hooper-pagination']//li[2]/button", 15).click();
				Thread.sleep(1500);
				choose_plan.get(num).click();
				break;

			case 2: // MONEY SAVER Plan
				VisibilityOfElementByXpath("//div[@class='hooper-pagination']//li[3]/button", 15).click();
				Thread.sleep(1500);
				choose_plan.get(num).click();
				break;

			}

			// Choose Billing Interval
			WebElement duration = VisibilityOfElementByXpath(SignupModel.getPersonal_billing_interval(), 15);
			duration.click();

			// Submit
			try {
				VisibilityOfElementByXpath("//button[@class='submit-btn HSKg29-lwI4BPWKQPVBps_0']", 5).click();
			} catch (Exception e) {
				VisibilityOfElementByXpath("//div[@class='submit-container']/button", 10).click();
			}

		}

		// Load home page
		boolean homepage_displayed = false;
		try {
			homepage_displayed = VisibilityOfElementByXpath(
					"//li[@class='hooper-slide column is-active is-current']//div[contains(text(),'CAR FINDER')]", 90)
							.isDisplayed();

		} catch (Exception e) {

		}
		Assert.assertEquals(homepage_displayed, true, "Home page is not loaded!");

		if (SignupModel.getBluetooth_is().equals("free_plus_paid")) {
			VisibilityOfElementByXpath("//button[contains(text(),'GOT IT')]", 10).click();
		}

	}

	public void AddCreditCard() throws Exception {
		// Enter First name
		VisibilityOfElementByXpath("//input[@placeholder='First Name']", 15).sendKeys(SignupModel.getF_name());

		// Enter Last name
		VisibilityOfElementByXpath("//input[@placeholder='Last Name']", 15).sendKeys(SignupModel.getL_name());

		// Enter Billing Address
		VisibilityOfElementByXpath("//input[@placeholder='Billing Address']", 15).sendKeys(SignupModel.getStreet());

		// Enter City
		VisibilityOfElementByXpath("//input[@placeholder='City']", 15).sendKeys(SignupModel.getCity());
		Thread.sleep(2000);

		// Select State
		Select state = new Select(VisibilityOfElementByXpath("//option[contains(text(),'State')]//parent::select", 15));
		state.selectByVisibleText(SignupModel.getState());

		// Add card number
		VisibilityOfElementByXpath("//input[@placeholder='Card Number']", 15).sendKeys(SignupModel.getCard_no());

		// Select Month
		Select month = new Select(VisibilityOfElementByXpath("//option[contains(text(),'Month')]//parent::select", 15));
		month.selectByIndex(11);
		Thread.sleep(2000);

		// Select Year
		Select year = new Select(VisibilityOfElementByXpath("//option[contains(text(),'Year')]//parent::select", 15));
		year.selectByIndex(8);

		// Enter CVV
		VisibilityOfElementByXpath("//input[@placeholder='CVV']", 15).sendKeys(SignupModel.getCard_cvv());

		// Enter Zip Code
		VisibilityOfElementByXpath("//input[@placeholder='Zip Code']", 15).sendKeys(SignupModel.getZip());

		// Click on check-box (Make This Your Primary Card)
		VisibilityOfElementByXpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']", 15).click();

		// Click on Save button
		VisibilityOfElementByXpath("//button[contains(text(),'Save')]", 15).click();

		// Validate enter card details accepted or not
		boolean is_error = false;
		try {
			is_error = VisibilityOfAllElementsByXpath("//div[@class='flash-message text-center error']", 5).size() == 1;
		}

		catch (Exception e) {

		}
		Assert.assertEquals(is_error, false, "Found error while trying to submit the card details!");
	}

	// CREATE DEVICE NUMBER FROM WORKER PANEL
	public ArrayList<String> createDeviceFromPanel() throws Exception {
		String device_num = null;

		for (int i = 0; i < SignupModel.getTotal_bought_devices(); i++) {

			// Navigate to add device page
			driver.navigate().to("https://stg.autobrain.com/worker/devices/new");

			// Validating add device page opened or not
			boolean isNewDevicePageFound = false;
			while (!isNewDevicePageFound) {
				isNewDevicePageFound = driver.findElements(By.xpath("//h1[contains(text(),'Add a new device')]"))
						.size() == 1;
			}

			// Enter phone number
			VisibilityOfElementByXpath("//textarea[@placeholder='2125550123,2125554567']", 15)
					.sendKeys(GeneratePhoneNumber());

			// Check-box - Phone number is not from United state or Canada
			VisibilityOfElementByXpath("//input[@id='device_international_phone_number']", 15).click();

			// Select Model
			Select sel = new Select(driver.findElement(By.name("device[model]")));

			if (SignupModel.getBluetooth_is().contains("free")) {

				// Enter cellular service type
				VisibilityOfElementByName("device[cellular_service_type]", 10).sendKeys("FREE_1");

				// Select model
				sel.selectByVisibleText("Autobrain_Bluetooth_1");
			}

			else {
				// Enter cellular service type
				VisibilityOfElementByName("device[cellular_service_type]", 10).sendKeys("WYLESS");

				// Select model
				sel.selectByVisibleText("Standard");
			}

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

			// Check if page broken
			try {
				if (wait(driver, 2).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("//h1[text()='Whoops! It seems like something went wrong!']"))).size() == 1) {
					System.out.println("Page broken. Unable to create device number.");
					Assert.assertEquals(true, false, "Page broken!");
				}
			} catch (Exception e) {

			}

			// VALIDATE ERROR
			while (driver.findElements(By.id("flash_alert")).size() > 0) {
				if (driver.findElements(By.xpath("//div[contains(text(),'Uid has already been taken')]")).size() == 1) {
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
					VisibilityOfElementByXpath("//textarea[@placeholder='2125550123,2125554567']", 15).clear();
					VisibilityOfElementByXpath("//textarea[@placeholder='2125550123,2125554567']", 15)
							.sendKeys(GeneratePhoneNumber());

					// Click on create device button
					VisibilityOfElementByName("commit", 10).click();
				}
			}

			// Adding device one by one
			SignupModel.getAll_Devices_No().add(device_num);
			System.out.println(device_num);
		}

		Assert.assertTrue(VisibilityOfElementByXpath("//div[@id='flash_success']", 10).isDisplayed());
		return SignupModel.getAll_Devices_No();
	}

	// ESF_Exemptions Method
	public void EsfExemptionsSetup() throws Exception {

		if (SignupModel.isSet_esf()) {
			// Logout registered user
			VisibilityOfElementByXpath("//div[contains(text(),'Log Out')]", 15).click();
			Thread.sleep(2000);

			// Login main user
			login.login("john@example.com", "welcome");

			// Navigate to ESF_Exemptions page
			driver.navigate().to("https://stg.autobrain.com/worker/esf_exemptions");

			// Enter email
			VisibilityOfElementByXpath("//input[@id='email']", 15).sendKeys(SignupModel.getOwner_email());

			// Click on add button
			VisibilityOfElementByXpath("//input[@name='commit' and @value ='Add']", 15).click();

			boolean email_added;
			// validate email added
			try {
				email_added = VisibilityOfAllElementsByXpath(
						"//td[contains(text(),'" + SignupModel.getOwner_email() + "')]", 15).size() == 1;
			} catch (Exception e) {
				email_added = false;
			}

			Assert.assertTrue(email_added);

			// Logout panel user
			VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]", 15).click();
			Thread.sleep(2000);

			// Navigate user to login url
			driver.navigate().to(url);

			// Login last registered user
			VisibilityOfElementByID("user_email", 15).sendKeys(SignupModel.getOwner_email());

			// Entering password
			VisibilityOfElementByID("user_password", 15).sendKeys("welcome");

			// Click on login button
			wait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();

			boolean is_step1_available;
			try {
				is_step1_available = PresenceOfAllElementsByXpath("//h4[contains(text(),'Create a Vehicle Profile')]",
						15).size() == 1;
			}

			catch (Exception e) {
				is_step1_available = false;
			}

			Assert.assertEquals(is_step1_available, true, "Step 1 page not found!");

			boolean esf_check_box = true;

			Thread.sleep(2000);

			try {
				esf_check_box = PresenceOfAllElementsByXpath("//div[contains(text(),'Upon canceling')]", 10)
						.size() == 1;
			}

			catch (Exception e) {
				esf_check_box = false;
			}

			Assert.assertEquals(esf_check_box, false, "ESF Exemptions check-box should not appear!");
		}

	}

	// Generate Random Device Number
	public String GenerateDeviceNumber() {
		String CHARS = "123456789";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * CHARS.length());
			salt.append(CHARS.charAt(index));
		}
		String saltStr = salt.toString(); // System.out.println(saltStr);
		return saltStr;
	}

	// Generate Random Phone Number
	public String GeneratePhoneNumber() {
		int num1, num2, num3; // 3 numbers in area code
		int set2, set3; // sequence 2 and 3 of the phone number

		Random generator = new Random();

		// Area code number; Will not print 8 or 9
		num1 = generator.nextInt(7) + 1; // add 1 so there is no 0 to begin
		num2 = generator.nextInt(8); // randomize to 8 because 0 counts as a number in the generator
		num3 = generator.nextInt(8);

		// Sequence two of phone number
		// the plus 100 is so there will always be a 3 digit number
		// randomize to 643 because 0 starts the first placement so if i randomized up
		// to 642 it would only go up to 641 plus 100
		// and i used 643 so when it adds 100 it will not succeed 742
		set2 = generator.nextInt(643) + 100;

		// Sequence 3 of number
		// add 1000 so there will always be 4 numbers
		// 8999 so it wont succeed 9999 when the 1000 is added
		set3 = generator.nextInt(8999) + 1000;
		String ph_num = "" + num1 + num2 + num3 + set2 + set3;

		return ph_num;

	}

	// Generate random email
	public String GenerateRandomEmail() {
		// Generating random email
		Random randomGenerator = new Random();
		SignupModel.setRandom_int(randomGenerator.nextInt(10000));
		String email = "junking" + SignupModel.getRandom_int() + "@yopmail.com";
		SignupModel.setL_name("" + SignupModel.getRandom_int());
		return email;
	}

}
