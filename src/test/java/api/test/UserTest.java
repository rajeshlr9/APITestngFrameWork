package api.test;


import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.ResourceBundle;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;



/*

[
  {
    "id": 0,
    "username": "string",
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "password": "string",
    "phone": "string",
    "userStatus": 0
  }
]


*/
public class UserTest {

	Faker f;
	User userpayload;
	
	
	@BeforeClass
	public void setupData()
	
	{
		
	f = new Faker();
	userpayload = new User();
		
	
	//userpayload.setId(f.idNumber().hashCode());
	userpayload.setId(Integer.toString(f.idNumber().hashCode()));
	userpayload.setUsername(f.name().username());
	userpayload.setFirstname(f.name().firstName());
	userpayload.setLastname(f.name().lastName());
	userpayload.setEmail(f.internet().safeEmailAddress());	
	userpayload.setPassword(f.internet().password(5, 10));
	userpayload.setPhone(f.phoneNumber().cellPhone());
	
	}
	
	@Test(priority=1)
	public void testPostuser() throws InterruptedException
	{
	
		System.out.println("Create user start .................................................................");
		System.out.println("POST URL DIRECTLY" + ResourceBundle.getBundle("routes").getString("post_url"));
		
	Response response = UserEndPoints2.createUser(userpayload);
		
	response.then().log().all();
	response.statusCode();
	
	System.out.println(userpayload.getUsername());
	
	Thread.sleep(10000);
		
	}
	
	

	
	@Test(priority=2)
	public void testGetuser() throws InterruptedException
	{
		
		System.out.println("get user start .................................................................");
		Thread.sleep(10000);
		Response response =	UserEndPoints2.ReadUser(userpayload.getUsername());
		response.then().log().all();
		System.out.println("INITIAL *********************************  " + this.userpayload.getPhone());
		
		String uname = response.jsonPath().getJsonObject("username").toString();
		
		//System.out.println("INITIAL " + this.userpayload.getFirstname());
		
		
		System.out.println(uname);
		
		Assert.assertEquals(userpayload.getUsername(), uname, "Username validation : ");
		
	}
		
	
	

	@Test(priority=3)
	public void testUpdateuser() throws InterruptedException
	{
		// update data same payload
		
		
		
		userpayload.setFirstname(f.name().firstName());
		userpayload.setLastname(f.name().lastName());
		userpayload.setEmail(f.internet().safeEmailAddress());	
		userpayload.setPhone(f.phoneNumber().cellPhone());
		
		
		
		System.out.println("Update user start ...............................................");
		Thread.sleep(10000);
		Response response =	UserEndPoints2.UpdateUser(userpayload.getUsername(), userpayload);
		
		response.then().log().all();
		
		
		System.out.println("UPDATDED " + this.userpayload.getPhone());
		
		
		
		
		//String uname = response.jsonPath().getJsonObject("username").toString();
		
		
		
		//System.out.println(uname);
		
		//Assert.assertEquals(userpayload.getUsername(), uname, "Username validation : ");
		
	}
		
	
	@Test (priority=4)
	
	public void deleteUser()
	{
		
		System.out.println("Delete user start .................................................................");
		
		Response response = UserEndPoints2.DeleteUser(userpayload.getUsername());
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		
	}
	
	
	
	
	
	
	
}
