package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver driver;

    public static WebDriver getDriver() {
        if(driver == null) {
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
