package com.sample;

import com.pojo.EligibilityAlertPojo.Detail;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XML {
    public static void main(String[] args) {
        try {
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
//
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(Detail.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            assert detailList != null;
            List<Detail> list = new ArrayList<>();
            Detail detail;


            for (int i=0; i<detailList.getLength();i++) {
                detail = (Detail) unmarshaller.unmarshal(detailList.item(i));
                list.add(detail);
            }
            System.out.println(list.get(0).getTextbox75());
            System.out.println(list.get(1).getTextbox8());
            System.out.println(list.get(2).getTextbox8());



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}