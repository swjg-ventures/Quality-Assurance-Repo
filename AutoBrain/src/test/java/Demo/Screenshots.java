package Demo;


import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.autobrain.pages.Login;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


public class Screenshots extends Login {
	
	//WHOLE PAGE SCREENSHOT
	public void WholePageScreenshot(String screenshotName) throws Exception {
		 Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		 ImageIO.write(fpScreenshot.getImage(),"PNG",new File("./Images/"+screenshotName+".png"));

	}
	
	
	//NORMAL SCREENSOT
	public void NormalScreenshot(String screenshotName) throws Exception {
		TakesScreenshot ts = (TakesScreenshot)driver;	
		File source = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File("./Images/"+screenshotName+".png"));
		} catch (Exception e) {
			
		System.out.println("Exception while taking screenshot"+e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	// PARTICULAR IMAGE COMPARISON
	public void particularimagecomparison() throws Exception {
		login("john@example.com", "welcome");
		
		WebElement caricon = driver.findElement(By.xpath("//div[@id='bigMapCarIcon']"));
		File f = new File("C:\\Users\\Rajesh\\git\\Quality-Assurance-Repo\\AutoBrain\\Images\\one.png");
		BufferedImage expimage = ImageIO.read(f);
		
		// Step 1
		Screenshot car = new AShot().takeScreenshot(driver, caricon);
		
		//To save screenshot
//		ImageIO.write(car.getImage(), "PNG", new File(""));
		
		BufferedImage actimage = car.getImage(); 
		
		
		ImageDiffer imagediffer = new ImageDiffer();
		ImageDiff imagediff = imagediffer.makeDiff(expimage, actimage);
		softassert.assertFalse(imagediff.hasDiff(), "Both images are different!");	
		softassert.assertAll();
		Thread.sleep(1000);		
	}
	
	
	
	//WHOLE PAGE IMAGE COMPARISON
	public void wholepageimagecomparison() throws Exception {
		
//		element.click();
//		driver.findElement(By.xpath("//a[contains(text(),'Terms of Use')]")).click(); 
		Thread.sleep(4000);
		Screenshot sc = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(3000)).takeScreenshot(driver);
//		Screenshot sc = new AShot().takeScreenshot(driver);
		File f = new File("C:\\Users\\Rajesh\\git\\Quality-Assurance-Repo\\AutoBrain\\Images\\whole.png");
		ImageIO.write(sc.getImage(), "PNG", f);
	
		BufferedImage expimage = ImageIO.read(f);
		
	
		Thread.sleep(2000);
		Screenshot sc1 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
//		Screenshot sc1 = new AShot().takeScreenshot(driver);
		File f1 = new File("C:\\Users\\Rajesh\\git\\Quality-Assurance-Repo\\AutoBrain\\Images\\whole1.png");
		ImageIO.write(sc1.getImage(), "PNG", f1);
		
		BufferedImage actualimage = ImageIO.read(f1);
		
	
		ImageDiffer imagediffer = new ImageDiffer();
		ImageDiff imagediff = imagediffer.makeDiff(expimage, actualimage);
		softassert.assertFalse(imagediff.hasDiff(), "Both images are different!");	
		

		
		softassert.assertAll();
		Thread.sleep(1000);			
	}
	
	
	
	
	
	
	
}