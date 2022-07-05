Feature: The kata can be retrieved

  Scenario: A user publish a new Kata
    Given A user publish a new Kata
    When A user asks for the Kata list
    Then He should have a success response