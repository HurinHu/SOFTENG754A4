package base;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Scenario;

public class BaseUtil {

    public WebDriver driver;
    public String screenshot;
    public Scenario scenario;

    public void setScreenShot(String screenshot) {
        this.screenshot = screenshot;
    }
}