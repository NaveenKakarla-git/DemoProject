package Demoproject;

import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;






public class oAuthtest1 {



public static void main(String[] args) throws InterruptedException {

// TODO Auto-generated method stub

String url ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWgavdeRj6sBq3IOsnb4jIDzb863ejgQwjT79K_Vxq7tGIMDCrQaNs4yeh7z74czNwXpPw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";


String partialcode=url.split("code=")[1];

String code=partialcode.split("&scope")[0];

System.out.println(code);

String response = given() .urlEncodingEnabled(false)
                  .queryParams("code",code)
                  .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                  .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                  .queryParams("grant_type", "authorization_code")
                  .queryParams("state", "verifyfjdss")
                  .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                  .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
                  .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                  .when().log().all()
                  .post("https://www.googleapis.com/oauth2/v4/token").asString(); 

                System.out.println(response);
                
                JsonPath jsonPath = new JsonPath(response);
                String accessToken = jsonPath.getString("access_token");

                System.out.println(accessToken);
                
                
                //String responce2 =    given().contentType("application/json")
                //		.queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
                //		.when().get("https://rahulshettyacademy.com/getCourse.php").asString();

                
                
                GetCourse gc =    given().contentType("application/json").urlEncodingEnabled(false)
                		.queryParams("access_token", "ya29.a0AX9GBdUwTOix7VuvHx0y_yT69fURHu-pveNwm-p3JwDirHDWp8yrJQlXh2bmDpZLPJC2m4oZV6ztb5i5pSeM3fQvdYAowjbc-k4vDrSDh7biOOSq6Hwq5bOwqpPPE7LpO7mqnez0Uy17-OkF3fwEjiK-O5xbBQaCgYKAQESARASFQHUCsbCN7BbALxoUGq3CKvRRlugZQ0165")
                				.expect().defaultParser(Parser.JSON)
                		.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

              //  System.out.println(responce2);
               System.out.println(gc.getLinkedIn());
               System.out.println(gc.getInstructor());
              
              System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
              
              //API course details
              
              List<Api> apiCourses = gc.getCourses().getApi();
              for(int i=0;i<apiCourses.size();i++)
              {
            	  if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"));
            			  {
            				 System.out.println(apiCourses.get(i).getPrice());
            			  }
              }
              
              //web Automation course details
              
              List<WebAutomation> w = gc.getCourses().getWebAutomation();
              for(int j=0;j<w.size();j++)
              {
            	System.out.println(w.get(j).getCourseTitle());
              }
                		

}	           

                		

}         		



                	


                  

                  


               

                   
                        

                       

                       

                        
                      

                       

                       

                        
 



