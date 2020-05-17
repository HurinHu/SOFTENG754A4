import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import org.mockito.*;

public class CourseTest {
  @Test
  public void testGetCourseDescription() {
    int course_id = 123;
    Database db = Mockito.mock(Database.class);
    Course course = new Course(db, course_id);
    Mockito.when(db.getDescription(course_id)).thenReturn("some description");
    String descripion = course.getCourseDescription();
    assertEquals("some description", descripion);
    try{
      course = new Course(db, -1);
      descripion = course.getCourseDescription();
    } catch (Exception e) {
      assertEquals("Course is not exist", e.getMessage());
    }
  }

}