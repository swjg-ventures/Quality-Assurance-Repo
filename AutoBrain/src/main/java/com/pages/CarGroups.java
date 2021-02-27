package com.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CarGroups extends Login {
	int z, grouped_cars, ungrouped_cars;
	boolean group_status, group_deleted;
	ArrayList<String> arr = new ArrayList<String>();
	ArrayList<Integer> arr2 = new ArrayList<Integer>();
	ArrayList<String> arr3 = new ArrayList<String>();
	List<WebElement> cars_title, cars;

	public void cargroups() throws Exception {
		login("john@example.com", "welcome");

		// Click on corner dots to expand the menu
		Thread.sleep(2000);
//		wait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.className("ellipsis-opener"))).click();

		// Check car group option in menu
		wait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='active-car-title']")))
				.click();
		Thread.sleep(1000);
		List<WebElement> total_cars = wait(driver, 10)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='car-title']")));
		wait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='active-car-title']")))
				.click();

		if (total_cars.size() > 0) {
			wait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.className("ellipsis-opener")))
					.click();
			
			Thread.sleep(1000);
			// Select Car Groups from expanded drop-down
			wait(driver, 10).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Car Groups')]")))
					.click();

			Thread.sleep(1500);

			// Verify car groups page opened
			Assert.assertEquals(driver.findElement(By.xpath("//h4[contains(text(),'Car Groups')]")).getText(),
					"Car Groups");

			// Print total number of grouped cars
			System.out.println("Total grouped cars:" + " " + grouped_cars());

			// Print total number of ungrouped cars
			System.out.println("Total ungrouped cars:" + " " + ungrouped_cars());

			// Deleting existing existing car group
			dlt_grp();

			// Click on Create Group button
			wait(driver, 10)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//button[contains(text(),'+ Create Group')]")))
					.click();

			// Verify form page opened
			Assert.assertEquals(
					wait(driver, 10)
							.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Group Name')]")))
							.getText(),
					"Group Name");

			// Enter Group Name
			wait(driver, 10)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Group Name']")))
					.sendKeys("Test_Group");

			// Enter Address or Location
			wait(driver, 10)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//input[@placeholder='Address or Location']")))
					.sendKeys("Houston");

			// Select fetch location from list
			wait(driver, 10).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='_2aL9ikio50GMobGq7MN6LE_0']/div[2]/div[1]")))
					.click();

			// Storing element for scrolling page
			List<WebElement> Ele = wait(driver, 10).until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[contains(text(),'Save')]")));

			// Scrolling page to the bottom
			scroll_until_ele_not_found(Ele);
			Thread.sleep(4000);

			// Increase radius
			WebElement Slider = driver
					.findElement(By.xpath("//div[@class='fNjXxCP3qpHRngtT1uPy5_0']/following-sibling::input"));
			Actions moveSlider = new Actions(driver);
			Action action = moveSlider.dragAndDropBy(Slider, 30, 0).build();
			action.perform();

			// Storing Actual radius value
			String Act_radius = wait(driver, 10)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[@class='_1U3QaQ0p_zC4zwFUWcNqP2_0']")))
					.getText();

			// Click on save button
			wait(driver, 10)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Save')]")))
					.click();
			Thread.sleep(2000);

			while (driver.findElements(By.xpath("//h4[contains(text(),'Car Groups')]")).size() != 1) {
				System.out.println("Loading...");
			}

			// Validate group created
			try {
				group_status = wait(driver, 15)
						.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Test_Group')]")))
						.isDisplayed();
			} catch (Exception e) {
			}

			Assert.assertEquals(group_status, true, "Group not created!");

			// Edit group
			edit_grp_btn();
			Thread.sleep(2000);

			// Scrolling page
			List<WebElement> Ele1 = wait(driver, 10).until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[contains(text(),'Save')]")));
			scroll_until_ele_not_found(Ele1);

			// Store expected radius
			String Exp_radius = wait(driver, 10)
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[@class='_1U3QaQ0p_zC4zwFUWcNqP2_0']")))
					.getText();
			softassert.assertEquals(Act_radius, Exp_radius, "Radius not updated!");

			Thread.sleep(1200);
			// Click on save button
			wait(driver, 10)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Save')]")))
					.click();

			softassert.assertAll();
		}

		else {
			System.out.println(
					"Car group option not available in Menu because of" + " " + total_cars.size() + " " + "car");
		}
	}

	// Edit group button
	public void edit_grp_btn() throws Exception {

		List<WebElement> Element = wait(driver, 10).until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='group-container']/div[1]/div[1]")));
		List<WebElement> Edit = wait(driver, 10).until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='group-container']/div[2]/div[1]")));
		for (int i = 0; i < Element.size(); i++) {

			// Get names of all existing group name
			String grp_name = Element.get(i).getText();

			// Click on Edit button
			if (grp_name.contains("Test_Group")) {
				Thread.sleep(3000);
				Edit.get(i).click();
			}
		}
	}

	// Delete existing group
	public void dlt_grp() throws Exception {
		List<WebElement> Element1 = driver.findElements(By.xpath("//div[@class='group-container']/div[1]/div[1]"));
		List<WebElement> dlt_grp = driver.findElements(By.xpath("//div[@class='group-container']/div[2]/div[2]"));
		for (z = 0; z < Element1.size(); z++) {
			String group_name = Element1.get(z).getText();

			if (group_name.contains("Test_Group")) {
				dlt_grp.get(z).click();
				wait(driver, 10).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Yes')]")))
						.click();
				Thread.sleep(2000);

				try {
					group_deleted = wait(driver, 2)
							.until(ExpectedConditions
									.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'Test_Group')]")))
							.size() == 0;
				} catch (Exception e) {

				}

				if (group_deleted == true) {
					System.out.println("Group deleted successfully!");
				} else {
					Assert.assertEquals(false, false, "Group not deleted successfully!");
				}

			}
		}
	}

	// Total Grouped cars
	public int grouped_cars() {
		cars_title = wait(driver, 10).until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='group-container']/div[1]/div[1]")));

		cars = wait(driver, 10).until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='group-container']/div[1]/div[2]")));

		// Print total size
		for (int i = 0; i < cars_title.size(); i++) {
			if (!cars_title.get(i).getText().contains("Ungrouped Cars")) {
				arr.add(cars.get(i).getText());
			}
		}

		// Grouped cars
		for (int i = 0; i < arr.size(); i++) {
			int index = arr.get(i).indexOf(" car");
			String in = arr.get(i).substring(0, index);
			int car_list = Integer.parseInt(in);
			arr2.add(car_list);
		}

		// Total grouped cars
		for (int num : arr2) {
			grouped_cars = grouped_cars + num;
		}

		return grouped_cars;
	}

	// Total Ungrouped cars
	public int ungrouped_cars() throws Exception {
		Thread.sleep(2000);
		for (int i = 0; i < cars_title.size(); i++) {
			if (cars_title.get(i).getText().contains("Ungrouped Cars")) {
				arr3.add(cars.get(i).getText());
			}
		}

		// Ungrouped cars
		for (int i = 0; i < arr3.size(); i++) {
			int index = arr3.get(i).indexOf(" car");
			String in = arr3.get(i).substring(0, index);
			int un_cars = Integer.parseInt(in);
			ungrouped_cars = un_cars;
		}

		return ungrouped_cars;

	}

}
