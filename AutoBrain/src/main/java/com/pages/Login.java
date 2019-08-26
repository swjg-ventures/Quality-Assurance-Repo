package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.base.Base;

// 1.
public class Login extends Base {
SoftAssert softassert = new SoftAssert();

	//Main Method
	public void UserLogin() throws Exception {
		
		//Blank Login Method
		BlankLogin();
		
		//Invalid Login Method		
		InvalidLogin();
		Thread.sleep(2000);
		
		//Valid Login and Logout Method
		ValidLogin();
		
		//Logout Method
		LogoutUser();
		
		
	}


	
	
//Case 1. Login with Blank Details
	
	public void BlankLogin() throws Exception {
		
		//Click on login button
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
				
		//Storing error message
		String actmsg = wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("flash_alert"))).getText();
				
		//Storing expected message
		String expmsg = "Invalid email or password";
				
		Thread.sleep(2000);
		
		//Validation login
		softassert.assertEquals(actmsg, expmsg);
		Thread.sleep(2000);
		softassert.assertAll();
	}


//Case 2. Login and Logout Method
	public void ValidLogin() throws Exception {
		Thread.sleep(2000);
		//Entering email
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).clear();
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys("john@example.com");
		
		//Entering password
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).clear();
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).sendKeys("welcome");
		
		//Click on login button
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
		
		//Validation login
		softassert.assertEquals(driver.getCurrentUrl(), "https://stg.autobrain.com/");
		Thread.sleep(2000);
		softassert.assertAll();
	}
		
	
	
//Case 3. Login with invalid details
	public void InvalidLogin() throws Exception {
		
		//Entering email
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys("jon@example.com");
				
		//Entering password
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).sendKeys("welcom");
				
		//Click on login button
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
		
		//Storing error message
		String actmsg = wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("flash_alert"))).getText();
				
		//Storing expected message
		String expmsg = "Invalid email or password";
				
		Thread.sleep(2000);
		//Validation login
		softassert.assertEquals(actmsg, expmsg);
		softassert.assertAll();	
	}
	
	
//Case 4. Logout Method
	public void LogoutUser() throws Exception {
		Thread.sleep(2000);
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("hamburger-container"))).click();
		Thread.sleep(2000);
		
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'LOG OUT')]"))).click();
		Thread.sleep(2000);
		
		//Storing Expected Url after logout
		String exp = "https://stg.autobrain.com/users/sign_in";
		
		//Validating logout
		Assert.assertEquals(driver.getCurrentUrl(), exp);
		
	}
}
