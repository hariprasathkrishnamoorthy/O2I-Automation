package com.stepDef;

import org.json.simple.parser.ParseException;
import org.sikuli.script.FindFailed;
import com.utility.UtilityClass;
import com.managers.FileReaderManager;
import com.pages.XMLgeneratepage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;


public class XMLgenerateAction
{

    XMLgeneratepage XMLgeneratepageobj = new XMLgeneratepage();

    @Then("Generate the XML files and zip")
    public void opening_patient_related_task() throws FindFailed, InterruptedException, IOException, ParseException
    {
        XMLgeneratepageobj.generateXMLandzip();
        Thread.sleep(17000);
        XMLgeneratepageobj.deleteXMLFilesExceptPom(System.getProperty("user.dir"));

    }


    @Then("delete xml files")
    public void deleteXmlFiles() throws FindFailed, InterruptedException, IOException, ParseException
    {
        Thread.sleep(7000);
        XMLgeneratepageobj.deleteXMLFilesExceptPom(System.getProperty("user.dir"));

    }






}
