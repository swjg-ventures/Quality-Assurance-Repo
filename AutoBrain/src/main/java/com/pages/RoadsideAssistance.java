package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RoadsideAssistance extends Login {
boolean page_load;
	public void roadside_assistance() throws Exception {
		
		//Login
//		login(); 
		
		driver.navigate().to(url);
		desktop_notification_alert();
		
		//Click on roadside assistance button from the main main
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]/div[6]/a/div[2]"))).click();
		
		//Validate correct page opened	
		try {
			page_load = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//textarea[@placeholder='Cannot be Blank']"))).size()==1;
			
			//Describe the issue
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Cannot be Blank']"))).sendKeys("This is testing");
		
			//Click on Roadside button
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Request Roadside')]"))).click();
			extractJSLogsInfo();
		} catch(Exception e) {
			System.out.println("Roadside assistance page not opened.");
		}
		softassert.assertEquals(page_load, true, "Roadside assistance page not loaded.");	
		softassert.assertAll();
		
	}
}
