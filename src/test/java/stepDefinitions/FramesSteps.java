package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FramesPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class FramesSteps {

	WebDriver driver = DriverFactory.getDriver();
	FramesPage framesPage = new FramesPage(driver);
	
	@Given("I am on the DemoQA {string} page opened through alerts tab")
	public void i_am_on_the_demo_qa_page_opened_through_alerts_tab(String string) {
		// Navigate to the specified DemoQA page
		/*
		 * driver.get(ConfigReader.getBaseUrl()); // Get URL from ConfigReader
		 * browserWindowsPage.goToAlertsWindowsPage();
		 * browserWindowsPage.goToBrowserWindowsPage(); originalWindow =
		 * driver.getCurrentUrl(); originalWindowHandle = driver.getWindowHandle();
		 * 
		 * // Assert that the current URL matches the expected URL
		 * Assert.assertEquals(originalWindow, ConfigReader.getBaseUrl()+ string);
		 */
	}
	
	//Scenario 1
	@When("I switch to the parent iframe")
	public void i_switch_to_the_parent_iframe() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("I should see the text {string} inside the parent iframe")
	public void i_should_see_the_text_inside_the_parent_iframe(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("I switch to the child iframe")
	public void i_switch_to_the_child_iframe() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("I should see the text {string} inside the child iframe")
	public void i_should_see_the_text_inside_the_child_iframe(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	
	//scenario 2

	@When("I switch to the first iframe")
	public void i_switch_to_the_first_iframe() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("I should see the header {string} inside the iframe")
	public void i_should_see_the_header_inside_the_iframe(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
