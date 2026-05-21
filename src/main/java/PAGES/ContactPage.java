package PAGES;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ContactPage extends BasePage {

    // add new contact
    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "birthdate")
    WebElement birthdate;

    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "phone")
    WebElement phone;

    @FindBy(id = "street1")
    WebElement street1;

    @FindBy(id = "street2")
    WebElement street2;

    @FindBy(id = "city")
    WebElement city;

    @FindBy(id = "stateProvince")
    WebElement stateProvince;

    @FindBy(id = "postalCode")
    WebElement postalCode;

    @FindBy(id = "country")
    WebElement country;

    @FindBy(id = "submit")
    WebElement submitButton;

    @FindBy(id = "cancel")
    WebElement cancelButton;

    @FindBy(id = "error")
    WebElement errorMsg;

    // contact details

    @FindBy(id = "edit-contact")
    WebElement editContactButton;

    @FindBy(id = "delete")
    WebElement deleteContactButton;

    @FindBy(id = "return")
    WebElement returnToHomePage;


    public ContactPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addNewContact(String firstName, String lastName, String birthdate, String email, String phone, String street1, String street2, String city, String stateProvince, String postalCode, String country) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.birthdate.sendKeys(birthdate);
        this.email.sendKeys(email);
        this.phone.sendKeys(phone);
        this.street1.sendKeys(street1);
        this.street2.sendKeys(street2);
        this.city.sendKeys(city);
        this.stateProvince.sendKeys(stateProvince);
        this.postalCode.sendKeys(postalCode);
        this.country.sendKeys(country);
    }

    public boolean isErrorVisible() {
        return isElementDisplayed(errorMsg);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void clickCancel() {
        cancelButton.click();
    }

    public void clickEditContact() {
        waitUtils.waitForElementVisible(editContactButton).click();
    }

    public void editFirstName(String value) {
        editField(firstName, value);
    }

    public void editLastName(String value) {
        editField(lastName, value);
    }

    public void editBirthdate(String value) {
        editField(birthdate, value);
    }

    public void editEmail(String value) {
        editField(email, value);
    }

    public void editPhone(String value) {
        editField(phone, value);
    }

    public void editStreet1(String value) {
        editField(street1, value);
    }

    public void editStreet2(String value) {
        editField(street2, value);
    }

    public void editCity(String value) {
        editField(city, value);
    }

    public void editStateProvince(String value) {
        editField(stateProvince, value);
    }

    public void editPostalCode(String value) {
        editField(postalCode, value);
    }

    public void editCountry(String value) {
        editField(country, value);
    }


    // metoda face click pe input, foloseste tastele *CTRL + A* pentru a selecta tot textul din input, dupa care apasa tasta backspace si introduce noua valoare.
    public void editField(WebElement element, String value) {

        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(value);
    }

    public void deleteContact() {
        deleteContactButton.click();
    }

    public void returnToHomePage() {
        returnToHomePage.click();
    }

    public WebElement waitForErrorVisible() {
        return waitUtils.waitForElementVisible(errorMsg);
    }

}