package com.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertSettings extends Login {
	boolean car_ins_form, car_reg_form;
	String 	licenceno = "TY78", effectivedate = "//div[@class='text-input'][2]/div/div[2]/div/span[contains(text(),'25')]", 
			expiredate = "//div[@class='text-input'][3]/div/div[2]/div/span[contains(text(),'28')]", insurancecmpy = "Demo", 
			policyno = "55512";

	int i,j;
	ArrayList<String> arr = new ArrayList<String>();
	
	public void alert_settings() throws Exception {
		
		// Login
//		login();
		Thread.sleep(2000);
		// Sliding to next page
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='hooper-indicator']"))).click();
		
		// Click on alert settings button
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[4]/div[3]/a/div[2]"))).click();
		Thread.sleep(2000);

		
		
		// Storing all status
		List<WebElement> status = wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']")));
		

		// Storing all status ON / OFF name
		List<WebElement> on_off = wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']//span")));

		
		
		for(i=0; i<status.size(); i++) {
			Thread.sleep(2000);
			
			status.get(i).click();	
			
			
			
			
			try {
			 car_ins_form= wait(driver, 2).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'Add Your Car Insurance Info')]"))).size()==1;
			} 
			catch(Exception e) {
				car_ins_form=false;
			}
			 try {
			 car_reg_form= wait(driver, 2).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'Add Your Car Registration Info')]"))).size()==1;
			}
			 catch(Exception e) {
				 car_reg_form=false;
			 }
			 
			
			if(car_ins_form==true && i==7) {
			car_ins();
		
			}
			
			if(car_reg_form==true && i==8) {
				car_regis();
			}
			
			
			for(j=i; j==i; j++ ) {	
				if(j==7 || j==8) {
				Thread.sleep(5000);
			String ck = on_off.get(j).getText();
			System.out.println(ck);
				}
			arr.add(on_off.get(i).getText());			
			}
			
			
		}
		
		// Refreshing the page
		driver.navigate().refresh();
		try {
		boolean page_refresh=	wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Alert Settings')]"))).size()!=0;
		softassert.assertEquals(page_refresh, true, "Page not refreshed properly!");
		} catch (Exception e) {
			driver.navigate().refresh();
		}
		
		
		// Validating all status as per last click
		status();
		
		
		
		
		Thread.sleep(2000);
		softassert.assertAll();
	}
	
	// ADD CAR INSURANCE INFO METHOD
	public void car_ins() throws Exception {
		//NEW FORM CAR INSURANCE	(Click on Edit button)

		// License Plate No
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='TYP013']")))
		.clear();
		wait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='TYP013']")))
		.sendKeys(licenceno);
		Thread.sleep(2000);

		// Effective Date (Clicking to open date calendar)
		wait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='text-input'][2]/div/div[1]/input")))
		.click();
		Thread.sleep(1000);
		
		// Selecting date from opened calendar
		wait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(effectivedate))).click();	
		
		// Expiration Date (Clicking to open date calendar)
		wait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='text-input'][3]/div/div[1]/input")))
		.click();
		
		// Selecting expire date from opened calendar
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expiredate))).click();

		// Enter insurance company name
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name']")))
		.clear();
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name']")))
		.sendKeys(insurancecmpy);

		// Enter policy number
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='X00 0000-000-000']")))
		.clear();
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='X00 0000-000-000']")))
		.sendKeys(policyno);
		Thread.sleep(4000);

		// Clicking on Save button
		WebElement ele = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(2000);
		ele.click();
		//Finish car insurance form


	}
	
	// ADD CAR REGISTRATION INFO METHOD
	public void car_regis() throws Exception {
		//NEW FORM CAR REGISTRATION (Click on Edit button) 
			
				// License Plate No
				Thread.sleep(3000);
				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='TYP013']")))
				.clear();
				Thread.sleep(2000);

				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='TYP013']")))
				.sendKeys(licenceno);
				Thread.sleep(2000);

				// Expiration Date (Clicking to open date calendar)
				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Pick A Date']")))
				.click();
				Thread.sleep(1000);

				// Selecting expire date from opened calendar
				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vdp-datepicker__calendar'][1]/div/span[contains(text(),'25')]")))
				.click();
				Thread.sleep(1000);			
				
				// Save button
				wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Save')]")))
				.click();
				//Finish Register Form
	}
	
	
	
	// VALIDATE ALL STATUS (ON / OFF) METHOD
	public void status() throws Exception {
		Thread.sleep(2000);
		List<WebElement> On_Off_Status = wait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']//span")));
		
		// CAR HEALTH ALERTS
		String Exp1_Car_Health_Alerts = On_Off_Status.get(0).getText();
		softassert.assertEquals(arr.get(0),Exp1_Car_Health_Alerts);		
		System.out.println("CAR HEALTH ALERTS"+"-->"+Exp1_Car_Health_Alerts + "=" + arr.get(0) );
		
		// LOW BATTERY ALERTS
		String  Exp2_Car_Health_Alerts = On_Off_Status.get(1).getText();
		softassert.assertEquals(Exp2_Car_Health_Alerts, arr.get(1));
		softassert.assertEquals(arr.get(1), Exp2_Car_Health_Alerts);	
		System.out.println("LOW BATTERY ALERTS"+"-->"+Exp2_Car_Health_Alerts + "=" + arr.get(1) );
		
		// TOW ALERTS
		String  Exp3_Car_Health_Alerts = On_Off_Status.get(2).getText();
		softassert.assertEquals( arr.get(2), Exp3_Car_Health_Alerts);	
		System.out.println("TOW ALERTS"+"-->"+Exp3_Car_Health_Alerts + "=" + arr.get(2) );
		
		// CAR "LEFT ON" ALERTS
		String  Exp4_Car_Health_Alerts = On_Off_Status.get(3).getText();
		softassert.assertEquals(arr.get(3), Exp4_Car_Health_Alerts);	
		System.out.println("CAR \"LEFT ON\" ALERTS"+"-->"+Exp4_Car_Health_Alerts + "=" + arr.get(3) );
		
		// DEVICE DISCONNECTED ALERTS
		String  Exp5_Car_Health_Alerts = On_Off_Status.get(4).getText();
		softassert.assertEquals( arr.get(4),Exp5_Car_Health_Alerts);	
		System.out.println("DEVICE DISCONNECTED ALERTS"+"-->"+Exp5_Car_Health_Alerts + "=" + arr.get(4) );
		
		// MOTION DETECTION
		String  Exp6_Car_Health_Alerts = On_Off_Status.get(5).getText();
		softassert.assertEquals(arr.get(5), Exp6_Car_Health_Alerts);
		System.out.println("MOTION DETECTION"+"-->"+Exp6_Car_Health_Alerts + "=" + arr.get(5) );
		
		// MOBILE PHONE NOTIFICATIONS
		String  Exp7_Car_Health_Alerts = On_Off_Status.get(6).getText();
		softassert.assertEquals(arr.get(6), Exp7_Car_Health_Alerts);
		System.out.println("MOBILE PHONE NOTIFICATIONS"+"-->"+Exp7_Car_Health_Alerts + "=" + arr.get(6) );
		
		// INSURANCE RENEWAL
		Thread.sleep(5000);
		String  Exp8_Car_Health_Alerts = On_Off_Status.get(7).getText();
		softassert.assertEquals(arr.get(7), Exp8_Car_Health_Alerts);	
		System.out.println("INSURANCE RENEWAL"+"-->"+Exp8_Car_Health_Alerts + "=" + arr.get(7) );
		
		// VEHICLE REGISTATION RENEWAL
		Thread.sleep(5000);
		String  Exp9_Car_Health_Alerts = On_Off_Status.get(8).getText();
		softassert.assertEquals( arr.get(8), Exp9_Car_Health_Alerts);
		System.out.println("VEHICLE REGISTATION RENEWAL"+"-->"+Exp9_Car_Health_Alerts + "=" + arr.get(8) );
		
		
	}
	
}
