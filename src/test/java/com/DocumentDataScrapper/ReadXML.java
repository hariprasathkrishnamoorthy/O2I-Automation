package com.DocumentDataScrapper;

import com.pojo.EligibilityAlertPojo.Detail;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
import java.util.ArrayList;
import java.util.List;

public class ReadXML {



    public static List<Detail> readXMLfile() throws IOException, SAXException, ParserConfigurationException, JAXBException {

        {
            File inputFile = new File("D:\\Users\\pradeep\\IdeaProjects\\Excelfiles\\Auto Eligibility Response Report.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList labelGroupList = doc.getElementsByTagName("LabelGroup");
            NodeList detailList = null;
            for (int i = 0; i < labelGroupList.getLength(); i++) {



                Element labelGroup = (Element) labelGroupList.item(i);
                String lableValue = String.valueOf(labelGroup.getAttributes().item(0));



                if (lableValue.contains("Eligibility Alert:")) {
                    detailList = labelGroup.getElementsByTagName("Detail");
                    break;


                }
//                else
//                {
//                    // log message should be printed here
//                }
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(Detail.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            assert detailList != null;
            List<Detail> eligibilityAlertlist = new ArrayList<>();
            Detail detail;


            for (int i=0; i<detailList.getLength();i++) {
                detail = (Detail) unmarshaller.unmarshal(detailList.item(i));
                eligibilityAlertlist.add(detail);
            }

            return eligibilityAlertlist;



        }
    }
    }

