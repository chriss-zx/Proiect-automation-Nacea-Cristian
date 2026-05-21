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

public class SignupPage extends BasePage{

    public SignupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "signup")
    WebElement signupButton;

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "submit")
    WebElement submitButton;

    @FindBy(id = "cancel")
    WebElement cancelButton;

    @FindBy(id = "error")
    WebElement errorMsg;


    public HomePage signupAs(String firstName, String lastName, String email, String password) {
        signupButton.click();
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.email.sendKeys(email);
        this.password.sendKeys(password);

        return new HomePage(driver);
    }

    public boolean isErrorVisible() {
        return isElementDisplayed(errorMsg);
    }

    public void clickSubmit() {
        submitButton.click();
    }

}