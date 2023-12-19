package com.stepDef;

import com.managers.TextContext;
import com.pages.PatientRelatedTaskPage;
import com.pages.UploadtoS3Actionpage;
import io.cucumber.java.en.Given;

import java.net.URISyntaxException;

public class UploadtoS3Action {



    UploadtoS3Actionpage UploadtoS3Actionpage;


    @Given("Open the s3 bucket")
    public void Open_the_s3_bucket()
    {
        UploadtoS3Actionpage= new UploadtoS3Actionpage();
        UploadtoS3Actionpage.uploadfilestoS3();


    }




}
