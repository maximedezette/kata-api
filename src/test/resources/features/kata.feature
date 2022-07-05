Feature: The kata can be retrieved

  Scenario: A user publish a new Kata
    Given A user publish a new Kata named AperoTech
    When A user asks for the Kata list
    Then He should have a success response
    And The following kata list should be in the response content
          | name      |
          | AperoTech |