package com.demo.Helpers.Site;

import com.demo.Constants.Endpoints;
import com.demo.Helpers.specificationHelper.Specification;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;


public class SiteServiceHelper {

    public Response getAllMeasureType(){
        return RestAssured
                .given()
                .spec(Specification.getRequestSpec())
                .contentType(ContentType.JSON)
                .when()
                .log()
                .all()
                .get(Endpoints.GET_MEASURE_TYPE)
                .then().assertThat().statusCode(HttpStatus.SC_OK).extract().response();

    }

    public Response getAllSites(){
        return RestAssured
                .given()
                .spec(Specification.getRequestSpec())
                .contentType(ContentType.JSON)
                .when()
                .log()
                .all()
                .get(Endpoints.GET_ALL_SITES)
                .then().assertThat().statusCode(HttpStatus.SC_OK).extract().response();

    }
}
