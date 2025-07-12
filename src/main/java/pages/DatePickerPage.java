package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatePickerPage {

	private WebDriver driver;
	private WebDriverWait wait;

	// Add any additional locators or methods as needed for the registration page
	public DatePickerPage(WebDriver driver) {
		// Constructor for the RegistrationPage class
		this.driver = driver;
		this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	
	 

	

}
