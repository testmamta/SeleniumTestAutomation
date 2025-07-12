Feature: Broken Links and Images Verification

  As a test automation engineer
  I want to verify that all links and images on a page are valid
  So that I can identify broken resources

  Background:
    Given I am on the DemoQA "Broken Links - Images" page

  Scenario: Verify valid image is displayed
    Then the "valid image" should be displayed correctly

  Scenario: Verify broken image is not displayed
    Then the "broken image" should not be displayed
    And its status code should be 404

  Scenario: Verify valid link
    When I click the "Valid Link"
    Then I should be navigated to "https://demoqa.com/"

  Scenario: Verify broken link
    When I attempt to navigate to the "Broken Link"
    Then its status code should be 500
    And I should remain on the "Broken Links - Images" page