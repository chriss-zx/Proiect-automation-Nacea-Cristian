package tests;

import PAGES.HomePage;
import PAGES.LoginPage;
import PAGES.SignupPage;
import base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.WaitUtils;


public class LoginTests extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTests.class);

    // in acest test se apasa butonul de signup si se introduc datele VALIDE. se verifica daca datele nu sunt folosite, apoi se verifica ca redirect-ul sa fie corect, adica pe homepage.
    @Test
    public void signupTestValid() {

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signupAs("testc", "abcd", "testabc9876@test.com", "test123");
        signupPage.clickSubmit();

        Assert.assertFalse(signupPage.isErrorVisible(), "User-ul nu a putut fi creat.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");
    }


    // in acest test se apasa butonul de signup si se introduc datele INVALIDE, dupa care se verifica ca mesajul de eroare sa fie afisat.
    @Test
    public void signupTestInvalid(){

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signupAs("testc", "abc", "testabc123@test.com", "test123");
        signupPage.clickSubmit();

        Assert.assertTrue(signupPage.isErrorVisible(), "User-ul nu a putut fi creat.");
    }



    // in acest test se introduc datele de login VALIDE, apoi se verifica daca datele sunt valide. daca sunt valide, se redirectioneaza catre homepage si se verifica daca pagina este corecta. la final se apasa butonul logout.

    @Test
    public void loginTestValid() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("testc@test.com", "test123");
        loginPage.clickSubmit();

        Assert.assertFalse(loginPage.isErrorVisible(), "Credentiale login invalide.");
        Assert.assertTrue(waitUtils.waitForUrlContains("contactList"), "URL-ul nu contine contactList.");

        HomePage homePage = new HomePage(driver);
        homePage.clickLogout();
    }


    // in acest test se introduc datele de login INVALIDE si se verifica ca mesajul de eroare sa fie afisat.
    @Test
    public void loginTestInvalid() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("test", "test123");
        loginPage.clickSubmit();

        Assert.assertTrue(loginPage.isErrorVisible(), "Credentiale login invalide.");
    }


}