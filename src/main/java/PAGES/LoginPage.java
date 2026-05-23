package PAGES;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        return isElementDisplayed(errorMsg);
    }

    public WebElement waitForErrorVisible() {
        return waitUtils.waitForElementVisible(errorMsg);
    }


    public void clickSubmit() {
        waitUtils.waitForElementVisible(submitButton).click();
    }

}
