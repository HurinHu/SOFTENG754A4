Feature: Is Course Information displayed?
  Every student use the system wants to know the course information
   
  Scenario: Student wants to know a single course’s location
    Given The student has logged in to the system   
    When the student clicked the course information button 
    And he selected SOFTENG754
    Then the student should be told "303G01"
  