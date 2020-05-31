package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RestController("/")
public class Api {
	
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index.html");
	}	

    @RequestMapping(value="/api/data", method= {RequestMethod.GET})
	public Object data() {
        return "Hello Gradle!";
    }

}