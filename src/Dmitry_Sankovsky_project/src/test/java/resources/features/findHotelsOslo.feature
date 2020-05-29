Feature: Finding hotels in Oslo

  Scenario: Finding hotel in Oslo and changing color
    Given I go to booking.com
    Then I enter data to search
    And I find hotels with 3 and 4 stars
    Then I find hotel №10 in list
    And I'm changing background and text color
    And I check that the text color is red
