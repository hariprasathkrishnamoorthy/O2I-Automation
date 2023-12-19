package com.pages;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.Screen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class GenerateJsonTemplateTaskPage {

    WebDriver driver;
    Screen screen;

    Map<String, String> Datafromexcel;


    public GenerateJsonTemplateTaskPage(WebDriver driver, Screen screen) {
        this.driver = driver;
        this.screen = screen;
        this.Datafromexcel = OpenandreadData();

        PageFactory.initElements(driver, this);


    }

    public void generateConfig() throws IOException {

        String jsonFilePath = "D:\\Users\\hariprasath.k\\IdeaProjects\\HCHB\\src\\test\\java\\com\\config\\Encompass_config.json";

        // Specify the path for the output JSON file
        String outputJsonFilePath = "D:\\Users\\hariprasath.k\\IdeaProjects\\HCHB\\output_config1243new.json";


        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode jsonNode = objectMapper.readValue(new File(jsonFilePath), ObjectNode.class);

        // Read the JSON file into a JsonNode
        JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));

        // Get the "gridFilter" array
        JsonNode gridFilterArray = rootNode
                .path("Workflow")
                .path("ProcessAlertsForPatient")
                .path("gridFilter");


        String toProcess=gridFilterArray.asText();

        String outputString = toProcess.replaceAll("\\{\\{Mrno\\}}",Datafromexcel.get("Mrno") );

        ((ObjectNode) jsonNode.get("Workflow").get("ProcessAlertsForPatient")).put("gridFilter",outputString);



        // Write the updated JSON back to the file
        objectMapper.writeValue(new File(outputJsonFilePath), jsonNode);


        System.out.println("Updated JSON saved to: " + outputJsonFilePath);


    }


    public void generateTemplate() {
        try {
            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Specify the path to the JSON file
            String filePath = System.getProperty("user.dir")+File.separator+"src\\test\\java\\com\\config\\OnService_QA.json"; // Update with the actual file path

            // Read JSON from file
            File jsonFile = new File(filePath);
            ObjectNode jsonNode = objectMapper.readValue(jsonFile, ObjectNode.class);


            // Replace values
            jsonNode.put("patientName", Datafromexcel.get("Patient Name"));
            jsonNode.put("mrno", Datafromexcel.get("Mrno"));
            jsonNode.put("alertArrivedDate", Datafromexcel.get("Arrived"));
            jsonNode.put("task", Datafromexcel.get("Task"));

            // Convert the modified JSON back to a string
            String updatedJsonString = objectMapper.writeValueAsString(jsonNode);

            // Print the updated JSON
            System.out.println(updatedJsonString);

            // Create an ObjectWriter to write the JSON back to a file
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

            // Write the updated JSON to the file
            objectWriter.writeValue(jsonFile, jsonNode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public Map<String, String> OpenandreadData() {

        HashMap<String, String> Patdata = new HashMap<String, String>();
        try {
            // Specify the path to your Excel file
            String excelFilePath = System.getProperty("user.dir")+File.separator+"Downloads\\GridData\\PatientRelatedTask_HCHB.xlsx";

            // Create a FileInputStream to read the Excel file
            FileInputStream fis = new FileInputStream(excelFilePath);

            // Create a Workbook instance for the Excel file (XLSX format)
            Workbook workbook = new XSSFWorkbook(fis);

            // Get the first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is on the first sheet (index 0)

            // Find the header row that contains column names
            Row headerRow = sheet.getRow(0);

            // Initialize variables to store column indices
            int patientNameColumnIndex = -1;
            int taskColumnIndex = -1;
            int stageColumnIndex = -1;
            int arrivedColumnIndex = -1;
            int mRNOIndex = -1;


            // Loop through the cells in the header row to find the specified columns
            for (int columnIndex = 0; columnIndex < headerRow.getLastCellNum(); columnIndex++) {
                Cell cell = headerRow.getCell(columnIndex);
                String cellValue = cell.getStringCellValue().trim();

                if (cellValue.equalsIgnoreCase("Patient Name")) {
                    patientNameColumnIndex = columnIndex;
                } else if (cellValue.equalsIgnoreCase("Task")) {
                    taskColumnIndex = columnIndex;
                } else if (cellValue.equalsIgnoreCase("Stage")) {
                    stageColumnIndex = columnIndex;
                } else if (cellValue.equalsIgnoreCase("Arrived")) {
                    arrivedColumnIndex = columnIndex;
                } else if (cellValue.equalsIgnoreCase("MR No.")) {
                    mRNOIndex = columnIndex;
                }
            }

            // Check if all columns were found
            if (patientNameColumnIndex != -1 && taskColumnIndex != -1 && stageColumnIndex != -1 && arrivedColumnIndex != -1 && mRNOIndex != -1) {
                // Now, you can process the data in subsequent rows using these column indices
                // For example, you can iterate through rows and get values from these columns
                for (int rowIndex = 1; rowIndex <= 1; rowIndex++) {
                    Row dataRow = sheet.getRow(rowIndex);
                    String patientName = getCellValueAsString(dataRow.getCell(patientNameColumnIndex));
                    String task = getCellValueAsString(dataRow.getCell(taskColumnIndex));
                    String stage = getCellValueAsString(dataRow.getCell(stageColumnIndex));
                    String arrived = getCellValueAsString(dataRow.getCell(arrivedColumnIndex));
                    String mrno = getCellValueAsString(dataRow.getCell(mRNOIndex));


                    // Do something with the extracted data
                    Patdata.put("Patient Name", patientName);
                    Patdata.put("Task", task);
                    Patdata.put("Stage", stage);
                    Patdata.put("Arrived", arrived);
                    Patdata.put("Mrno", mrno);
                }
            } else {
                System.out.println("One or more columns not found in the header row.");
            }

            // Close the workbook and FileInputStream
            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return Patdata;

    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            // Handle numeric cells, convert them to string
            if (DateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                // Format the date as "MM/dd/yyyy HH:mm:ss" and return it as a string
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                return dateFormat.format(date);
            } else {
                return ""; // Handle other cell types as needed
            }
        }

        return "";
    }


}