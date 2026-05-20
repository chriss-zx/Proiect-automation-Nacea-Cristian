package tests;

import PAGES.HomePage;
import PAGES.LoginPage;
import PAGES.SignupPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.WaitUtils;


public class LoginTests extends BaseTest {

    // in acest test se deschide site-ul, se apasa butonul de signup, apoi se introduc datele. daca datele sunt invalide, apare mesaj de eroare. daca toate datele sunt valide, contul este creeat si se redirectioneaza catre homepage.
    @Test
    public void signupTest() {

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signupAs("testc", "abcd", "testabc1234@test.com", "test123");
        signupPage.clickSubmit();

        Assert.assertFalse(signupPage.isErrorVisible(), "User-ul nu a putut fi creat.");
    }


    // in acest test se deschide mai intai site-ul, apoi se introduc datele de login. la final se verifica daca datele sunt valide/invalide. daca sunt valide, se redirectioneaza catre homepage, iar daca sunt invalide apare mesaj de eroare.

    @Test
    public void loginTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("testc@test.com", "test123");
        loginPage.clickSubmit();

        Assert.assertFalse(loginPage.isErrorVisible(), "Credentiale login invalide.");

        waitUtils.waitForUrlContains("contactList");

        HomePage homePage = new HomePage(driver);
        homePage.clickLogout();
    }
}