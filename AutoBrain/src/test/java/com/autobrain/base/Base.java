package com.autobrain.base;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import com.autobrain.utilities.Custom_Listner;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

@Listeners(Custom_Listner.class)
public class Base {
	public Logger logger = null;

	boolean page_load;
	public String url = "https://stg.autobrain.com/";
	public SoftAssert softassert = new SoftAssert();
	public static final String USERNAME = "prince202";
	public static final String AUTOMATE_KEY = "cRZ8y2VauprKHB1HGg9s";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	@BeforeMethod
	@Parameters({ "Browsers", "Headless" })
	public void CheckBrowsers(String Browsers, String headless) throws Exception {
		if (Browsers.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		}

		else if (Browsers.equalsIgnoreCase("chrome")) {
			System.setProperty("org.freemarker.loggerLibrary", "none");

			DesiredCapabilities cap = DesiredCapabilities.chrome();
			LoggingPreferences log = new LoggingPreferences();
			log.enable(LogType.BROWSER, Level.SEVERE);
			cap.setCapability(CapabilityType.LOGGING_PREFS, log);

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

			// Headless browser run without UI
			if (headless.equalsIgnoreCase("true")) {
				options.addArguments("window-size=1366,768");
				options.addArguments("headless");
				System.out.println("Test will run in Headless Browser!");
			}

			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver(options.merge(cap)));

		}

		else if (Browsers.equalsIgnoreCase("browserstack")) {
			DesiredCapabilities caps = DesiredCapabilities.safari();
			caps.setPlatform(Platform.MAC);
			caps.setCapability("browser_version", "12");
			caps.setCapability("browserstack.debug", true);
			java.net.URL url = new java.net.URL(URL);
			driver.set(new RemoteWebDriver(url, caps));
		}

		else if (Browsers.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		getDriver().get(url);

		// Validate login page displayed
		boolean isLoginPagedLoaded = VisibilityOfElementByXpath("//input[@name='commit']", 60).isDisplayed();
		Assert.assertEquals(isLoginPagedLoaded, true, "Staging is not loading!");

	}

	public WebDriver getDriver() {
		return driver.get();
	}

	// QUIT BROWSER
	@AfterMethod
	public void quit() throws Exception {
		getDriver().quit();
	}

	// Get javascript error from console
	public boolean extractJSLogsInfo(String inputErrorMsg) throws Exception {
		Thread.sleep(1000);
		getDriver().getCurrentUrl();
		Thread.sleep(1000);
		LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			if (entry.getMessage().contains("500 (Internal Server Error)")) {
				System.out.println(AnsiConsoleColors.RED_BACKGROUND_BRIGHT + AnsiConsoleColors.WHITE_BOLD_BRIGHT
						+ inputErrorMsg + AnsiConsoleColors.RESET);
				return true;
			}
		}
		return false;

	}

// DELETE FAILED TEST SCREENSHOTS FROM IMAGES FOLDER BEFORE THE TEST START
//	@BeforeClass
//	public void deleteFailedTestScreenshots() throws IOException {
//		File f = new File("Images\\");
//		int count = 0;
//		for (File file : f.listFiles()) {
//			if (file.isFile() && file.getName().endsWith(".png")) {
//				String file_name = file.getName();
//				Files.deleteIfExists(Paths.get("Images\\" + file_name));
//				count++;
//			}
//		}
//
//		if (count > 0) {
//			System.out.println("Old tests screenshots deleted successfully before launching the new tests!");
//		}
//	}

	// WAIT
	public WebDriverWait wait(WebDriver driver, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait;
	}

	// Visibility of element by xpath
	public WebElement VisibilityOfElementByXpath(String xpath, int time) {
		WebDriverWait wait = new WebDriverWait(getDriver(), time);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	// Visibility all of elements by xpath
	public List<WebElement> VisibilityOfAllElementsByXpath(String xpath, int time) {
		WebDriverWait wait = new WebDriverWait(getDriver(), time);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
	}

	// Visibility of element by ID
	public WebElement VisibilityOfElementByID(String ID, int time) {
		WebDriverWait wait = new WebDriverWait(getDriver(), time);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ID)));
	}

	// Visibility of element by class name
	public WebElement VisibilityOfElementByClassName(String className, int time) {
		WebDriverWait wait = new WebDriverWait(getDriver(), time);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
	}

	// Visibility of element by name
	public WebElement VisibilityOfElementByName(String Name, int time) {
		WebDriverWait wait = new WebDriverWait(getDriver(), time);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
	}

	// Presence of element by xpath
	public WebElement PresenceOfElementByXpath(String xpath, int time) {
		WebDriverWait wait = new WebDriverWait(getDriver(), time);
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}

	// Presence all of elements by xpath
	public List<WebElement> PresenceOfAllElementsByXpath(String xpath, int time) {
		WebDriverWait wait = new WebDriverWait(getDriver(), time);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
	}

	// Presence of element
	public WebElement PresenceOfWebElement(WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(getDriver(), time);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Presence of all elements
	public List<WebElement> PresenceOfAllWebElements(By element, int time) {
		WebDriverWait wait = new WebDriverWait(getDriver(), time);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) element));
	}

	// CAPTURE SCREENSHOT IF THE TEST GOT FAILED
	public void FullPageScreenshot(String screenshotName) throws Exception {
		Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(getDriver());
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

	// EXCEL CREATE ROW AND CELL TO WRITE
	public void ExcelCreateRowCreateCellAndWrite(int row, int column, String input_data, String file_path)
			throws Exception {
		File file = new File(file_path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		sheet.createRow(row).createCell(column).setCellValue(input_data);
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
	}

	// EXCEL GET ROW AND CREATE CELL TO WRITE
	public void ExcelGetRowCreateCellAndWrite(int row, int column, String input_data, String file_path)
			throws Exception {
		File file = new File(file_path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		sheet.getRow(row).createCell(column).setCellValue(input_data);
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
	}

	// EXCEL READ
	public String ExcelRead(int row, int column, String file_path) throws Exception {
		File file = new File(file_path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		String cell_value = sheet.getRow(row).getCell(column).getStringCellValue().trim();
		return cell_value;
	}

	// EXCEL GET TOTAL NUMBER OF ROWS
	public int ExcelGetNumberOfRows(String file_path) throws Exception {
		File file = new File(file_path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int total_rows = sheet.getLastRowNum();
		return total_rows;
	}

	// PRINT CURRENT DATE AND TIME
	public String GetCurrentDateTime() {
		// Create object of SimpleDateFormat class and decide the format for date and
		// time
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		// get current date time with Date()
		Date date = new Date();
		// Now format the date
		String date1 = dateFormat.format(date);
		return date1;
	}

}