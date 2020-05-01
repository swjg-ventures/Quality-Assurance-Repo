package com.pages;



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



public class Manually_Create_Invoice_And_Signup extends Register{
boolean new_device, error, choose_plan_page;	
String invoice_name, invoice_id_before, email;

//Used for create device
ArrayList<String> All_Devices_No = new ArrayList<String>();
	
	
	//Property method
	private Properties property() throws Exception 
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src\\main\\java\\Library\\Create_Invoice.properties");
		prop.load(fis);
		return prop;
	}
	
	
	
	
		//Main Method
		public void RegisterWithCustomerInvoice() throws Exception 
		{
			Writer file = new FileWriter("C:\\Users\\Rajesh\\Downloads\\mark.csv");
			 CSVWriter writer = new CSVWriter(file);
			
			 //Create record
		      String [] header = {"device_number"};
		      writer.writeNext(header);   
		      
		      //Create device
		      CreateDevicesByPanel();
		      
		      for(int i=0; i<All_Devices_No.size(); i++)
		      {
		    	String number = All_Devices_No.get(i);
		    	String [] num = {number};
			    writer.writeNext(num);
		      }
		      

		      //Close the writer
		      writer.close();
		      System.out.println("Done");   
		      
		      CreateInvoice();		      
		      
		      //(STEP 1)
		      SubmitCsvFile();
		      
		      //(STEP 2)
		      ChooseInvoicePricingPlanAndDistributionChannel();
		      
		      //Main method
		      Signup();
		      
		}
		
		
		
		
			//Select file for import (Step 1)
			private void SubmitCsvFile() throws Exception
			{
											
				//Redirect to devices page in panel
				driver.get("https://stg.autobrain.com/worker/retail_fulfillment/ready_for_distribution");
				
				//Click on upload file button
				WebElement upload_file=	PresenceOfElementByXpath("//input[@name='file']");
				
				upload_file.sendKeys("C:\\Users\\Rajesh\\Downloads\\mark.csv");	
				
				Thread.sleep(2000);									

			}
	
	
			
			//Choose Invoice, Pricing Plan and Distribution Channel (Step 2)
			private void ChooseInvoicePricingPlanAndDistributionChannel() throws Exception
			{
							
				//This will select the top invoice means the latest one
				VisibilityOfElementByXpath("//select[@name='invoice']/option[2]").click();
				
				//Pricing plan
				Select pricing_plan = new Select(VisibilityOfElementByXpath("//select[@name='pricing_plan']"));
				
				//Choose which pricing plan we want to select
				pricing_plan.selectByVisibleText(property().getProperty("pricing_plan_from_mark_device_sold")); //Coming from property
				
				//Distribution channel
				Select distribution_channel = new Select(VisibilityOfElementByXpath("//select[@name='distribution_channel']"));
				
				//Choose which distribution channel we want to select
				distribution_channel.selectByVisibleText(property().getProperty("distribution_channel_name")); //Coming from property
				
				
				//Submit Form
				VisibilityOfElementByXpath("//input[@name='commit']").click();
				
				//Check error exist
				try
				{
					error = wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='flash_alert']"))).isDisplayed();
				}
				
				catch(Exception e)
				{
					System.out.println("Device Added successfully!");
				}
				

				
				Assert.assertEquals(error, false, "Device not added!");
			
				//Logout
				VisibilityOfElementByXpath("//a[contains(text(),'Log Out')]").click();
				
			}
			
			
			
			
			//MAIN METHOD
			private void Signup() throws Exception {
				email = GenerateRandomEmail();
				driver.navigate().to(url);
				
				//Clicking on sign-up button
				VisibilityOfElementByXpath("//a[contains(text(),'SIGN UP')]").click();	

				//Entering first-name
				VisibilityOfElementByID("user_first_name").clear();
				VisibilityOfElementByID("user_first_name").sendKeys("Temp");
				
				//Entering last-name
				VisibilityOfElementByID("user_last_name").clear();
				VisibilityOfElementByID("user_last_name").sendKeys(""+randomInt);
				
				//Entering email
				VisibilityOfElementByID("user_email").clear();
				
				System.out.println(email);
				VisibilityOfElementByID("user_email").sendKeys(email);
				
						
				//Store entered email in variable
				entered_email = driver.findElement(By.xpath("//input[@placeholder='Email']")).getAttribute("value");
				
				
				//Entering phone number
				VisibilityOfElementByID("user_contacts_attributes_0_data").clear();
				VisibilityOfElementByID("user_contacts_attributes_0_data").sendKeys("1234567890");
				
				
				//Entering password
				VisibilityOfElementByID("user_password").clear();
				VisibilityOfElementByID("user_password").sendKeys("welcome");
				
				//Entering confirm password
				VisibilityOfElementByID("user_password_confirmation").clear();
				VisibilityOfElementByID("user_password_confirmation").sendKeys("welcome");
				
				//Clicking on sign-up button to register
				VisibilityOfElementByXpath("//div[contains(text(),'SIGN UP')]").click();
				Thread.sleep(2500);
				
				for(int i=0; i<driver.findElements(By.xpath("//span[@class='help-block']")).size(); i++) 
				{
					boolean email_alert = driver.findElements(By.xpath("//span[contains(text(),'has already been taken')]")).size()!=0;
					boolean firstN_alert = driver.findElements(By.xpath("//input[@placeholder='First Name']/following-sibling::span")).size()!=0;
					boolean lastN_alert = driver.findElements(By.xpath("//input[@placeholder='Last Name']/following-sibling::span")).size()!=0;
					
					//First name already exist
					if(firstN_alert==true) {
						VisibilityOfElementByID("user_first_name").clear();
						VisibilityOfElementByID("user_first_name").sendKeys("demouser"+randomInt);
					}
					
					//Last name already exist
					if(lastN_alert==true) {
						VisibilityOfElementByID("user_last_name").clear();
						VisibilityOfElementByID("user_last_name").sendKeys("Last"+randomInt);	
					}
					
					//Email already exist
					if(email_alert==true) {
						VisibilityOfElementByID("user_email").clear();
						new_entered_email=  GenerateRandomEmail(); System.out.println();
						System.out.println("Email Already exist! New Entered Email-->"+" "+new_entered_email);
						VisibilityOfElementByID("user_email").sendKeys(new_entered_email);
						
						VisibilityOfElementByID("user_password").sendKeys("welcome");
						VisibilityOfElementByID("user_password_confirmation").sendKeys("welcome");
						
						//Clicking on sign-up button to register
						VisibilityOfElementByXpath("//div[contains(text(),'SIGN UP')]").click();
					}
			
				}
				
						
				//Validating confirmation key page found
				String expected_next_page = "Resend Confirmation Email";
				try 
				{
					Is_Next_Page_Correct = VisibilityOfElementByXpath("//a[contains(text(),'Resend Confirmation Email')]").getText().contains(expected_next_page);
				} 
				catch(Exception e)
				{
					Is_Next_Page_Correct=false;
				}
				
				Assert.assertEquals(Is_Next_Page_Correct, true, "Confirmation key page not found!");
				
				//Open new tab
				Thread.sleep(2000);
				driver.get("https://www.mailinator.com");
				
				//Click on Email button
				List<WebElement> email_btn = PresenceOfAllElementsByXpath("//a[contains(text(),'Email')]");
				email_btn.get(1).click();
				Thread.sleep(2000);
				
				//Enter registered email id
				if(new_entered_email==null)
				{
					 VisibilityOfElementByXpath("//input[@id='inbox_field']").sendKeys(entered_email);
					
				} 
				else 
				{
					 VisibilityOfElementByXpath("//input[@id='inbox_field']").sendKeys(new_entered_email);
					
				}
									
				Thread.sleep(2000);
				//Click on Go button
				VisibilityOfElementByID("go_inbox").click(); 
				Thread.sleep(2000);
				
				// Click on the first email
				VisibilityOfElementByXpath("//a[contains(text(),'Autobrain account')]").click();
				
				// Switch to frame
				driver.switchTo().frame(driver.findElement(By.id("msg_body")));
				
				//Get confirmation code
				code =	VisibilityOfElementByXpath("//td[contains(text(),'To get started')]/strong").getText();
				
				driver.get(url);				
				
				//Input verification code
				VisibilityOfElementByXpath("//input[@placeholder='Confirmation Code']").sendKeys(code);
				
				//Click on submit button
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click(); Thread.sleep(2000);		
				
				if(p_load==false) 
				{
				try 
				{	
				 p_load= VisibilityOfElementByXpath("//div[contains(text(),'Is Your')]/following-sibling::div//span").isDisplayed();
				} 
				
				catch(Exception e) 
				{
					System.out.println("Step 1 page not found after confirmation code.");
				}	
				}
				
				
				//ESF_Exemptions		
				if(ESFExemptions()) 
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
				
				
				Step1(All_Devices_No.get(0));		
				
				Step2();
				
				Step3();
				
				Step4();
				
				Done();			
				
				
				if(!reg_success) 
				{
					
				try
				{
					Thread.sleep(3000);
					List<WebElement> el = PresenceOfAllElementsByXpath("//h4[contains(text(),'Welcome')]/following-sibling::div/button");
					el.get(1).click();
				}
				
				catch (Exception e)
				{
					System.out.println("Welcome to autobrain popup not found");
					desktop_notification_alert();
				}
				
				try 
				{
					reg_success= PresenceOfAllElementsByXpath("//span[contains(text(),'Searching')]").size()==1;					
					
				} 
				
				catch(Exception e) 
				{				
					reg_success = false;
				}
				
				Assert.assertEquals(reg_success, true, "Unable to find searching after welcome popup!");
				
				}
				
				Thread.sleep(2000);
				
				ActivateNewDevice();
				softassert.assertAll();
				
			}
			
			
	
			private void ActivateNewDevice() throws Exception
			{ 
				
				
				for(int i=1; i<All_Devices_No.size(); i++)
				{
					//Missing one condition always there should be a 2 devices. In in case of 1 device, this function will not work
					StepOneActivateNewDevice(All_Devices_No.get(i));		
					Step2();
					Step3();
					Step4();
					Done();
					
				}	
				
			}
			
			
			
			//Step 1 to activate new device after sign-up
			private void StepOneActivateNewDevice(String device_no) throws Exception
			{
				
					//Open navigation menu
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.className("hamburger-container"))).click();
					Thread.sleep(2000);
				
					//Activate new device
					VisibilityOfElementByXpath("//span[contains(text(),'Activate New Device')]").click();
					Thread.sleep(2000);
					
					//Create a Name for Your Car
					VisibilityOfElementByXpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[1]/input").
					sendKeys(DeviceName);				
					
					//Enter device number
					VisibilityOfElementByXpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[2]/input").
					sendKeys(device_no);
					
					//Re-enter device number
					VisibilityOfElementByXpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[3]/input").
					sendKeys(device_no);
					
					//Enter Your VIN
					VisibilityOfElementByXpath("//label[contains(text(),'Create a Name for Your Car')]/following-sibling::div[4]/input").
					sendKeys(VIN);
					
					// Expanding Car Icon drop-down
					VisibilityOfElementByXpath("//div[@class='select-dropdown']/div").click();
					Thread.sleep(3000);
					
					// Selecting Car Icon from drop-down
					VisibilityOfElementByXpath(caricon).click();
					
					// Car Year
					Select y = new Select(VisibilityOfElementByXpath("//div[@class='secondary-input'][1]/div[2]/select"));
					y.selectByVisibleText(year);
					Thread.sleep(2000);

					// Car Make
					Select m = new Select(VisibilityOfElementByXpath("//div[@class='secondary-input'][2]/div[2]/select"));
					m.selectByVisibleText(make);
					Thread.sleep(2000);

					// Car Model
					Select mo = new Select(VisibilityOfElementByXpath("//div[@class='secondary-input'][3]/div[2]/select"));
					mo.selectByIndex(model);
					model_selected=mo.getFirstSelectedOption().getText().trim();
					Thread.sleep(1000);
					
					//Fill Up Insurance and Registration Forms
//					VehicleProfile v = new VehicleProfile();
//					v.Ins_Reg_Forms(); 
					
					//Check terms and conditions
					List<WebElement> el = VisibilityOfAllElementsByXpath("//div[@class='esf']/div[1]");
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
					
					
					
					//VALIDATE CHOOSE PLAN PAGE						
					try
					{
						choose_plan_page= wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Choose Plan')]"))).size()==1;
					}
					
					catch (Exception e)
					{
						choose_plan_page = false;
					}
					
					Assert.assertEquals(choose_plan_page, true, "Choose plan page not found!");
								
						
						//BUSINESS PLAN 
						if(prop().get("account_type").equals("Autobrain Business"))
						{
						
						//Choose Billing Interval
						WebElement billing_interval = PresenceOfElementByXpath(prop().getProperty("business_plan_interval"));
						billing_interval.click();	
						Thread.sleep(2000);	
							
						
						}
						
						
						//FAMILY PLAN
						if(prop().get("account_type").equals("Autobrain Family"))
						{ 
			
						//Choose Plan
						List<WebElement> choose_plan = PresenceOfAllElementsByXpath("//div[contains(text(),'see full list')]/following-sibling::button");
						String num = property().getProperty("choose_plan");
						int number = Integer.parseInt(num);	
						Thread.sleep(2000);
						
						switch (number)
						
						{
							case 0: //VIP Plan
							VisibilityOfElementByXpath("//div[@class='hooper-pagination']//li[1]/button").click();
							Thread.sleep(1500);
							choose_plan.get(number).click();
							break;
							
							case 1: //ESSENTIAL Plan
							VisibilityOfElementByXpath("//div[@class='hooper-pagination']//li[2]/button").click();
							Thread.sleep(1500);
							choose_plan.get(number).click();
							break;
							
							case 2: //MONEY SAVER Plan
							VisibilityOfElementByXpath("//div[@class='hooper-pagination']//li[3]/button").click();	
							Thread.sleep(1500);
							choose_plan.get(number).click();
							break;
							
						}
							
							//Choose Billing Interval
							WebElement duration = VisibilityOfElementByXpath(property().getProperty("choose_billing_interval"));
							duration.click();	
						
							//Submit
							VisibilityOfElementByXpath("//button[@class='submit-btn HSKg29-lwI4BPWKQPVBps_0']").click();
						
						}	
					}						
					
				

			
			
	
			//This will just create a invoice from worker panel
			private String CreateInvoice() throws Exception 
			{
								
				driver.get("https://stg.autobrain.com/worker/retail_fulfillment/new_invoice");
				
				//Add Quantity
				String s = property().getProperty("quantity");
				int quantity = Integer.parseInt(s);
				
				for(int i=0; i<quantity; i++)
				{
				VisibilityOfElementByXpath("//div[@class='input-group number-picker']/span[2]/button").click();
				Thread.sleep(500);
				}
		
		
				//Get invoice auto-generated number
				 invoice_id_before = VisibilityOfElementByXpath("//code[@class='name-preview']").getText();
				 invoice_id_before = invoice_id_before.replace("#", "");
				
				//Description
				VisibilityOfElementByID("invoice_description").sendKeys("testing_"+invoice_id_before);
			
				
				//Select account type
				Select select = new Select(VisibilityOfElementByID("invoice_account_type"));
				
				select.selectByVisibleText(property().getProperty("create_invoice_account_type"));
			
				//Submit button
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
				
				
				//Validate invoice created
				//Get invoice auto-generated number
				String invoice_id_after = VisibilityOfElementByXpath("//code[@class='name-preview']").getText();
				
				if(!invoice_id_before.equals(invoice_id_after))
				{
					System.out.println("Invoice created successfully!");
				}
				
				else if(invoice_id_before.equals(invoice_id_after)) 
				{
					Assert.assertEquals(true,false, "Invoice not created successfully!");
				}
				
				return invoice_name= "testing_"+invoice_id_before;
						
			}
	
	
	

			//This will create number of devices from worker panel
			private ArrayList<String> CreateDevicesByPanel() throws Exception 
			{
			String device_num=null;	
			
			
			//LOGIN
			//Entering email
			VisibilityOfElementByID("user_email").clear();
			VisibilityOfElementByID("user_email").sendKeys("john@example.com");
							
			//Entering password
			VisibilityOfElementByID("user_password").clear();
			VisibilityOfElementByID("user_password").sendKeys("welcome");
							
			//Click on login button
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
					
					
			//Redirect to devices page in panel
			driver.navigate().to("https://stg.autobrain.com/worker/devices/");
			
			String total_bought_devices = property().getProperty("quantity");
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
			VisibilityOfElementByXpath("//textarea[@placeholder='2125550123,2125554567']").sendKeys(GeneratePhoneNumber());
			
			//Check-box - Phone number is not from United state or Canada
			VisibilityOfElementByXpath("//input[@id='device_international_phone_number']").click();
			
			
			//Enter Cellular service type
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("device[cellular_service_type]"))).sendKeys("WYLESS");
			
			
			
			//Select Model
			Select sel = new Select(driver.findElement(By.name("device[model]")));
			sel.selectByVisibleText("Standard");
			
			
			//Enter Device UID (ESN)
			VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']").sendKeys(GenerateDeviceNumber());
			device_num = VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']").getAttribute("value");
			//Select ESN Format
			Select sel2 = new Select(driver.findElement(By.name("device[uid_format]")));
			sel2.selectByVisibleText("Decimal");		
			
			//Click on create device button
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
			
			//VALIDATE ERROR	
			while(driver.findElements(By.id("flash_alert")).size()>0){
				if(driver.findElements(By.xpath("//div[contains(text(),'Uid has already been taken')]")).size()==1==true) 
				{
					VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']").clear();
					VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']").sendKeys(GenerateDeviceNumber());
					device_num = VisibilityOfElementByXpath("//textarea[@placeholder='4541234567,4548901234']").getAttribute("value");
					//Click on create device button
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();	
				}
				
				if(driver.findElements(By.xpath("//div[contains(text(),' Phone number has already been taken')]")).size()==1==true) 
				{
					VisibilityOfElementByXpath("//textarea[@placeholder='2125550123,2125554567']").clear();
					VisibilityOfElementByXpath("//textarea[@placeholder='2125550123,2125554567']").sendKeys(GeneratePhoneNumber());				
					
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
	
}
