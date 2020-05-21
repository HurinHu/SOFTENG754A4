import static org.junit.Assert.assertEquals;
import java.util.*;
import java.util.Date;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.*;

@Category(IntegrationTest.class)
public class EnrollTest {
	@Test
	public void testMeetRequirementAndEnroll() {
		Database db = Mockito.mock(Database.class);
		Course course = new Course(db);
		User user = Mockito.mock(User.class);
		Enrollment enrol = new Enrollment(db,user);
		Mockito.when(db.enrollCourse(user.getId(), course.getCourseId())).thenReturn(true);
		Mockito.when(db.checkEligibility(course, user)).thenReturn(true);
		boolean result = false;
		if(course.checkEligibility(course, user) == true) {
			result = enrol.enrollCourse(course);
		}
		assertEquals(result,true);
	}

	@Test
	public void testCreateCourseFollowedByCheckingCourseInfo() {
		Database db = new Database();
		Course course = new Course(db);
		String description = "course description";
		int capacity = 100;
		boolean concession = true;
		List<String> compulsory_program = new ArrayList<>();
		compulsory_program.add("Master of Engineering Studies");
		compulsory_program.add("Master of Science");
		List<String> prerequisite = new ArrayList<>();
		prerequisite.add("COMPSCI 321");
		prerequisite.add("COMPSCI 350");
		List<String> timeslots = new ArrayList<>();
		timeslots.add("Tue 11:00-12:00");
		timeslots.add("Wed 13:00-15:00");
		timeslots.add("Fri 10:00-12:00");
		String location = "some where";
		String status = "open";
		course.create(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status);
		List<Course> courses =  db.getCourses();
		assertEquals(courses.size(), 1);
		Course c = courses.get(courses.size()-1);
		assertEquals(c.getCourseId(), 1);
		assertEquals(c.getCourseDescription(), description);
		assertEquals(c.getLocation(), location);
		description = "another course description";
		location = "some where else";
		course.create(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status);
		courses =  db.getCourses();
		assertEquals(courses.size(), 2);
		c = courses.get(courses.size()-1);
		assertEquals(c.getCourseId(), 2);
		assertEquals(c.getCourseDescription(), description);
		assertEquals(c.getLocation(), location);
	}


	@Test
	public void testFailedToEnrollACourseThenApplyForConcession(){
		Database db = Mockito.mock(Database.class);
		Course course = Mockito.mock(Course.class);
		User user = Mockito.mock(User.class);
		Enrollment enrollment = new Enrollment(db, user);
		Mockito.when(db.getCapacity(course.getCourseId())).thenReturn(20);
		Mockito.when(db.getEnrolled(course.getCourseId())).thenReturn(1);
		Mockito.when(user.getId()).thenReturn(1);
		int remaining_before = enrollment.getRemainingSeats(course);
		
		// A student tries to enroll in a course but fails.
		Mockito.when(course.getCourseId()).thenReturn(1);
		Mockito.when(user.getId()).thenReturn(1);
		Mockito.when(db.enrollCourse(1,1)).thenReturn(false);
		boolean output = enrollment.enrollCourse(course);
		assertEquals(false, output);
		
		// Then this student tries to apply for a concession
		ConcessionService concessionService = new ConcessionService(db);
		Mockito.when(user.isValidForConcession()).thenReturn(true);
		Mockito.when(course.isValidForConcession()).thenReturn(true);
		Mockito.when(user.isMasterStudent()).thenReturn(false);
		output = concessionService.applyConcession(user, course);
		assertEquals(true, output);

		// when this concession is created successfully,  then it will be put into a waiting pool and its status will be concession_waiting.
		Date date =  new Date(System.currentTimeMillis());
		Concession concession = new Concession(user,course);
		Mockito.when(db.getConcession(user, course, date)).thenReturn(concession);
		ConcessionStatus status = concessionService.checkConcession(user, course, date);
		assertEquals(ConcessionStatus.concession_waiting, status);

		// Manager can approve this concession, then its status will be changed to concession_enrolled and this course will be enrolled.
		String result = concessionService.approveConcession(user, course, date);
		status = concessionService.checkConcession(user, course, date);
		assertEquals(ConcessionStatus.concession_enrolled, status);
		assertEquals("enrolled", result);

		// When a student enrolled in one course, the number of remaining seat - 1
		Mockito.when(db.getStatus(user.getId(),course.getCourseId())).thenReturn("enrolled");
		String CourseStats = enrollment.getCourseStatus(course);
		assertEquals("enrolled", CourseStats);
		Mockito.when(db.getCapacity(course.getCourseId())).thenReturn(20);
		Mockito.when(db.getEnrolled(course.getCourseId())).thenReturn(2);
		int remaining_after = enrollment.getRemainingSeats(course);
		assertEquals(remaining_before - remaining_after, 1);	

	}

}
