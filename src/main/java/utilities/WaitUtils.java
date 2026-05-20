package utilities;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    // metoda asteapta pana cand URL-ul contine parametrul.
    public boolean waitForUrlContains(String value) {
        return wait.until(ExpectedConditions.urlContains(value));
    }


    public boolean isElementVisible(WebElement element) {

        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}