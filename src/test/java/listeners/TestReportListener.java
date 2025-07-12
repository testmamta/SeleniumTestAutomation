package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import utilities.DriverFactory;
import utilities.ScreenshotUtil;
import org.openqa.selenium.WebDriver;


public class TestReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
    	String reportPath = System.getProperty("extent.reporter.spark.out");
    	if (reportPath == null || reportPath.isEmpty()) {
    	    reportPath = "target/extent-reports/ExtentReport.html"; // fallback default
    	}
    	ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
    	htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Selenium Test Execution");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Tester", "Mamta Kaushik");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        // Attach screenshot
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
            test.get().fail(result.getThrowable());
            test.get().fail("Screenshot on Failure", 
            	    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
