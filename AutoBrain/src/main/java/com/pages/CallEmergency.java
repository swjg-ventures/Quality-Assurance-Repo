package com.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CallEmergency extends Login {
	boolean error_msg;
	
	public void call_emergency() throws Exception {
		
		driver.navigate().refresh();
		// Click on call emergency button
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[3]/div[5]/div/div[2]"))).click();
		
		Thread.sleep(2000);
		//Select number of people involved in accident
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='buttons-container']/button[2]"))).click();
		
		//Select emergency service will be calling
		Select s = new Select(wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='field-label'][3]/following-sibling::select"))));
		s.selectByVisibleText("Steven Wolf");
		Thread.sleep(2000);
		
		//Click on call emergency button
		wait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'CALL EMERGENCY SERVICES TO THIS CAR')]"))).click();
		Thread.sleep(7000);
	
		//Get error message
		try {
		 error_msg = wait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'There was an error')]"))).size()==0;
		
		//Click the opened message box
		 wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'There was an error')]/following-sibling::div/button"))).click();
		
		//Validating error message exist or not
		 softassert.assertEquals(error_msg, true, "There was an error while attempting to request emergency services.");
		} 
		catch(Exception e) {
			softassert.assertEquals(error_msg, false, "Success message not found!");
		}
		
		
		
		softassert.assertAll();
	
	}
}