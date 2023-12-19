package com.sample;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class testJson {
    String jsonString;



    public testJson() throws IOException, ParseException {


        try {
            File file = new File(System.getProperty("user.dir") + "\\AutomationConfig\\TestAutomationConfig.json");
            jsonString = new String(Files.readAllBytes(file.toPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    public int getJsonArrayCount(String stringJsonPath){
        int jsonArray = JsonPath.read(jsonString, stringJsonPath);
        return jsonArray;

    }
    @Test
    public void getJsonArray(String stringJsonPath){
        String jsonString = null;


        try {
            File file = new File(System.getProperty("user.dir") + "\\AutomationConfig\\TestAutomationConfig.json");
            jsonString = new String(Files.readAllBytes(file.toPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    
        String[] jsonArray = JsonPath.read(jsonString, stringJsonPath);

        System.out.println(jsonArray[0]);
    }
}
