Feature: Apply concession
  Students are able to apply concession

  Scenario: Student can apply concession
	Given the user logged in as non-Master student and he can apply concession for a course
	When the course "SOFTENG 750" is required to concession
	Then click "Apply" button
    And the status shows "Concession Applied"
