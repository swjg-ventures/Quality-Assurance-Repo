package com.autobrain.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CarFinder extends Login {
	By carFinderBtn = By.xpath("//div[contains(text(),'CAR FINDER')]");

	@Test
	public void carFinder() throws Exception {

		login("john@example.com", "welcome");

		isDesktopNotificationAlert();

		PresenceOfAllWebElements(carFinderBtn, 5).get(1).click();

		boolean isDisplayed = VisibilityOfElementByXpath("//span[contains(text(),'NAV TO CAR')]", 15).isDisplayed();

		// Validating correct page opened or not
		Assert.assertEquals(isDisplayed, true, "Car Finder page not opened!");

		// Click on navigate to car
		VisibilityOfElementByXpath("//span[contains(text(),'NAV TO CAR')]", 15).click();

		// Checking how many windows are opened currently
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());

		// Validating window opened or not
		Assert.assertEquals(tabs.size(), 2);

		getDriver().switchTo().window(tabs.get(1));
		Thread.sleep(1000);
		getDriver().switchTo().window(tabs.get(1)).close();

		// Switching to main window
		getDriver().switchTo().window(tabs.get(0));

		// Click on spotlight
		VisibilityOfElementByXpath("//a[contains(text(),'SPOTLIGHT')]", 15).click();

		// Validating correct page opened or not
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://stg.autobrain.com/spotlight");

		// Closing the spotlight description by clicking on the cross button
		VisibilityOfElementByXpath("//i[@class='fa fa-times _3N4180tmOGF0PjzMAODtFh_0']", 15).click();

		Thread.sleep(2000);

		// Turning status ON
		VisibilityOfElementByXpath("//div[@class='spotlight-car'][1]/div[1]/div[2]", 15).click();

		// Checking the status is ON or not
		List<WebElement> status = PresenceOfAllElementsByXpath("//span[contains(text(),'ON')]", 15);
		Thread.sleep(2000);

		for (int i = 0; i < status.size(); i++) {
			if (i == 1) {
				String status_info = status.get(i).getText();
				Thread.sleep(1000);

				// Validating, status should be ON
				Assert.assertEquals(status_info, "ON");
			}
		}

		// Closing the opened alert box
		VisibilityOfElementByXpath("//div[@class='spotlight-car'][1]//button", 15).click();
		Thread.sleep(2000);

		// Turning status OFF
		VisibilityOfElementByXpath("//div[@class='spotlight-car'][1]/div[1]/div[2]", 15).click();

		Thread.sleep(2000);
		// Validating status OFF or not
		boolean status_off = getDriver().findElements(By.xpath("//span[contains(text(),'ON')]")).size() == 0;
		Assert.assertEquals(status_off, true);
	}
}
