import file.payload;
import io.restassured.path.json.JsonPath;

public class Complexjsonparse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		//Print No of courses returned by API
	int count =	js.getInt("courses.size()");
	System.out.println(count);
	
	//Print Purchase Amount
	int totalAmount = js.getInt("dashboard.purchaseAmount");
	System.out.println(totalAmount);
		
	
	//Print Title of the first course
	String titleFirstcourse = js.get("courses[0].title");
	System.out.println(titleFirstcourse);
	
	//Print All course titles and their respective Prices
	for(int i = 0;i<count;i++)
	{
		String courseTitles = js.get("courses["+i+"].title");
		System.out.println(courseTitles);   //Method1 using system.out.println separately.
		
		System.out.println(js.get("courses["+i+"].price").toString());  //Method2 using system.out.println combined
	}
	
	// Print no of copies sold by RPA Course
	System.out.println("Print no of copies sold by RPA Course");
	
	for(int i = 0;i<count;i++)
	{
		String courseTitles = js.get("courses["+i+"].title");
		if(courseTitles.equalsIgnoreCase("RPA"))
		{
			int copies = js.get("courses["+i+"].copies");
			System.out.println(copies);
			break;
		}
	}
	
	//Verify if Sum of all Course prices matches with Purchase Amount

	
	
	}

}
