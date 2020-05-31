package web;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class User {
    int _user_id = 123;
	String _user_role = "";
	String _user_name = "";
    String _Gpa;
    Database _db;
    Map<Integer, String> _enrolledCourses = new Hashtable<>();//<course_id,course time>

    public User() {

    }
	
	public User(int id, String role, String name) {
		this._user_id = id;
		this._user_role = role;
		this._user_name = name;
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
        return this._user_id ;
    }

    public String getCourseTime(int course_id) {
        return _enrolledCourses.get(course_id);
    }
	
	public String getRole() {
        return this._user_role;
    }
	
	public String getName() {
        return this._user_name;
    }

    public boolean isMasterStudent() {
		if(this._user_role.equals("Master Student")){
			 return true;
		}else{
			 return false;
		}
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

