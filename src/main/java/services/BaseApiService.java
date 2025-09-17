package services;
   
   import io.restassured.RestAssured;
   import io.restassured.specification.RequestSpecification;
   
   public class BaseApiService {
       protected static final String BASE_URI = "https://restful-booker.herokuapp.com";
       
       static {
           RestAssured.baseURI = BASE_URI;
       }
       
       protected RequestSpecification getRequestSpec() {
           return RestAssured.given()
                   .header("Content-Type", "application/json")
                   .header("Accept", "application/json");
       }
       
       protected RequestSpecification getRequestSpecWithAuth(String token) {
           return RestAssured.given()
                   .header("Content-Type", "application/json")
                   .header("Accept", "application/json")
                   .header("Cookie", "token=" + token);
       }
   }