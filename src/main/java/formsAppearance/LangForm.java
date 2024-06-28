package formsAppearance;

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

public class LangForm extends TestBase{
	
	
	public static String NonEngLang;
	public static String ForeignLangue;
	
	
	
	
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
				//	ForeignLangue = eElement.getElementsByTagName("NonEngBusinessLangName").item(0).getTextContent();
							
					//System.out.println(ForeignLangue);
		            TestStepData.setCellData(TestController.TestCaseID, "EngLang", 2, NonEngLang);
		           // TestStepData.setCellData(TestController.TestCaseID, "TheLanguage", 2, ForeignLangue);

		        }
		    }
		    } catch (Exception e) {
		    e.printStackTrace();
		    }
		
		
	}
	
	public static void CompareformforeignContect() {

		if ( NonEngLang.equalsIgnoreCase("Yes")) {

			
			System.out.println("Foreign Language Affirmation For Insurance Products");


		} else if( NonEngLang.equalsIgnoreCase("No")) {
			
			System.out.println("Must Not appear");


		}

	}
	

	
}
