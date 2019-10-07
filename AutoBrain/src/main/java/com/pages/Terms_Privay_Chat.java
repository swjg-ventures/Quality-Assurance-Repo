package com.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Terms_Privay_Chat extends Login{

		//1. Validating Terms and COnditions 
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
	
	
		//2. Validating Privacy Policy
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
			
			
			
		//3. Validating chat
			public void ChatButton() throws Exception {
				//Clicking on sign-up button
//				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'SIGN UP')]"))).click();	
			
				//Click on chat icon
				Thread.sleep(4000);
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
				
				//Validating text sent in in-box or not
				boolean text =	wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("app-conversation-editor"))).getText().isEmpty();
				softassert.assertEquals(text, true);
				
				//Closing the opened chat box
				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("minimize"))).click();
				Thread.sleep(2000);
				driver.navigate().to(url);
			
			}
	
}
