package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	
	
	public static Response createUser(User payloads){
		
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payloads)
		
		.when()
			.post(Routes.post_url);
		
		return res;
		
	}
	
	public static Response getUser(String userName) {
		
	Response res =  given()
			.pathParam("username", userName)
	.when()
		.get(Routes.get_url);
	
	return res;
	}
	
	public static Response updateUser(String userName,User payloads) {
		
		Response res = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payloads)

			.when()
				.put(Routes.put_url);
			
			return res;
		
	}
	
	public static Response deleteUser(String userName) {
		
		Response res =  given()
				.pathParam("username", userName)
		.when()
			.delete(Routes.delete_url);
		
		return res;
	}
	
	

}
