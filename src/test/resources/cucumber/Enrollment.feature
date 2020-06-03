Feature: Enrollment
  Students want to enroll courses

  Scenario: Students want to add courses to enrollment cart
    Given the user logged in as a student and in enrollment page
    When the student clicked "Add classes" button
    And the course "Softeng751" needs to be added to cart
    And the student click "Add to cart" button
    Then the selected course will be added to "Enrollment cart"
    And the course status shows "In cart"

  Scenario:	Students want to confirm selected courses
    Given the user logged in as a student and in enrollment page
    And he has added courses "Softeng752" and "Softeng753" to enrollment cart
    When "Softeng752" is selected in enrollment cart
    And "Softeng753" is not selected in enrollment cart
    And he click "confirm the selected courses" button in enrollment cart page
    Then the selected course will be added to "Current Enrollment Status" tab view and the course status shows "enrolled"
    And the unselected course will stay in "Enrollment cart" tab view and the course status shows "In cart"

  Scenario:	Students want to cancel selected courses
    Given the user logged in as a student and in enrollment page
    And he has added courses "Softeng754" and "Softeng755" to enrollment cart
    When "Softeng754" is selected in enrollment cart
    And "Softeng755" is not selected in enrollment cart
    And he click "cancel the selected courses" button in enrollment cart page
    Then the selected course will be added to "Add classes" tab view and the course status shows "unenrolled"
    And the unselected course will stay in "Enrollment cart" tab view and the course status shows "In cart"


