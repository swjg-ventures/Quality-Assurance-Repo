package com.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CarFinder extends Login {

	public void car_finder() throws Exception {

		
		//Refreshing the home page
		driver.navigate().to(url);
		isDesktopNotificationAlert();
		Thread.sleep(2000);
		//Click on car finder
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]/div[1]/a/div[2]"))).click();
		
		//Validating correct page opened or not
		softassert.assertEquals(driver.getCurrentUrl(), "https://stg.autobrain.com/cars/1604/car_finder");
		
		
		//Click on navigate to car
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'NAV TO CAR')]"))).click();
		Thread.sleep(2000);
		
		//Checking how many windows are opened currently
		 ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		 
		 //Validating window opened or not
		 softassert.assertEquals(tabs.size(), 2);
		 
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);
		driver.switchTo().window(tabs.get(1)).close();
		
		//Switching to main window
		driver.switchTo().window(tabs.get(0));
		
		
		//Click on spotlight
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'SPOTLIGHT')]"))).click();
		Thread.sleep(2000);
		
		//Validating correct page opened or not
		softassert.assertEquals(driver.getCurrentUrl(), "https://stg.autobrain.com/spotlight");
		
		//Closing the spotlight description by clicking on the cross button
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa fa-times _3N4180tmOGF0PjzMAODtFh_0']"))).click();
		
		Thread.sleep(5000);
		//Turning status ON
		wait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='spotlight-car'][1]/div[1]/div[2]"))).click();
		
		//Checking the status is ON or not
		List <WebElement> status = wait(driver, 30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'ON')]")));
		Thread.sleep(2000);
		
		for(int i=0; i<status.size(); i++) {
			if(i==1) {
				String status_info=	status.get(i).getText();
				Thread.sleep(1000);
				
				//Validating, status should be ON
				softassert.assertEquals(status_info, "ON");
			}
		}
		
		//Closing the opened alert box
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='spotlight-car'][1]//button"))).click();
		Thread.sleep(2000);	
		
		//Turning status OFF
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='spotlight-car'][1]/div[1]/div[2]"))).click();
		
		Thread.sleep(2000);
		//Validating status OFF or not
		boolean status_off = driver.findElements(By.xpath("//span[contains(text(),'ON')]")).size()==0;		
		softassert.assertEquals(status_off, true);
		
		//Click on the Home bottom menu button
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Home')]"))).click();
		isDesktopNotificationAlert();
		softassert.assertAll();
	}
}
