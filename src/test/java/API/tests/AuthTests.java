package API.tests;

import API.config.TestConfig;
import API.data.TestData;
import API.utils.utilsClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class AuthTests {

    @Test
    public void signUpTest() {
        String email = "api" + System.currentTimeMillis() + "@test.com";
        String token = utilsClass.createUser("api", "test", email, "test123");

        Assert.assertNotNull(token);

        System.out.println(token);
    }


    @Test
    public void loginWithToken() {
        String token = utilsClass.getToken(TestData.email, TestData.password);
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(TestConfig.testerContactList_base_url + TestConfig.login_endpoint)
                .then()
                .statusCode(200)
                .body("email", notNullValue())
                .body("password", notNullValue());
    }

    @Test
    public void logoutTest() {
        String token = utilsClass.getToken(TestData.email, TestData.password);
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .post(TestConfig.testerContactList_base_url + TestConfig.users_endpoint + "/logout")
                .then()
                .statusCode(200);
    }
}
