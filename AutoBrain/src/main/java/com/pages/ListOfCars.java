package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

public class ListOfCars extends Login {

	public void ChangeCar() throws Exception {
		SoftAssert softassert = new SoftAssert();
		
		//Calling login method
//		ValidLogin();
	
		for (int i=0; i<2; i++) {
			String xpath=null;
			if(i==0) {
				xpath = "//div[@class='multi-car-container']/div[2]";
			}
			
			if(i==1) {
			xpath ="//span[contains(text(),'Steven')]";	
			}
			
		//Expanding the drop-down list
		wait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='active-car-title']"))).click();
		
		//Getting name of car which is on first index
		String act_carname =wait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		Thread.sleep(4000);
		
		//Selecting first car from list
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		Thread.sleep(4000);
		
		//Get selected car name
		String exp_carname= wait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='active-car-title']"))).getText();
		
		//Validating both car name
		softassert.assertEquals(act_carname, exp_carname);
		}
		
		softassert.assertAll();
	}
}
