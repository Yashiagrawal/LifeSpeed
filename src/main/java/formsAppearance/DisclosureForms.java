package formsAppearance;

import java.io.File;
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

public class DisclosureForms extends TestBase{

	public static String State;
	public static String ProductType;

	//public static File file = new File(System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\Txn.xml");

	//public static void main(String argv[]) {
		
		public static void Disc() {
		try {
			// creating a constructor of file class and parsing an XML file
			// an instance of factory that gives a document builder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// an instance of builder to parse the specified xml file
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			//Document doc = db.parse(file);
			 FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\FeedDetails\\FeedStatus.xml");
		        Reader reader = new InputStreamReader(fileInputStream, "UTF-8");
		        InputSource inputSource = new InputSource(reader); 
			    
			    Document doc = dBuilder.parse(inputSource);
			doc.getDocumentElement().normalize();
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			{
				NodeList nodeList = doc.getElementsByTagName("txnDetails");
				System.out.println(nodeList);
				// nodeList is not iterable, so we are using for loop
				for (int itr = 0; itr < nodeList.getLength(); itr++) {
					Node node = nodeList.item(itr);
					System.out.println("\nNode Name :" + node.getNodeName());
					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) node;

						State = eElement.getElementsByTagName("SolicitationStateCode").item(0).getTextContent();
						System.out.println(State);
			            TestStepData.setCellData(TestController.TestCaseID, "StateForm", 2, State);

					}

				}

				

			}

			{
				NodeList nodeList = doc.getElementsByTagName("Policy");
				System.out.println(nodeList);
				// nodeList is not iterable, so we are using for loop
				for (int itr = 0; itr < nodeList.getLength(); itr++) {
					Node node = nodeList.item(itr);
					System.out.println("\nNode Name :" + node.getNodeName());
					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) node;

						ProductType = eElement.getElementsByTagName("JPMCProductType").item(0).getTextContent();
						System.out.println(ProductType);
			            TestStepData.setCellData(TestController.TestCaseID, "ProdJPMC", 2, ProductType);

						FormNYDD();

					}

				}

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void FormNYDD() {

		if (ProductType != "VUL" && State != "NY") {

			System.out.println("Life Insurance Authorization Disclosure Document (Non-NY Non-Variable IADD)");

		} else if (ProductType == "VUL" && State != "NY") {
			System.out.println("Insurance Authorization Disclosure Document (Non-NY Variable IADD)");

		} else if (ProductType != "VUL" && State == "NY") {

			System.out.println("New York Insurance Authorization Disclosure Document (NY Non-Variable IADD)");

		} else if (ProductType == "VUL" && State == "NY") {

			System.out.println("New York Insurance Authorization Disclosure Document  (NY Variable IADD)");

		} else {

			System.out.println("No Disclosure form");
		}

	}

}
