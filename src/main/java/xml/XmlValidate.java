package xml;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXParseException;

public class XmlValidate {

	
	public static String xsdFilePath = "C:/Users/agraway/Desktop/LifeSpeed-mainy/LifeSpeed-main/src/main/java/xml/TXLife2.29..xsd";
	
	public  static String xmlFilePath = "C:/Users/agraway/Desktop/LifeSpeed-mainy/LifeSpeed-main/src/test/java/sMAL_File/Carrier103.xml";
	
	public static void main(String[] args) throws SAXParseException{
        try {
            // Path to your XSD schema file
            

            // Path to your XML file
           

            // Create a SchemaFactory and load the XSD schema
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(xsdFilePath));

            // Create a Validator
            Validator validator = schema.newValidator();

          
            
            validator.validate(new StreamSource(new File(xmlFilePath)));

            System.out.println(xmlFilePath + " adheres to the schema.");
        } catch (Exception e) {
         System.out.println("Not as per schema");
            e.printStackTrace();
        }
}
}