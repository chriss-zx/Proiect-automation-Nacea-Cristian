package PAGES;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WaitUtils;

public class BasePage {

        protected WebDriver driver;
        protected WaitUtils waitUtils;

        public BasePage(WebDriver driver) {
            this.driver = driver;
            this.waitUtils = new WaitUtils(driver);
        }

    public boolean isError(WebElement element) {
        return waitUtils.isElementVisible(element);
    }
}
