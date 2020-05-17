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

  @Test
  public void testCreateCourse() {
    Database db = Mockito.mock(Database.class);
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

    // create success
    Mockito.when(db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status)).thenReturn(true);
    boolean flag = course.create(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status);
    assertTrue(flag);

    // create fail
    Mockito.when(db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status)).thenReturn(false);
    flag = course.create(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status);
    assertFalse(flag);

    // empty description
    try{
      Mockito.when(db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status)).thenReturn(false);
      flag = course.create("",capacity,concession,compulsory_program,prerequisite,timeslots,location,status);
    } catch (Exception e) {
      assertEquals("Course description is empty", e.getMessage());
    }
    try{
      Mockito.when(db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status)).thenReturn(false);
      flag = course.create(null,capacity,concession,compulsory_program,prerequisite,timeslots,location,status);
    } catch (Exception e) {
      assertEquals("Course description is empty", e.getMessage());
    }

    // empty capacity
    try{
      Mockito.when(db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status)).thenReturn(false);
      flag = course.create(description,0,concession,compulsory_program,prerequisite,timeslots,location,status);
    } catch (Exception e) {
      assertEquals("Course capacity is invalid", e.getMessage());
    }
    try{
      Mockito.when(db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status)).thenReturn(false);
      flag = course.create(description,-1,concession,compulsory_program,prerequisite,timeslots,location,status);
    } catch (Exception e) {
      assertEquals("Course capacity is invalid", e.getMessage());
    }

    // empty timeslots
    try{
      Mockito.when(db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status)).thenReturn(false);
      flag = course.create(description,capacity,concession,compulsory_program,prerequisite,null,location,status);
    } catch (Exception e) {
      assertEquals("Course timeslots is empty", e.getMessage());
    }
    try{
      Mockito.when(db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status)).thenReturn(false);
      flag = course.create(description,capacity,concession,compulsory_program,prerequisite,new ArrayList<>(),location,status);
    } catch (Exception e) {
      assertEquals("Course timeslots is empty", e.getMessage());
    }

    // empty location
    try{
      Mockito.when(db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status)).thenReturn(false);
      flag = course.create(description,capacity,concession,compulsory_program,prerequisite,timeslots,"",status);
    } catch (Exception e) {
      assertEquals("Course location is empty", e.getMessage());
    }
    try{
      Mockito.when(db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status)).thenReturn(false);
      flag = course.create(description,capacity,concession,compulsory_program,prerequisite,timeslots,null,status);
    } catch (Exception e) {
      assertEquals("Course location is empty", e.getMessage());
    }

    // empty status
    try{
      Mockito.when(db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status)).thenReturn(false);
      flag = course.create(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,"");
    } catch (Exception e) {
      assertEquals("Course status is empty", e.getMessage());
    }
    try{
      Mockito.when(db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status)).thenReturn(false);
      flag = course.create(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,null);
    } catch (Exception e) {
      assertEquals("Course status is empty", e.getMessage());
    }
  }
}