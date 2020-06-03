Feature: Enrollment
  Students want to enroll courses

  Scenario: Students want to add courses to enrollment cart
    Given the user logged in as a student and in enrollment page
    When the student clicked "Add classes" button
    And the course "Softeng751" needs to be added to cart
    And the student click "Add to cart" button
    Then the selected course will be added to "Enrollment cart"
    And the course status shows "In cart"







