#The name of the Feature
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday
  #A brief description of the feature

  #The Scenario to Test.
  #Notice: 'Outline' is only required while working
  #        with multiple test data
  Scenario Outline: Today is or is not Friday
    #The description of the initial system state
    #referencing the test data from the 'day' column
    Given today is "<day>"

    #The action to perform transfer from
    #the initial system state to the final system state
    When I ask whether it's Friday yet

    #The description of the final system state
    #referencing the test data from the 'answer' column
    Then I should be told "<answer>"

    #The test data of the scenario
    Examples:
      | day | answer |
      | Friday | TGIF |
      | Sunday | Nope |
      | anything else! | Nope |