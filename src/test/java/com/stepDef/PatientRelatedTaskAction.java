package com.stepDef;

import com.managers.FileReaderManager;
import com.managers.TextContext;
import com.pages.PatientRelatedTaskPage;
import com.utility.UtilityClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.simple.parser.ParseException;
import org.sikuli.script.FindFailed;

import java.io.IOException;
import java.net.URISyntaxException;

public class PatientRelatedTaskAction  extends UtilityClass {

    PatientRelatedTaskPage patientRelatedTaskPage;
    TextContext textContext;


    public PatientRelatedTaskAction(TextContext context) throws URISyntaxException {

        this.textContext = context;
        patientRelatedTaskPage = textContext.getPageObjectManager().getPatientRelatedTaskPage();


    }

    @Given("Open the patient related task")
    public void opening_patient_related_task() throws FindFailed, InterruptedException, IOException, ParseException {
        patientRelatedTaskPage.SelectingPatientRelatedTask();


    }

    @Then("Filter the stage")
    public void clicking_the_stage_filter_button() throws FindFailed, IOException, ParseException {

        patientRelatedTaskPage.ClickingStagefilter();
        patientRelatedTaskPage.EnteringStageName(FileReaderManager.getJsonConfigReader().getJsonString("HCHB.SelectionFilter.Stage[0]"));
        patientRelatedTaskPage.SelectingTheStageResults();
        patientRelatedTaskPage.ClickingCloseButtonInFilter();
        patientRelatedTaskPage.EnteringStageName(FileReaderManager.getJsonConfigReader().getJsonString("HCHB.SelectionFilter.Stage[1]"));
        patientRelatedTaskPage.SelectingTheStageResults();
        patientRelatedTaskPage.ClickingApplySelectionButton();
        patientRelatedTaskPage.clickLoadButtonYellow();


    }
}
