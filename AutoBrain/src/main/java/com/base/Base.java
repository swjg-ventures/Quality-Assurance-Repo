package com.base;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

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
				//Open window incognito mode
			/*
			 * ChromeOptions c = new ChromeOptions(); c.addArguments("incognito");
			 */			
				
				//Driver to launch chrome browser
				WebDriverManager.chromedriver().setup();			
			    driver = new ChromeDriver();
			}
			
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			
			try {
			driver.get(url);			
		
			}
				catch(Exception e) {
					driver.quit();
					CheckBrowsers(bro);
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
		
		//DATE FORMATE CHANGE METHOD
		public static String date_format(String sdate) throws Exception {
			SimpleDateFormat formatter2=new SimpleDateFormat("dd MMM yyyy");  
		    SimpleDateFormat formatter3=new SimpleDateFormat("MMM dd, yyyy");  
		    Date date2=formatter2.parse(sdate);  
		    String d = formatter3.format(date2);
		    return d;
		}
		
		}