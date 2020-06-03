package web;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Database {
    List<Concession> _concessionsPool = new ArrayList<>();
    Map<Integer,Map> _enrolledCourses = new Hashtable<>();
	List<Course> courseList = new ArrayList<>();
    List<Course> cartList = new ArrayList<>();

    public Database(){
		List<String> timeslots = new ArrayList<>();
		timeslots.add("Wed 10:00-12:00");
		timeslots.add("Thu 13:00-15:00");
        cartList.add(new Course(1, "SOFTENG 701", "Some description", 50, true, "404-404", "In Cart", timeslots,""));
		timeslots = new ArrayList<>();
		timeslots.add("Mon 09:00-11:00");
		timeslots.add("Thu 13:00-15:00");
        cartList.add(new Course(2, "SOFTENG 702", "Some description", 40, false, "403-404", "In Cart", timeslots,""));
		timeslots = new ArrayList<>();
		timeslots.add("Tue 14:00-16:00");
		timeslots.add("Fri 11:00-12:00");
        cartList.add(new Course(3, "SOFTENG 703", "Some description", 80, true, "405-401", "Enrolled", timeslots,"Tue 14:00-16:00"));
		timeslots = new ArrayList<>();
		timeslots.add("Mon 14:00-15:00");
		timeslots.add("Thu 14:00-16:00");
        cartList.add(new Course(4, "SOFTENG 704", "Some description", 100, false, "402-403", "In Cart", timeslots,""));
		timeslots = new ArrayList<>();
		timeslots.add("Wed 08:00-10:00");
		timeslots.add("Thu 11:00-13:00");
        cartList.add(new Course(5, "SOFTENG 705", "Some description", 50, false, "404-405", "In Cart", timeslots,""));
		timeslots = new ArrayList<>();
		timeslots.add("Mon 15:00-18:00");
		timeslots.add("Thu 09:00-10:00");
        cartList.add(new Course(6, "SOFTENG 750", "Some description", 80, true, "402-403", "In Cart", timeslots,""));
		timeslots = new ArrayList<>();
		timeslots.add("Wed 12:00-13:00");
		timeslots.add("Fri 15:00-16:00");
        cartList.add(new Course(7, "SOFTENG 751", "Some description", 50, true, "406-402", "Concession Applied", timeslots,"Fri 15:00-16:00"));
		timeslots = new ArrayList<>();
		timeslots.add("Tue 09:00-11:00");
		timeslots.add("Thu 14:00-15:00");
        cartList.add(new Course(8, "SOFTENG 752", "Some description", 50, true, "404-403", "In Cart", timeslots,""));
		timeslots = new ArrayList<>();
		timeslots.add("Wed 11:00-13:00");
		timeslots.add("Thu 08:00-10:00");
        cartList.add(new Course(9, "SOFTENG 753", "Some description", 50, true, "404-403", "Enrolled", timeslots,"Thu 08:00-10:00"));
		timeslots = new ArrayList<>();
		timeslots.add("Mon 10:00-12:00");
		timeslots.add("Wed 13:00-14:00");
        cartList.add(new Course(10, "SOFTENG 754", "Some description", 50, true, "404-403", "In Cart", timeslots,""));
		timeslots = new ArrayList<>();
		timeslots.add("Mon 09:00-11:00");
		timeslots.add("Thu 13:00-15:00");
        cartList.add(new Course(11, "SOFTENG 756", "Some description", 50, true, "404-403", "Enrolled", timeslots,"Thu 13:00-15:00"));
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

	public boolean createCourse(String name, String description,int capacity,boolean concession,List<String> compulsory_program,List<String> prerequisite,List<String> timeslots,String location,String status) {
		Course course;
		if(courseList.size()>0){
			int id = courseList.get(courseList.size() - 1).getCourseId();
			course = new Course(id+1, name, description, capacity, concession, compulsory_program,prerequisite, timeslots, location, status);
		}else{
			course = new Course(1, name, description, capacity, concession, compulsory_program,prerequisite, timeslots, location, status);
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

	public List<Course> getCompulsoryCourses(int userId){
    	List<Course> compulsoryCourses = new ArrayList<>();
		Course course = new Course(1,"SOFTENG754");
		Course course1 = new Course(2,"SOFTENG751");
		Course course2 = new Course(3,"SOFTENG701");
		compulsoryCourses.add(course);
		compulsoryCourses.add(course1);
		compulsoryCourses.add(course2);
    	return compulsoryCourses;
    }

    public List<Course> getElectiveCourses(int userId){
		List<Course> electiveCourses = new ArrayList<>();
		List<String> electiveList = Stream.of("COMPSCI754", "COMPSCI751", "COMPSCI761").collect(Collectors.toList());
		electiveList.forEach(item->{
			electiveCourses.add(new Course(9,item));
		});
		return electiveCourses;
	}
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

    public List<Course> getCarts() {
        return cartList;
    }

    public void setCarts(int id, String status) {
        for(Course course : cartList) {
            if (course.getCourseId() == id) {
                course.setStatus(status);
            }
        }
    }
	
	public void setTime(int id, String time) {
        for(Course course : cartList) {
            if (course.getCourseId() == id) {
                course.setTime(time);
            }
        }
    }
}
