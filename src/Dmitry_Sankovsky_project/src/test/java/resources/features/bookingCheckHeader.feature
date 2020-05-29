Feature: Check header elements

  Scenario: Log in and check all header elements exist
    Given I go to booking.com
    Then I log in
    And I find all header elements
    And I check the number of items found