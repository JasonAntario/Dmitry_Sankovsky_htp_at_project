Feature: Finding hotels in Paris


  Scenario: Finding hotel with maximal price in Paris
    Given I go to booking.com
    Then I choose city "Paris", on 7 days after 3 days for 4 adults and 0 children in 2 rooms and search
    Then I filter hotels at the maximum price
    And I'm looking hotel with minimum price
    And hotel's price could be higher than price in filters
