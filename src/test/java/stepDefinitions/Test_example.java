import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

public class Test_example {
	public static WebDriver driver;
	private static String OS = System.getProperty("os.name").toLowerCase();
	private String today;
    private String actualAnswer;

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
		driver.get("http://localhost:8181/test.html");
		driver.manage().window().maximize();
	}
	
	@Given("today is Sunday")
    public void today_is_Sunday() {
        today = "Sunday";
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
		List<WebElement> text= driver.findElements(By.tagName("h1"));
        actualAnswer = text.get(0).getText();
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
	
	@After
	public void closeBrowser(){
		this.takeSnapShot(driver, "target/screenshot/Test_example.png") ;
		driver.quit();
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
