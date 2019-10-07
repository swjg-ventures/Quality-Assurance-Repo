package com.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.base.Base;

public class Register extends Base {
	//Generating random email
	Random randomGenerator = new Random();  
	int randomInt = randomGenerator.nextInt(1000);
	String entered_email, code;
	
	
	//1. Validating each field during SIGNUP
		public void Verify_Register() throws Exception {
			
					//Clicking on sign-up button
					wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'SIGN UP')]"))).click();	
			
					//Entering first-name
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_first_name"))).clear();
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_first_name"))).sendKeys("Demouser"+randomInt);
					
					//Entering last-name
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))).clear();
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))).sendKeys("L"+randomInt);
					
					//Entering email
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).clear();
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys("demouser"+randomInt+"@mailinator.com");
					
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
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
					
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
							wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).clear();
							wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys("demonew"+randomInt+"@mailinator.com");
						}
						
						//Entering password
						wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).clear();
						wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).sendKeys("welcome");
						
						//Entering confirm password
						wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password_confirmation"))).clear();
						wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password_confirmation"))).sendKeys("welcome");
									
						
						//Clicking on sign-up button to register
						wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();		
					}
					
					
					
					//Validating user redirected to next page
					String nextpageurl = "https://stg.autobrain.com/users/sign_up/instructions?from=signup_steps";
					String currentpageurl = driver.getCurrentUrl();
					
					if(nextpageurl.equals(currentpageurl)) {
						System.out.println("Registered successfully!");
					}
					
					else {					
						Verify_Register() ;
					}
					
					
					//Open new tab
					new_tab();
					
					//Checking how many windows are opened currently
					ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
					driver.switchTo().window(tabs.get(1));
					Thread.sleep(2000);
					driver.get("https://www.mailinator.com");
					
					//Click on Email button
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'EMAIL')]"))).click();
					
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
					driver.switchTo().window(tabs.get(1)).close();
					
					//Switching back to main window
					driver.switchTo().window(tabs.get(0));
				
					
					//Input verification code
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Confirmation Code']"))).sendKeys(code);
					
					//Click on submit button
					wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
					System.out.println(entered_email);
					
					Thread.sleep(2000);
					
		}
		
		
		
		
}
