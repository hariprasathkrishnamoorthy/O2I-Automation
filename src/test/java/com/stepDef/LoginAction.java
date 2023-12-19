package com.stepDef;

import com.managers.TextContext;
import com.pages.LoginPage;
import com.utility.UtilityClass;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.simple.parser.ParseException;
import org.sikuli.script.FindFailed;

import java.io.IOException;


public class LoginAction extends UtilityClass {

   TextContext textContext;

    LoginPage loginPage;



  public LoginAction(TextContext context)
  {

      this.textContext = context;
      this.loginPage = context.getPageObjectManager().getLoginPage();



  }



    @Given("Login using valid username and password")
    public void login_using_valid_username_and_password() throws FindFailed, IOException, ParseException {


        loginPage.enterUsernamePassword();


    }

    @Then("Search the Citrix application link")
    public void searching_the_citrix_application_link() throws FindFailed, IOException, ParseException {
        loginPage.SearchingCitrixLink();

    }

    @Then("Open the Citrix application link")
    public void opening_the_citrix_application_link() throws FindFailed {
        loginPage.OpeningCitrix();
    }
}
