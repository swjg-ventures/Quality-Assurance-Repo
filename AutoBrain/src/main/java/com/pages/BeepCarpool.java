package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class BeepCarpool extends Login {
boolean beep_page, form_page, member_created;
	
	
	
	public void beep_carpool() throws Exception {
		driver.navigate().to(url);
		desktop_notification_alert();
//		login();
		
		// Sliding to next page
		Thread.sleep(2000);
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='hooper-indicator']"))).click();
		
		// Click on beep carpool button
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[4]/div[5]/a/div[2]"))).click();
		
		//Validate beep carpool page opened
		beep_page = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Add a New Carpool Member')]"))).size()==1;
		Assert.assertEquals(beep_page, true,"Beep Carpool page not opened!");
		
		//Click on Add a New Carpool Member button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Add a New Carpool Member')]"))).click();
		
		//Validate add member form opened
		form_page = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[contains(text(),'Add a New Carpool Member')]"))).size()==1;
		Assert.assertEquals(form_page, true,"Form page not opened!");
		
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
			extractJSLogsInfo();
		}
		Assert.assertEquals(member_created, true, "Member not created!");
		driver.navigate().back();
		
		//Add member
		add_frnd_from_beep_frnd(); 
		
		//Delete member
		delete_beep_frnd();
		
	}
	
	
	public void add_frnd_from_beep_frnd() throws Exception {
		
		Thread.sleep(2000);
		// Sliding to next page
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='hooper-indicator']"))).click();
				
		// Click on beep carpool button
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[4]/div[4]/a/div[2]"))).click();
		
		
		//Click on Add a New Carpool Member button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Add a New Beep Friend')]"))).click();
				
		//Validate add member form opened
		form_page = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[contains(text(),'Add a Friend from Beep Carpool')]"))).size()==1;
		Assert.assertEquals(form_page, true,"Form page not opened!");	
		
		//Click on Add a New Carpool Member button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add a Friend from Beep Carpool')]"))).click();
		
		//Select a friend from beep friend list
		Select s  = new Select(driver.findElement(By.xpath("//select[@class='select gm0q1xaPzRR_hIlmNUybK_0 form-control']")));
		s.selectByVisibleText("Test_Carpool Member");
		
		// Click on submit button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]"))).click();
	
		try {
			member_created = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Test_Carpool')]"))).size()==1;
			} catch(Exception e) {
			}
	
	
	
	
	
	}
	
	
	
	
	
	
	//Deleted added member from carpool member list
	public void delete_beep_frnd() {
	boolean member_dlt;
	
		//Click on added member
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Test_Carpool Member')]"))).click();
		
		//Click on delete button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("delete-beep"))).click();
		
		//Select Delete option
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Delete')]"))).click();
		
		//Validate member deleted or not
		member_dlt = wait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Test_Carpool Member')]"))).size()!=1; 
		 
		Assert.assertEquals(member_dlt, true, "Member not deleted!");
	}
	
	
	
	
	
	
	
	
	
	
}
