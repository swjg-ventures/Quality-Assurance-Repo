package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

// 4.
public class ForgotPassword extends ManageAccount{

	//1. Entering valid registered email
	public void RegisteredEmail() throws Exception {
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Forgot password')]"))).click();
		
		//Validating user redirected to actual page or not
		Assert.assertEquals(driver.getCurrentUrl(), "https://stg.autobrain.com/users/password/new");
		
		//Entering registered email in forgot password field
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("forgot-password-email"))).sendKeys("john@example.com");
		Thread.sleep(2000);
		
		//Click on submit button
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]"))).click();
		Thread.sleep(2000);
		//Storing expected message 
		String exp = "Email Sent";
		
		//Validating that user received Email sent message after enter valid email or not
		String act = wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Email Sent')]"))).getText();
	
		//Validating both messages
		Assert.assertEquals(act, exp);
		
		//Taking back user to Login page
		driver.navigate().back();
	}
	
	
	
	//2. Enter invalid email in forgot password field
		public void EnterInvalidEmail() throws Exception {
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Forgot password')]"))).click();
			
			//Validating user redirected to actual page or not
			Assert.assertEquals(driver.getCurrentUrl(), "https://stg.autobrain.com/users/password/new");
			
			//Entering invalid email in forgot password field
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("forgot-password-email"))).sendKeys("jn@emple.com");
			Thread.sleep(2000);
			//Click on submit button
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]"))).click();
			Thread.sleep(2000);
			
			boolean msg = driver.findElements(By.xpath("//h2[contains(text(),'Email Sent')]")).size()==0;
			
			Assert.assertEquals(msg, true);
			//Taking back user to Login page
			driver.navigate().back();
		}
		
		
		
		
		//3. Logout button should not display on Forgot password page
		public void LogoutButtonVisibility() {
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Forgot password')]"))).click();
			
			//Validating user redirected to actual page or not
			Assert.assertEquals(driver.getCurrentUrl(), "https://stg.autobrain.com/users/password/new");
			
			//Validating logout button visiblity
			boolean btn = driver.findElements(By.xpath("//div[contains(text(),'Log Out')]")).size()==0;
			Assert.assertEquals(btn, true, "Logout button should not visible");
		}
		
		
	/*
	 * //4. Click on LOGO icon should take user back to Sign-In page public void
	 * LogoClickWorking() throws Exception { wait(driver,
	 * 5).until(ExpectedConditions.visibilityOfElementLocated(By.
	 * xpath("//a[contains(text(),'Forgot password')]"))).click();
	 * 
	 * //Validating user redirected to actual page or not
	 * Assert.assertEquals(driver.getCurrentUrl(),
	 * "https://stg.autobrain.com/users/password/new");
	 * 
	 * //Click on LOGO wait(driver,
	 * 5).until(ExpectedConditions.visibilityOfElementLocated(By.className(
	 * "long-logo"))).click(); Thread.sleep(2000); //Validating user redirected back
	 * to sign-in page or not String actpage
	 * ="https://stg.autobrain.com/users/sign_in";
	 * Assert.assertEquals(driver.getCurrentUrl(), actpage);
	 * 
	 * }
	 */
		
		
		
		//5. Validating click on Cancel button is working or not
		public void CancelBtn() throws Exception {
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Forgot password')]"))).click();
			
			//Validating user redirected to actual page or not
			Assert.assertEquals(driver.getCurrentUrl(), "https://stg.autobrain.com/users/password/new");
				
			//Click on Cancel button
			Thread.sleep(2000);
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Cancel')]"))).click();
			Thread.sleep(2000);
			//Validating user redirected back to sign-in page or not
			String actpage ="https://stg.autobrain.com/users/sign_in";
			Assert.assertEquals(driver.getCurrentUrl(), actpage);
		}
		
} 	
