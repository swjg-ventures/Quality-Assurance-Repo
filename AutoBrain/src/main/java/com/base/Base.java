package com.base;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

	public class Base {
	public WebDriver driver;
	public String url ="https://stg.autobrain.com/";	

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
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
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
		}