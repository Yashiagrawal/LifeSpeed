package samlUtility;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.AWTException;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

public class MakeMyTripDateTest {

	WebDriver driver;

	WebDriverWait wait;

	@BeforeMethod

	public void openBrowser() throws InterruptedException {

//Launch chrome browser and navigate to Make My Trip Site

		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\agraway\\eclipse-workspace\\LifeSpeed-main\\Drivers\\geckodriver.exe");
// driver = new ChromeDriver();
		
		driver =  new FirefoxDriver();

 //WebDriverWait wait3	= new WebDriverWait(driver, Duration.ofSeconds(10));
			
			
	driver.get("https://www.makemytrip.com/");

	driver.manage().window().maximize();

	Thread.sleep(3000);

	}

	@Test

	public void tripDetails() throws InterruptedException, AWTException {

		// Close the modal dialog box if it appears

		List<WebElement> closeModal = driver.findElements(By.cssSelector("span.commonModal__close"));

		if (closeModal.size() > 0) {

			closeModal.get(0).click();

		}

		try {

			WebElement dept = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("p[data-cy='departureDate']")));

			dept.click();

			Thread.sleep(2000);

			selectDate("June 2024", "8");

			Thread.sleep(3000);

			String selectedDate = driver.findElement(By.xpath("//p[@data-cy='departureDate']/span[1]")).getText();

			Assert.assertEquals(selectedDate, "8");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void selectDate(String month_year, String day) throws InterruptedException {

 // For selecting month and year

List<WebElement> months = driver.findElements(By.xpath("//div[@class='DayPicker-Caption']/div"));

 System.out.println("months count: " +months.size());



 for (int i = 0; i < months.size(); i++) {

// Select date corresponding to the the month

if (months.get(i).getText().equals(month_year)) {

 List<WebElement> days = driver.findElements(By.xpath("(//div[@class='DayPicker-Caption']/div)[" + i + "+1]/..//following-sibling::div[@class='DayPicker-Body']//div[@class='DayPicker-Day']//p"));


System.out.println("days count: " + days.size());

for (int j = 0; j < days.size(); j++) {

 if (days.get(j).getText().equals(day)) {

days.get(j).click();

break;

 }
 
}

}

 }
 
	}

	@AfterMethod

	public void closeBrowser() {

		driver.quit();

	}

}
