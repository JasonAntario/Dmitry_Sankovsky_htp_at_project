Feature: Finding users

  Scenario Outline: Finding users by username
    Given I start finding by <index> predicate
    When I get a response from a web service
    And I form a known <resultName> result
    Then I validate the web service response
    Examples:
      | index | resultName      |
      | 0     | "ALL_USERS"     |
      | 1     | "PARTIAL_SHORT" |
      | 2     | "FULL_SHORT"    |
      | 3     | "PARTIAL_LONG"  |
      | 4     | "FULL_LONG"     |