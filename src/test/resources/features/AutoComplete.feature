Feature: Auto Complete with AJAX

  As a user of DemoQA
  I want to use the auto-complete feature
  So that I can quickly find and select options from dynamic suggestions

  Background:
    Given I am on the DemoQA "Auto Complete" page

  Scenario Outline: Select a single color from auto-complete
    When I type "<input_text>" into the "Type single color name" field
    Then I should see auto-complete suggestions
    And I select "<selection_text>" from the auto-complete suggestions
    Then the "Type single color name" field should contain "<selection_text>"

    Examples:
      | input_text | selection_text |
      | Gr         | Green          |
      | Ye         | Yellow         |

  Scenario Outline: Select multiple colors from auto-complete
    When I type "<input_text>" into the "Type multiple color names" field
    Then I should see auto-complete suggestions
    And I select "<selection_text>" from the auto-complete suggestions
    Then the "Type multiple color names" field should contain "<expected_value>"

    Examples:
      | input_text | selection_text | expected_value     |
      | Bl         | Black          | Black              |
      | Wh         | White          | Black, White       |
      | Gr         | Green          | Black, White, Green|