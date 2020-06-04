package web;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin
@RestController("/")
public class Api {
	Database db = new Database();
	
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index.html");
	}	

    @RequestMapping(value="/api/data", method= {RequestMethod.GET})
	public Object data() {
        return "Hello Gradle!";
    }
  
    @RequestMapping(value="/api/userlist", method= {RequestMethod.GET})
	public Object userlist() {
		List<User> users = new ArrayList<>();
		users.add(new User(1, "Teacher", "Prof. xxx"));
		users.add(new User(2, "Undergraduate Student", "aaa"));
		users.add(new User(3, "Master Student", "bbb"));
        return users;
    }
	
	@RequestMapping(value="/api/courselist", method= {RequestMethod.GET})
	public Object courselist() {
		List<Course> courses = db.getCourses();
        return courses;
    }

	
	@RequestMapping(value="/api/course", method= {RequestMethod.POST})
	public Object createcourse(@RequestParam(value="name", required=true) String name, @RequestParam(value="description", required=true) String description, @RequestParam(value="concession", required=true) boolean concession,@RequestParam(value="capacity", required=true) int capacity,@RequestParam(value="compulsory", required=true) String[] compulsory,@RequestParam(value="prerequisite", required=true) String[] prerequisite, @RequestParam(value="timeslots", required=true) String[] timeslot, @RequestParam(value="location", required=true) String location,@RequestParam(value="status", required=true) String status) {

		List<String> compulsory_program = new ArrayList<>();
		List<String> prerequisites = new ArrayList<>();
		List<String> timeslots = new ArrayList<>();
		if(compulsory != null && compulsory.length != 0) {
			for(int i=0; i<compulsory.length; i++) {
					compulsory_program.add(compulsory[i]);
			}
		}
		if(prerequisite != null && prerequisite.length != 0) {
			for(int i=0; i<prerequisite.length; i++) {
					prerequisites.add(prerequisite[i]);
			}
		}
		if(timeslot != null && timeslot.length != 0) {
			for(int i=0; i<timeslot.length; i++) {
					timeslots.add(timeslot[i]);
			}
		}
				db.createCourse(name,description,capacity,concession,compulsory_program,prerequisites,timeslots,location,status);
        return new Response("success");
    }

    @RequestMapping(value="/api/cartlist", method= {RequestMethod.GET})
	  public Object cartlist() {
		    List<Course> carts = db.getCarts();
        return carts;
    }

    @RequestMapping(value="/api/setCarts", method= {RequestMethod.GET})
	  public Object setCarts(@RequestParam(value="id", required=true) int id, @RequestParam(value="status", required=true) String status) {
		    db.setCarts(id, status);
        return new Response("success");
    }

	@RequestMapping(value="/api/setTime", method= {RequestMethod.GET})
	  public Object setTime(@RequestParam(value="id", required=true) int id, @RequestParam(value="time", required=true) String time) {
		    db.setTime(id, time);
        return new Response("success");
    }

    @RequestMapping(value="/api/getProgrammeRequirement", method= {RequestMethod.GET})
    public Object getProgrammeRequirement() {
        return db.getCompulsoryCourses(1);
    }

    @RequestMapping(value="/api/getElectiveCourses", method= {RequestMethod.GET})
    public Object getElectiveCourses() {
        return db.getElectiveCourses(1);
    }

    @RequestMapping(value="/api/metRequirement/{id}", method= {RequestMethod.GET})
	public Object metProgrameRequirement(@PathVariable("id") String id){
		int userId = Integer.parseInt(id);
		return db.metProgrammeRequirement(userId);
	}

	@RequestMapping(value="/api/enrol", method= {RequestMethod.POST})
	public Object enrolAndUpdateSeating(@RequestParam(value="id", required=true) int id, @RequestParam(value="enrolNum", required=true) int enrol_student_num){
		db.enrollInCourse(id,enrol_student_num);
		return "success";
	}
  
}

class Response{
    private String status;
    public Response(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
}