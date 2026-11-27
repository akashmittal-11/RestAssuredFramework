package api.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndpoints;
import api.payloads.Store;
import io.restassured.response.Response;

public class StoreTest {
	
	static Store storePayloads = new Store();
	Faker fake = new Faker();
	
	@BeforeClass
	public void setup() {
		
		Random rand = new Random();
		
		String[] options = new String[] {"placed","approved","delivered "};

		int id = rand.nextInt();
		int store_id = rand.nextInt();
		int quantity = fake.number().numberBetween(0, 100);
		String status = fake.options().option(options);
		boolean complete = fake.bool().bool();
				
		storePayloads.setId(id);
		storePayloads.setStoreId(store_id);
		storePayloads.setQuantity(quantity);
		storePayloads.setStatus(status);
		storePayloads.setComplete(complete);
	}
	
	@Test(priority=1)
	public static void createStore() {
		
		Response res = StoreEndpoints.createStore(storePayloads);
		
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);	
		res.then().log().body();
	}
	
	@Test(priority=2)
	public void getStore() {
		
		Response res = StoreEndpoints.getStore();
		int code = res.getStatusCode();
		res.then().log().all();
		Assert.assertEquals(code, 200);	
	}
	
	@Test(priority=3)
	public void getStoreByID() {
		System.out.println("Generate ID is: " + this.storePayloads.getId());
		Response res = StoreEndpoints.getStoreByID(this.storePayloads.getId());
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);
	}
	
	@Test(priority=4)
	void deleteStore() {
		
		System.out.println("Generate ID is: " + this.storePayloads.getId());
		Response res = StoreEndpoints.deleteStore(this.storePayloads.getId());
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);
		
	}
	
	
	
	
	
	

}
