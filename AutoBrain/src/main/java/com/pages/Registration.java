package com.pages;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class Registration extends Register_by_panel_device {
	String F_name = "Test", L_name = "Demo", Street = "Demo 123 street", City = "Califonia", State = "Texas", Zip = "55525", 
			Phone = "1236547895", Card_Name = "Demo card",
			Card_CVV = "555", Card_No = "4242424242424242";	
	 
	ArrayList<String> ar = new ArrayList<String>();

	
	 public ArrayList<String> BuyDevice() throws Exception {
		
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
						.visibilityOfElementLocated(By.xpath("//Select[@placeholder='State']/option[52]")))
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
		
		//Caught console error
		
		
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
		
		
		//Navigate to add device page
		driver.navigate().to("https://stg.autobrain.com/worker/devices");
		
		String device_no = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();
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
	
	
}
