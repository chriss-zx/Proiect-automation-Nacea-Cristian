package PAGES;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//button[@id='logout']")
    WebElement logoutButton;
    @FindBy(id = "add-contact")
    WebElement addNewContactButton;
    @FindBy(id = "error")
    WebElement errorMsg;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickLogout() {
        waitUtils.waitForElementVisible(logoutButton).click();
    }

    public void createNewContact() {
        waitUtils.waitForElementVisible(addNewContactButton).click();
    }


    // metoda face scroll pana elementul este vizibil in centrul paginii, apoi da click.
    public void clickOnContact(int index) {

        List<WebElement> rows = driver.findElements(
                By.cssSelector("tr.contactTableBodyRow")
        );

        WebElement row = rows.get(index);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", row);

        waitUtils.waitForElementVisible(row);

        try {
            row.click();
        } catch (ElementNotInteractableException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", row);
        }
    }

    // metoda verifica ca mesajul de eroare sa fie afisat.
    public boolean isErrorVisible() {
        return isElementDisplayed(errorMsg);
    }
}
