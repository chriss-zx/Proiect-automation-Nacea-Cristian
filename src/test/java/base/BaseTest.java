package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;
import utilities.DriverFactory;
import utilities.WaitUtils;


public class BaseTest {

    protected WebDriver driver;
    protected WaitUtils waitUtils;


    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("baseURL"));

        waitUtils = new WaitUtils(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}