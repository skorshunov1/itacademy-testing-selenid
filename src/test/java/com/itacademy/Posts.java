package com.itacademy;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Posts {


    @Test(priority = 1)
    public void createUserPojo() {
        User user = new User();
        user.setName("pojo");
        user.setJob("pojo");
        user.setName("pojo-it");
        user.setJob("job-it");
        RestAssured.baseURI = "https://reqres.in";
        given().contentType("application/json").log().uri()
                .body(user)
                .when().post("/api/users")
                .then().statusCode(201).log().all()
                .header("Content-Type", "application/json; charset=utf-8");
    }
}

