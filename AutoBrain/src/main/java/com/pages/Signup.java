package com.pages;


import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

// 2.
public class Signup extends Login{
//Creating object for soft assertion	
SoftAssert softassert = new SoftAssert();

//Generating random email
Random randomGenerator = new Random();  
int randomInt = randomGenerator.nextInt(1000);

		
	
	//1. Validating each field during SIGNUP
	public void ValidSignup() throws Exception {
		//Clicking on sign-up button
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'SIGN UP')]"))).click();
		
			
			if(driver.findElements(By.xpath("//*[@id=\"new_user\"]/small[1]")).size()!=0) {
				//Entering first-name
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_first_name"))).sendKeys("Demo"+randomInt);
				//Clicking on sign-up button to register
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
			}
			
			if(driver.findElements(By.xpath("//*[@id=\"new_user\"]/small[2]")).size()!=0) {
				//Entering last-name
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))).sendKeys("L"+randomInt);
				//Clicking on sign-up button to register
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();			
			}
			
			 if(driver.findElements(By.xpath("//*[@id=\"new_user\"]/small[3]")).size()!=0) {
				//Entering email
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys("demo"+randomInt+"@mailinator.com");
				boolean emailalt = driver.findElements(By.xpath("//span[contains(text(),'has already been taken')]")).size()!=0;
				if(emailalt==true) {
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).clear();	
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys("demo1"+randomInt+1+"@mailinator.com");	
				}
				
				
				//Clicking on sign-up button to register
//				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
			}
			
			 if(driver.findElements(By.xpath("//*[@id=\"new_user\"]/small[4]")).size()!=0) {
				//Entering phone number
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_contacts_attributes_0_data"))).sendKeys("1234567890");	
				//Clicking on sign-up button to register
//				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
			}
			
			 if(driver.findElements(By.xpath("//*[@id=\"new_user\"]/small[5]")).size()!=0) {
				//Entering password
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).sendKeys("123456");
				//Clicking on sign-up button to register
//				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
			}
			
			 if(driver.findElements(By.xpath("//*[@id=\"new_user\"]/small[6]")).size()!=0) {
				//Entering confirm password
				wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password_confirmation"))).sendKeys("12345");
				Thread.sleep(2000);
				if(driver.findElements(By.xpath("//*[@id=\"new_user\"]/small[6]")).size()!=0) {
					//Entering confirm password
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password_confirmation"))).clear();
					//Entering confirm password
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password_confirmation"))).sendKeys("123456");
					//Clicking on sign-up button to register
					wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
					
					//Storing urls
					String nextpageurl = "https://stg.autobrain.com/users/sign_up/instructions?from=signup_steps";
					String currentpageurl = driver.getCurrentUrl();
					
					if(nextpageurl.equals(currentpageurl)) {
						System.out.println("Registered successfully!");
					}
					
					else {
						driver.navigate().to(url);
						ValidSignup();
					}
					driver.navigate().to(url);
					Thread.holdsLock(2000);
//					driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
			}				
		}		
	}
	
	
	
		//2. Validating Terms and COnditions 
			public void TermsandConditions() throws Exception{
				//Clicking on sign-up button
				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'SIGN UP')]"))).click();
				
				//Clicking on terms and condition footer link
				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Terms of Use')]"))).click();
				Thread.sleep(2000);
				//Storing actual page url
				String expectedpage ="https://stg.autobrain.com/terms";
				
				//Validating actual page opened or not with url
				softassert.assertEquals(driver.getCurrentUrl(), expectedpage);
				
				//Storing actual title of terms and condition page
				String ActHeading = driver.findElement(By.xpath("//h4[contains(text(),'Terms of Use and End User License')]")).getText();
				
				//Validating terms and condition title is matching or not
				softassert.assertEquals(ActHeading, "Terms of Use and End User License");
				
				//Navigating back user to login page
				driver.navigate().to(url);
				softassert.assertAll();
			}
	
	
		//3. Validating Privacy Policy
			public void PrivayPolicy() throws Exception {
				//Clicking on sign-up button
				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'SIGN UP')]"))).click();
				
				//Clicking on Privacy policy 
				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Privacy Policy')]"))).click();
				Thread.sleep(2000);
				
				//Storing actual page url
				String expectedpage ="https://stg.autobrain.com/privacy_policy";
				
				//Validating actual page opened or not
				softassert.assertEquals(driver.getCurrentUrl(), expectedpage);
				
				//Storing actual heading of privacy policy page
				String ActHeading = driver.findElement(By.xpath("//h4[contains(text(),'Privacy Policy')]")).getText();
				
				//Validating Privacy policy title
				softassert.assertEquals(ActHeading, "Privacy Policy");
				//Navigating back user to login page
				driver.navigate().back();
				
				
				softassert.assertAll();
			}
			
			
			
		//4. Validating chat
			public void ChatButton() throws Exception {
				//Clicking on sign-up button
//				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'SIGN UP')]"))).click();	
			
				//Click on chat icon
				driver.switchTo().frame("fc_widget");
				Thread.sleep(2000);
				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("chat-content"))).click();
				
				//Writing message in chat box
				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("app-conversation-editor")))
				.sendKeys("Hello, this is for test!");
				
				Thread.sleep(3000);
				//Now, pressing the Enter button from keyboard in order to submit the query in inbox
				WebElement textbox = driver.findElement(By.id("app-conversation-editor"));
				textbox.sendKeys(Keys.ENTER); 
				Thread.sleep(5000);
				
				//Validating text sent in inbox or not
	boolean text =	wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("app-conversation-editor"))).getText().isEmpty();
				softassert.assertEquals(text, true);
				
				//Closing the opened chat box
				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("minimize"))).click();
				Thread.sleep(2000);
				driver.navigate().to(url);
			
			}
	
}
