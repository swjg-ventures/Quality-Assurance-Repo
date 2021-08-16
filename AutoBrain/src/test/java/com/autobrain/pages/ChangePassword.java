package com.autobrain.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ChangePassword extends Login {
	boolean home_page_loaded, actresult;
	String currentpass = null, newpass = null, confirmnewpass = null;
	String email = "junking4946@yopmail.com";

//EXPAND MENU BUTTON	
	@FindBy(className = "ellipsis-opener")
	WebElement expand_menu_btn;

//CHANGE PASSWORD BUTTON
	@FindBy(xpath = "//a[contains(text(),'Change Password')]")
	WebElement change_password_btn;

//CURRENT PASSWORD FIELD
	@FindBy(xpath = "//div[@class='input-container'][1]/div[2]/input")
	WebElement current_password_field;

//NEW PASSWORD FIELD
	@FindBy(xpath = "//div[@class='input-container'][2]/div[2]/input")
	WebElement new_password_field;

//CONFIRM PASSWORD FIELD
	@FindBy(xpath = "//div[@class='input-container'][3]/div[2]/input")
	WebElement confirm_password_field;

//UPDATE PASSWORD BUTTON
	@FindBy(xpath = "//button[contains(text(),'UPDATE')]")
	WebElement update_btn;

//SUCCESS MESSAAGE
	@FindBy(xpath = "//div[@class='flash-message text-center success']")
	List<WebElement> success_msg;

	public void changePassword() throws Exception {

		PageFactory.initElements(getDriver(), this);

		// Resetting password will help to know the current password
		ForgotPassword fpass = new ForgotPassword();
		fpass.forgotPassword(email);

		currentpass = fpass.reset_pass;
		newpass = GetCurrentDateTime().replace(" ", "");
		confirmnewpass = newpass;

		// Click on corner dots to expand the menu
		Thread.sleep(2000);
		expand_menu_btn.click();
		Thread.sleep(1000);

		// Select Change Password from expanded drop-down
		change_password_btn.click();

		// Current password
		current_password_field.clear();
		current_password_field.sendKeys(currentpass);
		Thread.sleep(1000);

		// New password
		new_password_field.clear();
		new_password_field.sendKeys(newpass);
		Thread.sleep(1000);

		// Confirm new password
		confirm_password_field.clear();
		confirm_password_field.sendKeys(confirmnewpass);
		Thread.sleep(1000);

		// Click on update button
		update_btn.click();
		Thread.sleep(3000);

		// Storing actual result
		actresult = success_msg.size() != 0;

		// Comparing actual result
		Assert.assertEquals(actresult, true,
				"Seems like password not updated successfully because no success message found!");

		// Wait unit the flash message disappear
		while (getDriver().findElements(By.xpath("//div[@class='flash-message text-center success']")).size() != 0) {
			System.out.println("Checking...");
			Thread.sleep(2000);
		}

		logout();
		loginWithNewPassword(newpass);

	}

	public void loginWithNewPassword(String new_pass) throws Exception {
		// Login with new password
		PageFactory.initElements(getDriver(), this);

		// Entering email
		wait(getDriver(), 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))).sendKeys(email);

		// Entering password
		wait(getDriver(), 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password")))
				.sendKeys(new_pass);

		// Click on login button
		wait(getDriver(), 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("commit"))).click();

		// Validation login
		while (home_page_loaded == false) {
			try {
				home_page_loaded = wait(getDriver(), 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//li[@class='hooper-slide column is-active is-current']//div[contains(text(),'CAR FINDER')]")))
						.isDisplayed();
			} catch (Exception e) {
				System.out.println("Home page not loaded properly.");
				break;
			}
		}

		Assert.assertEquals(getDriver().getCurrentUrl(), "https://stg.autobrain.com/");
		isDesktopNotificationAlert();
		Thread.sleep(2000);
	}

}