import java.util.*;

public class Database {
    List<Concession> _concessionsPool = new ArrayList<>();
    Map<Integer,Map> _enrolledCourses = new Hashtable<>();

    public Database(){

    }

    public void setConcessionPool(Concession concession) {
        _concessionsPool.add(concession);
    }

    public List<Concession> getConcessionPool() {
        return _concessionsPool;
    }

    public Concession getConcession(User user, Course course, Date date) {
        return null;
    }

	public String getDescription(int course_id) {
		return null;
	}

	public boolean createCourse(String description,int capacity,boolean concession,List<String> compulsory_program,List<String> prerequisite,List<String> timeslots,String location,String status) {
		return false;
	}

	public int getCapacity(int course_id) {
		return 0;
	}

	public int getEnrolled(int course_id) {
    	return 0;
  	}

	public String getStatus(int course_id, int user_id) {
    	return null;
	}

	public boolean setStatus(int course_id, int user_id) {
    	return false;
  	}

  	public Map getEnrolledCourse(int user_id){
        Integer userId = user_id;
        return null;
    }

	public boolean isMeetRequirement(User user, Course course) {return true;
	}

	public boolean enrollCourse(int id, int courseId) {return false;}

	public String selectTimeSlot(int courseId) {
		return null;
  	}
  
  	public String getCourseLocation(int courseId){
		return null;
	}

	public int getCourseCapacityForTimeSlot(int courseId, int timeslotId) {
	return 0;
}

	public List<Integer> getCourseCapacityForCompulsory(int courseId, int timeslotId) {
	return null;
}

	public List<Integer> getCourseCapacityForElective(int courseId, int timeslotId) {
	return null;
}

	public boolean checkTimetableClash(int courseId, int timeslotId) {
		return false;
	}

	public Hashtable<Integer,String> getUsersGpa(int user_id){
    	return null;
	}

	public List<Course> getCompulsoryCourses(int userId){return null;}

	public int getProgramme(int userId) {return 0; }

	public int getSelectedCompulsoryCourseNumber(int userId) {return 0;}

	public int getCompulsoryCoursesRequirementOfProgram(int programmeId) {return 0;}

	public List<Course> getPrerequestCourse(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}
}
