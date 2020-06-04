Feature: Course Management System


  Scenario: Course Coordinator post new course to the system
    Given user logged in as Course Coordinator
    When he clicks “Add Course” in the course management page
    And he enters the course details in correct format name:"Software Architecture",code:"SOFTENG345"
    Then the new course should be displayed in the system