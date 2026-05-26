package Demoproject;
import static io.restassured.RestAssured.given;



//import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
//import pojo.GetCourse;
public class oAuthtest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWgavdeZ23RdNAJU9p6xyU-mfsm6H3KUbAWpR5GPI9RUlJlcI4yKf_MQoMiEQLV4aLuZKQ&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		
		
		String partialcode = url.split("code=")[1];
		
		String code = partialcode.split("&scope")[0];
		
		System.out.println(code);
		
		 String responce = given().urlEncodingEnabled(false)
        .queryParam("code",code)
        .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
        .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
        .queryParams("grant_type", "authorization_code")
        .queryParams("state", "verifyfjdss")
        .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
        .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
        .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
        .when().log().all()
        .post("https://www.googleapis.com/oauth2/v4/token").asString(); 
		 
		 System.out.println(responce);

		
		JsonPath jsonpath = new JsonPath(responce);
		String accessToken = jsonpath.getString("access_Token");
		
		System.out.println(accessToken);
		
		String responce1 = given().urlEncodingEnabled(false)
				.queryParam("access_token","ya29.a0AX9GBdWD6aa4ZYUkQHG5zbnjRmSmMNesH3AzzqPo9mS5FI18dEhy0GQnO0SalIAKYNHmUPWWeZ5s7v8lXTNfLVlorGZdqBZA11IZrmy4spwwUOx7Gn3rDeN-b7uMOOarTpwqX8Jet7ej-z8fbXZKW9-lC-gUWAaCgYKASISARASFQHUCsbCmUwhpS65EfASViemO4fnuA0165")
				.when()
		.get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		
		
		System.out.println(responce1);
		
	}

}
