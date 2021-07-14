package com.autobrain.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Testing extends Login{

	WebDriver driver;
	
	@Test
	public void check() throws Exception {
		driver = getDriver();
		login("testuser5470@mailcatch.com", "welcome");
		driver.get("https://stg.autobrain.com/cars/plan_information");
		
		
		
		VisibilityOfElementByXpath("//input[@placeholder='Search For Car']", 10).sendKeys("9315777258");
		Thread.sleep(1000);
		String tier3 = VisibilityOfElementByXpath("//div[@class='plan-info-container']", 10).getText();
		System.out.println("Vip Plan "+tier3);
		
		System.out.println();
		VisibilityOfElementByXpath("//input[@placeholder='Search For Car']", 10).clear();
		VisibilityOfElementByXpath("//input[@placeholder='Search For Car']", 10).sendKeys("6668249391");
		Thread.sleep(1000);
		String tier1 = VisibilityOfElementByXpath("//div[@class='plan-info-container']", 10).getText();
		System.out.println("Money Saver "+tier1);
		
		Assert.assertEquals(tier1, tier3);
		
	}
}
