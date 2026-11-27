package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.StoreEndpoints;
import api.payloads.Store;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class StoreDDTests {

	
	public static final String path = System.getProperty("user.dir") + "/testData/StoreData.xlsx";

	 @Test(priority = 1,
	          dataProvider = "Data",
	          dataProviderClass = DataProviders.class)
	public static void createStore(String id, String storeID, String quantity, String status, String complete) {
		
	    Store storePayloads = new Store();	

		storePayloads.setId(Integer.parseInt(id));
		storePayloads.setStoreId(Integer.parseInt(storeID));
		storePayloads.setQuantity(Integer.parseInt(quantity));
		storePayloads.setStatus(status);
		storePayloads.setComplete(Boolean.parseBoolean(complete));
		
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
	

	 @Test(priority = 3,
	          dataProvider = "IDs",
	          dataProviderClass = DataProviders.class)
	public void getStoreByID(String ids) {
		System.out.println("Generate ID is: " + ids);
		Response res = StoreEndpoints.getStoreByID(Integer.parseInt(ids));
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);
	}
	
	 @Test(priority = 4,
			 dependsOnMethods = "getStoreByID",
	          dataProvider = "IDs",
	          dataProviderClass = DataProviders.class)
	void deleteStore(String ids) {
		
		Response res = StoreEndpoints.deleteStore(Integer.parseInt(ids));
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);
		
	}
	
	
	
	
}
