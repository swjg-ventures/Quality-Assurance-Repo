package com.pages;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Signup extends Login {

	String // For card details
	F_name = "Test", L_name = "Demo", Street = "935 Gravier Place, Suite 1160, New Orleans, LA.", City = "Boca",
			State = "Florida", Zip = "70112", Phone = "1236547890", Card_Name = "Demo card", Card_CVV = "555",
			Card_No = "4242424242424242", expected_product_price,

			// For vehicle profile
			carname1 = "Demo_Car_Test", caricon1 = "//div[@class='grid']/div[1]",
			status1 = "//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div", year1 = "2015", make1 = "SKODA",
			VIN1 = "DHSJ879373J738H", carname2 = "Demo_Car_Test", caricon2 = "//div[@class='grid']/div[10]",
			status2 = "//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div", year2 = "2018", make2 = "VOLVO",
			VIN2 = "MCSJ879373J736G", model_selected, Insurance_Eff_Date, Insurance_Exp_Date, car_Reg_exp, DeviceName,
			Signup_New_Email, entered_email, code, Device_Num, new_entered_email;

	int randomInt, Total_bought_devices, model1 = 2, model2 = 6;

	// Store email which we used during buy the product on
	ArrayList<String> ar = new ArrayList<String>();

	// Store number of all bought devices (Used in create_device_by_panel)
	ArrayList<String> All_Devices_No = new ArrayList<String>();

	boolean reg_success, shipping_label_box, Is_Next_Page_Correct, p_load, credit_card, isNewDevicePageFound,
			safty_modes, alert_setting, roadside, home, Device_Added, card_error_msg, email_added,
			billing_interval_page_title;

	// Shuffling variables
	// Car name
	String Scarname[] = { carname1, carname2 };
	String carname = Scarname[new Random().nextInt(Scarname.length)];

	// Car VIN
	String Svin[] = { VIN1, VIN2 };
	String VIN = Svin[new Random().nextInt(Scarname.length)];

	// Car icon
	String Scaricon[] = { caricon1, caricon2 };
	String caricon = Scaricon[new Random().nextInt(Scaricon.length)];

	// Year
	String Syear[] = { year1, year2 };
	String year = Syear[new Random().nextInt(Syear.length)];

	// Make
	String Smake[] = { make1, make2 };
	String make = Smake[new Random().nextInt(Smake.length)];

	// Model
	int Smodel[] = { model1, model2 };
	int model = Smodel[new Random().nextInt(Smodel.length)];

	String licenceno1 = "TY78",
			effectivedate1 = "//div[@class='text-input'][2]/div/div[2]/div/span[contains(text(),'25')]",
			expiredate1 = "//div[@class='text-input'][3]/div/div[2]/div/span[contains(text(),'28')]",
			insurancecmpy1 = "Demo", policyno1 = "55512";
	String licenceno2 = "TY89",
			effectivedate2 = "//div[@class='text-input'][2]/div/div[2]/div/span[contains(text(),'26')]",
			expiredate2 = "//div[@class='text-input'][3]/div/div[2]/div/span[contains(text(),'27')]",
			insurancecmpy2 = "Test", policyno2 = "44125";

	// License
	String Slicenceno[] = { licenceno1, licenceno2 };
	String licenceno = Slicenceno[new Random().nextInt(Slicenceno.length)];

	// Effective date
	String Seffectivedate[] = { effectivedate1, effectivedate2 };
	String effectivedate = Seffectivedate[new Random().nextInt(Seffectivedate.length)];

	// Expire date
	String Sexpiredate[] = { expiredate1, expiredate2 };
	String expiredate = Sexpiredate[new Random().nextInt(Sexpiredate.length)];

	// Insurance company
	String Sinsurancecmpy[] = { insurancecmpy1, insurancecmpy2 };
	String insurancecmpy = Sinsurancecmpy[new Random().nextInt(Sinsurancecmpy.length)];

	// Insurance company
	String Spolicyno[] = { policyno1, policyno2 };
	String policyno = Spolicyno[new Random().nextInt(Spolicyno.length)];

	// Add device button
	@FindBy(xpath = "//a[contains(text(),'Add device #')]")
	List<WebElement> add_device_btn;

	// Number of input devices
	@FindBy(xpath = "//input[@placeholder='Enter device number']")
	List<WebElement> no_of_input_devices;

	Properties prop;

	// MAIN METHOD
	public void signup(String accountType) throws Exception {
		if (accountType.contains("personal")) {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("src\\main\\java\\Library\\personal.properties");
			prop.load(fis);
		}

		if (accountType.contains("business")) {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("src\\main\\java\\Library\\business.properties");
			prop.load(fis);
		}

		orderDeviceFromWebSite();

		// Clicking on sign-up button
		VisibilityOfElementByXpath("//a[contains(text(),'SIGN UP')]", 15).click();

		// Entering first-name
		VisibilityOfElementByID("user_first_name", 15).clear();
		VisibilityOfElementByID("user_first_name", 15).sendKeys("Test");

		// Entering last-name
		VisibilityOfElementByID("user_last_name", 15).clear();
		VisibilityOfElementByID("user_last_name", 15).sendKeys("" + randomInt);

		// Entering email
		VisibilityOfElementByID("user_email", 15).clear();

		// Change sign-up email which will different from bought product email
		if (prop.getProperty("Want_To_Change_Signup_Email").contains("true")) {
			String new_email = GenerateRandomEmail();
			VisibilityOfElementByID("user_email", 15).sendKeys(new_email);
			System.out.println("Signup Email Has Been Changed --> " + new_email);

			// Update last name
			VisibilityOfElementByID("user_last_name", 15).clear();
			VisibilityOfElementByID("user_last_name", 15).sendKeys("" + randomInt);
		} else {
			VisibilityOfElementByID("user_email", 15).sendKeys(ar.get(0));
		}

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
		Thread.sleep(2500);
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

		if (Total_bought_devices == 1) {

			// Validating confirmation key page found
			String expected_next_page = "Resend Confirmation Email";
			try {
				Is_Next_Page_Correct = VisibilityOfElementByXpath("//a[contains(text(),'Resend Confirmation Email')]",
						15).getText().contains(expected_next_page);
			} catch (Exception e) {
				Is_Next_Page_Correct = false;
			}

			Assert.assertEquals(Is_Next_Page_Correct, true, "Confirmation key page not found!");

			// YOPMAIL.COM
			Thread.sleep(2000);

			driver.get("http://www.yopmail.com/en/");

			Thread.sleep(2000);

			// Enter registered email id
			
			if (new_entered_email == null) {
			
				VisibilityOfElementByXpath("//input[@id='login']", 15)
						.sendKeys(entered_email);
				;
				System.out.println("Input entered_email in mailinator.com " + entered_email);
			} 
			
			else {
				new_entered_email = new_entered_email.replace("@maildrop.cc", "");
				VisibilityOfElementByXpath("//input[@id='login']", 15)
						.sendKeys(new_entered_email);
				;
				System.out.println("Input new_entered_email in mailinator.com " + new_entered_email);
			}

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
			String code = VisibilityOfElementByXpath("//td[contains(text(),'To get started')]/strong", 15).getText();

			driver.get(url);

			// Input verification code
			VisibilityOfElementByXpath("//input[@placeholder='Confirmation Code']", 15).sendKeys(code);

			// Click on submit button
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
			Thread.sleep(2000);
		}

		if (!p_load) {
			try {
				p_load = VisibilityOfElementByXpath("//div[contains(text(),'Is Your')]/following-sibling::div//span",
						15).isDisplayed();
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ESF_Exemptions
		if (ESFExemptions()) {
			boolean check_box;
			driver.navigate().refresh();
			Thread.sleep(4000);
			try {
				check_box = PresenceOfAllElementsByXpath("//div[contains(text(),'Upon canceling')]", 15).size() == 1;
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

		Thread.sleep(2500);
		softassert.assertAll();

	}

	private ArrayList<String> orderDeviceFromWebSite() throws Exception {
		// Initializing elements
		PageFactory.initElements(driver, this);

		// Generating random email
		String Email = GenerateRandomEmail();

		// Calling login method
		login("john@example.com", "welcome");

		// Click on Menu button
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.className("hamburger-container")))
				.click();

		// Click on Download App
		VisibilityOfElementByXpath("//span[contains(text(),'Get Another Device')]", 15).click();
		Thread.sleep(2000);

		// Checking how many windows are opened currently
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		// Switching to Buy now page
		driver.switchTo().window(tabs.get(1));

		// Open drop-down to select number of devices
		Select s = new Select(VisibilityOfElementByXpath("//div[@class='quanities-container']/select", 15));
		s.selectByVisibleText(prop.getProperty("no_of_devices"));

		// Select Account Type BUSINESS OR PERSONAL
		Select ss = new Select(VisibilityOfElementByXpath("//div[@class='account-types-container']/select", 15));
		ss.selectByVisibleText(prop.getProperty("account_type"));

		Thread.sleep(2000);
		// Store expected product price
		expected_product_price = VisibilityOfElementByXpath("//span[@class='price-cost']", 15).getText();

		// Click on add to cart button
		VisibilityOfElementByXpath("//button[contains(text(),'Add To Cart')]", 15).click();

		// Click on continue to shopping info button
		VisibilityOfElementByXpath("//button[contains(text(),'Continue to Shipping Info')]", 15).click();

		// (SHIPPING INFO) Verify new page opened or not
		boolean nextpage = VisibilityOfAllElementsByXpath("//h5[contains(text(),'Shipping  Information')]", 15)
				.size() == 1;
		softassert.assertEquals(nextpage, true);

		// Enter first name
		VisibilityOfElementByXpath("//input[@placeholder='First Name']", 15).sendKeys(F_name);

		// Enter last name
		VisibilityOfElementByXpath("//input[@placeholder='Last Name']", 15).sendKeys(L_name);

		// Enter email address
		VisibilityOfElementByXpath("//input[@placeholder='Email Address']", 15).sendKeys(Email);
		System.out.println("Entered Email During Bought The Product " + "--> " + Email);

		// Enter street address
		VisibilityOfElementByXpath("//input[@placeholder='Street Address']", 15).sendKeys(Street);

		// Enter city
		VisibilityOfElementByXpath("//input[@placeholder='City']", 15).sendKeys(City);

		// Select state
		Select state = new Select(VisibilityOfElementByXpath("//select[@placeholder='State']", 15));
		state.selectByVisibleText(State);

		// Enter zip code
		VisibilityOfElementByXpath("//input[@placeholder='Zip Code']", 15).sendKeys(Zip);

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
		Assert.assertEquals(A_address, Street);

		// Get city name and verify
		String A_City = VisibilityOfElementByXpath("//input[@placeholder='City']", 15).getAttribute("value");
		Assert.assertEquals(A_City, City);

		// Get state name and verify
		String A_state = VisibilityOfElementByXpath("//Select[@placeholder='State']/option[13]", 15).getText().trim();
		Assert.assertEquals(A_state, State);

		// Get city name and verify
		VisibilityOfElementByXpath("//input[@placeholder='Phone Number']", 15).sendKeys(Phone);

		// Click on continue to card button
		VisibilityOfElementByXpath("//button[contains(text(),'Continue to Card Info')]", 15).click();

		// PAYMENT METHOD (Verify new page opened or not)
		boolean nextpage3 = VisibilityOfAllElementsByXpath("//h5[contains(text(),'Payment  Method')]", 15).size() == 1;
		Assert.assertEquals(nextpage3, true);

		// Enter card name
		VisibilityOfElementByXpath("//input[@placeholder='Name On Card']", 15).sendKeys(Card_Name);

		// Enter card number
		VisibilityOfElementByXpath("//input[@placeholder='Card Number']", 15).sendKeys(Card_No);

		// Enter card CVV
		VisibilityOfElementByXpath("//input[@placeholder='CVV']", 15).sendKeys(Card_CVV);

		// Select card month
		Select month = new Select(VisibilityOfElementByXpath("//select[@placeholder='Month']", 15));
		month.selectByVisibleText("12");

		// Select card year
		Select year = new Select(VisibilityOfElementByXpath("//select[@placeholder='Year']", 15));
		year.selectByVisibleText("2035");

		// Enter zip code
		boolean isZipCodeFound = VisibilityOfElementByXpath("//input[@placeholder='Zip Code']", 15)
				.getAttribute("value").equalsIgnoreCase(Zip);
		Assert.assertEquals(isZipCodeFound, true, "Zip code not fetch automatically!");

		// Click on submit my order button
		VisibilityOfElementByXpath("//button[contains(text(),'Submit My Order')]", 15).click();

		Thread.sleep(2000);

		// Verify order placed or not
		boolean order_placed = VisibilityOfAllElementsByXpath("//h2[contains(text(),'Thank You For Your Order')]", 30)
				.size() == 1;
		softassert.assertEquals(order_placed, true);

		// Verify order quantity
		boolean quantiy = VisibilityOfAllElementsByXpath("//p[contains(text(),'Your package will include "
				+ prop.getProperty("no_of_devices") + " Autobrain device as well as a 5 step quick start guide.')]", 15)
						.size() == 1;

		softassert.assertEquals(quantiy, true, "Ordered quantiy not matching with order receipt!");

		// Verify actual product price according to selected quantity
		String actual_product_price = VisibilityOfElementByXpath(
				"//div[contains(text(),'Total:')]/following-sibling::div", 15).getText();

		softassert.assertEquals(actual_product_price, expected_product_price,
				"Expected product price not matching with actual product price!");

		// Closing current window
		driver.switchTo().window(tabs.get(1)).close();
		Thread.sleep(1000);

		// Switching to main window
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(1000);

		ar.clear();
		ar.add(Email);

		// Navigate to devices page
		driver.navigate().to("https://stg.autobrain.com/worker/devices");

		// Store user buy device ID
		String user_buy_device = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();

		// Create device number for customer
		create_device_by_panel();

		// Switch to Orders To Ship page
		driver.navigate().to("https://stg.autobrain.com/worker/online_fulfillment/invoices_to_ship");

		// Click on Add Device button to add the manually created device
		add_device_btn.get(0).click();

		// Validate enter field box opened
		boolean msg_prompt = PresenceOfAllElementsByXpath(
				"//h4[contains(text(),'Add a device number to the following cars')]", 15).size() != 0;

		Assert.assertEquals(msg_prompt, true, "Message alert not prompted!");

		// Enter Device number
		Thread.sleep(2500);

		for (int i = 0; i < no_of_input_devices.size(); i++) {
			TypeInFieldSlowly(no_of_input_devices.get(i), All_Devices_No.get(i));
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
				add_device_btn.get(0).click();
				Thread.sleep(1500);

				// Enter the custom created device number
				// Reset i to 0 and start entering the device ID from first index
				i = 0;
				TypeInFieldSlowly(no_of_input_devices.get(i), All_Devices_No.get(i));
				Thread.sleep(3000);
			}

		}

		// Click on Save button
		List<WebElement> btn = driver.findElements(By.xpath("//button[contains(text(),'Save changes')]"));
		btn.get(0).click();

		// Validate device added
		try {
			Device_Added = VisibilityOfAllElementsByXpath("//tbody/tr[1]//td[8]/a[contains(text(),'Device #')]", 15)
					.size() == 1;
		} catch (Exception e) {
			Device_Added = false;
			e.getStackTrace();
		}

		Assert.assertEquals(Device_Added, true, "Device not added!");

		Thread.sleep(1500);

		// Click on Print/View shipping label
		VisibilityOfElementByXpath("//tbody/tr[1]//td[9]/a", 15).click();

		// Waiting until the message box not opened
		try {
			shipping_label_box = VisibilityOfElementByXpath("//h4[contains(text(),'Shipping Label')]", 15)
					.isDisplayed();
		} catch (Exception e) {
			shipping_label_box = false;
			e.printStackTrace();
		}

		Assert.assertEquals(shipping_label_box, true, "Print/View shipping label prompt box not opened!");

		// Close print label
		List<WebElement> close_print_msgbox = PresenceOfAllElementsByXpath("//button[@class='close']", 15);
		Thread.sleep(2000);
		close_print_msgbox.get(2).click(); // Thread.sleep(2000);

		// Click on Mark As Shipped
		List<WebElement> mark_as_shipped_btn = PresenceOfAllElementsByXpath("//a[contains(text(),'Mark as shipped')]",
				25);
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

		String device_no = driver.findElement(By.xpath("//tbody/tr[2]/td[1]")).getText();

		boolean user_device_updated = user_buy_device != device_no;
		Assert.assertEquals(user_device_updated, true, "User bought device not updated!");

		VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]", 15).click();
		Thread.sleep(2000);
		driver.navigate().to("https://stg.autobrain.com");
		softassert.assertAll();
		return ar;
	}

	// Step 1 will add all car info
	public void Step1(String device_no) throws Exception {

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
		Select y = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][1]/div[2]/select")));
		y.selectByVisibleText(year);
		Thread.sleep(2000);

		// Car Make
		Select m = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][2]/div[2]/select")));
		m.selectByVisibleText(make);
		Thread.sleep(2000);

		// Car Model
		Select mo = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][3]/div[2]/select")));
		mo.selectByIndex(model);
		model_selected = mo.getFirstSelectedOption().getText().trim();
		Thread.sleep(1000);

		// Fill Up Insurance and Registration Forms
		VehicleProfile v = new VehicleProfile();
//		v.Ins_Reg_Forms();

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
			VisibilityOfElementByXpath("//span[contains(text(),'Continue')]", 15).click();

		}

		catch (Exception e) {
			// Submit
			VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 15).click();
		}

		// VALIDATE NEXT PAGE (ADD CREDIT CARD OPENED)
		if (!billing_interval_page_title) {
			if (prop.get("account_type").equals("Autobrain Business")) {
				billing_interval_page_title = wait(driver, 30)
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//h4[contains(text(),'Choose Billing Interval')]")))
						.isDisplayed();
			}

			if (prop.get("account_type").equals("Autobrain Family")) {
				billing_interval_page_title = wait(driver, 30)
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//h4[contains(text(),'Choose Plan')]")))
						.isDisplayed();
			}

			Assert.assertEquals(billing_interval_page_title, true, "Billing interval page title not found!");
		}

		if (billing_interval_page_title) {
			ChoosePricingPlanAndAddCardDetails();
		}

	}

	// This step will add all card details and will also choose pricing plan
	private void ChoosePricingPlanAndAddCardDetails() throws Exception {

		// BUSINESS PLAN
		if (prop.get("account_type").equals("Autobrain Business")) {

			// Choose Billing Interval
			WebElement billing_interval = PresenceOfElementByXpath(prop.getProperty("business_plan_interval"), 15);
			billing_interval.click();
			Thread.sleep(2000);
		}

		// FAMILY PLAN
		if (prop.get("account_type").equals("Autobrain Family")) {
			// Choose Plan
			WebElement choose_plan = VisibilityOfElementByXpath(prop.getProperty("choose_plan"), 15);
			choose_plan.click();

			// Choose Billing Interval
			WebElement duration = VisibilityOfElementByXpath(prop.getProperty("choose_billing_interval"), 15);
			duration.click();
			Thread.sleep(4000);
		}

		try {
			// Check continue button is available
			wait(driver, 2)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Continue']")))
					.click();
		}

		catch (Exception e) {
			System.out.println("Credit card page split in two pages!");
		}

		// Enter First name
		VisibilityOfElementByXpath("//input[@placeholder='First Name']", 15).sendKeys("Jhon");

		// Enter Last name
		VisibilityOfElementByXpath("//input[@placeholder='Last Name']", 15).sendKeys("example");

		// Enter Billing Address
		VisibilityOfElementByXpath("//input[@placeholder='Billing Address']", 15).sendKeys(Street);

		// Enter City
		VisibilityOfElementByXpath("//input[@placeholder='City']", 15).sendKeys(City);
		Thread.sleep(2000);

		// Select State
		Select state = new Select(VisibilityOfElementByXpath("//option[contains(text(),'State')]//parent::select", 15));
		state.selectByVisibleText(State);

		// Add card number
		VisibilityOfElementByXpath("//input[@placeholder='Card Number']", 15).sendKeys(Card_No);

		// Select Month
		Select month = new Select(VisibilityOfElementByXpath("//option[contains(text(),'Month')]//parent::select", 15));
		month.selectByIndex(11);
		Thread.sleep(2000);

		// Select Year
		Select year = new Select(VisibilityOfElementByXpath("//option[contains(text(),'Year')]//parent::select", 15));
		year.selectByIndex(8);

		// Enter CVV
		VisibilityOfElementByXpath("//input[@placeholder='CVV']", 15).sendKeys(Card_CVV);

		// Enter Zip Code
		VisibilityOfElementByXpath("//input[@placeholder='Zip Code']", 15).sendKeys(Zip);

		// Click on check-box (Make This Your Primary Card)
		VisibilityOfElementByXpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']", 15).click();

		// Click on Save button
		VisibilityOfElementByXpath("//button[contains(text(),'Save')]", 15).click();

		// Validate enter card details accepted or not
		try {
			card_error_msg = wait(driver, 5).until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='flash-message text-center error']")))
					.size() == 1;
		} catch (Exception e) {

		}
		Assert.assertEquals(card_error_msg, false, "Found error while trying to submit the card details!");

		// Validate Step 2 page opened
		try {
			credit_card = VisibilityOfAllElementsByXpath("//h4[contains(text(),'Driver Setup')]", 20).size() == 1;
		} catch (Exception e) {

		}
		Assert.assertEquals(credit_card, true, "Monitor and Driver page not found!");
		Thread.sleep(2000);
	}

	// Step 2 will add monitor driver details
	public void Step2() throws Exception {

		if (prop.get("account_type").equals("Autobrain Business")) {
			System.out.println("This is Business Plan");
		}

		if (prop.get("account_type").equals("Autobrain Familyss")) {
			// Click on Add new monitor button
			VisibilityOfElementByXpath("//button[contains(text(),'Add New Monitor')]", 15).click();

			// Validate form open
			boolean form_open = VisibilityOfAllElementsByXpath("//label[contains(text(),'Monitor First Name')]", 15)
					.size() == 1;
			Assert.assertEquals(form_open, true, "Add Monitor form not opened!");

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
			VisibilityOfElementByXpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']", 15).click();

			// Submit button
			VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 15).click();

			// Verify success message after click on submit button
			boolean success_msg = VisibilityOfAllElementsByXpath("//div[@class='flash-message text-center success']",
					15).size() == 1;
			Assert.assertEquals(success_msg, true, "Succes message not appear!");

			// Verify Monitor added in drop-down list (Who Drives This Car The Most?)
			Select sel = new Select(driver.findElement(By.xpath("//select[@class='select']")));
			try {
				sel.selectByVisibleText("Test_Monitor Demo");
			} catch (Exception e) {
				softassert.assertEquals(false, true, "Added Monitor not found in drop-down list!");
			}

			// Added Monitor should also appear in List Of All Monitors And Drivers
			boolean list_mon = VisibilityOfAllElementsByXpath("//h4[contains(text(),'Test_Monitor Demo')]", 15)
					.size() == 1;
			softassert.assertEquals(list_mon, true, "Added Monitor not found in List Of All Monitors And Drivers!");

			// Closing the Monitor form
			VisibilityOfElementByXpath("//button[contains(text(),'Cancel')]", 15).click();

			// ADD DRIVER
			// Click on Add New Driver button
			VisibilityOfElementByXpath("//button[contains(text(),'Add New Driver')]", 15).click();

			// Validate form open
			boolean form_open2 = VisibilityOfAllElementsByXpath("//label[contains(text(),'Driver First Name')]", 15)
					.size() == 1;
			Assert.assertEquals(form_open2, true, "Add Driver form not opened!");

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
			VisibilityOfElementByXpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']", 15).click();

			// Submit button
			VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 15).click();

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

		// Finally click on Save and Go To Next Step button
		Thread.sleep(2000);

		try {
			wait(driver, 3).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Save and Go To Next Step')]")))
					.click();
		}

		catch (Exception e) {
			VisibilityOfElementByXpath("//span[contains(text(),'Continue')]", 15).click();
		}

		// Validate Step 3 page opened

		try {
			if (prop.getProperty("account_type").contains("Autobrain Business")) {
				safty_modes = VisibilityOfAllElementsByXpath("//h4[contains(text(),'Introduction to Modes')]", 15)
						.size() == 1;
			}

			else if (prop.getProperty("account_type").contains("Autobrain Family")) {
				safty_modes = VisibilityOfAllElementsByXpath("//h4[contains(text(),'Introduction to Safety Modes')]",
						15).size() == 1;
			}
		}

		catch (Exception e) {

		}

		// Validating Step 3 form appear
		Assert.assertEquals(safty_modes, true, "Step 3 title not found!");

	}

	// Step 3 Safety Mode
	public void Step3() throws Exception {
		Thread.sleep(2000);
		VisibilityOfElementByXpath("//a[@class='hide-below-500 nav-button']/span", 15).click();

		// Validate Step 4 page opened
		try {
			alert_setting = VisibilityOfAllElementsByXpath("//h4[contains(text(),'Alert Settings')]", 20).size() == 1;
		}

		catch (Exception e) {
			System.out.println("Alert Settings page not found.");
		}

		// Validating Step 4 form appear
		Assert.assertEquals(alert_setting, true, "Step 4 title not found!");
	}

	// Step 4 Alert Setting
	public void Step4() throws Exception {
		Thread.sleep(2000);

		// TURN ON LOW FUEL NOTIFICATIONS ALERTS
		VisibilityOfElementByXpath("//span[text()='Low Fuel Notifications']/following-sibling::div/div/div", 10)
				.click();
		Thread.sleep(2000);

		// Toggle status
		String fuel_noti_toggle_status = VisibilityOfElementByXpath(
				"//span[text()='Low Fuel Notifications']/following-sibling::div/div/div/span", 10).getText();

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
				"//span[contains(text(),'50%')]/following-sibling::div/div/span", 15).getText().contains("ON");

		Assert.assertEquals(is_percentage_set_to_50, true,
				"Unable to set fuel percentage to 50! Status not turned ON.");

		// Click on all alert settings button (Going back)
		VisibilityOfElementByXpath("//button[text()='All Alert Settings']", 10).click();

		// Click on Next page button
		try {
			wait(driver, 2).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Save and Go To')]"))).click();
		}

		catch (Exception e) {
			VisibilityOfElementByXpath("//span[contains(text(),'Continue')]", 15).click();
		}

		// Validate Step 5 page opened
		try {
			roadside = VisibilityOfAllElementsByXpath("//h4[contains(text(),'Roadside Emergency Card')]", 15)
					.size() == 1;
		}

		catch (Exception e) {
			System.out.println("Roadside Emergency Card page not found.");
		}

		// Validating Step 5 form appear
		Assert.assertEquals(roadside, true, "Step 5 title not found!");
	}

	// Step 5 Done
	public void Done() throws Exception {
		Thread.sleep(2000);
		// Click on Finish button
		VisibilityOfElementByXpath("//a[contains(text(),'Finish')]", 15).click();

		// Verify user redirected to home page or not
		try {
			home = VisibilityOfAllElementsByXpath("//li[contains(text(),'You are now ready to drive!')]", 15)
					.size() == 1;
		}

		catch (Exception e) {
			System.out.println("Home page not found.");
		}

		Assert.assertEquals(home, true, "Home page not found!");

		try {
			Thread.sleep(3000);
			List<WebElement> el = PresenceOfAllElementsByXpath(
					"//h4[contains(text(),'Welcome')]/following-sibling::div/button", 15);
			el.get(1).click();
		}

		catch (Exception e) {
			System.out.println("Welcome to autobrain popup not found");
		}

		try {
			reg_success = VisibilityOfAllElementsByXpath("//span[contains(text(),'Searching')]", 15).size() == 1;
		}

		catch (Exception e) {
			System.out.println(
					"NOT FOUND--> Your Autobrain device may take up to 12 hours and 3 drives to sync with your car.");
		}

		Assert.assertEquals(reg_success, true, "Not Found. Searching vehicle tripe...");
	}

	// CREATE DEVICE NUMBER FROM WORKER PANEL
	private ArrayList<String> create_device_by_panel() throws Exception {
		String device_num = null;

		String total_bought_devices = prop.getProperty("no_of_devices");
		Total_bought_devices = Integer.parseInt(total_bought_devices);

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
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
				}
			}

			// Adding device one by one
			All_Devices_No.add(device_num);
			System.out.println(device_num);
		}

		return All_Devices_No;
	}

	// ESF_Exemptions Method
	public boolean ESFExemptions() throws Exception {

		if (prop.getProperty("ESF_exemptions").contains("true")) {
			// Logout registered user
			VisibilityOfElementByXpath("//div[contains(text(),'Log Out')]", 15).click();
			Thread.sleep(2000);

			// Login main user
			login("john@example.com", "welcome");

			// Navigate to ESF_Exemptions page
			driver.navigate().to("https://stg.autobrain.com/worker/esf_exemptions");

			// Enter email
			if (new_entered_email == null) {
				VisibilityOfElementByXpath("//input[@id='email']", 15).sendKeys(entered_email);
			} else {
				VisibilityOfElementByXpath("//input[@id='email']", 15).sendKeys(new_entered_email);
			}

			// Click on add button
			VisibilityOfElementByXpath("//input[@name='commit' and @value ='Add']", 15).click();

			// validate email added
			if (new_entered_email == null) {

				try {
					email_added = VisibilityOfAllElementsByXpath("//td[contains(text(),'" + entered_email + "')]", 15)
							.size() == 1;
				} catch (Exception e) {
					email_added = false;
				}
			}

			else {
				try {
					email_added = VisibilityOfAllElementsByXpath("//td[contains(text(),'" + new_entered_email + "')]",
							15).size() == 1;
				} catch (Exception e) {
					email_added = false;
				}
			}

			// Logout panel user
			VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]", 15).click();
			Thread.sleep(2000);

			// Navigate user to login url
			driver.navigate().to(url);

			// Login last registered user
			if (new_entered_email == null) {
				VisibilityOfElementByID("user_email", 15).sendKeys(entered_email);
			} else {
				VisibilityOfElementByID("user_email", 15).sendKeys(new_entered_email);
			}

			// Entering password
			VisibilityOfElementByID("user_password", 15).sendKeys("welcome");

			// Click on login button
			wait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();

			boolean create_a_vehicle_profile;
			try {
				create_a_vehicle_profile = PresenceOfAllElementsByXpath(
						"//h4[contains(text(),'Create a Vehicle Profile')]", 15).size() == 1;
			}

			catch (Exception e) {
				create_a_vehicle_profile = false;
			}

			Assert.assertEquals(create_a_vehicle_profile, true, "Step 1 page not found!");
			return true;

		}

		else {
			return false;
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
		randomInt = randomGenerator.nextInt(10000);
		String Email = "junk" + randomInt + "@yopmail.com";
		DeviceName = "test" + randomInt;
		return Email;
	}

}