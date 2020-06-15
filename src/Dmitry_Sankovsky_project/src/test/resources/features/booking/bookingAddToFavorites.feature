Feature: Add to Favorites

  Scenario: Add 2 Hotels to the Favorites, check color of the heart button
    Given I go to booking.com
    Then I log in
    Then I choose city "Madrid", on 5 days after 30 days for 2 adults and 0 children in 1 rooms and search
    And I click heart button on the first hotel
    And I check first heart button color
    Then I go to last page
    And I click heart button on the last hotel
    And I check last heart button color
    And I check hotels id
