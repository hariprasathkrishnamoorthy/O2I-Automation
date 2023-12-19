package com.sample;

import com.pojo.CoordinationNotesPojo;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ReadCSVFile {
    public ReadCSVFile() throws ParserConfigurationException, IOException, SAXException{
    }




    public static void main(String ards[]) throws ParserConfigurationException, IOException, SAXException, JAXBException {
        File xmlFile = new File("D:\\Users\\pradeep\\IdeaProjects\\Excelfiles\\Client Coordination Notes Report.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        doc.getDocumentElement().normalize();
        NodeList studentNodes = doc.getElementsByTagName("Detail");

        JAXBContext jaxbContext = JAXBContext.newInstance(CoordinationNotesPojo.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        CoordinationNotesPojo cNotes = (CoordinationNotesPojo) unmarshaller.unmarshal(studentNodes.item(0));




    }
    }


