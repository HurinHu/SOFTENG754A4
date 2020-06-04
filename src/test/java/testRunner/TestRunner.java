import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/",
				glue={"stepDefinitions"},
//				tags={"@CompleteTesting"},
				strict=false,
				tags={"@CourseManagement"},
//				plugin={"pretty"},
//				plugin={"pretty"},
//				plugin={"pretty", "html:target/cucumber-reports"},
//				plugin={"pretty", "json:target/cucumber-reports/Cucumber.json"},
//				plugin={"pretty", "junit:target/cucumber-reports/cucumber.xml"},
//				plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//				plugin={"de.monochromata.cucumber.report.PrettyReports:target/cucumber"},
				monochrome=true)
public class TestRunner {

}
