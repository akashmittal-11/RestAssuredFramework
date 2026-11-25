package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {


    @Test(priority = 1,
          dataProvider = "Data",
          dataProviderClass = DataProviders.class)

    public void testPostUser(String userID,
                             String username,
                             String fname,
                             String lname,
                             String email,
                             String password,
                             String phone,
                             String userStatus) {

        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(username);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);
        userPayload.setUserStatus(
            Integer.parseInt(userStatus));

        Response response =
            UserEndpoints.createUser(userPayload);

//        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2,
          dependsOnMethods = "testPostUser",
          dataProvider = "UserNames",
          dataProviderClass = DataProviders.class)

    public void testGetUser(String username) {

        Response response =
            UserEndpoints.getUser(username);

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        Assert.assertEquals(
            response.jsonPath().getString("username"),
            username);
    }
//
//    @Test(priority = 3,
//          dependsOnMethods = "testGetUser",
//          dataProvider = "UserNames",
//          dataProviderClass = DataProviders.class)

    public void testDeleteUser(String username) {

        Response response =
            UserEndpoints.deleteUser(username);

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
