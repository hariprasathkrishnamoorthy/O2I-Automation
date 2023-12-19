package com.restAssured;//package com.hchb.restAssured;
//
//import com.hchb.utility.UtilityClass;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import io.restassured.response.ResponseBody;
//import io.restassured.specification.RequestSpecification;
//
//import static io.restassured.RestAssured.given;
//
//public class EndPoints extends UtilityClass {
//
//    static AuthorizationRequest authRequest = new AuthorizationRequest(prop.getProperty("SPAUsername"), prop.getProperty("SPAPassword"));
//    private static final String BASE_URL = prop.getProperty("BaseURL");
//    static String cookie = "test";
//
//
//    public static void authenticateUser(AuthorizationRequest authRequest) {
//        RestAssured.baseURI = BASE_URL;
//        cookie = "connect.sid=" + String.valueOf(RestAssured.given()
//                .baseUri(BASE_URL)
//                .contentType(ContentType.JSON)
//                .when()
//                .body(authRequest)
//                .post("/login")
//                .then()
//                .statusCode(201)
//                .extract()
//                .response()
//                .cookies().get("connect.sid"));
//
//
//    }
//
//    public static Response GetResponse(String Endpoint, String PatientName) {
//        RestAssured.baseURI = BASE_URL;
//        Response response;
//        RequestSpecification httpRequest;
//
//        if (cookie == null) {
//            EndPoints.authenticateUser(authRequest);
//        }
//        httpRequest = RestAssured.given().header("cookie", cookie).contentType(ContentType.JSON);
//        response = httpRequest.queryParam("patientName", PatientName).queryParam("$limit", prop.getProperty("ScratchPadLimit")).get(Endpoint);
//
//        int StatusCode = response.getStatusCode();
//
//        if (StatusCode == 500) {
//            authenticateUser(authRequest);
//            httpRequest = RestAssured.given().header("cookie", cookie).contentType(ContentType.JSON);
//            response = httpRequest.queryParam("patientName",PatientName).queryParam("$limit", prop.getProperty("ScratchPadLimit")).get(Endpoint);
//        }
//
//        return response;
//
//    }
//}