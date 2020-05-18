import java.util.*;

public class Course {
	private Database db;
	private int id;
	private List<TimeSlot> _timeslots;
    public Course(){

    }

	public Course(Database db) {
		this.db = db;
	}
	
	public Course(int id, List<TimeSlot> ts) {
		this.id = id;
		this._timeslots = ts;
	}

	public Course(Database db, int course_id) {
		this.db = db;
    	this.id = course_id;
    }

    public Boolean isValidForConcession() {
        return true;
    }

	public int getCourseId() {
    	return this.id;
	}

	public String getCourseDescription() {
		if (this.id != -1) {
			return this.db.getDescription(this.id);
		} else {
			throw new RuntimeException("Course is not exist");
		}
	}

	public boolean create(String description,int capacity,boolean concession,List<String> compulsory_program,List<String> prerequisite,List<String> timeslots,String location,String status) {
		if (description == null || description.equals("")) {
			throw new RuntimeException("Course description is empty");
		} else if (capacity <= 0) {
			throw new RuntimeException("Course capacity is invalid");
		} else if (timeslots == null || timeslots.size() <= 0) {
			throw new RuntimeException("Course timeslots is empty");
		} else if (location == null || location.equals("")) {
			throw new RuntimeException("Course location is empty");
		} else if (status == null || status.equals("")) {
			throw new RuntimeException("Course status is empty");
		} else {
			return this.db.createCourse(description,capacity,concession,compulsory_program,prerequisite,timeslots,location,status);
		}
	}
	
	

	public List<TimeSlot> getTimeSlots(int courseId){
		if(courseId == this.id) {
			return this._timeslots; 
		}
		throw new RuntimeException("Course status is empty");
		
	}

	public String selectTimeSlot(int courseId) {
		// TODO Auto-generated method stub
		return this.db.selectTimeSlot(this.id);
	}
	
}
