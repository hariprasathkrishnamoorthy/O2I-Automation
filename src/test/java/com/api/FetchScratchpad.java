package com.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.managers.FileReaderManager;
import com.pojo.AuthorizationRequest;
import com.pojo.MainPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Map;

public class FetchScratchpad {

    static AuthorizationRequest authRequest;
    private static final String BASE_URL;
    static String cookie = null;
    static Response response;


    static {
        try {

            authRequest = new AuthorizationRequest(FileReaderManager.getJsonConfigReader().getJsonString("Scratchpad.Username"), FileReaderManager.getJsonConfigReader().getJsonString("Scratchpad.Password"));
            BASE_URL = FileReaderManager.getJsonConfigReader().getJsonString("Scratchpad.BaseURL");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }




    public static void authenticateUser(AuthorizationRequest authRequest) {
        RestAssured.baseURI = BASE_URL;
        cookie = "connect.sid=" + String.valueOf(RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when()
                .body(authRequest)
                .post("/login")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .cookies().get("connect.sid"));




    }
    public static void SearchDataFromAPI(String PatientName) throws IOException, ParseException {
         RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest;


        if (cookie == null) {
            authenticateUser(authRequest);
        }
        httpRequest = RestAssured.given().header("cookie", cookie).contentType(ContentType.JSON);
        response = httpRequest.queryParam("patientName", PatientName)
                .queryParam("$limit", FileReaderManager.getJsonConfigReader().getJsonString("Scratchpad.Limit"))
                .get(FileReaderManager.getJsonConfigReader().getJsonString("Scratchpad.IndexName"));

        int StatusCode = response.getStatusCode();

        if (StatusCode == 500){
            authenticateUser(authRequest);
            httpRequest = RestAssured.given().header("cookie", cookie).contentType(ContentType.JSON);
            response = httpRequest.queryParam("patientName", PatientName).queryParam("$limit", FileReaderManager.getJsonConfigReader().getJsonString("Scratchpad.Limit")).get(FileReaderManager.getJsonConfigReader().getJsonString("Scratchpad.IndexName"));
            String responseString = response.asString();
            System.out.println(responseString);


        }


    }

    public static MainPojo SetDataFromAPIToScratchpadPojo()
    {
        String responseString = response.asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonPath jsonPath = JsonPath.from(responseString);
        Map<String, String> scratchpadDataAsMap = jsonPath.get("match[0]._source");
        MainPojo mainPojo = mapper.convertValue(scratchpadDataAsMap, MainPojo.class);
        return mainPojo;


    }

}
