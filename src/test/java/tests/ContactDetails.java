package tests;

import PAGES.ContactPage;
import PAGES.HomePage;
import PAGES.LoginPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDetails extends BaseTest {

    // in acest test se face login-ul, se verifica ca mesajul de eroare sa nu fie afisat, apoi se redirectioneaza catre homepage si se verifica ca pagina sa fie cea corecta, adica homepage.
    // se apasa pe index-ul unui contact, se verifica ca pagina deschisa sa fie cea corecta, iar mai apoi se poate modifica fiecare camp separat cu date VALIDE. [...]
    // [...] dupa apasarea butonului submit, se verifica ca mesajul de eroare sa nu fie afisat, apoi se apasa butonul return to homepage si se verifica ca pagina sa fie cea corecta.

    @Test
    public void ValidContactDetailsTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("testc@test.com", "test123");
        loginPage.clickSubmit();

        Assert.assertFalse(loginPage.isErrorVisible(), "Credentiale login invalide.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");

        HomePage homePage = new HomePage(driver);
        homePage.clickOnContact(1);
        Assert.assertTrue(waitUtils.waitForUrlContains("contactDetails"), "URL-ul nu contine contactDetails.");

        ContactPage contactPage = new ContactPage(driver);
        contactPage.clickEditContact();
        Assert.assertTrue(waitUtils.waitForUrlContains("editContact"), "URL-ul nu contine editContact.");
        contactPage.editFirstName("Maria");
        contactPage.editLastName("ABC");
        contactPage.editBirthdate("1996-06-16");
        contactPage.editEmail("abc.maria@test.com");
        contactPage.editPhone("0782766432");
        contactPage.editStreet1("Barcelona");
        contactPage.editCity("Bucuresti");

        contactPage.clickSubmit();

        Assert.assertFalse(contactPage.isErrorVisible(), "Datele introduse nu pot fi validate.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactDetails"), "URL-ul nu contine contactDetails.");

        contactPage.returnToHomePage();

        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");
    }


    // in acest test se face login-ul, se verifica ca mesajul de eroare sa nu fie afisat, apoi se redirectioneaza catre homepage si se verifica ca pagina sa fie cea corecta, adica homepage.
    // se apasa pe index-ul unui contact, se verifica ca pagina deschisa sa fie cea corecta, iar mai apoi se poate modifica fiecare camp separat folosind date INVALIDE. [...]
    // [...] dupa apasarea butonului submit, se verifica ca mesajul de eroare sa fie afisat
    @Test
    public void InvalidContactDetailsTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("testc@test.com", "test123");
        loginPage.clickSubmit();

        Assert.assertFalse(loginPage.isErrorVisible(), "Credentiale login invalide.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");

        HomePage homePage = new HomePage(driver);
        homePage.clickOnContact(1);
        Assert.assertTrue(waitUtils.waitForUrlContains("contactDetails"), "URL-ul nu contine contactDetails.");

        ContactPage contactPage = new ContactPage(driver);
        contactPage.clickEditContact();
        Assert.assertTrue(waitUtils.waitForUrlContains("editContact"), "URL-ul nu contine editContact.");
        contactPage.editFirstName("Maria");
        contactPage.editLastName("ABC");
        contactPage.editBirthdate("test123");
        contactPage.editEmail("abc.maria@test.com");
        contactPage.editPhone("doesn't exist");
        contactPage.editStreet1("Barcelona");
        contactPage.editCity("Bucuresti");

        contactPage.clickSubmit();

        Assert.assertTrue(contactPage.isErrorVisible(), "Datele introduse nu pot fi validate.");
    }


    // in acest test se face login-ul, se verifica ca mesajul de eroare sa nu fie afisat, apoi se redirectioneaza catre homepage si se verifica ca pagina sa fie cea corecta, adica homepage.
    // se apasa pe index-ul unui contact, se verifica ca pagina deschisa sa fie cea corecta si se apasa butonul "Delete Contact".
    @Test
    public void DeleteContactTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("testc@test.com", "test123");
        loginPage.clickSubmit();

        Assert.assertFalse(loginPage.isErrorVisible(), "Credentiale login invalide.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");

        HomePage homePage = new HomePage(driver);
        homePage.clickOnContact(1);
        Assert.assertTrue(waitUtils.waitForUrlContains("contactDetails"), "URL-ul nu contine contactDetails.");

        ContactPage contactPage = new ContactPage(driver);
        contactPage.deleteContact();
    }
}