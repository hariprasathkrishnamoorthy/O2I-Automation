package com.FileOperations;

import com.managers.FileReaderManager;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class DeleteFile {


    public static void DeletingThefilesInLocal() throws IOException, ParseException {

    File file = new File(FileReaderManager.getJsonConfigReader().getJsonString("ExcelOperations.GridExportFilePath"));
       try{
           if (file.exists() && file.isFile()) {
               // Delete the file
               file.delete();
           }

       }catch (Exception Ignored){

       }
}}
