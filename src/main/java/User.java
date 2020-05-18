import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class User {
    int _user_id = 123;
    String _Gpa;
    Database _db;
    Map<Integer, String> _enrolledCourses = new Hashtable<>();//<course_id,course time>

    public User() {

    }

    public User(Database db) {
        this._db = db;
        this._enrolledCourses = db.getEnrolledCourse(this._user_id);
    }

    public User(Database db, int user_id) {
        this._db = db;
        this._user_id = user_id;
        this._enrolledCourses = db.getEnrolledCourse(this._user_id);
        Hashtable<Integer,String> table = this._db.getUsersGpa(this._user_id);
        this._Gpa = table.get(this._user_id);
    }

    public boolean isValidForConcession() {
        return true;
    }

    public int getId() {
        return 0;
    }

    public String getCourseTime(int course_id) {
        return _enrolledCourses.get(course_id);
    }

    public boolean isMasterStudent() {
        return false;
    }

    public boolean checkTimeTableClashOneHour() {
        if (containsClash(this._enrolledCourses)) {
            if (Integer.parseInt(this._Gpa) > 7) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    public boolean containsClash(Map<Integer, String> _enrolledCourses) {
        return true;
    }
}

