package com.itacademy;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BearerAuth {

    String token;
     @Test
     public void login(){
         Response response = given().contentType(ContentType.JSON).body("{\"username\":\"emilys\",\"password\":\"emilyspass\",  \"expiresInMins\": 30}")
                 .when().post("https://dummyjson.com/auth/login")
                 .then().log().body().statusCode(200).extract().response();
     token = response.jsonPath().getString("token");
     }

    @Test
    public void getUser(){
        given().header("Authorization","Bearer" + token).when().get("https://dummyjson.com/auth/me").then().statusCode(200);
    }
}
