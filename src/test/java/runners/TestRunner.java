package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
// REMOVE these imports if they are present and no longer used:
// import org.testng.annotations.BeforeClass;
// import org.testng.ITestContext;

import utilities.ExecutionContext;
import org.testng.annotations.Listeners; // Import Listeners annotation
import listeners.TestParameterListener; // Import your custom listener

@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = {"stepDefinitions"},
	    tags = "@smoke",
	    plugin = {
	        "pretty",
	        "html:target/cucumber-report.html",   // optional: Cucumber HTML report
	        "summary",
	        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
	    },
	    monochrome = true
	)
@Listeners(TestParameterListener.class) // <-- ADD THIS LINE to register your listener
public class TestRunner extends AbstractTestNGCucumberTests {

    // REMOVED: Parameterized constructor
    // REMOVED: @BeforeClass method (setupTestRunnerBrowserAndMode)
    // The TestParameterListener will now handle setting ExecutionContext per scenario.

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        // This method just provides the scenarios to TestNG.
        // The ExecutionContext will be set by the TestParameterListener right before each scenario runs.
        System.out.println("TestRunner DataProvider - Preparing scenarios (Thread: " + Thread.currentThread().getId() + ")");
        // You can remove these debug lines if they get too noisy later, but helpful for initial verification.
        System.out.println("TestRunner DataProvider - Current ExecutionContext Browser: " + ExecutionContext.getBrowser() + ", RunMode: " + ExecutionContext.getRunMode() + " (Thread: " + Thread.currentThread().getId() + ")");
        return super.scenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownTestRunner() {
        System.out.println("TestNG Test Runner teardown for thread: " + Thread.currentThread().getId());
        // No need to clear ExecutionContext here, as the listener handles clearing after each test method (scenario).
        // This prevents accidental clearing before other scenarios on the same runner complete.
        // ExecutionContext.clear(); // <-- REMOVE OR COMMENT OUT THIS LINE
    }
}