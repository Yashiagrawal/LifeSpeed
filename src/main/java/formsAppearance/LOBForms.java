package formsAppearance;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class LOBForms {

	public static void main(String[] args) throws Exception {
		File file = new File(System.getProperty("user.dir") + "//src//test//java//sMAL_File//POS_Protectioniul.xml");

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
				String mydata = eElement.getElementsByTagName("IdValue").item(0).getTextContent();
				System.out.println(mydata);

				String expectedData = "JPMA";
				String expectedData1 = "CWM";
				String expectedData2 = "USPB";

				if (mydata.equalsIgnoreCase(expectedData)) {

					System.out.println("JPMS Privacy Notice");
				} else if (mydata.equalsIgnoreCase(expectedData1)) {

					System.out.println("Chase Privacy Notice");

				} else if (mydata.equalsIgnoreCase(expectedData2)) {
					System.out.println("J.P. Morgan Private Bank Privacy Notice");

				} else {
					System.out.println("No LOB Form");
				}

			}

		}

	}

}
