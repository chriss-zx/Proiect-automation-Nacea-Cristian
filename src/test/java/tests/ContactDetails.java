package tests;

import PAGES.ContactPage;
import PAGES.HomePage;
import PAGES.LoginPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ContactDetails extends BaseTest {

    // in acest test se face login-ul, se verifica ca mesajul de eroare sa nu fie afisat, apoi se redirectioneaza catre homepage si se verifica [...]
    // [...] ca pagina sa fie cea corecta, adica homepage.
    // se apasa pe index-ul unui contact, se verifica ca pagina deschisa sa fie cea corecta, iar mai apoi se poate modifica fiecare camp separat [...]
    // [...] cu date VALIDE. dupa apasarea butonului submit, se verifica ca mesajul de eroare sa nu fie afisat, apoi se apasa butonul [...]
    // [...] return to homepage si se verifica ca pagina sa fie cea corecta.

    @Test(priority = 2)
    public void ValidContactDetailsTest() {

        Reporter.log("Acum se introduc datele de login.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginValid();
        loginPage.clickSubmit();

        Reporter.log("Acum se verifica ca mesajul de eroare sa nu fie vizibil.");
        Assert.assertFalse(loginPage.isErrorVisible(), "Credentiale login invalide.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");

        Reporter.log("Acum se apasa pe contact.");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnContact(0);

        Reporter.log("Acum se verifica ca URL-ul sa fie corect.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactDetails"), "URL-ul nu contine contactDetails.");

        Reporter.log("Acum se apasa 'Edit contact'.");
        ContactPage contactPage = new ContactPage(driver);
        contactPage.clickEditContact();

        Reporter.log("Acum se verifica ca URL-ul sa fie corect.");
        Assert.assertTrue(waitUtils.waitForUrlContains("editContact"), "URL-ul nu contine editContact.");

        Reporter.log("Acum se introduc noile date.");
        contactPage.editFirstName("Maria");
        contactPage.editLastName("ABC");
        contactPage.editBirthdate("1996-06-16");
        contactPage.editEmail("abc.maria@test.com");
        contactPage.editPhone("0782766432");
        contactPage.editStreet1("Barcelona");
        contactPage.editCity("Bucuresti");

        contactPage.clickSubmit();

        Reporter.log("Acum se verifica ca mesajul de eroare sa nu fie afisat.");
        Assert.assertFalse(contactPage.isErrorVisible(), "Datele introduse nu pot fi validate.");

        Reporter.log("Acum se verifica ca URL-ul sa fie corect.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactDetails"), "URL-ul nu contine contactDetails.");

        contactPage.returnToHomePage();

        Reporter.log("Acum se verifica ca pagina deschisa sa fie Homepage.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");
    }


    // in acest test se face login-ul, se verifica ca mesajul de eroare sa nu fie afisat, apoi se redirectioneaza catre homepage si se verifica [...]
    // [...] ca pagina sa fie cea corecta, adica homepage.
    // se apasa pe index-ul unui contact, se verifica ca pagina deschisa sa fie cea corecta, iar mai apoi se poate modifica fiecare camp separat [...]
    // [...] folosind date INVALIDE. dupa apasarea butonului submit, se asteapta ca mesajul de eroare sa fie afisat si se face verificarea.
    @Test(priority = 2)
    public void InvalidContactDetailsTest() {

        Reporter.log("Acum se introduc datele de login.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginValid();
        loginPage.clickSubmit();

        Reporter.log("Acum se verifica ca mesajul de eroare sa nu fie vizibil.");
        Assert.assertFalse(loginPage.isErrorVisible(), "Credentiale login invalide.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");

        Reporter.log("Acum se apasa pe contact.");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnContact(0);

        Reporter.log("Acum se verifica ca URL-ul sa fie corect.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactDetails"), "URL-ul nu contine contactDetails.");

        Reporter.log("Acum se apasa 'Edit contact'.");
        ContactPage contactPage = new ContactPage(driver);
        contactPage.clickEditContact();

        Reporter.log("Acum se verifica ca URL-ul sa fie corect.");
        Assert.assertTrue(waitUtils.waitForUrlContains("editContact"), "URL-ul nu contine editContact.");

        Reporter.log("Acum se introduc noile date.");
        contactPage.editFirstName("Maria");
        contactPage.editLastName("ABC");
        contactPage.editBirthdate("test123");
        contactPage.editEmail("abc.maria@test.com");
        contactPage.editPhone("doesn't exist");
        contactPage.editStreet1("Barcelona");
        contactPage.editCity("Bucuresti");

        contactPage.clickSubmit();

        Reporter.log("Acum se asteapta ca mesajul de eroare sa fie afisat.");
        contactPage.waitForErrorVisible();

        Reporter.log("Acum se verifica ca mesajul de eroare sa nu fie afisat.");
        Assert.assertTrue(contactPage.isErrorVisible(), "Datele introduse nu pot fi validate.");
    }


    // in acest test se face login-ul, se verifica ca mesajul de eroare sa nu fie afisat, apoi se redirectioneaza catre homepage si se verifica [...]
    // [...] ca pagina sa fie cea corecta, adica homepage.
    // se apasa pe index-ul unui contact, se verifica ca pagina deschisa sa fie cea corecta si se apasa butonul "Delete Contact".
    // mai apoi se accepta alerta dechisa, se verifica ca pagina deschisa sa fie homepage, dupa care se asteapta ca, contactul sa dispara [...]
    // [...] de pe pagina si se face verificarea.
    @Test(priority = 3)
    public void DeleteContactTest() {

        Reporter.log("Acum se introduc datele de login.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginValid();
        loginPage.clickSubmit();

        Reporter.log("Acum se verifica ca mesajul de eroare sa nu fie vizibil.");
        Assert.assertFalse(loginPage.isErrorVisible(), "Credentiale login invalide.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");

        Reporter.log("Acum se apasa pe contact.");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnContact(0);

        Reporter.log("Acum se verifica ca URL-ul sa fie corect.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactDetails"), "URL-ul nu contine contactDetails.");

        Reporter.log("Acum se apasa 'Delete contact'.");
        ContactPage contactPage = new ContactPage(driver);
        contactPage.deleteContact();

        Reporter.log("Acum se accepta alerta.");
        waitUtils.acceptAlert();

        Reporter.log("Acum se verifica ca pagina deschisa sa fie Homepage.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");

        waitUtils.waitForContactToDisappear("Iulia Boierescu");

        Reporter.log("Acum se verifica ca, contactul sa nu fie afisat.");
        Assert.assertFalse(contactPage.isContactPresent("Iulia Boierescu"), "Contactul inca exista.");
    }
}