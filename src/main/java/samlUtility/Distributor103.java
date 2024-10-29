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

import keywords.Keywords;


public class Distributor103 extends Keywords{
	
	public static String Product;
	public static String Carrier;
	public static String Plan;
	public static String Cusip;
	public static String Paymnt;
	
	public static void Distributor() {
		try {
			// creating a constructor of file class and parsing an XML file
			// an instance of factory that gives a document builder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// an instance of builder to parse the specified xml file
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			//Document doc = db.parse(file);
			 FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//sMAL_File//Distributor103.xml");
		        Reader reader = new InputStreamReader(fileInputStream, "UTF-8");
		        InputSource inputSource = new InputSource(reader); 
			    
			    Document doc = dBuilder.parse(inputSource);
			doc.getDocumentElement().normalize();
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			{
				NodeList nodeList = doc.getElementsByTagName("Policy");
				System.out.println(nodeList);
				// nodeList is not iterable, so we are using for loop
				for (int itr = 0; itr < nodeList.getLength(); itr++) {
					Node node = nodeList.item(itr);
					System.out.println("\nNode Name :" + node.getNodeName());
					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) node;

						Product = eElement.getElementsByTagName("ProductCode").item(0).getTextContent();
						System.out.println(Product);
						Carrier = eElement.getElementsByTagName("CarrierCode").item(0).getTextContent();
						System.out.println(Carrier);
						Plan = eElement.getElementsByTagName("PlanName").item(0).getTextContent();
						System.out.println(Carrier);
						Cusip = eElement.getElementsByTagName("CusipNum").item(0).getTextContent();
						System.out.println(Cusip);
						Paymnt = eElement.getElementsByTagName("PaymentAmt").item(0).getTextContent();
						System.out.println(Paymnt);


					}

				}

				

			}

//			{
//				NodeList nodeList = doc.getElementsByTagName("Policy");
//				System.out.println(nodeList);
//				// nodeList is not iterable, so we are using for loop
//				for (int itr = 0; itr < nodeList.getLength(); itr++) {
//					Node node = nodeList.item(itr);
//					System.out.println("\nNode Name :" + node.getNodeName());
//					if (node.getNodeType() == Node.ELEMENT_NODE) {
//
//						Element eElement = (Element) node;
//
//						ProductType = eElement.getElementsByTagName("JPMCProductType").item(0).getTextContent();
//						System.out.println(ProductType);
//			            TestStepData.setCellData(TestController.TestCaseID, "ProdJPMC", 2, ProductType);
//
//						FormNYDD();
//
//					}
//
//				}
//
//			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}


	
		
		
}
