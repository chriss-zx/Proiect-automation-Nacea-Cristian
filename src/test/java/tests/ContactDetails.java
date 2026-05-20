package tests;

import PAGES.ContactPage;
import PAGES.HomePage;
import PAGES.LoginPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.WaitUtils;

public class ContactDetails extends BaseTest {

    // mai intai se deschide aplicatia, apoi se face login-ul. daca datele sunt invalide apare mesaj de eroare, iar daca sunt valide se redirectioneaza catre homepage.
    // se apasa pe index-ul unui contact, iar mai apoi se poate modifica fiecare camp separat. dupa apasarea butonului submit, se face verificare, iar daca datele nu respecta formatul, apare mesaj de eroare si testul pica.
    // daca datele sunt valide, se apasa butonul de return to homepage, se asteapta ca pagina afisata sa fie homepage-ul, apoi se face click pe index-ul unui contact si se apasa butonul delete.

    @Test
    public void ContactDetailsTest() {

        HomePage homePage = new HomePage(driver);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("testc@test.com", "test123");
        loginPage.clickSubmit();

        Assert.assertFalse(loginPage.isErrorVisible(), "Credentiale login invalide.");

        homePage.clickOnContact(2);

        ContactPage contactPage = new ContactPage(driver);
        contactPage.clickEditContact();
        contactPage.editFirstName("Maria");
        contactPage.editLastName("ABC");
        contactPage.editBirthdate("1996-06-16");
        contactPage.editEmail("abc.maria@test.com");
        contactPage.editPhone("0782766432");
        contactPage.editStreet1("Barcelona");
        contactPage.editCity("Bucuresti");

        contactPage.clickSubmit();

        Assert.assertFalse(contactPage.isErrorVisible(), "Datele introduse nu pot fi validate.");

//        contactPage.clickCancel();

        contactPage.returnToHomePage();

        waitUtils.waitForUrlContains("contactList");

        homePage.clickOnContact(0);
        contactPage.deleteContact();
    }
}