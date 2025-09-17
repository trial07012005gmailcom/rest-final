package services;

public package services;

import io.restassured.response.Response;

public class AuthService extends BaseApiService {

    public Response createToken(String username, String password) {
        return getRequestSpec()
                .body(String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password))
                .when()
                .post("/auth");
    }
} {
    
}
