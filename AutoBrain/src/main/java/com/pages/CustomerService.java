package com.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CustomerService extends Login {

	public void customer_service() throws Exception {


		// Click on customer service
		List<WebElement> Car_Finder = wait(driver, 20).until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'CUSTOMER SERVICE')]")));
		for (int i = 0; i < Car_Finder.size(); i++) {
			if (i == 1) {
				Thread.sleep(2000);
				Car_Finder.get(i).click();
			}
		}
		
		//Click on Send Message button to open chat
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='contact-button call']/following-sibling::button"))).click();
		
		//Start chat
		driver.switchTo().frame("fc_widget");
		Thread.sleep(2000);
		
		//Writing message in chat box
		wait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id("app-conversation-editor")))
		.sendKeys("Hello, this is for test!");
		
		Thread.sleep(1000);
		//Now, pressing the Enter button from keyboard in order to submit the query in inbox
		WebElement textbox = driver.findElement(By.id("app-conversation-editor"));
		textbox.sendKeys(Keys.ENTER); 
		Thread.sleep(5000);
		
		//Validating text sent in inbox or not
		boolean text =	wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("app-conversation-editor"))).getText().isEmpty();
		softassert.assertEquals(text, true);
		
		//Closing the opened chat box
		wait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("minimize"))).click();
		Thread.sleep(4000);
		
	}
}
