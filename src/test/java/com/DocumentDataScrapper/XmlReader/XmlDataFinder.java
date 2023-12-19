package com.DocumentDataScrapper.XmlReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlDataFinder {




    public static NodeList FindingDataByNodenameFromXml(String nodeName) throws IOException, ParserConfigurationException, SAXException {
        Document doc = XmlReader.ReadingFromXmlDocument();

        NodeList nodeGroupList = doc.getElementsByTagName(nodeName);


        return nodeGroupList;
    }

    public static NodeList FindingDataByLabelNameFromXml(String mainNodeName, String labelName, String subNodeName) throws IOException, ParserConfigurationException, SAXException {

        NodeList nodeGroupList = FindingDataByNodenameFromXml(mainNodeName);
        NodeList detailList = null;
        for (int i = 0; i < nodeGroupList.getLength(); i++) {
            Element labelGroup = (Element) nodeGroupList.item(i);
            String lableValue = String.valueOf(labelGroup.getAttributes().item(0));
            if (lableValue.contains(labelName)) {
                detailList = labelGroup.getElementsByTagName(subNodeName);
                break;
            }
            //add a Else part as log message
        }
        return detailList;
    }

//    public static List<Detail> SetDataFromXMLToPojo(NodeList detailList, Class classname) throws JAXBException {
//
//
//        JAXBContext jaxbContext = JAXBContext.newInstance(classname);
//        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        List<Detail> eligibilityAlertlist = new ArrayList<>();
//        Detail detail;
//
//        for (int i=0; i<detailList.getLength();i++) {
//            detail = (Detail) unmarshaller.unmarshal(detailList.item(i));
//            eligibilityAlertlist.add(detail);
//        }
//
//        return eligibilityAlertlist;
//
//
//    }
}
