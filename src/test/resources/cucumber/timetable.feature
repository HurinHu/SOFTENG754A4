Feature: Timetable
  Student can view the timetable and select time slots

  Scenario: Student should know the time clash
	Given the user enrolled a course "SOFTENG 750"
	When the course "SOFTENG 750" time is clashed with other enrolled courses
	Then "time clash" notification should be shown

  Scenario: Student should view course time slots
	Given the user logged in and view a course "SOFTENG 750"
	When user select a course "SOFTENG 750"
	Then time slots "Mon 15:00-18:00" and "Thu 09:00-10:00" should be shown

  Scenario: Student should select available course time slots
	Given the user logged in and select a course "SOFTENG 750" time slots
	When user select a time slots "Mon 15:00-18:00"
	Then available time slots "Mon 15:00-18:00" should be able to select
