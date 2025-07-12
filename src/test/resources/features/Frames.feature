Feature: iFrames Interaction

  As a user of DemoQA
  I want to interact with elements inside iframes
  So that I can automate tests for embedded content

  Background:
    Given I am on the DemoQA "frames" page opened through alerts tab


  Scenario: Interacting with a single iframe
    When I switch to the first iframe
    Then I should see the header "This is a sample page" inside the iframe
    And I switch back to the default content of Frames
    And I should see the main page header "Frames"

  Scenario: Interacting with nested iframes
    When I switch to the parent iframe
    And I should see the text "Parent frame" inside the parent iframe
    And I switch to the child iframe
    Then I should see the text "Child Iframe" inside the child iframe
    And I switch back to the default content of Nested Frames
    And I should see the main page header "Nested Frames"