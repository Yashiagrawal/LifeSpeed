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

public class FormAppearingTrust extends TestBase {
	public static String Ownername;
	public static String IsSelectedTrust;

	//public static File file = new File(System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\Txn.xml");

	// public static void main(String argv[]) {
	public static void TrustEntityForms() {
		try {
			// creating a constructor of file class and parsing an XML file
			// an instance of factory that gives a document builder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// an instance of builder to parse the specified xml file
			// DocumentBuilder db = dbf.newDocumentBuilder();
			// Document doc = db.parse(file);
			// doc.getDocumentElement().normalize();
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();

			FileInputStream fileInputStream = new FileInputStream(
					System.getProperty("user.dir") + "\\FeedDetails\\FeedStatus.xml");
			Reader reader = new InputStreamReader(fileInputStream, "UTF-8");
			InputSource inputSource = new InputSource(reader);

			Document doc = dBuilder.parse(inputSource);
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
						IsSelectedTrust = eElement.getElementsByTagName("Selected").item(0).getTextContent();
						System.out.println(IsSelectedTrust);
			            TestStepData.setCellData(TestController.TestCaseID, "TrusteeForm", 2, IsSelectedTrust);

						Ownername = eElement.getElementsByTagName("Name").item(0).getTextContent();
						System.out.println(Ownername);
			            TestStepData.setCellData(TestController.TestCaseID, "OwnerTrust", 2, IsSelectedTrust);


						CompareformTrust();
						OwnerTrustEntity();

					}

				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void CompareformTrust() {

		if (Ownername.equalsIgnoreCase("Trust")) {

			System.out.println("Trustee Certification for Insurance Products");

		} else {
			System.out.println("Trust form is Not generated");

		}

	}

	public static void OwnerTrustEntity() {

		if (Ownername.equalsIgnoreCase("Entity") || Ownername.equalsIgnoreCase("Trust")) {

			System.out.println("Life Policy Signatory Information Sheet");

		} else {
			System.out.println("Signatory form is Not generated");

		}

	}

}
