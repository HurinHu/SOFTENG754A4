import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class User {
    int _user_id = 123;
    Database _db;
    Map<Integer,String> _enrolledCourses = new Hashtable<>();//<course_id,course time>
    public User(){

    }

    public User(Database db){
        this._db = db;
        this._enrolledCourses = db.getEnrolledCourse(this._user_id);
    }

    public boolean isValidForConcession() {
        return true;
    }

	public int getId() {
    	return 0;
  	}

  	public String getCourseTime(int course_id){
        return _enrolledCourses.get(course_id);
    }
}
