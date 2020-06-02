Feature: Apply concession
  Students are able to apply concession

  Scenario: Student can apply concession
    Given the user logged in as non-Master student and he can apply concession for a course
    When the course "SOFTENG 750" is required to concession
    Then click "Apply" button
    And the status shows "Concession Applied"

  Scenario: Enrol the course after concession approved
    Given the user logged in as non-Master student and he can apply concession for a course
    When the course "SOFTENG 750" is required to concession
    And the course "SOFTENG 750" concession is approved
    Then the status shows "Enrolled"

  Scenario: Concession rejected
    Given the user logged in as non-Master student and he can apply concession for a course
    When the course "SOFTENG 750" is required to concession
    And the course "SOFTENG 750" concession is rejected
    Then the status shows "Concession Rejected"

