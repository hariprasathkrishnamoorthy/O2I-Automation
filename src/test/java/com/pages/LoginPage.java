package com.pages;

import com.managers.FileReaderManager;
import com.utility.UtilityClass;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.sikuli.script.*;
import org.testng.Assert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginPage extends UtilityClass {
    WebDriver driver;
    Screen screen;
    Wait wait;
    String filePath = (System.getProperty("user.dir")) ;


    public LoginPage(WebDriver driver, Screen screen){
        this.driver = driver;
        this.screen = screen;

        PageFactory.initElements(driver, this);


    }

    @FindBy(xpath = "//input[@id='Enter user name']")
    WebElement Username;

    @FindBy(xpath = "//input[@id = 'passwd']")
    WebElement Password;

    @FindBy(xpath = "//input[@id = 'Log_On']")
    WebElement SubmitButton;

    @FindBy(xpath = "//span[text()= 'Your account is disabled.']")
    WebElement AccountDisabled;

    @FindBy(xpath = "//span[text()= 'Incorrect password.']")
    WebElement IncorrectPassword;

    @FindBy(xpath = "//div[text()='Apps']")
    WebElement Apps;

    @FindBy(xpath = "//a[text()= 'Detect Citrix Workspace app']")
    WebElement DetectCitrix;

    @FindBy(xpath = "//a[@id='searchBtn']")
    WebElement SearchButton;

    @FindBy(xpath = "//input[@id='searchHeaderInputId']")
    WebElement SearchType;

    @FindBy(xpath = "//a[@class = 'storeapp-details-link']")
    WebElement HCHBinputLinkIcon;

    @FindBy(xpath = "//p[@class = 'storeapp-name']")
    WebElement HCHBinputLinkName;
    //Sikuli

    Pattern DetectCitrixPopup = new Pattern(filePath+"\\Images\\Detect Citrix popup.PNG");
    Pattern OpenCtirixButton = new Pattern(filePath+"\\Images\\Open Citrix button.PNG");

    Pattern AlwaysAllow = new  Pattern(filePath+"\\Images\\Always Allow checkbox.PNG");

    Pattern HCHBHomepage = new Pattern(filePath+"\\Images\\HCHB Homepage.PNG");

    Pattern HCHBLogo = new Pattern(filePath+"\\Images\\HCHB Main button.PNG");



public void enterUsernamePassword() throws FindFailed, IOException, ParseException {

    driver.get(FileReaderManager.getJsonConfigReader().getJsonString("HCHB.URL"));
    driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    Username.sendKeys(FileReaderManager.getJsonConfigReader().getJsonString("HCHB.Username"));
    Password.sendKeys(FileReaderManager.getJsonConfigReader().getJsonString("HCHB.Password"));
    SubmitButton.click();
    DetectCitrix.click();
    screen.wait(DetectCitrixPopup);
    screen.click(AlwaysAllow);
    screen.click(OpenCtirixButton);
}

public void SearchingCitrixLink() throws IOException, ParseException {
//    wait = new WebDriverWait(driver, 30);
//    wait.until(ExpectedConditions.visibilityOf(Apps));
    SearchButton.click();
    SearchType.sendKeys(FileReaderManager.getJsonConfigReader().getJsonString("HCHB.ApplicationName"));
    Assert.assertEquals(HCHBinputLinkName.getText(), FileReaderManager.getJsonConfigReader().getJsonString("HCHB.ApplicationName"), "HCHB Link Not Available");


}

public  void OpeningCitrix() throws FindFailed {
    HCHBinputLinkIcon.click();
    WaitUntilImageAppear(HCHBHomepage, 30000);
    Match homelogo = screen.find(HCHBLogo);
    Region hchblogoregion = new Region(homelogo.getRect());


    Assert.assertNotNull(hchblogoregion.exists(HCHBLogo),"Homepage not available");

}



}
