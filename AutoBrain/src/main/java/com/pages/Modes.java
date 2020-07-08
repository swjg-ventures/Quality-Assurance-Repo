package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class Modes extends Login {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	boolean mode_status = true;
	String create_safe_zone = "//div[text()='Safe Zone:']/following-sibling::div[2]//span[2]",
			add_your_home_address = "//div[text()='Arrived And Left Home Alerts:']/following-sibling::div[2]//span[2]",
			add_work_address = "//div[text()='Arrived and Left Work Alerts:']/following-sibling::div[2]//span[2]",
			add_new_address = "//div[text()='Arrived and Left Anywhere Alerts:']/following-sibling::div[2]//span[2]";

	public void createGeoFence() throws Exception {
		login("junk4679@mailinator.com", "welcome");
		isDesktopNotificationAlert();

		try {
			// Click on Mode from main menu
			List<WebElement> mode = wait(driver, 20).until(
					ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'MODES')]")));
			mode.get(1).click();
		} catch (Exception e) {
			mode_status = false;
			e.printStackTrace();
		}

		Assert.assertEquals(mode_status, true, "Mode button is disabled!");
		customMode(create_safe_zone);
		customMode(add_your_home_address);
		customMode(add_work_address);
		customMode(add_new_address);
	}

	public void customMode(String type_of_creating_zone) throws Exception {

		// Scroll until the safe zone button not found
		WebElement scrollUntilEleFound = wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Create a Safe Zone']")));
		js.executeScript("arguments[0].scrollIntoView();", scrollUntilEleFound);
		Thread.sleep(1000);

		// Create safe zone
		VisibilityOfElementByXpath(type_of_creating_zone, 10).click();

		// Input address or location
		VisibilityOfElementByXpath("//input[@placeholder='Address or Location']", 10).sendKeys("boca");

		// Validate list fetch
		boolean isAddressListFetch = VisibilityOfAllElementsByXpath("//div[@class='_12V4dTH1KHShAbyaEfEsMl_0']", 10)
				.size() != 0;
		Assert.assertEquals(isAddressListFetch, true, "Address list not fetched!");
		Thread.sleep(1000);

		// Select first address from the fetched list
		VisibilityOfElementByXpath("//div[@class='_12V4dTH1KHShAbyaEfEsMl_0'][1]", 10).click();
		Thread.sleep(1500);

		// Click on submit button
		VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 10).click();

		boolean isTitleErrorDisplayed = false;
		try {
			isTitleErrorDisplayed = VisibilityOfElementByXpath("//input[@placeholder='Title']", 2).isDisplayed();
		} catch (Exception e) {

		}

		if (isTitleErrorDisplayed) {
			VisibilityOfElementByXpath("//input[@placeholder='Title']", 10).sendKeys("Test_zone");
			VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 10).click();
		}

		// Validate safe zone created
		boolean isSafeZoneCreated = false;
		try {
			isSafeZoneCreated = VisibilityOfAllElementsByXpath("//div[@class='_1q_9csrLzQW0wMP2_ltCQd_0']", 15)
					.size() == 1;
		} catch (Exception e) {

		}

		Assert.assertEquals(isSafeZoneCreated, true, "Safe zone not created!");

		// Delete the created zone
		if (isSafeZoneCreated) {
			Thread.sleep(1500);
			VisibilityOfElementByXpath("//i[@class='fa fa-trash _2XkqKinBcWdxg7eyNtIeUK_0']", 10).click();
		}

		// Validate zone deleted
		boolean isSafeZoneDeleted = false;
		try {
			isSafeZoneCreated = VisibilityOfAllElementsByXpath("//div[@class='_1q_9csrLzQW0wMP2_ltCQd_0']", 10)
					.size() == 1;
		} catch (Exception e) {

		}

		Assert.assertEquals(isSafeZoneDeleted, false, "Safe zone not deleted!");

	}

}
