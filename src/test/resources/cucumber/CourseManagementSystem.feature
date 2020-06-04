Feature: Course Management System


  Scenario: Course Coordinator post new course to the system
    Given user logged in as Course Coordinator
    When enters the course details in correct format name:"Software Architecture",code:"SOFTENG345"
    And he clicks "Add Course" in the course management page
    Then the new course should be displayed in the system
