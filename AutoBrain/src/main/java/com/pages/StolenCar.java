package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StolenCar extends Login {

	public void stolen_car() throws Exception {
		
		
		//Click on stolen car from main menu
		List<WebElement> Car_Finder = wait(driver, 20).until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'STOLEN CAR')]")));
		for (int i = 0; i < Car_Finder.size(); i++) {
			if (i == 1) {
				Thread.sleep(2000);
				Car_Finder.get(i).click();
				Thread.sleep(5000);
			}
		}
			
		//Click on car icon
		List<WebElement> Car_icon = wait(driver, 20).until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//img[@src='/assets/cars/red_car-f7395f64cae1a8925bdb53b96cdd8b9cebfd3d18d5ebe51f34648611fc706f7b.png']")));
		
		
		
		//If the whole map
		if(Car_icon.size()==4)
		for (int i = 0; i < Car_icon.size(); i++) {
			if (i == 3) {
				Thread.sleep(2000);
				Car_icon.get(i).click();
			}		
		}
		
		if(Car_icon.size()==2)
			for (int i = 0; i < Car_icon.size(); i++) {
				if (i == 1) {
					Thread.sleep(2000);
					Car_icon.get(i).click();
				}		
			}
		
		
		
		//Get location text
	String act_address=	wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gm-style-iw-d']//span"))).getText();
	Thread.sleep(3000);
	
	String exp_address=	wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='address']"))).getText();	
	
	//Validating both address
	softassert.assertEquals(act_address, exp_address);
	
	//Click on Home bottom menu button
	wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Home')]"))).click();
	driver.navigate().refresh();
	Thread.sleep(4000);
		softassert.assertAll();
	}
	
}
