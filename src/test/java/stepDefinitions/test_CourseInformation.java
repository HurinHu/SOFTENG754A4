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
	private String actualLocation = "";
	private String Softeng754Location = "";
	private String Softeng751Location = "";
	
	private String actualCourseTime = "";
	private String Softeng754Time = "";
	private String Softeng751Time = "";
	
	public static WebDriver driver;
	private static String OS = System.getProperty("os.name").toLowerCase();
	private String today;
    private String actualAnswer;
    private String courseNames;
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
     } else if(this.base.scenario.getName().equals("Students want to cancel selected courses")){
         this.base.setScreenShot("Enrollment3.png");
     } else if(this.base.scenario.getName().equals("Students want to see capacity of courses")){
         this.base.setScreenShot("Enrollment4.png");
     }
     this.base.driver.get("http://localhost:8181/courseInfo.html");
     this.base.driver.manage().window().maximize();

	this.user_type = "student";
    
}

@When("the student clicked the course information button")
public void the_student_clicked_the_course_information_button() {
    // Write code here that turns the phrase above into concrete actions
    //hrow new io.cucumber.java.PendingException();
	List<WebElement> locations= this.base.driver.findElements(By.id("locations"));
    this.actualLocation = locations.get(0).getText();
    
	List<WebElement> courses= this.base.driver.findElements(By.id("courses"));
    this.courseNames = courses.get(0).getText();
	
}

@And("he selected SOFTENG754")
public void he_selected_SOFTENG754() {
    // Write code here that turns the phrase above into concrete actions
  //  throw new io.cucumber.java.PendingException();
	String[] actualResult = this.actualLocation.split("\n");
	//String result = actualResult[actualResult.length-1];
	//this.Softeng754Location = result;
	String[] actualCourse = this.courseNames.split("\n");
	//String result = actualResult[actualResult.length-1];
	//this.Softeng754Location = result;
	
	for( int i = 0; i < actualCourse.length - 1; i++)
	{
	   if(actualCourse[i].trim().equals("SOFTENG754")) {
		   this.Softeng754Location = actualResult[i];
	   }
	}
}



@Then("student should be told {string}")
public void student_should_be_told(String expectedAnswer) {
    // Write code here that turns the phrase above into concrete actions
	
	
	System.out.println(expectedAnswer);
    assertEquals(expectedAnswer, this.Softeng754Location.trim());
}



@When("he selected SOFTENG754 and SOFTENG751")
public void he_selected_SOFTENG754_and_SOFTENG751() {
	String[] actualResult = this.actualLocation.split("\n");
	//String result = actualResult[actualResult.length-1];
	//this.Softeng754Location = result;
	String[] actualCourse = this.courseNames.split("\n");
    // Write code here that turns the phrase above into concrete actions
	for( int i = 0; i < actualCourse.length - 1; i++)
	{
	   if(actualCourse[i].trim().equals("SOFTENG754")) {
		   this.Softeng754Location = actualResult[i];
	   }
	   if(actualCourse[i].trim().equals("SOFTENG751")) {
		   this.Softeng751Location = actualResult[i];
	   }
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
	List<WebElement> locations= this.base.driver.findElements(By.id("courseTimes"));
    this.actualCourseTime = locations.get(0).getText();
    String[] actualResult = this.actualCourseTime.split("\n");
	String[] actualCourse = this.courseNames.split("\n");
    for( int i = 0; i < actualCourse.length - 1; i++)
	{
	   if(actualCourse[i].trim().equals("SOFTENG751")) {
		   this.Softeng751Time = actualResult[i];
	   }
	}
}

@Then("the student should get {string}")
public void the_student_should_get(String string) {
    assertEquals(string, this.Softeng751Time.trim());
}





public boolean isWindows() {
	return (OS.indexOf("win") >= 0);
}

public boolean isMac() {
	return (OS.indexOf("mac") >= 0);
}

public boolean isUnix() {
	return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
}

public void takeSnapShot(WebDriver webdriver,String fileWithPath) {
	try{
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
	} catch(Exception e){
		System.out.println(e.getMessage());
	}
}



}
