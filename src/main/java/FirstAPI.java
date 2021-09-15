import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class FirstAPI {

	

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("KEY", "qaclick123")
		.header("Content-Type","application/json")
		.body(PayLoad.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
				.header("server", "Apache/2.4.18 (Ubuntu)").extract().asString();
		
		System.out.println("Response = " + response);
		
		JsonPath js = new JsonPath(response);
		String placeId=js.getString("place_id");
		System.out.println("*******"+placeId+"********");
		
		
		//Update Place
		
		given().log().all().queryParam("KEY", "qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\"70 Summer walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").put("maps/api/place/update/json")
		.then().assertThat().log().all().(200).body("msg", equalTo("Address successfully updated"));
		
		
		

		

	}

}
