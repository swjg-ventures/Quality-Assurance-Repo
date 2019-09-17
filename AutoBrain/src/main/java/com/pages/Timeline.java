package com.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Timeline extends Login {

	public void Timeline_By_Date() throws Exception {
//		ValidLogin();
//		Thread.sleep(5000);

		//Get start location of car icon
		ArrayList<Integer> start = new ArrayList<Integer>();
		for (int i = 0; i < 1; i++) {
			Point p = driver.findElement(By.id("bigMapCarIcon")).getLocation();			
			int startx = p.getX();	int starty = p.getY();	
			
			start.add(startx);
			start.add(starty);				
			Thread.sleep(1000);			
			System.out.println(startx);
			System.out.println(starty);
		} 
				
		//Storing web-element for date change
		WebElement webElement = wait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tripPlayerDatepicker']/input")));

		// Changing date with java-script executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value',arguments[1]);", webElement, "Mon, Sep 2nd 2019");

		// Play	
		for(int i=0; i<2; i++) {
		wait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='map-date']"))).click();
		Thread.sleep(1000);
		}
	

		// Increase the moving speed of car
		for (int i = 0; i < 3; i++) {
			Thread.sleep(1000);
			wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa fa-forward']"))).click();		
		}
		Thread.sleep(5000);
		
		//Get end location of car icon
		ArrayList<Integer> end = new ArrayList<Integer>();
		  for(int i=0; i<1; i++) { 
			  Point p=	driver.findElement(By.id("bigMapCarIcon")).getLocation();		
			  int endx= p.getX();  int endy= p.getY();		  	  
			  end.add(endx);
			  end.add(endy);
			  
			  System.out.println(endx); 
			  System.out.println(endy);	  
		  }
		  System.out.println(start.toString());
		  System.out.println(end.toString());
		  //Validating both car location icon location
		 if(!start.equals(end)) {
			 softassert.assertEquals(true, true);
		 }
		 else {
			 softassert.assertEquals(true, false,"Start and end location points should not match!"); 
		 }
		  
		 
		 //Stop the car
		 wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='holder-bg col-xs-12']/div[1]/div[1]/div[2]"))).click();
		 Thread.sleep(2000);

		 
		 
		 softassert.assertAll();
	}
}
