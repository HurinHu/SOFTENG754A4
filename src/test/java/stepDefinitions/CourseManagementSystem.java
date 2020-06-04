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
    private int counter=1;
    private String _id;


    public CourseManagementSystem(BaseUtil base){
        this.base = base;
    }


    
    @Given("user logged in as Course Coordinator")
    public void user_logged_in_as_Course_Coordinator() {
        if(this.base.scenario.getName().equals("Course Coordinator post new course to the system")){
            this.base.setScreenShot("CourseManagement1.png");
        }else if(this.base.scenario.getName().equals("Course Coordinator post new course to the system with incorrect format information")) {
            this.base.setScreenShot("CourseManagement2.png");
        }
        // Write code here that turns the phrase above into concrete actions
        this.wait = new WebDriverWait(this.base.driver, 20);
        this.base.driver.get("http://localhost:8181/courseManage.html");
        try {
            this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("option"),1));
        } catch(TimeoutException e){
            throw new NoSuchElementException("user");
        }
        Select user= new Select(this.base.driver.findElement(By.id("users")));
        user.selectByValue("Teacher");
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

    @Given("SOFTENG759 has {int} student enrolled")
    public void softeng759_has_student_enrolled(Integer int1) {

        this.base.driver.get("http://localhost:8181/courseManage.html");
        this.base.setScreenShot("CourseManagementUpdateSeat"+this.counter+".png");
        this.wait = new WebDriverWait(this.base.driver, 20);
        counter++;
    }

    @When("{int} student enrolled in the course negative reprsents swap out")
    public void student_enrolled_in_the_course_negative_reprsents_swap_out(Integer int1) {

        switch(int1){
            case 1:
                _id = "button1";
                break;
            case 2:
                _id = "button2";
                break;
            case 48:
                _id = "button3";
                break;
            case -1:
                _id = "button4";
                break;
            case -49:
                _id = "button5";
                break;
        }
        try {
            this.wait.until(ExpectedConditions.numberOfElementsToBe(By.id(_id),1));
        } catch(TimeoutException e){
            throw new NoSuchElementException(_id);
        }
        WebElement button = this.base.driver.findElement(By.id(_id));
        button.click();
    }

    @Then("the remained seating should be {int}")
    public void the_remained_seating_should_be(Integer int1) {
//        int index = 0;
//        switch(_id){
//            case "button1":
//                index = 1;
//                break;
//            case "button2":
//                index = 2;
//                break;
//            case "button3":
//                index = 3;
//                break;
//            case "button4":
//                index = 4;
//                break;
//            case "button5":
//                index = 5;
//                break;
//        }
        String str = this.base.driver.findElements(By.xpath(".//p[@class='badge']")).get(0).getText();

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
        System.out.println("actual seat was: "+integer +"should be: "+int1);
        if(!integer.equals(int1)){
            fail();
        }

    }

}
