Feature: Users Api Test

  Scenario: Check user by full name
    Given I start execution
    When I search user by "Vova" name
    Then I verify that I got "Vova"

  Scenario: Check user by partial name
    Given I start execution
    When I search user by "Vl" name
    Then I verify that I got "Vladimir"
