package utilities;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
// import org.openqa.selenium.logging.LogType; // Uncomment if you want to use LoggingPreferences
// import org.openqa.selenium.logging.LoggingPreferences; // Uncomment if you want to use LoggingPreferences
// import java.util.logging.Level; // Uncomment if you want to use LoggingPreferences

import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverFactory {
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    private DriverFactory() {
    }

    /**
     * Initializes a WebDriver instance for the current thread based on the given browser and run mode.
     * This method should be called once per test execution thread (e.g., in a @Before hook).
     *
     * @param browserType The name of the browser (e.g., "chrome", "firefox", "edge").
     * @param runMode The execution mode ("local" or "remote").
     * @throws RuntimeException If WebDriver initialization fails.
     */
    public static void initializeDriver(String browserType, String runMode) {
        long currentThreadId = Thread.currentThread().getId();
    	System.out.println("🚀 [DF] Initializing browser: " + browserType + ", mode: " + runMode + " on Thread ID: " + currentThreadId);
    	Log.getLogger().info("Initializing WebDriver for browser: " + browserType  + ", mode: " + runMode + " on Thread ID: " + currentThreadId);

        if (threadLocalDriver.get() != null) {
            System.out.println("[DF] Driver already initialized for thread: " + currentThreadId + ", skipping re-initialization.");
            return;
        }

        WebDriver driver = null;
        boolean headless = ConfigReader.isHeadless();
        URL remoteUrl = null;

        if (runMode.equalsIgnoreCase("remote")) {
            try {
                remoteUrl = new URL("http://localhost:4444/wd/hub");
                System.out.println("[DF] Remote URL for Grid: " + remoteUrl + " (Thread: " + currentThreadId + ")");
            } catch (Exception e) {
                System.err.println("[DF] ERROR: Invalid remote URL setup for Thread ID: " + currentThreadId);
                Log.getLogger().error("[DF] ERROR: Invalid remote URL setup for Thread ID: " + currentThreadId);
                e.printStackTrace();
                throw new RuntimeException("Invalid remote URL setup.", e);
            }
        }

        try {
            switch (browserType.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) {
                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--disable-gpu");
                        chromeOptions.addArguments("--window-size=1920,1080");
                        chromeOptions.addArguments("--no-sandbox");
                        chromeOptions.addArguments("--disable-dev-shm-usage");
                        chromeOptions.setAcceptInsecureCerts(true);
                    } else {
                        chromeOptions.addArguments("--start-maximized");
                    }
                    // Optional: Add logging preferences for more detailed Selenium Grid logs
                    // LoggingPreferences logPrefs = new LoggingPreferences();
                    // logPrefs.enable(LogType.BROWSER, Level.ALL);
                    // logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
                    // chromeOptions.setCapability("goog:loggingPrefs", logPrefs);

                    if (runMode.equalsIgnoreCase("remote")) {
                        System.out.println("[DF] Attempting RemoteWebDriver (Chrome) for Thread ID: " + currentThreadId);
                        driver = new RemoteWebDriver(remoteUrl, chromeOptions);
                    } else {
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver(chromeOptions);
                    }
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) {
                        firefoxOptions.addArguments("--headless");
                        firefoxOptions.addArguments("--no-sandbox");
                        firefoxOptions.addArguments("--disable-dev-shm-usage");
                    } else {
                        firefoxOptions.addArguments("--start-maximized");
                    }

                    if (runMode.equalsIgnoreCase("remote")) {
                        System.out.println("[DF] Attempting RemoteWebDriver (Firefox) for Thread ID: " + currentThreadId);
                        driver = new RemoteWebDriver(remoteUrl, firefoxOptions);
                    } else {
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver(firefoxOptions);
                    }
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless) {
                        edgeOptions.addArguments("--headless");
                        edgeOptions.addArguments("--no-sandbox");
                        edgeOptions.addArguments("--disable-dev-shm-usage");
                    } else {
                        edgeOptions.addArguments("--start-maximized");
                    }

                    if (runMode.equalsIgnoreCase("remote")) {
                        System.out.println("[DF] Attempting RemoteWebDriver (Edge) for Thread ID: " + currentThreadId);
                        driver = new RemoteWebDriver(remoteUrl, edgeOptions);
                    } else {
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver(edgeOptions);
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Browser '" + browserType + "' is not supported. Please use chrome, firefox, or edge.");
            }

            if (driver != null) {
                threadLocalDriver.set(driver);
                System.out.println("[DF] WebDriver successfully created and set for thread: " + currentThreadId);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getTimeout()));
            } else {
                // This scenario means RemoteWebDriver constructor didn't throw an exception but returned null, which is highly unusual.
                System.err.println("[DF] CRITICAL WARNING: Driver object was NULL after browser initialization attempt for " + browserType + " on Thread ID: " + currentThreadId + ". No exception was caught, but driver is null.");
                throw new RuntimeException("Driver initialization returned null for " + browserType + ". Check environment and capabilities.");
            }

        } catch (Exception e) {
            System.err.println("[DF] FATAL ERROR: Exception during WebDriver initialization for " + browserType + " on Thread ID: " + currentThreadId);
            System.err.println("[DF] Error Message: " + e.getMessage());
            e.printStackTrace(); // This MUST print the full stack trace now!
            throw new RuntimeException("Failed to initialize WebDriver for " + browserType + ". See full logs for details.", e);
        }
    }

    /**
     * Method to get instance of WebDriver for the current thread.
     *
     * @return The WebDriver instance for the current thread.
     * @throws IllegalStateException If WebDriver has not been initialized for the current thread.
     */
    public static WebDriver getDriver() {
        WebDriver driver = threadLocalDriver.get();
        if (driver == null) {
            System.err.println("[DF] ERROR: getDriver() called but WebDriver is NULL for Thread ID: " + Thread.currentThread().getId() + ". Check if initializeDriver() failed or was not called.");
            throw new IllegalStateException("WebDriver not initialized for the current thread. Ensure initializeDriver() is called before getDriver().");
        }
        return driver;
    }

    /**
     * Quits the WebDriver instance for the current thread and removes it from ThreadLocal.
     */
    public static void quitDriver() {
        if (threadLocalDriver.get() != null) {
            long currentThreadId = Thread.currentThread().getId();
            System.out.println("[DF] Quitting WebDriver for thread: " + currentThreadId);
            Log.getLogger().info("WebDriver quit for Thread ID: {}", currentThreadId);
            try {
                threadLocalDriver.get().quit();
            } catch (Exception e) {
                System.err.println("[DF] WARNING: Error while quitting WebDriver for thread " + currentThreadId + ": " + e.getMessage());
                e.printStackTrace();
            } finally {
                threadLocalDriver.remove();
                System.out.println("[DF] WebDriver removed from ThreadLocal for thread: " + currentThreadId);
            }
        } else {
            System.out.println("[DF] No WebDriver to quit for thread: " + Thread.currentThread().getId() + " (already null).");
        }
    }
}