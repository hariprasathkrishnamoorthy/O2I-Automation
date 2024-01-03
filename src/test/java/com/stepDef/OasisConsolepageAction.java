package com.stepDef;

import com.config.JsonConfigReader;
import com.utility.UtilityClass;
import com.managers.FileReaderManager;
import com.managers.TextContext;
import com.pages.OasisConsolepage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.simple.parser.ParseException;
import org.sikuli.script.FindFailed;

import java.io.IOException;
import java.net.URISyntaxException;

public class OasisConsolepageAction  extends UtilityClass
{
    OasisConsolepage OasisConsolepage;
    TextContext textContext;


    public OasisConsolepageAction(TextContext context) throws URISyntaxException, IOException, ParseException {

        this.textContext = context;
        OasisConsolepage = textContext.getPageObjectManager().getOasisConsolepage();


    }

    @Given("Open the OASIS console")
    public void Open() throws FindFailed, InterruptedException, IOException, ParseException
    {
        OasisConsolepage.selectOasisConsoleicon();


    }


    @Then("Apply the status selection for workflow1")
    public void applyTheStatusSelectionwf1() throws FindFailed, InterruptedException, IOException, ParseException
    {
        JsonConfigReader jsreader=FileReaderManager.getJsonConfigReader();
        String Status  = jsreader.getJsonString("HCHB.Status");
        OasisConsolepage.applyStatusSelection(Status);

    }

    @Then("Apply the status selection for workflow2")
    public void applyTheStatusSelectionwf2() throws FindFailed, InterruptedException, IOException, ParseException
    {
        OasisConsolepage.applyStatusSelection("ACCEPTED");

    }

    @Then("validate the Load button")
    public void validateTheLoadButton() {
        
    }

    @Then("Load the data")
    public void loadTheData() {
    }

    @Then("Export the data to File")
    public void exportTheDataToFile() {
    }
}
