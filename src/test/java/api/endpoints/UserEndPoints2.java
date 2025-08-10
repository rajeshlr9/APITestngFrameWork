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
import java.util.ResourceBundle;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.payload.User;

// UserEndspoint to perform CRUD operations.
public class UserEndPoints2 {

	// to get url from properties file.
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");	// "routes" is properties file name.	
		
		return routes;
		
	}	
	
	
	
	public static Response createUser(User payload)
	
	{
		
		String post_url = getURL().getString("post_url");
		
		System.out.println("POST URL DIRECTLY" + ResourceBundle.getBundle("routes").getString("post_url"));
		
		
	Response res =	given()
		        .contentType(ContentType.JSON)
		        .accept(ContentType.JSON)
		        //.accept("application/json")
		        .body(payload)
		
		.when()
		
		        .post(post_url);
		
		return res;
	
		
	}
	
	
public static Response ReadUser(String username)
	

	{
		
	String get_url = getURL().getString("get_url");
	
	Response res =	given()
		        .contentType(ContentType.JSON)
		        .accept(ContentType.JSON)
		        //.accept("application/json")
		        .pathParam("username", username)
		
		.when()
		
		        .get(get_url);
		
		return res;
	
		
	}
	
	
public static Response UpdateUser(String username,User payload)

{
	String update_url = getURL().getString("update_url");
	
Response res =	given()
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	        //.accept("application/json")
	        .pathParam("username", username)
	        .body(payload)
	
	.when()
	
	      .put(update_url);
	
	return res;

	
}
	
	
public static Response DeleteUser(String username)

{
	
	String delete_url  = getURL().getString("delete_url");
	
Response res =	given()
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	        //.accept("application/json")
	        .pathParam("username", username)
	
	.when()
	
	        .delete(delete_url);
	
	return res;

	
}













	
	
	
	
}
