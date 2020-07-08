package com.pages;


import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


public class ManageAccount extends Login {
	SoftAssert softassert = new SoftAssert();

	// 1. METHOD- UDPATE CONTACT INFO METHOD
	public void UpdatingContactInfo() throws Exception {


		// Click on corner dots to expand the menu
		wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.className("ellipsis-opener"))).click();
		Thread.sleep(2000);

		// Click on update contact info from expanded drop-down
		wait(driver, 20).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Update Contact Info')]")))
				.click();

		for (int i = 0; i < 2; i++) {
			String fname = null, lname = null, email = null, ph = null;
			if (i == 0) {
				fname = "Shon";
				lname = "Gill";
				email = "shon@mailinator.com";
				ph = "9876543210";

			}

			if (i == 1) {
				fname = "John";
				lname = "Example";
				email = "john@example.com";
				ph = "1234567890";

			}

			// Giving some wait to load the data
			Thread.sleep(2000);
			// Updating first name
			wait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).clear();
			wait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).sendKeys(fname);

			// Updating last name
			wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName"))).clear();
			wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName"))).sendKeys(lname);

			// Updating Email
			wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).clear();
			wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(email);

			// Updating Phone Number
			wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("phoneNumber"))).clear();
			wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("phoneNumber"))).sendKeys(ph);

			// Click on Update button
			wait(driver, 20).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'UPDATE')]")))
					.click();

			// Enter password before click on Submit button
			wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("password")))
					.sendKeys("welcome");
			Thread.sleep(2000);

			// Click on submit button to udpate the details
			wait(driver, 20).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]")))
					.click();

			// Storing success message
			String successmsg = wait(driver, 20)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Your information')]")))
					.getText();

			// Validating success message appear or not after update
			Assert.assertEquals(successmsg, "Your information has been updated!");
			Thread.sleep(4000);
		}
	}




	// 2. METHOD- PAYMENTS METHODS (Add New Credit Card)
	public void AddNewCreditCard() throws Exception {
		// Calling Login Method to get user login
		// ValidLogin();

		// Click on corner dots to expand the menu
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.className("ellipsis-opener"))).click();
		Thread.sleep(2000);

		// Click on update contact info from expanded drop-down
		wait(driver, 15).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Payment Methods')]")))
				.click();
		
		
		while(driver.findElements(By.xpath("//button[contains(text(),'Add A New Credit Card')]")).size()!=1)
		{
			System.out.println("Waiting for the visibility of Add A New Credit Card Button");
			Thread.sleep(1500);
		}
		
		
		
		
		
		
		// Clicking on Add new credit card button
		wait(driver, 15)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add A New Credit Card')]")))
				.click();

		// Enter First name
		wait(driver, 15)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='First Name']")))
				.sendKeys("Jhon");

		// Enter Last name
		wait(driver, 15)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Last Name']")))
				.sendKeys("example");

		// Enter Billing Address
		wait(driver, 15)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//input[@placeholder='Billing Address']")))
				.sendKeys("#123 street tower");

		// Enter City
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='City']")))
				.sendKeys("New York");
		Thread.sleep(2000);

		// Select State
		Select state = new Select(wait(driver, 20).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//option[contains(text(),'State')]//parent::select"))));
		state.selectByVisibleText("Alaska");

		// Add card number
		wait(driver, 15)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Card Number']")))
				.sendKeys("4000056655665556");

		// Select Month
		Select month = new Select(wait(driver, 20).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//option[contains(text(),'Month')]//parent::select"))));
		month.selectByIndex(11);
		Thread.sleep(2000);

		// Select Year
		Select year = new Select(wait(driver, 20).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//option[contains(text(),'Year')]//parent::select"))));
		year.selectByIndex(8);

		// Enter CVV
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='CVV']")))
				.sendKeys("111");

		// Enter Zip Code
		wait(driver, 15)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Zip Code']")))
				.sendKeys("160065");

		// Click on check-box (Make This Your Primary Card)
		wait(driver, 15).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']")))
				.click();

		// Click on Save button
		wait(driver, 15)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Save')]")))
				.click();
		
		//Store result message
		String msg = wait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Success')]"))).getText();
		
		//Validate result
		Assert.assertEquals(msg, "Success", "Success message not found!");

	}

	// 3. METHOD- PAYMENTS METHODS (Update Credit Card)
	public void UpdateCreditCard() throws Exception {
		String Fname1 = "Jordan", Lname1 = "J", Billingadd1 = "New billing 12", City1 = "Demo city", State1 = "Texas";
		int mnth1 = 10, yr1 = 7;
		String Fname2 = "Trim", Lname2 = "T", Billingadd2 = "Demo address 123", City2 = "London", State2 = "Washington";
		int mnth2 = 11, yr2 = 8;

		// Billing address
		String Sbilling[] = { Billingadd1, Billingadd2 };
		String Billingadd = Sbilling[new Random().nextInt(Sbilling.length)];

		// FirstName
		String Sfname[] = { Fname1, Fname2 };
		String Fname = Sfname[new Random().nextInt(Sfname.length)];

		// LastName
		String Slname[] = { Lname1, Lname2 };
		String Lname = Slname[new Random().nextInt(Slname.length)];

		// City
		String Scity[] = { City1, City2 };
		String City = Scity[new Random().nextInt(Scity.length)];

		// Month
		int Smnth[] = { mnth1, mnth2 };
		int mnth = Smnth[new Random().nextInt(Smnth.length)];

		// Year
		int Syr[] = { yr1, yr2 };
		int yr = Syr[new Random().nextInt(Syr.length)];

		// State
		String Sstate[] = { State1, State2 };
		String State = Sstate[new Random().nextInt(Sstate.length)];

		// Calling Login Method to get user login
//		ValidLogin();
		Thread.sleep(4000);
		// Click on corner dots to expand the menu
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.className("ellipsis-opener"))).click();
		Thread.sleep(2000);

		// Click on update contact info from expanded drop-down
		wait(driver, 20).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Payment Methods')]")))
				.click();
		Thread.sleep(5000);

		// Select credit card and click on update button
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(text(),'MasterCard')]/following-sibling::a/button"))).click();

		Thread.sleep(7000);
		// Enter First name
		wait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='First Name']")))
				.clear();
		wait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='First Name']")))
				.sendKeys(Fname);
		Thread.sleep(2000);

		// Enter Last name
		wait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Last Name']")))
				.clear();
		wait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Last Name']")))
				.sendKeys(Lname);
		Thread.sleep(2000);

		// Enter Billing Address
		wait(driver, 5).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Billing Address']")))
				.clear();
		wait(driver, 5)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//input[@placeholder='Billing Address']")))
				.sendKeys(Billingadd);
		Thread.sleep(2000);

		// Enter City
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='City']")))
				.clear();
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='City']")))
				.sendKeys(City);
		Thread.sleep(2000);

		// Select State
		Select state = new Select(driver.findElement(By.xpath("//option[contains(text(),'State')]//parent::select")));
		state.selectByVisibleText(State);
		Thread.sleep(2000);

		// Select Month
		Select month = new Select(driver.findElement(By.xpath("//option[contains(text(),'Month')]//parent::select")));

		month.selectByIndex(mnth);
		Thread.sleep(2000);

		// Select Year
		Select year = new Select(driver.findElement(By.xpath("//option[contains(text(),'Year')]//parent::select")));

		year.selectByIndex(yr);
		Thread.sleep(2000);

		// Click on Save button to update the info
		wait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Save')]")))
				.click();
		Thread.sleep(4000);
//			Assert.assertEquals(driver.getCurrentUrl(), "https://stg.autobrain.com/payments/card_info");

	}

	
	// 7. METHOD- PRINT ROADSIDE CARD
	public void PrintRoadsideCard() throws Exception {
//		ValidLogin();
		Thread.sleep(4000);
		// Click on corner dots to expand the menu
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.className("ellipsis-opener"))).click();
		Thread.sleep(2000);

		// Click on update contact info from expanded drop-down
		wait(driver, 5).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Print Roadside Card')]")))
				.click();
		Thread.sleep(3000);

		// Emergency Contact #1
		// Enter Name
		wait(driver, 5)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@class='contact'][1]/div[2]/div[1]/input")))
				.sendKeys("John");
		Thread.sleep(1000);

		// Enter Phone number
		wait(driver, 5)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@class='contact'][1]/div[2]/div[2]/input")))
				.sendKeys("1234567890");
		Thread.sleep(1000);

		// Emergency Contact #2
		// Enter Name
		wait(driver, 5)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@class='contact'][2]/div[2]/div[1]/input")))
				.sendKeys("Albert");
		Thread.sleep(1000);

		// Enter Phone number
		wait(driver, 5)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@class='contact'][2]/div[2]/div[2]/input")))
				.sendKeys("9998885552");

//		// Opening Printer Dialog box
//		Robot robot = new Robot();
//		robot.keyPress(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_P);
//		robot.keyRelease(KeyEvent.VK_P);
//		robot.keyRelease(KeyEvent.VK_CONTROL);
//		Thread.sleep(4000);
//
//		// Closing Printer Dialog box
//		robot.keyPress(KeyEvent.VK_ESCAPE);
//		robot.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(2000);

	}

}
