import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

class IsItFriday {
    static String isItFriday(String today) {
        return "Nope";
    }
}


@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/cucumber",
				glue={"stepDefinitions"},
				tags={"@CompleteTesting"},
//				plugin={"pretty"},
//				plugin={"pretty", "html:target/cucumber-reports"},
				plugin={"pretty", "json:target/cucumber-reports/Cucumber.json"},
//				plugin={"pretty", "junit:target/cucumber-reports/cucumber.xml"},
//				plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//				plugin={"de.monochromata.cucumber.report.PrettyReports:target/cucumber"},
				monochrome=true)
public class Stepdefs {
    private String today;
    private String actualAnswer;

    @Given("today is Sunday")
    public void today_is_Sunday() {
        today = "Sunday";
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
        actualAnswer = IsItFriday.isItFriday(today);
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
}
