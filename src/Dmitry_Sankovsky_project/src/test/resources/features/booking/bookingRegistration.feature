Feature: Registration new user in booking

  Scenario: create trash email and registration on booking
    Given I go to trashmail.com
    Then I get new trash mail
    Then I go to booking.com
    Then I create new user
    And I go to yandex.ru
    And I confirm email
    Then I go to booking.com
    Then I go to user page
    And a registration banner could't exist
