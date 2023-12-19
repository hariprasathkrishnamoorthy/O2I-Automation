package com.stepDef;

import com.managers.TextContext;
import com.pages.FileExportPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.simple.parser.ParseException;
import org.sikuli.script.FindFailed;

import java.io.IOException;
import java.net.URISyntaxException;

public class FileExportAction {

    TextContext textContext;
    FileExportPage fileExportPage;


    public FileExportAction(TextContext context) throws URISyntaxException
    {

        this.textContext = context;
        fileExportPage = textContext.getPageObjectManager().getFileExportPage();


    }

    @Then("Exporting the file")
    public void Exporting_the_file() throws FindFailed, IOException, ParseException, InterruptedException
    {

        fileExportPage.ExportGridRecords();


    }


    @Given("Exporting the file for WF1")
    public void ExportingFilewf1() throws FindFailed, IOException, ParseException, InterruptedException
    {

       //fileExportPage.ExportGridRecords();
        fileExportPage.SavingFilesToLocal("WF1");

    }

    @Given("Exporting the file for WF2")
    public void ExportingFilewf2() throws FindFailed, IOException, ParseException, InterruptedException
    {


        fileExportPage.SavingFilesToLocal("WF2");

    }
}
