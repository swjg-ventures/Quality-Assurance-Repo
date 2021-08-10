package com.autobrain.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import com.autobrain.base.Base;

public class Login extends Base {
	boolean homePage, desktopNotification;

	public void login(String email, String pass) throws Exception {

		// Entering email
		VisibilityOfElementByXpath("//input[@id='user_email']", 5).clear();
		VisibilityOfElementByXpath("//input[@id='user_email']", 5).sendKeys(email);

		// Entering password
		VisibilityOfElementByXpath("//input[@id='user_password']", 5).clear();
		VisibilityOfElementByXpath("//input[@id='user_password']", 5).sendKeys(pass);

		// Click on login button
		VisibilityOfElementByXpath("//input[@name='commit']", 5).click();

		// Validation login
		try {
			homePage = VisibilityOfElementByXpath(
					"//li[@class='hooper-slide column is-active is-current']//div[contains(text(),'CAR FINDER')]", 90)
							.isDisplayed();

		} catch (Exception e) {

		}

		try {
			// Click on Yes
			VisibilityOfElementByXpath("//button[contains(text(),'GOT IT')]", 2).click();
		}

		catch (Exception e) {

		}
		Assert.assertEquals(homePage, true, "Home page is not loaded!");
		isDesktopNotificationAlert();
	}

	public void isDesktopNotificationAlert() {
		try {
			desktopNotification = wait(getDriver(), 4).until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Desktop Notifications')]")))
					.size() == 1;

			getDriver().findElement(By.xpath("//button[contains(text(),'Yes')]")).click();
			Thread.sleep(1500);

		} catch (Exception e) {

		}
	}

	
	public void logout() throws Exception {

		VisibilityOfElementByClassName("hamburger-container", 10).click();
		VisibilityOfElementByXpath("//a[contains(text(),'LOG OUT')]", 10).click();

		String loginpage = "https://stg.autobrain.com/users/sign_in";
		Assert.assertEquals(getDriver().getCurrentUrl(), loginpage);

	}
}
