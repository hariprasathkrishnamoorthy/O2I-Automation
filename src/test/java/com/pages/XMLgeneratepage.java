package com.pages;

import com.managers.FileReaderManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.pages.CSVgeneratepage.getRfaidfromtype;

public class XMLgeneratepage
{


    public void generateXMLandzip()
    {
        try {
            // Load the Excel workbook
            Workbook workbook = new XSSFWorkbook(System.getProperty("user.dir")+File.separator+"Downloads\\GridData\\PatientRelatedTask_WF1.xlsx");

            // Get the first sheet (you can change the sheet index as needed)
            Sheet sheet = workbook.getSheetAt(0);


            int rowCount = Integer.parseInt(FileReaderManager.getJsonConfigReader().getJsonString("HCHB.Rowcount")) - 1;


            // Loop through all rows in the Excel sheet
            for (int rowIndex = 1; rowIndex <=rowCount; rowIndex++) {

                int Episodeid=0;
                Row row = sheet.getRow(rowIndex);

                // Create a new XML document for each row
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document xmlDoc = docBuilder.newDocument();
                xmlDoc.setXmlStandalone(true);


                // Create the root element for the XML document
                Element rootElement = xmlDoc.createElement("ASSESSMENT");
                xmlDoc.appendChild(rootElement);




                // Loop through the cells in the Excel row and create XML elements
                for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
                    if (cellIndex==0 || cellIndex==4 || cellIndex==1 || cellIndex==2  || cellIndex==19)
                    {

                        if(cellIndex==19)
                        {
                            Cell cell = row.getCell(cellIndex);
                            Episodeid= (int) cell.getNumericCellValue();


                        }



                         if(cellIndex==0)
                         {
                             Cell cell = row.getCell(cellIndex);
                             Element cellElement = xmlDoc.createElement("M0090_INFO_COMPLETED_DT");
                             cellElement.appendChild(xmlDoc.createTextNode( getInputDateStr( cell.toString())));
                             rootElement.appendChild(cellElement);

                         }

                        if(cellIndex==4)
                        {
                            Cell cell = row.getCell(cellIndex);
                            String[] Namesplit = cell.toString().split(",");
                            String[] firstAndMiddleNameParts = Namesplit[1].trim().split("\\s+");
                            String firstName ="";
                            String middleName = "";
                            if(firstAndMiddleNameParts.length>=2)
                            {
                                 firstName = firstAndMiddleNameParts[0];
                                 middleName = firstAndMiddleNameParts[1];
                            }
                            else
                            {
                                 firstName = firstAndMiddleNameParts[0];
                                 middleName = "^";

                            }


                            Element cellElement1 = xmlDoc.createElement("M0040_PAT_FNAME");
                            cellElement1.appendChild(xmlDoc.createTextNode(firstName.trim()));
                            rootElement.appendChild(cellElement1);

                            Element cellElement2 = xmlDoc.createElement("M0040_PAT_LNAME");
                            cellElement2.appendChild(xmlDoc.createTextNode( Namesplit[0].trim()));
                            rootElement.appendChild(cellElement2);

                            Element cellElement3 = xmlDoc.createElement("M0040_PAT_MI");
                            cellElement3.appendChild(xmlDoc.createTextNode( middleName.trim().replaceAll("\\.$", "")));
                            rootElement.appendChild(cellElement3);

                        }

                        if(cellIndex==1)
                        {
                            Cell cell = row.getCell(cellIndex);
                            Element cellElement = xmlDoc.createElement("M0100_ASSMT_REASON");
                            cellElement.appendChild(xmlDoc.createTextNode(getRfaidfromtype(cell.toString())));
                            rootElement.appendChild(cellElement);


                        }

                        if(cellIndex==2)
                        {
                            Cell cell = row.getCell(cellIndex);
                            Element cellElement = xmlDoc.createElement("M0020_PAT_ID");
                            cellElement.appendChild(xmlDoc.createTextNode(cell.toString()));
                            rootElement.appendChild(cellElement);


                        }


                     }
                }



                // Load the source XML document
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document sourceDoc = builder.parse(new File(System.getProperty("user.dir")+File.separator+"\\AutomationConfig\\XML_Template.xml"));

                // Import the nodes from the source XML to the new XML
               // Node importedNode = xmlDoc.importNode(sourceDoc.getDocumentElement(), true);

                Element sample = (Element) sourceDoc.getElementsByTagName("sample").item(0);

                NodeList sampleChildren = sample.getChildNodes();

                for (int i = 0; i < sampleChildren.getLength(); i++) {
                    // Append the imported nodes below the "ASSESSMENT" element
                    Node child = sampleChildren.item(i);
                    rootElement.appendChild(xmlDoc.importNode(child,true));
                }

                // Formatting the XML document as needed for Space, Indentation




                // Generate a unique XML file name for each row (e.g., row_1.xml, row_2.xml, etc.)
                String outputFileName = "OASIS_PatName_val_" + (rowIndex) + "_"+ Episodeid+"_{55AE4496}.xml";

                // Save the XML document to a separate file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(xmlDoc);
                StreamResult result = new StreamResult(new FileOutputStream(outputFileName));
                transformer.transform(source, result);

                System.out.println("Row " + (rowIndex) + " converted to XML: " + outputFileName);
            }



            // Close the workbook
            workbook.close();
            zipXMLFiles( System.getProperty("user.dir"),"XMLFiles.zip");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String getInputDateStr(String inputDateStr)
    {


        // Create a SimpleDateFormat object to parse the input format
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        // Create a SimpleDateFormat object to format the output as "yyyyMMdd"
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyyMMdd");

        String outputDateStr="";

        try {
            // Parse the input date string
            Date inputDate = inputDateFormat.parse(inputDateStr);

            // Format the date as "yyyyMMdd"
             outputDateStr = outputDateFormat.format(inputDate);

            // Print the formatted date
            System.out.println("Formatted Date: " + outputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDateStr;
    }




    public static void zipXMLFiles(String sourceDirectory, String zipFileName) {
        try {
            // Create an output stream to the zip file
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            // Get a list of XML files in the source directory
            File sourceDir = new File(sourceDirectory);
            File[] files = sourceDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml") && !name.equalsIgnoreCase("pom.xml"));

            if (files != null) {
                // Iterate through the XML files (excluding "pom.xml") and add them to the zip file
                for (File file : files) {
                    FileInputStream fis = new FileInputStream(file);
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zipOut.putNextEntry(zipEntry);

                    // Read the file content and write it to the zip file
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zipOut.write(buffer, 0, length);
                    }

                    // Close the current entry
                    fis.close();
                }

                // Close the zip output stream
                zipOut.close();
            }

            System.out.println("XML files have been successfully zipped to " + zipFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




    public  void deleteXMLFilesExceptPom(String directoryPath) {
        File directory = new File(directoryPath);

        // Check if the specified path exists and is a directory
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            // Iterate through the files in the directory
            assert files != null;
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".xml") && !file.getName().equalsIgnoreCase("pom.xml")) {

                    waitForFileRelease(file);
                   if( file.setWritable(true))
                    {
                        // Delete XML files (excluding "pom.xml")
                        if (file.delete()) {
                            System.out.println("Deleted: " + file.getName());
                        } else {
                            System.err.println("Failed to delete: " + file.getName());
                            if (isFileInUse(file)) {
                                System.out.println("File in use---: " + file.getName());

                            }
                            // Additional diagnostics
                            if (file.isDirectory() && Objects.requireNonNull(file.list()).length > 0) {
                                System.out.println("Directory is not empty. Delete its contents first.");
                            }

                            if (file.canWrite()) {
                                System.out.println("File is read-only. Change file attributes if needed.");
                            }
                            file.deleteOnExit();

                        }
                    }
                }
            }

            System.out.println("XML files (except 'pom.xml') have been deleted.");
        } else {
            System.err.println("Invalid directory path: " + directoryPath);
        }
    }




    private static void waitForFileRelease(File file) {
        while (isFileInUse(file)) {
            try {
                System.out.println("File is still in use. Waiting...");
                Thread.sleep(2000); // Wait for 1 second before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isFileInUse(File file) {
        try {
            // Attempt to acquire an exclusive lock on the file
            try (FileChannel channel = FileChannel.open(file.toPath(), StandardOpenOption.WRITE)) {
                try (FileLock lock = channel.tryLock()) {
                    // If the lock is null, another process is using the file
                    return lock == null;
                }
            }
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
            return true; // Treat as if the file is in use
        }
    }





    public static void formatXml(Document xmlDoc) {
        try {
            // Create a transformer
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Set properties for formatting
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            // Transform the DOMSource to a StreamResult
            DOMSource source = new DOMSource(xmlDoc);
            StreamResult result = new StreamResult(String.valueOf(xmlDoc));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







}
