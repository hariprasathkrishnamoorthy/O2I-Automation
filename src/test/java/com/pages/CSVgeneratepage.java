package com.pages;
import com.config.JsonConfigReader;
import com.managers.FileReaderManager;
import com.utility.UtilityClass;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;
import org.python.antlr.ast.Str;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CSVgeneratepage
{


    public static void generateCSV() throws java.io.FileNotFoundException, java.io.IOException, ParseException {
        String excelFilePath =System.getProperty("user.dir")+ File.separator+"Downloads\\GridData\\PatientRelatedTask_WF1.xlsx";
        String zipFilePath =  System.getProperty("user.dir")+File.separator+"XMLFiles.zip";
        String csvFilePath1 = System.getProperty("user.dir")+File.separator+"CSV-FILE-1.csv";
        String csvFilePath2 = System.getProperty("user.dir")+File.separator+"CSV-FILE-2.csv";
        String csvHeader1 = "Agency Name,Agency ID (FAC_ID),City/State,Report Period,Report Run Date,Submission Date/Time,Submission ID,Submitter User ID,Submission File Name,Submission File Status,Completion Date/TimeName,Total Records Processed,Accepted Records,Rejected Records,Duplicate Records,# Production Records Submitted Without Agency Authority,Total # of messages,Record,Status,Name,XML Filename,Name(M0040),SSN (M0064),Medicare Number(M0063),Res_int_ID,RFABranch ID,Asmt ID,Correction Num,M0090 Date,Eff Date,Type of Transaction,OASIS Item(s),Data Submitted,Message Number/Severity,Message";
        String csvHeader2="Column1,Patient Name,MR No.,Payor Type,Payor Source,Type,NPI,Visit Date,Version";
        JsonConfigReader jsreader=FileReaderManager.getJsonConfigReader("CSV_Template.json");

//
//        //Load from Template for CSV1 columns-----
          String Agency_Name = jsreader.getJsonString("CSV1.Agency_Name");
          System.out.println(Agency_Name);
//        String Agency_ID_FAC_ID= FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Agency ID (FAC_ID)");
//        String City_State =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.City/State");
//        String Report_Period =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Report Period");
//        String Report_Run_Date =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Report Run Date");
//        String Submission_Time =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Submission Date/Time");
//        String Submission_ID =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Submission ID");
//        String Submitter_Use_ID =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Submitter User ID");
//        String Submission_File_name =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Submission File Name");
//        String Submission_File_Status =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Submission File Status");
//        String Completion_TimeName =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Completion Date/TimeName");
//        String Total_Records_Processed =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Total Records Processed");
//        String Accepted_Records =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Accepted Records");
//        String Rejected_Records =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Rejected Records");
//        String Duplicate_Records =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Duplicate Records");
//        String Production_Authority =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.# Production Records Submitted Without Agency Authority");
//        String Total_messages =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Total # of messages");
//        String Record =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Record");
//        String Status =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Status");
//        String SSN_M0064 =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.SSN (M0064)");
//        String Medicare_NumberM0063 =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Medicare Number(M0063)");
//        String Res_int_ID =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Res_int_ID");
//        String Asmt_ID =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Asmt ID");
//        String Correction_Num =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Correction Num");
//        String Eff_Date =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Eff Date");
//        String Type_of_Transaction =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Type of Transaction");
//        String OASIS_Item =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.OASIS Item(s)");
//        String Data_Submitted =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Data Submitted");
//        String Message_Number_Severity =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Message Number/Severity");
//        String Message =FileReaderManager.getJsonConfigReader().getJsonString("CSV1.Message");
//
//        //Load from Template for CSV2 columns------
//
//        String Column1 =FileReaderManager.getJsonConfigReader().getJsonString("CSV2.Column1");
//        String Payor_Type  =FileReaderManager.getJsonConfigReader().getJsonString("CSV2.Payor Type");
//        String Payor_Source  =FileReaderManager.getJsonConfigReader().getJsonString("CSV2.Payor Source");
//        String NPI  =FileReaderManager.getJsonConfigReader().getJsonString("CSV2.NPI");
//        String Version  =FileReaderManager.getJsonConfigReader().getJsonString("CSV2.Version");



        int rowCounter = 1;
        int rowcount =Integer.parseInt(FileReaderManager.getJsonConfigReader().getJsonString("HCHB.Rowcount"))-1 ;
        List<String> fileNames = getFileNamesInZip(zipFilePath);
        Collections.sort(fileNames, new NumericValueComparator());

        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath));
             BufferedWriter csvWriter1 = new BufferedWriter(new FileWriter(csvFilePath1)) ;
             BufferedWriter csvWriter2 = new BufferedWriter(new FileWriter(csvFilePath2)) )
        {
            //--Assuming data is in the first sheet
            Sheet sheet = workbook.getSheetAt(0);
            // int rowCount=sheet.getLastRowNum();
            //--Write header to CSV
            csvWriter1.write(csvHeader1);
            csvWriter1.newLine();
            csvWriter2.write(csvHeader2);
            csvWriter2.newLine();

            //--Process rows specified
            for (int rowIndex = 1; rowIndex <=rowcount; rowIndex++) {


                Row row = sheet.getRow(rowIndex);
                Cell dateCell = row.getCell(getColumnIndex(sheet,"Visit Date"));
                Cell typeCell = row.getCell(getColumnIndex(sheet,"Type"));
                Cell nameCell = row.getCell(getColumnIndex(sheet,"Patient Name"));
                Cell MRno =  row.getCell(getColumnIndex(sheet,"MR No."));

                if (dateCell != null && typeCell != null && nameCell != null ) {
                    String date = getInputDateStr(dateCell.getDateCellValue());
                    String datewithtime = getInputDateStrwithtime(dateCell.getDateCellValue());
                    String type = typeCell.toString();
                    String name = nameCell.toString();
                    String MR = MRno.getStringCellValue();
                    String status = "Accepted";
                    String RFAid = getRfaidfromtype(type);
                    String xmlFileName= fileNames.get(rowIndex-1);
                    String Empty="";

                    // Write selected data to CSV
                    csvWriter1.write(Empty + "," + Empty + "," + Empty + "," + Empty + "," + Empty + "," + Empty + "," +"59385399"+","
                            + Empty + ","+ Empty + ","+ Empty + ","+ Empty + "," + Empty + ","+ Empty + ","+ Empty + ","+ Empty + ","+ Empty + ","+ Empty + ","+ Empty + ","+ status + ","  + formatCSVField(name) + ","+ xmlFileName + "," + formatCSVField(removeLastNameInitial(name)) + "," + Empty + ","+ Empty + ","+ Empty + "," + RFAid + ","
                            + Empty + ","+ Empty + ","+ date +","+ Empty + ","+ Empty + ","+ Empty + ","+ Empty + ","+ Empty + ","+ Empty);
                    csvWriter1.newLine();


                    csvWriter2.write("False"+ "," +formatCSVField(name)+ ","+MR+","+Empty+","+Empty+","+type+","+"1790125987"+","+datewithtime+","+Empty);
                    csvWriter2.newLine();


                    rowCounter++;

                }
            }

            System.out.println("Rows processed: " + rowCounter);

        }




    }


    public static String getRfaidfromtype(String type) {
        String typeval = type.toUpperCase();

        return switch (typeval) {
            case "SOC" -> "01";
            case "RESUMPTION OF CARE" -> "03";
            case "RECERT" -> "04";
            case "FOLLOW UP" -> "05";
            case "TRANSFER TO INPATIENT FACILITY" -> "06";
            case "DISCHARGE" -> "09";
            default -> "00";
        };
    }

    private static String formatCSVField(String field)
    {
        if (field.contains(",")) {
            return "\"" + field + "\"";
        }
        return field;
    }



    public static int getColumnIndex(Sheet sheet, String columnName)
    {
        Row firstRow = sheet.getRow(0); // Assuming the column names are in the first row
        for (int i = 0; i < firstRow.getLastCellNum(); i++) {
            Cell cell = firstRow.getCell(i);
            if (cell != null && cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                return i; // Found the column index
            }
        }
        return -1; // Column not found
    }



    public static String getInputDateStr(Date inputDateStr)
    {

        // Create a SimpleDateFormat object to format the output as "yyyyMMdd"
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        String outputDateStr="";

        // Parse the input date string
        // Format the date as "yyyyMMdd"
        outputDateStr = outputDateFormat.format(inputDateStr);

        // Print the formatted date
        System.out.println("Formatted Date: " + outputDateStr);

        return outputDateStr;
    }


    public static String getInputDateStrwithtime(Date inputDateStr)
    {

        // Create a SimpleDateFormat object to format the output as "yyyyMMdd"
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        String outputDateStr="";

        // Parse the input date string
        // Format the date as "yyyyMMdd"
        outputDateStr = outputDateFormat.format(inputDateStr);

        // Print the formatted date
        System.out.println("Formatted Date: " + outputDateStr);

        return outputDateStr;
    }



    private static String removeLastNameInitial(String fullName) {
        // Check if the name contains a comma
        int commaIndex = fullName.indexOf(",");
        if (commaIndex != -1) {
            // Extract the part before the comma
            String lastNamePart = fullName.substring(0, commaIndex + 1);

            // Remove any trailing whitespace
            lastNamePart = lastNamePart.trim();

            // Check if the last part is an initial
            int lastSpaceIndex = fullName.lastIndexOf(" ");
            if (lastSpaceIndex != -1 && lastSpaceIndex > commaIndex) {
                String lastPart = fullName.substring(lastSpaceIndex + 1).trim();
                if (lastPart.length() == 2 && Character.isLetter(lastPart.charAt(0)) && lastPart.charAt(1) == '.') {
                    // If the last part is an initial, remove it
                    return lastNamePart + fullName.substring(commaIndex + 1, lastSpaceIndex).trim();
                }
            }

            // If no initial is found, return the original name
            return fullName;
        } else {
            // Return the original name if there is no comma
            return fullName;
        }
    }




    private static List<String> getFileNamesInZip(String zipFilePath) throws IOException {
        List<String> fileNames = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(zipFilePath);
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                // Skip directories
                if (!entry.isDirectory()) {
                    // Add file name to the list
                    fileNames.add(entry.getName());
                }

                // Close the current entry
                zis.closeEntry();
            }
        }

        return fileNames;
    }


    private static class NumericValueComparator implements Comparator<String> {

        public int compare(String str1, String str2) {
            int value1 = extractNumericValue(str1);
            int value2 = extractNumericValue(str2);

            // Compare based on the extracted numeric values
            return Integer.compare(value1, value2);
        }

        private int extractNumericValue(String str) {
            // Use a regular expression to extract the numeric value
            String regex = "OASIS_PatName_val_(\\d+)_";
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
            java.util.regex.Matcher matcher = pattern.matcher(str);

            if (matcher.find()) {
                return Integer.parseInt(matcher.group(1));
            } else {
                // Return a default value if not found
                return 0;
            }
        }


    }


}
