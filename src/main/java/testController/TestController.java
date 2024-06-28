package testController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import enums.Browsers;
import keywords.Keywords;
import reportUtils.ReportUtil;
import testBase.TestBase;

public class TestController extends TestBase {

	public static String TestCaseID;
	public static int TD;


	@BeforeClass
	public void initBrowser() throws IOException {
		Initialize();
	}

	@Test
	public void TestCaseController() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {

		String startTime = TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa");

		ReportUtil.startTesting("index", startTime, "QA", "1706.5");

		ReportUtil.startSuite("Suite1");
		String TCStatus = "Pass";

		// loop through the test cases
		for (int TC = 2; TC <= SuiteData.getRowCount("TestCases"); TC++) {

			// This will Read testCase ID from TestSuite e.g (TC01,TC02)
			TestCaseID = SuiteData.getCellData("TestCases", "TCID", TC).trim();

			// This will Read RunMode from TestSuite for testID
			String RunMode = SuiteData.getCellData("TestCases", "RunMode", TC).trim();

			if (RunMode.equals("Y")) {
				String TSStatus = "Pass";
			//	driver = TestBase.selectBrowser(Browsers.FIREFOX.name());

				// For testCase Id get the numbers of Test data rows from TestSuite1Data.xlsx
				int rows = TestStepData.getRowCount(TestCaseID);

				// This is required to handle the scenarios where there is no test data.
				// in that case we need to run the test case once
				if (rows < 2) {
					rows = 2;
				}

				// loop through test data
				for (TD = 2; TD <= rows; TD++) 
				{
					driver = TestBase.selectBrowser(Browsers.FIREFOX.name());

					//this will read runmodedata for test data
				//	String RunModeData = TestStepData.getCellData(TestCaseID, "RunModeData", TD).trim();
					
					//if(RunModeData.equals("Y")) {
					//	String TCStaus = "Pass";


					if (driver == null) {
						driver = TestBase.selectBrowser(Browsers.FIREFOX.name());
					}
					// loop through the test steps Form TestSuite1.xlsx for each Test ID

					for (int TS = 2; TS <= SuiteData.getRowCount(TestCaseID); TS++) {

						keyword = SuiteData.getCellData(TestCaseID, "Keyword", TS).trim();
						webElement = SuiteData.getCellData(TestCaseID, "WebElement", TS).trim();
						ProceedOnFail = SuiteData.getCellData(TestCaseID, "ProceedOnFail", TS).trim();
						TSID = SuiteData.getCellData(TestCaseID, "TSID", TS).trim();
						Description = SuiteData.getCellData(TestCaseID, "Description", TS).trim();

						TestDataField = SuiteData.getCellData(TestCaseID, "TestDataField", TS).trim();

						if (TestDataField != null && TestDataField != "") {
							TestData = TestStepData.getCellData(TestCaseID, TestDataField, TD).trim();
						}

 						if (TestDataField.equals("email")) {
							TestData = "test" + System.currentTimeMillis() + "@gmail.com";
						}

						Method method = Keywords.class.getMethod(keyword);
						TSStatus = (String) method.invoke(method);

					if (TSStatus.contains("Failed")) {
							// take the screenshot
							String filename = TestCaseID + "[" + (TD - 1) + "]" + keyword;
							String screenShot = TestBase.getScreenShot(filename);

							TCStatus = TSStatus;
							// report error
							ReportUtil.addKeyword(Description, keyword, TSStatus, screenShot);

							if (ProceedOnFail.equals("N")) {
								break;
							}
						} else {
							ReportUtil.addKeyword(Description, keyword, TSStatus, null);
						}
					}
					
					driver.quit();
					ReportUtil.addTestCase(TestCaseID, startTime, TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa"), TCStatus);
				//	
				//}
				}
				
				//driver.quit();
			} else {
				// skip the test case
				ReportUtil.addTestCase(TestCaseID, startTime, TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa"), "Skipped");
		if (driver != null) {
				driver.quit();
		}

			}
		

		}
		//driver.quit();
		ReportUtil.endSuite();
		ReportUtil.updateEndTime(TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa"));
	}

	@AfterClass
	public void quitBrowser() {
		System.out.println("In quitBrowser---------------------------");
		if (driver != null) {
			driver.quit();
		}
	}
}
