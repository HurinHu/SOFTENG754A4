import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void getCourseTime(){
        int course_id;
        Database db = Mockito.mock(Database.class);
        User user = new User(db);
        String courseTime = user.getCourseTime(course_id);
        assertEquals(courseTime, "Monday 14:00-15:00");
    }
}
