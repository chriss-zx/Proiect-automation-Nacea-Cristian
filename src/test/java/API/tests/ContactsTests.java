package API.tests;

import API.config.TestConfig;
import API.data.TestData;
import API.utils.utilsClass;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;


public class ContactsTests {

    @Test
    public void addContactTest() {
        String token = utilsClass.getToken(TestData.email, TestData.password);
        String addContact = utilsClass.addContact(token, "api", "test", "1927/07/26", "api.testing@postman.com", "0736493624", "Strada 2", "Nr. 27", "Bucuresti", "IF", "2192736", "Romania");

        Assert.assertNotNull(addContact);

        System.out.println(addContact);
    }

    @Test
    public void updateContactTest() {
        String token = utilsClass.getToken(TestData.email, TestData.password);
        String contactId = utilsClass.addContact(token, "api", "test", "1927-07-26", "api.testing@postman.com", "0736493624", "Strada 2", "Nr. 27", "Bucuresti", "IF", "2192736", "Romania");
        utilsClass.updateContact(token, contactId, "updateFirst", "updateLast", "1990-08-12", "new@email.com", "011111111", "new1", "new2", "Vaslui", "VS", "029373", "Romania");

        System.out.println(token);
        ValidatableResponse response = utilsClass.getContactById(token, contactId);
        response.body("firstName", equalTo("updateFirst"))
                .body("lastName", equalTo("updateLast"));
    }

    @Test
    public void getContactListTest() {
        String token = utilsClass.getToken(TestData.email, TestData.password);

        given()

                .header("Authorization", "Bearer " + token)
                .when()
                .get(TestConfig.testerContactList_base_url + TestConfig.contact_endpoint)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("_id", notNullValue());
    }

    @Test
    public void deleteContactTest() {
        String token = utilsClass.getToken(TestData.email, TestData.password);

        String contactId = utilsClass.addContact(token, "api", "delete", "1999-07-12", "test@delete.com", "0722222222", "str1", "str2", "Sibiu", "SB", "120177", "Romania");
        utilsClass.deleteContact(token, contactId);
    }
}
