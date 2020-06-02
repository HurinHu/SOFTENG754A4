package web;

import java.util.List;

public class ProgramRequirementsService {
    Database _db;
    User _user;

    public ProgramRequirementsService(Database db, User user){
        _db = db;
        _user = user;
    }

    public List<Course> getCompulsoryCourses(){
        return _db.getCompulsoryCourses(_db.getProgramme(_user.getId()));
    }

    public List<Course> getElectiveCourses(){
        return _db.getElectiveCourses(_db.getProgramme(_user.getId()));
    }

    public String isMeetTheRequirement() {
        int output = _db.getCompulsoryCoursesRequirementOfProgram(_db.getProgramme(_user.getId())) - _db.getSelectedCompulsoryCourseNumber(_user.getId());
        if (output <= 0){
            return "You have met the requirement of the programme!";
        } else {
            String result = String.format("You haven't met the requirement of programme, still need to take %d compulsory courses to complete.", output);
            return result;
        }
    }
}
