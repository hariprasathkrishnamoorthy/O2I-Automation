package com.stepDef;

import com.managers.TextContext;
import com.pages.GenerateJsonTemplateTaskPage;
import io.cucumber.java.en.Given;
import org.json.simple.parser.ParseException;
import org.sikuli.script.FindFailed;

import java.io.IOException;

public class GenerateJsonTemplateAction {

    GenerateJsonTemplateTaskPage GenerateJsonTemplateTaskPageobj ;
    TextContext textContext;

    public GenerateJsonTemplateAction(TextContext context)
    {

        this.textContext = context;
        this.GenerateJsonTemplateTaskPageobj = context.getPageObjectManager().getGenerateJsonTemplateTaskPage();


    }


    @Given("Generate config file")
    public void generate_config() throws FindFailed, IOException, ParseException {


        GenerateJsonTemplateTaskPageobj.generateConfig();


    }

    @Given("Generate the Json template")
    public void generate_template() throws FindFailed, IOException, ParseException {


        GenerateJsonTemplateTaskPageobj.generateTemplate();


    }




}
