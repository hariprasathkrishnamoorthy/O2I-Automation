package com.stepDef;

import com.managers.TextContext;
import com.pages.GridFilter;
import io.cucumber.java.en.Given;
import org.json.simple.parser.ParseException;
import org.sikuli.script.FindFailed;

import java.io.IOException;
import java.net.URISyntaxException;

public class GridFilterAction {

    TextContext textContext;
    GridFilter gridFilter;


    public GridFilterAction(TextContext context) throws URISyntaxException {

        this.textContext = context;
        gridFilter = textContext.getPageObjectManager().getGridFilterPage();


    }
    @Given("Entering the Dispatcher Grid Filter")
    public void EnteringDispatcherGridFilter() throws FindFailed, IOException, ParseException
    {


        gridFilter.ClickClearButton();
        gridFilter.GridTabNavigation("Dispatcher");


    }
    @Given("Entering the Performer Grid Filter")
    public void EnteringPerformerGridFilter() throws FindFailed, IOException, ParseException
    {


        gridFilter.ClickClearButton();
        gridFilter.GridTabNavigation("Performer");
        boolean fileAvailability = gridFilter.FindingGridRecords();
        if(fileAvailability)
        {
         gridFilter.SelectingTheFilteredRecord();
        }

    }


}
