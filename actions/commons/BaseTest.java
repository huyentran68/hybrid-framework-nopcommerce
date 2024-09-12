package commons;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    protected WebDriver getBrowserDriver(String browserName){
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser){
            case FIREFOX:
                //System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
                driver = new FirefoxDriver();
                break;
            case CHROME:
                //System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is invalid");
        }

        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        //driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }
    protected String getEmailAddress(){
        Random rand = new Random();
        return "automation" + rand.nextInt(9999) + "@mail.vn";
    }
    protected void closeBrowser(){
        if (null==driver){
            System.out.println("Browser is closed");
        }
        else {
            driver.quit();
        }
    }
}
