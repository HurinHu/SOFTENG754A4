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
    Then the student should be able to see "303G01,303G02"
   
  Scenario: Students want to know single course time
    Given The student has logged in to the system   
    When the student clicked the course information button 
    And he selected SOFTENG751
    Then the student should get "12:00"
    
  Scenario: Students want to know multiple course time
    Given The student has logged in to the system   
    When the student clicked the course information button 
    And he selected SOFTENG751 and SOFTENG754
    Then the student should receive "12:00,10:00"
    
  Scenario: Students want to know single course description
    Given The student has logged in to the system   
    When the student clicked the course information button 
    And he clicked getDescription button for SOFTENG751
    Then the student should get "OTHERDESCRIPTION" as course description
    
  Scenario: Students want to know single course description
    Given The student has logged in to the system   
    When the student clicked the course information button 
    And he clicked getDescription button for SOFTENG751 and SOFTENG754
    Then the student should get "OTHERDESCRIPTION,SOMEDESCRIPTION" as course descriptions