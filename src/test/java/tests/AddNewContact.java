package tests;

import PAGES.ContactPage;
import PAGES.HomePage;
import PAGES.LoginPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewContact extends BaseTest {

    // se face login-ul, se verifica ca mesajul de eroare sa nu fie afisat si se redirectioneaza catre homepage, dupa care se verifica ca pagina [...]
    // [...]sa fie cea corecta.
    // dupa redirectionarea pe homepage, se apasa butonul de "Add a New Contact", se verifica ca pagina sa fie cea corecta si se introduc datele VALIDE, [...]
    // [...] dupa care se apasa butonul submit, se verifica ca pagina sa fie cea corecta si apoi se verifica daca contactul a fost adaugat.

    @Test
    public void addValidContactTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("testc@test.com", "test123");
        loginPage.clickSubmit();

        Assert.assertFalse(loginPage.isErrorVisible(), "Credentiale login invalide.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");

        HomePage homePage = new HomePage(driver);
        homePage.createNewContact();

        Assert.assertTrue(waitUtils.waitForUrlContains("addContact"), "URL-ul nu contine addContact.");

        ContactPage contactPage = new ContactPage(driver);
        contactPage.addNewContact("Iulia", "Boierescu", "1974/07/27", "boierescu.iulia@test.com", "0728468462", "Florilor, 84", "", "Cluj-Napoca", "", "283648", "Romania");
        contactPage.clickSubmit();

        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");
        Assert.assertTrue(contactPage.isContactPresent("Iulia Boierescu"), "Contactul nu a fost adaugat.");

    }

    // se face login-ul, se verifica ca mesajul de eroare sa nu fie afisat si se redirectioneaza catre homepage, dupa care se verifica ca pagina [...]
    // [...] sa fie cea corecta.
    // dupa redirectionarea pe homepage si verificare, se apasa butonul "Add a New Contact", se verifica ca pagina sa fie cea corecta si se [...]
    // [...] introduc datele INVALIDE, dupa care se apasa butonul submit. se asteapta ca mesajul de eroare sa apara si apoi se verifica daca mesajul [...]
    // [...] de eroare este afisat.

    @Test
    public void addInvalidContactTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("testc@test.com", "test123");
        loginPage.clickSubmit();

        Assert.assertFalse(loginPage.isErrorVisible(), "Credentiale login invalide.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");

        HomePage homePage = new HomePage(driver);
        homePage.createNewContact();

        Assert.assertTrue(waitUtils.waitForUrlContains("addContact"), "URL-ul nu contine addContact.");

        ContactPage contactPage = new ContactPage(driver);
        contactPage.addNewContact("Andrei", "Ionescu", "doesn't exist", "ionescu.andrei@test", "doesn't exist", "Marului, 726", "Bl. 7, sc. B", "Bucuresti", "Romania", "823664", "Romania");
        contactPage.clickSubmit();

        contactPage.waitForErrorVisible();

        Assert.assertTrue(homePage.isErrorVisible(), "Datele introduse nu pot fi validate.");
    }
}
