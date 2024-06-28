package formsAppearance;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileWriter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.w3c.dom.Element;

public class LOBForms {

	public static WebDriver driver;
	
	public static String mydata;

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
				OpenForm();
			}

		}

	}

	public static String OpenForm() throws InterruptedException {

		WebDriverManager.firefoxdriver().setup();

		WebDriver driver = new FirefoxDriver();

		driver.get("https://lifespeed.uat.ebixexchange.com/lifespeedplus/login.aspx");
		driver.findElement(By.id("txtUserID")).sendKeys("Yashig");
		driver.findElement(By.id("txtPassword")).sendKeys("p");
		driver.findElement(By.id("btnLogin")).click();
		WebElement management = driver.findElement(By.xpath("//a[text()='Management ']"));
		management.click();
		Thread.sleep(500);
		WebElement mgntool = driver.findElement(By.xpath("//*[@class='ls-menu-item'][contains(.,'Management Tools')]"));
		mgntool.click();
		Actions a = new Actions(driver);
		a.moveToElement(mgntool).perform();

		WebElement ViewNBFL = driver.findElement(By.xpath("//a[text()='Feed Management']"));
		ViewNBFL.click();
		Thread.sleep(10000);

		WebElement Search1Bar = driver.findElement(By.xpath("//a[@class='btn btn-success']"));
		Search1Bar.click();
		Thread.sleep(500);

		Select TrID = new Select(driver.findElement(By.xpath("//select[@class='search-panel-ctrl-fields']")));
		TrID.selectByValue("AppID");
		Thread.sleep(500);

		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("529036");

		// SearchButton

		WebElement SearchButton = driver
				.findElement(By.xpath("//a[@class='btn btn-success btn-sm search-panel-btn-submit']"));
		SearchButton.click();
		Thread.sleep(1500);

		// WebElement RowResult =
		// driver.findElement(By.className("grid-row-inner-cell"));

		WebElement RowResult = driver.findElement(By.className("grid-row-inner-cell"));
		RowResult.click();

		Thread.sleep(1000);

		// WebElement TransXmlbutton =
		// driver.findElement(By.xpath("//button[@cmd='RTSM']"));

		WebElement TransXmlbutton = driver.findElement(By.xpath("//button[@cmd='PRINT_SIGN']"));
		TransXmlbutton.click();
		Thread.sleep(1500);
         driver.manage().window().maximize();
		WebElement Form = driver.findElement(By.linkText(mydata));

		Form.click();
		// TransResponseXML.getText();
		//System.out.println(Form.getAttribute("value"));
		// System.out.println("TheData =="+TransResponseXML.click());

		return "Pass";

	}

}
