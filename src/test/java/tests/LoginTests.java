package tests;

import PAGES.HomePage;
import PAGES.LoginPage;
import PAGES.SignupPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class LoginTests extends BaseTest {


    // in acest test se apasa butonul de signup si se introduc datele VALIDE. se verifica daca datele nu sunt folosite, apoi se verifica ca [...]
    // [...] redirect-ul sa fie corect, adica pe homepage.
    @Test(priority = -1)
    public void signupTestValid() {
        String email = "testc" + System.currentTimeMillis() + "@test.com";

        Reporter.log("Acum se introduc datele de signup.");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signupAs("testc", "abcd", email, "test123");
        signupPage.clickSubmit();

        Reporter.log("Acum se verifica ca mesajul de eroare sa nu fie vizibil.");
        Assert.assertFalse(signupPage.isErrorVisible(), "User-ul nu a putut fi creat.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");
    }


    // in acest test se apasa butonul de signup si se introduc datele INVALIDE, se asteapta ca mesajul de eroare sa fie afisat si se face verificarea.
    @Test(priority = -1)
    public void signupTestInvalid() {

        Reporter.log("Acum se introduc datele de signup.");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signupAs("testc", "abc", "testabc123@test.com", "test123");
        signupPage.clickSubmit();

        signupPage.waitForErrorVisible();

        Reporter.log("Acum se verifica ca mesajul de eroare sa fie vizibil.");
        Assert.assertTrue(signupPage.isErrorVisible(), "User-ul nu a putut fi creat.");
    }


    // in acest test se introduc datele de login VALIDE, apoi se verifica daca datele sunt valide. daca sunt valide, se redirectioneaza catre [...]
    // [...] homepage si se verifica daca pagina este corecta. la final se apasa butonul logout.

    @Test
    public void loginTestValid() {

        Reporter.log("Acum se introduc datele de login.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("testc@test.com", "test123");
        loginPage.clickSubmit();

        Reporter.log("Acum se verifica ca mesajul de eroare sa nu fie vizibil.");
        Assert.assertFalse(loginPage.isErrorVisible(), "Credentiale login invalide.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");

        HomePage homePage = new HomePage(driver);
        homePage.clickLogout();
    }


    // in acest test se introduc datele de login INVALIDE, se asteapta ca mesajul de eroare sa fie afisat si se face verificarea.
    @Test
    public void loginTestInvalid() {

        Reporter.log("Acum se introduc datele de login.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("test", "test123");
        loginPage.clickSubmit();

        loginPage.waitForErrorVisible();

        Reporter.log("Acum se verifica ca mesajul de eroare sa fie vizibil.");
        Assert.assertTrue(loginPage.isErrorVisible(), "Credentiale login invalide.");
    }
}