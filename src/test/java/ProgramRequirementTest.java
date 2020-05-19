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

    @Test
    public void testIsMeetTheRequirement() {
        Database db = Mockito.mock(Database.class);
        User user = Mockito.mock(User.class);
        ProgramRequirementsService programRequirementsService = new ProgramRequirementsService(db, user);

        // check if user has met the requirement of the programme which is judged by number of compulsory courses need to
        // take for that programme - number of compulsory courses has selected by the user.
        // if the outcome is 0, which means user has selected as many compulsory courses as he needed for his programme
        // if the outcome is larger than 0, which means user hasn't met the requirement of his programme
        Mockito.when(user.getId()).thenReturn(1);
        Mockito.when(db.getProgramme(1)).thenReturn(123);
        Mockito.when(db.getCompulsoryCoursesRequirementOfProgram(123)).thenReturn(5);
        Mockito.when(db.getSelectedCompulsoryCourseNumber(1)).thenReturn(5);
        String output = programRequirementsService.isMeetTheRequirement();
        assertEquals("You have met the requirement of the programme!", output);

        Mockito.when(user.getId()).thenReturn(2);
        Mockito.when(db.getProgramme(2)).thenReturn(124);
        Mockito.when(db.getCompulsoryCoursesRequirementOfProgram(124)).thenReturn(5);
        Mockito.when(db.getSelectedCompulsoryCourseNumber(2)).thenReturn(3);
        output = programRequirementsService.isMeetTheRequirement();
        assertEquals("You haven't met the requirement of programme, still need to take 2 compulsory courses to complete.", output);
    }
}
