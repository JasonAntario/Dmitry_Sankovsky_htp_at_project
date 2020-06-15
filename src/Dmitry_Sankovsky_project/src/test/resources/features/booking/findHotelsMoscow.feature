Feature: Finding hotels in Moscow

  Scenario: Finding hotel with minimal price in Moscow
    Given I go to booking.com
    Then I choose city "Moskow", on 5 days after 10 days for 2 adults and 0 children in 1 rooms and search
    And I enter 4 adults and 2 rooms by actions
    Then I filter hotels at the minimum price
    And I'm looking minimum price hotel
    And hotel's price could be lower than price in filters
