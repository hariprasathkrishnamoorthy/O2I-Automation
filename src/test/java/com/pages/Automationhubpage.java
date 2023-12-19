package com.pages;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Automationhubpage
{
    public  Map<String, String>  readwf2Data() throws IOException {
        String excelFilePath = System.getProperty("user.dir")+File.separator+"Downloads\\GridData\\PatientRelatedTask_WF1.xlsx";
        int rowCounter = 0;
        Map<String, String> patientstatus = new HashMap<String, String>();

        try

        {
            Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath));
            // --Assuming data is in the first sheet
            Sheet sheet = workbook.getSheetAt(0);
            // --Stop after processing 10 rows
            for (Row row : sheet) {
                if (rowCounter >= 10) {
                    break;
                }


                Cell nameCell = row.getCell(CSVgeneratepage.getColumnIndex(sheet,"Patient Name"));
                Cell statusCell = row.getCell(CSVgeneratepage.getColumnIndex(sheet,"Status"));

                if (nameCell != null && statusCell != null  && rowCounter!=0 ) {

                    String name = nameCell.toString();
                    String status=statusCell.toString();
                    patientstatus.put(name, status);

                }
                rowCounter++;
            }

            System.out.println("Rows processed: " + rowCounter);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return patientstatus;
    }

    public Map<String, String> readapiData()
    {
        Map<String, String> patientstatusapi = new HashMap<String, String>();


        String apiUrl = "https://api-qa.e5.ai/forms/taskrun?$sort[created_at]=-1&runStatus=Completed&task=61273e733d0216002e89b643&$limit=1";

        // Send a GET request to the API and store the response
        Response response = RestAssured.get(apiUrl);
        String body =response.getBody().asString();

        JSONParser parser = new JSONParser();
        try {
            // Parse the JSON response
            Object obj = parser.parse(body);

            // Assuming the top-level object is a JSONObject
            JSONObject jsonObject = (JSONObject) obj;

            // Extract the "data" array
            JSONArray dataArray = (JSONArray) jsonObject.get("data");

            // Iterate through the "data" array and extract "Patient name" and "Validation_status"
            for (Object dataObj : dataArray) {
                JSONObject dataObject = (JSONObject) dataObj;
                String patientName = (String) dataObject.get("Patient name");
                String validationStatus = (String) dataObject.get("Validation_status");

                // Add the extracted data to the HashMap
                patientstatusapi.put(patientName, validationStatus);
            }
        } catch (ParseException e) {
            System.out.println("Error parsing JSON response: " + e.getMessage());
        }

        return patientstatusapi;

    }

    public boolean compareData(Map<String, String> actualMap , Map<String, String> expectedMap)
    {
        // Iterate through the actualMap and compare the status for each patient
        for (Map.Entry<String, String> entry : actualMap.entrySet()) {
            String patientName = entry.getKey();
            String actualStatus = entry.getValue();
            String expectedStatus = expectedMap.get(patientName);

            // If the expectedStatus is null (patient not found in expectedMap) or the statuses don't match, return false
            if (expectedStatus == null || !expectedStatus.equals(actualStatus)) {
                return false;
            }
        }

        // All statuses matched, return true
        return true;

    }




}
