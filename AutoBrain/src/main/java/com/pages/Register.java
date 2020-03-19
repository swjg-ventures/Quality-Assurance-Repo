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


public class Register extends Login {
	
	int randomInt; boolean email_added;
	
	String F_name = "Test", L_name = "Demo", Street = "935 Gravier Place, Suite 1160, New Orleans, LA.", City = "Boca", State = "Florida", Zip = "70112", 
	Phone = "1236547890", Card_Name = "Demo card",
	Card_CVV = "555", Card_No = "4242424242424242", expected_product_price;	
	
	//Store email which we used during buy the product on https//stg.autobrain.com/buy
	ArrayList<String> ar = new ArrayList<String>();
	
	//Store number of all bought devices (Used in create_device_by_panel)
	ArrayList<String> All_Devices_No = new ArrayList<String>();
	
	boolean add_credit_error, reg_success, shipping_label_box, Is_Next_Page_Correct ;	
	
	String entered_email, code, Device_Num, new_entered_email;
	boolean p_load, add_creadit_card_page, credit_card, new_device, safty_modes, alert_setting,roadside, home, Device_Added, card_error_msg;
	
	String carname1 = "Demo_Car_Test", caricon1 = "//div[@class='grid']/div[1]", status1 = "//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div", 
			year1 = "2015", make1 = "SKODA", VIN1 = "DHSJ879373J738H"; int model1 = 2;	
			
	String carname2 = "Demo_Car_Test", caricon2 = "//div[@class='grid']/div[10]", status2 = "//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div", 
			year2 = "2018", make2 = "VOLVO", VIN2 = "MCSJ879373J736G"; int model2 = 6;

	String model_selected, Insurance_Eff_Date, Insurance_Exp_Date, car_Reg_exp;
	String DeviceName, Signup_New_Email;
	
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
	
	//Number of input devices
	@FindBy(xpath = "//input[@placeholder='Enter device number']")
	List<WebElement> no_of_input_devices;
	
	public Properties prop() throws Exception 
	{
		 Properties prop = new Properties();
		 FileInputStream fis = new FileInputStream("C:\\Users\\Rajesh\\git\\Quality-Assurance-Repo\\AutoBrain\\src\\main\\java\\Library\\register.properties");
		 prop.load(fis);
		 return prop;
	}
	
	
	
	
	
	 public ArrayList<String> BuyDevice() throws Exception 
	 {
		
		 
		 //Initializing elements 
		PageFactory.initElements(driver, this);
		
		//Generating random email
		String Email = Get_Random_Email();
	
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
		Select s = new Select(wait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='quanities-container']/select"))));
		s.selectByVisibleText(prop().getProperty("no_of_devices")); //Coming from "register.properties"
		
		// Select Account Type BUSINESS OR PERSONAL
		Select ss = new Select(wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='account-types-container']/select"))));
		ss.selectByVisibleText(prop().getProperty("account_type")); //Coming from "register.properties"
		
		Thread.sleep(2000);
		// Store expected product price
		expected_product_price = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='price-cost']"))).getText();
		
		// Click on add to cart button
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]"))).click();

		

		// Click on continue to shopping info button
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Continue to Shipping Info')]"))).click();

		// (SHIPPING INFO) Verify new page opened or not
		boolean nextpage = wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h5[contains(text(),'Shipping  Information')]"))).size() == 1;
		softassert.assertEquals(nextpage, true);

		// Enter first name
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='First Name']"))).sendKeys(F_name);

		// Enter last name
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Last Name']"))).sendKeys(L_name);

		// Enter email address
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Email Address']"))).sendKeys(Email); System.out.println("Entered Email During Bought The Product "+"--> "+Email);

		// Enter street address
		wait(driver, 20).until(ExpectedConditions	.visibilityOfElementLocated(By.xpath("//input[@placeholder='Street Address']"))).sendKeys(Street);

		// Enter city
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='City']"))).sendKeys(City);

		// Select state
		Select state = new Select(wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@placeholder='State']"))));
		state.selectByVisibleText(State);

		// Enter zip code
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Zip Code']"))).sendKeys(Zip);

		// Click on continue to billing info button
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Continue to Billing')]"))).click();

		// BILLING ADDRESS (Verify new page opened or not)

		boolean nextpage2 = wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h5[contains(text(),'Billing  Address')]"))).size() == 1;
		softassert.assertEquals(nextpage2, true);

		// Click on Same as shipping check-box
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']"))).click();

		// Get street address and verify
		String A_address = wait(driver, 20).until(ExpectedConditions	.visibilityOfElementLocated(By.xpath("//input[@placeholder='Street Address']"))).getAttribute("value");
		softassert.assertEquals(A_address, Street);

		// Get city name and verify
		String A_City = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='City']"))).getAttribute("value");
		softassert.assertEquals(A_City, City);

		// Get state name and verify
		String A_state = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Select[@placeholder='State']/option[13]"))).getText().trim();
		softassert.assertEquals(A_state, State);

		// Get city name and verify
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Phone Number']"))).sendKeys(Phone);

		// Click on continue to card button
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Continue to Card Info')]"))).click();

		// PAYMENT METHOD (Verify new page opened or not)
		boolean nextpage3 = wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h5[contains(text(),'Payment  Method')]"))).size() == 1;
		softassert.assertEquals(nextpage3, true);

		// Enter card name
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name On Card']"))).sendKeys(Card_Name);

		// Enter card number
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Card Number']"))).sendKeys(Card_No);

		// Enter card CVV
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='CVV']"))).sendKeys(Card_CVV);

		// Select card month

		Select month = new Select(wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@placeholder='Month']"))));
		month.selectByVisibleText("12");

		// Select card year
		Select year = new Select(wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@placeholder='Year']"))));
		year.selectByVisibleText("2035");

		// Enter zip code
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Zip Code']"))).sendKeys(Zip);

		// Click on submit my order button
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit My Order')]"))).click(); 
		
		Thread.sleep(2000);
		
		
		// Verify order placed or not
		boolean order_placed = wait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Thank You For Your Order')]"))).size() == 1;
		softassert.assertEquals(order_placed, true);
		
	
		// Verify order quantity
		boolean quantiy = wait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'Your package will include " +prop().getProperty("no_of_devices")+ " Autobrain device as well as a 5 step quick start guide.')]"))).size() == 1;
		
		softassert.assertEquals(quantiy, true, "Ordered quantiy not matching with order receipt!");
		
		// Verify actual product price according to selected quantity
		String actual_product_price = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Total:')]/following-sibling::div"))).getText();
		
		softassert.assertEquals(actual_product_price, expected_product_price, "Expected product price not matching with actual product price!");
		
		
		
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
		create_device_by_panel();
		
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
		
		for(int i=0; i<no_of_input_devices.size(); i++)
		{
//			no_of_input_devices.get(i).sendKeys(All_Devices_No.get(i));
			TypeInFieldSlowly(no_of_input_devices.get(i), All_Devices_No.get(i));
		
		
		Thread.sleep(3000);
		
		//Check if the error exist after enter custom created device number
		while(driver.findElements(By.xpath("//div[@class='form-group form-inline col-xs-12 has-error']")).size()>0)
		{
			//Closed opened message box
			List<WebElement> close_btn = driver.findElements(By.xpath("//div[@class='modal fade in']//button[1]"));
			close_btn.get(1).click(); Thread.sleep(1000);
			
			//Refresh the page
			driver.navigate().refresh();
			
			//Time to reload the complete page
			while(driver.findElements(By.xpath("//h3[contains(text(),'Orders to ship')]")).size()==0)
			{
				System.out.println("Loading...Orders to ship page!");
			}
			
			//Again open the message box
			add_device_btn.get(0).click(); Thread.sleep(1500);
			
			//Enter the custom created device number
			//Reset i to 0 and start entering the device ID from first index
			i=0;
			TypeInFieldSlowly(no_of_input_devices.get(i), All_Devices_No.get(i));
			Thread.sleep(3000);	
		}
		
		}
		
		//Click on Save button
		List<WebElement> btn= driver.findElements(By.xpath("//button[contains(text(),'Save changes')]"));
		btn.get(0).click();
		
		//Validate device added
		try 
		{
		Device_Added = wait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Device #')]"))).size()==1;
		}
		catch (Exception e)
		{
			Device_Added = false;
			e.getStackTrace();
		}
		
		
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
		List<WebElement>close_print_msgbox =  wait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[@class='close']")));
		Thread.sleep(2000);
		close_print_msgbox.get(2).click(); // Thread.sleep(2000);
		
		//Click on Mark As Shipped
		List<WebElement> mark_as_shipped_btn =  wait(driver, 15).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Mark as shipped')]")));
		Thread.sleep(4000);
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
		
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Log Out')]"))).click(); 
		Thread.sleep(2000);
		driver.navigate().to("https://stg.autobrain.com");
		softassert.assertAll();
		return ar;
	}
	
	
	
	
	//MAIN METHOD
	public void register() throws Exception {
		BuyDevice();
		
		//Clicking on sign-up button
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'SIGN UP')]"))).click();	

		//Entering first-name
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_first_name"))).clear();
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_first_name"))).sendKeys("Test");
		
		//Entering last-name
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))).clear();
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))).sendKeys(""+randomInt);
		
		//Entering email
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).clear();
		
		//Change sign-up email which will different from bought product email
		if(prop().getProperty("Want_To_Change_Signup_Email").contains("true"))
		{
			 String new_email = Get_Random_Email();
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys(new_email);	
			System.out.println("Signup Email Has Been Changed --> "+new_email);
			
			//Update last name
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))).clear();
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))).sendKeys(""+randomInt);
		}
		else 
		{
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys(ar.get(0));
		}
		
		
		
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
		Thread.sleep(2500);
		
		for(int i=0; i<driver.findElements(By.xpath("//span[@class='help-block']")).size(); i++) 
		{
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
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).clear();
				new_entered_email=  Get_Random_Email(); System.out.println();
				System.out.println("Email Already exist! New Entered Email-->"+" "+new_entered_email);
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys(new_entered_email);
				
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).sendKeys("welcome");
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password_confirmation"))).sendKeys("welcome");
				
				//Clicking on sign-up button to register
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'SIGN UP')]"))).click();
			}
	
		}
		
	
		if(!prop().getProperty("Bypass_Confirmation_Page").equals("true"))
		{
		
		//Validating confirmation key page found
		String expected_next_page = "Resend Confirmation Email";
		try 
		{
			Is_Next_Page_Correct = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Resend Confirmation Email')]"))).getText().contains(expected_next_page);
		} 
		catch(Exception e)
		{
			Is_Next_Page_Correct=false;
		}
		
		Assert.assertEquals(Is_Next_Page_Correct, true, "Confirmation key page not found!");
		
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
		if(new_entered_email==null)
		{
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='inbox_field']"))).sendKeys(entered_email);
		} 
		else 
		{
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='inbox_field']"))).sendKeys(new_entered_email);	
		}
		
		
		
		
		//Click on Go button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("go_inbox"))).click(); Thread.sleep(2000);
		
		// Click on the first email
		wait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Autobrain account')]"))).click();
		
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
//		System.out.println(entered_email);

		}
		
		
		if(p_load==false) 
		{
		try 
		{	
		 p_load= wait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Is Your')]/following-sibling::div//span"))).isDisplayed();
		} 
		
		catch(Exception e) 
		{
			System.out.println("Step 1 page not found after confirmation code.");
		}	
		}
		
		
		//ESF_Exemptions		
		if(ESF_Exemptions()==true) 
		{  boolean check_box;
			driver.navigate().refresh(); Thread.sleep(4000);
				try 
				{
				check_box=	wait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'Upon canceling')]"))).size()==1;
				}
				
				catch(Exception e)
				{
					check_box=false;
				}
				
				softassert.assertEquals(check_box, false, "ESF Exemptions check-box should not appear!");
				softassert.assertAll();
		}
		
		//STEP 1
		step_1(All_Devices_No.get(0));		
		
		//STEP 2
		step_2();
		
		//STEP 3
		step_3();
		
		//STEP 4
		step_4();
		
		//Done
		done();
		
		
		
		
		if(reg_success==false) 
		{
			
		try
		{
			Thread.sleep(3000);
			List<WebElement> el = wait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Welcome')]/following-sibling::div/button")));
			el.get(1).click();
		}
		
		catch (Exception e)
		{
			System.out.println("Welcome to autobrain popup not found");
			desktop_notification_alert();
		}
		
		try 
		{
			reg_success= wait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h1[contains(text(),'Searching')]"))).size()==1;
			if(reg_success==true) {
				
			}
			
		} 
		
		catch(Exception e) 
		{
		driver.navigate().refresh();
		
		if(reg_success==false) 
		{
			try 
			{
				add_creadit_card_page= wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Add Credit Card')]"))).size()==1;
				add_credit_card();
				step_2();
				step_3();
				step_4();
				done();
				softassert.assertEquals("Fill all steps twice", "Fill all steps once", "User filled up all Steps twice which should not!");
			} 
			catch(Exception ee) 
			{
					System.out.println("After refresh, Add credit card page not found again!");
					softassert.assertEquals(add_creadit_card_page, true,"After refresh, Add credit card page not found again!");		
			}		
		}
		
		}
		}
		
		Thread.sleep(5000); 
		softassert.assertAll();
		
	}
	
	
	
	
	
	// STEP 1		//STEP 1 (ADD CAR INFO)
			public void step_1(String device_no) throws Exception {
				
				//Create a Name for Your Car
				wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[1]/input"))).
				sendKeys(DeviceName);
				
				
				//Enter device number
				wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[2]/input"))).
				sendKeys(device_no);
				
				//Re-enter device number
				wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[3]/input"))).
				sendKeys(device_no);
				
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
				try {
				el.get(1).click();
				}
				
				catch(Exception e)
				{
					
					System.out.println("Excemption not found! Because the check-box is hidden for this customer.");
				}
				
				Thread.sleep(4000);
				
				
				try
				{
					//Submit
					wait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Continue')]"))).click();
					
				}
				
				catch (Exception e)
				{
					//Submit
					wait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]"))).click();	
				}
				
				
				
				//VALIDATE NEXT PAGE (ADD CREDIT CARD OPENED)
				if(add_creadit_card_page==false) 
				{
					
				try
				{
				add_creadit_card_page= wait(driver, 25).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Add Credit Card')]"))).size()==1;
				}
				
				catch (Exception e)
				{
					add_creadit_card_page = false;
				}
				
				Assert.assertEquals(add_creadit_card_page, true, "Add credit card page not found!");
				
				
				if(add_creadit_card_page ==true) 
				{
					add_credit_card();
				}
					
				}
				
				
			}
			
			
			
			// ADD CREDIT CARD
			public void add_credit_card() throws Exception {
				
				//BUSINESS PLAN 
				if(prop().get("account_type").equals("Autobrain Business"))
				{
				
				//Choose Billing Interval
				WebElement billing_interval = wait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop().getProperty("business_plan_interval"))));
				billing_interval.click();	
				Thread.sleep(2000);	
					
				
				}
				
				
				//FAMILY PLAN
				if(prop().get("account_type").equals("Autobrain Family"))
				{
				//Choose Plan
				WebElement choose_plan = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop().getProperty("choose_plan"))));
				choose_plan.click();
				
				//Choose Billing Interval
				WebElement duration = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop().getProperty("choose_billing_interval"))));
				duration.click();	Thread.sleep(4000);
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
				
			//Validate enter card details accepted or not	
				try {
					card_error_msg = wait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='flash-message text-center error']"))).size()==1;
				}
				catch(Exception e) {
					card_error_msg = false;
				}
			Assert.assertEquals(card_error_msg, false, "Found error while trying to submit the card details!");	
			
				
				
				// Validate Step 2 page opened
				while(credit_card==false) {
					try {
					credit_card= wait(driver, 40).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Driver Setup')]"))).size()==1;
					} catch(Exception e) {
						System.out.println("Monitor and Driver page not found.");
						break;
					}
						}
					
					Thread.sleep(2000);
			}
			
			
					
			
	// STEP 2 		Monitor and Driver setup
					public void step_2() throws Exception {
					
									
					if(prop().get("account_type").equals("Autobrain Business"))
					{
					System.out.println("This is Business Plan");	
					}
					
					if(prop().get("account_type").equals("Autobrain Personal"))
					{
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
					
					}
					
					//Finally click on Save and Go To Next Step button
					Thread.sleep(2000);
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Save and Go To Next Step')]"))).click();
					
					// Validate Step 3 page opened
					if(safty_modes==false) 
					{
						try 
						{
							if(prop().getProperty("account_type").contains("Autobrain Business")) 
							{
							safty_modes= wait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Introduction to Modes')]"))).size()==1;
							}
							
							else if(prop().getProperty("account_type").contains("Autobrain Family")) 
							{
								safty_modes= wait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Introduction to Safety Modes')]"))).size()==1;	
							}
							} 
						
						catch(Exception e) 
						
						{	
				
						}
						
					}
					
					
					//Validating Step 2 form submitted
					softassert.assertEquals(safty_modes, true, "Step 3 title not found!");
					
					
					
					}
		
					
			
			
	// STEP 3				Introduction to Safety Modes
							public void step_3() throws Exception 
							{
								Thread.sleep(2000);
							wait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Skip')]"))).click();	
							
							// Validate Step 4 page opened
							if(alert_setting==false) 
							{
								try {
									alert_setting= wait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Alert Settings')]"))).size()==1;
								} catch(Exception e) {
									System.out.println("Alert Settings page not found.");
									
								}
							}
							
							}
		

	//STEP 4			Roadside Emergency Card
							public void step_4() throws Exception 
							{
								Thread.sleep(2000);
								wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Save and Go To')]"))).click();	
								
								// Validate Step 5 page opened
								if(roadside==false) 
								{
									try {
										roadside= wait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Roadside Emergency Card')]"))).size()==1;
									} catch(Exception e) {
										System.out.println("Roadside Emergency Card page not found.");
										
									}
								}	
		
							}
			
			
			
	//STEP 5			Done
						public void done() throws Exception 
						{
							Thread.sleep(2000);
							//Click on Finish button
							wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Finish')]"))).click();
							
							//Verify user redirected to home page or not
							if(home==false) 
							{
								try {
									home= wait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[contains(text(),'You are now ready to drive!')]"))).size()==1;
								} catch(Exception e) {
									System.out.println("Home page not found.");
									
								}
							}
						}
					
							
	
						
//				 //ADD NEW DEVICE		
				 public void add_new_device() throws Exception 
				 {
					 for(int i=0; i<All_Devices_No.size(); i++)
					 {
						 if(i==0)
						 {
							 i=1;
						 }
					
					 
					 
					 
					//Expand Menu
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.className("hamburger-container"))).click();
					 
					//Select Activate new Device
					wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Activate New Device')]"))).click();
					
					//Create a Name for Your Car
					wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[1]/input"))).sendKeys(carname);
														
					//Enter device number
					wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[2]/input"))).sendKeys(All_Devices_No.get(i));
							
					//Re-enter device number
					wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[3]/input"))).sendKeys(All_Devices_No.get(i));
							
					//Enter Your VIN
					wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[4]/input"))).sendKeys(VIN);
							
					// Expanding Car Icon drop-down
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select-dropdown']/div"))).click();Thread.sleep(3000);
							
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
//					VehicleProfile v = new VehicleProfile();
//					v.Ins_Reg_Forms(); 
							
					//Check terms and conditions
					List<WebElement> el = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='esf']/div[1]")));
					el.get(0).click(); Thread.sleep(2000);
					el.get(1).click();
							
					//Submit
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Continue')]"))).click();
						
					 }	
						
				 }				
						
						
						
						
			
			//CREATE DEVICE NUMBER FROM WORKER PANEL
			public ArrayList<String> create_device_by_panel() throws Exception {
				String device_num=null;	
				
				//Login
//				login();
				
				String total_bought_devices = prop().getProperty("no_of_devices");
				int Total_bought_devices = Integer.parseInt(total_bought_devices);
				
			for(int i=0; i<Total_bought_devices; i++)
				{		
				
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
				
				//Adding device one by one
				All_Devices_No.add(device_num);
				System.out.println(device_num);
				}
			
				return All_Devices_No;
				
			}	
		
			
			
			
			
			// ESF_Exemptions Method
			public boolean ESF_Exemptions() throws Exception {
				
				if(prop().getProperty("ESF_Exemptions").contains("true"))
				{	 	
				//Logout registered user
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Log Out')]"))).click();
				Thread.sleep(2000);
				
				
				//Login main user
				login();
							
				
				//Navigate to ESF_Exemptions page
				driver.navigate().to("https://stg.autobrain.com/worker/esf_exemptions");
				
				
				//Enter email
				if(new_entered_email==null)
				{
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']"))).sendKeys(entered_email);
				} 
				else 
				{
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']"))).sendKeys(new_entered_email);	
				}
				
				//Click on add button
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='commit' and @value ='Add']"))).click();
				
				//validate email added
				if(new_entered_email==null)
				{
				
				  try 
				   {
				     email_added = wait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td[contains(text(),'"+entered_email+"')]"))).size()==1;
				   }
				  catch(Exception e)
				  	{
					  email_added = false;
				  	}
				}
				
								
				else
				{
					try 
					{
					 email_added = wait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td[contains(text(),'"+new_entered_email+"')]"))).size()==1;
					}
					catch(Exception e)
					{
					 email_added = false;
					}
				}
				
				//Logout panel user
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Log Out')]"))).click();
				Thread.sleep(2000);
				
				//Navigate user to login url
				driver.navigate().to(url);
				
				//Login last registered user
				if(new_entered_email==null)
				{
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys(entered_email);
				} 
				else 
				{
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys(new_entered_email);
				}	
				
				
				//Entering password
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).sendKeys("welcome");
				
				//Click on login button
				wait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
				
				
				boolean create_a_vehicle_profile;
				try 
				{
				    create_a_vehicle_profile = wait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Create a Vehicle Profile')]"))).size()==1;
				}
				
				catch(Exception e)
				{
					create_a_vehicle_profile=false;
				}
				
				Assert.assertEquals(create_a_vehicle_profile, true, "Step 1 page not found!");				
				return true;
				
				}
				
				else
				{
				return false;
				}
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
				String saltStr = salt.toString(); // System.out.println(saltStr);
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
	
			
			
			//Generate random email
			public String Get_Random_Email()
			{
				//Generating random email
				Random randomGenerator = new Random(); 
				randomInt = randomGenerator.nextInt(10000);
				String Email = "temp"+randomInt+"@mailinator.com";
				DeviceName = "test"+randomInt;
				return Email;
			}
			
	
}
