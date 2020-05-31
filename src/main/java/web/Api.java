package web;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public String createcourse(@RequestParam(value="name", required=true) String name, @RequestParam(value="description", required=true) String description, @RequestParam(value="concession", required=true) boolean concession,@RequestParam(value="capacity", required=true) int capacity,@RequestParam(value="compulsory", required=true) String[] compulsory,@RequestParam(value="prerequisite", required=true) String[] prerequisite, @RequestParam(value="timeslots", required=true) String[] timeslot, @RequestParam(value="location", required=true) String location,@RequestParam(value="status", required=true) String status) {

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
        return "{'status':'success'}";
    }

}