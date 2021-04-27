package com.autobrain.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CustomerInfo extends Login{

	public void customerInfo() throws Exception {
		String fname, lname;	
		
		login("lib1@mailinator.com", "welcome");
		
		// Click on corner dots to expand the menu
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.className("ellipsis-opener"))).click();
		Thread.sleep(2000);

		// Click on update contact info from expanded drop-down
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Update Contact Info')]"))).click();
		
		Thread.sleep(2000);	
		fname=	wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='firstName']"))).getAttribute("value");
		
		lname=	wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='lastName']"))).getAttribute("value");

		Thread.sleep(1000);	
		// Click on corner dots to expand the menu
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.className("ellipsis-opener"))).click(); Thread.sleep(1500);	
		
		// Click on update contact info from expanded drop-down
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Customer Info')]"))).click();
		Thread.sleep(4000);
		
		String get_fname = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'First Name:')]/following-sibling::span"))).getText();
		
		// Validating First name
		softassert.assertEquals(fname, get_fname, "FirstName not mathicng with customer info details.");

		String get_lname = wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Last Name:')]/following-sibling::span"))).getText();

		// Validating Last name
		softassert.assertEquals(lname, get_lname, "LastName not mathicng with customer info details.");
		softassert.assertAll();
	}
}
