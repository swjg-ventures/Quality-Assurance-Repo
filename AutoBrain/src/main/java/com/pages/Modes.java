package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class Modes extends Login {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String last_geofence, enter_geofence="Test_Demo", dlt_geofence;
	boolean mode_status=true;
	public void mode() throws Exception {
			desktop_notification_alert();
		
			try
			{
			//Click on Mode from main menu
			List<WebElement> mode = wait(driver, 20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'MODES')]")));
			mode.get(1).click();
			}
			catch(Exception e)
			{
				mode_status=false;
				e.printStackTrace();
			}
			
			Assert.assertEquals(mode_status, true, "Mode button is disabled!");
				
			//Scrolling page to the bottom
			WebElement Element = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text(),'Add A New Address')]")));
			js.executeScript("arguments[0].scrollIntoView();",Element );
			Thread.sleep(1000);
			
			//Click on add new address
			Element.click();
			
			//Enter Title of geo location
			wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Title']"))).sendKeys(enter_geofence);
			
			//Enter Geo Location Address
			wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Address or Location']"))).sendKeys("1250");
			
			//Validating related above entered address list fetch or not
			boolean address_list = wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='_12V4dTH1KHShAbyaEfEsMl_0']"))).size()!=0;
			softassert.assertEquals(address_list, true, "No address list found");	
			
			//Selecting address which is on first index
			WebElement first_location=	wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_12V4dTH1KHShAbyaEfEsMl_0'][1]")));
			first_location.click();
			Thread.sleep(2000);
			
			//Click on the Submit button
			wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]"))).click();
			Thread.sleep(2000);
			
			//Created geo-fence name		
			List<WebElement> el = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='_1q_9csrLzQW0wMP2_ltCQd_0']")));	
			last_geofence = el.get(el.size()-1).getText();
			
			//Validating geo-fence created 
			softassert.assertEquals(last_geofence, enter_geofence, "Geo-fence not created!");
			
			//Delete last added geo-fence
			delete_geo_fence();
			
	}
	
	
	
			//Delete geo-fence		
			public void delete_geo_fence() throws Exception 
			{
				Thread.sleep(2500);
				
				//Created geo-fence name	
				try 
				{
				List<WebElement> el = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//i[@class='fa fa-trash _2XkqKinBcWdxg7eyNtIeUK_0']")));		
				
				Thread.sleep(2000);
				el.get(el.size()-1).click();
				Thread.sleep(2000);
				} 
				
				catch(Exception e) 
				{
				e.printStackTrace();
				}
				
				//Validate geo fence deleted
				List<WebElement> ell = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='_1q_9csrLzQW0wMP2_ltCQd_0']")));	
				
				for(int i=0; i<ell.size(); i++) 
				{
				dlt_geofence = ell.get(i).getText();
				if(dlt_geofence.contains(enter_geofence)) {
					System.out.println("Not Deleted successfully!");
				}
				
				}
			}
	
	
	
	
	
}
