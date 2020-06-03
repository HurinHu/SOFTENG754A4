import base.BaseUtil;

import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;
import static org.junit.Assert.*;

public class TimeTable extends BaseUtil {

	private BaseUtil base;
    private String course;
	private String timeclash;
    private WebDriverWait wait;
    private List<WebElement> rows;

	public TimeTable(BaseUtil base) {
        this.base = base;
    }

	@Given("the user enrolled a course {string}")
	public void the_user_enrolled_a_course(String course) {
		this.wait = new WebDriverWait(this.base.driver, 20);
        this.course = course;
        this.base.driver.get("http://localhost:8181/timetable.html");
        try {
            this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("option"),1));
        } catch(TimeoutException e){
            throw new NoSuchElementException("users");
        }
		this.rows = this.base.driver.findElement(By.id("courselist")).findElements(By.className("card"));
		String id;
        for (WebElement row : this.rows){
            List<WebElement> cells = row.findElement(By.className("card-header")).findElements(By.tagName("div"));
			id = row.getAttribute("data-id");
            if (cells.get(0).getText().equals(this.course)){
                this.base.driver.get("http://localhost:8181/api/setCarts?id="+id+"&status=Enrolled");
				this.base.driver.get("http://localhost:8181/api/setTime?id="+id+"&time=Thu%2009:00-10:00");
                this.base.driver.get("http://localhost:8181/timetable.html");
                try {
                    this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("card"),10));
                } catch(TimeoutException e){
                    throw new NoSuchElementException("courselist");
                }
                Select user= new Select(this.base.driver.findElement(By.id("users")));
                user.selectByValue("Undergraduate Student");
                break;
            }
		}
	}

	@Given("the user logged in and view a course {string}")
	public void the_user_logged_in_and_view_a_course(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("the course {string} time is clashed with other enrolled courses")
	public void the_course_time_is_clashed_with_other_enrolled_courses(String course) {
		 if(this.base.scenario.getName().equals("Student should know the time clash")){
            this.base.setScreenShot("Timetable1.png");
		 }
		this.timeclash = "";
		this.rows = this.base.driver.findElement(By.id("courselist")).findElements(By.className("card"));
        for (WebElement row : this.rows){
            List<WebElement> cells = row.findElement(By.className("card-header")).findElements(By.tagName("div"));
            if (cells.get(0).getText().equals(this.course)){
				this.timeclash = cells.get(2).getText();
                break;
            }
		}
	}

	@When("user select a course {string}")
	public void user_select_a_course(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("{string} notification should be shown")
	public void notification_should_be_shown(String error) {
		assertEquals(error, this.timeclash);
	}

	@Then("time slots {string} and {string} should be shown")
	public void time_slots_and_should_be_shown(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

}