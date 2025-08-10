package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.payload.User;

// UserEndspoint to perform CRUD operations.
public class UserEndPoints {

	public static Response createUser(User payload)
	
	{
		
	Response res =	given()
		        .contentType(ContentType.JSON)
		        .accept(ContentType.JSON)
		        //.accept("application/json")
		        .body(payload)
		
		.when()
		
		        .post(Routes.post_url);
		
		return res;
	
		
	}
	
	
public static Response ReadUser(String username)
	
	{
		
	Response res =	given()
		        .contentType(ContentType.JSON)
		        .accept(ContentType.JSON)
		        //.accept("application/json")
		        .pathParam("username", username)
		
		.when()
		
		        .get(Routes.get_url);
		
		return res;
	
		
	}
	
	
public static Response UpdateUser(String username,User payload)

{
	
Response res =	given()
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	        //.accept("application/json")
	        .pathParam("username", username)
	        .body(payload)
	
	.when()
	
	      .put(Routes.update_url);
	
	return res;

	
}
	
	
public static Response DeleteUser(String username)

{
	
Response res =	given()
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	        //.accept("application/json")
	        .pathParam("username", username)
	
	.when()
	
	        .delete(Routes.delete_url);
	
	return res;

	
}













	
	
	
	
}
