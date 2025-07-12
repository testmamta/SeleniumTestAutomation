package utilities;

public class ExecutionContext {
    private static ThreadLocal<String> browser = new ThreadLocal<>();
    private static ThreadLocal<String> runMode = new ThreadLocal<>();

    public static void set(String browserName, String runModeValue) {
        browser.set(browserName);
        runMode.set(runModeValue);
        Log.getLogger().debug("ExecutionContext set: Browser='{}', RunMode='{}')", browser, runMode);

    }

    public static String getBrowser() {
        return browser.get();
    }

    public static String getRunMode() {
        return runMode.get();
    }

    public static void clear() {
        browser.remove();
        runMode.remove();
    }
}
