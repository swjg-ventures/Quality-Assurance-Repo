package com.autobrain.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CustomerService extends Login {

	public void customerService() throws Exception {
		login("john@example.com", "welcome");

		// Click on customer service
		List<WebElement> Car_Finder = wait(getDriver(), 20).until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'CUSTOMER SERVICE')]")));
		for (int i = 0; i < Car_Finder.size(); i++) {
			if (i == 1) {
				Thread.sleep(2000);
				Car_Finder.get(i).click();
			}
		}
		
		//Click on Send Message button to open chat
		wait(getDriver(), 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='contact-button call']/following-sibling::button"))).click();
		
		//Start chat
		getDriver().switchTo().frame("fc_widget");
		Thread.sleep(2000);
		
		//Writing message in chat box
		wait(getDriver(), 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("app-conversation-editor")))
		.sendKeys("Hello, this is for test!");
		
		Thread.sleep(1000);
		//Now, pressing the Enter button from keyboard in order to submit the query in inbox
		WebElement textbox = getDriver().findElement(By.id("app-conversation-editor"));
		textbox.sendKeys(Keys.ENTER); 
		Thread.sleep(5000);
		
		//Validating text sent in inbox or not
		boolean text =	wait(getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("app-conversation-editor"))).getText().isEmpty();
		softassert.assertEquals(text, true);
		
		//Closing the opened chat box
		wait(getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("minimize"))).click();
		getDriver().switchTo().parentFrame();
		Thread.sleep(4000);
		
	}
}
