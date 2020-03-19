package com.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import com.base.Base;


public class Login extends Base {
boolean home_page_loaded, desktop_notification;
int n=1;



//Case 1. Login
	public void login() throws Exception {
		Thread.sleep(2000);
		//Entering email
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).clear();
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys("john@example.com");
		
		//Entering password
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).clear();
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).sendKeys("welcome");
		
		//Click on login button
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
		
		//Validation login
		if(home_page_loaded==false) {
			try {
			home_page_loaded =wait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='hooper-slide column is-active is-current']//div[contains(text(),'CAR FINDER')]"))).isDisplayed();	
			} catch(Exception e) {
				home_page_loaded=false;
			}
		}
		
		Assert.assertEquals(home_page_loaded, true, "Home page is not loading!" );
		desktop_notification_alert();
		Thread.sleep(2000);
		softassert.assertAll();
	}
		
	
	//Desktop notification alert
	public void desktop_notification_alert() {
		try {
			desktop_notification = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Desktop Notifications')]"))).size()==1;
			if(desktop_notification==true) {
				driver.findElement(By.xpath("//button[contains(text(),'Yes')]")).click();
				Thread.sleep(2000);
			}
			} catch(Exception e) {
				
			}
	
		
	}
	
	
	
//Case 2. Logout Method
	public void LogoutUser() throws Exception {
		Thread.sleep(2000);
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.className("hamburger-container"))).click();
		Thread.sleep(2000);
		
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'LOG OUT')]"))).click();
		Thread.sleep(2000);
		
		//Storing Expected Url after logout
		String exp = "https://stg.autobrain.com/users/sign_in";
		
		//Validating logout
		Assert.assertEquals(driver.getCurrentUrl(), exp);
		
	}
}
