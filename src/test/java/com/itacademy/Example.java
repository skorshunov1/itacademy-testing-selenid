package com.itacademy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Example {

    @Test
    public void fakerName(){
        Faker faker = new Faker();
        System.out.println(faker.name().firstName());
        System.out.println(faker.name().firstName());
        System.out.println(faker.animal().name());
    }
    @Test
    public void testCookies(){
        Map<String,String> cookies = given()
                .when().get("https://www.google.com/").getCookies();
        cookies.entrySet().stream().map(k-> k.getValue()).forEach(System.out::println);

    }
    @Test
   public void fake(){
        Date date = new Date();
        //date.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 5);
        //calendar.setTime(date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd YYYY H m s");
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }

    @Test
    public void cook() throws IOException {
        User user = new User();
        user.setName("pojo");
        user.setJob("pojo");


        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        System.out.println(json);


        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/test/resources/json/userjsons"), user);
        User user2 = objectMapper.readValue(json, User.class);
        System.out.println(user2.getName());

    }


    @Test
    public void login(){
        given().log().all().when().post("https://workout.su/login").then().statusCode(400);
    }

    @Test
    public void getPost() {
        given().log().all().body("{\n" +
                "    \"username\": \"sydney@fife\"\n" + "password \n : \"35545\"\n"+
                "}").post("https://workout.su/login").then().statusCode(400);
    }

    @Test
    public void soapTest() {
        Response response = given().contentType("application/soap+xml").body("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://www.dataaccess.com/webservicesserver/\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <web:NumberToDollars>\n" +
                        "         <web:dNum>10</web:dNum>\n" +
                        "      </web:NumberToDollars>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>")
                .when().post("https://www.dataaccess.com/webservicesserver/numberconversion.wso")
                .then().log().all().statusCode(200).extract().response();
        System.out.println(response.xmlPath().getString("Envelope.Body.NumberToDollarsResponse.NumberToDollarsResult"));
    }


    @Test
    public void firstTest(ITestContext context) {
        context.setAttribute("user_id", 5);
    }

    @Test
    public void secondTest(ITestContext context) {
        System.out.println(context.getAttribute("user_id"));
    }


}

