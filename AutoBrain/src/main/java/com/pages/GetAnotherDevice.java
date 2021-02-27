package com.pages;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class GetAnotherDevice extends Login {
boolean order_placed;

public void BuyDevice() throws Exception {
	
			String F_name = "Test", L_name = "Demo", Emai = "Demo@gmail.com", Street = "Demo 123 street",
			City = "Califonia", State = "Texas", Zip = "55525", Phone = "1236547895", Card_Name = "Demo card",
			Card_CVV = "555", Card_No = "4242424242424242";
		
			login("john@example.com", "welcome");
		
	
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
			s.selectByVisibleText("2");
	
			// Click on add to cart button
			wait(driver, 20).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]")))
					.click();
	
			// Get total cost as per number of devices
			String actualcost = wait(driver, 20).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='order-row total']/span[2]")))
					.getText();
	
			String expectedcost = "$59.94";
	
			// Comparing both cost
			softassert.assertEquals(actualcost, expectedcost);
	
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
					.sendKeys(Emai);
	
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

		
		
		

			if(order_placed==false) 
			{
				try 
				{
					order_placed=	wait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Thank You For Your Order')]"))).size() == 1;
				} 
				catch(Exception e) 
				{
						
				}
				
			}
		
			//Validate card processed or not
			Assert.assertEquals(order_placed, true, "There was an error processing card!");
			
			
			if(order_placed==false)
			{
			//Switching to main window
			driver.switchTo().window(tabs.get(0));	
			Thread.sleep(1000);
		
			//Closing last opened window		
			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));	
			Thread.sleep(1000);
			}
				
		
		
			if(order_placed==true)
			{
			
			String order_des = wait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='confirm-row'][2]"))).getText();
			String exp_des ="Your package will include 2 Autobrain device as well as a 5 step quick start guide.";
			softassert.assertEquals(order_des, exp_des);
	
			// Verify Your Checkout Total Details
			// Sub-total
			String Act_sub_total = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='amount-container'][1]/div[2]"))).getText();				
			softassert.assertEquals(Act_sub_total, "$59.94");
			
			
			// Verify Free Trial Period
			String Act_free_trial = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='amount-container'][2]/div[2]"))).getText();				
			softassert.assertEquals(Act_free_trial, "30 Days");
			
			// Verify Shipping
			String Act_shipping = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='amount-container'][3]/div[2]"))).getText();				
			softassert.assertEquals(Act_shipping, "Free");
			
			
			// Verify Tax
			String Act_tax = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='amount-container'][4]/div[2]"))).getText();				
			softassert.assertEquals(Act_tax, "$0.00");
			
			// Verify Total
			String Act_total = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='amount-container total']/div[2]"))).getText();				
			softassert.assertEquals(Act_total, "$59.94");
			
			
			// Verify Your Shipping Information
			// Verify user-name
			String Act_username = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='order-list top']/li[2]"))).getText();				
			softassert.assertEquals(Act_username, "Test Demo");
			
			//Verify Email
			String Act_email = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='order-list top']/li[3]"))).getText();				
			softassert.assertEquals(Act_email, "Demo@gmail.com");
			
			
			//Verify Address
			String Act_address = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='order-list top']/li[4]"))).getText();				
			softassert.assertEquals(Act_address, "Demo 123 street, Califonia, Texas, 55525");
			
			
			
			// Verify Your Billing Information
			// Verify card name
			String Act_card_name = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='order-list']/li[2]"))).getText();				
			softassert.assertEquals(Act_card_name, "Demo card");
			
			// Verify card number
			String Act_card_no = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='order-list']/li[3]"))).getText();				
			softassert.assertEquals(Act_card_no, "**** **** **** 4242");
			
			// Verify phone number
			String Act_phone_no = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='order-list']/li[4]"))).getText();				
			softassert.assertEquals(Act_phone_no, "1236547895");
			
			//Scrolling page to bottom
			JavascriptExecutor js = (JavascriptExecutor) driver;  
			WebElement ele = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Share Your')]")));
			js.executeScript("arguments[0].scrollIntoView();", ele);
			Thread.sleep(1000);	
			
			//Verify Your Promo Code
			String act_promo_code = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Your Promo Code')][2]/following-sibling::h2"))).getText();
			String exp_promo_code = "hrGxT7";
			softassert.assertEquals(act_promo_code, exp_promo_code);
			
			//Switching to main window
			driver.switchTo().window(tabs.get(0));	
			Thread.sleep(1000);
		
			//Closing last opened window		
			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));	
			Thread.sleep(1000);
			
			}
			softassert.assertAll();

	}

}
