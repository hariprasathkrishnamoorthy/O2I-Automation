package com.FileOperations;

import com.managers.FileReaderManager;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelManipulations
{
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
                        String cellValue = "";
                        if (cell.getCellType() == CellType.STRING)
                        {
                            cellValue = cell.getStringCellValue();
                        }
                        if (cell.getCellType() == CellType.NUMERIC) 
                        {
                            cellValue = String.valueOf(cell.getNumericCellValue());
                        }
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

