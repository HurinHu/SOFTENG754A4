import base.BaseUtil;

import java.util.*;
import java.util.logging.Level;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook  extends BaseUtil {
	private BaseUtil base;
    private static String OS = System.getProperty("os.name").toLowerCase();

    public Hook(BaseUtil base) {
        this.base = base;
    }

	@Before
	public void startBrowser(Scenario scenario){
		this.base.scenario = scenario;
		String browser="Chrome";
		ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");
        System.setProperty("webdriver.chrome.args", "--disable-logging");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		if (isWindows()) {
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
			this.base.driver = new ChromeDriver(options);
		} else if (isMac()) {
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver(mac)");
			this.base.driver = new ChromeDriver(options);
		} else if (isUnix()) {
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver(linux)");
			this.base.driver = new ChromeDriver(options);
		}
        System.out.println("Start Scenario: "+scenario.getName());
	}

	@After
	public void closeBrowser(Scenario scenario){
		this.takeSnapShot("target/screenshot/"+this.base.screenshot);
		this.base.driver.close();
		this.base.driver.quit();
        if (scenario.isFailed()) {
            System.out.println("Scenario Failed: "+scenario.getName());
        } else {
            System.out.println("Scenario Passed: "+scenario.getName());
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

	public void takeSnapShot(String fileWithPath) {
		try{
			TakesScreenshot scrShot =((TakesScreenshot)this.base.driver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(fileWithPath);
			FileUtils.copyFile(SrcFile, DestFile);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
    }
}
