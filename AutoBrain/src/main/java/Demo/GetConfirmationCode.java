package Demo;

import org.testng.Assert;

import com.pages.Login;

public class step4 extends Login{
	
	public void vvlogin() throws Exception {
		
		login("junk7078@mailinator.com", "welcome");
		
		// Turn on low fuel alert notification
		VisibilityOfElementByXpath("//span[text()='Low Fuel Notifications']/following-sibling::div/div/div", 10).click();
		
		// Toggle status
		String fuel_noti_toggle_status = VisibilityOfElementByXpath("//span[text()='Low Fuel Notifications']/following-sibling::div/div/div/span", 10).getText();
		
		// Validate notification toggle has turned ON
		Assert.assertEquals(fuel_noti_toggle_status, "ON", "Unable to turn ON fuel notification toggle!");
		
		// Click on advance setting to set fuel percentage notification
		VisibilityOfElementByXpath("//span[text()='Low Fuel Notifications']/following-sibling::div//i", 10).click();
		
		// Validate low fuel notifications settings page opened
		boolean low_fuel_noti_settings_page_opened = VisibilityOfAllElementsByXpath("//h4[text()='Low Fuel Notifications Settings']", 10).size()==1;
		
		Assert.assertEquals(low_fuel_noti_settings_page_opened, true, "Low fuel notifications settings page not opened!");
		
		// Set fuel percentage to 50%
		VisibilityOfElementByXpath("//span[contains(text(),'50%')]/following-sibling::div/div", 10).click();
		Thread.sleep(2000);
		
		// Validate percentage set to 50 or not
		boolean is_percentage_set_to_50 = VisibilityOfElementByXpath("//span[contains(text(),'50%')]/following-sibling::div/div/span", 10).getText().contains("ON");
	
		Assert.assertEquals(is_percentage_set_to_50, true, "Unable to set fuel percentage to 50! Status not turned ON.");
	}

}
