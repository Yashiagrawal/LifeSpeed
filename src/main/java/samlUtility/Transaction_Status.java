package samlUtility;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import testBase.TestBase;
import testController.TestController;

public class Transaction_Status extends TestBase
{
	
	public static String NonEngLang;
	public static String ForeignLangue;
	
	public static void status_103()
	{
		
		
		try {
		    //File fXmlFile = new File(System.getProperty("user.dir")+"\\FeedDetails\\FeedStatus.xml");
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    
		    FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\FeedDetails\\FeedStatus.xml");
	        Reader reader = new InputStreamReader(fileInputStream, "UTF-8");
	        InputSource inputSource = new InputSource(reader); 
		    
		    Document doc = dBuilder.parse(inputSource);
		    doc.getDocumentElement().normalize();

		  //  System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		    NodeList nList = doc.getElementsByTagName("soap:Body");
		  //  System.out.println("----------------------------");

		    for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		     //   System.out.println("\nCurrent Element :" + nNode.getNodeName());
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		            Element eElement = (Element) nNode;
		            
		            String status103 = eElement.getElementsByTagName("Status").item(0).getTextContent();
		            System.out.println("103 Status : "+ status103);
		            TestStepData.setCellData(TestController.TestCaseID, "103_Status", TestController.TD, status103);
		        }
		    }
		    } catch (Exception e) {
		    e.printStackTrace();
		    }
		
		
		//return "Pass";
	}

	
	
	public static void status_RTSM()
	{
		
		
		try {
		    //File fXmlFile = new File(System.getProperty("user.dir")+"\\FeedDetails\\FeedStatus.xml");
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    
		    FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\FeedDetails\\FeedStatus.xml");
	        Reader reader = new InputStreamReader(fileInputStream, "UTF-8");
	        InputSource inputSource = new InputSource(reader); 
		    
		    Document doc = dBuilder.parse(inputSource);
		    doc.getDocumentElement().normalize();

		  //  System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		    NodeList nList = doc.getElementsByTagName("soap:Body");
		  //  System.out.println("----------------------------");

		    for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		     //   System.out.println("\nCurrent Element :" + nNode.getNodeName());
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		            Element eElement = (Element) nNode;
		            
		            String statusRTSM = eElement.getElementsByTagName("Status").item(0).getTextContent();
		            System.out.println("RTSM Status : "+ statusRTSM);
		            TestStepData.setCellData(TestController.TestCaseID, "RTSM_Status", TestController.TD, statusRTSM);
		        }
		    }
		    } catch (Exception e) {
		    e.printStackTrace();
		    }
		
		
		//return "Pass";
	}
	
	
	public static void status_TransXML()
	{
		
		
		try {
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    
		    FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\FeedDetails\\FeedStatus.xml");
	        Reader reader = new InputStreamReader(fileInputStream, "UTF-8");
	        InputSource inputSource = new InputSource(reader); 
		    
		    Document doc = dBuilder.parse(inputSource);
		    doc.getDocumentElement().normalize();

		  //  System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		    NodeList nList = doc.getElementsByTagName("AgentReport");
		  //  System.out.println("----------------------------");

		    for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		     //   System.out.println("\nCurrent Element :" + nNode.getNodeName());
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		            Element eElement = (Element) nNode;
		            
		           
		            NonEngLang = eElement.getElementsByTagName("NonEngBusinessLang").item(0).getTextContent();
					System.out.println(NonEngLang);
					ForeignLangue = eElement.getElementsByTagName("NonEngBusinessLangName").item(0)
							.getTextContent();
					System.out.println(ForeignLangue);
		            TestStepData.setCellData(TestController.TestCaseID, "EngLang", TestController.TD, NonEngLang);
		            TestStepData.setCellData(TestController.TestCaseID, "TheLanguage", TestController.TD, ForeignLangue);

		        }
		    }
		    } catch (Exception e) {
		    e.printStackTrace();
		    }
		
		
		//return "Pass";
	}
	
	
	
	
}
