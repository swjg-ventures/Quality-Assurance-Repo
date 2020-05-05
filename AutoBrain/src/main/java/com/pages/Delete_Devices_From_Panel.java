package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class Delete_Devices_From_Panel extends Login {
	boolean msg_closed, alt_closed;
	
	@FindBy(xpath = "//a[contains(text(),'Add device #')]")
	List<WebElement> ele;
	
	
	public void delete_devices() throws Exception {
		
		//Login
		
		//Entering email
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).clear();
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys("john@example.com");
				
		//Entering password
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).clear();
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))).sendKeys("welcome");
				
		//Click on login button
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();
		
		
		//Redirect to devices page in panel
		driver.navigate().to("https://stg.autobrain.com/worker/devices/");
		
		
		
		
		for(int i=0; i<25; i++)
		{
			List<WebElement> ele = wait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[text()='Delete']")));
		ele.get(0).click(); Thread.sleep(1000);
		
		//Click on confirm button
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Confirm']"))).click();
		

				//Alert message closed
				try
				{
				    alt_closed = wait(driver, 2).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='modal false fade in']"))).size()!=1;
				}
				
				catch(Exception e)
				{
					alt_closed = true;
				}
				
				Assert.assertEquals(alt_closed, true, "Alert not closed successfully!");
				
				
		
		
		//Close alert message
		wait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-info']/a"))).click();
		
		
				//Validate message closed
				try
				{
					msg_closed = wait(driver, 2).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='alert alert-info']/a"))).size()!=1;
					
				}
		
				catch(Exception e)
				{
					msg_closed = true;
				}
				
				Assert.assertEquals(msg_closed, true, "Message not closed successfully!");
		
				}
		
		
}
	
}
