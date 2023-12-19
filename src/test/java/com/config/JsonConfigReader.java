package com.config;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

public class JsonConfigReader {


    String jsonString;
    public JsonConfigReader() throws IOException, ParseException {



        try {
            File file = new File(System.getProperty("user.dir") + "\\AutomationConfig\\TestAutomationConfig.json");
             jsonString = new String(Files.readAllBytes(file.toPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public JsonConfigReader(String filename) throws IOException, ParseException {



        try {
            File file = new File(System.getProperty("user.dir") + "\\AutomationConfig\\"+filename);
            jsonString = new String(Files.readAllBytes(file.toPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public String getJsonString(String stringJsonPath) throws IOException, ParseException {

        return JsonPath.read(jsonString,stringJsonPath );

    }
    public int getJsonArrayCount(String stringJsonPath){
        int jsonArray = JsonPath.read(jsonString, stringJsonPath);
        return jsonArray;

    }
    public String [] getJsonArray(String stringJsonPath){
        String[] jsonArray = JsonPath.read(jsonString, stringJsonPath);
        return jsonArray;

    }



}
