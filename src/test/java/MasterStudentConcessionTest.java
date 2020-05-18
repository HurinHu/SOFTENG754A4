import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

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
}
