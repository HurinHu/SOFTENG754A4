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

  @Test
  public void testGetCourseTimeslots() {
	  int id1 = 1;
	  String time1 = "Tue 11:00-12:00";
	  String time2 = "Wed 13:00-15:00";
	  int courseId1 = 1;
	  int courseId2 = 2;
	  int capacity1 = 50;
	  int capacity2 = 50;
	  TimeSlot ts1 = new TimeSlot(id1,time1,courseId1,capacity1);
	  TimeSlot ts2 = new TimeSlot(id1,time2,courseId1,capacity2);
	  List<TimeSlot> timeslots = new ArrayList<TimeSlot>();
	  timeslots.add(ts1);
	  timeslots.add(ts2);
	  Course course = new Course(id1,timeslots);
	  //Mockito.when(course.getTimeSlots(1)).thenReturn(timeslots);
	  List<TimeSlot> outputTs = course.getTimeSlots(id1);
	  assertEquals(outputTs,timeslots);
  }
  
  
  @Test
  public void testSelectTimeSlot() {
	  int courseId = 1;
	  Database db = Mockito.mock(Database.class);
	  Course course = new Course(db,courseId);
	  Mockito.when(db.selectTimeSlot(courseId)).thenReturn("Tue 11:00-12:00");
	  String selectedTime = course.selectTimeSlot(courseId);
	  assertEquals(selectedTime,"Tue 11:00-12:00");

  }

  @Test
  public void testGetCourseLocation() {
    int course_id = 123;
    Database db = Mockito.mock(Database.class);
    Mockito.when(db.getCourseLocation(course_id)).thenReturn("201N-346");
    Course course = new Course(db, course_id);
    String location = course.getLocation();
    assertEquals("201N-346",location);
  }
  
  @Test
  public void testGetTimeClash() {
	  int courseId = 1;
	  int timeslotId = 1; 
	  Database db = Mockito.mock(Database.class);
	  Course course = new Course(db,courseId);
	  Mockito.when(db.checkTimetableClash(courseId,timeslotId)).thenReturn(true);
	  boolean clash = course.checkTimetableClash(courseId,timeslotId);
	  assertEquals(clash,true);
  }
  
  
  @Test
  public void testGetCourseCapacityForTimeSlot() {
	  int courseId = 1;
	  int timeslotId = 1; 
	  Database db = Mockito.mock(Database.class);
	  Course course = new Course(db,courseId);
	  Mockito.when(db.getCourseCapacityForTimeSlot(courseId,timeslotId)).thenReturn(100);
	  int capacity = course.getCourseCapacityForTimeSlot(courseId,timeslotId);
	  assertEquals(capacity,100);
  }
  
  @Test
  public void testGetCompulsoryAndElectiveTimeSlotCapacity() {
	  int courseId = 1;
	  int timeslotId = 1; 
	  Database db = Mockito.mock(Database.class);
	  Course course = new Course(db,courseId);
	  List<Integer> compulsorycapacityresult = new ArrayList<Integer>();
	  compulsorycapacityresult.add(1);
	  compulsorycapacityresult.add(3);
	  List<Integer> electivecapacityresult = new ArrayList<Integer>();
	  electivecapacityresult.add(100);
	  electivecapacityresult.add(80);
	  
	  Mockito.when(db.getCourseCapacityForCompulsory(courseId,timeslotId)).thenReturn(compulsorycapacityresult);
	  Mockito.when(db.getCourseCapacityForElective(courseId,timeslotId)).thenReturn(electivecapacityresult);
	  
	  List<Integer> compulsorycapacity = course.getCourseCapacityForCompulsory(courseId,timeslotId);
	  List<Integer> electivecapacity = course.getCourseCapacityForElective(courseId,timeslotId);

	  assertEquals(compulsorycapacity,compulsorycapacityresult);
	  
	  assertEquals(electivecapacity,electivecapacityresult);
  }
  
  
  @Test
  public void testGetPrerequestCourse() {
	  int courseId = 1; 
	  int prequestCourseId1 = 2;
	  int prequestCourseId2 = 3;
	  Database db = Mockito.mock(Database.class);
	  Course course = new Course(db,courseId);
	  Course prerequestCourse1 = new Course(db,prequestCourseId1);
	  Course prerequestCourse2 = new Course(db,prequestCourseId2);
	  List<Course> prequests = new ArrayList<Course>();
	  prequests.add(prerequestCourse1);
	  prequests.add(prerequestCourse2);
	  
	  Mockito.when(db.getPrerequestCourse(courseId)).thenReturn(prequests);
	  List<Course> prequest = course.getPrerequestCourse(courseId);
	  assertEquals(prequest,prequests);
  }
  
  @Test
  public void testGetConflictCourse() {
	  int courseId = 1; 
	  int conflictCourseId1 = 2;
	  Database db = Mockito.mock(Database.class);
	  Course course = new Course(db,courseId);
	  Course conflictCourse1 = new Course(db,conflictCourseId1);
	  List<Course> conflictCourses = new ArrayList<Course>();
	  conflictCourses.add(conflictCourse1);
	  Mockito.when(db.getConflictCourse(courseId)).thenReturn(conflictCourses);
	  List<Course> conflictOutput = course.getConflictCourse(courseId);
	  assertEquals(conflictOutput,conflictCourses);
  }
  
  
  
}