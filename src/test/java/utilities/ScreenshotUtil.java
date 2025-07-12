package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	
	public static String takeScreenshot(WebDriver driver,String screenshotName) {
		if(driver==null) {
			System.err.println("Can't take screenshot as WebDriver instance is null");
		}
		try {
			File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			String timestamp= new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String fileName = screenshotName +"_" +timestamp +".png";
			String screenshotPath = "target/screenshots/" + fileName;
			// Ensure the directory exists
            File destinationDir = new File(System.getProperty("user.dir") + "/target/screenshots/");
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }

            // Copy file to the specified destination
            File destFile = new File(screenshotPath);
            FileUtils.copyFile(srcFile, destFile); // Using Selenium's FileHandler for simplicity
            return screenshotPath; // Return the path for reporting
		}
		catch (IOException e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while taking screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
		
		
		
	}

}
