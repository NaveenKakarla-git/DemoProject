package Demoproject;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class JiraCreateissue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "http://localhost:8080/";
		SessionFilter session = new SessionFilter();
		
		//jira Login
		
		String responce = given().header("Content-Type","application/json")
				.body("{ \"username\": \"nkakarla\", "
						+ "\"password\": \"Naveenkakarla8@\" }").log().all().filter(session).when().post("rest/auth/1/session").
				then().log().all().extract().response().asString();
		
		
		//Create issue
		
	     given().header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\":\r\n"
				+ "        {\r\n"
				+ "            \"key\":\"RES\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"Creditcard Defect\",\r\n"
				+ "        \"description\": \"creating my  bug\",\r\n"
				+ "        \"issuetype\":\r\n"
				+ "        {\r\n"
				+ "            \"name\":\"Bug\"\r\n"
				+ "         }\r\n"
				+ "    }\r\n"
				+ "}").log().all().filter(session).when().post("rest/api/2/issue").
		then().log().all().extract().response().asString();
	     
	     System.out.println(responce);
	}

}
