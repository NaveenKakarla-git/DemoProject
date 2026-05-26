package Demoproject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequest;
import pojo.LoginResponce;
import pojo.OrderDetails;
import pojo.Orders;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//import org.testng.Assert;

public class EcommerceAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("naveenkakarla20@gmail.com");
		loginRequest.setUserPassword("Naveenkakarla8@");
		
		
		RequestSpecification reqLogin = given().log().all().spec(req).body(loginRequest);
		LoginResponce loginResponce = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract().response()
				.as(LoginResponce.class);
		
		System.out.println(loginResponce.getToken());
		String Token=loginResponce.getToken();
		System.out.println(loginResponce.getUserId());
		String UserId = loginResponce.getUserId();
		
		
		//Add Product
		
		
		RequestSpecification addproductbasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addHeader("authorization",Token ).build();
		
		RequestSpecification Addproduct = given().log().all().spec(addproductbasereq).param("productName", "Laptop")
				.param("productAddedBy",UserId)
		.param("productCategory", "shirts").param("productSubCategory", "design")
		.param("productPrice", "11500").param("productDescription", "HP").param("productFor", "men")
		.multiPart("productImage",new File("\\Users\\Naveen Kakarla\\Downloads//ocean-3605547__480.jpg"));
		
		
		String AddProductresponce =Addproduct.when().post("/api/ecom/product/add-product").then().log().all()
		.extract().response().asString();
		JsonPath js = new JsonPath(AddProductresponce);
		String productId = js.get("productId");
		
		
		//Create Order
		
		RequestSpecification CreateOrderbasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addHeader("authorization",Token ).build();
		
		OrderDetails orderDetail = new OrderDetails();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(productId);
		
		List<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
		orderDetailList.add(orderDetail);	
		Orders orders = new Orders();
		orders.setOrders(orderDetailList);
		
		RequestSpecification createOrderReq=given().log().all().spec(CreateOrderbasereq).body(orders);

		String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order")
				.then().log().all().extract().response().asString();
		
		System.out.println(responseAddOrder);



		//Delete Product

	//	RequestSpecification deleteProdBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
	//	.addHeader("authorization", Token).setContentType(ContentType.JSON)
	//	.build();

	//	RequestSpecification deleteProdReq =given().log().all().spec(deleteProdBaseReq).pathParam("productId",productId);
//
	//	String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().
	//	extract().response().asString();

	//	JsonPath js1 = new JsonPath(deleteProductResponse);
     //   Assert.assertEquals("Product Deleted Successfully",js1.get("message"));
//
		
		
		
		
		

	}

}
