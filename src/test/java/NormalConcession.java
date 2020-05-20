import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@Category(UnitTest.class)
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

    @Test
    public void testCreateConcession() {
        Database db = new Database();
        Course course = new Course();
        User user = new User();
        ConcessionService concessionService = new ConcessionService(db);

        // create 3 concessions and check whether it creates successfully
        concessionService.createConcession(user, course);
        concessionService.createConcession(user, course);
        concessionService.createConcession(user, course);
        int output = db.getConcessionPool().size();
        assertEquals(3, output);
    }

    @Test
    public void testCheckConcessionStatus() {
        Database db = Mockito.mock(Database.class);
        Course course = new Course();
        User user = new User();
        ConcessionService concessionService = new ConcessionService(db);

        Concession c = new Concession(user, course);
        Date date = new Date(System.currentTimeMillis());
        // mocking the step when get data from database
        Mockito.when(db.getConcession(user, course, date)).thenReturn(c);
        ConcessionStatus output = concessionService.checkConcession(user, course, date);
        assertEquals(ConcessionStatus.concession_waiting, output);
    }

    @Test
    public void testApproveAConcession() {
        Database db = Mockito.mock(Database.class);
        Course course = new Course();
        User user = new User();
        ConcessionService concessionService = new ConcessionService(db);

        Concession c = new Concession(user, course);
        Date date = new Date(System.currentTimeMillis());
        // mocking the step when get data from database
        Mockito.when(db.getConcession(user, course, date)).thenReturn(c);

        // Approve the concession then the status of this concession would be changed to concession_enrolled
        concessionService.approveConcession(user, course, date);
        ConcessionStatus output = concessionService.checkConcession(user, course, date);
        assertEquals(ConcessionStatus.concession_enrolled, output);
    }

    @Test
    public void testCancelAConcession() {
        Database db = Mockito.mock(Database.class);
        Course course = new Course();
        User user = new User();
        ConcessionService concessionService = new ConcessionService(db);

        Concession c = new Concession(user, course);
        Date date = new Date(System.currentTimeMillis());
        // mocking the step when get data from database
        Mockito.when(db.getConcession(user, course, date)).thenReturn(c);

        // Cancel the concession then the status of this concession would be changed to concession_cancelled
        concessionService.cancelConcession(user, course, date);
        ConcessionStatus output = concessionService.checkConcession(user, course, date);
        assertEquals(ConcessionStatus.concession_cancelled, output);
    }

    @Test
    public void testEnrollCourseAfterConcessionApproved() {
        Database db = Mockito.mock(Database.class);
        ConcessionService concessionService = new ConcessionService(db);
        Course course = Mockito.mock(Course.class);
        User user = Mockito.mock(User.class);

        Concession c = new Concession(user, course);
        Date date = new Date(System.currentTimeMillis());
        // mocking the step when get data from database
        Mockito.when(db.getConcession(user, course, date)).thenReturn(c);
        Mockito.when(db.enrollCourse(user.getId(), course.getCourseId())).thenReturn(true);

        // Approve the concession then the status of this concession would be changed to concession_enrolled and
        // this course will be enrolled successfully
        concessionService.approveConcession(user, course, date);
        ConcessionStatus output = concessionService.checkConcession(user, course, date);
        String output1 = concessionService.approveConcession(user, course, date);
        assertEquals(ConcessionStatus.concession_enrolled, output);
        assertEquals("enrolled", output1);
    }
}
