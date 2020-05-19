import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProgramRequirementTest {
    @Test
    public void testGetCompulsoryCourses() {
        Database db = Mockito.mock(Database.class);
        User user = Mockito.mock(User.class);
        ProgramRequirementsService programRequirementsService = new ProgramRequirementsService(db, user);

        List<Course> courses = new ArrayList<Course>();
        courses.add(new Course());
        courses.add(new Course());

        // get the compulsory courses for a programme
        Mockito.when(user.getId()).thenReturn(1);
        Mockito.when(db.getProgramme(1)).thenReturn(123);
        Mockito.when(db.getCompulsoryCourses(123)).thenReturn(courses);

        List<Course> output = programRequirementsService.getCompulsoryCourses();
        assertEquals(courses, output);
    }
}
