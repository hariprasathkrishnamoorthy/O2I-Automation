package com.FileOperations;

import com.managers.FileReaderManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class ExcelDataFetch {

    public ArrayList<String> FetchingDataFromExcel() {

        ArrayList<String> filterDataList = null;
        try {

            File file = new File(FileReaderManager.getJsonConfigReader().getJsonString("ExcelOperations.InputExcelFilePath"));
            FileInputStream fis = new FileInputStream(file);
            Workbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(0);

            for (Row row : sheet) { // For each Row.

                filterDataList = new ArrayList<String>();

                int statuscolumnIndex = Integer.parseInt(FileReaderManager.getJsonConfigReader().getJsonString("ExcelOperations.StatusColumnIndex"));
                Cell cell = row.getCell(statuscolumnIndex); // Get the Cell at the Index / Column you want.
                if (cell.getStringCellValue().equalsIgnoreCase("New"))
                {
                    for (int i = 0; i < cell.getRow().getLastCellNum() - 1; i++) {
                        if (cell.getRow().getCell(i).getCellType() == CellType.NUMERIC) {
                            long a = (long) cell.getRow().getCell(i).getNumericCellValue();
                            filterDataList.add(String.valueOf(a));

                        }

                        if (cell.getRow().getCell(i).getCellType() == CellType.STRING) {
                            filterDataList.add(cell.getRow().getCell(i).getStringCellValue());


                        }


                    }
                    break;

                }
            }

        } catch (Exception e) {
             System.out.println(e);
        }
        return filterDataList;


    }}
