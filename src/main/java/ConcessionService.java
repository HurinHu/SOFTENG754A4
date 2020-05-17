public class ConcessionService {
    public ConcessionService(Database db) {
    }

    public Boolean applyConcession(User user, Course course) {
        if (user.isValidForConcession() && course.isValidForConcession()){
            return true;
        } else {
            return false;
        }
    }
}
