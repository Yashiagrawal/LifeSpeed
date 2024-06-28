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

public class EntityForm extends TestBase {

	public static String TrustType;
	public static String OwnerType;

	public static File file1 = new File(System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\Txcm.xml");

	// public static void main(String[] args) throws Exception {
	public static void EntityOwnerForm() {
		try {

			// creating a constructor of file class and parsing an XML file
			// an instance of factory that gives a document builder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// an instance of builder to parse the specified xml file

			DocumentBuilder dBuilder = dbf.newDocumentBuilder();

			FileInputStream fileInputStream = new FileInputStream(
					System.getProperty("user.dir") + "\\FeedDetails\\FeedStatus.xml");
			Reader reader = new InputStreamReader(fileInputStream, "UTF-8");
			InputSource inputSource = new InputSource(reader);

			Document doc = dBuilder.parse(inputSource);
			//DocumentBuilder db = dbf.newDocumentBuilder();
			//Document doc = db.parse(file1);
			doc.getDocumentElement().normalize();
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			{
				NodeList nodeList = doc.getElementsByTagName("OwnerEntityType");
				System.out.println(nodeList);
				// nodeList is not iterable, so we are using for loop
				for (int itr = 0; itr < nodeList.getLength(); itr++) {
					Node node = nodeList.item(itr);
					System.out.println("\nNode Name :" + node.getNodeName());
					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) node;

						OwnerType = eElement.getElementsByTagName("Name").item(0).getTextContent();
						System.out.println(OwnerType);
						
			            TestStepData.setCellData(TestController.TestCaseID, "EntityOwnerType", 2, OwnerType);


					}

				}

			}

			{
				NodeList nodeList = doc.getElementsByTagName("trusteePersons");
				System.out.println(nodeList);
				// nodeList is not iterable, so we are using for loop
				for (int itr = 0; itr < nodeList.getLength(); itr++) {
					Node node = nodeList.item(itr);
					System.out.println("\nNode Name :" + node.getNodeName());
					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) node;

						TrustType = eElement.getElementsByTagName("Type").item(0).getTextContent();
						System.out.println(TrustType);
						
			            TestStepData.setCellData(TestController.TestCaseID, "EntitTrustType", 2, TrustType);


						FormTrustteEntity();

					}

				}

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void FormTrustteEntity() {

		if (OwnerType.equalsIgnoreCase("Trust") && TrustType.equalsIgnoreCase("Non-Individual")
				|| OwnerType.equalsIgnoreCase("Entity")) {

			System.out.println("Entity Resolution Certification");

		} else {

			System.out.println("Entity Resolution Certification form not found");
		}

	}

}
