package Demo;

import java.awt.Robot;
import java.awt.event.InputEvent;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.pages.Login;

public class VCSPushDemo extends Login {

	public void vcsPush() throws Exception {
		login("john@example.com", "welcome");
		VisibilityOfElementByXpath("//div[contains(text(),'VCS')]", 15).click();

		Robot robot = new Robot();
		robot.mouseMove(1076, 328);
		int mask4 = InputEvent.BUTTON1_DOWN_MASK;
		robot.mousePress(mask4);
		robot.delay(75);
		robot.mouseRelease(mask4);
		Thread.sleep(3000);

		robot.mouseMove(1284, 327);
		int mask5 = InputEvent.BUTTON1_DOWN_MASK;
		robot.mousePress(mask5);
		robot.delay(75);
		robot.mouseRelease(mask5);
		Thread.sleep(3000);

		VisibilityOfElementByClassName("hamburger-container", 10).click();
		VisibilityOfElementByXpath("//div[@class='middle-section']//div[7]/span", 10).click();

		// Validate virtual car simulator page opened
		boolean IsVcsPageOpened = VisibilityOfElementByXpath("//h4[text()='Virtual Car Simulator ']", 15).isDisplayed();
		Assert.assertEquals(IsVcsPageOpened, true, "VCS page not opened!");

		// Expand first vcs point
		VisibilityOfElementByXpath("//div[@class='vcs-point-container'][1]", 10).click();

		// Select type of push
		Select s = new Select(VisibilityOfElementByXpath("//div[@class='vcs-point-container selected']//select", 15));
		s.selectByVisibleText("ignition on report - 61");


		// Expand second vcs point
		VisibilityOfElementByXpath("//div[@class='vcs-point-container'][1]", 10).click();

		// Select type of push
		Select ss = new Select(VisibilityOfElementByXpath("//div[@class='vcs-point-container selected']//select", 15));
		ss.selectByVisibleText("ignition off report - 62");

		

		// Click on send OBD button
		VisibilityOfElementByXpath("//button[text()='Send Obds']", 15).click();
		
		// Go to Home page
		VisibilityOfElementByXpath("//div[contains(text(),'Finder')]", 15).click();
		
		

		Thread.sleep(120000);
	}

}
