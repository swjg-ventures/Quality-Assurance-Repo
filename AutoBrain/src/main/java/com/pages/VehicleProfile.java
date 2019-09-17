package com.pages;


import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class VehicleProfile extends ForgotPassword {

	public void VehicleProfileInfo() throws Exception {
		String carname1 = "Steven's car", caricon1 = "//div[@class='grid']/div[10]", status1 = "//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div", year1 = "2015", make1 = "SKODA"; int model1 = 2;	
		String carname2 = "Steven's car", caricon2 = "//div[@class='grid']/div[1]", status2 = "//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div", year2 = "2018", make2 = "VOLVO"; int model2 = 6;

		
		
//Shuffling variables		
		//Car name
		String Scarname[]  = {carname1, carname2};
		String carname = Scarname[new Random().nextInt(Scarname.length)];
	
		
		//Car icon
		String Scaricon[]  = {caricon1, caricon2};
		String caricon = Scaricon[new Random().nextInt(Scaricon.length)];
		
		
		//Status
		String Sstatus[]  = {status1, status2};
		String status = Sstatus[new Random().nextInt(Sstatus.length)];
		
		//Year
		String Syear[]  = {year1, year2};
		String year = Syear[new Random().nextInt(Syear.length)];
		
		//Make
		String Smake[]  = {make1, make2};
		String make = Smake[new Random().nextInt(Smake.length)];
		
		//Model
		int Smodel[]  = {model1, model2};
		int model = Smodel[new Random().nextInt(Smodel.length)];
		
		
		
		String licenceno1 = "TY78", effectivedate1 = "//div[@class='text-input'][2]/div/div[2]/div/span[contains(text(),'25')]", expiredate1 = "//div[@class='text-input'][3]/div/div[2]/div/span[contains(text(),'28')]", insurancecmpy1 = "Demo", policyno1 = "55512";
		String licenceno2 = "TY89", effectivedate2 = "//div[@class='text-input'][2]/div/div[2]/div/span[contains(text(),'26')]", expiredate2 = "//div[@class='text-input'][3]/div/div[2]/div/span[contains(text(),'27')]", insurancecmpy2 = "Test", policyno2 = "44125";
		
		//License
		String Slicenceno[]  = {licenceno1, licenceno2};
		String licenceno = Slicenceno[new Random().nextInt(Slicenceno.length)];
		
		//Effective date
		String Seffectivedate[]  = {effectivedate1, effectivedate2};
		String effectivedate = Seffectivedate[new Random().nextInt(Seffectivedate.length)];
		
		//Expire date
		String Sexpiredate[]  = {expiredate1, expiredate2};
		String expiredate = Sexpiredate[new Random().nextInt(Sexpiredate.length)];
		
		//Insurance company
		String Sinsurancecmpy[] = {insurancecmpy1, insurancecmpy2};
		String insurancecmpy =Sinsurancecmpy[new Random().nextInt(Sinsurancecmpy.length)];
		
		
		//Insurance company
		String Spolicyno[] = {policyno1, policyno2};
		String policyno =Spolicyno[new Random().nextInt(Spolicyno.length)];
		
		
		

		// Calling Login Method
//		ValidLogin();

		
			Thread.sleep(3000);
			// Opening Navigation Menu
			wait(driver, 5).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='hamburger-container']")))
					.click();
			Thread.sleep(1000);

			// Click on Vehicle Profile from Menu
			wait(driver, 5)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Vehicle Profile')]")))
					.click();
			Thread.sleep(2000);

			// Car Name
			wait(driver, 5)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[@class='new-device-form']/div[1]/input")))
					.clear();

			wait(driver, 5)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[@class='new-device-form']/div[1]/input")))
					.sendKeys(carname);
			Thread.sleep(2000);

			// Expanding Car Icon drop-down
			wait(driver, 5).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select-dropdown']/div")))
					.click();
			Thread.sleep(2000);

			// Selecting Car Icon from drop-down
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(caricon))).click();
			Thread.sleep(3000);

			// Is your car hybrid status (ON/OFF)
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(status))).click();
			Thread.sleep(2000);

			// Car Year
			Select y = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][1]/div[2]/select")));
			y.selectByVisibleText(year);
			Thread.sleep(2000);

			// Car Make
			Select m = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][2]/div[2]/select")));
			m.selectByVisibleText(make);
			Thread.sleep(2000);

			// Car Model
			Select mo = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][3]/div[2]/select")));
			mo.selectByIndex(model);
			Thread.sleep(2000);

//NEW FORM CAR INSURANCE	(Click on Edit button)
			wait(driver, 5)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[@class='car-insurance-header']/div[2]/span")))
					.click();
			Thread.sleep(2000);

			// License Plate No
			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='TYP013']")))
					.clear();
			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='TYP013']")))
					.sendKeys(licenceno);
			Thread.sleep(2000);

			// Effective Date (Clicking to open date calendar)
			wait(driver, 5)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[@class='text-input'][2]/div/div[1]/input")))
					.click();
			Thread.sleep(1000);

			// Selecting date from opened calendar
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(effectivedate))).click();
			Thread.sleep(1000);

			// Expiration Date (Clicking to open date calendar)
			wait(driver, 5)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[@class='text-input'][3]/div/div[1]/input")))
					.click();
			Thread.sleep(1000);

			// Selecting expire date from opened calendar
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expiredate))).click();
			Thread.sleep(1000);

			// Enter insurance company name
			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name']")))
					.clear();
			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name']")))
					.sendKeys(insurancecmpy);
			Thread.sleep(2000);

			// Enter policy number
			wait(driver, 5).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='X00 0000-000-000']")))
					.clear();
			wait(driver, 5)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//input[@placeholder='X00 0000-000-000']")))
					.sendKeys(policyno);
			Thread.sleep(4000);

			// Clicking on Save button
			WebElement ele = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
			Thread.sleep(2000);
			ele.click();
			Thread.sleep(3000);
			//Finish car insurance form

//NEW FORM CAR REGISTRATION (Click on Edit button) 
			wait(driver, 5).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='car-registration-header']/div[2]/span")))
					.click();
			Thread.sleep(2000);

			// License Plate No
			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='TYP013']")))
					.clear();
			Thread.sleep(2000);

			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='TYP013']")))
					.sendKeys(licenceno);
			Thread.sleep(2000);

			// Expiration Date (Clicking to open date calendar)
			wait(driver, 5).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Pick A Date']")))
					.click();
			Thread.sleep(1000);

			// Selecting expire date from opened calendar
			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='vdp-datepicker__calendar'][1]/div/span[contains(text(),'25')]")))
					.click();
			Thread.sleep(1000);

			// Save button
			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Save')]")))
					.click();
			Thread.sleep(2000);
			//Finish Register Form
			

			// Submit button
			wait(driver, 5).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]")))
					.click();
			Thread.sleep(10000);
			
			// Get last inserted Car Name
		String actcarname= 	wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='new-device-form']/div[1]/input"))).getAttribute("value");
		Assert.assertEquals(actcarname, carname);	
		Thread.sleep(2000);
		
	}
}
