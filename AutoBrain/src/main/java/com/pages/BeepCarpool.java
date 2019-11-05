package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class BeepCarpool extends Login {
boolean beep_page, form_page, member_created;
	
	
	
	public void beep_carpool() throws Exception {
		driver.navigate().refresh();
//		login();
		// Sliding to next page
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='hooper-indicator']"))).click();
		
		// Click on call emergency button
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[4]/div[5]/a/div[2]"))).click();
		
		//Validate beep carpool page opened
		beep_page = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Add a New Carpool Member')]"))).size()==1;
		Assert.assertEquals(beep_page, true,"Beep Carpool page not opened!");
		
		//Click on Add a New Carpool Member button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Add a New Carpool Member')]"))).click();
		
		//Validate add member form opened
		form_page = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[contains(text(),'Add a New Carpool Member')]"))).size()==1;
		Assert.assertEquals(beep_page, true,"Form page not opened!");
		
		//Select Add a New Carpool Member option 
		WebElement ele =  wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='add-from-toggles']/button[1]")));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		
		
		String col = ele.getCssValue("background-color");
		System.out.println(col);
		
		//Enter first name
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='beep-input gWHzj82vXtHhEXQk0erkB_0'][1]/input"))).sendKeys("Test_Carpool");
		
		//Enter last name
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='beep-input gWHzj82vXtHhEXQk0erkB_0'][2]/input"))).sendKeys("Member");
			
		//Enter phone number
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='beep-input gWHzj82vXtHhEXQk0erkB_0'][3]/input"))).sendKeys("8965471235");
				
		//Enter address
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gWHzj82vXtHhEXQk0erkB_0']/input"))).sendKeys("Houston");
					
		// Select fetch location from list
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_2aL9ikio50GMobGq7MN6LE_0']/div[2]/div[1]"))).click();
		
		Thread.sleep(2500);
		// Click on submit button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]"))).click();
		
		// Validate group created
		try {
		member_created = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Test_Carpool')]"))).size()==1;
		} catch(Exception e) {
		}
		Assert.assertEquals(member_created, true, "Member not created!");
		
		
		
	}
	
	
	
	//Deleted added member from carpool member list
	public void delete_carpool_member() {
		
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='beep-item last']"))).click();
		
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("delete-beep"))).click();
		
		 
	}
	
	
	
	
	
	
	
	
	
	
}
