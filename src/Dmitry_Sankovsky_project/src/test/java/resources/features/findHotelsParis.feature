Feature: Finding hotels in Paris

  Scenario: Finding hotel with maximal price in Paris
    Given I go to booking.com
    Then I enter data to search
    Then I filter hotels at the maximum price
    And I'm looking hotel with minimum price
    And I compare hotel's price and price in filters
