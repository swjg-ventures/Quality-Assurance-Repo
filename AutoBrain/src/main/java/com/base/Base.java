package com.base;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import Library.Custom_Listner;
import atu.testrecorder.ATUTestRecorder;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
@Listeners(Custom_Listner.class)
	public class Base {
	boolean page_load;	
	public static WebDriver driver;
	public String url ="https://stg.autobrain.com/";	
	public SoftAssert softassert = new SoftAssert();
	
	
		@BeforeClass		
		@Parameters("Browsers")
		public void CheckBrowsers(String bro) throws Exception
		{			
			if(bro.equalsIgnoreCase("firefox")) 
			{
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			}
			
			else if (bro.equalsIgnoreCase("chrome"))
			{	
				//This will disable default browser notifications
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
						
	//			System.setProperty("webdriver.chrome.driver", "WebDriver\\chromedriver.exe");
				WebDriverManager.chromedriver().setup();			
			    driver = new ChromeDriver(options);
//			    recorder= new ATUTestRecorder("Images", "mhyvid", false);
//			    recorder.start();
			}
			
			
			driver.manage().window().maximize();
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
			driver.manage().deleteAllCookies();
			
			try {
			driver.get(url);			
		
			}
				catch(Exception e) {
					driver.quit();
					CheckBrowsers(bro);
				}
			} 
		
		
		//CAPTURE CONSOLE ERROR
//		public void extractJSLogsInfo() throws Exception{
//			LogEntries logEntries= driver.manage().logs().get(LogType.BROWSER);
//			for(LogEntry entry : logEntries) {
//				System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " "+ entry.getMessage());
//			}
//		}
		
		
		
		//MAIN PAGE RELOAD
		public void main_page() 
		{
			driver.get(url);
			while(page_load==false) 
			{
				try 
				{
				page_load =wait(driver, 80).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]/div[9]/a/div[2]"))).isDisplayed();	
				} 
				
				catch(Exception e) 
				{
					System.out.println("Home page not loaded properly.");
					break;
				}
			}
		}
		
		
		
		//WAIT METHOD
		public WebDriverWait wait(WebDriver driver, int time) {
			WebDriverWait wait = new WebDriverWait(driver, time);
			return wait;
		}

		//Visibility of element by xpath
		public WebElement VisibilityOfElementByXpath(String xpath)
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));			
		}
		
		//Visibility all of elements by xpath
		public List<WebElement> VisibilityOfAllElementsByXpath(String xpath)
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));			
		}
		
		//Visibility of element by ID
		public WebElement VisibilityOfElementByID(String ID)
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ID)));			
		}
		
		
		
		
		//Presence of element by xpath
		public WebElement PresenceOfElementByXpath(String xpath)
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));			
		}
		
		
		//Presence all of elements by xpath
		public List<WebElement> PresenceOfAllElementsByXpath(String xpath)
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));			
		}
		
		
		
		
		
		
		//QUIT BROWSER
		@AfterClass
		public void quit() throws Exception{
			Thread.sleep(5000); 
			driver.quit();
//			recorder.stop();
		
		}
		
		
		
		//CAPTURE SCREENSHOT IF THE TEST GOT FAILED	
		public void FullPageScreenshot(String screenshotName) throws Exception {
			 Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
			 ImageIO.write(fpScreenshot.getImage(),"PNG",new File("./Images/"+screenshotName+".png"));
	
		}
		
		

		
		//DATE FORMATE CHANGE METHOD
		public static String date_format(String sdate) throws Exception {
			SimpleDateFormat formatter2=new SimpleDateFormat("dd MMM yyyy");  
		    SimpleDateFormat formatter3=new SimpleDateFormat("MMM dd, yyyy");  
		    Date date2=formatter2.parse(sdate);  
		    String d = formatter3.format(date2);
		    return d;
		}
		
		
		//OPEN NEW TAB IN BROWSER
		public void new_tab() throws Exception {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(4000);
				}
		
		
		//HARD REFRESH PAGE
		public void hard_refresh_page() throws Exception {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_F5);
			robot.keyRelease(KeyEvent.VK_F5);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
		
		//SCROLL UNITL THE ELEMENT NOT FOUND
		public void scroll_until_ele_not_found(List<WebElement> Element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;			
			int i = Element.size();
			WebElement ele = Element.get(i-1);
		    js.executeScript("arguments[0].scrollIntoView();", ele);
		    
		}
		
		//TYPE IN INPUT FIELD SLOWLY (SENDKEYS)
		public void TypeInFieldSlowly(WebElement ele, String value) throws Exception{
		    String val = value; 
		   
		    for (int i = 0; i < val.length(); i++){
		        char c = val.charAt(i);
		        String s = new StringBuilder().append(c).toString();
		        ele.sendKeys(s);
		        Thread.sleep(150);
		    }       
		}
		
		
		}