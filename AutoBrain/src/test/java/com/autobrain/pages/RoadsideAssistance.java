package com.autobrain.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RoadsideAssistance extends Login {
boolean form_open, active_request, check_assistance_complete;
	public void roadside_assistance() throws Exception {
		
		//Login
//		login(); 
		
		getDriver().navigate().to(url);
		isDesktopNotificationAlert();
		
		//Click on roadside assistance button from the main main
		List<WebElement> ele = wait(getDriver(), 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'ROADSIDE ASSISTANCE')]")));
		ele.get(1).click();
		
		
		//Validate correct page opened	
		try 
		{
			form_open = wait(getDriver(), 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//textarea[@placeholder='Cannot be Blank']"))).size()==1;
			
		} 
		
		catch(Exception e) 
		{
			active_request = wait(getDriver(), 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Active Request ')]"))).size()==1;
			
		}	
			
			if(form_open==true)
			{	
				//Describe the issue
				wait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Cannot be Blank']"))).sendKeys("This is testing");
		
				//Click on Roadside button
				wait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Request Roadside')]"))).click();
//				extractJSLogsInfo();
				
				wait(getDriver(), 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Assistance Complete?')]"))).click();
				wait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Yes')]"))).click();	
				
			}
			
			
			if(active_request==true) 
			{
				wait(getDriver(), 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Assistance Complete?')]"))).click();
				wait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Yes')]"))).click();	
			}
			
			Thread.sleep(2500);
			check_assistance_complete = getDriver().findElements(By.xpath("//h4[contains(text(),'Active Request')]")).size()==0;
			softassert.assertEquals(check_assistance_complete, true, "Assistance request not closed!");

			softassert.assertAll();
		
	}
	
	
	
	
}
