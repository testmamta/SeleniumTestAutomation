Feature: Browser Windows Handling

  As a user of DemoQA
  I want to interact with new browser windows and tabs
  So that I can verify navigation and content in them

  Background:
    Given I am on the DemoQA "browser-windows" page

@smoke
  Scenario: Opening and switching to a new tab
    When I click the "New Tab" button
    Then a new tab should open with URL "https://demoqa.com/sample"
    And I should see the text "This is a sample page" on the new tab
    And I switch back to the original window
    And the original window should still be on the "browser-windows" page


  Scenario: Opening and switching to a new window
    When I click the "New Window" button
    Then a new window should open with URL "https://demoqa.com/sample"
    And I should see the text "This is a sample page" on the new window
    And I switch back to the original window
    And the original window should still be on the "browser-windows" page

#  Scenario: Opening and interacting with a new window message
#    When I click the "New Window Message" button
 #   Then a new window should open with a message
  #  And I should see the text "Knowledge increases by sharing " within the new window message content
   # And I close the new window message
    #And the original window should still be on the "browser-windows" page