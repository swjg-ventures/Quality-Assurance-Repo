package com.pages;

import java.util.ArrayList;

import org.testng.Assert;

public class ForgotPassword extends Login {
	String date = GetCurrentDateTime();
	String reset_pass = date.replace(" ", "");
	// Use this email for forgot password: junking4946@yopmail.com

	public void forgotPassword(String email) throws Exception {

		VisibilityOfElementByXpath("//a[contains(text(),'Forgot password')]", 20).click();

		// Validating user redirected to actual page or not
		Assert.assertEquals(driver.getCurrentUrl(), "https://stg.autobrain.com/users/password/new");

		// Entering registered email in forgot password field
		VisibilityOfElementByID("forgot-password-email", 5).sendKeys(email);

		Thread.sleep(1500);

		// Click on submit button
		VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 10).click();

		// Validating success message
		Assert.assertTrue(
				VisibilityOfElementByXpath("//h2[contains(text(),'Email Sent')]", 30).getText().contains("Email Sent"));

		// Validate customer receive replacement email
		driver.get("http://www.yopmail.com/en/");
		Thread.sleep(2000);

		VisibilityOfElementByXpath("//input[@id='login']", 15).sendKeys(email);

		// Click on check for new email button
		VisibilityOfElementByXpath("//input[@class='sbut']", 15).click();
		Thread.sleep(2000);

		// Switch to frame
		driver.switchTo().frame("ifmail");

		// Validate email received
		Assert.assertTrue(
				VisibilityOfElementByXpath("//td[contains(text(),'We received a request to reset your password')]", 10)
						.isDisplayed());

		// Click on set new password button link
		VisibilityOfElementByXpath("//a[contains(text(),'Set New Password')]", 10).click();

		// Checking how many windows are opened currently
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		// Switching to Buy now page
		driver.switchTo().window(tabs.get(1));

		// Confirm reset password page opened
		Assert.assertTrue(
				VisibilityOfElementByXpath("//label[contains(text(),'Confirm New Password')]", 10).isDisplayed());

		// Enter new password
		VisibilityOfElementByXpath("//input[@id='user_password']", 10).sendKeys(reset_pass);
		Thread.sleep(1000);

		// Enter confirm new password
		VisibilityOfElementByXpath("//input[@id='user_password_confirmation']", 10).sendKeys(reset_pass);
		Thread.sleep(1000);

		// Submit
		VisibilityOfElementByXpath("//input[@id='submitPassword']", 10).click();
		Thread.sleep(1000);

		// Now user should login automatically after submit
		Assert.assertTrue(driver.getCurrentUrl().contentEquals("https://stg.autobrain.com/"));

		// Validate the loading of home page
		// Validation login
		try {
			homePage = VisibilityOfElementByXpath(
					"//li[@class='hooper-slide column is-active is-current']//div[contains(text(),'CAR FINDER')]", 90)
							.isDisplayed();

		} catch (Exception e) {

		}
		Assert.assertEquals(homePage, true, "Home page is not loaded!");

	}

}
