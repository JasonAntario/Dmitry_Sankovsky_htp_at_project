Feature: Finding hotels in Oslo

  Scenario: Finding hotel in Oslo and changing color
    Given I go to booking.com
    Then I choose city "Oslo", on 1 days after 1 days for 2 adults and 1 children in 1 rooms and search
    And I find hotels with 3 and 4 stars
    Then I find hotel №10 in list
    And I'm changing background and text color
    And I check that the text color is red
