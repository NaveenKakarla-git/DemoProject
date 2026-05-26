package Demoproject;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import file.ReUsableMethods;
import file.payload;
import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;


 public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	public void AddBook(String isbn,String aisle)
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String responce = given().log().all().header("Content-Type","application/json").body(payload.AddBook(isbn,aisle))
		.when().post("/Library/Addbook.php").
		then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = ReUsableMethods.rawToJson(responce);
		String id =js.get("ID");
		System.out.println(id);
		
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"osbje","4333"},{"objes","5343"},{"odgse","6534"}};
	}

	
	}


