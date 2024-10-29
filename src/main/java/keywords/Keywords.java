package keywords;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.net.UrlChecker.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import excelReader.ExcelReader;
import formsAppearance.DisclosureForms;
import formsAppearance.EntityForm;
import formsAppearance.FormAppearingTrust;
import formsAppearance.LangForm;
import samlUtility.Base64Encode;
import samlUtility.ProductAndCarrierCodeUtils;
import samlUtility.Product_Name_Utils;
import samlUtility.Saml_Util;
import samlUtility.Transaction_Status;
import samlUtility.UserEmail;

import samlUtility.UserName;
import samlUtility.UserRole;
import samlUtility.UserRoleSU;
import testBase.LoggerHelper;
import testBase.TestBase;
import testBase.Wait;
import testController.TestController;

public class Keywords extends TestBase {

	private final static Logger logger = LoggerHelper.getLogger(Keywords.class);
	private final static ExcelReader reader = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\java\\testData\\TestSuite1Data.xlsx");

	static String parentWindowHandle;
	static String QueryStatus;
	static String ActualText;

	public static String SAML_Content;

	public static WebElement getWebElement(String locator) throws Exception {
		// logger.info("locator
		// data:-"+locator+"is---"+Repository.getProperty(locator));
		String keywordValue = Repository.getProperty(locator);
		return getLocator(keywordValue);
	}

	public static List<WebElement> getWebElements(String locator) throws Exception {
		return getLocators(Repository.getProperty(locator));
	}

	public static String navigate() {
		logger.info("Navigate is called");
		driver.get(webElement);
		return "Pass";
	}

	public static String clickRadioButton() {
		try {
			expliciteWait();
			getWebElement(webElement).click();
		} catch (Throwable t) {
			return "Failed - Element not found " + webElement;
		}
		return "Pass";
	}

	public static String clickCheckBox() {
		try {
			expliciteWait();
			getWebElement(webElement).click();
		} catch (Throwable t) {
			return "Failed - Element not found " + webElement;
		}
		return "Pass";
	}

	public static String inputText() {
		logger.info("InputText is called");
		try {
			expliciteWait();
			getWebElement(webElement).sendKeys(TestData);
		} catch (Throwable t) {
			return "Failed - Element not found " + webElement;
		}
		return "Pass";
	}

	public static String clickOnLink() {
		logger.info("ClickOnLink is called");
		try {
			expliciteWait();
			getWebElement(webElement).click();
		} catch (Throwable t) {
			t.printStackTrace();
			return "Failed - Element not found " + webElement;
		}
		return "Pass";
	}

	public static String verifyText() {
		logger.info("VerifyText is called");
		try {
			expliciteWait();
			String ActualText = getWebElement(webElement).getText();
			logger.info(ActualText);
			if (!ActualText.equals(TestData)) {
				return "Failed - Actual text " + ActualText + " is not equal to to expected text " + TestData;
			}
		} catch (Throwable t) {
			return "Failed - Element not found " + webElement;
		}
		logger.info(ActualText);
		return "Pass";
	}

	public static String verifyAppText() {
		logger.info("VerifyText is called");
		try {
			expliciteWait();
			String ActualText = getWebElement(webElement).getText();
			if (!ActualText.equals(AppText.getProperty(webElement))) {
				return "Failed - Actual text " + ActualText + " is not equal to to expected text "
						+ AppText.getProperty(webElement);
			}
		} catch (Throwable t) {
			return "Failed - Element not found " + webElement;
		}
		return "Pass";
	}

	public static String mouseOver() {
		try {
			expliciteWait();
			WebElement element = getWebElement(webElement);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			Thread.sleep(1000);
		} catch (Exception e) {
			return "Failed - Element not found " + webElement;
		}
		return "Pass";
	}

	
	public static String SpaceKeys() {
		try {
			expliciteWait();
			WebElement element = getWebElement(webElement);
			Actions builder = new Actions(driver);
			builder.sendKeys(element).build().perform();
			Thread.sleep(1000);
		} catch (Exception e) {
			return "Failed - Element not found " + webElement;
		}
		return "Pass";
	}
	public static String selectByValue() {
		try {
			expliciteWait();
			WebElement element = getWebElement(webElement);
			Select select = new Select(element);
			select.selectByValue(TestData);
		} catch (Exception e) {
			return "Failed - Element not found " + webElement;
		}
		return "Pass";
	}

	public static String selectByVisibleText() {
		try {
			expliciteWait();
			WebElement element = getWebElement(webElement);
			Select select = new Select(element);
			select.selectByVisibleText(TestData);
		} catch (Exception e) {
			return "Failed - Element not found " + webElement;
		}
		return "Pass";
	}

	public static String selectByIndex() {
		try {
			WebElement element = getWebElement(webElement);
			Select select = new Select(element);
			select.selectByIndex(Integer.parseInt(TestData));
		} catch (Exception e) {
			return "Failed - Element not found " + webElement;
		}
		return "Pass";
	}

	/**
	 * This Method will return web element.
	 * 
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public static WebElement getLocator(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.tagName(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}

	public static List<WebElement> getLocators(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}

	public static void expliciteWait() throws Exception {
		try {
			logger.info("Waiting for webElement..." + webElement.toString());
			WebDriverWait wait = new WebDriverWait(driver,
					Duration.ofSeconds(Integer.parseInt(Wait.getExplicitWait())));
			wait.until(ExpectedConditions.visibilityOf(getWebElement(webElement)));
			logger.info("Element found..." + webElement.toString());
		} catch (Throwable e) {
			throw new TimeoutException(webElement, e);

		}

	}

	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		logger.debug("");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		// wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		// wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void waitForElementVisible(WebElement locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		logger.info(locator);
		WebDriverWait wait = getWait(300, 5);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	public static String implicitlyWait() {
		try {
			// Thread.sleep(5000);
			// driver.manage().timeouts().implicitlyWait(Integer.parseInt(Wait.getImplicitWait()),
			// Duration.ofSeconds());
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Wait.getImplicitWait()), TimeUnit.SECONDS);
		} catch (Exception e) {
			return "Failed - unable to load the page";
		}
		return "Pass";
	}

	public static String clickWhenReady(By locator, Duration timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
		return "Pass";

	}

	public static String waitFor() throws InterruptedException {
		try {
			Thread.sleep(16000);
		} catch (InterruptedException e) {
			return "Failed - unable to load the page";
		}
		return "Pass";
	}

	public static String waitForDocumentsToGenerate() throws InterruptedException {
		try {
			Thread.sleep(90000);
		} catch (InterruptedException e) {
			return "Failed - unable to load the page";
		}
		return "Pass";
	}

	public static String openNewWindowTab() {
		String windowTab = "window.open('about:blank','_blank');";
		((JavascriptExecutor) driver).executeScript(windowTab);
		return "Pass";
	}

	public static String encripted_SAML() {
		Base64Encode.SAML_base64Encode();
		return "Pass";
	}

	public static String decripted_SAML() {
		Base64Encode.SAML_base64Decode();
		return "Pass";
	}

	public static String read_SAML_DATA_XMLFile() throws InterruptedException {

		driver.get(System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\POS_Protectioniul.xml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		SAML_Content = driver.findElement(By.id("folder0")).getText();

		return "Pass";
	}

	public static String read_PM_Approve_SAML_DATA_XMLFile() throws InterruptedException {

		driver.get("E:\\AN4\\PM_APPROVE_SANDEEPPM.xml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		SAML_Content = driver.findElement(By.id("folder0")).getText();

		return "Pass";
	}

	public static String paste_SAML() {
		driver.findElement(By.id("SAMLResponse")).sendKeys(SAML_Content);
		return "Pass";
	}

	public static String getParentWindowhandle() {
		parentWindowHandle = driver.getWindowHandle();
		System.out.println("Parent window's handle -> " + parentWindowHandle);
		return "Pass";

	}

	public static String switchToWindow() {
		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String handle : allWindowHandles) {
			System.out.println("Window handle - > " + handle);
			driver.switchTo().window(handle);
			driver.manage().window().maximize();
		}
		return "Pass";
	}

	public static String switchToParticuarWindow() {
		logger.info("switching to particular window...");
		// driver.switchTo().defaultContent();

		Set handles = driver.getWindowHandles();
		String[] individualHandle = new String[handles.size()];
		
		System.out.println(individualHandle);
		// Iterator it = handles.iterator();

		Iterator its = handles.iterator();
		int iy = 0;
		while (its.hasNext()) {
			individualHandle[iy] = (String) its.next();
			iy++;
		}

		driver.switchTo().window(individualHandle[1]);
		System.out.println(driver.switchTo().window(individualHandle[1]).getTitle());
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());

		return "Pass";
	}

	
	public static String switchToParentWindow() {
		logger.info("switching to parent window...");
		// driver.switchTo().defaultContent();

		System.out.println(driver.getWindowHandles());
	driver.switchTo().defaultContent();
		System.out.println("inparent window error");
		//driver.switchTo().window(parentWindowHandle);
		
		return "Pass";
	}

	public static String resizeBroserWindow() {
		Dimension d = new Dimension(1250, 870);
		// Resize the current window to the given dimension
		driver.manage().window().setSize(d);
		return "Pass";
	}

	public static Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script);
	}

	public static String scrollDownVertically() {
		executeScript("window.scrollTo(0,document.body.scrollHeight)");
		return "Pass";
	}

	public static String scrollTopVertically() {
		executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		return "Pass";
	}

	public static String scrollDownVerticallyByPixel() {
		executeScript("window.scrollBy(0,550)");
		return "Pass";
	}

	public static String acceptPopUpMessage() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
		return "Pass";
	}

	public void switchToFrame() throws Exception {
		WebElement element = getWebElement(webElement);
		driver.switchTo().frame(element);
		logger.info("switched to frame " + element.toString());
	}

	public static String switchToDialog() throws Exception {
		getWebElement(webElement);
		return "Pass";
	}

	static String transactionid;

	public static String getText() {
		logger.info("GetText is called");
		try {
			expliciteWait();
			ActualText = getWebElement(webElement).getText();
			logger.info(ActualText);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Pass";
	}

	public static String clearText() {
		logger.info("ClearText is called");
		try {
			expliciteWait();
			getWebElement(webElement).clear();
			logger.info(ActualText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Pass";
	}

	public static String getDistributerName() {
		getText();
		reader.setCellData("Status", "Distributor", TestController.TD, ActualText);
		return "Pass";
	}

	public static String getCarrierName() {
		getText();
		reader.setCellData("Status", "Carrier", TestController.TD, ActualText);
		return "Pass";
	}

	public static String getTxnStatus() {
		getText();
		reader.setCellData("Status", "Txn_Status", TestController.TD, ActualText);
		return "Pass";
	}

	public static String getEbixTxnID() {
		getText();
		String[] result = ActualText.split(" ");
		transactionid = result[1];
		System.out.println(result[0] + "," + result[1]);
		logger.info(ActualText);
		TestStepData.setCellData(TestController.TestCaseID, "Ebix_Txn_Id", TestController.TD, transactionid);
		return "Pass";

	}

	LocalDate time = LocalDate.now();

	public static String getCurrentTime() {
		getText();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(date);
		reader.setCellData("Status", "Date", 2, strDate);
		return "Pass";
	}

	public static String change_External_Internal_TxnId() throws Exception {
		// --------- LOAD XML
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document DataDoc = db.parse(new InputSource(new StringReader(Saml_Util.getXMLString())));

		// --------- PROCESS
		String cdata = Saml_Util.getCData(DataDoc);
		// System.out.println(cdata);
		Document docWithCDataPlaceholder = Saml_Util.getDataDocWithCDataPlaceHolder(DataDoc);

		// --------- LOAD CDATA XML
		Document cDataDoc = db.parse(new InputSource(new StringReader(cdata)));
		Document modifiedCData = Saml_Util.getModifiedCData(cDataDoc);

		// --------- OUTPUT
		String finalcData = Saml_Util.convertXMLDocumentToString(modifiedCData, true);
		String finalXML = Saml_Util.convertXMLDocumentToString(docWithCDataPlaceholder, false);

		finalcData = "<![CDATA[" + finalcData + "]]>";
		finalXML = finalXML.replace("C_DATA", finalcData);

		final String xmlStr = new String(finalXML);
		Document DataDocFinal = Saml_Util.convertStringToDocument(xmlStr);

		FileOutputStream output = new FileOutputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\POS_Protectioniul.xml");

		Saml_Util.writeXml(DataDocFinal, output);
		return "Pass";
	}

	public static String Change_UserEmail() throws ParserConfigurationException, SAXException, IOException,
			TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {

		File fXmlFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\POS_Protectioniul.xml");
// Xls_Reader reader = new
// Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\java\\data\\UAT_Stability_Status.xlsx");
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
// System.out.println("Root element :" +
// doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("saml2:Attribute");

// System.out.println("----------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Element element = (Element) nList.item(temp);
			NodeList name = element.getElementsByTagName("saml2:AttributeValue");
			Element line = (Element) name.item(0);
			NodeList list = line.getChildNodes();
			String data;
			// System.out.println(list.getLength());
			for (int index = 0; index < list.getLength(); index++) {
				String updatedEmail = null;
				if (list.item(index) instanceof CharacterData) {
					CharacterData child = (CharacterData) list.item(index);
					data = child.getData();
					if (data != null && data.trim().length() > 0)
						if (data.contains("<Data>")) {
							// System.out.println(data);
							String lEValue = data.substring(data.indexOf("<EmailAddress>") + 1);
							String valueEmail = lEValue.substring(13, lEValue.indexOf("</EmailAddress>"));
							System.out.println(valueEmail);
							String excelEmail = reader.getCellData(TestController.TestCaseID, "EmailAddress",
									TestController.TD);
							System.out.println(excelEmail);
							updatedEmail = data.replace(valueEmail, excelEmail);
							child.setNodeValue(updatedEmail);
						}
				}
			}
		}
		writeXMLFile(doc);

		return "Pass";
	}

	public static String Change_Product_Name() throws Exception {
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document DataDoc = db.parse(new InputSource(new StringReader(Product_Name_Utils.getXMLString())));

		// --------- PROCESS
		String cdata = Product_Name_Utils.getCData(DataDoc);
		// System.out.println(cdata);
		Document docWithCDataPlaceholder = Product_Name_Utils.getDataDocWithCDataPlaceHolder(DataDoc);

		// --------- LOAD CDATA XML
		Document cDataDoc = db.parse(new InputSource(new StringReader(cdata)));
		Document modifiedCData = Product_Name_Utils.getModifiedCDataProductName(cDataDoc);

		// --------- OUTPUT
		String finalcData = Product_Name_Utils.convertXMLDocumentToString(modifiedCData, true);
		String finalXML = Product_Name_Utils.convertXMLDocumentToString(docWithCDataPlaceholder, false);

		finalcData = "<![CDATA[" + finalcData + "]]>";
		finalXML = finalXML.replace("C_DATA", finalcData);

		final String xmlStr = new String(finalXML);
		Document DataDocFinal = Product_Name_Utils.convertStringToDocument(xmlStr);

		FileOutputStream output = new FileOutputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\POS_Protectioniul.xml");

		Product_Name_Utils.writeXml(DataDocFinal, output);

		return "Pass";
	}

	public static String Change_Product_Code() throws Exception {
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document DataDoc = db.parse(new InputSource(new StringReader(ProductAndCarrierCodeUtils.getXMLString())));

		// --------- PROCESS
		String cdata = ProductAndCarrierCodeUtils.getCData(DataDoc);
		// System.out.println(cdata);
		Document docWithCDataPlaceholder = ProductAndCarrierCodeUtils.getDataDocWithCDataPlaceHolder(DataDoc);

		// --------- LOAD CDATA XML
		Document cDataDoc = db.parse(new InputSource(new StringReader(cdata)));
		Document modifiedCData = ProductAndCarrierCodeUtils.getModifiedCDataProductCodeCarrier(cDataDoc);

		// --------- OUTPUT
		String finalcData = ProductAndCarrierCodeUtils.convertXMLDocumentToString(modifiedCData, true);
		String finalXML = ProductAndCarrierCodeUtils.convertXMLDocumentToString(docWithCDataPlaceholder, false);

		finalcData = "<![CDATA[" + finalcData + "]]>";
		finalXML = finalXML.replace("C_DATA", finalcData);

		final String xmlStr = new String(finalXML);
		Document DataDocFinal = ProductAndCarrierCodeUtils.convertStringToDocument(xmlStr);

		FileOutputStream output = new FileOutputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\POS_Protectioniul.xml");

		ProductAndCarrierCodeUtils.writeXml(DataDocFinal, output);

		return "Pass";
	}

	public static String Change_UserName() throws Exception {
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document DataDoc = db.parse(new InputSource(new StringReader(UserName.getXMLString())));

		// --------- PROCESS
		String cdata = UserName.getCData(DataDoc);
		// System.out.println(cdata);
		Document docWithCDataPlaceholder = UserName.getDataDocWithCDataPlaceHolder(DataDoc);

		// --------- LOAD CDATA XML
		Document cDataDoc = db.parse(new InputSource(new StringReader(cdata)));
		Document modifiedCData = UserName.getModifiedCDataUserName(cDataDoc);

		// --------- OUTPUT
		String finalcData = UserName.convertXMLDocumentToString(modifiedCData, true);
		String finalXML = UserName.convertXMLDocumentToString(docWithCDataPlaceholder, false);

		finalcData = "<![CDATA[" + finalcData + "]]>";
		finalXML = finalXML.replace("C_DATA", finalcData);

		final String xmlStr = new String(finalXML);
		Document DataDocFinal = UserName.convertStringToDocument(xmlStr);

		FileOutputStream output = new FileOutputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\POS_Protectioniul.xml");

		UserName.writeXml(DataDocFinal, output);
		return "Pass";
	}
	
	
	
	public static String Change_UserRole() throws Exception {
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document DataDoc = db.parse(new InputSource(new StringReader(UserRole.getXMLString())));

		// --------- PROCESS
		String cdata = UserRole.getCData(DataDoc);
		// System.out.println(cdata);
		Document docWithCDataPlaceholder = UserRole.getDataDocWithCDataPlaceHolder(DataDoc);

		// --------- LOAD CDATA XML
		Document cDataDoc = db.parse(new InputSource(new StringReader(cdata)));
		Document modifiedCData = UserRole.getModifiedCDataUserRole(cDataDoc);

		// --------- OUTPUTconvertXMLDocumentToString(modifiedCData, true);
		String finalcData = UserRole.convertXMLDocumentToString(modifiedCData, true);
		String finalXML = UserRole.convertXMLDocumentToString(docWithCDataPlaceholder, false);

		finalcData = "<![CDATA[" + finalcData + "]]>";
		finalXML = finalXML.replace("C_DATA", finalcData);

		final String xmlStr = new String(finalXML);
		Document DataDocFinal = UserRole.convertStringToDocument(xmlStr);

		FileOutputStream output = new FileOutputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\POS_Protectioniul.xml");

		UserRole.writeXml(DataDocFinal, output);
		return "Pass";
	}
	
	
	public static String Change_UserRoleSU() throws Exception {
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document DataDoc = db.parse(new InputSource(new StringReader(UserRoleSU.getXMLString())));

		// --------- PROCESS
		String cdata = UserRole.getCData(DataDoc);
		// System.out.println(cdata);
		Document docWithCDataPlaceholder = UserRoleSU.getDataDocWithCDataPlaceHolder(DataDoc);

		// --------- LOAD CDATA XML
		Document cDataDoc = db.parse(new InputSource(new StringReader(cdata)));
		Document modifiedCData = UserRoleSU.getModifiedCDataUserRoleSU(cDataDoc);

		// --------- OUTPUTconvertXMLDocumentToString(modifiedCData, true);
		String finalcData = UserRoleSU.convertXMLDocumentToString(modifiedCData, true);
		String finalXML = UserRoleSU.convertXMLDocumentToString(docWithCDataPlaceholder, false);

		finalcData = "<![CDATA[" + finalcData + "]]>";
		finalXML = finalXML.replace("C_DATA", finalcData);

		final String xmlStr = new String(finalXML);
		Document DataDocFinal = UserRoleSU.convertStringToDocument(xmlStr);

		FileOutputStream output = new FileOutputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\POS_Protectioniul.xml");

		UserRoleSU.writeXml(DataDocFinal, output);
		return "Pass";
	}

	public static String Change_CUSIP() throws ParserConfigurationException, SAXException, IOException,
			TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {

		File fXmlFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\POS_Protectioniul.xml");
		// Xls_Reader reader = new
		// Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\java\\data\\UAT_Stability_Status.xlsx");
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		// System.out.println("Root element :" +
		// doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("saml2:Attribute");

		// System.out.println("----------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Element element = (Element) nList.item(temp);
			NodeList name = element.getElementsByTagName("saml2:AttributeValue");
			Element line = (Element) name.item(0);
			NodeList list = line.getChildNodes();
			String data;
			// System.out.println(list.getLength());
			for (int index = 0; index < list.getLength(); index++) {
				String updatedCUSIP = null;
				if (list.item(index) instanceof CharacterData) {
					CharacterData child = (CharacterData) list.item(index);
					data = child.getData();
					if (data != null && data.trim().length() > 0)
						if (data.contains("<Data>")) {
							// System.out.println(data);
							String lValue = data.substring(data.indexOf("<CUSIP>") + 1);
							String valuecuspi = lValue.substring(6, lValue.indexOf("</CUSIP>"));
							System.out.println(valuecuspi);
							String excelCUSIP = reader.getCellData(TestController.TestCaseID, "CUSIP",
									TestController.TD);
							System.out.println(excelCUSIP);
							updatedCUSIP = data.replace(valuecuspi, excelCUSIP);
							child.setNodeValue(updatedCUSIP);
						}
				}
			}
		}
		writeXMLFile(doc);

		return "Pass";
	}

	private static void writeXMLFile(Document doc)
			throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		doc.getDocumentElement().normalize();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\sMAL_File\\POS_Protectioniul.xml"));
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.transform(source, result);
		System.out.println("XML file updated successfully");
	}

	public static String getFlagsText() {
		logger.info("Get Flag Text is called");
		try {
			expliciteWait();
			List<WebElement> elements = getWebElements(webElement);
			flaglist = new ArrayList<>();
			for (int i = 0; i < elements.size(); i++) {
				String flagNumber = elements.get(i).getText();
				String[] actualNumber = flagNumber.split("_");
				// reader.setCellData("Flags", "FlagNumber", i+2, actualNumber[0]);
				// int columns = reader.getColumnCount("TC05");

				flaglist.add(actualNumber[0]);
				reader.setCellData("Flags", i, 1, actualNumber[0]);
				logger.info("Flag Number :- " + actualNumber[0]);
			}
			System.out.println(flaglist);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Pass";
	}

	public static String getTransactXML() {
		logger.info("GetText is called");
		try {
			expliciteWait();
			ActualText = getWebElement(webElement).getText();
			System.out.println(ActualText);
			logger.info(ActualText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Pass";
	}

	public static String getPopupText() {
		try {
			expliciteWait();
			ActualText = getWebElement(webElement).getAttribute("value");
			File path = new File(System.getProperty("user.dir") + "\\FeedDetails\\FeedStatus.xml");

			// passing file instance in filewriter
			FileWriter wr = new FileWriter(path);

			// calling writer.write() method with the string
			wr.write(ActualText);

			// flushing the writer
			wr.flush();

			// closing the writer
			wr.close();
			System.out.println("Data written to the file successfully");

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "Pass";
	}

	public static String transactXMLParse() {
		try {
			// File file = new
			// File("C:\\Users\\suneel.kumar\\Desktop\\New_folder\\tran.xml");
			FileInputStream fis = new FileInputStream(
					new File(System.getProperty("user.dir") + "\\FeedDetails\\FeedStatus.xml"));

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(fis);
			XPath xPath = XPathFactory.newInstance().newXPath();

			System.out.println("*************************");
			String expression = "//REQUEST/EXTENSIONS/EXTENSION/PARAMETERS/PARAMETER/NAME";
			String expressionValue = "//REQUEST/EXTENSIONS/EXTENSION/PARAMETERS/PARAMETER/VALUE";
			System.out.println(expression);
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
			NodeList nodeListValue = (NodeList) xPath.compile(expressionValue).evaluate(xmlDocument,
					XPathConstants.NODESET);
			transactXMLflagsList = new ArrayList<>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				String flagName = nodeList.item(i).getFirstChild().getNodeValue();
				String flagValue = nodeListValue.item(i).getFirstChild().getNodeValue();
				boolean flagNameStatus = flagName.contains("YELLOW_FLAGS");
				boolean flagValueStatus = flagValue.contains("Yes");
				if (flagNameStatus && flagValueStatus) {
					String[] actualFlagNumber = flagName.split("_");

					transactXMLflagsList.add(actualFlagNumber[0]);
					System.out.println(actualFlagNumber[0]);
				}
			}
			System.out.println(transactXMLflagsList);

			System.out.println("*************************");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return "Pass";
	}

	public static ArrayList<String> flaglist;
	public static ArrayList<String> transactXMLflagsList;

	public static String validatFlags() {

		System.out.println("Flags in Last Screen of Application" + flaglist);
		System.out.println("Flags in Transact XML" + transactXMLflagsList);

		System.out.println("Un-common Flags of the Last Screen");
		flaglist.removeAll(transactXMLflagsList);
		System.out.println(flaglist);

		return "Pass";
	}

	public static String properWait() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return "Pass";
	}

	public static String verify_103Status() {
		Transaction_Status.status_103();
		return "Pass";
	}

	public static String verify_RTSMStatus() {
		Transaction_Status.status_RTSM();
		return "Pass";
	}

	/*
	 * public static String selectDaysInDropDown() throws Exception{
	 * RegistrationPage reg = new RegistrationPage(); String status =
	 * reg.selectDaysInDropDown(); return status; }
	 * 
	 * public static String selectMonthInDropDown() throws Exception{
	 * RegistrationPage reg = new RegistrationPage(); return
	 * reg.selectMonthInDropDown(); }
	 * 
	 * public static String selectYearInDropDown() throws Exception{
	 * RegistrationPage reg = new RegistrationPage(); return
	 * reg.selectYearInDropDown(); }
	 * 
	 * public static String selectYourAddressCountry() throws Exception{
	 * RegistrationPage reg = new RegistrationPage(); return
	 * reg.selectYourAddressCountry(); }
	 */
	public static String closeBrowser() {
		driver.quit();
		return "Pass";
	}

	public static String closeB() {
		driver.close();
		return "Pass";
	}
	public static String verify_Transtatus() {

		LangForm.status_TransXML();
		return "Pass";
	}

	public static String verify_formsApp() {

		LangForm.CompareformforeignContect();

		// TODO: handle exception

		return "Pass";
	}

	public static String DateSelection() {
		try {
			expliciteWait();

			JavascriptExecutor executor8 = (JavascriptExecutor) driver;

			executor8.executeScript("arguments[0].value=arguments[1]",getWebElement(webElement), TestData);
			

		} catch (Exception e) {
			return "Failed - Element not found " + webElement;
		}
		return "Pass";
	}

	public static String verify_Disclosure() {

		DisclosureForms.FormNYDD();
		FormAppearingTrust.CompareformTrust();
		FormAppearingTrust.OwnerTrustEntity();
		EntityForm.FormTrustteEntity();
		// TODO: handle exception

		return "Pass";
	}

	public static String Cond_Disclosure() {

		DisclosureForms.Disc();
		FormAppearingTrust.TrustEntityForms();
		EntityForm.EntityOwnerForm();
		// TODO: handle exception

		return "Pass";
	}

	public static String getPopupTransText() {
		try {
			expliciteWait();

			ActualText = getWebElement(webElement).getAttribute("value");
			File path = new File(System.getProperty("user.dir") + "\\FeedDetails\\FeedStatus.xml");

			// passing file instance in filewriter
			FileWriter wr = new FileWriter(path);
			// FileOutputStream wr=new FileOutputStream(path);

			// calling writer.write() method with the string

			wr.write(ActualText);
			System.out.println(ActualText);

			// flushing the writer
			wr.flush();

			// closing the writer
			wr.close();
			System.out.println("Data written to the file successfully");

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "Pass";
	}
	
	
	
	public static String closeMultipleTabs() throws InterruptedException {
        // Get all open tabs
        Set<String> allTabs = driver.getWindowHandles();
       
        // Get Current tab
        String currentTab = driver.getWindowHandle();
       
        Iterator<String> iterator = allTabs.iterator();
       
        while(iterator.hasNext()) {
            // Condition to check if the selected tab is not current tab
            String selectedTab = iterator.next();
            if(!selectedTab.equals(currentTab)) {
                // Switch to new tab
                driver.switchTo().window(selectedTab);
               
                // Print title of tabs to be closed
                System.out.println("Closing Tab = "+driver.getTitle());
               
                // Close the selected tab
                driver.close();
               
                // Time delay
                Thread.sleep(1000);
            }
        }
		return "Pass"; // closeMultipleTabs
    }
	
	
	public void openNewTab()
	{
		  
		 driver.switchTo().newWindow(WindowType.WINDOW);	
		
		
	}

}
