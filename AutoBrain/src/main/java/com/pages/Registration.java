package com.pages;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class Registration extends Login {
	String F_name = "Test", L_name = "Demo", Street = "935 Gravier Place, Suite 1160, New Orleans, LA.", City = "Boca", State = "Florida", Zip = "70112", 
			Phone = "1236547890", Card_Name = "Demo card",
			Card_CVV = "555", Card_No = "4242424242424242";	
	 
	ArrayList<String> ar = new ArrayList<String>();

	
	boolean add_credit_error, reg_success, shipping_label_box;
	//Generating random email
	Random randomGenerator = new Random();  
	int randomInt = randomGenerator.nextInt(1000);
	String entered_email, code, Device_Num;
	boolean p_load, add_creadit_card_page, credit_card, new_device, safty_modes, alert_setting,roadside, home;
	
	String carname1 = "Demo_Car_Test", caricon1 = "//div[@class='grid']/div[1]", status1 = "//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div", 
			year1 = "2015", make1 = "SKODA", VIN1 = "DHSJ879373J738H"; int model1 = 2;	
			
	String carname2 = "Demo_Car_Test", caricon2 = "//div[@class='grid']/div[10]", status2 = "//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div", 
			year2 = "2018", make2 = "VOLVO", VIN2 = "MCSJ879373J736G"; int model2 = 6;

	String model_selected, Insurance_Eff_Date, Insurance_Exp_Date, car_Reg_exp;
	
	//Shuffling variables		
	
	//Car name
	String Scarname[]  = {carname1, carname2};
	String carname = Scarname[new Random().nextInt(Scarname.length)];
	
	//Car VIN
	String Svin[]  = {VIN1, VIN2};
	String VIN = Svin[new Random().nextInt(Scarname.length)];
	
	
	//Car icon
	String Scaricon[]  = {caricon1, caricon2};
	String caricon = Scaricon[new Random().nextInt(Scaricon.length)];
	
	
	//Year
	String Syear[]  = {year1, year2};
	String year = Syear[new Random().nextInt(Syear.length)];
	
	//Make
	String Smake[]  = {make1, make2};
	String make = Smake[new Random().nextInt(Smake.length)];
	
	//Model
	int Smodel[]  = {model1, model2};
	int model = Smodel[new Random().nextInt(Smodel.length)];
	
	
	String licenceno1 = "TY78", effectivedate1 = "//div[@class='text-input'][2]/div/div[2]/div/span[contains(text(),'25')]", expiredate1 = "//div[@class='text-input'][3]/div/div[2]/div/span[contains(text(),'28')]", insurancecmpy1 = "Demo", policyno1 = "55512";
	String licenceno2 = "TY89", effectivedate2 = "//div[@class='text-input'][2]/div/div[2]/div/span[contains(text(),'26')]", expiredate2 = "//div[@class='text-input'][3]/div/div[2]/div/span[contains(text(),'27')]", insurancecmpy2 = "Test", policyno2 = "44125";
	
	//License
	String Slicenceno[]  = {licenceno1, licenceno2};
	String licenceno = Slicenceno[new Random().nextInt(Slicenceno.length)];
	
	//Effective date
	String Seffectivedate[]  = {effectivedate1, effectivedate2};
	String effectivedate = Seffectivedate[new Random().nextInt(Seffectivedate.length)];
	
	//Expire date
	String Sexpiredate[]  = {expiredate1, expiredate2};
	String expiredate = Sexpiredate[new Random().nextInt(Sexpiredate.length)];
	
	//Insurance company
	String Sinsurancecmpy[] = {insurancecmpy1, insurancecmpy2};
	String insurancecmpy =Sinsurancecmpy[new Random().nextInt(Sinsurancecmpy.length)];
	
	
	//Insurance company
	String Spolicyno[] = {policyno1, policyno2};
	String policyno =Spolicyno[new Random().nextInt(Spolicyno.length)];

	
	//Add device button
	@FindBy(xpath = "//a[contains(text(),'Add device #')]")
	List<WebElement> add_device_btn;
	
	
	
	
	
	
	
	 public ArrayList<String> BuyDevice() throws Exception {
		PageFactory.initElements(driver, this);
		Random randomGenerator = new Random(); 
		int randomInt = randomGenerator.nextInt(1000);
		String Email = "demouser"+randomInt+"@mailinator.com";
		
		// Calling login method
		Login l = new Login();
		l.login();
	
		
		// Click on Menu button
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.className("hamburger-container")))
				.click();

		// Click on Download App
		wait(driver, 20).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Get Another Device')]"))).click();
		Thread.sleep(2000);

		// Checking how many windows are opened currently
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		// Switching to Buy now page
		driver.switchTo().window(tabs.get(1));

		// Open drop-down to select number of devices
		Select s = new Select(wait(driver, 20).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='quanities-container']/select"))));
		s.selectByVisibleText("1");

		// Click on add to cart button
		wait(driver, 20).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]")))
				.click();

		

		// Click on continue to shopping info button
		wait(driver, 20)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Continue to Shipping Info')]")))
				.click();

		// (SHIPPING INFO) Verify new page opened or not
		boolean nextpage = wait(driver, 20)
				.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//h5[contains(text(),'Shipping  Information')]")))
				.size() == 1;
		softassert.assertEquals(nextpage, true);

		// Enter first name
		wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='First Name']")))
				.sendKeys(F_name);

		// Enter last name
		wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Last Name']")))
				.sendKeys(L_name);

		// Enter email address
		wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Email Address']")))
				.sendKeys(Email);

		// Enter street address
		wait(driver, 20)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//input[@placeholder='Street Address']")))
				.sendKeys(Street);

		// Enter city
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='City']")))
				.sendKeys(City);

		// Select state
		Select state = new Select(wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@placeholder='State']"))));
		state.selectByVisibleText(State);

		// Enter zip code
		wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Zip Code']")))
				.sendKeys(Zip);

		// Click on continue to billing info button
		wait(driver, 20)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Continue to Billing')]")))
				.click();

		// BILLING ADDRESS (Verify new page opened or not)

		boolean nextpage2 = wait(driver, 20)
				.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//h5[contains(text(),'Billing  Address')]")))
				.size() == 1;
		softassert.assertEquals(nextpage2, true);

		// Click on Same as shipping check-box
		wait(driver, 20).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']")))
				.click();

		// Get street address and verify
		String A_address = wait(driver, 20)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//input[@placeholder='Street Address']")))
				.getAttribute("value");
		softassert.assertEquals(A_address, Street);

		// Get city name and verify
		String A_City = wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='City']")))
				.getAttribute("value");
		softassert.assertEquals(A_City, City);

		// Get state name and verify
		String A_state = wait(driver, 20)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//Select[@placeholder='State']/option[13]")))
				.getText().trim();
		softassert.assertEquals(A_state, State);

		// Get city name and verify
		wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Phone Number']")))
				.sendKeys(Phone);

		// Click on continue to card button
		wait(driver, 20)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Continue to Card Info')]")))
				.click();

		// PAYMENT METHOD (Verify new page opened or not)
		boolean nextpage3 = wait(driver, 20)
				.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//h5[contains(text(),'Payment  Method')]")))
				.size() == 1;
		softassert.assertEquals(nextpage3, true);

		// Enter card name
		wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name On Card']")))
				.sendKeys(Card_Name);

		// Enter card number
		wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Card Number']")))
				.sendKeys(Card_No);

		// Enter card CVV
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='CVV']")))
				.sendKeys(Card_CVV);

		// Select card month

		Select month = new Select(wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@placeholder='Month']"))));
		month.selectByVisibleText("12");

		// Select card year
		Select year = new Select(wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@placeholder='Year']"))));
		year.selectByVisibleText("2035");

		// Enter zip code
		wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Zip Code']")))
				.sendKeys(Zip);

		// Click on submit my order button
		wait(driver, 20).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit My Order')]")))
				.click(); 
		
		Thread.sleep(2000);
		
		
		// Verify order placed or not
		boolean order_placed = wait(driver, 15).until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Thank You For Your Order')]")))
				.size() == 1;
		softassert.assertEquals(order_placed, true);
		
	
		//Closing current window
		driver.switchTo().window(tabs.get(1)).close();
		Thread.sleep(1000);
		
		//Switching to main window
		driver.switchTo().window(tabs.get(0));	
		Thread.sleep(1000);

		ar.clear();
		ar.add(Email);
		
		
		//Navigate to devices page
		driver.navigate().to("https://stg.autobrain.com/worker/devices");
		
		//Store user buy device ID
		String user_buy_device = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();
		
		//Create device number for customer
		Device_Num = create_device_by_panel();
		
		//Switch to Orders To Ship page
		driver.navigate().to("https://stg.autobrain.com/worker/online_fulfillment/invoices_to_ship");
		
		//Click on Add Device button to add the manually created device
//		List<WebElement> add_device_btn= wait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Add device #')]")));
		add_device_btn.get(0).click();
		
		
		//Validate enter field box opened
		boolean msg_prompt = wait(driver, 20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Add a device number to the following cars')]"))).size()!=0;
		
		Assert.assertEquals(msg_prompt, true,"Message alert not prompted!");
		
		//Enter Device number
		Thread.sleep(2500);
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter device number']"))).sendKeys(Device_Num);
		Thread.sleep(4000);
		while(driver.findElements(By.xpath("//div[@class='form-group form-inline col-xs-12 has-error']")).size()==1)
		{
			List<WebElement> close_btn = driver.findElements(By.xpath("//div[@class='modal fade in']//button[1]"));
			close_btn.get(1).click(); Thread.sleep(1000);
			driver.navigate().refresh();
			while(driver.findElements(By.xpath("//h3[contains(text(),'Orders to ship')]")).size()==0)
			{
				System.out.println("Loading...Orders to ship page!");
			}
			add_device_btn.get(0).click(); Thread.sleep(1500);
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter device number']"))).sendKeys(Device_Num);
			Thread.sleep(3000);	
		}
		
		
		
		
		
		//Click on Save button
		List<WebElement> btn= driver.findElements(By.xpath("//button[contains(text(),'Save changes')]"));
		btn.get(0).click();
		Thread.sleep(2500);
		
		//Validate device added		
		WebElement device_added = driver.findElement(By.xpath("//tbody//tr[1]//td[8]/a"));
		boolean Device_Added = device_added.getText().contentEquals("Device #"+Device_Num+" added");
		
		Assert.assertEquals(Device_Added, true , "Device not added!");
		
		Thread.sleep(1500);
		//Click on Print/View shipping label
		List<WebElement> print_btn = driver.findElements(By.xpath("//a[contains(text(),'Print/View shipping label')]"));
		print_btn.get(0).click();
		
		//Waiting until the message box not opened
		try
		{
			shipping_label_box=	wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Shipping Label')]"))).isDisplayed();
		}
		catch(Exception e)
		{	
			shipping_label_box= false;
			e.printStackTrace();
		}
		
		Assert.assertEquals(shipping_label_box, true, "Print/View shipping label prompt box not opened!");
		
		//Close print label
		List<WebElement>close_print_msgbox = driver.findElements(By.xpath("//button[@class='close']"));
		close_print_msgbox.get(2).click(); Thread.sleep(1000);
		
		//Click on Mark As Shipped
		List<WebElement> mark_as_shipped_btn = driver.findElements(By.xpath("//a[contains(text(),'Mark as shipped')]"));
		mark_as_shipped_btn.get(0).click();
				
		//Refresh the page
		driver.navigate().refresh();
		
		Thread.sleep(2500);
		
		
		//After marked as shipped device should disappear from the list
		boolean user_disappear_from_list= driver.findElements(By.xpath("//a[contains(text(),'Device #"+"Device_Num"+" added')]")).size()==0;
		Assert.assertEquals(user_disappear_from_list, true, "User still exist in the list which should not after marked the device as shipped!");
		
		
		//Navigate to devices page
		driver.navigate().to("https://stg.autobrain.com/worker/devices");
	
		String device_no = driver.findElement(By.xpath("//tbody/tr[2]/td[1]")).getText();
		
		boolean user_device_updated = user_buy_device!=device_no;
		Assert.assertEquals(user_device_updated, true, "User bought device not updated!");
							
		ar.add(Device_Num);		
				
		
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Log Out')]"))).click(); Thread.sleep(2000);
		driver.navigate().to("https://stg.autobrain.com");
		ar.add(device_no);
		softassert.assertAll();
		return ar;
	}
	
	
	
	
	
	public void register() throws Exception {
		BuyDevice();
		
		//Clicking on sign-up button
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'SIGN UP')]"))).click();	

		//Entering first-name
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_first_name"))).clear();
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_first_name"))).sendKeys("Demouser"+randomInt);
		
		//Entering last-name
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))).clear();
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))).sendKeys("L"+randomInt);
		
		//Entering email
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).clear();
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys(ar.get(0));
		
		//Store entered email in variable
		entered_email = driver.findElement(By.xpath("//input[@placeholder='Email']")).getAttribute("value");
		
		
		//Entering phone number
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_contacts_attributes_0_data"))).clear();
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_contacts_attributes_0_data"))).sendKeys("1234567890");
		
		
		//Entering password
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).clear();
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).sendKeys("welcome");
		
		//Entering confirm password
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password_confirmation"))).clear();
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password_confirmation"))).sendKeys("welcome");
		
		//Clicking on sign-up button to register
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'SIGN UP')]"))).click();
		
		for(int i=0; i<driver.findElements(By.xpath("//span[@class='help-block']")).size(); i++) {
			boolean email_alert = driver.findElements(By.xpath("//span[contains(text(),'has already been taken')]")).size()!=0;
			boolean firstN_alert = driver.findElements(By.xpath("//input[@placeholder='First Name']/following-sibling::span")).size()!=0;
			boolean lastN_alert = driver.findElements(By.xpath("//input[@placeholder='Last Name']/following-sibling::span")).size()!=0;
			
			//First name already exist
			if(firstN_alert==true) {
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_first_name"))).clear();
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_first_name"))).sendKeys("demouser"+randomInt);
			}
			
			//Last name already exist
			if(lastN_alert==true) {
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))).clear();
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))).sendKeys("Last"+randomInt);	
			}
			
			//Email already exist
			if(email_alert==true) {
				driver.navigate().to("https://stg.autobrain.com");
				register();		
			}
	
		}
		
		
		
		//Validating user redirected to next page
		String nextpageurl = "https://stg.autobrain.com/users/sign_up/instructions?from=signup_steps";
		String currentpageurl = driver.getCurrentUrl();
		
		if(nextpageurl.equals(currentpageurl)) {
			System.out.println("Registered successfully!");
		}
		
		else {					
			System.out.println("Not Registered Successfully!");
		}
		
		//MAILINATOR.COM
		//Open new tab
		Thread.sleep(2000);
//		new_tab();
		
		//Checking how many windows are opened currently
//		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(tabs.get(1));
//		Thread.sleep(2000);
		driver.get("https://www.mailinator.com");
		
		//Click on Email button
		List<WebElement> email_btn = wait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Email')]")));
		email_btn.get(1).click();
		Thread.sleep(2000);
		
		//Enter registered email id
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='inbox_field']"))).sendKeys(entered_email);
		
		
		
		
		
		//Click on Go button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("go_inbox"))).click(); Thread.sleep(2000);
		
		// Click on the first email
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Autobrain account')]"))).click();
		
		// Switch to frame
		driver.switchTo().frame(driver.findElement(By.id("msg_body")));
		
		//Get confirmation code
		code =	wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'To get started')]/strong"))).getText();
		
		//Closing the last window
//		driver.switchTo().window(tabs.get(1)).close();
		
		//Switching back to main window
//		driver.switchTo().window(tabs.get(0));
		driver.get(url);				
		
		//Input verification code
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Confirmation Code']"))).sendKeys(code);
		
		//Click on submit button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click(); Thread.sleep(2000);
		System.out.println(entered_email);

		
		while(p_load==false) {
		try {	
		 p_load= wait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Is Your')]/following-sibling::div//span"))).isDisplayed();
		} catch(Exception e) {
			System.out.println("Step 1 page not found after confirmation code.");
			break;
		}	
		}
		
		
		//STEP 1
		step_1(ar.get(1));		
		
		//STEP 2
		step_2();
		
		//STEP 3
		step_3();
		
		//STEP 4
		step_4();
		
		//Done
		done();
		
		
		
		
		while(reg_success==false) {
			Thread.sleep(3000);
		driver.findElement(By.xpath("//div[4]/div[3]/div/div[2]/button ")).click();
		try {
			reg_success= wait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h1[contains(text(),'Searching')]"))).size()==1;
			if(reg_success==true) {
				break;
			}
			
		} catch(Exception e) {
		driver.navigate().refresh();
		if(reg_success==false) {
			try {
				add_creadit_card_page= wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Add Credit Card')]"))).size()==1;
				add_credit_card();
				step_2();
				step_3();
				step_4();
				done();
				softassert.assertEquals("Fill all steps twice", "Fill all steps once", "User filled up all Steps twice which should not!");
			} catch(Exception ee) {
					System.out.println("After refresh, Add credit card page not found again!");
					softassert.assertEquals(add_creadit_card_page, true,"After refresh, Add credit card page not found again!");		
				}		
		}
		
		}
		}
		
		Thread.sleep(5000); 
		softassert.assertAll();
		
	}
	
	
	
	
	
	// FORM 1		//STEP 1 (ADD CAR INFO)
			public void step_1(String de) throws Exception {
				
				//Create a Name for Your Car
				wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[1]/input"))).
				sendKeys(carname);
				
				
				//Enter device number
				wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[2]/input"))).
				sendKeys(de);
				
				//Re-enter device number
				wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[3]/input"))).
				sendKeys(de);
				
				//Enter Your VIN
				wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[4]/input"))).
				sendKeys(VIN);
				
				// Expanding Car Icon drop-down
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select-dropdown']/div"))).click();
				Thread.sleep(3000);
				
				// Selecting Car Icon from drop-down
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(caricon))).click();
				
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
				model_selected=mo.getFirstSelectedOption().getText().trim();
				Thread.sleep(1000);
				
				//Fill Up Insurance and Registration Forms
//				VehicleProfile v = new VehicleProfile();
//				v.Ins_Reg_Forms(); 
				
				//Check terms and conditions
				List<WebElement> el = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='esf']/div[1]")));
				el.get(0).click(); Thread.sleep(2000);
				el.get(1).click();
				
				//Submit
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Continue')]"))).click();
				
				//VALIDATE NEXT PAGE (ADD CREDIT CARD OPENED)
				if(add_creadit_card_page==false) {
				try {
				add_creadit_card_page= wait(driver, 25).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Add Credit Card')]"))).size()==1;
				if(add_creadit_card_page ==true) {
					add_credit_card();
				}
				
				} catch(Exception e) {
							
				}
				}
				
				
				//If credit card page not found then try with refreshing the page
				if(add_creadit_card_page==false) 
				{
					driver.navigate().refresh();
					try 
					{
						add_creadit_card_page= wait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Add Credit Card')]"))).size()==1;
						softassert.assertEquals(add_creadit_card_page, false,"After Step 1, Add credit card page not found. It appear only after refresh the page.");
						add_credit_card();					
					} 
					
					catch(Exception e) 
					{
						
					}		
				}
			
				Thread.sleep(2000);
			}
			
			
			
	//FORM 2		// ADD CREDIT CARD
			public void add_credit_card() throws Exception {
				try
				{
			
				//Choose Plan
				WebElement money_save= wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_1nPLChEwNgDH5KMyzoXBEb_0']/div[1]//button")));
				money_save.click();
				
				//Choose Billing Interval
				WebElement monthly= wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Monthly')]/following-sibling::button")));
				monthly.click();	
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				// Enter First name
				wait(driver, 15)
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='First Name']")))
						.sendKeys("Jhon");

				// Enter Last name
				wait(driver, 15)
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Last Name']")))
						.sendKeys("example");

				// Enter Billing Address
				wait(driver, 15)
						.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//input[@placeholder='Billing Address']")))
						.sendKeys("#123 street tower");

				// Enter City
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='City']")))
						.sendKeys("New York");
				Thread.sleep(2000);

				// Select State
				Select state = new Select(wait(driver, 20).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//option[contains(text(),'State')]//parent::select"))));
				state.selectByVisibleText("Alaska");

				// Add card number
				wait(driver, 15)
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Card Number']")))
						.sendKeys("4000056655665556");

				// Select Month
				Select month = new Select(wait(driver, 20).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//option[contains(text(),'Month')]//parent::select"))));
				month.selectByIndex(11);
				Thread.sleep(2000);

				// Select Year
				Select year = new Select(wait(driver, 20).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//option[contains(text(),'Year')]//parent::select"))));
				year.selectByIndex(8);

				// Enter CVV
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='CVV']")))
						.sendKeys("111");

				// Enter Zip Code
				wait(driver, 15)
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Zip Code']")))
						.sendKeys("160065");

				// Click on check-box (Make This Your Primary Card)
				wait(driver, 15).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']")))
						.click();

				// Click on Save button
				wait(driver, 15)
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Save')]")))
						.click();
				
				
			//	add_credit_error= wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='flash-message text-center success']"))).size()==1;		
			//	Assert.assertEquals(add_credit_error, false, "User not redirected to Step 2 after filled up all valid Add credit card details!");
				
			
				
				
				// Validate Step 2 page opened
				while(credit_card==false) {
					try {
					credit_card= wait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Monitor and Driver Setup')]"))).size()==1;
					} catch(Exception e) {
						System.out.println("Monitor and Drivre page not found.");
						break;
					}
						}
					
					Thread.sleep(2000);
			}
			
			
			
			
			
			
			
	// Form 3 		Monitor and Driver setup
					public void step_2() throws Exception {
					
					//Click on Add new monitor button
					wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add New Monitor')]"))).click();
					
					//Validate form open
					boolean form_open = wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[contains(text(),'Monitor First Name')]"))).size()==1;
					Assert.assertEquals(form_open, true,"Add Monitor form not opened!");
					
					//Storing all elements to fill up Monitor form
					List<WebElement> mon_ele= wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='gWHzj82vXtHhEXQk0erkB_0']/input")));
						
					//Enter Monitor First Name
					mon_ele.get(0).sendKeys("Test_Monitor");
					
					//Enter Monitor Last Name
					mon_ele.get(1).sendKeys("Demo");
					
					//Monitor Email
					mon_ele.get(2).sendKeys("demomonitor123@mailinator.com");
					
					//Monitor phone number
					mon_ele.get(3).sendKeys("8527419632");
						
					//Check-box (Phone number is not from US)
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']"))).click();
					
					//Submit button
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]"))).click();
					
					
					//Verify success message after click on submit button
					boolean success_msg = wait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='flash-message text-center success']"))).size()==1;			
					Assert.assertEquals(success_msg, true, "Succes message not appear!");
					
					//Verify Monitor added in drop-down list (Who Drives This Car The Most?)
					Select sel =  new Select(driver.findElement(By.xpath("//select[@class='select']")));
					try {
					sel.selectByVisibleText("Test_Monitor Demo");
					}
					catch(Exception e) {
						softassert.assertEquals(false, true,"Added Monitor not found in drop-down list!");
					}
					
					//Added Monitor should also appear in List Of All Monitors And Drivers
					boolean list_mon = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Test_Monitor Demo')]"))).size()==1;
					softassert.assertEquals(list_mon, true,"Added Monitor not found in List Of All Monitors And Drivers!");
					
					//Closing the Monitor form
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Cancel')]"))).click();
					
					
					//ADD DRIVER
					//Click on Add New Driver button
					wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add New Driver')]"))).click();
					
					//Validate form open
					boolean form_open2 = wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[contains(text(),'Driver First Name')]"))).size()==1;
					Assert.assertEquals(form_open2, true,"Add Driver form not opened!");
					
					//Storing all elements to fill up Monitor form
					List<WebElement> mon_ele2= wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='gWHzj82vXtHhEXQk0erkB_0']/input")));
						
					//Enter Driver First Name
					mon_ele2.get(0).sendKeys("Test_Driver");
					
					//Enter Driver Last Name
					mon_ele2.get(1).sendKeys("Demo");
					
					//Driver Email
					mon_ele2.get(2).sendKeys("demodriver123@mailinator.com");
					
					//Driver phone number
					mon_ele2.get(3).sendKeys("7418529632");
						
					//Check-box (Phone number is not from US)
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']"))).click();
					
					//Submit button
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]"))).click();
					
					
					//Verify success message after click on submit button
					boolean success_msg2 = wait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='flash-message text-center success']"))).size()==1;			
					Assert.assertEquals(success_msg2, true, "Succes message not appear!");
					
					//Verify Monitor added in drop-down list (Who Drives This Car The Most?)
					Select sel2 =  new Select(driver.findElement(By.xpath("//select[@class='select']"))); Thread.sleep(2000);
					try {
					sel2.selectByVisibleText("Test_Driver Demo");
					}
					catch(Exception e) {
						softassert.assertEquals(false, true,"Added Driver not found in drop-down list!");
					}
					
					//Added Monitor should also appear in List Of All Monitors And Drivers
					boolean list_mon2 = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Test_Driver Demo')]"))).size()==1;
					softassert.assertEquals(list_mon2, true,"Added Driver not found in List Of All Monitors And Drivers!");
					
					//Finally click on Save and Go To Next Step button
					Thread.sleep(2000);
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Save and Go To Next Step')]"))).click();
					
					// Validate Step 3 page opened
					if(safty_modes==false) 
					{
						try 
						{
							safty_modes= wait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Introduction to Safety Modes')]"))).size()==1;
						} 
						
						catch(Exception e) 
						
						{	
							
						}
						
					}
					
					
					//Validating Step 2 form submitted
					softassert.assertEquals(safty_modes, true, "Step 2 form not submitted first time! User needs to submit the form twice!");
					
					if(safty_modes==false)
					{
						//Submit Step 2 again
						wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Save and Go To Next Step')]"))).click();	
						
						try 
						{
							safty_modes= wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Introduction to Safety Modes')]"))).size()==1;
						} 
						
						catch(Exception e) 
						
						{
							System.out.println("Safty Modes page not found. Clicked submit button twice!");			
						}
						
					}
					
					}
		
					
			
			
	// Form 4				Introduction to Safety Modes
							public void step_3() throws Exception {
								Thread.sleep(2000);
							wait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Skip')]"))).click();	
							
							// Validate Step 4 page opened
							while(alert_setting==false) {
								try {
									alert_setting= wait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Alert Settings')]"))).size()==1;
								} catch(Exception e) {
									System.out.println("Alert Settings page not found.");
									break;
								}
									}
							
							}
		

	//Form 5			Roadside Emergency Card
							public void step_4() throws Exception {
								Thread.sleep(2000);
								wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Save and Go To')]"))).click();	
								
								// Validate Step 5 page opened
								while(roadside==false) {
									try {
										roadside= wait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Roadside Emergency Card')]"))).size()==1;
									} catch(Exception e) {
										System.out.println("Roadside Emergency Card page not found.");
										break;
									}
										}	
		
							}
			
			
			
	//Form 6			Done
						public void done() throws Exception {
							Thread.sleep(2000);
							//Click on Finish button
							wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Finish')]"))).click();
							
							//Verify user redirected to home page or not
							while(home==false) {
								try {
									home= wait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[contains(text(),'You are now ready to drive!')]"))).size()==1;
								} catch(Exception e) {
									System.out.println("Home page not found.");
									break;
								}
									}
						}
							
							
							
						
							
			
			
			//CREATE DEVICE NUMBER FROM WORKER PANEL
			public String create_device_by_panel() throws Exception {
				String device_num=null;	
				//Login
//				login();
				
				//Navigate to add device page
				driver.navigate().to("https://stg.autobrain.com/worker/devices/new");
				
				//Validating add device page opened or not
				while(new_device==false) {
					new_device= driver.findElements(By.xpath("//h1[contains(text(),'Add a new device')]")).size()==1;
				}	
				
				
				//Enter phone number
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='2125550123,2125554567']"))).sendKeys(Get_Phone_Num());
				
				//Check-box - Phone number is not from United state or Canada
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='device_international_phone_number']"))).click();
				
				
				//Enter Cellular service type
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("device[cellular_service_type]"))).sendKeys("WYLESS");
				
				
				
				//Select Model
				Select sel = new Select(driver.findElement(By.name("device[model]")));
				sel.selectByVisibleText("Standard");
				
				
				//Enter Device UID (ESN)
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='4541234567,4548901234']"))).sendKeys(Get_Device_Num());
				device_num = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='4541234567,4548901234']"))).getAttribute("value");
				//Select ESN Format
				Select sel2 = new Select(driver.findElement(By.name("device[uid_format]")));
				sel2.selectByVisibleText("Decimal");		
				
				//Click on create device button
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
				
				//VALIDATE ERROR	
				while(driver.findElements(By.id("flash_alert")).size()>0){
					if(driver.findElements(By.xpath("//div[contains(text(),'Uid has already been taken')]")).size()==1==true) {
						wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='4541234567,4548901234']"))).clear();
						wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='4541234567,4548901234']"))).sendKeys(Get_Device_Num());
						device_num = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='4541234567,4548901234']"))).getAttribute("value");
						//Click on create device button
						wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();	
					}
					
					if(driver.findElements(By.xpath("//div[contains(text(),' Phone number has already been taken')]")).size()==1==true) {
						wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='2125550123,2125554567']"))).clear();
						wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='2125550123,2125554567']"))).sendKeys(Get_Phone_Num());				
						
						//Click on create device button
						wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
					}		
				}
				
				//Logout worker panel
//				wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Log Out')]"))).click(); Thread.sleep(2000);
				
//				driver.navigate().to("https://stg.autobrain.com");
				System.out.println(device_num);
				return device_num;
				
			}		
			
			// Generate Random Device Number
			public String Get_Device_Num() {
				String CHARS = "123456789";
				StringBuilder salt = new StringBuilder();
				Random rnd = new Random();
				while (salt.length() < 10) { // length of the random string.
					int index = (int) (rnd.nextFloat() * CHARS.length());
					salt.append(CHARS.charAt(index));
				}
				String saltStr = salt.toString(); System.out.println(saltStr);
				return saltStr; 
			}

			// Generate Random Phone Number
			public String Get_Phone_Num() {
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
	
	
	
	
	
	
	
	
	
	
}
