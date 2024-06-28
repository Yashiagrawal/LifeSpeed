package formsAppearance;

import java.io.File;
import java.io.FileWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RegForms {

	public static String PolicType;
	public static String JPMProductType;
	public static String mydata;

	String expectedData2 = "USPB";

	public static File file1 = new File(System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\Txpt.xml");

	public static void main(String[] args) throws Exception {
		try {

			// creating a constructor of file class and parsing an XML file
			// an instance of factory that gives a document builder

			{

				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				// an instance of builder to parse the specified xml file
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(file1);
				doc.getDocumentElement().normalize();
				System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
				NodeList nodeList = doc.getElementsByTagName("Policy");
				System.out.println(nodeList);
				// nodeList is not iterable, so we are using for loop
				for (int itr = 0; itr < nodeList.getLength(); itr++) {
					Node node = nodeList.item(itr);
					System.out.println("\nNode Name :" + node.getNodeName());
					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) node;

						PolicType = eElement.getElementsByTagName("Type").item(0).getTextContent();
						System.out.println(PolicType);

						JPMProductType = eElement.getElementsByTagName("JPMCProductType").item(0).getTextContent();
						System.out.println(JPMProductType);

					}

				}

			}

			{
				File file = new File(
						System.getProperty("user.dir") + "//src//test//java//sMAL_File//POS_Protectioniul.xml");

				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document doc = builder.parse(file);

				NodeList nodes = doc.getElementsByTagName("saml2:Attribute");
				System.out.println(nodes.getLength());
				for (int i = 8; i < nodes.getLength(); i++) {
					String cData = nodes.item(i).getTextContent();

					System.out.println(cData);

					String directoryPath = "C:\\Users\\yashi.agrawal\\Downloads\\LifeSpeed_Stability (1)\\LifeSpeed_Stability\\src\\test\\java\\sMAL_File\\";
					String fileName = "newfile.xml";

					File filef = new File(directoryPath + fileName);

					FileWriter fWriter = new FileWriter(filef);

					fWriter.write(cData);

					fWriter.close();

					System.out.println("File is created successfully with the content.");

					// String fileNamse = directoryPath + fileName;
					DocumentBuilder builder2 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					Document doc2 = builder2.parse(filef);
					NodeList nodes2 = doc2.getElementsByTagName("BusinessUnit");
					System.out.println(nodes2.getLength());

					int itr = 1;
					Node node = nodes2.item(itr);
					System.out.println("\nNode Name :" + node.getNodeName());
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node;
						// System.out.println("mydata " +
						// eElement.getElementsByTagName("IdValue").item(0).getTextContent());
						mydata = eElement.getElementsByTagName("IdValue").item(0).getTextContent();
						System.out.println(mydata);
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

		if ((PolicType.equalsIgnoreCase("Variable Universal Life") && mydata.equalsIgnoreCase("JPMA"))
				|| (JPMProductType.equalsIgnoreCase("HLTC") && mydata.equalsIgnoreCase("JPMA"))
				|| (PolicType.equalsIgnoreCase("Variable") && mydata.equalsIgnoreCase("JPMA"))
				|| (PolicType.equalsIgnoreCase("Variable Whole Life") && mydata.equalsIgnoreCase("JPMA"))) {

			System.out.println("JPMA - Reg BI Brochure and Client Relationship Summary");

		} else if ((PolicType.equalsIgnoreCase("Variable Universal Life") && mydata.equalsIgnoreCase("CWM"))
				|| (JPMProductType.equalsIgnoreCase("HLTC") && mydata.equalsIgnoreCase("CWM"))
				|| (PolicType.equalsIgnoreCase("Variable") && mydata.equalsIgnoreCase("CWM"))
				|| (PolicType.equalsIgnoreCase("Variable Whole Life") && mydata.equalsIgnoreCase("CWM"))) {

			System.out.println("CWM - Reg BI Brochure and Client Relationship Summary");

		} else if ((PolicType.equalsIgnoreCase("Variable Universal Life") && mydata.equalsIgnoreCase("USPB"))
				|| (JPMProductType.equalsIgnoreCase("HLTC") && mydata.equalsIgnoreCase("USPB"))
				|| (PolicType.equalsIgnoreCase("Variable") && mydata.equalsIgnoreCase("USPB"))
				|| (PolicType.equalsIgnoreCase("Variable Whole Life") && mydata.equalsIgnoreCase("USPB"))) {

			System.out.println("USPB - Reg BI Brochure and Client Relationship Summary");

		} else {

			System.out.println("No reg form must appear");
		}

	}

}
