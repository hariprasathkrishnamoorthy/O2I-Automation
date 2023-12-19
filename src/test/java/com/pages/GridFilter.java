package com.pages;

import com.FileOperations.ExcelDataReader;
import com.managers.FileReaderManager;
import com.utility.UtilityClass;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.sikuli.script.*;

import java.io.IOException;
import java.util.Map;

public class GridFilter extends UtilityClass {

    WebDriver driver;
    Screen screen;
    Wait wait;
    String filePath = (System.getProperty("user.dir"));


    public GridFilter(WebDriver driver, Screen screen) {
        this.driver = driver;
        this.screen = screen;

        PageFactory.initElements(driver, this);

    }

    Pattern ClearGridBtn = new Pattern(filePath + "\\Images\\Clear_grid_icon.PNG");
    Pattern PatientNameColumnHeader = new Pattern(filePath + "\\Images\\patient_name_column_with_header.PNG");
    Pattern PatientNameTextBox = new Pattern(filePath + "\\Images\\PatientNameTextbox.PNG");
    Pattern RightArrow = new Pattern(filePath + "\\Images\\RightArrow.PNG");
    Pattern LoadBtnBlue = new Pattern(filePath + "\\Images\\LoadButtonBlue.PNG");




    public void ClickClearButton() throws FindFailed {
        WaitUntilImageAppear(LoadBtnBlue, 30000);
        screen.click(ClearGridBtn);
    }
    public void clickLoadButtonBlue() throws FindFailed {
        screen.click(LoadBtnBlue);
    }


    public void GridTabNavigation(String jobKeyValue) throws IOException, ParseException, FindFailed {
        Match PatientNameColumnHeaderMatch = screen.find(PatientNameColumnHeader);
        Region PatientNameColumnHeaderRegion = new Region(PatientNameColumnHeaderMatch.getRect());
        Region PatientNameTextbox = new Region(PatientNameColumnHeaderRegion.find(PatientNameTextBox));


        PatientNameTextbox.click();


        if (jobKeyValue.equals("Dispatcher"))
        {


            int totalGridFilterCount = FileReaderManager.getJsonConfigReader().getJsonArrayCount("$..DispatcherGridFilter.length()");
            for (int i = 0; i < totalGridFilterCount; i++) {
                String value = FileReaderManager.getJsonConfigReader().getJsonString("HCHB.GridFilter.DispatcherGridFilter[%s].Value".formatted(i));
                String colIndex = FileReaderManager.getJsonConfigReader().getJsonString("HCHB.GridFilter.DispatcherGridFilter[%s].ColumnIndex".formatted(i));
                for (int j = 0; j < Integer.parseInt(colIndex); j++) {
                    PatientNameTextbox.type(Key.TAB);
                }
                PatientNameTextbox.type(value);
                PatientNameTextbox.click();
                clickLoadButtonBlue();
                WaitUntilImageAppear(LoadBtnBlue, 5000);
            }


        } else if (jobKeyValue.equals("Performer")) {

//            ExcelDataFetch excelDataFetch = new ExcelDataFetch();
//            ArrayList<String> filterValue = new ArrayList<>();

            ExcelDataReader excelDataReader = new ExcelDataReader();
            Map<String, String> excelDataMapValue;
//          filterValue = excelDataFetch.FetchingDataFromExcel();
            excelDataMapValue = excelDataReader.readDataFromExcel();
            int totalGridFilterCount = FileReaderManager.getJsonConfigReader().getJsonArrayCount("$..PerformerGridFilter.length()");
            for (int i = 0; i < totalGridFilterCount; i++) {
//                String value = filterValue.get(i);
                String value = excelDataMapValue.get(FileReaderManager.getJsonConfigReader().getJsonString("HCHB.GridFilter.PerformerGridFilter[%s].name".formatted(i)));
                String colIndex = FileReaderManager.getJsonConfigReader().getJsonString("HCHB.GridFilter.PerformerGridFilter[%s].ColumnIndex".formatted(i));
                for (int j = 0; j < Integer.parseInt(colIndex); j++) {
                    PatientNameTextbox.type(Key.TAB);
                }
                PatientNameTextbox.type(value);
                PatientNameTextbox.click();
            }
            clickLoadButtonBlue();
            WaitUntilImageAppear(LoadBtnBlue, 5000);




        }


    }

    public boolean FindingGridRecords() throws FindFailed {
        Match ClearGridBtnMatch = screen.find(ClearGridBtn);
        Region ClearGridBtnRegion = new Region(ClearGridBtnMatch.getRect());
        ClearGridBtnRegion.below(50).click();

        Region r = ClearGridBtnRegion.below(50).grow(60, 60, 60, 60);

        if (r.exists(RightArrow) != null) {
            return true;
        } else {
            return false;


        }

    }

    public void SelectingTheFilteredRecord() throws FindFailed {
        Match ClearGridBtnMatch = screen.find(ClearGridBtn);
        Region ClearGridBtnRegion = new Region(ClearGridBtnMatch.getRect());
        ClearGridBtnRegion.below(50).doubleClick();

    }

}

