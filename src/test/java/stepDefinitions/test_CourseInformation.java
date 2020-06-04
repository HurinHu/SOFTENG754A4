import java.util.*;
import base.BaseUtil;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class test_CourseInformation  extends BaseUtil {
	private String user_type;
	private String actualLocation1 = "";
	private String actualLocation2 = "";
	private String Softeng754Location = "";
	private String Softeng751Location = "";
	
	private String actualCourseTime1 = "";
	private String actualCourseTime2 = "";
	private String Softeng754Time = "";
	private String Softeng751Time = "";
	
	public static WebDriver driver;
	private static String OS = System.getProperty("os.name").toLowerCase();
	private String today;
    private String actualAnswer;
    private String courseName1;
    private String courseName2;
    private BaseUtil base;
    private String course;
    private String user_role;
    private WebDriverWait wait;
    private List<WebElement> rows;
    
    public test_CourseInformation(BaseUtil base) {
        this.base = base;
       // this.wait = new WebDriverWait(this.base.driver, 20);
    }
    
@Given("The student has logged in to the system")
public void the_student_has_logged_in_to_the_system() {
	 this.wait = new WebDriverWait(this.base.driver, 20);
	 this.base.driver.get("http://localhost:8181/courseInfo.html");
    // Write code here that turns the phrase above into concrete actions
	 if(this.base.scenario.getName().equals("Student wants to know a single course’s location")){
         this.base.setScreenShot("CourseInfo1.png");
     } else if(this.base.scenario.getName().equals("Student wants to know multiple courses’ locations")){
         this.base.setScreenShot("CourseInfo2.png");
     } else if(this.base.scenario.getName().equals("Students want to know single course time")){
         this.base.setScreenShot("CourseInfo3.png");
     } else if(this.base.scenario.getName().equals("Students want to know multiple course time")){
         this.base.setScreenShot("CourseInfo4.png");
     }
     this.base.driver.get("http://localhost:8181/courseInfo.html");
     this.base.driver.manage().window().maximize();

	this.user_type = "student";
    
}

@When("the student clicked the course information button")
public void the_student_clicked_the_course_information_button() {
    // Write code here that turns the phrase above into concrete actions
    //hrow new io.cucumber.java.PendingException();

    
	List<WebElement> courses1= this.base.driver.findElements(By.id("course1"));
    this.courseName1 = courses1.get(0).getText();
    
    
	List<WebElement> courses2= this.base.driver.findElements(By.id("course2"));
    this.courseName2 = courses2.get(0).getText();
    
	
}

@When("he selected SOFTENG754")
public void he_selected_SOFTENG754() {
    // Write code here that turns the phrase above into concrete actions
  //  throw new io.cucumber.java.PendingException();
	//String result = actualResult[actualResult.length-1];
	if(this.courseName1.trim().equals("SOFTENG754")) {
		this.base.driver.findElement(By.id("btn_location1")).click();
		List<WebElement> locations1= this.base.driver.findElements(By.id("location1"));
	    this.actualLocation1 = locations1.get(0).getText();
	    
		this.Softeng754Location = this.actualLocation1;
	}
	else if(this.courseName2.trim().equals("SOFTENG754")) {
		this.base.driver.findElement(By.id("btn_location2")).click();
		List<WebElement> locations2= this.base.driver.findElements(By.id("location2"));
	    this.actualLocation2 = locations2.get(0).getText();
		this.Softeng754Location = this.actualLocation2;
	}
	
	
}



@Then("student should be told {string}")
public void student_should_be_told(String expectedAnswer) {
    // Write code here that turns the phrase above into concrete actions
	
	    assertEquals(expectedAnswer, this.Softeng754Location.trim());
}



@When("he selected SOFTENG754 and SOFTENG751")
public void he_selected_SOFTENG754_and_SOFTENG751() {
    // Write code here that turns the phrase above into concrete actions

	if(this.courseName1.trim().equals("SOFTENG754")) {
		this.base.driver.findElement(By.id("btn_location1")).click();
		List<WebElement> locations1= this.base.driver.findElements(By.id("location1"));
	    this.actualLocation1 = locations1.get(0).getText();
		this.Softeng754Location = this.actualLocation1;
	}
	else if(this.courseName2.trim().equals("SOFTENG754")) {
		this.base.driver.findElement(By.id("btn_location2")).click();
		List<WebElement> locations2= this.base.driver.findElements(By.id("location2"));
	    this.actualLocation2 = locations2.get(0).getText();
		this.Softeng754Location = this.actualLocation2;
	}
	if(this.courseName1.trim().equals("SOFTENG751")) {
		this.base.driver.findElement(By.id("btn_location1")).click();
		List<WebElement> locations1= this.base.driver.findElements(By.id("location1"));
	    this.actualLocation1 = locations1.get(0).getText();
		this.Softeng751Location = this.actualLocation1;
	}
	else if(this.courseName2.equals( "SOFTENG751")) {
		this.base.driver.findElement(By.id("btn_location2")).click();
		List<WebElement> locations2= this.base.driver.findElements(By.id("location2"));
	    this.actualLocation2 = locations2.get(0).getText();
		this.Softeng751Location = this.actualLocation2;
	}
	
}


@Then("the student should be able to see {string}")
public void the_student_should_be_able_to_see(String string) {
    // Write code here that turns the phrase above into concrete actions
	String output = this.Softeng754Location.trim() + ","+  this.Softeng751Location.trim();
	  assertEquals(string,output);
}

@When("he selected SOFTENG751")
public void he_selected_SOFTENG751() {
    // Write code here that turns the phrase above into concrete actions

    

    
	if(this.courseName1.trim().equals("SOFTENG751")) {
		this.base.driver.findElement(By.id("btn_time1")).click();
		List<WebElement> time1= this.base.driver.findElements(By.id("courseTime1"));
	    this.actualCourseTime1 = time1.get(0).getText();
		this.Softeng751Time = this.actualCourseTime1;
	}
	else if(this.courseName2.trim().equals("SOFTENG751")) {
		this.base.driver.findElement(By.id("btn_time2")).click();
		List<WebElement> time2= this.base.driver.findElements(By.id("courseTime2"));
	    this.actualCourseTime2 = time2.get(0).getText();
		this.Softeng751Time = this.actualCourseTime2;
	}
}

@Then("the student should get {string}")
public void the_student_should_get(String string) {
    assertEquals(string, this.Softeng751Time.trim());
}



}
