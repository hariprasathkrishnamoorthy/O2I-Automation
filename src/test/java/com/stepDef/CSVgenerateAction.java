package com.stepDef;


import com.utility.UtilityClass;
import com.managers.FileReaderManager;
import com.managers.TextContext;
import com.pages.CSVgeneratepage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.simple.parser.ParseException;
import org.sikuli.script.FindFailed;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
public class CSVgenerateAction
{
    CSVgeneratepage CSVgeneratepageobj ;

    @Given("Generate the CSV file")
    public void Generate_the_CSV_file() throws FindFailed, InterruptedException, IOException, ParseException
    {

        CSVgeneratepage.generateCSV();
        CSVgeneratepage.deleteXLSXFiles(System.getProperty("user.dir")+ File.separator+"Downloads\\GridData");
        //CSVgeneratepage.movezip_csv_xml_files();

    }







}
