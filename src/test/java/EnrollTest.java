import static org.junit.Assert.assertEquals;

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
}
