package com.sample;

import java.io.*;

import com.managers.FileReaderManager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.Test;

public class ExcelWriter {

    public static void CreatingNewExcelFile() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell1 = row.createCell(0);
        XSSFCell cell2 = row.createCell(1);
        cell1.setCellValue("Value1");
        cell2.setCellValue("test2");
        FileOutputStream fileOut = new FileOutputStream("example.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }



    public void ReadingExcelSheet() throws IOException {

        FileInputStream file = new FileInputStream("D:\\Users\\pradeep\\IdeaProjects\\qa-regression-suite\\Downloads\\GridDatapatientData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        XSSFCell cell = row.getCell(0);
       System.out.println(cell);
    }


    @Test
    public void CopyingExcelData() throws IOException {

        try {

            File fileTarget = new File(FileReaderManager.getJsonConfigReader().getJsonString("ExcelOperations.InputExcelFilePath"));
            File fileSource = new File(FileReaderManager.getJsonConfigReader().getJsonString("ExcelOperations.GridExportFilePath"));



            FileInputStream fileSourceStream = new FileInputStream(fileSource);

            FileInputStream fileTargetStream = new FileInputStream(fileTarget);
            XSSFWorkbook sourceWorkbook = new XSSFWorkbook(fileSourceStream);
            XSSFWorkbook targetWorkbook = new XSSFWorkbook(fileTargetStream);

            XSSFSheet sourceSheet = sourceWorkbook.getSheetAt(0);
            XSSFSheet targetSheet = targetWorkbook.getSheetAt(0);

//            String[] columnIndexToCopyArray = FileReaderManager.getJsonConfigReader().getJsonArray("ExcelOperations.ExcelColumnToCopy");

            int arrayLength = FileReaderManager.getJsonConfigReader().getJsonArrayCount("ExcelOperations.ExcelColumnToCopy.length()");

            for (int i = 0; i < arrayLength; i++) {

//                int columnToCopy = Integer.parseInt(columnIndexToCopyArray[i]); // Column number to copy

                 String columnToCopy = String.valueOf(FileReaderManager.getJsonConfigReader().getJsonString("ExcelOperations.ExcelColumnToCopy[%s]".formatted(i)));
                int rowIndex = 0; // Starting row index

                for (Row row : sourceSheet) {
                    XSSFCell cell = (XSSFCell) row.getCell(Integer.parseInt(columnToCopy));
                    if (cell != null) {
                        String cellValue = cell.getStringCellValue();
                        Row targetRow = targetSheet.getRow(rowIndex);
                        if (targetRow == null) {
                            targetRow = targetSheet.createRow(rowIndex);
                        }
                        XSSFCell targetCell = (XSSFCell) targetRow.createCell(i);
                        targetCell.setCellValue(cellValue);
                    }
                    if (i == arrayLength-1){
                        Row targetRow = targetSheet.getRow(rowIndex+1);
                        if (targetRow == null) {
                            targetRow = targetSheet.createRow(rowIndex+1);
                        }
                        XSSFCell targetCell = (XSSFCell) targetRow.createCell(i+1);
                        targetCell.setCellValue("New");
                    }
                    rowIndex++;

                }


            }
            fileSourceStream.close();
            FileOutputStream os = new FileOutputStream(fileTarget);
            targetWorkbook.write(os);
            targetWorkbook.close();
            sourceWorkbook.close();
            fileTargetStream.close();
            os.close();
        } catch(Exception e)
        {
            System.out.println("File Not Found" +e );

        }




    }
}

