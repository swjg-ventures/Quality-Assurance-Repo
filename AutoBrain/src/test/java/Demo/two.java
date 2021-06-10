package Demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.autobrain.base.Base;

import io.github.bonigarcia.wdm.WebDriverManager;

public class two extends Base {

	public static void main(String[] args) {
		WebDriverManager.edgedriver().setup();
	
		

		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://stg.autobrain.com");
		System.out.println(driver.getCurrentUrl());
		driver.quit();
	}
}
