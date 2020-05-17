public class ConcessionService {
    private Database _db;

    public ConcessionService(Database db){
        _db = db;
    }

    public Boolean applyConcession(User user, Course course) {
        if (user.isValidForConcession() && course.isValidForConcession()){
            return true;
        } else {
            return false;
        }
    }
}
