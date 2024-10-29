package aPI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TAb {

	public static void main(String[] args) throws InterruptedException {
		
		
		
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
				WebDriverManager.firefoxdriver().setup();
				
				WebDriver driver = new FirefoxDriver();
				// comment the above 2 lines and uncomment below 2 lines to use Chrome
				// System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
				// WebDriver driver = new ChromeDriver();

				String baseUrl = "https://lifespeed.uat.zinnia.com/lifespeedplus/login.aspx";
				driver.get(baseUrl);
				

				Thread.sleep(500);
				//for partner code text field
				//driver.findElement(By.className("form-control input-sm")).sendKeys("EBIX");
				//Thread.sleep(500);
				//for user id text field
				driver.findElement(By.id("txtUserID")).sendKeys("Yashig");
				Thread.sleep(500);
				//for password text field
				driver.findElement(By.name("txtPassword")).sendKeys("operA$");
				Thread.sleep(500);
		
				driver.findElement(By.name("btnLogin")).click();
				Thread.sleep(500);
		      
		        driver.switchTo().newWindow(WindowType.TAB);

		driver.get("https://ess.bdo.in/zinniapayroll/");
				//executeScript("window.open(arguments[0]),theURrL")
		
		

	}

}
