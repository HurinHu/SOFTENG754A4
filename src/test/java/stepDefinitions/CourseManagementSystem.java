package stepDefinitions;
import base.BaseUtil;

import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    private String courseCode;


    public CourseManagementSystem(BaseUtil base){
        this.base = base;
        this.wait = new WebDriverWait(this.base.driver, 20);
        this.base.driver.get("http://localhost:8181/courseManage.html");
        WebElement currentUser = base.driver.findElement(By.id("users"));
    }


    
    @Given("user logged in as Course Coordinator")
    public void user_logged_in_as_Course_Coordinator() {
        // Write code here that turns the phrase above into concrete actions
        this.wait = new WebDriverWait(this.base.driver, 20);
        this.base.driver.get("http://localhost:8181/courseManage.html");
        WebElement currentUser = base.driver.findElement(By.id("users"));
        Select users = new Select(currentUser);
        // undergrade student
        users.selectByVisibleText("Prof. xxx");
        if(this.base.scenario.getName().equals("Course Coordinator post new course to the system")){
            this.base.setScreenShot("CourseManagement1.png");
        }else if(this.base.scenario.getName().equals("student want to know if his programme requirement of his programme has been met")) {
            this.base.setScreenShot("ProgrammeRequirement2.png");
        }
    }

    @When("enters the course details in correct format name:{string},code:{string}")
    public void enters_the_course_details_in_correct_format_name_code(String string, String string2) {
        WebElement code = this.base.driver.findElement(By.id("courseCode"));
        WebElement name = this.base.driver.findElement(By.id("courseName"));
        this.courseCode = string2;
        code.sendKeys(string2);
        name.sendKeys(string);
    }

    @When("he clicks {string} in the course management page")
    public void he_clicks_in_the_course_management_page(String string) {
        WebElement button = this.base.driver.findElement(By.id("addCourse"));
        button.click();
    }


    @Then("the new course should be displayed in the system")
    public void the_new_course_should_be_displayed_in_the_system() {
        WebElement courseList = this.base.driver.findElement(By.id("courseList"));
        List<WebElement> list = courseList.findElements(By.xpath(".//h6[@class='my-0']"));
        String courseCode = list.get(list.size()-1).getText();
        if(!this.courseCode.equals(courseCode)){
            fail();
        }
    }

    @Then("an alert message would pop up {string}")
    public void an_alert_message_would_pop_up(String string) {
        try
        {
            this.base.driver.switchTo().alert();
        }   // try
        catch (NoAlertPresentException Ex)
        {
            fail();
        }
    }

    @Given("SOFTENG754 has {int} student enrolled")
    public void softeng754_has_student_enrolled(Integer int1) {

    }

    @When("{int} student enrolled in the course negative reprsents swap out")
    public void student_enrolled_in_the_course_negative_reprsents_swap_out(Integer int1) {
        String id = "";
        switch(int1){
            case 1:
                id = "1";
                break;
            case 2:
                id = "2";
                break;
            case 48:
                id = "3";
                break;
            case -1:
                id = "4";
                break;
            case -49:
                id = "5";
                break;
        }
        WebElement button = this.base.driver.findElement(By.id(id));
        button.click();
    }

    @Then("the remained seating should be {int}")
    public void the_remained_seating_should_be(Integer int1) {
        String str = this.base.driver.findElement(By.xpath(".//p[@class='badge']")).getText();

        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^\\d]", " ");

        // Remove extra spaces from the beginning
        // and the ending of the string
        str = str.trim();

        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" +", " ");

        Integer integer = Integer.parseInt(str);
        if(!integer.equals(int1)){
            fail();
        }

    }

}
