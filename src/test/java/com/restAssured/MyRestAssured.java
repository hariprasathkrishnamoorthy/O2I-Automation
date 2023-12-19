package com.restAssured;

import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//given() cookies, auth, headers
//when() all the request
//then()validate the response
public class MyRestAssured {

public static HashMap map = new HashMap();

    private static final String BASE_URL = "https://api-qa.e5.ai";
    static String cookie = "test";

    public void loginWithAPI(){

        map.put("username", "botadmin@empath.com");
        map.put("password", "Test123$");


        cookie ="connect.sid=" + String.valueOf(RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .body(map)
                .post("https://api-qa.e5.ai/login")
                .then()
                .statusCode(201)
                .extract()
                .response()
                        .cookies().get("connect.sid"));

    }@Test(priority = 1)
    public void getScratchpadData()
    {
        RestAssured.baseURI = BASE_URL;
        ResponseBody responebody = (ResponseBody) given()
                .header("cookie", cookie)
                .contentType(ContentType.JSON)
                .when()
                .get("https://api-qa.e5.ai/scratch-pad/empath_rcd_qa");




        String bodyAsString = responebody.asString();
        System.out.println(bodyAsString);

    }







    }




