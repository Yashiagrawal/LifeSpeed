package formsAppearance;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class FormMappingRep {
	
	public static String State;
	public static String IsRepl;


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
				NodeList nodeList = doc.getElementsByTagName("txnDetails");
				System.out.println(nodeList);
				// nodeList is not iterable, so we are using for loop
				for (int itr = 0; itr < nodeList.getLength(); itr++) {
					Node node = nodeList.item(itr);
					System.out.println("\nNode Name :" + node.getNodeName());
					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) node;
						State = eElement.getElementsByTagName("SolicitationState").item(0).getTextContent();
						System.out.println(State);
						String IsRepl = eElement.getElementsByTagName("IsRep").item(0).getTextContent();
						System.out.println(IsRepl);
						
						CompareRepform();
						theform();

					}

				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void CompareRepform() {

		if (IsRepl != "false") {

			System.out.println("Life Insurance Replacement Client Acknowledgement.");

		} else {
			System.out.println("Replacement form is Not generated");

		}
		
	}
	
	
	
	
	public static void theform() {
		
		
		if (State != "NewYork") {
			System.out.println("Life Insurance Authorization Form");
		}

		else {
			System.out.println("Life Insurance Authorization FormNy");
			System.out.println("New York Suitability and Best Interest Form");
		}
		
	}

}
