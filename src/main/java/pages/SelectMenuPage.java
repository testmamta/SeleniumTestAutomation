package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectMenuPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public SelectMenuPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h5[text()='Widgets']")
	WebElement widgetsWindowsPageLink;

	/* Below are locators for custom styled dropdowns */
	// we have used xpath here as we are trying to find element using text and css
	// has limitations in it .it can only Match by ID, class, tag, attributes
	@FindBy(xpath = "//span[text()='Select Menu']")
	WebElement selectMenuPageLink; 
	
	@FindBy(id="withOptGroup")
	WebElement selectValueDropdown;
	
	@FindBy(id="selectOne")
	WebElement selectOneDropdown;
	
	@FindBy(xpath="//div[@id='withOptGroup']//div[contains(@class,'option') and not(contains(@id,'group'))]") 
	List<WebElement> allDropdownOptionsLocator;
	
	/* Locators for select dropdowns*/
	
	@FindBy(id="oldSelectMenu")
	WebElement oldStyleSelectMenu;
	
	@FindBy(xpath="//div[text()='Select...']")
	WebElement multiSelectDropdown;
	
	
	public void selectValuesInOldSelectMenu(String colorName) {
		wait.until(ExpectedConditions.visibilityOf(oldStyleSelectMenu));
	//	oldStyleSelectMenu.click();
		Select select = new Select(oldStyleSelectMenu);
		select.selectByVisibleText(colorName);
	}
	
	public String getSelectedOption() {
		Select select = new Select(oldStyleSelectMenu);
		return select.getFirstSelectedOption().getText();
	}
	public void selectValueDropdown()
	{
		wait.until(ExpectedConditions.visibilityOf(selectValueDropdown));
		selectValueDropdown.click();
	}
	
	public String getValueSelectedInSelectValueDrpdwn() {
		WebElement actualDisplayValueLocatorElement= driver.findElement(By.xpath("//div[@id='withOptGroup']//div[contains(@class, 'singleValue')]"));
		wait.until(ExpectedConditions.visibilityOf(actualDisplayValueLocatorElement));
		// Try getting innerText via JavaScript
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String actualTextJS = (String) js.executeScript("return arguments[0].innerText;", actualDisplayValueLocatorElement);
		String actualTextCleaned = actualTextJS.trim();
		return actualTextCleaned;
	}
	
	public void selectValuesinMultiSelectDropdown(String value1,String value2,String value3) {
		multiSelectDropdown.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(multiSelectDropdown));
		driver.findElement(By.xpath("//div[text()='"+value1+ "']")).click();
		driver.findElement(By.xpath("//div[text()='"+value2+ "']")).click();
		driver.findElement(By.xpath("//div[text()='"+value3+ "']")).click();
	}
	
	public String getValueSelectedInMultiSelectDrpdwn() {
		WebElement actualDisplayValueLocatorElement= driver.findElement(By.xpath("//div[contains(@class, 'multiValue')]"));
		wait.until(ExpectedConditions.visibilityOf(actualDisplayValueLocatorElement));
		// Try getting innerText via JavaScript
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String actualTextJS = (String) js.executeScript("return arguments[0].innerText;", actualDisplayValueLocatorElement);
		String actualTextCleaned = actualTextJS.trim();
		return actualTextCleaned;
	}
	public void clickValueDropdownOption(String optionToSelect) {
		wait.until(ExpectedConditions.visibilityOfAllElements(allDropdownOptionsLocator));
		driver.findElement(By.xpath("//div[text()='"+optionToSelect+ "']")).click();
	}
	
	public void selectedOptionValue(String optionSelected) {
		
	}
	public void goToWidgetsWindowsPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Widgets']")));
		driver.findElement(By.xpath("//h5[text()='Widgets']")).click();
	}

	public void goToSelectMenuWindowsPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		selectMenuPageLink.click();
	}
}
