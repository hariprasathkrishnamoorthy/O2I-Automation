package com.pages;

import com.FileOperations.DeleteFile;
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
    public OasisConsolepage(WebDriver driver, Screen screen)
    {
        this.driver = driver;
        this.screen = screen;

    }
    String filePath = (System.getProperty("user.dir")) ;


    Pattern OASISicon = new Pattern(filePath+"\\Images\\OASIS_icon.PNG");
    Pattern LoadBtnBlue = new Pattern(filePath + "\\Images\\LoadButtonBlue.PNG");
    Pattern SearchBy = new Pattern(filePath+"\\Images\\OASIS_Search_by.PNG");

    Pattern downarrowicon = new Pattern(filePath+"\\Images\\OASIS_dropdown_box_icon_downarrow.PNG");

    Pattern SearchBydropdown = new Pattern(filePath+"\\Images\\OASIS_searchdropdwon_Selector.PNG");

    Pattern Status = new Pattern(filePath+"\\Images\\OASIS_status_Selection.PNG");

    Pattern Searchfor = new Pattern(filePath+"\\Images\\OASIS_search_box.PNG");

    Pattern Searchicon = new Pattern(filePath+"\\Images\\Search Icon.PNG");

    Pattern DragToolbar = new Pattern(filePath+"\\Images\\OASIS_Toolbar_Top.PNG");

    Pattern Exportoolbar = new Pattern(filePath+"\\Images\\OASIS_ExportGrid_entire.PNG");

    Pattern Exportgrid = new Pattern(filePath+"\\Images\\OASIS_ExportGrid.PNG");



    public void selectOasisConsoleicon() throws FindFailed, InterruptedException, IOException, ParseException
    {


        screen.click(OASISicon);
        WaitUntilImageAppear(LoadBtnBlue, 30000);


    }

    public void applyStatusSelection(String value) throws FindFailed, InterruptedException, IOException, ParseException
    {



        WaitUntilImageAppear(SearchBy, 20000);
        screen.click(SearchBy);
        Match SearchByval = screen.find(SearchBy);
        Region SearchByvalregion = new Region(SearchByval.getRect());
        SearchByvalregion.type("Status");

        screen.click(Searchfor);
        Match Searchforval = screen.find(Searchfor);
        Region Searchforvalregion = new Region(Searchforval.getRect());
        Searchforvalregion.type(value);
        screen.click(Searchicon);

        screen.rightClick(DragToolbar);
        WaitUntilImageAppear(Exportoolbar, 20000);
        screen.click(Exportgrid);

    }






}
