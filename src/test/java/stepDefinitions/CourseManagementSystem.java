package stepDefinitions;
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

public class CourseManagementSystem {
    private BaseUtil base;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private web.User user;
    private WebDriverWait wait;


    public CourseManagementSystem(BaseUtil base){
        this.base = base;
    }


    
    @Given("user logged in as Course Coordinator")
    public void user_logged_in_as_Course_Coordinator() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("he clicks ��Add Course�� in the course management page")
    public void he_clicks_Add_Course_in_the_course_management_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("he enters the course details in correct format name:{string},code:{string}")
    public void he_enters_the_course_details_in_correct_format_name_code(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the new course should be displayed in the system")
    public void the_new_course_should_be_displayed_in_the_system() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
