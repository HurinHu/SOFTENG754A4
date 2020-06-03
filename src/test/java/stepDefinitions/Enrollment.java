package stepDefinitions;

import base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Enrollment extends BaseUtil {

    private BaseUtil base;
    private WebDriverWait wait;
    private String course;
    private List<WebElement> rows;

    public Enrollment(BaseUtil base) {
        this.base = base;
        this.base.setScreenShot("EnrollmentFeature.png");
        this.wait = new WebDriverWait(this.base.driver, 20);
    }

    @Given("the user logged in as a student and in enrollment page")
    public void the_user_logged_in_as_a_student_and_in_enrollment_page() {
        if(this.base.scenario.getName().equals("Students want to add courses to enrollment cart")){
            this.base.setScreenShot("Enrollment1.png");
        }
        this.base.driver.get("http://localhost:8181/enrollment.html");
        this.base.driver.manage().window().maximize();
        Select user= new Select(this.base.driver.findElement(By.id("users")));
        user.selectByValue("Undergraduate Student");
    }

    @When("the student clicked {string} button")
    public void click_tab_button(String tabButton) {
        this.base.driver.findElement(By.xpath(".//button[text()='"+tabButton+"']")).click();
    }

    @And("the course {string} needs to be added to cart")
    public void course_add_to_cart(String course) {
        this.course = course;
    }

    @And("the student click {string} button")
    public void theStudentClickAddToCartButton(String button) {
        this.rows = this.base.driver.findElement(By.id("tbody")).findElements(By.tagName("tr"));
        for (WebElement row : this.rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(1).getText().equals(course)){
                assertEquals(cells.get(cells.size()-1).getText(), button);
                cells.get(cells.size()-1).click();
                break;
            }
        }
    }

    @Then("the selected course will be added to {string}")
    public void the_selected_course_will_be_added_to(String tabButton) {
        this.base.driver.findElement(By.xpath(".//button[text()='"+tabButton+"']")).click();
    }

    @And("the course status shows {string}")
    public void the_course_status_shows(String status) {
        this.rows = this.base.driver.findElement(By.id("cartBody")).findElements(By.tagName("tr"));
        for (WebElement row : this.rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(1).getText().equals(course)){
                assertEquals(status,cells.get(cells.size()-2).getText());
                break;
            }
        }
    }
}