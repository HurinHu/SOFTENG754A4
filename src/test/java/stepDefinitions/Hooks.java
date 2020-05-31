import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Hooks {
public static WebDriver driver;
	private static String OS = System.getProperty("os.name").toLowerCase();
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
		driver.get("http://localhost:8181/api/data");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.takeSnapShot(driver, "target/screenshot/screenshot.png") ;
	}
	
	@After
	public void closeBrowser()
	{
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
