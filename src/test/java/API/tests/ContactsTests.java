package API.tests;

import API.data.TestData;
import API.utils.utilsClass;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

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
}
