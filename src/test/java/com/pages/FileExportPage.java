package com.pages;

import com.utility.UtilityClass;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.sikuli.script.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileExportPage extends UtilityClass {


    WebDriver driver;
    Screen screen;
    Wait wait;
    String filePath = (System.getProperty("user.dir"));
    String gridExportFilePath;





    public FileExportPage(WebDriver driver, Screen screen) {
        this.driver = driver;
        this.screen = screen;

        PageFactory.initElements(driver, this);

    }



    Pattern ClearGridBtn = new Pattern(filePath + "\\Images\\Clear_grid_icon.PNG");
    Pattern PatientNameColumnHeader = new Pattern(filePath + "\\Images\\patient_name_column_with_header.PNG");
    Pattern PatientNameTextBox = new Pattern(filePath + "\\Images\\PatientNameTextbox.PNG");
    Pattern RightArrow = new Pattern(filePath + "\\Images\\RightArrow.PNG");
    Pattern GridExportPopup = new Pattern(filePath + "\\Images\\Export_Grid_popup.PNG");
    Pattern ExportGridButton = new Pattern(filePath + "\\Images\\Export_Grid_Button.PNG");

    Pattern SaveAsIcon = new Pattern(filePath + "\\Images\\Save_As_Icon.PNG");

    Pattern This_PC_Button = new Pattern(filePath + "\\Images\\This_PC_Button.PNG");

    Pattern CitrixSecurityWarningPopup = new Pattern(filePath + "\\Images\\Citrix_Workspace_Security_Warning_Popup.PNG");


    Pattern CitrixPermitAllOption = new Pattern(filePath + "\\Images\\Permit_All_Access_Button.PNG");
    Pattern FilepathTextBox = new Pattern(filePath + "\\Images\\File_Name_TextBox.PNG");

    Pattern GridExportCompletedPopup = new Pattern(filePath + "\\Images\\Grid_Export_Completed_Popup.PNG");

    Pattern GridExportCompletedPopupOkBtn = new Pattern(filePath +"\\Images\\Export_Grid_OK_Button.PNG");

    public void ExportGridRecords() throws FindFailed, InterruptedException, IOException, ParseException {

        Match ClearGridBtnMatch = screen.find(ClearGridBtn);
        Region ClearGridBtnRegion = new Region(ClearGridBtnMatch.getRect());
        ClearGridBtnRegion.below(50).click();
        Region r = ClearGridBtnRegion.below(50).grow(60, 60, 60, 60);
        r.rightClick(RightArrow);
        WaitUntilImageAppear(GridExportPopup, 3000);
        Match GridExportPopupMatch = screen.find(GridExportPopup);
        Region GridExportPopupRegion = new Region(GridExportPopupMatch.getRect());
        GridExportPopupRegion.click(ExportGridButton);
        SavingFilesToLocal();


    }

    public void SavingFilesToLocal() throws FindFailed, IOException, ParseException {
        WaitUntilImageAppear(SaveAsIcon, 2000);
        Match ThisPCmatch = screen.find(This_PC_Button);
        Region ThisPCRegion = new Region(ThisPCmatch.getRect());
        ThisPCRegion.click();
        WaitUntilImageAppear(CitrixSecurityWarningPopup,2000);
        try{
            Region CitrixSecurityWarningpopupRegion = new Region(screen.find(CitrixSecurityWarningPopup).getRect());
            CitrixSecurityWarningpopupRegion.find(CitrixPermitAllOption).click();
            screen.waitVanish(CitrixSecurityWarningPopup);
            synchronized (CitrixSecurityWarningPopup) {
                screen.waitVanish(CitrixSecurityWarningPopup,2000);
            }



        }catch (Exception e)
        {
           System.out.println(e);
        }
         finally {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestamp = dateFormat.format(new Date());

            gridExportFilePath = filePath.replace(":","$");
            gridExportFilePath = "\\\\Client\\" +gridExportFilePath+"\\Downloads\\GridData\\PatientRelatedTask_HCHB.xlsx";
            Region FilePathTextBoxRegion = new Region(screen.find(FilepathTextBox).getRect());
            FilePathTextBoxRegion.click();
            FilePathTextBoxRegion.type(gridExportFilePath);
            FilePathTextBoxRegion.type(Key.ENTER);
            WaitUntilImageAppear(GridExportCompletedPopupOkBtn,7000);
            Region GridExportCompletedPopupRegion = new Region(screen.find(GridExportCompletedPopup).getRect());
            GridExportCompletedPopupRegion.click(GridExportCompletedPopupOkBtn);
        }













    }





    public void SavingFilesToLocal(String Workflowname) throws FindFailed, IOException, ParseException {
        WaitUntilImageAppear(SaveAsIcon, 2000);
        Match ThisPCmatch = screen.find(This_PC_Button);
        Region ThisPCRegion = new Region(ThisPCmatch.getRect());
        ThisPCRegion.click();
        WaitUntilImageAppear(CitrixSecurityWarningPopup,2000);
        try{
            Region CitrixSecurityWarningpopupRegion = new Region(screen.find(CitrixSecurityWarningPopup).getRect());
            CitrixSecurityWarningpopupRegion.find(CitrixPermitAllOption).click();
            screen.waitVanish(CitrixSecurityWarningPopup);
            synchronized (CitrixSecurityWarningPopup) {
                screen.waitVanish(CitrixSecurityWarningPopup,2000);
            }



        }catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestamp = dateFormat.format(new Date());

            gridExportFilePath = filePath.replace(":","$");
            gridExportFilePath = "\\\\Client\\" +gridExportFilePath+"\\Downloads\\GridData\\PatientRelatedTask_"+Workflowname+".xlsx";
            Region FilePathTextBoxRegion = new Region(screen.find(FilepathTextBox).getRect());
            FilePathTextBoxRegion.click();
            FilePathTextBoxRegion.type(gridExportFilePath);
            FilePathTextBoxRegion.type(Key.ENTER);
            WaitUntilImageAppear(GridExportCompletedPopupOkBtn,7000);
            Region GridExportCompletedPopupRegion = new Region(screen.find(GridExportCompletedPopup).getRect());
            GridExportCompletedPopupRegion.click(GridExportCompletedPopupOkBtn);
        }













    }


}
