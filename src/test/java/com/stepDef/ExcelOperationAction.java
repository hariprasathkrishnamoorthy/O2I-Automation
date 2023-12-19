package com.stepDef;


import com.FileOperations.DeleteFile;
import com.FileOperations.ExcelManipulations;
import io.cucumber.java.en.Given;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ExcelOperationAction
{

    ExcelManipulations excelManipulations = new ExcelManipulations();

    @Given("Copying the Excel Data From Source File")
    public void CopyingpatientDataFromMainFile() throws IOException, ParseException {

     excelManipulations.CopyingExcelData();
        DeleteFile.DeletingThefilesInLocal();

    }
}
