package com.itacademy;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HwTestOnJson {


    @Test
    public void getGetNoFound(){
        given().log().all().when().get("https://reqres.in//api/users/23").then().statusCode(404);
    }


    @Test
    public void getGets() {
        given().log().all().when().get("https://reqres.in/api/users?page=2").then().log().all().statusCode(200);
    }

    @Test
    public void getPost() {
        given().log().all().body("{\n" +
                "    \"email\": \"sydney@fife\"\n" +
                "}").post("https://reqres.in/api/register").then().statusCode(400);
    }

    @Test
    public void getPut() {
        Map<String, String> info = new HashMap<>();
        info.put("name", "new name1");
        info.put("job", "new job1");
        given().log().all().contentType(ContentType.JSON).body(info)
                .when().put("https://reqres.in/api/users/2").then().statusCode(200);

    }

    @Test
    public void getDelete() {
        given().log().uri()
                .when().delete("https://reqres.in/api/users/2")
                .then().statusCode(204);
    }
}
