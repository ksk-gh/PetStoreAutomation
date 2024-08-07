package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostuser(String userId, String userName, String fName, String lName,String userEmail, String pwd, String phone) {
		User userPayload= new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUserName(userName);
		userPayload.setFirstName(lName);
		userPayload.setLastName(lName);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);

		
		Response response=UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)

	public void testDeleteUserByName(String userName) {
		User userPayload= new User();
	

		
		Response response=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 404);
		
	}
	
}
