package com.pages;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;




public class DownloadApp extends Login {
	
	public void DownloadAppLinks() throws Exception {
	
		
		
		//Click on Menu button
		Thread.sleep(2000);
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("hamburger-container"))).click();
		Thread.sleep(2000);
		
		//Click on Download App
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Download App')]"))).click();
		Thread.sleep(2000);
		
		
		//Checking how many windows are opened currently
		 ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

			driver.switchTo().window(tabs.get(1));
			Thread.sleep(2000);
			
			//Verify new tab opened with correct link
			softassert.assertEquals(driver.getCurrentUrl(), "https://stg.autobrain.com/getapp");
			
			//Click on Google play store link
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='buttons']/a[1]"))).click();
			Thread.sleep(2000);
			
			//Empty array list
			tabs.removeAll(tabs);
			
			//Get all opened windows again
			tabs.addAll(driver.getWindowHandles());
		
			driver.switchTo().window(tabs.get(2));
			Thread.sleep(2000);
			
			//Verify google play store title
			softassert.assertEquals(driver.getTitle(), "Autobrain - Apps on Google Play");
			
			driver.switchTo().window(tabs.get(1));	
			Thread.sleep(2000);
			
			//Click on iTunes store link
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='buttons']/a[2]"))).click();
			Thread.sleep(2000);
			
			//Empty array list
			tabs.removeAll(tabs);
			
			//Get all opened windows again
			tabs.addAll(driver.getWindowHandles());
			
			if(driver.toString().contains("ChromeDriver")) {
				driver.switchTo().window(tabs.get(3));
			}
			else {
			driver.switchTo().window(tabs.get(2));
			}
			Thread.sleep(4000);
			
			//Verify iTunes store title
			softassert.assertEquals(driver.getCurrentUrl(), "https://apps.apple.com/us/app/autobrain/id950321581");
			
			//Empty array list
			tabs.removeAll(tabs);
			
			//Get all opened windows again
			tabs.addAll(driver.getWindowHandles());
			Thread.sleep(2000);
		
			
			
			
			driver.switchTo().window(tabs.get(3)).close();
			Thread.sleep(1000);
			driver.switchTo().window(tabs.get(2)).close();
			Thread.sleep(1000);
			driver.switchTo().window(tabs.get(1)).close();
			Thread.sleep(1000);
			driver.switchTo().window(tabs.get(0));	
			Thread.sleep(1000);
			softassert.assertAll();
			}			

}
