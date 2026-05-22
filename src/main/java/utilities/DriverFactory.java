package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public WebDriver driver;

    public static WebDriver getDriver() {
        if(driver == null) {
            driver = new FirefoxDriver(getFirefoxOptions());
        }
        return driver;
    }

    public static void quitDriver() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        if (isCi()) {
            options.addArguments("--headless");
        }

        options.addPreference("layout.css.devPixelsPerPx", "1.0");

        return options;
    }

    private static boolean isCi() {
        return "true".equalsIgnoreCase(System.getenv("CI"));
    }
}
