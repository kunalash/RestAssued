package com.demo.Helpers.specificationHelper;

import com.demo.Constants.Endpoints;
import com.demo.Model.Token.Token;
import com.demo.Utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Specification {
    private static final String BASE_URL = ConfigManager.getInstance().getConfigValue("baseUrl");
    private static final String AUTH_USER = ConfigManager.getInstance().getConfigValue("AuthUser");
    private static final String AUTH_PASSWORD = ConfigManager.getInstance().getConfigValue("AuthPassword");

    public static RequestSpecification getRequestSpec(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(BASE_URL);
        Token token = getTokenAndSpecs();
       return requestSpecBuilder.addHeader("Authorization","Bearer "+token.getResponse().jsonPath().getString("access_token"))
               .addCookies(token.getResponse().cookies())
               .build();
    }

    private static Token getTokenAndSpecs(){
        final Response response =
                RestAssured.given().auth().basic(AUTH_USER, AUTH_PASSWORD).post(BASE_URL + Endpoints.CLIENT_CREDENTIALS);
        Token token = new Token();

        token.setResponse(response);
        return token;
    }

    private Specification(){}

}
