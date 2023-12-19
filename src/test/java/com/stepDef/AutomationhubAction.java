package com.stepDef;

import com.pages.Automationhubpage;
import com.pages.CSVgeneratepage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.simple.parser.ParseException;
import org.sikuli.script.FindFailed;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AutomationhubAction
{


    Automationhubpage Automationhubpageobj = new Automationhubpage();
    Map<String, String> Exceldatamap = new HashMap<String, String>();
    Map<String, String> Apidatamap = new HashMap<String, String>();


    @Given("Open the WF2 Data from excel and Load")
    public void readWF2data() throws FindFailed, InterruptedException, IOException, ParseException
    {

        Exceldatamap= Automationhubpageobj.readwf2Data();

    }

    @Then("read  Hub API Response")
    public void readApidata() throws FindFailed, InterruptedException, IOException, ParseException
    {

        Apidatamap= Automationhubpageobj.readapiData();

    }


    @Then("assert both records")
    public void comparestatus() throws FindFailed, InterruptedException, IOException, ParseException
    {

        boolean result=Automationhubpageobj.compareData(Exceldatamap,Apidatamap);
        Assert.assertTrue(result, "Both Status of actual and expected patients are same");

    }




}
