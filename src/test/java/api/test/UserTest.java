package api.test;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTest {
	
	User userPayloads = new User();
	Faker fake = new Faker();
	public Logger logger;

	@BeforeClass
	public void setup() {
		
		int id = fake.idNumber().hashCode();
		String username = fake.name().username();
		String firstname = fake.name().firstName();
		String lastname = fake.name().lastName();
		String email = fake.internet().emailAddress();
		String password = fake.internet().password();
		String phone = fake.phoneNumber().cellPhone();
		int status = fake.number().numberBetween(0, 2);
				
		userPayloads.setId(id);
		userPayloads.setUsername(username);
		userPayloads.setFirstName(firstname);
		userPayloads.setLastName(lastname);
		userPayloads.setEmail(email);
		userPayloads.setPassword(password);
		userPayloads.setPhone(phone);
		userPayloads.setUserStatus(status);
		
		//initiate the logs
		
		logger = LogManager.getLogger(UserTest.class);
		
	}
	
	@Test(priority=1)
	void createUser() {
		
		logger.info("*********** Creating User ***********");
        Response res = UserEndpoints.createUser(userPayloads);
		
		res.then().log().all();
		int code = res.getStatusCode();
		assertEquals(code, 200);
		logger.info("*********** User Created ***********");

	}
	
	
	@Test(priority=2)
	void getTestUser() {
		
		logger.info("*********** Getting User Details ***********");

		Response res = UserEndpoints.getUser(this.userPayloads.getUsername());
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		res.then().log().all();
		logger.info("*********** Fetched User ***********");

	}
	
	@Test(priority=3)
	void updateUser() {
		 
		logger.info("*********** Updating User ***********");

		String firstname = fake.name().firstName();
		String lastname = fake.name().lastName();
		String email = fake.internet().emailAddress();
		userPayloads.setFirstName(firstname);
		userPayloads.setLastName(lastname);
		userPayloads.setEmail(email);
		
		Response res = UserEndpoints.updateUser(this.userPayloads.getUsername(),userPayloads);
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("*********** User is updated ***********");

	}
	
	
	@Test(priority = 4)
	void deleteUser() {
		
		logger.info("*********** Deleting User ***********");

		Response res = UserEndpoints.deleteUser(this.userPayloads.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("*********** User is Deleted ***********");

	}

}
