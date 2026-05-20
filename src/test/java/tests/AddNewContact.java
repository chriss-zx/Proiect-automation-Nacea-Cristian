package tests;

import PAGES.ContactPage;
import PAGES.HomePage;
import PAGES.LoginPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewContact extends BaseTest {


    // mai intai se deschide aplicatia, apoi se face login-ul. daca datele sunt invalide apare mesaj de eroare, iar daca sunt valide se redirectioneaza catre homepage.
    // dupa redirectionarea pe homepage, se apasa butonul de "Add a New Contact" si se introduc datele. campurile firstname si lastname sunt obligatorii, iar anumite campuri (ex. date of birth, email, phone) au un format care trebuie respectat, prin urmare [...]
    // [...] daca formatul nu este respectat, se face verificare. daca formatul este gresit, apare eroare si testul pica.

    // la primul apel al metodei addNewContact, toate datele sunt valide. dupa completare, se apasa butonul submit, apoi se asteapta ca pagina afisata sa fie homepage-ul, dupa care se apasa din nou "Add a New Contact".
    // la al 2-lea apel al metodei, anumite date sunt gresite intentionat, ca sa se verifice mesajul de eroare.


    @Test
    public void addNewContactTest() {


        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("testc@test.com", "test123");
        loginPage.clickSubmit();

        Assert.assertFalse(loginPage.isErrorVisible(), "Credentiale login invalide.");

        HomePage homePage = new HomePage(driver);
        homePage.createNewContact();

        ContactPage contactPage = new ContactPage(driver);
        contactPage.addNewContact("Iulia", "Boierescu", "1974/07/27","boierescu.iulia@test.com", "0728468462", "Florilor, 84", "", "Cluj-Napoca","", "283648","Romania");
        contactPage.clickSubmit();

        waitUtils.waitForUrlContains("contactList");

        homePage.createNewContact();

//        addContactPage.clickCancel();
//
//        homePage.createNewContact();

        contactPage.addNewContact("Andrei", "Ionescu", "doesn't exist","ionescu.andrei@test", "doesn't exist", "Marului, 726", "Bl. 7, sc. B", "Bucuresti", "Romania", "823664", "Romania");
        contactPage.clickSubmit();

        Assert.assertFalse(homePage.isErrorVisible(), "Datele introduse nu pot fi validate.");

    }
}
