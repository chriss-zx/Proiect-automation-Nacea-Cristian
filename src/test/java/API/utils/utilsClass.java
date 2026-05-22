package API.utils;

import API.config.TestConfig;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class utilsClass {

    public static String createUser(String firstName, String lastName, String email, String password) {
        Map<String, String> payload = new HashMap<>();
        payload.put("firstName", firstName);
        payload.put("lastName", lastName);
        payload.put("email", email);
        payload.put("password", password);

        return given()

                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(TestConfig.testerContactList_base_url + TestConfig.users_endpoint)
                .then()
                .statusCode(201)
                .body("token", notNullValue())
                .extract()
                .path("token");
    }


    public static String getToken(String email, String password) {
        String payload = "{\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"password\": \"" + password + "\"\n" +
                "}";
        String token = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(TestConfig.testerContactList_base_url + TestConfig.login_endpoint)
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .path("token");

        return token;
    }

    public static String addContact(String token, String firstName, String lastName, String birthdate, String email, String phone, String street1, String street2, String city, String stateProvince, String postalCode, String country) {
        Map<String, String> payload = new HashMap<>();
        payload.put("firstName", firstName);
        payload.put("lastName", lastName);
        payload.put("birthdate", birthdate);
        payload.put("email", email);
        payload.put("phone", phone);
        payload.put("street1", street1);
        payload.put("street2", street2);
        payload.put("city", city);
        payload.put("stateProvince", stateProvince);
        payload.put("postalCode", postalCode);
        payload.put("country", country);

        return given()

                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .when()
                .post(TestConfig.testerContactList_base_url + TestConfig.contact_endpoint)
                .then()
                .statusCode(201)
                .extract()
                .path("_id");
    }

    public static ValidatableResponse updateContact(String token, String contactId, String firstName, String lastName, String birthdate, String email, String phone, String street1, String street2, String city, String stateProvince, String postalCode, String country) {
        Map<String, String> payload = new HashMap<>();
        payload.put("firstName", firstName);
        payload.put("lastName", lastName);
        payload.put("birthdate", birthdate);
        payload.put("email", email);
        payload.put("phone", phone);
        payload.put("street1", street1);
        payload.put("street2", street2);
        payload.put("city", city);
        payload.put("stateProvince", stateProvince);
        payload.put("postalCode", postalCode);
        payload.put("country", country);

        return given()

                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .when()
                .put(TestConfig.testerContactList_base_url + TestConfig.contact_endpoint + "/" + contactId)
                .then()
                .statusCode(200);
    }

    public static ValidatableResponse getContactById(String token, String contactId) {

        return given()

                .header("Authorization", "Bearer " + token)
                .when()
                .get(TestConfig.testerContactList_base_url + TestConfig.contact_endpoint + "/" + contactId)
                .then()
                .statusCode(200);
    }

    public static ValidatableResponse deleteUser(String token) {

        return given()

                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(TestConfig.testerContactList_base_url + TestConfig.users_endpoint + "/me")
                .then()
                .statusCode(200);
    }
}
