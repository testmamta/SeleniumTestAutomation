package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FramesPage extends BasePage {

	private WebDriver driver;
	public WebDriverWait wait;

	// Constructor for the LoginPage class
	public FramesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

}
