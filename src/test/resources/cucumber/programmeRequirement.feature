Feature: Programme Requirement

  Scenario: student want to know the programme requirement of his programme
    Given user logged in as a Software Engineering Student
    And he is in his "4" year study
    When he clicks enrol
    And he clicks my programme requirement tab
    Then he should see all the compulsory courses for software engineering degree
    And he should see the available elective courses


