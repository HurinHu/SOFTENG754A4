import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.Hashtable;

import static org.junit.Assert.assertEquals;

@Category(UnitTest.class)
public class UserTest {

    @Test
    public void testGetCourseTime(){
        int course_id = 123;
        int user_id = 123;
        Hashtable<Integer, String> table = new Hashtable();
        table.put(123,"Monday 14:00-15:00");
        Database db = Mockito.mock(Database.class);
        Mockito.when(db.getEnrolledCourse(user_id)).thenReturn(table);
        User user = new User(db);
        String courseTime = user.getCourseTime(course_id);
        assertEquals("Monday 14:00-15:00",courseTime);
    }

    @Test
    public void testCheckTimeClashOneHour(){
        int user_id = 123;
        int user2_id = 345;
        Database db = Mockito.mock(Database.class);

        Hashtable<Integer, String> table = new Hashtable();
        table.put(123,"Monday 14:00-15:00");
        table.put(123,"Monday 14:00-15:00");
        table.put(345,"Monday 14:00-15:00");
        table.put(345,"Monday 14:00-15:00");
        Hashtable<Integer, String> Gpatable = new Hashtable();
        Gpatable.put(user_id,"9");
        Gpatable.put(user2_id,"6");
        Mockito.when(db.getUsersGpa(user_id)).thenReturn(Gpatable);
        Mockito.when(db.getUsersGpa(user2_id)).thenReturn(Gpatable);
        Mockito.when(db.getEnrolledCourse(user_id)).thenReturn(table);
        Mockito.when(db.getEnrolledCourse(user2_id)).thenReturn(table);
        User user = new User(db,user_id);
        boolean timeClashAllowed = user.checkTimeTableClashOneHour();
        assertEquals(timeClashAllowed,true);

        User user2 = new User(db,user2_id);

        boolean timeClashAllowed2 = user2.checkTimeTableClashOneHour();
        assertEquals(timeClashAllowed2,false);
    }
}
