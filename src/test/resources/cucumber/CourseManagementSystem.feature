@CourseManagement
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
      | SOFTENG759 | 0                  | 1          | 49           |
      | SOFTENG759 | 1                  | 2          | 47           |
      | SOFTENG759 | 3                  | 47         | 0            |
      | SOFTENG759 | 50                 | -1         | 1            |
      | SOFTENG759 | 49                 | -49        | 50           |