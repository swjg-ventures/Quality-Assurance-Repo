package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.base.Base;

public class TermsAndConditions extends Base {

	
	// Main method
	public void termsAndPrivacy() throws Exception {
		termsandConditions();
		privayPolicy();
	}
	
	

	public void termsandConditions() throws Exception {
		// Clicking on sign-up button
		VisibilityOfElementByXpath("//a[contains(text(),'SIGN UP')]", 10).click();

		// Clicking on terms and condition footer link
		VisibilityOfElementByXpath("//a[contains(text(),'Terms of Use')]", 10).click();
		Thread.sleep(2000);

		// Storing actual page url
		String expectedpage = "https://stg.autobrain.com/terms";

		// Validating actual page opened or not with url
		Assert.assertEquals(driver.getCurrentUrl(), expectedpage);

		// Storing actual title of terms and condition page
		String ActHeading = driver.findElement(By.xpath("//h4[contains(text(),'Terms of Use and End User License')]"))
				.getText();

		// Validating terms and condition title is matching or not
		Assert.assertEquals(ActHeading, "Terms of Use and End User License");

		// Navigating back user to login page
		driver.navigate().to(url);

	}


	public void privayPolicy() throws Exception {
		// Clicking on sign-up button
		VisibilityOfElementByXpath("//a[contains(text(),'SIGN UP')]", 10).click();

		// Clicking on Privacy policy
		VisibilityOfElementByXpath("//a[contains(text(),'Privacy Policy')]", 10).click();
		Thread.sleep(2000);

		// Storing actual page url
		String expectedpage = "https://stg.autobrain.com/privacy_policy";

		// Validating actual page opened or not
		Assert.assertEquals(driver.getCurrentUrl(), expectedpage);

		// Storing actual heading of privacy policy page
		String ActHeading = VisibilityOfElementByXpath("//h4[contains(text(),'Privacy Policy')]", 10).getText();

		// Validating Privacy policy title
		Assert.assertEquals(ActHeading, "Privacy Policy");

		// Navigating back user to login page
		driver.navigate().to(url);
	}


	public void chatButton() throws Exception {
		VisibilityOfElementByXpath("//a[contains(text(),'SIGN UP')]", 10).click();

		// Click on chat icon
		boolean isSignupPageDisplayed = VisibilityOfElementByXpath("//input[@placeholder='Phone Number']", 10)
				.isDisplayed();

		Assert.assertEquals(isSignupPageDisplayed, true, "Signup page not displayed!");

		driver.switchTo().frame("fc_widget");
		Thread.sleep(1000);

		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("chat-content"))).click();

		// Writing message in chat box
		VisibilityOfElementByID("app-conversation-editor", 10).sendKeys("Hello, this is for test!");

		Thread.sleep(3000);
		// Now, pressing the Enter button from keyboard in order to submit the query in
		// inbox
		WebElement textbox = driver.findElement(By.id("app-conversation-editor"));
		textbox.sendKeys(Keys.ENTER);
		Thread.sleep(5000);

		// Validating text sent in in-box or not
		boolean text = wait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("app-conversation-editor"))).getText()
				.isEmpty();
		Assert.assertEquals(text, true);

		// Closing the opened chat box
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("minimize"))).click();
		Thread.sleep(2000);
		driver.navigate().to(url);

	}

}
