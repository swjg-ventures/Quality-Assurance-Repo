package Demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.pages.Login;

public class BulkMonitor extends Login {
	boolean success_msg;
	
	public void addMonitor() throws Exception {
		login("john@example.com", "welcome");
		
		VisibilityOfElementByXpath("//li[@class='hooper-slide column is-active is-current']//div[contains(text(),'MONITOR/DRIVER')]", 15).click();	
		
		//Click on Add new monitor button	
		VisibilityOfElementByXpath("//button[contains(text(),'Add New Monitor')]", 15).click();
		
		for (int i=0; i<50; i++)
		{
		
		
		//Validate form open
		boolean form_open = VisibilityOfAllElementsByXpath("//label[contains(text(),'Monitor First Name')]", 15).size()==1;
		Assert.assertEquals(form_open, true,"Add Monitor form not opened!");
				
		//Storing all elements to fill up Monitor form
		List<WebElement> mon_ele= VisibilityOfAllElementsByXpath("//div[@class='gWHzj82vXtHhEXQk0erkB_0']/input", 15);
					
		//Enter Monitor First Name
		mon_ele.get(0).sendKeys("Monitor");
		
		//Enter Monitor Last Name
		mon_ele.get(1).sendKeys(""+i+1);
				
		//Monitor Email
		mon_ele.get(2).sendKeys("monitor"+(i+1)+"@mailinator.com");
				
		//Monitor phone number
		mon_ele.get(3).sendKeys("8527419632");

		//Submit button
		VisibilityOfElementByXpath("//button[contains(text(),'Submit')]", 15).click();
		Thread.sleep(5000);		
				
		//Verify success message after click on submit button
		success_msg = VisibilityOfAllElementsByXpath("//div[@class='flash-message text-center success']", 15).size()==1;			
		Assert.assertEquals(success_msg, true, "Monitor not added!");
		
		try
		{
		while (wait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='flash-message text-center success']"))).size()==1) 
		{
			
		}
		}
		
		catch(Exception e) 
		{
			System.out.println("Flash message not found!");
		}
			System.out.println(i+1+" "+"Monitor added successfully");
		}
		
		Thread.sleep(1000);
		
	}
}
