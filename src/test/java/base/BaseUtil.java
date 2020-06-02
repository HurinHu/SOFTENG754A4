package base;
import org.openqa.selenium.WebDriver;

public class BaseUtil {

    public WebDriver driver;
    public String screenshot;

    public void setScreenShot(String screenshot) {
        this.screenshot = screenshot;
    }
}