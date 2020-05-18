import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.*;

public class EnrollmentTest {

  @Test
  public void testGetCourseCapacity() {
    Course course = Mockito.mock(Course.class);
    Database db = Mockito.mock(Database.class);
    Mockito.when(course.getCourseId()).thenReturn(123);
    Mockito.when(db.getCapacity(123)).thenReturn(10);
    Enrollment enrollment = new Enrollment(db);
    int capacity = enrollment.getCourseCapacity(course);
    assertEquals(10, capacity);
    try{
      Mockito.when(course.getCourseId()).thenReturn(-1);
      capacity = enrollment.getCourseCapacity(course);
    } catch (Exception e) {
      assertEquals("Course is not exist", e.getMessage());
    }
  }

  @Test
  public void testGetEnrolledStudentNum() {
    Course course = Mockito.mock(Course.class);
    Database db = Mockito.mock(Database.class);
    Mockito.when(course.getCourseId()).thenReturn(123);
    Mockito.when(db.getEnrolled(123)).thenReturn(10);
    Enrollment enrollment = new Enrollment(db);
    int enrolledStudent = enrollment.getEnrolledStudentNum(course);
    assertEquals(10, enrolledStudent);
    try{
      Mockito.when(course.getCourseId()).thenReturn(-1);
      enrolledStudent = enrollment.getEnrolledStudentNum(course);
    } catch (Exception e) {
      assertEquals("Course is not exist", e.getMessage());
    }
  }

}