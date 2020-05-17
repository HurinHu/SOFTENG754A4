import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class NormalConcession {

    @Test
    public void testApplyConcession() {
        Database db = Mockito.mock(Database.class);
        ConcessionService concessionService = new ConcessionService(db);
        Course course = Mockito.mock(Course.class);
        User user = Mockito.mock(User.class);
        Mockito.when(course.isValidForConcession()).thenReturn(true);
        Mockito.when(user.isValidForConcession()).thenReturn(true);
        Boolean output = concessionService.applyConcession(user, course);
        assertEquals(true, output);

        // if one of user and course is not valid, then failed to apply for a concession
        Course course2 = Mockito.mock(Course.class);
        User user2 = Mockito.mock(User.class);
        Mockito.when(course2.isValidForConcession()).thenReturn(false);
        Boolean output2 = concessionService.applyConcession(user2, course2);
        assertEquals(false, output2);

        Course course3 = Mockito.mock(Course.class);
        User user3 = Mockito.mock(User.class);
        Mockito.when(user3.isValidForConcession()).thenReturn(false);
        Boolean output3 = concessionService.applyConcession(user3, course3);
        assertEquals(false, output3);
    }
}
