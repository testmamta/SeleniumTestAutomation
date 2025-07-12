package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserWindowsPage extends BasePage {

//	private WebDriver driver;
//	private WebDriverWait wait;

	public BrowserWindowsPage(WebDriver driver) {
		super(driver);
		/*
		 * this.driver = driver; this.wait = new WebDriverWait(driver,
		 * Duration.ofSeconds(10));
		 */
		PageFactory.initElements(driver, this);
	}

	// we have used xpath here as we are trying to find element using text and css
	// has limitations in it .it can only Match by ID, class, tag, attributes
	@FindBy(xpath = "//span[text()='Browser Windows']")
	WebElement browserWindowsPageLink;

	/*
	 * @FindBy(xpath="//button[text()='New Tab')]") WebElement newTabButton;
	 */

	

	@FindBy(id = "sampleHeading")
	WebElement samplePageHeading;

	public void goToBrowserWindowsPage() {
		browserWindowsPageLink.click();
	}

	public void clickOnButton(String buttonName) {
		
		WebElement buttonToClick = driver.findElement(By.xpath("//button[text()='" + buttonName + "']"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonToClick);
		wait.until(ExpectedConditions.visibilityOf(buttonToClick));
		buttonToClick.click();
	}

	public String getSamplePageText() {
		return samplePageHeading.getText();
	}

	public boolean windowHasBody() {
		List<WebElement> bodyElements = driver.findElements(By.tagName("body"));
		return !bodyElements.isEmpty();
	}

	public String getWindowBodyText() {
		List<WebElement> bodyElements = driver.findElements(By.tagName("body"));
		return bodyElements.get(0).getText();
	}
}