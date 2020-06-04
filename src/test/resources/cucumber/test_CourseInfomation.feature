Feature: Is Course Information displayed?
  Every student use the system wants to know the course information
   
  Scenario: Student wants to know a single course’s location
    Given The student has logged in to the system   
    When the student clicked the course information button 
    And he selected SOFTENG754
    Then student should be told "303G01"
  
  Scenario: Student wants to know multiple courses’ locations
    Given The student has logged in to the system   
    When the student clicked the course information button 
    And he selected SOFTENG754 and SOFTENG751
    Then student should be told "303G01,303G02"