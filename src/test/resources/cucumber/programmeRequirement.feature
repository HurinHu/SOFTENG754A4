Feature: Programme Requirement

  Scenario: student want to know the programme requirement of his programme
    Given user logged in as a Software Engineering Student
    And he is in his "4" year study
    When he clicks enrol
    And he clicks my programme requirement tab
    Then he should see "SOFTENG754", "SOFTENG751", "SOFTENG701" as compulsory
    And he should see "COMPSCI754", "COMPSCI751", "COMPSCI761" as elective

  Scenario: student want to know if his programme requirement of his programme has been met
    Given user logged in as a Software Engineering Student
    And his is in his 3rd year of study
    When he clicks enrol
    And he is enrolled in "SOFTENG754", "SOFTENG751", "SOFTENG701"
    And he clicks my programme requirement tab
    Then he should be told that if he has "met the requirement" for the 3rd year



