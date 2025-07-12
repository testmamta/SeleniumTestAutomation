Feature: Dropdowns and Select Menu Interaction

  As a user of DemoQA
  I want to interact with various types of dropdowns
  So that I can select options in forms

  Background:
    Given I am on the DemoQA "select-menu" page opened through widgets

  Scenario Outline: Select an option from a standard dropdown
    When I select the option "<option_text>" from the Select Value dropdown
    Then the Select Value dropdown should display "<option_text>"

    Examples:
      | option_text |
      | Group 1, option 2 |
      
     # | Group 2, option 1 |
     # | A root option     |

  Scenario Outline: Select a color from a custom single-select dropdown
    When I select the color "<color_name>" from the Old Style Select Menu dropdown
    Then the Old Style Select Menu dropdown should display "<color_name>" as selected

    Examples:
      | color_name |
      | Green      |
      | Indigo     |
      | Red        |


  Scenario: Select multiple options from a multi-select dropdown
    When I select colors "Red", "Blue", and "Black" from the Multiselect dropdown
    Then the Multiselect dropdown should display "Red", "Blue", and "Black" as selected

