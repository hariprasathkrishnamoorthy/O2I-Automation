package com.controller;

import com.DocumentDataScrapper.XmlReader.XmlDataFinder;
import com.pojo.CoordinationNotesPojo;
import com.pojo.EligibilityAlertPojo.Detail;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLController
{

    public static List<CoordinationNotesPojo> searchingNodeFromXml(String nodeName) throws IOException, ParserConfigurationException, SAXException, JAXBException {

        NodeList nodeList = XmlDataFinder.FindingDataByNodenameFromXml(nodeName);
        JAXBContext jaxbContext = JAXBContext.newInstance(CoordinationNotesPojo.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        List<CoordinationNotesPojo> coordinationNotesPojoList = new ArrayList<>();
        CoordinationNotesPojo coordinationNotesPojo;

        for (int i=0; i<nodeList.getLength();i++) {
            coordinationNotesPojo = (CoordinationNotesPojo) unmarshaller.unmarshal(nodeList.item(i));
            coordinationNotesPojoList.add(coordinationNotesPojo);
        }

        return coordinationNotesPojoList;
    }

    public static List<Detail> searchingNodeFromXml(String nodeName, String labelName, String subNodeName) throws IOException, ParserConfigurationException, SAXException, JAXBException {


        NodeList nodeList = XmlDataFinder.FindingDataByLabelNameFromXml(nodeName,labelName, subNodeName);
        JAXBContext jaxbContext = JAXBContext.newInstance(Detail.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        List<Detail> eligibilityAlertlist = new ArrayList<>();
        Detail detail;

        for (int i=0; i<nodeList.getLength();i++) {
            detail = (Detail) unmarshaller.unmarshal(nodeList.item(i));
            eligibilityAlertlist.add(detail);
        }

        return eligibilityAlertlist;

    }


}
