package api.endpoints;

import java.util.ResourceBundle;

import api.payloads.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class StoreEndpoints {

	static ResourceBundle getURL() {
		
		ResourceBundle prop = ResourceBundle.getBundle("propertiesFile");
		return prop;
	}
	
	public static Response createStore(Store payload) {
		String post_url=getURL().getString("store_post_url");
		
		Response res = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				
		.when()
		
			.post(post_url);
		
		return res;	
	}
	
	public static Response getStore() {
		String get_url=getURL().getString("store_get_url");
		System.out.println("GET URL IS: " +get_url);
		
		Response res = given()
				
		.when()
		
			.get(get_url);
		
		return res;	
	}
	
	
	public static Response getStoreByID(int storeID) {
		String get_url=getURL().getString("store_getById_url");
		
		Response res = given()
				
				.pathParam("orderID", storeID)
				
		.when()
		
			.get(get_url);
		
		return res;	
	}
	
	
	public static Response deleteStore(int storeID) {
		String delete_url=getURL().getString("store_delete_url");
		
		Response res = given()
				
				.pathParam("orderID", storeID)
				
		.when()
		
			.delete(delete_url);
		
		return res;	
	} 
	
	
}
