package api.test;



import org.apache.logging.log4j.LogManager; // log4j
import org.apache.logging.log4j.Logger; // log4j
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

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;


public class DDTests {

	public Logger logger;
	
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostuser(String id,String uname,String fname,String lname,String email,String pwd,String phNo)
	
	{
		
		logger = LogManager.getLogger(this.getClass());
		
	logger.info("****** CreateUser test start **** ");
		
		User userPayload = new User();
		
		userPayload.setId(id);
		userPayload.setUsername(uname);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phNo);
		
		Response response = UserEndPoints.createUser(userPayload);
		
		//response.then().log().all();
		response.statusCode();
		
		String ID = response.jsonPath().getJsonObject("message").toString();
		
		Assert.assertEquals(ID, userPayload.getId(), "Validatating created ID  " + userPayload.getUsername());
		System.out.println(userPayload.getId());
		System.out.println(userPayload.getUsername());
		

        //logger.info("****** CreateUser test End **** ");
	}
	
	
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteuser(String uname)
	
	{
		
		User userPayload = new User();
		
		
		userPayload.setUsername(uname);
		
	
		
	logger.info("****** DeleteUser test start **** ");
		
		System.out.println("Username for deleting " + userPayload.getUsername());
		Response response = UserEndPoints.DeleteUser(uname);
		
		//response.then().log().all();
		response.statusCode();
		
	//	String ID = response.jsonPath().getJsonObject("message").toString();
		
		
		if(response.getStatusCode()==200)
		{
			 Assert.assertEquals(response.getStatusCode(),200, "Validatating response code :" + userPayload.getUsername());
			logger.info("****** User deleted as expected **** "+ userPayload.getUsername() );
			
		}
		
		else
		{
			 logger.info("****** User not deleted as expected **** "+ userPayload.getUsername() );
         Assert.assertEquals(response.getStatusCode(),200, "Validatating response code :" + userPayload.getUsername());
	//	System.out.println(userPayload.getId());
        
		}
	
		
	
	}
	
	
	
	
	
	
	
	
	
	
}
