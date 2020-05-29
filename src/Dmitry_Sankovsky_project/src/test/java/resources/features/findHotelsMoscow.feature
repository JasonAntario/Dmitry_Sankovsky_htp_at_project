Feature: Finding hotels in Moscow

  Scenario: Finding hotel with minimal price in Moscow
    Given I go to booking.com
    Then I enter data to search
    And I enter alders amount by actions
    Then I filter hotels at the minimum price
    And I'm looking hotel with minimum price
    And I compare hotel's price and price in filters
