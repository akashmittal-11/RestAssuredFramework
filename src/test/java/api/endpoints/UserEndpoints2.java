package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {
	
	
	//methods to getting the URLs from properties file.
	static ResourceBundle getURL() {
		
		ResourceBundle prop = ResourceBundle.getBundle("propertiesFile"); //Load the properties file 
		return prop;
	}
	
	
	
	public static Response createUser(User payloads){
		
		String post_url = getURL().getString("post_url");
		
		System.out.println("Post URL is: " +post_url);
		
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payloads)
		
		.when()
			.post(post_url);
		
		return res;
		
	}
	
	public static Response getUser(String userName) {
		
		String get_url = getURL().getString("get_url");

	Response res =  given()
			.pathParam("username", userName)
	.when()
		.get(get_url);
	
	return res;
	}
	
	public static Response updateUser(String userName,User payloads) {
		
		String update_url = getURL().getString("update_url");

		Response res = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payloads)

			.when()
				.put(update_url);
			
			return res;
		
	}
	
	public static Response deleteUser(String userName) {
		
		String delete_url = getURL().getString("delete_url");
		
		Response res =  given()
				.pathParam("username", userName)
		.when()
			.delete(delete_url);
		
		return res;
	}
	
	

}
