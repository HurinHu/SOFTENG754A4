Feature: Timetable
  Student can view the timetable and select time slots

  Scenario: Student should know the time clash
	Given the user enrolled a course "SOFTENG 750"
	When the course "SOFTENG 750" time is clashed with other enrolled courses
	Then "time clash" notification should be shown
