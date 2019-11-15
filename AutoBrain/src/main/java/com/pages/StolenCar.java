package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StolenCar extends Login {
	boolean car_stolen_page;
	
	public void stolen_car() throws Exception {
		
		desktop_notification_alert();
		//Click on stolen car from main menu
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]/div[9]/a/div[2]"))).click();
		
		
		//Verify car stolen page open
		while (car_stolen_page==false) {
			car_stolen_page = driver.findElements(By.xpath("//h4[contains(text(),'Car Stolen')]")).size()==1;
			Thread.sleep(1000);
		}
		
		
	
		//Click on car icon
		List<WebElement> Car_icon = wait(driver, 20).until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//div[3]/div/img")));
		
		
		
		//If the whole map
		if(Car_icon.size()==2) {
				Thread.sleep(2000);
				Car_icon.get(1).click();		
		}
		
		if(Car_icon.size()==1) {
					Thread.sleep(2000);
					Car_icon.get(0).click();			
			}
	
		
	//Get location text
	Thread.sleep(3000);
	String act_address=	wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gm-style-iw-d']//span"))).getText();
	Thread.sleep(3000);
	
	String exp_address=	wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='address']"))).getText();	
	
	//Validating both address
	softassert.assertEquals(act_address, exp_address);
	
	//Click on Home bottom menu button
	wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Home')]"))).click();
	softassert.assertAll();
	}

}
