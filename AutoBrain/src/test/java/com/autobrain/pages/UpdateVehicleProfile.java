package com.autobrain.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.autobrain.base.Base;
import com.autobrain.models.SignupModel;

public class UpdateVehicleProfile extends Base {
	Login login;
	String model_selected, Insurance_Eff_Date, Insurance_Exp_Date, car_Reg_exp, old_hybrid_status;

	@Test
	public void updateVehicleProfile() throws Exception {

		login = new Login();
		login.login("lib1@mailinator.com", "welcome");

		// Opening Navigation Menu
		VisibilityOfElementByXpath("//div[@class='hamburger-container']", 10).click();
		Thread.sleep(1000);

		// Click on Vehicle Profile from Menu
		VisibilityOfElementByXpath("//span[contains(text(),'Vehicle Profile')]", 10).click();
		Thread.sleep(1000);

		// Car Name
		VisibilityOfElementByXpath("//div[@class='new-device-form']/div[1]/input", 10).clear();

		VisibilityOfElementByXpath("//div[@class='new-device-form']/div[1]/input", 10).sendKeys(SignupModel.getCarname());

		// Expanding Car Icon drop-down
		VisibilityOfElementByXpath("//div[@class='select-dropdown']/div", 10).click();
		Thread.sleep(2000);

		// Selecting Car Icon from drop-down
		VisibilityOfElementByXpath(SignupModel.getCaricon(), 10).click();

		// Enter VIN number
		VisibilityOfElementByXpath("//label[contains(text(),'VIN Number')]/following-sibling::div/input", 10).clear();

		VisibilityOfElementByXpath("//label[contains(text(),'VIN Number')]/following-sibling::div/input", 10)
				.sendKeys(SignupModel.getVIN());
		
		// Get car hybrid old status before update
		old_hybrid_status = SignupModel.getCar_hybrid_status();

		// Is your car hybrid status (ON/OFF)
		VisibilityOfElementByXpath("//div[@class='_3fRlA9ofZ3wg1XWxsL0y2l_0']/parent::div", 10).click();
		Thread.sleep(1000);

		// Car Year
		Select y = new Select(getDriver().findElement(By.xpath("//div[@class='secondary-input'][1]/div[2]/select")));
		y.selectByVisibleText(SignupModel.getYear());

		// Car Make
		Select m = new Select(getDriver().findElement(By.xpath("//div[@class='secondary-input'][2]/div[2]/select")));
		m.selectByVisibleText(SignupModel.getMake());

		// Car Model
		Select mo = new Select(getDriver().findElement(By.xpath("//div[@class='secondary-input'][3]/div[2]/select")));
		mo.selectByIndex(SignupModel.getModel());
		model_selected = mo.getFirstSelectedOption().getText().trim();

		// Fill Up Insurance and Registration Forms
		insRegForm();

		// Submit button
		VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 10).click();

		// Validate page loaded completely
		Assert.assertTrue(VisibilityOfElementByXpath("//h4[contains(text(),'Vehicle Profile')]", 20).isDisplayed(),
				"Vehicle profile page not loaded!");

		getDriver().navigate().refresh();

		// Validate page loaded completely after refresh
		Assert.assertTrue(VisibilityOfElementByXpath("//h4[contains(text(),'Vehicle Profile')]", 20).isDisplayed(),
				"Vehicle profile page not loaded after refresh the page!");

		Thread.sleep(1000);

		// VALIDATING ALL DATA
		validateVehicleProfileInfo();
	}

	// Insurance and Registration forms
	public void insRegForm() throws Exception {

		// NEW FORM CAR INSURANCE (Click on Edit button)
		VisibilityOfElementByXpath("//div[@class='car-insurance-header']/div[2]/span", 5).click();
		Thread.sleep(2000);

		// License Plate No
		VisibilityOfElementByXpath("//input[@placeholder='TYP013']", 5).clear();

		VisibilityOfElementByXpath("//input[@placeholder='TYP013']", 5).sendKeys(SignupModel.getLicenceno());
		Thread.sleep(2000);

		// Effective Date (Clicking to open date calendar)
		VisibilityOfElementByXpath("//div[@class='text-input'][2]/div/div[1]/input", 10).click();
		Thread.sleep(1000);

		// Selecting date from opened calendar
		VisibilityOfElementByXpath(SignupModel.getEffectivedate(), 10).click();

		// Expiration Date (Clicking to open date calendar)
		VisibilityOfElementByXpath("//div[@class='text-input'][3]/div/div[1]/input", 10).click();
		Thread.sleep(1000);

		// Selecting expire date from opened calendar
		VisibilityOfElementByXpath(SignupModel.getExpiredate(), 10).click();

		// Enter insurance company name
		VisibilityOfElementByXpath("//input[@placeholder='Name']", 10).clear();

		VisibilityOfElementByXpath("//input[@placeholder='Name']", 10).sendKeys(SignupModel.getInsurancecmpy());

		// Enter policy number
		VisibilityOfElementByXpath("//input[@placeholder='X00 0000-000-000']", 10).clear();

		VisibilityOfElementByXpath("//input[@placeholder='X00 0000-000-000']", 10).sendKeys(SignupModel.getPolicyno());
		Thread.sleep(2000);

		// Clicking on Save button
		WebElement ele = getDriver().findElement(By.xpath("//button[contains(text(),'Save')]"));
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(1500);
		ele.click();

		Thread.sleep(1000);
		Insurance_Eff_Date = VisibilityOfElementByXpath("//li[contains(text(),'Effective Date')]/following-sibling::li",
				5).getText();

		Insurance_Exp_Date = VisibilityOfElementByXpath("//div[@class='car-insurance-info']//div[3]//li[2]", 5)
				.getText();
		// Finish car insurance form

		// NEW FORM CAR REGISTRATION (Click on Edit button)
		VisibilityOfElementByXpath("//div[@class='car-registration-header']/div[2]/span", 5).click();
		Thread.sleep(2000);

		// License Plate No
		VisibilityOfElementByXpath("//input[@placeholder='TYP013']", 5).clear();
		Thread.sleep(2000);

		VisibilityOfElementByXpath("//input[@placeholder='TYP013']", 5).sendKeys(SignupModel.getLicenceno());
		Thread.sleep(2000);

		// Expiration Date (Clicking to open date calendar)
		VisibilityOfElementByXpath("//input[@placeholder='Pick A Date']", 5).click();
		Thread.sleep(1000);

		// Selecting expire date from opened calendar
		VisibilityOfElementByXpath("//div[@class='vdp-datepicker__calendar'][1]/div/span[contains(text(),'25')]", 5)
				.click();
		Thread.sleep(1000);

		// Save button
		VisibilityOfElementByXpath("//button[contains(text(),'Save')]", 5).click();
		Thread.sleep(1000);

		car_Reg_exp = VisibilityOfElementByXpath("//div[@class='car-registration-info']//div[2]//li[2]", 5).getText();
		// Finish Register Form
	}

	// VALIDATE INSERTED DATA
	public void validateVehicleProfileInfo() throws Exception {

		// VALIDATE CAR NAME
		String cName = VisibilityOfElementByXpath("//div[@class='new-device-form']/div[1]/input", 5)
				.getAttribute("value");
		
		if (SignupModel.getCaricon() == SignupModel.caricon1) {
			softassert.assertEquals(cName, SignupModel.getCarname(), "Vehicle name is not updated!");
		}

		if (SignupModel.getCaricon() == SignupModel.caricon2) {
			softassert.assertEquals(cName, SignupModel.getCarname(), "Vehicle name is not updated!");
		}

		// VALIDATE VIN
		String act2 = VisibilityOfElementByXpath("//label[contains(text(),'VIN Number')]/following-sibling::div/input",
				5).getAttribute("value");
		softassert.assertEquals(act2, SignupModel.getVIN(), "Vehicle VIN no. is not updated!");

		
		// VALIDATE car hybrid status
		if(old_hybrid_status.equals(SignupModel.getCar_hybrid_status())) {
			softassert.assertFalse(true, "Car hybrid status not updated!");
		}
		
		
		
		// VALIDATE Car icon
		String Red_car_icon = "/packs/main/images/cars/red_car-5918da978accf5d006ec426ff0848381.png";
		String Gray_car_icon = "/packs/main/images/cars/dark_gray_car-c61e59ad0654efb25c0719d54a794eb3.png";

		WebElement element = getDriver()
				.findElement(By.xpath("//div[@class='form-control _3qEkANrTzyuCnEsHMJPsxV_0']/div/img"));
		String act_colour = ((JavascriptExecutor) getDriver())
				.executeScript("return arguments[0].attributes['src'].value;", element).toString();

		if (SignupModel.getCaricon() == SignupModel.caricon1) {
			softassert.assertEquals(act_colour, Red_car_icon, "Vehicle icon is not updated!");
		}

		if (SignupModel.getCaricon() == SignupModel.caricon2) {
			softassert.assertEquals(act_colour, Gray_car_icon, "Vehicle icon is not updated!");
		}

		// YEAR
		Select y1 = new Select(getDriver().findElement(By.xpath("//div[@class='secondary-input'][1]/div[2]/select")));
		String act4 = y1.getFirstSelectedOption().getText().trim();

		softassert.assertEquals(act4, SignupModel.getYear(), "Vehicle year is not updated!");

		// Validate Car's Make
		Select y2 = new Select(getDriver().findElement(By.xpath("//div[@class='secondary-input'][2]/div[2]/select")));
		String act5 = y2.getFirstSelectedOption().getText().trim();

		softassert.assertEquals(act5, SignupModel.getMake(), "Vehicle make is not updated!");

		// Validate Car's Model
		Select y3 = new Select(getDriver().findElement(By.xpath("//div[@class='secondary-input'][3]/div[2]/select")));
		String act6 = y3.getFirstSelectedOption().getText().trim();

		if (SignupModel.getMake() == "SKODA") {
			softassert.assertEquals(act6, model_selected, "Vehicle model is not updated!");
		}
		if (SignupModel.getMake() == "VOLVO") {
			softassert.assertEquals(act6, model_selected, "Vehicle model is not updated!");
		}

		// Validate Effective Date
		String act7 = VisibilityOfElementByXpath("//li[contains(text(),'Effective Date')]/following-sibling::li", 5)
				.getText();
		softassert.assertEquals(act7, (Insurance_Eff_Date), "Vehicle insurance effective date is not updated!");

		// Validate Expire Date
		String act8 = VisibilityOfElementByXpath("//div[@class='car-insurance-info']//div[2]//li[2]", 5).getText();
		softassert.assertEquals(act8, (Insurance_Exp_Date), "Vehicle insurance expiring date is not updated!");

		// Validate Policy number
		String act9 = VisibilityOfElementByXpath("//li[contains(text(),'Policy Number')]/following-sibling::li", 5)
				.getText();
		softassert.assertEquals(act9, SignupModel.getPolicyno(), "Vehicle policy no. is not updated!");

		// Validate Insurance company name
		String act10 = VisibilityOfElementByXpath("//li[contains(text(),'Insurance Company')]/following-sibling::li", 5)
				.getText();
		softassert.assertEquals(act10, SignupModel.getInsurancecmpy(),
				"Vehicle insurance company name is not updated!");

		// Validate Vehicle Registration Info (Expire Date)
		String act11 = VisibilityOfElementByXpath("//div[@class='car-registration-info']//div[1]//li[2]", 5).getText();
		softassert.assertEquals(act11, (car_Reg_exp), "Vehicle registration expiring date is not updated!");

		softassert.assertAll();
	}

}
