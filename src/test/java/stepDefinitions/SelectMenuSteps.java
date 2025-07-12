package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SelectMenuPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class SelectMenuSteps {

	WebDriver driver = DriverFactory.getDriver();
	SelectMenuPage selectMenuPage = new SelectMenuPage(driver);
	String originalWindow = null;
	String originalWindowHandle = null;

	@Given("I am on the DemoQA {string} page opened through widgets")
	public void i_am_on_the_demo_qa_page_opened_through_widgets(String string) {
		// Navigate to the specified DemoQA page
		driver.get(ConfigReader.getBaseUrl()); // Get URL from ConfigReader
		selectMenuPage.goToWidgetsWindowsPage();
		selectMenuPage.goToSelectMenuWindowsPage();
		originalWindow = driver.getCurrentUrl();
		originalWindowHandle = driver.getWindowHandle();

		// Assert that the current URL matches the expected URL
		Assert.assertEquals(originalWindow, ConfigReader.getBaseUrl() + string);
	}

	@When("I select the option {string} from the Select Value dropdown")
	public void i_select_the_option_from_the_select_value_dropdown(String string) {
		selectMenuPage.selectValueDropdown();
		selectMenuPage.clickValueDropdownOption(string);
	}

	@Then("the Select Value dropdown should display {string}")
	public void the_select_value_dropdown_should_display(String string) {
		Assert.assertEquals(selectMenuPage.getValueSelectedInSelectValueDrpdwn(), string);

	}

	@When("I select the color {string} from the Old Style Select Menu dropdown")
	public void i_select_the_color_from_the_old_style_select_menu_dropdown(String string) {
		selectMenuPage.selectValuesInOldSelectMenu(string);
	}

	@Then("the Old Style Select Menu dropdown should display {string} as selected")
	public void the_old_style_select_menu_dropdown_should_display_as_selected(String string) {
		Assert.assertEquals(selectMenuPage.getSelectedOption(), string);	
	}

	@When("I select colors {string}, {string}, and {string} from the Multiselect dropdown")
	public void i_select_colors_and_from_the_multiselect_dropdown(String string, String string2, String string3) {
	 selectMenuPage.selectValuesinMultiSelectDropdown(string, string2, string3);
	}

	@Then("the Multiselect dropdown should display {string}, {string}, and {string} as selected")
	public void the_multiselect_dropdown_should_display_and_as_selected(String string, String string2, String string3) {
	
		Assert.assertEquals(selectMenuPage.getValueSelectedInMultiSelectDrpdwn(),string);
	}

}
