package stepDefinitions;


import java.util.Set;

// Import necessary libraries and classes
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BrowserWindowsPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

// Step definition class for user authentication scenarios
public class BrowserWindowsHandlingSteps {

	
	WebDriver driver ;
	BrowserWindowsPage browserWindowsPage;
	String originalWindow = null;
	String originalWindowHandle = null;

	@Given("I am on the DemoQA {string} page")
	public void i_am_on_the_demo_qa_page(String string) {
		// Initialize WebDriver using DriverFactory utility
		 driver = DriverFactory.getDriver();
		 browserWindowsPage = new BrowserWindowsPage(driver);
		// Navigate to the specified DemoQA page
		driver.get(ConfigReader.getBaseUrl()); // Get URL from ConfigReader
		browserWindowsPage.goToAlertsWindowsPage();
		browserWindowsPage.goToBrowserWindowsPage();
		originalWindow = driver.getCurrentUrl();
		originalWindowHandle = driver.getWindowHandle();

		// Assert that the current URL matches the expected URL
		Assert.assertEquals(originalWindow, ConfigReader.getBaseUrl()+ string);
	}

	@When("I click the {string} button")
	public void i_click_the_button(String string) {
		browserWindowsPage.clickOnButton(string);
	}

	@Then("a new tab should open with URL {string}")
	public void a_new_tab_should_open_with_url(String string) {
		ExpectedConditions.numberOfWindowsToBe(2);
		Set<String> allWindowHandles = driver.getWindowHandles();
		String newWindowHandle = null;
		originalWindowHandle = driver.getWindowHandle();

		for (String handle : allWindowHandles) {
			if (!handle.equals(originalWindowHandle)) {
				newWindowHandle = handle;
				break;
			}
		}
		if (newWindowHandle != null) {
			driver.switchTo().window(newWindowHandle);
		}
		Assert.assertEquals(driver.getCurrentUrl(), string);

	}

	@Then("I should see the text {string} on the new tab")
	public void i_should_see_the_text_on_the_new_tab(String string) {
		Assert.assertEquals(browserWindowsPage.getSamplePageText(), string);
	}

	@Then("I switch back to the original window")
	public void i_switch_back_to_the_original_window() {
		driver.switchTo().window(originalWindowHandle);
	}

	@Then("the original window should still be on the {string} page")
	public void the_original_window_should_still_be_on_the_page(String string) {
		Assert.assertEquals(originalWindow, ConfigReader.getBaseUrl() + string);

	}
	

@Then("a new window should open with URL {string}")
public void a_new_window_should_open_with_url(String string) {
	ExpectedConditions.numberOfWindowsToBe(2);
	Set<String> allWindowHandles = driver.getWindowHandles();
	String newWindowHandle = null;

	for (String handle : allWindowHandles) {
		if (!handle.equals(originalWindowHandle)) {
			newWindowHandle = handle;
			break;
		}
	}
	if (newWindowHandle != null) {
		driver.switchTo().window(newWindowHandle);
	}
	Assert.assertEquals(driver.getCurrentUrl(), string);
}
@Then("I should see the text {string} on the new window")
public void i_should_see_the_text_on_the_new_window(String string) {
	Assert.assertEquals(browserWindowsPage.getSamplePageText(), string);

}


@Then("a new window should open with a message")
public void a_new_window_should_open_with_a_message() {
	ExpectedConditions.numberOfWindowsToBe(2);
	Set<String> allWindowHandles = driver.getWindowHandles();
	String newWindowHandle = null;

	for (String handle : allWindowHandles) {
		if (!handle.equals(originalWindowHandle)) {
			newWindowHandle = handle;
			break;
		}
	}
	if (newWindowHandle != null) {
		driver.switchTo().window(newWindowHandle);
	}
	Assert.assertEquals(browserWindowsPage.windowHasBody(), true);
}
@Then("I should see the text {string} within the new window message content")
public void i_should_see_the_text_within_the_new_window_message_content(String string) {
 
	Assert.assertEquals(browserWindowsPage.getWindowBodyText().contains(string),true);

}
@Then("I close the new window message")
public void i_close_the_new_window_message() {
    driver.close();    
	driver.switchTo().window(originalWindowHandle);

}

}
