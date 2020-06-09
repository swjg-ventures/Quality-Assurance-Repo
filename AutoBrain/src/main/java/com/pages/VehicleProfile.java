package com.pages;



import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class VehicleProfile extends ForgotPassword {
	String carname1 = "Steven's car", caricon1 = "//div[@class='grid']/div[1]", status1 = "//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div", 
			year1 = "2015", make1 = "SKODA", VIN1 = "DHSJ879373J738H"; int model1 = 2;	
			
	String carname2 = "Steven's car", caricon2 = "//div[@class='grid']/div[10]", status2 = "//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div", 
			year2 = "2018", make2 = "VOLVO",  VIN2 = "MCSJ879373J736G"; int model2 = 6;

	String model_selected, Insurance_Eff_Date, Insurance_Exp_Date, car_Reg_exp;
	
	//Shuffling variables		
	//Car name
	String Scarname[]  = {carname1, carname2};
	String carname = Scarname[new Random().nextInt(Scarname.length)];

	//Car VIN
	String Svin[]  = {VIN1, VIN2};
	String VIN = Svin[new Random().nextInt(Scarname.length)];
	
	
	
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
	
	
	
	
	public void VehicleProfileInfo() throws Exception {

			login("temp781@mailinator.com", "welcome");
			
			// Opening Navigation Menu
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='hamburger-container']"))).click();
			

			// Click on Vehicle Profile from Menu
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Vehicle Profile')]"))).click();
			

			// Car Name
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='new-device-form']/div[1]/input"))).clear();

			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='new-device-form']/div[1]/input")))
			.sendKeys(carname);
	

			// Expanding Car Icon drop-down
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select-dropdown']/div")))
			.click();
			

			// Selecting Car Icon from drop-down
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(caricon))).click();
			

			// Enter VIN number
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'VIN Number')]/following-sibling::div/input")))
			.clear();

			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'VIN Number')]/following-sibling::div/input")))
			.sendKeys(VIN);
			
			
			
			// Is your car hybrid status (ON/OFF)
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(status))).click();
			Thread.sleep(2000);

			// Car Year
			Select y = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][1]/div[2]/select")));
			y.selectByVisibleText(year);
			

			// Car Make
			Select m = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][2]/div[2]/select")));
			m.selectByVisibleText(make);
			

			// Car Model
			Select mo = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][3]/div[2]/select")));
			mo.selectByIndex(model);
			model_selected=mo.getFirstSelectedOption().getText().trim();
			

			//Fill Up Insurance and Registration Forms
			Ins_Reg_Forms(); 
			

			// Submit button
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]")))
			.click();
			
			//Validate page loaded completely
			String page_load= wait(driver, 40).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Vehicle Profile')]")))
					.getText();
			
			softassert.assertEquals(page_load , "Vehicle Profile");
			Thread.sleep(2000);
			
			//VALIDATING ALL DATA
			validate_data();
	}
	
	
	//Insurance and Registration forms
	public void Ins_Reg_Forms() throws Exception {
		//NEW FORM CAR INSURANCE	(Click on Edit button)
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='car-insurance-header']/div[2]/span")))
		.click();
		Thread.sleep(2000);

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
		Thread.sleep(2000);
		Insurance_Eff_Date = wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='text-input'][2]/label/following-sibling::div//input")))
		.getAttribute("value");
		
		
		// Expiration Date (Clicking to open date calendar)
		wait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='text-input'][3]/div/div[1]/input")))
		.click();
		Thread.sleep(1000);

		// Selecting expire date from opened calendar
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expiredate))).click();
		Thread.sleep(2000);
		Insurance_Exp_Date = wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='text-input'][3]/label/following-sibling::div//input")))
		.getAttribute("value");
		
		// Enter insurance company name
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name']")))
		.clear();
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name']")))
		.sendKeys(insurancecmpy);
		Thread.sleep(2000);

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
		Thread.sleep(3000);
		//Finish car insurance form

//NEW FORM CAR REGISTRATION (Click on Edit button) 
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='car-registration-header']/div[2]/span")))
		.click();
		Thread.sleep(2000);

		// License Plate No
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
		car_Reg_exp = wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Expiration Date')]/following-sibling::div//input")))
				.getAttribute("value");
		
		
		// Save button
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Save')]")))
		.click();
		Thread.sleep(2000);
		//Finish Register Form
	}
	
	
	
	
	
	// VALIDATE INSERTED DATA
	public void validate_data() throws Exception {
		
		// VALIDATE CAR NAME
		String act1 = wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='new-device-form']/div[1]/input"))).getAttribute("value");
		softassert.assertEquals(act1, carname, "Vehicle name is not updated!");	
		
		
		// VALIDATE VIN
		String act2= wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'VIN Number')]/following-sibling::div/input"))).getAttribute("value");
		softassert.assertEquals(act2, VIN, "Vehicle VIN no. is not updated!");	
		
		// VALIDATE Car icon 
		String Red_car_icon ="/packs/main/images/cars/red_car-5918da978accf5d006ec426ff0848381.png";
		String Gray_car_icon = "/packs/main/images/cars/dark_gray_car-c61e59ad0654efb25c0719d54a794eb3.png";
		
		WebElement element = driver.findElement(By.xpath("//div[@class='form-control _3qEkANrTzyuCnEsHMJPsxV_0']/div/img"));
		String act_colour = ((JavascriptExecutor)driver).executeScript("return arguments[0].attributes['src'].value;", element).toString();
		
		if(caricon==caricon1) {
			softassert.assertEquals(act_colour, Red_car_icon, "Vehicle icon is not updated!");
		}
		
		if(caricon==caricon2) {
			softassert.assertEquals(act_colour, Gray_car_icon, "Vehicle icon is not updated!");
		}	
		
		
		// YEAR
		Select y1 = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][1]/div[2]/select")));
		String act4 = y1.getFirstSelectedOption().getText().trim();
		
		softassert.assertEquals(act4, year, "Vehicle year is not updated!");
		
		//Validate Car's Make
		Select y2 = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][2]/div[2]/select")));
		String act5 = y2.getFirstSelectedOption().getText().trim();
		
		softassert.assertEquals(act5, make, "Vehicle make is not updated!");
		
		//Validate Car's Model
		Select y3 = new Select(driver.findElement(By.xpath("//div[@class='secondary-input'][3]/div[2]/select")));
		String act6 = y3.getFirstSelectedOption().getText().trim();
		
		if(make=="SKODA") {
			softassert.assertEquals(act6, model_selected, "Vehicle model is not updated!");			
		}
		if(make=="VOLVO") {
			softassert.assertEquals(act6, model_selected, "Vehicle model is not updated!");		
		}
		
		// Validate Effective Date
		String act7=	wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Effective Date')]/following-sibling::li"))).getText();
		softassert.assertEquals(act7, date_format(Insurance_Eff_Date), "Vehicle insurance effective date is not updated!" );	
		
		
		// Validate Expire Date
		String act8=	wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='car-insurance-info']//div[2]//li[2]"))).getText();
		softassert.assertEquals(act8, date_format(Insurance_Exp_Date), "Vehicle insurance expiring date is not updated!" );	
		
		// Validate Policy number
		String act9=	wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Policy Number')]/following-sibling::li"))).getText();
		softassert.assertEquals(act9, policyno, "Vehicle policy no. is not updated!" );	
		
		// Validate Insurance company name
		String act10=	wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Insurance Company')]/following-sibling::li"))).getText();
		softassert.assertEquals(act10, insurancecmpy, "Vehicle insurance company name is not updated!" );	
		
		// Validate Vehicle Registration Info (Expire Date)
		String act11=	wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='car-registration-info']//div[1]//li[2]"))).getText();
		softassert.assertEquals(act11, date_format(car_Reg_exp), "Vehicle registration expiring date is not updated!" );	
				
		softassert.assertAll();
	}
	
}
