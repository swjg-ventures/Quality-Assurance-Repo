package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

// 3.
public class ManageAccount extends Signup {

	// 1. METHOD- UDPATE CONTACT INFO METHOD
	public void UpdatingContactInfo() throws Exception {
		// Calling Login Method to get user login
		ValidLogin();

		// Click on corner dots to expand the menu
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.className("ellipsis-opener"))).click();
		Thread.sleep(2000);

		// Click on update contact info from expanded drop-down
		wait(driver, 5).until(
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
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).clear();
			Thread.sleep(1000);
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).sendKeys(fname);

			// Updating last name
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName"))).clear();
			Thread.sleep(1000);
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName"))).sendKeys(lname);

			// Updating Email
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).clear();
			Thread.sleep(1000);
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(email);

			// Updating Phone Number
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("phoneNumber"))).clear();
			Thread.sleep(1000);
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("phoneNumber"))).sendKeys(ph);
			Thread.sleep(2000);

			// Click on Update button
			wait(driver, 5).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'UPDATE')]")))
					.click();

			// Enter password before click on Submit button
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("welcome");
			Thread.sleep(2000);

			// Click on submit button to udpate the details
			driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
			Thread.sleep(1000);

			// Storing success message
			String successmsg = wait(driver, 5)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Your information')]")))
					.getText();

			// Validating success message appear or not after update
			Assert.assertEquals(successmsg, "Your information has been updated!");
			Thread.sleep(4000);
		}
	}

	// 2. METHOD- CHANGE PASSWOR (Blank details)
	public void ChangePasswordWIthBlankDetails() throws Exception {

		// Calling Login Method to get user login
//		ValidLogin();
		Thread.sleep(2000);

		// Click on corner dots to expand the menu
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("ellipsis-opener"))).click();
		Thread.sleep(2000);

		// Select Change Password from expanded drop-down
		wait(driver, 5).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Change Password')]")))
				.click();

		// Click on update button
		wait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'UPDATE')]")))
				.click();

		Thread.sleep(2000);
		boolean act = driver.findElements(By.xpath("//div[@class='popper error-message']")).size() != 0;

		// Validating result
		Assert.assertEquals(act, true);

		// Now, click on Cancel button
		wait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='router-link-active']")))
				.click();
		
		Thread.sleep(1000);
	}

	// 3. METHOD- CHANGE PASSWORD (With wrong details)
	public void ChangePasswordWIthWrongDetails() throws Exception {

		// Click on corner dots to expand the menu
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("ellipsis-opener"))).click();
		Thread.sleep(2000);

		// Select Change Password from expanded drop-down
		wait(driver, 5).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Change Password')]")))
				.click();

		Thread.sleep(1000);
		// Current password
		wait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id=\"control-screen\"]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/input")))
				.sendKeys("123456");

		Thread.sleep(1000);
		// New password
		wait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id=\"control-screen\"]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/input")))
				.sendKeys("Rajesh@4321");

		Thread.sleep(1000);
		// Confirm new password
		wait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id=\"control-screen\"]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/input")))
				.sendKeys("Rajesh@4321");

		Thread.sleep(1000);
		// Click on update button
		wait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'UPDATE')]")))
				.click();

		// Storing actual result
		boolean actresult = driver.findElements(By.xpath("//div[@class='flash-message text-center error']"))
				.size() != 0;

		// Comparing actual result
		Assert.assertEquals(actresult, true);
		Thread.sleep(1000);
	}

	// 4. METHOD- CHANGE PASSWORD (With correct details)
	public void ChangePasswordWIthCorrectDetails() throws Exception {

		for (int i = 0; i < 2; i++) {
			String currentpass = null, newpass = null, confirmnewpass = null;
			if (i == 0) {
				currentpass = "welcome";
				newpass = "Rajesh@4321";
				confirmnewpass = "Rajesh@4321";
			}

			if (i == 1) {
				currentpass = "Rajesh@4321";
				newpass = "welcome";
				confirmnewpass = "welcome";
			}

			Thread.sleep(1000);
			// Current password
			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id=\"control-screen\"]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/input")))
					.clear();

			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id=\"control-screen\"]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/input")))
					.sendKeys(currentpass);

			Thread.sleep(1000);
			// New password
			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id=\"control-screen\"]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/input")))
					.clear();

			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id=\"control-screen\"]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/input")))
					.sendKeys(newpass);

			Thread.sleep(1000);
			// Confirm new password
			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id=\"control-screen\"]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/input")))
					.clear();

			wait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id=\"control-screen\"]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/input")))
					.sendKeys(confirmnewpass);

			Thread.sleep(1000);
			// Click on update button
			wait(driver, 5).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'UPDATE')]")))
					.click();
			Thread.sleep(2000);
			// Storing actual result
			boolean actresult = driver.findElements(By.xpath("//div[@class='flash-message text-center error']"))
					.size() == 0;

			// Comparing actual result
			Assert.assertEquals(actresult, true);
			Thread.sleep(5000);

		}

	}
	
	
	
	
	// 4. METHOD- PAYMENTS METHODS (Add card) 
		public void PaymentMethodWithCorrectDetails() throws Exception {
			// Calling Login Method to get user login
			ValidLogin();
			
			// Click on corner dots to expand the menu
			wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.className("ellipsis-opener"))).click();
			Thread.sleep(2000);

			// Click on update contact info from expanded drop-down
			wait(driver, 5).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Payment Methods')]")))
					.click();
			Thread.sleep(2000);
			
			// Clicking on Add new credit card button
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add A New Credit Card')]"))).click();
			
			//Enter First name
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='First Name']"))).sendKeys("Jhon");
			Thread.sleep(2000);
			
			
			//Enter Last name
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Last Name']"))).sendKeys("example");
			Thread.sleep(2000);
			
			
			//Enter Billing Address
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Billing Address']"))).sendKeys("#123 street tower");
			Thread.sleep(2000);
			
			
			//Enter City
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='City']"))).sendKeys("New York");
			Thread.sleep(2000);
			
			
			//Select State
			Select state = new Select (driver.findElement(By.
					xpath("//*[@id=\"control-screen\"]/div/div/div/div[2]/div[2]/div[3]/div/div/div[5]/div[2]/select")));
			
			state.selectByVisibleText("Alaska");
			Thread.sleep(2000);
			
			
			//Add card number
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Card Number']"))).
			sendKeys("4000056655665556");
			Thread.sleep(2000);
			
			
			//Select Month
			Select month = new Select (driver.findElement(By.
					xpath("//*[@id=\"control-screen\"]/div/div/div/div[2]/div[2]/div[3]/div/div/div[7]/div[1]/select")));
			
			month.selectByIndex(11);
			Thread.sleep(2000);
			
			
			
			//Select Year
			Select year = new Select (driver.findElement(By.
					xpath("//*[@id=\"control-screen\"]/div/div/div/div[2]/div[2]/div[3]/div/div/div[7]/div[2]/select")));
			
			year.selectByIndex(8);
			Thread.sleep(2000);
			
			
			//Enter CVV
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='CVV']"))).
			sendKeys("111");
			Thread.sleep(2000);
									
			
			//Enter Zip Code
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Zip Code']"))).
			sendKeys("160065");
			Thread.sleep(2000);
			
			
			//Click on check-box (Make This Your Primary Card)
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='tjXd8Trvz_04Wlld4lvGW_0']"))).click();
			
			
			
			//Click on Save button
			wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Save')]"))).click();
			
			
		}

}
