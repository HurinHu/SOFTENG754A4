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
}
