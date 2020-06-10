package com.base;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import Library.Custom_Listner;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

@Listeners(Custom_Listner.class)
public class Base {
	boolean page_load;
	public static WebDriver driver;
	public String url = "https://stg.autobrain.com/";
	public SoftAssert softassert = new SoftAssert();

	public static final String USERNAME = "prince202";
	public static final String AUTOMATE_KEY = "cRZ8y2VauprKHB1HGg9s";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	@BeforeClass
	@Parameters("Browsers")
	public void CheckBrowsers(String bro) throws Exception {
		if (bro.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		else if (bro.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");

			// Disable save password chrome dialog
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);

			// Disable chrome is being controlled by automated software
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			// Headless browser without UI
			options.addArguments("window-size=1366,768");
			options.addArguments("headless");

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}

		else if (bro.equalsIgnoreCase("browserstack")) {
			DesiredCapabilities caps = DesiredCapabilities.safari();
			caps.setPlatform(Platform.MAC);
			caps.setCapability("browser_version", "12");
			caps.setCapability("browserstack.debug", true);
			java.net.URL url = new java.net.URL(URL);
			driver = new RemoteWebDriver(url, caps);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(url);

	}

	// QUIT BROWSER
	@AfterClass
	public void quit() throws Exception {
		Thread.sleep(5000);
		driver.quit();
	}

	// WAIT
	public WebDriverWait wait(WebDriver driver, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait;
	}

	// Visibility of element by xpath
	public WebElement VisibilityOfElementByXpath(String xpath, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	// Visibility all of elements by xpath
	public List<WebElement> VisibilityOfAllElementsByXpath(String xpath, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
	}

	// Visibility of element by ID
	public WebElement VisibilityOfElementByID(String ID, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ID)));
	}

	// Visibility of element by class name
	public WebElement VisibilityOfElementByClassName(String className, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
	}

	// Visibility of element by name
	public WebElement VisibilityOfElementByName(String Name, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
	}

	// Presence of element by xpath
	public WebElement PresenceOfElementByXpath(String xpath, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}

	// Presence all of elements by xpath
	public List<WebElement> PresenceOfAllElementsByXpath(String xpath, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
	}

	// Presence of element
	public WebElement PresenceOfWebElement(WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Presence of all elements
	public List<WebElement> PresenceOfAllWebElements(By element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) element));
	}

	// CAPTURE SCREENSHOT IF THE TEST GOT FAILED
	public void FullPageScreenshot(String screenshotName) throws Exception {
		Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		ImageIO.write(fpScreenshot.getImage(), "PNG", new File("./Images/" + screenshotName + ".png"));

	}

	// DATE FORMATE CHANGE METHOD
	public static String date_format(String sdate) throws Exception {
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd MMM yyyy");
		SimpleDateFormat formatter3 = new SimpleDateFormat("MMM dd, yyyy");
		Date date2 = formatter2.parse(sdate);
		String d = formatter3.format(date2);
		return d;
	}

	// OPEN NEW TAB IN BROWSER
	public void new_tab() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(4000);
	}

	// HARD REFRESH PAGE
	public void hard_refresh_page() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_F5);
		robot.keyRelease(KeyEvent.VK_F5);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	// SCROLL UNITL THE ELEMENT NOT FOUND
	public void scroll_until_ele_not_found(List<WebElement> Element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int i = Element.size();
		WebElement ele = Element.get(i - 1);
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}

	// TYPE IN INPUT FIELD SLOWLY (SENDKEYS)
	public void TypeInFieldSlowly(WebElement ele, String value) throws Exception {
		String val = value;
		for (int i = 0; i < val.length(); i++) {
			char c = val.charAt(i);
			String s = new StringBuilder().append(c).toString();
			ele.sendKeys(s);
			Thread.sleep(150);
		}
	}

}