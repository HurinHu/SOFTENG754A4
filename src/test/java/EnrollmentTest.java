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

  @Test
  public void testGetRemainingSeats() {
    Course course = Mockito.mock(Course.class);
    Database db = Mockito.mock(Database.class);
    // class full
    Mockito.when(course.getCourseId()).thenReturn(123);
    Mockito.when(db.getCapacity(123)).thenReturn(10);
    Mockito.when(db.getEnrolled(123)).thenReturn(10);
    Enrollment enrollment = new Enrollment(db);
    int remaining = enrollment.getRemainingSeats(course);
    assertEquals(0, remaining);
    // remain 10 seats
    Mockito.when(course.getCourseId()).thenReturn(125);
    Mockito.when(db.getCapacity(125)).thenReturn(20);
    Mockito.when(db.getEnrolled(125)).thenReturn(10);
    remaining = enrollment.getRemainingSeats(course);
    assertEquals(10, remaining);
    // course id not exist
    try{
      Mockito.when(course.getCourseId()).thenReturn(-1);
      remaining = enrollment.getRemainingSeats(course);
    } catch (Exception e) {
      assertEquals("Course is not exist", e.getMessage());
    }
    // remaining seats less than zero
    try{
      Mockito.when(course.getCourseId()).thenReturn(126);
      Mockito.when(db.getCapacity(126)).thenReturn(10);
      Mockito.when(db.getEnrolled(126)).thenReturn(20);
      remaining = enrollment.getRemainingSeats(course);
    } catch (Exception e) {
      assertEquals("Internal Error", e.getMessage());
    }
  }

  @Test
  public void testGetCourseStatus() {
    Course course = Mockito.mock(Course.class);
    Database db = Mockito.mock(Database.class);
    User user = Mockito.mock(User.class);
    // enrolled
    Mockito.when(course.getCourseId()).thenReturn(123);
    Mockito.when(user.getId()).thenReturn(321);
    Mockito.when(db.getStatus(123, 321)).thenReturn("Enrolled");
    Enrollment enrollment = new Enrollment(db,user);
    String status = enrollment.getCourseStatus(course);
    assertEquals("Enrolled", status);

    // in cart
    Mockito.when(course.getCourseId()).thenReturn(124);
    Mockito.when(user.getId()).thenReturn(322);
    Mockito.when(db.getStatus(124, 322)).thenReturn("In Cart");
    status = enrollment.getCourseStatus(course);
    assertEquals("In Cart", status);

    // withdraw
    Mockito.when(course.getCourseId()).thenReturn(125);
    Mockito.when(user.getId()).thenReturn(323);
    Mockito.when(db.getStatus(125, 323)).thenReturn("Withdraw");
    status = enrollment.getCourseStatus(course);
    assertEquals("Withdraw", status);

    // concession
    Mockito.when(course.getCourseId()).thenReturn(126);
    Mockito.when(user.getId()).thenReturn(324);
    Mockito.when(db.getStatus(126, 324)).thenReturn("Concession");
    status = enrollment.getCourseStatus(course);
    assertEquals("Concession", status);

    // concession rejected
    Mockito.when(course.getCourseId()).thenReturn(127);
    Mockito.when(user.getId()).thenReturn(325);
    Mockito.when(db.getStatus(127, 325)).thenReturn("Concession Rejected");
    status = enrollment.getCourseStatus(course);
    assertEquals("Concession Rejected", status);

    // course id not exist
    try{
      Mockito.when(course.getCourseId()).thenReturn(-1);
      Mockito.when(user.getId()).thenReturn(326);
      status = enrollment.getCourseStatus(course);
    } catch (Exception e) {
      assertEquals("Course is not exist", e.getMessage());
    }

    // student id not exist
    try{
      Mockito.when(course.getCourseId()).thenReturn(128);
      Mockito.when(user.getId()).thenReturn(-1);
      status = enrollment.getCourseStatus(course);
    } catch (Exception e) {
      assertEquals("Student is not exist", e.getMessage());
    }
  }

}