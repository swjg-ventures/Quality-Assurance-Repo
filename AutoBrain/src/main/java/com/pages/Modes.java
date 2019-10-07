package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Modes extends Login {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	public void mode() throws Exception {
		login();
		
		//Click on Mode from main menu
				List<WebElement> mode = wait(driver, 20).until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'MODES')]")));
				for (int i = 0; i < mode.size(); i++) {
					if (i == 1) {
						Thread.sleep(2000);
						mode.get(i).click();
						Thread.sleep(3000);
					}
				}
				
			//Scrolling page to the bottom
				WebElement Element = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text(),'Add A New Address')]")));
			js.executeScript("arguments[0].scrollIntoView();",Element );
			Thread.sleep(1000);
			
			//Click on add new address
			Element.click();
			
			//Enter Title of geo location
			wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Title']"))).sendKeys("Test");
			
			//Enter Geo Location Address
			wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Address or Location']"))).sendKeys("1250");
			
			//Validating related above entered address list fetch or not
			boolean address_list = wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='_12V4dTH1KHShAbyaEfEsMl_0']"))).size()!=0;
			softassert.assertEquals(address_list, true, "No address list found");	
			
			//Selecting address which is on first index
			WebElement first_location=	wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_12V4dTH1KHShAbyaEfEsMl_0'][1]")));
			String Exp_Location_Name=	first_location.getText();
			first_location.click();
			Thread.sleep(2000);
			
			//Click on the Submit button
			wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]"))).click();
			
			//Getting total number of added locations
			List<WebElement> Total_Geo_Places = wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='_1q_9csrLzQW0wMP2_ltCQd_0']/text()")));
			
			//Getting last added location name 
			for(int i=1; i<Total_Geo_Places.size(); i++) {
				
				String Act_Location_Name = Total_Geo_Places.get(i).getText();
				Thread.sleep(1000);
				
			}
	
	}
}
