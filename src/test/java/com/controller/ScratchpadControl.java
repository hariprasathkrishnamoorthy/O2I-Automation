package com.controller;

import com.api.FetchScratchpad;
import com.pojo.MainPojo;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ScratchpadControl
{


    public static void searchingPatientRecord(String patientName) throws IOException, ParseException {
        FetchScratchpad.SearchDataFromAPI(patientName);
    }

    public static MainPojo FetchingPatientRecordFromAPI()
    {
        return FetchScratchpad.SetDataFromAPIToScratchpadPojo();
    }
}
