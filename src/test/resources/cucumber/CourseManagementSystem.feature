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