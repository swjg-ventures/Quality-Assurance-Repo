package com.base;


import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import Library.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;

	public class Base {
	public static WebDriver driver;
	public String url ="https://stg.autobrain.com/";	
	public SoftAssert softassert = new SoftAssert();
	
		@BeforeClass
		@Parameters("Browsers")
		public void CheckBrowsers(String bro)
		{
			if(bro.equalsIgnoreCase("firefox")) 
			{
			//Driver to launch firefox browser
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			}
			
			else if (bro.equalsIgnoreCase("chrome"))
			{
				DesiredCapabilities capabilities = new DesiredCapabilities().chrome();
				LoggingPreferences logginpre = new LoggingPreferences();
				logginpre.enable(LogType.BROWSER, Level.ALL);
				capabilities.setCapability(CapabilityType.LOGGING_PREFS, logginpre);
				
				
				//Driver to launch chrome browser
				WebDriverManager.chromedriver().setup();			
			    driver = new ChromeDriver(capabilities);
			}
			
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			
			try {
			driver.get(url);			
		
			}
				catch(Exception e) {
					driver.quit();
					CheckBrowsers(bro);
				}
			} 
		
		
		public void extractJSLogsInfo() throws Exception{
			LogEntries logEntries= driver.manage().logs().get(LogType.BROWSER);
			for(LogEntry entry : logEntries) {
				System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " "+ entry.getMessage());
			}
		}
		
		
		
		//Wait method until the element not found for given time-frame
		public WebDriverWait wait(WebDriver driver, int time) {
			WebDriverWait wait = new WebDriverWait(driver, time);
			return wait;
		}

		@AfterClass
		public void quit() throws Exception{
			Thread.sleep(5000);
			driver.quit();
		
		}
		
		
		
		@AfterMethod
		public void tearDown(ITestResult result) {
			
			if(ITestResult.FAILURE==result.getStatus()) {
				Utility u = new Utility();
				u.capscreenshot(result.getName());
			}
		}
		

		
		//DATE FORMATE CHANGE METHOD
		public static String date_format(String sdate) throws Exception {
			SimpleDateFormat formatter2=new SimpleDateFormat("dd MMM yyyy");  
		    SimpleDateFormat formatter3=new SimpleDateFormat("MMM dd, yyyy");  
		    Date date2=formatter2.parse(sdate);  
		    String d = formatter3.format(date2);
		    return d;
		}
		
		
		//Open new tab in browser
		public void new_tab() throws Exception {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(4000);
				}
		
		
		//Hard refresh page
		public void hard_refresh_page() throws Exception {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_F5);
			robot.keyRelease(KeyEvent.VK_F5);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
		
		//Scroll until the element not found
		public void scroll_until_ele_not_found(List<WebElement> Element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;			
			int i = Element.size();
			WebElement ele = Element.get(i-1);
		    js.executeScript("arguments[0].scrollIntoView();", ele);
		    
		}
		
		
		
		
		}