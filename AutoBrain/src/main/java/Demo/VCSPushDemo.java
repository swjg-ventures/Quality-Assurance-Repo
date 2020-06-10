package Demo;


import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import io.github.bonigarcia.wdm.WebDriverManager;

public class VCSPushDemo {
public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		
		//Disable save password chrome dialog
		Map<String, Object> prefs = new HashMap<String, Object>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    options.setExperimentalOption("prefs", prefs);
	
	    //Disable chrome is being controlled by automated software
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation")); 
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("https://stg.autobrain.com");
		
		driver.findElement(By.xpath("//input[@id='user_email']")).sendKeys("john@example.com");
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("welcome");
		driver.findElement(By.xpath("//input[@name='commit']")).click();
		Thread.sleep(12000);
		driver.findElement(By.xpath("//div[contains(text(),'VCS')]")).click();
		Thread.sleep(3000);

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
		driver.quit();
	}

}
