package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
User userPayload;
Faker faker;
String firstName;
String lastName;
public Logger logger;
@BeforeClass
public void setupData() {
	faker = new Faker();
	userPayload = new User();
	firstName= faker.name().firstName();
	lastName= faker.name().lastName();

	String username =firstName+"."+lastName;
	
	userPayload.setId(faker.idNumber().hashCode());
	//userPayload.setUserName(faker.name().username());
	userPayload.setUserName(username);

	//userPayload.setFirstName(faker.name().firstName()); 
	userPayload.setFirstName(firstName);
	//userPayload.setLastName(faker.name().lastName());
	userPayload.setLastName(lastName);
	userPayload.setEmail(faker.internet().emailAddress());
	userPayload.setPassword(faker.internet().password(5,10));
	userPayload.setPhone(faker.phoneNumber().cellPhone());
	//userPayload.setUserStatus(1);
	System.out.println(username);
	System.out.println("UserName is "+userPayload.getUserName());
	
	//logs
	
	logger=LogManager.getLogger(this.getClass());
	logger.debug("debugging...............");
}
@Test(priority=1)
public void testPostUser() {
	
	logger.info("*******Creating User*******");
	Response response = UserEndPoints.createUser(userPayload);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	
	logger.info("*******User is Created*******");
}
@Test(priority=2,enabled=true)
public void testGetUserName() {
	
	logger.info("*******Reading User Info*******");

	
Response response=UserEndPoints.readUser(userPayload.getUserName());	
response.then().log().body();

Assert.assertEquals(response.getStatusCode(), 404);
logger.info("*******User Info is displayed*******");

}

@Test(priority=3)
public void testUpdateUserName() {
	logger.info("*******Updating User Info*******");

	firstName= faker.name().firstName();
	lastName= faker.name().lastName();
	userPayload.setFirstName(firstName);
	userPayload.setLastName(lastName);
	userPayload.setEmail(faker.internet().emailAddress());
	
	Response response = UserEndPoints.updateUser(this.userPayload.getUserName(), userPayload);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	
	logger.info("*******User Info Updated*******");

	Response responseAfterUPdate = UserEndPoints.readUser(userPayload.getUserName());
	Assert.assertEquals(responseAfterUPdate.getStatusCode(), 404);
	
}

@Test(priority=4)
public void testDeleteByUserName() {
	
	logger.info("*******Deleting User*******");

	Response response = UserEndPoints.deleteUser(this.userPayload.getUserName());
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 404);
	
	logger.info("*******User Deleted*******");

}



}
