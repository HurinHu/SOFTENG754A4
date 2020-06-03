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

    private BaseUtil base;
    private String course;
    private String user_role;
    private WebDriverWait wait;
    private List<WebElement> rows;

    public ApplyConcession(BaseUtil base) {
        this.base = base;
    }

    @Given("the user logged in as {string} and he can apply concession for a course")
    public void the_user_logged_in_as_and_he_can_apply_concession_for_a_course(String user_role) {
        this.wait = new WebDriverWait(this.base.driver, 20);
        this.user_role = user_role;
        this.base.driver.get("http://localhost:8181/concession.html");
        try {
            this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("option"),1));
        } catch(TimeoutException e){
            throw new NoSuchElementException("cartlist");
        }
    }

    @When("the course {string} is required to concession")
    public void the_course_is_required_to_concession(String course) {
        if(this.base.scenario.getName().equals("Student can apply concession")){
            this.base.setScreenShot("ApplyConcession1.png");
        }else if(this.base.scenario.getName().equals("Enrol the course after concession approved")){
            this.base.setScreenShot("ApplyConcession2.png");
        }else if(this.base.scenario.getName().equals("Concession rejected")){
            this.base.setScreenShot("ApplyConcession3.png");
        }else if(this.base.scenario.getName().equals("Master student apply concession and approved without delay")){
            this.base.setScreenShot("ApplyConcession4.png");
        }
        this.course = course;
        this.rows = this.base.driver.findElement(By.id("cartlist")).findElements(By.tagName("tr"));
        for (WebElement row : this.rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(0).getText().equals(course)){
                this.base.driver.get("http://localhost:8181/api/setCarts?id="+cells.get(0).getAttribute("data-id")+"&status=In%20Cart");
                this.base.driver.get("http://localhost:8181/concession.html");
                try {
                    this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("option"),1));
                } catch(TimeoutException e){
                    throw new NoSuchElementException("cartlist");
                }
                Select user= new Select(this.base.driver.findElement(By.id("users")));
                if(this.user_role.equals("non-Master student")){
                      user.selectByValue("Undergraduate Student");
                }else if(this.user_role.equals("Master student")){
                      user.selectByValue("Master Student");
                }
                break;
            }
        }
        this.rows = this.base.driver.findElement(By.id("cartlist")).findElements(By.tagName("tr"));
        for (WebElement row : this.rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(0).getText().equals(course)){
                assertEquals("In Cart", cells.get(1).getText());
                assertEquals("Apply", cells.get(2).getText());
                break;
            }
        }
    }

    @When("the course {string} concession is approved")
    public void the_course_concession_is_approved(String course) {
          this.course = course;
          this.rows = this.base.driver.findElement(By.id("cartlist")).findElements(By.tagName("tr"));
          for (WebElement row : this.rows){
              List<WebElement> cells = row.findElements(By.tagName("td"));
              if (cells.get(0).getText().equals(course)){
                  this.base.driver.get("http://localhost:8181/api/setCarts?id="+cells.get(0).getAttribute("data-id")+"&status=Enrolled");
                  this.base.driver.get("http://localhost:8181/concession.html");
                  try {
                      this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("option"),1));
                  } catch(TimeoutException e){
                      throw new NoSuchElementException("cartlist");
                  }
                  Select user= new Select(this.base.driver.findElement(By.id("users")));
                  if(this.user_role.equals("non-Master student")){
                        user.selectByValue("Undergraduate Student");
                  }else if(this.user_role.equals("Master student")){
                        user.selectByValue("Master Student");
                  }
                  break;
              }
          }
    }

    @When("the course {string} concession is rejected")
    public void the_course_concession_is_rejected(String course) {
        this.course = course;
          this.rows = this.base.driver.findElement(By.id("cartlist")).findElements(By.tagName("tr"));
          for (WebElement row : this.rows){
              List<WebElement> cells = row.findElements(By.tagName("td"));
              if (cells.get(0).getText().equals(course)){
                  this.base.driver.get("http://localhost:8181/api/setCarts?id="+cells.get(0).getAttribute("data-id")+"&status=Concession%20Rejected");
                  this.base.driver.get("http://localhost:8181/concession.html");
                  try {
                      this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("option"),1));
                  } catch(TimeoutException e){
                      throw new NoSuchElementException("cartlist");
                  }
                  Select user= new Select(this.base.driver.findElement(By.id("users")));
                  if(this.user_role.equals("non-Master student")){
                        user.selectByValue("Undergraduate Student");
                  }else if(this.user_role.equals("Master student")){
                        user.selectByValue("Master Student");
                  }
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
            this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("tr"),10));
        } catch(TimeoutException e){
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