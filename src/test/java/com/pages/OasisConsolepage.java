package com.pages;

import com.FileOperations.DeleteFile;
import com.config.JsonConfigReader;
import com.managers.FileReaderManager;
import com.utility.UtilityClass;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.*;


import java.io.IOException;

public class OasisConsolepage extends UtilityClass
{

    WebDriver driver;
    Screen screen;
    public String basePath;
    public String Facility;

    public String Fromdate;

    public String Todate;
    public OasisConsolepage(WebDriver driver, Screen screen) throws IOException, ParseException {
        this.driver = driver;
        this.screen = screen;
        JsonConfigReader jsreader= FileReaderManager.getJsonConfigReader();
        this.Facility  = jsreader.getJsonString("HCHB.Facility");
        this.Fromdate = jsreader.getJsonString("HCHB.FromDate");
        this.Todate = jsreader.getJsonString("HCHB.ToDate");


    }
    String filePath = (System.getProperty("user.dir")) ;


    Pattern OASISicon = new Pattern(filePath+"\\Images\\OASIS_icon.PNG");
    Pattern LoadBtnBlue = new Pattern(filePath + "\\Images\\LoadButtonBlue.PNG");

    Pattern LoadBtnYellow = new Pattern(filePath + "\\Images\\LoadButtonYellow.PNG");


    Pattern SearchBy = new Pattern(filePath+"\\Images\\OASIS_Search_by.PNG");

    Pattern downarrowicon = new Pattern(filePath+"\\Images\\OASIS_dropdown_box_icon_downarrow.PNG");

    Pattern SearchBydropdown = new Pattern(filePath+"\\Images\\OASIS_searchdropdwon_Selector.PNG");

    Pattern Status = new Pattern(filePath+"\\Images\\OASIS_status_Selection.PNG");

    Pattern Searchfor = new Pattern(filePath+"\\Images\\OASIS_search_box.PNG");

    Pattern Searchicon = new Pattern(filePath+"\\Images\\Search Icon.PNG");

    Pattern DragToolbar = new Pattern(filePath+"\\Images\\OASIS_Toolbar_Top.PNG");

    Pattern Exportoolbar = new Pattern(filePath+"\\Images\\OASIS_ExportGrid_entire.PNG");

    Pattern Exportgrid = new Pattern(filePath+"\\Images\\OASIS_ExportGrid.PNG");

    Pattern OASIS_Date_Filter = new Pattern(filePath+"\\Images\\OASIS_Date_Filter.PNG");

    Pattern OASIS_FROM_Date= new Pattern(filePath+"\\Images\\OASIS_FROM-Date.PNG");

    Pattern OASIS_TO_Date= new Pattern(filePath+"\\Images\\OASIS_TO-Date.PNG");

    Pattern OASIS_Loadbtn_Yellow =new Pattern(filePath+"\\Images\\OASIS-Loadbtn-Yellow.PNG");

    Pattern OASIS_Branch =new Pattern(filePath+"\\Images\\OASIS-Branch.PNG");
    Pattern Branch_SearchIcon =new Pattern(filePath+"\\Images\\Branch_SearchIcon.PNG");

    Pattern Branch_SearchIcon2 =new Pattern(filePath+"\\Images\\OASIS-Branch_1.PNG");

    Pattern OASIS_Branch_inputTextbar =new Pattern(filePath+"\\Images\\OASIS_Branch_inputTextbar.PNG");
    Pattern OASIS_Branch_inputTextbar_searchicon =new Pattern(filePath+"\\Images\\OASIS_Branch_inputTextbar_serachicon");
    Pattern OASIS_Branch_selectionCheckbox =new Pattern(filePath+"\\Images\\OASIS-Branch-selectionCheckbox.PNG");

    Pattern OASIS_Branch_Applyselection =new Pattern(filePath+"\\Images\\OASIS-Branch-Applyselection.PNG");

    public void selectOasisConsoleicon() throws FindFailed, InterruptedException, IOException, ParseException
    {


        screen.click(OASISicon);
        WaitUntilImageAppear(LoadBtnBlue, 8000);


    }

    public void applyStatusSelection(String value) throws FindFailed, InterruptedException, IOException, ParseException
    {

        //Enter the Data in Search box for Status
        SearchBy.similar(0.5);
        WaitUntilImageAppear(SearchBy, 8000);
        screen.click(SearchBy);
        Match SearchByval = screen.find(SearchBy);
        Region SearchByvalregion = new Region(SearchByval.getRect());
        SearchByvalregion.type("Status");

        Searchfor.similar(0.5);
        Searchicon.similar(0.5);
        screen.click(Searchfor);
        Match Searchforval = screen.find(Searchfor);
        Region Searchforvalregion = new Region(Searchforval.getRect());
        Searchforvalregion.type(value);
        //screen.click(Searchicon);

        //Branch Selection
        screen.click(OASIS_Branch);
        WaitUntilImageAppear(Branch_SearchIcon2, 2000);
        screen.click(Branch_SearchIcon2);

        WaitUntilImageAppear(OASIS_Branch_inputTextbar, 2000);
        Match Textbar = screen.find(OASIS_Branch_inputTextbar);
        Region Textbarbranchregion = new Region(Textbar.getRect());
        Textbarbranchregion.type(Facility);
        screen.click(OASIS_Branch_inputTextbar_searchicon);
        WaitUntilImageAppear(OASIS_Branch_selectionCheckbox, 2000);
        screen.click(OASIS_Branch_selectionCheckbox);
        WaitUntilImageAppear(OASIS_Branch_Applyselection, 2000);
        screen.click(OASIS_Branch_Applyselection);













        // Enter DATE VALUE
        System.out.println("Entering Date Values");
        OASIS_Date_Filter.similar(0.3);
        WaitUntilImageAppear(OASIS_Date_Filter, 2000);
        screen.click(OASIS_Date_Filter);
        OASIS_FROM_Date.similar(0.5);
        WaitUntilImageAppear(OASIS_FROM_Date, 2000);
        screen.click(OASIS_FROM_Date);
        Match FromDate = screen.find(OASIS_FROM_Date);
        Region FromDatevalregion = new Region(FromDate.getRect());
        FromDatevalregion.type(Fromdate);

        OASIS_TO_Date.similar(0.5);
        WaitUntilImageAppear(OASIS_TO_Date, 2000);
        screen.click(OASIS_TO_Date);
        Match ToDate = screen.find(OASIS_TO_Date);
        Region ToDateDatevalregion = new Region(ToDate.getRect());
        ToDateDatevalregion.type(Todate);


        //Load data
        WaitUntilImageAppear(LoadBtnYellow, 2000);
        screen.click(LoadBtnYellow);


        // clicking Search


        //Export the Data
        screen.rightClick(DragToolbar);
        WaitUntilImageAppear(Exportoolbar, 20000);
        screen.click(Exportgrid);

    }






}
