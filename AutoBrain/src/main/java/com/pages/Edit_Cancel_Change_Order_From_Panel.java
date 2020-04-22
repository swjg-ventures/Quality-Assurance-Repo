package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Edit_Cancel_Change_Order_From_Panel extends Register {
	String exp_plan_name = "Plan Type: Personal, Cost: $29.97, Monthly: $9.97, Free Days: 30";
	String act_plan_name;
	boolean error;
	
	@FindBy(xpath = "//tbody/tr[1]/td[4]/select/option[@selected='selected']")
	WebElement act_plan_ele;
	
	
	
	public void Buy_Product() throws Exception {
		//Generating random email
		String Email = GenerateRandomEmail();
	
		// Calling login method
		Login l = new Login();
		l.login();
	
		 //Initializing elements 
		PageFactory.initElements(driver, this);
		
		
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
		Select s = new Select(wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='quanities-container']/select"))));
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
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Email Address']"))).sendKeys(Email); System.out.println(Email);

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
		
		//Navigate to Edit Cancel Change Order Information
		driver.navigate().to("https://stg.autobrain.com/worker/online_fulfillment/invoices_to_change#");

		//Get actual plan name
		act_plan_name = act_plan_ele.getText();
		
		//Validate customer having correct plan
		 Assert.assertEquals(act_plan_name, exp_plan_name);	
	
	
			//Update plan with new one
			Select se = new Select(wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[4]/select"))));
			se.selectByVisibleText("Cost: $19.97, Monthly: $9.97, Free Days: 30");
			
			//Wait for few seconds until the change plan confirmation page not found
			while(driver.findElements(By.xpath("//h4[contains(text(),'Are you sure about the following changes?')]")).size()!=1) {
				Thread.sleep(1000);
				System.out.println("Waiting for the change plan confirmation page alert...");
			}
			
			//Validate the price difference
			String exp_price_diff = "$-10.0";
			String act_price_diff = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Difference')]/following-sibling::td"))).getText();
			Assert.assertEquals(act_price_diff, exp_price_diff, "There is an issue in between price difference!");
				
			
			//Click on save changes button
			List<WebElement> save_changes_btn = wait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[contains(text(),'Save changes')]")));
			save_changes_btn.get(0).click();
			
			//Check if error found
			error = wait(driver, 20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='ajax-alert alert alert-danger']"))).size()==1;
			
			//Check if error found then plan should not changed
			if(error=true)
			{
				//Reload the page
				driver.navigate().refresh();
				
				//Cancel order
//				wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[7]"))).click();
				
				//Check plan changed or not
				Assert.assertEquals(act_plan_ele.getText(), exp_plan_name, "Plan should not changed after found error!");
			}	
		 
		 
	}
	

	
}
