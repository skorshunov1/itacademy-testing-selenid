package com.itacademy;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class TestsOnJson {
    @Test
    public void createUserIt() {
        given().log().all().body("{\n" +
                        "\"name\": \"morpheus\",\n" +
                        " \"job\": \"leader\"\n" +
                        "}")
                .when().post("https://reqres.in/api/users")
                .then().log().body().statusCode(201);
    }

    @Test
    public void updateUserIts() {
        Map<String, String> bodyInfo = new HashMap<>();
        bodyInfo.put("name", "new name");
        bodyInfo.put("job", "new job");
        given().log().all().contentType(ContentType.JSON).body(bodyInfo)
                .when().patch("https://reqres.in/api/users/1")
                .then().log().body().statusCode(200);
    }

    @Test
    public void deleteUsersIt() {
        given().log().uri()
                .when().delete("https://reqres.in/api/users/2")
                .then().statusCode(204);
    }

    @Test
    public void validateXML() {
        Response response = given()
                .get("https://mocktarget.apigee.net/xml")
                .then().statusCode(200).log().all().contentType(ContentType.XML).extract().response();
        System.out.println(response.xmlPath().getString("root.city"));
        XmlPath xmlPath = new XmlPath(response.asString());
    }

    @Test
    public void testJsonSchemaIT() {
        RestAssured.baseURI = "https://reqres.in";
        given().log().all()
                .when().get("/api/users/2")
                .then().log().body().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschema.json"));
    }

    @Test
    public void updateUserIt() {
        String name = "new name";
        Map<String, String> bodyInfo = new HashMap<>();
        bodyInfo.put("name", name);
        bodyInfo.put("job", "new job");
        String responseName = given().log().all().contentType(ContentType.JSON).body(bodyInfo)
                .when().put("https://reqres.in/api/users/1")
                .jsonPath().getString("name");
        assertEquals(name, responseName);
    }

    @Test
    public void testXMLSchemaIT() {
        given()
                .get("https://mocktarget.apigee.net/xml")
                .then().log().all().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xml.xsd"));
    }
}
