package aPI;

import io.restassured.RestAssured;
import io.restassured.response.Response;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;

import static io.restassured.RestAssured.given;
public class StatuschangeAPI {
	
	public static void main(String[] args) {
        // Define the endpoint and other parameters
        String endpoint = "https://lifespeed.uat.zinnia.com/AppServices/WorkflowAdvancementService.svc";
        String payloadFilePath = "C:\\Users\\agraway\\Desktop\\LifeSpeed-mainy\\LifeSpeed-main\\src\\test\\java\\aPI\\TM-UAT-soapui-project (1) (1).xml"; // Update the path to your XML file
        String keyStorePath = "C:\\Users\\agraway\\Desktop\\LifeSpeed-mainy\\LifeSpeed-main\\src\\test\\java\\aPI\\lifespeed.uat.zinnia.com (1).pfx"; // Update the path to your keystore
        String keyStorePassword = "12345"; // Replace with your keystore password
     char[] A = keyStorePassword.toCharArray();

        // Load the keystore
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
        	
        //    keyStore.load(Files.newInputStream(Paths.get(keyStorePath)), null);
          keyStore.load(Files.newInputStream(Paths.get(keyStorePath)), A);
        

            // Read the XML payload
            String xmlPayload = new String(Files.readAllBytes(Paths.get(payloadFilePath)), "UTF-8");

            // Configure Rest Assured
            RestAssured.useRelaxedHTTPSValidation(); // Disable SSL validation if necessary
            RestAssured.baseURI = endpoint;
            
            // Send the request
          //  Response response = given().log().all()
            Response response = given().log().all()
                    .header("Content-Type", "text/xml; UTF-8")
                    .header("SOAPAction", "Action") // Set the correct SOAP action
                    .body(xmlPayload)
                    .when()
                    .post()
                    .then()
                    .log().all()
                    .extract()
                    .response();

            // Print the response
            System.out.println("Response Status Code: " + response.getStatusCode());
            //System.out.println("Response Body: " + response.asString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	
	
	
}
