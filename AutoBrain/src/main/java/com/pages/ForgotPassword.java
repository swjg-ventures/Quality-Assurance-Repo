package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

// 4.
public class ForgotPassword extends Login{

	//1. Entering valid registered email
	public void forgot_password() throws Exception {

		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Forgot password')]"))).click();
		
		//Validating user redirected to actual page or not
		Assert.assertEquals(driver.getCurrentUrl(), "https://stg.autobrain.com/users/password/new");
		
		//Entering registered email in forgot password field
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("forgot-password-email"))).sendKeys("john@example.com");
		Thread.sleep(2000);
		
		//Click on submit button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]"))).click();
		Thread.sleep(2000);
		//Storing expected message 
		String exp = "Email Sent";
		
		//Validating that user received Email sent message after enter valid email or not
		String act = wait(driver, 35).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Email Sent')]"))).getText();
		Thread.sleep(4000);
		//Validating both messages
		Assert.assertEquals(act, exp);
		
		//Taking back user to Login page
		driver.navigate().to(url);
	}
	
		
		
} 	
