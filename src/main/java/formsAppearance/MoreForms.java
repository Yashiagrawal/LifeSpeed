package formsAppearance;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MoreForms {

	public static String NonEngLang;
	public static String ForeignLangue;

	public static File file = new File(System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\Txn.xml");

	public static void main(String argv[]) {
		try {
			// creating a constructor of file class and parsing an XML file
			// an instance of factory that gives a document builder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// an instance of builder to parse the specified xml file
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			{
				NodeList nodeList = doc.getElementsByTagName("AgentReport");
				System.out.println(nodeList);

				// nodeList is not iterable, so we are using for loop

				for (int itr = 0; itr < nodeList.getLength(); itr++) {
					Node node = nodeList.item(itr);
					System.out.println("\nNode Name :" + node.getNodeName());
					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) node;
						NonEngLang = eElement.getElementsByTagName("NonEngBusinessLang").item(0).getTextContent();
						System.out.println(NonEngLang);
						ForeignLangue = eElement.getElementsByTagName("NonEngBusinessLangName").item(0)
								.getTextContent();
						System.out.println(ForeignLangue);

						CompareformTrustContact();
					}

				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void CompareformTrustContact() {

		if (ForeignLangue.equalsIgnoreCase("English") && NonEngLang.equalsIgnoreCase("No")) {

			System.out.println("Must Not appear ");

		} else {

			System.out.println("Trusted Contact Person Authorization Form");
		}

	}

}
