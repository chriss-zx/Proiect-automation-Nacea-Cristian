package PAGES;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.WaitUtils;

import java.time.Duration;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "submit")
    WebElement submitButton;

    @FindBy(id = "error")
    WebElement errorMsg;

    public HomePage loginAs(String email, String password) {
        this.email.sendKeys(email);
        this.password.sendKeys(password);

        return new HomePage(driver);
    }

    public boolean isErrorVisible() {
        return isError(errorMsg);
    }

    public void clickSubmit() {
        submitButton.click();
    }

}
