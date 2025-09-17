package Utils;

import Constants.BookingEndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Request {

    public static Response get(String endpoint){
        RestAssured.baseURI = BookingEndPoints.BASE_URL;
        Response response = RestAssured.when().get(endpoint);
        response.then().log().body();
        return response;
    }

    public static Response getById(String endpoint, String id){
        RestAssured.baseURI = BookingEndPoints.BASE_URL;
        Response response = RestAssured.given().pathParam("id", id)
                .when().get(endpoint);
        response.then().log().body();
        return response;
    }

    public static Response post(String endpoint, String payload){
        RestAssured.baseURI = BookingEndPoints.BASE_URL;
        Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .header("Accept", "application/json")
                .header("Connection", "keep-alive")
                .body(payload)
                .when().post(endpoint);

        response.then().log().body();
        return response;
    }
}