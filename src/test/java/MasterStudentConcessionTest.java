import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

@Category(UnitTest.class)
public class MasterStudentConcessionTest {
    @Test
    public void testApplyConcessionForMasterStudent() {
        Database db = Mockito.mock(Database.class);
        ConcessionService concessionService = new ConcessionService(db);
        Course course = Mockito.mock(Course.class);
        User user = Mockito.mock(User.class);
        Mockito.when(course.isValidForConcession()).thenReturn(true);
        Mockito.when(user.isValidForConcession()).thenReturn(true);
        // check if user is a master student
        Mockito.when(user.isMasterStudent()).thenReturn(true);
        Boolean output = concessionService.applyConcession(user, course);
        assertEquals(true, output);
    }

    @Test
    public void testCreateConcessionForMasterStudent() {
        Database db = Mockito.mock(Database.class);
        ConcessionService concessionService = new ConcessionService(db);
        Course course = Mockito.mock(Course.class);
        User user = Mockito.mock(User.class);

        Mockito.when(db.isMeetRequirement(user, course)).thenReturn(true);
        Mockito.when(db.enrollCourse(user.getId(), course.getCourseId())).thenReturn(true);
        String output = concessionService.createConcessionForMasterStudent(user, course);
        assertEquals("enrolled", output);

        Mockito.when(db.isMeetRequirement(user, course)).thenReturn(true);
        Mockito.when(db.enrollCourse(user.getId(), course.getCourseId())).thenReturn(false);
        output = concessionService.createConcessionForMasterStudent(user, course);
        assertEquals("failed", output);

        Mockito.when(db.isMeetRequirement(user, course)).thenReturn(false);
        output = concessionService.createConcessionForMasterStudent(user, course);
        assertEquals("concession", output);
    }
}
