package stepDefinitions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import utilities.DriverFactory;
import utilities.ExecutionContext;
import utilities.Log;
import utilities.ScreenshotUtil;


public class Hooks {
	


	@Before
	public void beforeScenario(Scenario scenario) {
	    System.out.println("Hooks @Before - Current Thread ID (Before Get): " + Thread.currentThread().getId());
	    Log.getLogger().info("Before Scenario - " + scenario.getName());
	    String browser = ExecutionContext.getBrowser(); // This is what the listener should now populate
	    String runMode = ExecutionContext.getRunMode();
	    System.out.println("Hooks @Before - Retrieved from ExecutionContext: Browser='" + browser + "', RunMode='" + runMode + "' on Thread ID: " + Thread.currentThread().getId());
	    DriverFactory.initializeDriver(browser, runMode);  // Assuming your initializeDriver() takes both parameters

	    // ... rest of your @Before method ...
	}

	@After
	public void afterScenario(Scenario scenario) {
	    // ... your existing @After method ...
        WebDriver driver = DriverFactory.getDriver();

		if (scenario.isFailed()) {
            System.out.println("Scenario failed: " + scenario.getName());
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, scenario.getName());
            try {
                byte[] screenshotBytes = Files.readAllBytes(Paths.get(screenshotPath));
                scenario.attach(screenshotBytes, "image/png", scenario.getName());
            } catch (IOException e) {
                System.err.println("Could not attach screenshot to report: " + e.getMessage());
                Log.getLogger().error("Scenario failed: {} - capturing screenshot", scenario.getName());

            }
        } else {
            System.out.println("Scenario passed: " + scenario.getName());
        }
	    DriverFactory.quitDriver();
	    // ExecutionContext.clear(); // <-- ENSURE THIS LINE IS REMOVED OR COMMENTED OUT!
	}
}