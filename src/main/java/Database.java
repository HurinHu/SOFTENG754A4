import java.util.*;

public class Database {
    List<Concession> _concessionsPool = new ArrayList<>();
    Map<Integer,Map> _enrolledCourses = new Hashtable<>();
	List<Course> courseList = new ArrayList<>();
	//Course course1 = new Course(1, "SOFTENG754", 100, true,{"a","b"} ,{"a","b"}, {"a","b"}, "303G01", "open");
	//this.courseList.add(this.course1);
	

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
		String desc = "";
		for(Course course: courseList){
			if(course.getCourseId() == course_id) {
				desc = course.getCourseDescription();
			}
		}
		return desc;
	}

	public boolean createCourse(String description,int capacity,boolean concession,List<String> compulsory_program,List<String> prerequisite,List<String> timeslots,String location,String status) {
		Course course;
		if(courseList.size()>0){
			int id = courseList.get(courseList.size() - 1).getCourseId();
			course = new Course(id+1, description, capacity, concession, compulsory_program,prerequisite, timeslots, location, status);
		}else{
			course = new Course(1, description, capacity, concession, compulsory_program,prerequisite, timeslots, location, status);
		}
		courseList.add(course);
		return true;
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
		return null;
	}

	public List<Course> getConflictCourse(int courseId) {
		return null;
	}

	public boolean checkEligibility(Course course, User user) {
		return false;
	}

	public List<Course> getCourses() {
		return courseList;
	}
}
