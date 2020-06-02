import base.BaseUtil;

import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import java.io.File;
import org.openqa.selenium.By;
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

public class Test_example extends BaseUtil {

	private String today;
    private String actualAnswer;
    private  BaseUtil base;

    public Test_example(BaseUtil base) {
        this.base = base;
        this.base.setScreenShot("Test_example.png");
    }

	@Given("today is Sunday")
    public void today_is_Sunday() {
        today = "Sunday";
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
        this.base.driver.get("http://localhost:8181/test.html");
        this.base.driver.manage().window().maximize();
		List<WebElement> text= this.base.driver.findElements(By.tagName("h1"));
        actualAnswer = text.get(0).getText();
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
}
