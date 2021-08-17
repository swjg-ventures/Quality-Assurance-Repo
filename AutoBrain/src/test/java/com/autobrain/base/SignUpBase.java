package com.autobrain.base;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.autobrain.models.SignupModel;
import com.autobrain.pages.DeviceReplacement;
import com.autobrain.pages.Login;

import au.com.bytecode.opencsv.CSVWriter;

public class SignUpBase extends Base {
	private SignupModel signupModel;
	private SignupModel pageObj;
	public Login login;
	public String LockObject = "lockObject";

	public SignUpBase(SignupModel signupModel) {
		// Initializing
		login = new Login();
		pageObj = PageFactory.initElements(getDriver(), SignupModel.class);
		this.signupModel = signupModel;
		signupModel.setAll_Devices_No(new ArrayList<String>());
	}

	public void signup() throws Exception {

		// Check if the sign-up email is empty
		if ((signupModel.getOwner_email() == null)) {
			signupModel.setOwner_email(generateRandomEmail());
		}

		// Clicking on sign-up button
		VisibilityOfElementByXpath("//a[contains(text(),'SIGN UP')]", 15).click();

		// Entering first-name
		VisibilityOfElementByID("user_first_name", 15).clear();
		VisibilityOfElementByID("user_first_name", 15).sendKeys("Test");

		// Entering last-name
		VisibilityOfElementByID("user_last_name", 15).clear();
		VisibilityOfElementByID("user_last_name", 15).sendKeys("" + signupModel.getRandom_int());

		// Entering email
		VisibilityOfElementByID("user_email", 15).clear();

		// Sign-up email
		VisibilityOfElementByID("user_email", 15).sendKeys(signupModel.getOwner_email());

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
		for (int i = 0; i < getDriver().findElements(By.xpath("//span[@class='help-block']")).size(); i++) {
			boolean email_alert = getDriver()
					.findElements(By.xpath("//span[contains(text(),'has already been taken')]")).size() != 0;
			boolean firstN_alert = getDriver()
					.findElements(By.xpath("//input[@placeholder='First Name']/following-sibling::span")).size() != 0;
			boolean lastN_alert = getDriver()
					.findElements(By.xpath("//input[@placeholder='Last Name']/following-sibling::span")).size() != 0;

			// First name already exist
			if (firstN_alert) {
				VisibilityOfElementByID("user_first_name", 15).clear();
				VisibilityOfElementByID("user_first_name", 15).sendKeys("demouser" + signupModel.getRandom_int());
			}

			// Last name already exist
			if (lastN_alert) {
				VisibilityOfElementByID("user_last_name", 15).clear();
				VisibilityOfElementByID("user_last_name", 15).sendKeys("Last" + signupModel.getRandom_int());
			}

			// Email already exist
			if (email_alert) {

				VisibilityOfElementByID("user_email", 15).clear();
				signupModel.setOwner_email(generateRandomEmail());
				System.out.println("Email Already exist! New Email Is-->" + " " + signupModel.getOwner_email());
				VisibilityOfElementByID("user_email", 15).sendKeys(signupModel.getOwner_email());

				VisibilityOfElementByID("user_password", 15).sendKeys("welcome");
				VisibilityOfElementByID("user_password_confirmation", 15).sendKeys("welcome");

				// Clicking on sign-up button to register
				VisibilityOfElementByXpath("//div[contains(text(),'SIGN UP')]", 15).click();
			}

		}

		try {

			// Validating confirmation key page found
			Assert.assertTrue(VisibilityOfElementByXpath("//a[contains(text(),'Resend Confirmation Email')]", 15)
					.getText().contains("Resend Confirmation Email"), "Confirmation key page not found!");

			String confirmation_code = getConfirmationCode();
			getDriver().get(url);

			// Set confirmation code
			VisibilityOfElementByXpath("//input[@placeholder='Confirmation Code']", 15).sendKeys(confirmation_code);

			// Click on submit button
			wait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
			Thread.sleep(2000);
		}

		catch (Exception e) {
			if (extractJSLogsInfo("500 (Internal Server Error)")) {
				Assert.assertFalse(true, "500 (Internal Server Error)");
			}

			// Validate next page Step 1
			Assert.assertTrue(
					VisibilityOfElementByXpath("//div[contains(text(),'Is Your')]/following-sibling::div//span", 15)
							.isDisplayed());

		}

	}

	public synchronized void orderDeviceFromWebSite() throws Exception {

		String account_type = null;
		if (signupModel.getAccount_type().equalsIgnoreCase("personal")) {
			account_type = "Autobrain Family";
		}

		if (signupModel.getAccount_type().equalsIgnoreCase("business")) {
			account_type = "Autobrain Business";

		}

		if (signupModel.getAccount_type().equalsIgnoreCase("bluetooth")) {
			account_type = "Autobrain Bluetooth";

		}
		// Generating random email
		signupModel.setOwner_email(generateRandomEmail());

		getDriver().get("https://stg.autobrain.com/buy");

		// Open drop-down to select number of devices
		Select s = new Select(VisibilityOfElementByXpath("//div[@class='quanities-container']/select", 15));
		s.selectByVisibleText(String.valueOf(signupModel.getTotal_bought_devices()));

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
		VisibilityOfElementByXpath("//input[@placeholder='Email Address']", 15).sendKeys(signupModel.getOwner_email());
		System.out.println("Entered Email During Bought The Product " + "--> " + signupModel.getOwner_email());

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
						"//p[contains(text(),'Your package will include " + signupModel.getTotal_bought_devices()
								+ " Autobrain device as well as a 5 step quick start guide.')]",
						15).size() == 1,
				"Ordered quantity not matching with order receipt!");

		// Verify actual product price according to selected quantity
		String actual_product_price = VisibilityOfElementByXpath(
				"//div[contains(text(),'Total:')]/following-sibling::div", 15).getText();

//		Assert.assertEquals(actual_product_price, expected_product_price,
//				"Expected product price not matching with actual product price!");

		Thread.sleep(1000);
		orderToShip();
	}

	public void orderToShip() throws Exception {
		getDriver().navigate().to(url);
		// Calling login method
		login.login("john@example.com", "welcome");

		// Navigate to devices page
		getDriver().navigate().to("https://stg.autobrain.com/worker/devices");

		// Store user buy device ID
		String user_buy_device = getDriver().findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();

		// Create device number for customer
		createDeviceFromPanel();

		// Switch to Orders To Ship page
		getDriver().navigate().to("https://stg.autobrain.com/worker/online_fulfillment/invoices_to_ship");

		// Validate order received
		String l_name = String.valueOf(signupModel.getRandom_int());
		String last_name = VisibilityOfElementByXpath("//tr[1]/td[2]", 10).getText();
		Assert.assertTrue(last_name.equals(l_name), "Order not received!");

		// Click on Add Device button to add the manually created device
		pageObj.add_device_btn.get(0).click();

		// Validate enter field box opened
		boolean msg_prompt = PresenceOfAllElementsByXpath(
				"//h4[contains(text(),'Add a device number to the following cars')]", 15).size() != 0;

		Assert.assertEquals(msg_prompt, true, "Message alert not prompted!");

		// Enter Device number
		Thread.sleep(2500);

		for (int i = 0; i < pageObj.no_of_input_devices.size(); i++) {
			if (signupModel.isBluetooth_upgraded()) {
				TypeInFieldSlowly(pageObj.no_of_input_devices.get(i), signupModel.getAll_Devices_No().get(1));
			}

			else {
				TypeInFieldSlowly(pageObj.no_of_input_devices.get(i), signupModel.getAll_Devices_No().get(i));
			}
			Thread.sleep(3000);

			// Check if the error exist after enter custom created device number
			while (getDriver().findElements(By.xpath("//div[@class='form-group form-inline col-xs-12 has-error']"))
					.size() > 0) {
				// Closed opened message box
				List<WebElement> close_btn = getDriver()
						.findElements(By.xpath("//div[@class='modal fade in']//button[1]"));
				close_btn.get(1).click();
				Thread.sleep(1000);

				// Refresh the page
				getDriver().navigate().refresh();

				// Time to reload the complete page
				while (getDriver().findElements(By.xpath("//h3[contains(text(),'Orders to ship')]")).size() == 0) {
					System.out.println("Loading...Orders to ship page!");
				}

				// Again open the message box
				pageObj.add_device_btn.get(0).click();
				Thread.sleep(1500);

				// Enter the custom created device number
				// Reset i to 0 and start entering the device ID from first index
				i = 0;
				if (signupModel.isBluetooth_upgraded()) {
					TypeInFieldSlowly(pageObj.no_of_input_devices.get(i), signupModel.getAll_Devices_No().get(1));
				}

				else {
					TypeInFieldSlowly(pageObj.no_of_input_devices.get(i), signupModel.getAll_Devices_No().get(i));
				}
				Thread.sleep(3000);

			}

		}

		// Click on Save button
		List<WebElement> btn = getDriver().findElements(By.xpath("//button[contains(text(),'Save changes')]"));
		btn.get(0).click();
		Thread.sleep(1000);
		// Validate device added
		boolean device_added;
		try {
			device_added = PresenceOfAllElementsByXpath("//tbody/tr[1]//td[9]/a[contains(text(),'Device #')]", 15)
					.size() == 1;
		} catch (Exception e) {
			device_added = false;
			e.getStackTrace();
		}

		Assert.assertEquals(device_added, true, "Device not added!");

		Thread.sleep(2000);

		// Click on Print/View shipping label
		Actions action = new Actions(getDriver());
		WebElement el = ElementToBeClickableByXpath("//tbody/tr[1]//td[10]/a", 15);
		action.moveToElement(el).click().build().perform();

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
		Actions actions = new Actions(getDriver());
		actions.moveToElement(mark_as_shipped_btn.get(0)).click().build().perform();

		// Refresh the page
		getDriver().navigate().refresh();

		Thread.sleep(2500);

		// After marked as shipped device should disappear from the list
		boolean user_disappear_from_list = getDriver()
				.findElements(By.xpath("//a[contains(text(),'Device #" + "Device_Num" + " added')]")).size() == 0;
		Assert.assertEquals(user_disappear_from_list, true,
				"User still exist in the list which should not after marked the device as shipped!");

		// Navigate to devices page
		getDriver().navigate().to("https://stg.autobrain.com/worker/devices");

		String device_no = getDriver().findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();

		boolean user_device_updated = user_buy_device != device_no;
		Assert.assertEquals(user_device_updated, true, "User bought device not updated!");

		VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]", 15).click();
		Thread.sleep(2000);
		getDriver().navigate().to("https://stg.autobrain.com");
	}

	// Step 1 will add all car info
	public void step1Setup(String device_no) throws Exception {

		// Create a Name for Your Car
		VisibilityOfElementByXpath(
				"//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[1]/input", 15)
						.sendKeys(signupModel.getAll_Devices_No().get(0));

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
		Thread.sleep(1000);
		// Expanding Car Icon drop-down
//		VisibilityOfElementByXpath("//div[@class='select-dropdown']/div", 15).click();
//		Thread.sleep(3000);

		// Selecting Car Icon from drop-down
//		VisibilityOfElementByXpath(SignupModel.getCaricon(), 15).click();

		// Car Year
		Select y = new Select(getDriver().findElement(By.xpath("//div[@class='secondary-input'][1]/div[2]/select")));
		y.selectByVisibleText(SignupModel.getYear());
		Thread.sleep(1000);

		// Car Make
		Select m = new Select(getDriver().findElement(By.xpath("//div[@class='secondary-input'][2]/div[2]/select")));
		m.selectByVisibleText(SignupModel.getMake());
		Thread.sleep(1000);

		// Car Model
		Select mo = new Select(getDriver().findElement(By.xpath("//div[@class='secondary-input'][3]/div[2]/select")));
		mo.selectByIndex(SignupModel.getModel());
		Thread.sleep(1000);

		// Fill Up Insurance and Registration Forms
		// VehicleProfile v = new VehicleProfile();
		// v.Ins_Reg_Forms();

		// Check off terms and conditions
		List<WebElement> terms_conditions_btn = VisibilityOfAllElementsByXpath("//div[@class='esf']/div[1]", 15);
		terms_conditions_btn.get(0).click();
		Thread.sleep(2000);

		if (!((signupModel.getBluetooth_signup_tier().equals("free"))
				|| (signupModel.getBluetooth_signup_tier().equals("paid")))) {
			if (!(signupModel.isSet_esf())) {
				terms_conditions_btn.get(1).click();
			}

			if (signupModel.isSet_esf()) {
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
		if (signupModel.getAccount_type().contains("personal") || signupModel.getAccount_type().contains("bluetooth")) {
			Assert.assertTrue(PresenceOfElementByXpath("//h4[contains(text(),'Choose Plan')]", 60).isDisplayed(),
					"Billing interval page not found!");
		}

		if (signupModel.getAccount_type().contains("business")) {
			Assert.assertTrue(
					PresenceOfElementByXpath("//h4[contains(text(),'Choose Billing Interval')]", 30).isDisplayed(),
					"Billing interval page not found!");
		}

	}

	// This step will choose plan and add all card details
	public void choosePricingPlanAndAddCardDetails() throws Exception {

		// FAMILY PLAN
		if (signupModel.getAccount_type().contains("personal") || signupModel.getAccount_type().contains("bluetooth")) {

			// Choose Plan
//			WebElement choose_plan = VisibilityOfElementByXpath(signupModel.getPersonal_plan(), 15);
//			choose_plan.click();
			
			List<WebElement> nav_btn = VisibilityOfAllElementsByXpath("//ol[@class='hooper-indicators']/li/button", 5);
			
			for(int i=0; i<nav_btn.size(); i++) {
				try {
					WebElement choose_plan1 = VisibilityOfElementByXpath(signupModel.getPersonal_plan(), 2);
					choose_plan1.click();
					break;
				}
				catch(Exception e) {
					nav_btn.get(i).click();
				}
			}

			// Choose Billing Interval
			WebElement duration = VisibilityOfElementByXpath(signupModel.getPersonal_billing_interval(), 15);
			duration.click();
			Thread.sleep(4000);
		}

		// BUSINESS PLAN
		if (signupModel.getAccount_type().equals("business")) {

			// Choose Billing Interval
			WebElement billing_interval = PresenceOfElementByXpath(signupModel.getChoose_business_billing_interval(),
					15);
			billing_interval.click();
			Thread.sleep(2000);
		}

		if (signupModel.getBluetooth_signup_tier().equals("free")) {
			System.out.println("This is Bluetooth free device!");
			Thread.sleep(1000);
			// Click on pagination
			VisibilityOfElementByXpath("//ol[@class='hooper-indicators']/li[5]/button", 10).click();

			// Choose free plan
//			VisibilityOfElementByXpath("//div[@class='_1nPLChEwNgDH5KMyzoXBEb_0']/div[1]//button", 10).click();
			VisibilityOfElementByXpath("//div[text()='Free Forever']//following::button[1]", 10).click();

			Assert.assertTrue(
					PresenceOfElementByXpath("//h4[contains(text(),'This Plan Is Free Forever')]", 5).isDisplayed());

			// Click on continue
			VisibilityOfElementByXpath("//button[text()='Continue']", 10).click();

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

		if (signupModel.getBluetooth_signup_tier().equals("paid")) {

			System.out.println("Signing up with paid tier");
			// Click on continue button
			VisibilityOfElementByXpath("//button[text()='Continue']", 2).click();

			// Add credit card
			addCreditCard();

			// Same as billing check box
			VisibilityOfElementByXpath("//div[contains(text(),'Same as Billing')]/div//span", 10).click();

			// Enter email address
			VisibilityOfElementByXpath("//input[@placeholder='Email Address']", 15)
					.sendKeys(signupModel.getOwner_email());

			// Check terms and conditions check-box
			VisibilityOfElementByXpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']", 10).click();

			// Click on continue to billing info button
			VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 10).click();

			// Validate bluetooth HomePage
			Assert.assertTrue(VisibilityOfElementByXpath("//h4[contains(text(),'Bluetooth Instructions')]", 15)
					.getText().contains("Bluetooth Instructions"));

			VisibilityOfElementByXpath("//button[contains(text(),'GOT IT')]", 10).click();

		}

		if (!((signupModel.getBluetooth_signup_tier().equals("free")
				|| (signupModel.getBluetooth_signup_tier().equals("paid"))))) {

			try {
				// Check continue button is available
				VisibilityOfElementByXpath("//button[text()='Continue']", 2).click();
			}

			catch (Exception e) {
				System.out.println("Continue button not found!");
			}

			addCreditCard();

			// Validate next page (Step 2)
			Assert.assertTrue(VisibilityOfElementByXpath("//h4[contains(text(),'Driver Setup')]", 20).isDisplayed(),
					"Monitor and Driver page not found!");
			Thread.sleep(2000);
		}
	}

	// Step 2 will add monitor driver details
	public void step2Setup() throws Exception {

		if (signupModel.getAccount_type().contains("businessss")) {
			System.out.println("This is Business Plan");
		}

		if (signupModel.getAccount_type().contains("personalll")) {

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
			Select sel = new Select(getDriver().findElement(By.xpath("//select[@class='select']")));
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
			Select sel2 = new Select(getDriver().findElement(By.xpath("//select[@class='select']")));
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
		if (signupModel.getAccount_type().contains("business")) {
			Assert.assertTrue(
					VisibilityOfElementByXpath("//h4[contains(text(),'Introduction to Modes')]", 15).isDisplayed(),
					"Step 3 page not found!");
		}

		if (signupModel.getAccount_type().contains("personal")) {
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

		if (signupModel.getAccount_type().equals("business")) {
			VisibilityOfElementByXpath("//span[text()='Low Fuel Notifications']/following-sibling::div/div/div", 5)
					.click();

		}

		else {
			// TURN ON LOW FUEL NOTIFICATIONS ALERTS
			VisibilityOfElementByXpath("//span[text()='Low Fuel Notifications']/following-sibling::div/div/div", 15)
					.click();
		}

		Thread.sleep(2000);

		// Toggle status
		String fuel_noti_toggle_status = VisibilityOfElementByXpath(
				"//span[text()='Low Fuel Notifications']/following-sibling::div/div/div/span", 15).getText();

		// Validate notification toggle has turned ON
		Assert.assertEquals(fuel_noti_toggle_status, "ON", "Unable to turn ON fuel notification toggle!");

		// Click on advance setting to set fuel percentage notification
		VisibilityOfElementByXpath("//span[text()='Low Fuel Notifications']/following-sibling::div//i", 10).click();

		// Validate low fuel notifications settings page opened
		boolean low_fuel_noti_settings_page_opened = VisibilityOfAllElementsByXpath(
				"//h4[text()='Low Fuel Notifications Settings']", 10).size() == 1;

		Assert.assertEquals(low_fuel_noti_settings_page_opened, true,
				"Low fuel notifications settings page not opened!");

		// Set fuel percentage to 50%
		VisibilityOfElementByXpath("//span[contains(text(),'50%')]/following-sibling::div/div", 15).click();
		Thread.sleep(2000);

		// Validate percentage set to 50 or not
		boolean is_percentage_set_to_50 = VisibilityOfElementByXpath(
				"//span[contains(text(),'50%')]/following-sibling::div/div/span", 20).getText().contains("ON");

		Assert.assertEquals(is_percentage_set_to_50, true,
				"Unable to set fuel percentage to 50! Status not turned ON.");

		// Click on all alert settings button (Going back)
		VisibilityOfElementByXpath("//button[text()='All Alert Settings']", 10).click();

		// Click on Next page button
		try {
			wait(getDriver(), 2).until(ExpectedConditions
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

		try {
			// Click on Finish button
			VisibilityOfElementByXpath("//a[contains(text(),'Finish')]", 4).click();
		}

		catch (Exception e) {
			VisibilityOfElementByXpath("//a[contains(text(),'Save and Go To Next Step')]", 5).click();
		}
		Assert.assertTrue(validateHomepageLanding(), "HomePage Landing failed! Not Found. Searching vehicle trip...");

	}

	public boolean validateHomepageLanding() {
		// Verify user redirected to home page or not
		Assert.assertTrue(
				VisibilityOfElementByXpath("//li[contains(text(),'You are now ready to drive!')]", 60).isDisplayed(),
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
		Thread.sleep(1000);
		// Open navigation menu
		wait(getDriver(), 15).until(ExpectedConditions.visibilityOfElementLocated(By.className("hamburger-container")))
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

		if (!(signupModel.isSet_esf())) {
			el.get(1).click();
		}

		Thread.sleep(1500);

		WebElement ele = getDriver().findElement(By.xpath("//span[contains(text(),'Continue')]"));
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(1500);
		ele.click();

		if (!DeviceReplacement.is_device_rep) {

			// VALIDATE CHOOSE PLAN PAGE
			Assert.assertTrue(VisibilityOfElementByXpath("//h4[contains(text(),'Choose Plan')]", 10).isDisplayed(),
					"Choose plan page not found!");

			// BUSINESS PLAN
			if (signupModel.getAccount_type().contains("business")) {

				// Choose Billing Interval
				WebElement billing_interval = PresenceOfElementByXpath(
						signupModel.getChoose_business_billing_interval(), 15);
				billing_interval.click();

				// Submit
				VisibilityOfElementByXpath("//div[@class='submit-container']/button", 10).click();
				Thread.sleep(2000);

			}

			// FAMILY PLAN
			if (signupModel.getAccount_type().contains("personal")
					|| signupModel.getAccount_type().contains("bluetooth")) {
				int num = 5;

				// Choose Plan
				List<WebElement> choose_plan = PresenceOfAllElementsByXpath(
						"//div[contains(text(),'see full list')]/following-sibling::button", 15);

				Thread.sleep(2000);

				if (signupModel.getPersonal_plan().contains("//div[text()='VIP']//following::button[1]")) {
					num = 1;
				}
				if (signupModel.getPersonal_plan().contains("//div[text()='Essential']//following::button[1]")) {
					num = 2;
				}
				if (signupModel.getPersonal_plan().contains("//div[text()='Money Saver']//following::button[1]")) {
					num = 3;
				}

				switch (num)

				{
				case 1: // VIP Plan
					VisibilityOfElementByXpath("//div[@class='hooper-pagination']//li[2]/button", 15).click();
					Thread.sleep(1500);
					choose_plan.get(num).click();
					break;

				case 2: // ESSENTIAL Plan
					VisibilityOfElementByXpath("//div[@class='hooper-pagination']//li[3]/button", 15).click();
					Thread.sleep(1500);
					choose_plan.get(num).click();
					break;

				case 3: // MONEY SAVER Plan
					VisibilityOfElementByXpath("//div[@class='hooper-pagination']//li[4]/button", 15).click();
					Thread.sleep(1500);
					choose_plan.get(num).click();
					break;

				}

				// Choose Billing Interval
				WebElement duration = VisibilityOfElementByXpath(signupModel.getPersonal_billing_interval(), 15);
				duration.click();

				// Submit
				try {
					VisibilityOfElementByXpath("//button[@class='submit-btn HSKg29-lwI4BPWKQPVBps_0']", 5).click();
				} catch (Exception e) {
					VisibilityOfElementByXpath("//div[@class='submit-container']/button", 10).click();
				}
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

		if (signupModel.getBluetooth_signup_tier().equals("paid")) {
			VisibilityOfElementByXpath("//button[contains(text(),'GOT IT')]", 10).click();
		}

	}

	public void addCreditCard() throws Exception {
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
		// Scroll until the submit button and then click on it
		List<WebElement> checkbox = getDriver().findElements(By.xpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']"));
		scroll_until_ele_not_found(checkbox);
		Thread.sleep(1000);
		checkbox.get(0).click();

//		VisibilityOfElementByXpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']", 15).click();

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
	public synchronized ArrayList<String> createDeviceFromPanel() throws Exception {
		String device_num = null;

		for (int i = 0; i < signupModel.getTotal_bought_devices(); i++) {

			// Navigate to add device page
			getDriver().navigate().to("https://stg.autobrain.com/worker/devices/new");

			// Validating add device page opened or not
			boolean isNewDevicePageFound = false;
			while (!isNewDevicePageFound) {
				isNewDevicePageFound = getDriver().findElements(By.xpath("//h1[contains(text(),'Add a new device')]"))
						.size() == 1;
			}

			// Enter phone number
			VisibilityOfElementByXpath("//textarea[@placeholder='2125550123,2125554567']", 15)
					.sendKeys(generatePhoneNumber());

			// Check-box - Phone number is not from United state or Canada
			VisibilityOfElementByXpath("//input[@id='device_international_phone_number']", 15).click();

			// Select Model
			Select sel = new Select(getDriver().findElement(By.name("device[model]")));

			if (signupModel.getBluetooth_signup_tier().equals("free")
					|| signupModel.getBluetooth_signup_tier().equals("paid")) {

				if (signupModel.isBluetooth_upgraded()) {
					// Enter cellular service type
					VisibilityOfElementByName("device[cellular_service_type]", 10).sendKeys("WYLESS");

					// Select model
					sel.selectByVisibleText("Standard");
				}

				else {
					// Enter cellular service type
					VisibilityOfElementByName("device[cellular_service_type]", 10).sendKeys("FREE_1");

					// Select model
					sel.selectByVisibleText("Autobrain_Bluetooth_1");
				}
			}

			else {
				// Enter cellular service type
				VisibilityOfElementByName("device[cellular_service_type]", 10).sendKeys("WYLESS");

				// Select model
				sel.selectByVisibleText("Standard");
			}

			// Enter Device UID (ESN)
			VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']", 15)
					.sendKeys(generateDeviceNumber());
			device_num = VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']", 15)
					.getAttribute("value");
			// Select ESN Format
			Select sel2 = new Select(getDriver().findElement(By.name("device[uid_format]")));
			sel2.selectByVisibleText("Decimal");

			// Click on create device button
			wait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();

			// Check if page broken
			try {
				if (wait(getDriver(), 2).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("//h1[text()='Whoops! It seems like something went wrong!']"))).size() == 1) {
					System.out.println("Page broken. Unable to create device number.");
					Assert.assertEquals(true, false, "Page broken!");
				}
			} catch (Exception e) {

			}

			// VALIDATE ERROR
			while (getDriver().findElements(By.id("flash_alert")).size() > 0) {
				if (getDriver().findElements(By.xpath("//div[contains(text(),'Uid has already been taken')]"))
						.size() == 1) {
					VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']", 15).clear();
					VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']", 15)
							.sendKeys(generateDeviceNumber());
					device_num = VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']", 15)
							.getAttribute("value");
					// Click on create device button
					wait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit")))
							.click();
				}

				if (getDriver().findElements(By.xpath("//div[contains(text(),' Phone number has already been taken')]"))
						.size() == 1 == true) {
					VisibilityOfElementByXpath("//textarea[@placeholder='2125550123,2125554567']", 15).clear();
					VisibilityOfElementByXpath("//textarea[@placeholder='2125550123,2125554567']", 15)
							.sendKeys(generatePhoneNumber());

					// Click on create device button
					VisibilityOfElementByName("commit", 10).click();
				}
			}

			// Adding device one by one
			signupModel.getAll_Devices_No().add(device_num);
			System.out.println(device_num);
		}

		Assert.assertTrue(VisibilityOfElementByXpath("//div[@id='flash_success']", 10).isDisplayed());
		return signupModel.getAll_Devices_No();
	}

	// ESF_Exemptions Method
	public void esfExemptionsSetup() throws Exception {

		if (signupModel.isSet_esf()) {
			// Logout registered user
			VisibilityOfElementByXpath("//div[contains(text(),'Log Out')]", 15).click();
			Thread.sleep(2000);

			// Login main user
			login.login("john@example.com", "welcome");

			// Navigate to ESF_Exemptions page
			getDriver().navigate().to("https://stg.autobrain.com/worker/esf_exemptions");

			// Enter email
			VisibilityOfElementByXpath("//input[@id='email']", 15).sendKeys(signupModel.getOwner_email());

			// Click on add button
			VisibilityOfElementByXpath("//input[@name='commit' and @value ='Add']", 15).click();

			boolean email_added;
			// validate email added
			try {
				email_added = VisibilityOfAllElementsByXpath(
						"//td[contains(text(),'" + signupModel.getOwner_email() + "')]", 15).size() == 1;
			} catch (Exception e) {
				email_added = false;
			}

			Assert.assertTrue(email_added);

			// Logout panel user
			VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]", 15).click();
			Thread.sleep(2000);

			// Navigate user to login url
			getDriver().navigate().to(url);

			// Login last registered user
			VisibilityOfElementByID("user_email", 15).sendKeys(signupModel.getOwner_email());

			// Entering password
			VisibilityOfElementByID("user_password", 15).sendKeys("welcome");

			// Click on login button
			wait(getDriver(), 25).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();

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

	public void addDevicesInCsvFile() throws Exception {

		Writer file = new FileWriter(SignupModel.csvFile);
		CSVWriter writer = new CSVWriter(file);

		// Create record
		String[] header = { "device_number" };
		writer.writeNext(header);

		// Create device
		login.login("john@example.com", "welcome");
		createDeviceFromPanel();

		for (int i = 0; i < signupModel.getAll_Devices_No().size(); i++) {
			String number = signupModel.getAll_Devices_No().get(i);
			String[] num = { number };
			writer.writeNext(num);
		}

		// Close the writer
		writer.close();
		System.out.println("Device added successfully in CSV file.");

	}

	public void createInvoice() throws Exception {

		getDriver().get("https://stg.autobrain.com/worker/retail_fulfillment/new_invoice");

		// Add Quantity
		for (int i = 0; i < signupModel.getTotal_bought_devices(); i++) {
			VisibilityOfElementByXpath("//div[@class='input-group number-picker']/span[2]/button", 15).click();
			Thread.sleep(500);
		}

		// Get invoice auto-generated number
		String invoice_id_before;
		invoice_id_before = VisibilityOfElementByXpath("//code[@class='name-preview']", 15).getText();
		invoice_id_before = invoice_id_before.replace("#", "");

		// Description
		VisibilityOfElementByID("invoice_description", 15).sendKeys("testing_" + invoice_id_before);

		// Select account type
		Select select = new Select(VisibilityOfElementByID("invoice_account_type", 15));

		String invoice_account_type = null;
		if (signupModel.getAccount_type().contains("personal")) {
			invoice_account_type = "personal";
		}

		else if (signupModel.getAccount_type().contains("business")) {
			invoice_account_type = "business";
		}

		select.selectByVisibleText(invoice_account_type);

		// Submit button
		wait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();

		// Validate invoice created
		// Get invoice auto-generated number
		String invoice_id_after = VisibilityOfElementByXpath("//code[@class='name-preview']", 15).getText();

		if (!invoice_id_before.equals(invoice_id_after)) {
			System.out.println("Invoice created successfully!");
		}

		else if (invoice_id_before.equals(invoice_id_after)) {
			Assert.assertEquals(true, false, "Invoice not created successfully!");
		}

	}

	public void submitCsvFile() throws Exception {

		// Redirect to devices page in panel
		getDriver().get("https://stg.autobrain.com/worker/retail_fulfillment/ready_for_distribution");

		// Click on upload file button
		WebElement upload_file = PresenceOfElementByXpath("//input[@name='file']", 15);

		upload_file.sendKeys(SignupModel.csvFile.getAbsolutePath());
		Thread.sleep(2000);
	}

	public void chooseInvoicePricingPlanAndDistributionChannel() throws Exception {

		// This will select the top invoice means the latest one
		VisibilityOfElementByXpath("//select[@name='invoice']/option[2]", 15).click();

		// Pricing plan
		Select choose_pricing_plan = new Select(VisibilityOfElementByXpath("//select[@name='pricing_plan']", 15));

		// Choose which pricing plan we want to select
		choose_pricing_plan.selectByVisibleText(signupModel.getPricing_plan());

		// Distribution channel
		Select distribution_channel = new Select(
				VisibilityOfElementByXpath("//select[@name='distribution_channel']", 15));

		// Choose which distribution channel we want to select
		distribution_channel.selectByVisibleText("amazon");

		// Submit Form
		VisibilityOfElementByXpath("//input[@name='commit']", 15).click();

		// Check error exist
		boolean error = true;
		try {
			error = wait(getDriver(), 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='flash_alert']")))
					.isDisplayed();
		}

		catch (Exception e) {
			error = false;
			System.out.println("Invoice added successfully for devices!");
		}

		Assert.assertEquals(error, false, "Device not added!");

		// Logout
		VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]", 15).click();
		getDriver().navigate().to(url);

	}

	// Generate Random Device Number
	public String generateDeviceNumber() {
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
	public String generatePhoneNumber() {
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

	// Email service
	public String getConfirmationCode() throws Exception {
		Thread.sleep(1500);

		getDriver().get("http://mailcatch.com");

		// Input email
		VisibilityOfElementByXpath("//input[@name='box']", 15).sendKeys(signupModel.getOwner_email());
		System.out.println("Owner email: " + signupModel.getOwner_email());

		// Click on go to check new email
		VisibilityOfElementByXpath("//input[@value='Go!']", 15).click();
		Thread.sleep(1000);

		// Open confirmation email
		List<WebElement> con_email = VisibilityOfAllElementsByXpath(
				"//a[contains(text(),'Autobrain account confirmation email')]", 10);
		con_email.get(0).click();

		// Switch to frame
		getDriver().switchTo().frame(getDriver().findElement(By.id("emailframe")));

		// Get confirmation code
		String confirmation_code = VisibilityOfElementByXpath("//td[contains(text(),'To get started')]/strong", 15)
				.getText();

		return confirmation_code;
	}

	// Generate random email
	public String generateRandomEmail() {
		// Generating random email
		Random randomGenerator = new Random();
		signupModel.setRandom_int(randomGenerator.nextInt(10000));
		String email = "user" + signupModel.getRandom_int() + "@mailcatch.com";
		SignupModel.setL_name("" + signupModel.getRandom_int());
		return email;
	}

	public void upgradeRequest() throws Exception {

		// Click on the navigation menu
		VisibilityOfElementByXpath("//div[@class='ellipsis-opener']", 10).click();
		Thread.sleep(1000);

		// Click on choose plan
		VisibilityOfElementByXpath("//a[normalize-space()='Change Plan']", 10).click();

		// Validate change plan page opened
		Assert.assertTrue(VisibilityOfElementByXpath("//h4[normalize-space()='Change Plan']", 10).isDisplayed());

		// Click on choose plan button
		VisibilityOfElementByXpath("//div[text()='VIP']//following::button[1]", 10).click();

		// Choose billing interval
		VisibilityOfElementByXpath("//div[@class='TpDbnpVtZG__uMj7UUtnd_0']/div[1]//button", 10).click();

		// Click on change plan button
		VisibilityOfElementByXpath("//button[normalize-space()='Change Plan']", 5).click();

		try {

			// Validate add credit card form appear
			Assert.assertTrue(VisibilityOfElementByXpath("//h4[text()='Add Credit Card ']", 3).isDisplayed());
			// Add credit card info
			addCreditCard();
		}

		catch (Exception e) {

		}

		// Validate add shipping address page opened
		Assert.assertTrue(
				VisibilityOfElementByXpath("//h4[normalize-space()='Add Shipping Address']", 10).isDisplayed());

		// Add shipping address details
		// Enter first name
		VisibilityOfElementByXpath("//input[@name='fname']", 15).sendKeys(SignupModel.getF_name());

		// Convert integer to string
		String l_name = String.valueOf(signupModel.getRandom_int());

		// Enter last name
		VisibilityOfElementByXpath("//input[@name='lname']", 15).sendKeys(l_name);

		// Enter email address
		VisibilityOfElementByXpath("//input[@name='email']", 15).sendKeys(signupModel.getOwner_email());

		// Enter street address
		VisibilityOfElementByXpath("//input[@name='address']", 15).sendKeys(SignupModel.getStreet());

		// Enter city
		VisibilityOfElementByXpath("//input[@name='city']", 15).sendKeys(SignupModel.getCity());

		// Select state
		Select state = new Select(VisibilityOfElementByXpath("//select[@placeholder='State']", 15));
		state.selectByVisibleText(SignupModel.getState());

		// Enter zip code
		VisibilityOfElementByXpath("//input[@name='shipping zip']", 15).sendKeys(SignupModel.getZip());

		// Scroll until the submit button and then click on it
		List<WebElement> ele = getDriver().findElements(By.xpath("//button[contains(text(),'Submit')]"));
		scroll_until_ele_not_found(ele);
		Thread.sleep(1500);
		ele.get(0).click();

		// Check terms and conditions check-box
		VisibilityOfElementByXpath("//div[@class='same-shipping']//span", 10).click();

		// Submit
		VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 10).click();

		// Validate upgrade request successfully
		VisibilityOfElementByXpath("//button[contains(text(),'GOT IT')]", 10).click();

		// Logout current user
		login.logout();

		// Login worker panel
		login.login("john@example.com", "welcome");

		// Navigate to order to ship page
		getDriver().navigate().to("https://stg.autobrain.com/worker/online_fulfillment/invoices_to_ship#");

		// Validate page loaded
		Assert.assertTrue(VisibilityOfElementByXpath("//h3[normalize-space()='Orders to ship']", 10).isDisplayed());

		// Validate order received
		String last_name = VisibilityOfElementByXpath("//tr[1]/td[2]", 10).getText();
		Assert.assertTrue(last_name.equals(l_name), "Request for an upgrade unit order not received!");

	}

}
