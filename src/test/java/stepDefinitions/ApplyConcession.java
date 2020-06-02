import base.BaseUtil;

import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;

import static org.junit.Assert.*;

public class ApplyConcession extends BaseUtil {

    private  BaseUtil base;
	private String user_role;
    private String course;
    private WebDriverWait wait;
    private List<WebElement> rows;
    private Scenario scenario;

    public ApplyConcession(BaseUtil base) {
        this.base = base;
        this.wait = new WebDriverWait(this.base.driver, 20);
    }

    @Before
	public void reset(Scenario scenario){
        this.scenario = scenario;
        if(scenario.getName().equals("Student can apply concession")){
            this.base.driver.get("http://localhost:8181/api/setCarts?id=6&status=In%20Cart");
        }else if(scenario.getName().equals("Enrol the course after concession approved")){
            this.base.driver.get("http://localhost:8181/api/setCarts?id=6&status=Enrolled");
        }
	}

    @Given("the user logged in as non-Master student and he can apply concession for a course")
    public void the_user_logged_in_as_non_Master_student_and_he_can_apply_concession_for_a_course() {
        this.base.driver.get("http://localhost:8181/concession.html");
		try {
	        this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("option"),1));
	    }
	    catch(TimeoutException e){
	        throw new NoSuchElementException("cartlist");
	    }
        this.base.driver.manage().window().maximize();
        Select user= new Select(this.base.driver.findElement(By.id("users")));
        user.selectByValue("Undergraduate Student");
    }

    @When("the course {string} is required to concession")
    public void the_course_is_required_to_concession(String course) {
        this.base.setScreenShot("ApplyConcession1.png");
        this.course = course;
        try {
	        this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("tr"),10));
	    }
	    catch(TimeoutException e){
	        throw new NoSuchElementException("cartlist");
	    }
        this.rows = this.base.driver.findElement(By.id("cartlist")).findElements(By.tagName("tr"));
        for (WebElement row : this.rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(0).getText().equals(this.course)){
                assertEquals("In Cart", cells.get(1).getText());
                assertEquals("Apply", cells.get(2).getText());
                break;
            }
        }
    }

    @When("the course {string} concession is approved")
    public void the_course_concession_is_approved(String course) {
        this.base.setScreenShot("ApplyConcession2.png");
        try {
	        this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("tr"),10));
	    }
	    catch(TimeoutException e){
	        throw new NoSuchElementException("cartlist");
	    }
        this.rows = this.base.driver.findElement(By.id("cartlist")).findElements(By.tagName("tr"));
        for (WebElement row : this.rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(0).getText().equals(this.course)){
                String id = cells.get(2).findElement(By.tagName("button")).getAttribute("data-id");
                this.base.driver.get("http://localhost:8181/concession.html");
                break;
            }
        }
    }

    @Then("click {string} button")
    public void click_button(String apply) {
        for (WebElement row : this.rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(0).getText().equals(this.course)){
                cells.get(2).findElement(By.xpath(".//button[text()='"+apply+"']")).click();
                break;
            }
        }
    }

    @Then("the status shows {string}")
    public void the_status_shows(String status) {
        try {
	        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("tr"),10));
	    }
	    catch(TimeoutException e){
	        throw new NoSuchElementException("cartlist");
	    }
        this.rows = this.base.driver.findElement(By.id("cartlist")).findElements(By.tagName("tr"));
        for (WebElement row : this.rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(0).getText().equals(this.course)){
                assertEquals(status, cells.get(1).getText());
                break;
            }
        }
    }

}