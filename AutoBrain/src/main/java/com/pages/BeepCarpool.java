package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class BeepCarpool extends Login {
	boolean beep_page, form_page, member_created, beep_carpool_btn = true, beep_friends_btn = true;

	public void beepCarpool() throws Exception {

		login("lib1@mailinator.com", "welcome");

		// Sliding to next page
		Thread.sleep(2000);
		wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='hooper-indicator']")))
				.click();
		Thread.sleep(2000);

		// Click on beep carpool button
		VisibilityOfElementByXpath("//li[5]/div[6]/a/div[2]", 10).click();

		// Validate beep carpool page opened
		beep_page = wait(driver, 10).until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Add a New Carpool Member')]")))
				.size() == 1;
		Assert.assertEquals(beep_page, true, "Beep Carpool page not opened!");

		deleteAlreadyExistBeepCarpoolMember();

		// Click on Add a New Carpool Member button
		Thread.sleep(1000);
		wait(driver, 10)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Add a New Carpool Member')]")))
				.click();

		// Validate add member form opened
		form_page = wait(driver, 10).until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//button[contains(text(),'Add a New Carpool Member')]")))
				.size() == 1;
		Assert.assertEquals(form_page, true, "Form page not opened!");

		// Select Add a New Carpool Member option
		WebElement ele = wait(driver, 10).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='add-from-toggles']/button[1]")));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);

		String col = ele.getCssValue("background-color");
		System.out.println(col);

		// Enter first name
		wait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@class='beep-input gWHzj82vXtHhEXQk0erkB_0'][1]/input")))
				.sendKeys("Test_Carpool");

		// Enter last name
		wait(driver, 10).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='beep-input gWHzj82vXtHhEXQk0erkB_0'][2]/input")))
				.sendKeys("Member");

		// Enter phone number
		wait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@class='beep-input gWHzj82vXtHhEXQk0erkB_0'][3]/input")))
				.sendKeys("8965471235");

		// Enter address
		wait(driver, 10)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@class='gWHzj82vXtHhEXQk0erkB_0']/input")))
				.sendKeys("Houston");

		// Select fetch location from list
		wait(driver, 10).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='_2aL9ikio50GMobGq7MN6LE_0']/div[2]/div[1]")))
				.click();

		Thread.sleep(2500);
		// Click on submit button
		wait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]")))
				.click();

		// Validate group created
		try {
			member_created = wait(driver, 10)
					.until(ExpectedConditions
							.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Test_Carpool')]")))
					.size() == 1;
		} catch (Exception e) {
//			extractJSLogsInfo();
		}
		Assert.assertEquals(member_created, true, "Member not created!");
		driver.navigate().back();

		// Add member
		addFrndFromBeepCarpool();

		// Delete member
		deleteBeepFrnd();

	}

	public void addFrndFromBeepCarpool() throws Exception {

		Thread.sleep(2000);
		// Sliding to next page
		wait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='hooper-indicator']")))
				.click();

		// Click on beep carpool button
		VisibilityOfElementByXpath("//li[5]/div[5]/a/div[2]", 10).click();

		// Click on Add a New Carpool Member button
		wait(driver, 10)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Add a New Beep Friend')]")))
				.click();

		// Validate add member form opened
		form_page = wait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//button[contains(text(),'Add a Friend from Beep Carpool')]"))).size() == 1;
		Assert.assertEquals(form_page, true, "Form page not opened!");

		// Click on Add a New Carpool Member button
		wait(driver, 10).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add a Friend from Beep Carpool')]")))
				.click();

		// Select a friend from beep friend list
		Select s = new Select(
				driver.findElement(By.xpath("//select[@class='select gm0q1xaPzRR_hIlmNUybK_0 form-control']")));
		s.selectByVisibleText("Test_Carpool Member");

		// Click on submit button
		wait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]")))
				.click();

		try {
			member_created = wait(driver, 10)
					.until(ExpectedConditions
							.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Test_Carpool')]")))
					.size() == 1;
		} catch (Exception e) {
		}

	}

	// Deleted added member from carpool member list
	public void deleteBeepFrnd() throws Exception {
		boolean member_dlt = false;
		Thread.sleep(2000);
		// Click on added member
		wait(driver, 10)
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Test_Carpool Member')]")))
				.click();

		// Click on delete button
		wait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("delete-beep"))).click();

		// Select Delete option
		wait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Delete')]")))
				.click();

		Thread.sleep(2500);

		// Validate member deleted or not
		try {
			member_dlt = wait(driver, 5).until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Test_Carpool Member')]")))
					.size() == 1;
		} catch (Exception e) {

		}

		Assert.assertEquals(member_dlt, false, "Member not deleted!");
	}

	public void deleteAlreadyExistBeepCarpoolMember() throws Exception {
		List<WebElement> ele = null;
		try {
			ele = VisibilityOfAllElementsByXpath("//span[contains(text(),'Test_Carpool Member')]", 10);
		}

		catch (Exception e) {

		}

		if (ele != null) {

			for (int i = 0; i < ele.size(); i++) {
				List<WebElement> ele1 = VisibilityOfAllElementsByXpath("//span[contains(text(),'Test_Carpool Member')]",
						10);
				
				ele.get(i).click();
				VisibilityOfElementByXpath("//div[contains(text(),'DELETE BEEP')]", 10).click();
				Assert.assertTrue(
						VisibilityOfElementByXpath("//div[contains(text(),'Are you sure you want to delete')]", 10)
								.isDisplayed());
				VisibilityOfElementByXpath("//button[contains(text(),'Delete')]", 10).click();
				Thread.sleep(1500);
				List<WebElement> ele2 = null;

				try {
					ele2 = VisibilityOfAllElementsByXpath("//span[contains(text(),'Test_Carpool Member')]", 10);
					Assert.assertTrue(ele1.size() != ele2.size());
				}

				catch (Exception e) {
					
				}

			}
		}

	}

}
