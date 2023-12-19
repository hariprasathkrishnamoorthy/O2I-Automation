package com.sample;

import com.managers.FileReaderManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ReadExcel {

@Test
    public void readExcelData() {
        Map<String, String> dataMap = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(FileReaderManager.getJsonConfigReader().getJsonString("ExcelOperations.InputExcelFilePath"));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            for (Row row : sheet) {
                if (!row.getCell(3).getStringCellValue().equalsIgnoreCase("New") ) {
                    continue; // Skip the header row
                }

                for (Cell cell : row) {

                    int columnIndex = cell.getColumnIndex();
                    String header = headerRow.getCell(columnIndex).getStringCellValue();
                    String value = cell.getStringCellValue();
                    dataMap.put(header, value);
                }
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }




    for (String value : dataMap.values()) {
        System.out.println(value);
    }
    String Arrived = "Arrived";
    String Date = "MR No.";
    double arr = Double.parseDouble(dataMap.get(Arrived));
           System.out.println();
           System.out.println(dataMap.get(Date));
    java.util.Date date = DateUtil.getJavaDate(arr);
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String s = sdf.format(date);
    System.out.println(s);
    }
}
