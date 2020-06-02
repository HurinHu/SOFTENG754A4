import java.util.*;
import java.util.concurrent.TimeUnit;
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

import static org.junit.Assert.*;


public class ProgrammeRequirement {

    public static WebDriver driver;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private web.User user;

    @Before
    public void startBrowser(){
        String browser="Chrome";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");
        if (isWindows()) {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
            driver=new ChromeDriver(options);
        } else if (isMac()) {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver(mac)");
            driver=new ChromeDriver(options);
        } else if (isUnix()) {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver(linux)");
            driver=new ChromeDriver(options);
        }
        driver.get("http://localhost:8181/programmeRequirement.html");
        driver.manage().window().maximize();
    }

    @Given("user logged in as a Software Engineering Student")
    public void user_logged_in_as_a_Software_Engineering() {
        // Write code here that turns the phrase above into concrete actions
        WebElement currentUser = driver.findElement(By.id("users"));
        Select users = new Select(currentUser);
        // undergrade student
        users.selectByVisibleText("aaa");
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

    @Then("he should see all the compulsory courses for software engineering degree")
    public void he_should_see_all_the_compulsory_courses_for_software_engineering_degree() {
        // Write code here that turns the phrase above into concrete actions
        List<String> compulsoryList = Stream.of("SOFTENG754", "SOFTENG751", "SOFTENG701").collect(Collectors.toList());
        List<WebElement> text= driver.findElements(By.id("compulsoryCourses"));
        List<String> compulsoryCoursesOnPage = new ArrayList<>();
        for (String item: text.get(0).getText().split("\n")) {
            compulsoryCoursesOnPage.add(item);
        }

        if (!compulsoryCoursesOnPage.equals(compulsoryList)) {
            fail();
        }


    }

    @Then("he should see the available elective courses")
    public void he_should_see_the_available_elective_courses() {
        // Write code here that turns the phrase above into concrete actions
        List<String> electiveList = Stream.of("COMPSCI754", "COMPSCI751", "COMPSCI761").collect(Collectors.toList());
        List<WebElement> text= driver.findElements(By.id("electiveCourses"));
        List<String> electiveCoursesOnPage = new ArrayList<>();
        for (String item: text.get(0).getText().split("\n")) {
            electiveCoursesOnPage.add(item);
        }
        if (!electiveCoursesOnPage.equals(electiveList)){
            fail();
        }
    }


    public boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

    public void takeSnapShot(WebDriver webdriver,String fileWithPath) {
        try{
            TakesScreenshot scrShot =((TakesScreenshot)webdriver);
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile=new File(fileWithPath);
            FileUtils.copyFile(SrcFile, DestFile);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }




}
