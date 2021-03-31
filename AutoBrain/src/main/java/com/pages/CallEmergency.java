package com.pages;


import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CallEmergency extends Login {
	boolean error_msg;

	public void callEmergency() throws Exception {
		login("john@example.com", "welcome");

		// Click on call emergency button
		PresenceOfAllElementsByXpath("//div[contains(text(),'CALL EMERGENCY')]", 10).get(1).click();
		Thread.sleep(2000);

		// Select number of people involved in accident
		VisibilityOfElementByXpath("//div[@class='buttons-container']/button[2]", 10).click();

		// Select emergency service will be calling
		Select s = new Select(
				VisibilityOfElementByXpath("//div[@class='field-label'][3]/following-sibling::select", 10));
		s.selectByVisibleText("John Example");
		Thread.sleep(2000);

		// Click on call emergency button
		VisibilityOfElementByXpath("//button[contains(text(),'CALL EMERGENCY SERVICES TO THIS CAR')]", 10).click();
		Thread.sleep(7000);

		// Get error message
		try {
			error_msg = VisibilityOfAllElementsByXpath("//div[contains(text(),'There was an error')]", 10).size() == 0;

			// Click the opened message box
			VisibilityOfElementByXpath("//div[contains(text(),'There was an error')]/following-sibling::div/button", 10)
					.click();

			// Validating error message exist or not
			Assert.assertEquals(error_msg, true, "There was an error while attempting to request emergency services.");
		} catch (Exception e) {
			VisibilityOfElementByXpath(
					"//div[contains(text(),'Emergency Services have been')]/following-sibling::div/button", 10).click();
		}
		Assert.assertEquals(error_msg, false, "Success message not found!");

	}
}