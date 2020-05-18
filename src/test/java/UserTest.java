import org.junit.Test;
import org.mockito.Mockito;

import java.util.Hashtable;

import static org.junit.Assert.assertEquals;

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
}
