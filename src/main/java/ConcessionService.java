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

    public void createConcession(User user, Course course) {
        Concession concession = new Concession(user, course);
        _db.setConcessionPool(concession);
    }
}
