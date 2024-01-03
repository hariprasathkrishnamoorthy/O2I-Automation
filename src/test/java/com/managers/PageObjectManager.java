package com.managers;

import com.pages.*;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;

import java.io.IOException;


public class PageObjectManager {

    private WebDriver driver;
    Screen screen;
    private LoginPage loginPage;
    private PatientRelatedTaskPage patientRelatedTaskPage;

    private OasisConsolepage OasisConsolepage;

    private GridFilter gridFilter;

    private FileExportPage fileExportPage;

    private GenerateJsonTemplateTaskPage GenerateJsonTemplateTaskPageobj;

    public PageObjectManager(WebDriver driver, Screen screen)
    {

        this.driver = driver;
        this.screen = screen;

    }

    public LoginPage getLoginPage(){


        return (loginPage == null) ? loginPage = new LoginPage(driver, screen) : loginPage;

    }
    public PatientRelatedTaskPage getPatientRelatedTaskPage()
    {


        return (patientRelatedTaskPage == null) ? patientRelatedTaskPage = new PatientRelatedTaskPage(driver, screen) : patientRelatedTaskPage;

    }
    public GridFilter getGridFilterPage()
    {


        return (gridFilter == null) ? gridFilter = new GridFilter(driver, screen) : gridFilter;

    }


    public FileExportPage getFileExportPage()
    {


        return (fileExportPage == null) ? fileExportPage = new FileExportPage(driver, screen) : fileExportPage;

    }

    public OasisConsolepage getOasisConsolepage() throws IOException, ParseException {


        return (OasisConsolepage == null) ? OasisConsolepage = new OasisConsolepage(driver, screen) : OasisConsolepage;

    }


    public GenerateJsonTemplateTaskPage getGenerateJsonTemplateTaskPage()
    {


        return (GenerateJsonTemplateTaskPageobj == null) ? GenerateJsonTemplateTaskPageobj = new GenerateJsonTemplateTaskPage(driver, screen) : GenerateJsonTemplateTaskPageobj;

    }

}
