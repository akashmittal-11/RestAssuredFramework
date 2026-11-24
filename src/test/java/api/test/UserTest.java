package api.test;

import static org.testng.Assert.assertEquals;

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
		
	}
	
	@Test(priority=1)
	void createUser() {
		
        Response res = UserEndpoints.createUser(userPayloads);
		
		res.then().log().all();
		int code = res.getStatusCode();
		assertEquals(code, 200);
	}
	
	
	@Test(priority=2)
	void getTestUser() {
		
		Response res = UserEndpoints.getUser(this.userPayloads.getUsername());
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		res.then().log().all();
	}
	
	@Test(priority=3)
	void updateUser() {
		 
		String firstname = fake.name().firstName();
		String lastname = fake.name().lastName();
		String email = fake.internet().emailAddress();
		userPayloads.setFirstName(firstname);
		userPayloads.setLastName(lastname);
		userPayloads.setEmail(email);
		
		Response res = UserEndpoints.updateUser(this.userPayloads.getUsername(),userPayloads);
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	
	@Test(priority = 4)
	void deleteUser() {
		
		Response res = UserEndpoints.deleteUser(this.userPayloads.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
