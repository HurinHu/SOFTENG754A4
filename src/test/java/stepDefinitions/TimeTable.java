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
    private WebDriverWait wait;
    private List<WebElement> rows;

	@Given("the user enrolled a course {string}")
	public void the_user_enrolled_a_course(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("the course {string} time is clashed with other enrolled courses")
	public void the_course_time_is_clashed_with_other_enrolled_courses(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("{string} notification should be shown")
	public void notification_should_be_shown(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

}