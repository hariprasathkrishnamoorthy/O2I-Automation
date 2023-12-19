package com.pages;

import com.FileOperations.DeleteFile;
import com.utility.UtilityClass;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.*;

import java.io.IOException;


public class PatientRelatedTaskPage extends UtilityClass
{

    WebDriver driver;
    Screen screen;
    public String basePath;
    public PatientRelatedTaskPage(WebDriver driver, Screen screen)
    {
        this.driver = driver;
        this.screen = screen;


    }
    String filePath = (System.getProperty("user.dir")) ;


    Pattern WorkflowConsoleIcon = new Pattern(filePath+"\\Images\\Patient Related Task Icon.PNG");

    Pattern FullScreenButton = new Pattern(filePath+"\\Images\\FullScreenbutton.PNG");
    Pattern StageFilter = new Pattern(filePath+"\\Images\\StageFilter.PNG");

    Pattern StageFindButton = new Pattern(filePath+"\\Images\\Search Icon.PNG");

    Pattern SearchForImage = new Pattern(filePath+"\\Images\\Search For.PNG");


   Pattern TextBox = new Pattern(filePath+"\\Images\\TextBox.PNG");


   Pattern SearchButton = new Pattern (filePath+"\\Images\\Search Icon.PNG");

    Pattern SearchForCloseButton = new Pattern(filePath+"\\Images\\SearchForCloseButton.PNG");

  Pattern ApplySelectionButton = new Pattern(filePath+"\\Images\\ApplySelections.PNG");


   Pattern StageFilterCheckBox = new Pattern(filePath+"\\Images\\StageCheckbox.PNG");
   Pattern StageFilterHeader = new Pattern(filePath+"\\Images\\StageFilterHeader.PNG");
    Pattern LoadBtnYellow = new Pattern(filePath + "\\Images\\LoadButtonYellow.PNG");
    Pattern LoadBtnBlue = new Pattern(filePath + "\\Images\\LoadButtonBlue.PNG");


    public void SelectingPatientRelatedTask() throws FindFailed, InterruptedException, IOException, ParseException {
        DeleteFile.DeletingThefilesInLocal();


        screen.click(WorkflowConsoleIcon);
        screen.click(FullScreenButton);
        WaitUntilImageAppear(LoadBtnBlue, 30000);


    }

    public void clickLoadButtonYellow() throws FindFailed {
        screen.click(LoadBtnYellow);

    }

    public void ClickingStagefilter() throws FindFailed {
      Match StageImage = screen.find(StageFilter);
      Region StageRegion = new Region(StageImage.getRect());
      Region StageSearchButton = new Region(StageRegion.find(StageFindButton));
      screen.click(StageSearchButton);

    }
    public void EnteringStageName(String StageName) throws FindFailed {

        WaitUntilImageAppear(SearchForImage, 2000);
        Match SearchForMatch = screen.find(SearchForImage);
        Region SearchForRegion = new Region(SearchForMatch.getRect());
        Region SearchboxTextBox = new Region(SearchForRegion.find(TextBox));
        SearchboxTextBox.click();
        SearchboxTextBox.type(StageName +Key.ENTER);
    }

        public void SelectingTheStageResults() throws FindFailed {

            Match StageFilterHeaderMatch = screen.find(StageFilterHeader);
            Region StageFilterHeaderRegion = new Region(StageFilterHeaderMatch.getRect());
            Region StageFilterCheckbox = new Region(StageFilterHeaderRegion.find(StageFilterCheckBox));
            StageFilterCheckbox.click();
        }



    public void ClickingCloseButtonInFilter() throws FindFailed {
        Match SearchForMatch = screen.find(SearchForImage);
        Region SearchForRegion = new Region(SearchForMatch.getRect());
        SearchForRegion.click(SearchForCloseButton);


    }

    public void ClickingApplySelectionButton() throws FindFailed {
        screen.click(ApplySelectionButton);

    }






}
