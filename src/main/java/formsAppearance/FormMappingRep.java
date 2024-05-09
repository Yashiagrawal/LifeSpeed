package formsAppearance;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import keywords.Keywords;


import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class FormMappingRep extends Keywords {
	
	public WebDriver driver;

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
						openForm();

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

	public static String openForm() throws InterruptedException  {

		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\yashi.agrawal\\Downloads\\geckodriver-v0.34.0-win32\\geckodriver.exe");	
		WebDriver driver= new FirefoxDriver();
		driver.get(
				"https://lifespeed.uat.ebixexchange.com/LifeSpeedPlus/login.aspx?nX%2fXV5PW79jdTlbdR3teBuF1rvnjwn9ZuVi%2bX71oZHFEjA7HiLGjaA%3d%3d");
		driver.findElement(By.id("txtUserID")).sendKeys("Yashig");
		driver.findElement(By.id("txtPassword")).sendKeys("p");
		driver.findElement(By.id("btnLogin")).click();
		driver.findElement(By.xpath("//a[text()='Management ']")).click();
		WebElement ele = driver.findElement(By.xpath("//a[text()='Management Tools']"));
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();
		driver.findElement(By.xpath("//a[text()='Feed Management']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='btn btn-success']")).click();

		Select dropdown = new Select(driver.findElement(By.xpath("//*[@class='search-panel-ctrl-fields']")));
		dropdown.selectByValue("AppID");
		driver.findElement(By.xpath("//*[@type='text']")).click();
		driver.manage().window().maximize();
		//driver.findElement(By.xpath("//a[@class='btn btn-success']")).click();

		return "Pass";
		
		
	}

}
