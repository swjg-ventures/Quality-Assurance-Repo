package com.autobrain.pages;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;




public class DownloadApp extends Login {
	
	public void downloadApp() throws Exception {
	
		login("John@example.com", "welcome");
		
		//Click on Menu button
		Thread.sleep(2000);
		wait(getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("hamburger-container"))).click();
		Thread.sleep(2000);
		
		//Click on Download App
		wait(getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Download App')]"))).click();
		Thread.sleep(2000);
		
		
		//Checking how many windows are opened currently
		 ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());

			getDriver().switchTo().window(tabs.get(1));
			Thread.sleep(2000);
			
			//Verify new tab opened with correct link
			softassert.assertEquals(getDriver().getCurrentUrl(), "https://stg.autobrain.com/getapp");
			
			//Click on Google play store link
			wait(getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='buttons']/a[1]"))).click();
			Thread.sleep(2000);
			
			//Empty array list
			tabs.removeAll(tabs);
			
			//Get all opened windows again
			tabs.addAll(getDriver().getWindowHandles());
		
			getDriver().switchTo().window(tabs.get(2));
			Thread.sleep(2000);
			
			//Verify google play store title
			softassert.assertEquals(getDriver().getTitle(), "Autobrain - Apps on Google Play");
			
			getDriver().switchTo().window(tabs.get(1));	
			Thread.sleep(2000);
			
			//Click on iTunes store link
			wait(getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='buttons']/a[2]"))).click();
			Thread.sleep(2000);
			
			//Empty array list
			tabs.removeAll(tabs);
			
			//Get all opened windows again
			tabs.addAll(getDriver().getWindowHandles());
			
			if(getDriver().toString().contains("ChromegetDriver()")) {
				getDriver().switchTo().window(tabs.get(3));
			}
			else {
			getDriver().switchTo().window(tabs.get(2));
			}
			Thread.sleep(4000);
			
			//Verify iTunes store title
			softassert.assertEquals(getDriver().getCurrentUrl(), "https://apps.apple.com/us/app/autobrain/id950321581");
			
			//Empty array list
			tabs.removeAll(tabs);
			
			//Get all opened windows again
			tabs.addAll(getDriver().getWindowHandles());
			Thread.sleep(2000);
		
			
			
			
			getDriver().switchTo().window(tabs.get(3)).close();
			Thread.sleep(1000);
			getDriver().switchTo().window(tabs.get(2)).close();
			Thread.sleep(1000);
			getDriver().switchTo().window(tabs.get(1)).close();
			Thread.sleep(1000);
			getDriver().switchTo().window(tabs.get(0));	
			Thread.sleep(1000);
			softassert.assertAll();
			}			

}
