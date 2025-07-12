package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	 private static Properties properties;
	    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

	    static {
	    	try(FileInputStream fs = new FileInputStream(CONFIG_FILE_PATH)){
	    		properties = new Properties();
	    		properties.load(fs);	    		
	    		
	    	} 
			catch (IOException e) {
				System.err.println("Error loading configuration properties" + e.getMessage());
				e.printStackTrace();
				throw new RuntimeException("Could not load config file. ");
			}
	    }
	    
	    public static String getProperty(String key) {
	        // 1. Try to get from System Properties (highest priority for CI/CD overrides)
	        String value = System.getProperty(key);
	        if (value != null) {
	            return value;
	        }
	        // 2. Fallback to properties file
	        value = properties.getProperty(key);
	        if (value == null) {
	            throw new RuntimeException("Property '" + key + "' not found in config.properties or system properties.");
	        }
	        return value;
	    }

	    // Example specific getters for common properties
	    public static String getBaseUrl() {
	        return getProperty("base.url");
	    }

	    public static String getBrowser() {
	        return getProperty("browser");
	    }

	    public static int getTimeout() {
	        return Integer.parseInt(getProperty("timeout"));
	    }

	    public static boolean isHeadless() {
	        return Boolean.parseBoolean(getProperty("headless"));
	    }
	    
	    public static String getRunMode() {
	        return getProperty("runMode");
	    }
	}


