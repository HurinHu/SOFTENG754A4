Feature: Course Management System


  Scenario: Course Coordinator post new course to the system
    Given user logged in as Course Coordinator
    When enters the course details in correct format name:"Software Architecture",code:"SOFTENG345"
    And he clicks "Add Course" in the course management page
    Then the new course should be displayed in the system


  Scenario: Course Coordinator post new course to the system with incorrect format information
    Given user logged in as Course Coordinator
    When enters the course details in correct format name:"Software Architecture",code:""
    And he clicks "Add Course" in the course management page
    Then an alert message would pop up "Please fill the information correctly"

  Scenario Outline: update the student enrolled count when enrolled or swapped out
    Given <CourseCode> has <EnrolledStudentNum> student enrolled
    When <newStudent> student enrolled in the course negative reprsents swap out
    Then the remained seating should be <RemainedSeat>
    Examples:
      | CourseCode | EnrolledStudentNum | newStudent | RemainedSeat |
      | SOFTENG754 | 0                  | 1          | 49           |
      | SOFTENG754 | 9                  | 1          | 40           |
      | SOFTENG754 | 49                 | 1          | 0            |
      | SOFTENG751 | 49                 | -1         | 2            |
      | SOFTENG751 | 2                  | -2         | 0            |