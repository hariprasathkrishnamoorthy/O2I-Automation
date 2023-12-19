package com.DocumentDataScrapper.XmlReader;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlReader {

    public static Document ReadingFromXmlDocument() throws IOException, SAXException, ParserConfigurationException {
//        File inputFile = new File("D:\\Users\\pradeep\\IdeaProjects\\Excelfiles\\Client Coordination Notes Report.xml");
        File inputFile = new File("D:\\Users\\pradeep\\IdeaProjects\\Excelfiles\\Auto Eligibility Response Report.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        return doc;

    }
}
