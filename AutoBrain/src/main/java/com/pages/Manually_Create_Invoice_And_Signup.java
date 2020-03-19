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
boolean new_device, error;	String invoice_name, invoice_id_before, email;

//Used for create device
ArrayList<String> All_Devices_No = new ArrayList<String>();
	
	
	//Property method
	public Properties property() throws Exception 
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Rajesh\\git\\Quality-Assurance-Repo\\AutoBrain\\src\\main\\java\\Library\\Create_Invoice.properties");
		prop.load(fis);
		return prop;
	}
	
	
	
	
		//Mark a Device as Sold and Shipped
		public void Write_Csv_And_Mark_Device_as_Sold() throws Exception 
		{
			Writer file = new FileWriter("C:\\Users\\Rajesh\\Downloads\\mark.csv");
			 CSVWriter writer = new CSVWriter(file);
			
			 //Create record
		      String [] header = {"device_number"};
		      writer.writeNext(header);   
		      
		      //Create device
		      create_device_by_panel();
		      
		      for(int i=0; i<All_Devices_No.size(); i++)
		      {
		    	String number = All_Devices_No.get(i);
		    	String [] num = {number};
			    writer.writeNext(num);
		      }
		      

		      //Close the writer
		      writer.close();
		      System.out.println("Done");   
		      
		      //Create invoice
		      create_invoice();
		      
		      
		      //(STEP 1)
		      submit_csv_file();
		      
		      //(STEP 2)
		      choose_invoice_pricing_plan_and_disctibution_channel();
		      
		      //Main method
		      register();
		      
		}
		
		
		
		
			//Select file for import (Step 1)
			public void submit_csv_file() throws Exception
			{
											
				//Redirect to devices page in panel
				driver.get("https://stg.autobrain.com/worker/retail_fulfillment/ready_for_distribution");
				
				//Click on upload file button
				WebElement upload_file=	wait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='file']")));
				
				upload_file.sendKeys("C:\\Users\\Rajesh\\Downloads\\mark.csv");	
				
				Thread.sleep(2000);									

			}
	
	
			
			//Choose Invoice, Pricing Plan and Distribution Channel (Step 2)
			public void choose_invoice_pricing_plan_and_disctibution_channel() throws Exception
			{
							
				//This will select the top invoice means the latest one
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='invoice']/option[2]"))).click();
				
				//Pricing plan
				Select pricing_plan = new Select(wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='pricing_plan']"))));
				
				//Choose which pricing plan we want to select
				pricing_plan.selectByVisibleText(property().getProperty("pricing_plan_from_mark_device_sold")); //Coming from property
				
				//Distribution channel
				Select distribution_channel = new Select(wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='distribution_channel']"))));
				
				//Choose which distribution channel we want to select
				distribution_channel.selectByVisibleText(property().getProperty("distribution_channel_name")); //Coming from property
				
				
				//Submit Form
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='commit']"))).click();
				
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
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Log Out')]"))).click();
				
			}
			
			
			
			
			//MAIN METHOD
			public void register() throws Exception {
				email = Get_Random_Email();
				driver.navigate().to(url);
				
				//Clicking on sign-up button
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'SIGN UP')]"))).click();	

				//Entering first-name
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_first_name"))).clear();
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_first_name"))).sendKeys("Temp");
				
				//Entering last-name
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))).clear();
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))).sendKeys(""+randomInt);
				
				//Entering email
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).clear();
				
				System.out.println(email);
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys(email);
				
						
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
//				new_tab();
				
				//Checking how many windows are opened currently
//				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//				driver.switchTo().window(tabs.get(1));
//				Thread.sleep(2000);
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
//				driver.switchTo().window(tabs.get(1)).close();
				
				//Switching back to main window
//				driver.switchTo().window(tabs.get(0));
				driver.get(url);				
				
				//Input verification code
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Confirmation Code']"))).sendKeys(code);
				
				//Click on submit button
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click(); Thread.sleep(2000);
//				System.out.println(entered_email);

			
				
				
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
			
			


	
	
			//Create invoice
			public String create_invoice() throws Exception 
			{
								
				//Create Invoice Page
				driver.get("https://stg.autobrain.com/worker/retail_fulfillment/new_invoice");
				
				//Add Quantity
				String s = property().getProperty("quantity");
				int quantity = Integer.parseInt(s);
				
				for(int i=0; i<quantity; i++)
				{
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='input-group number-picker']/span[2]/button"))).click();
				Thread.sleep(500);
				}
		
		
				//Get invoice auto-generated number
				 invoice_id_before = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//code[@class='name-preview']"))).getText();
				 invoice_id_before = invoice_id_before.replace("#", "");
				
				//Description
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("invoice_description"))).sendKeys("testing_"+invoice_id_before);
			
				
				//Select account type
				Select select = new Select(wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("invoice_account_type"))));
				
				select.selectByVisibleText(property().getProperty("create_invoice_account_type"));
			
				//Submit button
				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
				
				
				//Validate invoice created
				//Get invoice auto-generated number
				String invoice_id_after = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//code[@class='name-preview']"))).getText();
				
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
	
	
	
	

			//CREATE DEVICE NUMBER FROM WORKER PANEL
			public ArrayList<String> create_device_by_panel() throws Exception 
			{
			Register r = new Register();
			String device_num=null;	
			
			
			//LOGIN
			//Entering email
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).clear();
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys("john@example.com");
							
			//Entering password
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).clear();
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).sendKeys("welcome");
							
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
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='2125550123,2125554567']"))).sendKeys(r.Get_Phone_Num());
			
			//Check-box - Phone number is not from United state or Canada
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='device_international_phone_number']"))).click();
			
			
			//Enter Cellular service type
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("device[cellular_service_type]"))).sendKeys("WYLESS");
			
			
			
			//Select Model
			Select sel = new Select(driver.findElement(By.name("device[model]")));
			sel.selectByVisibleText("Standard");
			
			
			//Enter Device UID (ESN)
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='4541234567,4548901234']"))).sendKeys(r.Get_Device_Num());
			device_num = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='4541234567,4548901234']"))).getAttribute("value");
			//Select ESN Format
			Select sel2 = new Select(driver.findElement(By.name("device[uid_format]")));
			sel2.selectByVisibleText("Decimal");		
			
			//Click on create device button
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
			
			//VALIDATE ERROR	
			while(driver.findElements(By.id("flash_alert")).size()>0){
				if(driver.findElements(By.xpath("//div[contains(text(),'Uid has already been taken')]")).size()==1==true) 
				{
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='4541234567,4548901234']"))).clear();
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='4541234567,4548901234']"))).sendKeys(r.Get_Device_Num());
					device_num = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='4541234567,4548901234']"))).getAttribute("value");
					//Click on create device button
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();	
				}
				
				if(driver.findElements(By.xpath("//div[contains(text(),' Phone number has already been taken')]")).size()==1==true) 
				{
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='2125550123,2125554567']"))).clear();
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='2125550123,2125554567']"))).sendKeys(r.Get_Phone_Num());				
					
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
