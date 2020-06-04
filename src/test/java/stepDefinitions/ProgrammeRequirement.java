import java.util.*;
import java.util.concurrent.TimeUnit;

import base.BaseUtil;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;


public class ProgrammeRequirement extends BaseUtil {
    private BaseUtil base;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private web.User user;
    private WebDriverWait wait;

    public ProgrammeRequirement(BaseUtil base){
        this.base = base;
    }


    @Given("user logged in as a Software Engineering Student")
    public void user_logged_in_as_a_Software_Engineering() {
        // Write code here that turns the phrase above into concrete actions
        this.wait = new WebDriverWait(this.base.driver, 20);
        this.base.driver.get("http://localhost:8181/programmeRequirement.html");
        WebElement currentUser = base.driver.findElement(By.id("users"));
        Select users = new Select(currentUser);
        // undergrade student
        users.selectByVisibleText("aaa");
        if(this.base.scenario.getName().equals("student want to know the programme requirement of his programme")){
            this.base.setScreenShot("ProgrammeRequirement1.png");
        }else if(this.base.scenario.getName().equals("student want to know if his programme requirement of his programme has been met")) {
            this.base.setScreenShot("ProgrammeRequirement2.png");
        }
    }

    @Given("he is in his {string} year study")
    public void he_is_in_his_year_study(String string) {
        // Write code here that turns the phrase above into concrete action
        this.user = new web.User(123, "Student", "Will");
    }

    @When("he clicks enrol")
    public void he_clicks_enrol() {
        // Write code here that turns the phrase above into concrete actions
        // clicked
    }

    @When("he clicks my programme requirement tab")
    public void he_clicks_my_programme_requirement_tab() {
        // Write code here that turns the phrase above into concrete actions
        // on the page
    }

    @Then("he should see {string}, {string}, {string} as compulsory")
    public void he_should_see(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        List<String> compulsoryList = Stream.of(string, string2, string3).collect(Collectors.toList());
        List<WebElement> text= base.driver.findElements(By.id("compulsoryCourses"));
        List<String> compulsoryCoursesOnPage = new ArrayList<>();
        for (String item: text.get(0).getText().split("\n")) {
            compulsoryCoursesOnPage.add(item);
        }

        if (!compulsoryCoursesOnPage.equals(compulsoryList)) {
            fail();
        }


    }

    @Then("he should see {string}, {string}, {string} as elective")
    public void he_should_see_the_available_elective_courses(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        List<String> electiveList = Stream.of(string, string2, string3).collect(Collectors.toList());
        List<WebElement> text= base.driver.findElements(By.id("electiveCourses"));
        List<String> electiveCoursesOnPage = new ArrayList<>();
        for (String item: text.get(0).getText().split("\n")) {
            electiveCoursesOnPage.add(item);
        }
        if (!electiveCoursesOnPage.equals(electiveList)){
            fail();
        }
    }


    @Given("his is in his 3rd year of study")
    public void his_is_in_his_3rd_year_of_study() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("he is enrolled in {string}, {string}, {string}")
    public void he_is_enrolled_in(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("he should be told that if he has {string} for the 3rd year")
    public void he_should_be_told_that_if_he_has_for_the_3rd_year(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



}
