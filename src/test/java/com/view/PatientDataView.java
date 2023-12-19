package com.view;


import com.controller.ScratchpadControl;
import com.controller.XMLController;
import com.managers.FileReaderManager;
import com.pojo.EligibilityAlertPojo.Detail;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class PatientDataView {

public void DisplayItems() throws IOException, ParseException {
    ScratchpadControl.searchingPatientRecord("ARZZZIAS, EUGENE");
    System.out.println(ScratchpadControl.FetchingPatientRecordFromAPI().getPatientName());
    System.out.println(ScratchpadControl.FetchingPatientRecordFromAPI().getMrno());
    System.out.println(ScratchpadControl.FetchingPatientRecordFromAPI().getStage());





}
@Test
public void DisplayXML() throws JAXBException, IOException, ParserConfigurationException, SAXException {
    List<Detail> eligibilityAlertlist;

//
        eligibilityAlertlist = XMLController.searchingNodeFromXml("LabelGroup","Eligibility Alert:","Detail");
        System.out.println(eligibilityAlertlist.get(0).getTextbox8());




    }


public void displayJson() throws IOException, ParseException {
    int i = 0;

    String filterValue = FileReaderManager.getJsonConfigReader().getJsonString("HCHB.GridFilter.DispatcherGridFilter[%s].Value".formatted(i));

           String colIndex = FileReaderManager.getJsonConfigReader().getJsonString("HCHB.GridFilter.DispatcherGridFilter[%s].ColumnIndex".formatted(i));
    int totalGridFilterCount = FileReaderManager.getJsonConfigReader().getJsonArrayCount("$..DispatcherGridFilter.length()");
           System.out.println(filterValue);
           int ol = Integer.parseInt(colIndex);
    System.out.println(ol);
}

public void displayItem(){
    String filePath = (System.getProperty("user.dir"));

    filePath = filePath.replace(":","$");
    filePath = "\\\\Client\\" +filePath+"\\Downloads\\GridData\\PatientRelatedTask.xlsx";

    System.out.println(filePath);

}

}
