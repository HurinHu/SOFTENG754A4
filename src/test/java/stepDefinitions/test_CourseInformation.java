import java.util.*;
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

import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class test_CourseInformation {
	private String user_type;
	private String actualLocation = "";
	private String Softeng754Location = "";
	public static WebDriver driver;
	private static String OS = System.getProperty("os.name").toLowerCase();
	private String today;
    private String actualAnswer;
    private String courseNames;
	@Before
	public void startBrowser(){
		String browser="Chrome";
		ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");
		if (isWindows()) {System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
			driver=new ChromeDriver(options);
		} else if (isMac()) {
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver(mac)");
			driver=new ChromeDriver(options);
		} else if (isUnix()) {
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver(linux)");
			driver=new ChromeDriver(options);
		}
		
		driver.get("http://localhost:8181/courseInfo.html");
		
		driver.manage().window().maximize();
	}
@Given("The student has logged in to the system")
public void the_student_has_logged_in_to_the_system() {
    // Write code here that turns the phrase above into concrete actions
	this.user_type = "student";
    
}

@When("the student clicked the course information button")
public void the_student_clicked_the_course_information_button() {
    // Write code here that turns the phrase above into concrete actions
    //hrow new io.cucumber.java.PendingException();
	List<WebElement> locations= driver.findElements(By.id("locations"));
    this.actualLocation = locations.get(0).getText();
    
	List<WebElement> courses= driver.findElements(By.id("courses"));
    this.courseNames = courses.get(0).getText();
	
}

@When("he selected SOFTENG754")
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

@After
public void closeBrowser(){
	this.takeSnapShot(driver, "target/screenshot/Test_example.png") ;
	driver.quit();
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
