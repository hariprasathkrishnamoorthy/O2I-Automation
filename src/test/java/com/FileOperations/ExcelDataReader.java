package com.FileOperations;

import com.managers.FileReaderManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelDataReader {

    public Map<String, String> readDataFromExcel() {

        Map<String, String> excelDataMap = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(FileReaderManager.getJsonConfigReader().getJsonString("ExcelOperations.InputExcelFilePath"));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            for (Row row : sheet) {
                if (!row.getCell(3).getStringCellValue().equalsIgnoreCase("New")) {
                    continue; // Skip the header row
                }

                for (Cell cell : row) {

                    int columnIndex = cell.getColumnIndex();
                    String header = headerRow.getCell(columnIndex).getStringCellValue();
                    String value = cell.getStringCellValue();
                    excelDataMap.put(header, value);
                }
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return excelDataMap;
    }
}
