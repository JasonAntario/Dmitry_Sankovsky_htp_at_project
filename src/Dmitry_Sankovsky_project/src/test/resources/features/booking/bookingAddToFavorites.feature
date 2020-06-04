Feature: Add to Favorites

  Scenario: Add 2 Hotels to the Favorites, check color of the heart button
    Given I go to booking.com
    Then I log in
    Then I enter data to search
    And I click heart button on the first hotel
    And I check heart button color
    Then I go to last page
    And I click heart button on the last hotel
    And I check heart button color
    Then I go to user page
    And I check hotels id
