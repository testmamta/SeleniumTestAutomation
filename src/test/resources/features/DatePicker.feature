Feature: Date Picker and Calendar Interaction

  As a user of DemoQA
  I want to select specific dates and times using the calendar widget
  So that I can input date/time information into forms

  Background:
    Given I am on the DemoQA "Date Picker" page

  Scenario: Select a date using the Date Picker
    When I click on the "Select Date" input field
    And I select year "2024"
    And I select month "December"
    And I select day "25"
    Then the "Select Date" input field should show "12/25/2024"

  Scenario: Select a date and time using the Date and Time Picker
    When I click on the "Select Date and Time" input field
    And I select year "2025"
    And I select month "January"
    And I select day "1"
    And I select time "10:30" AM
    Then the "Select Date and Time" input field should show "January 1, 2025 10:30 AM"

  Scenario Outline: Verify date picker navigation
    When I click on the "Select Date" input field
    And I navigate "<direction>" by "<count>" months in the calendar
    Then the displayed month should be "<expected_month>" and year "<expected_year>"

    Examples:
      | direction | count | expected_month | expected_year |
      | next      | 1     | March          | 2025          |
      | previous  | 2     | December       | 2024          |
      | next      | 12    | February       | 2026          |