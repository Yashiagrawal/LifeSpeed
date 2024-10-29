package formsAppearance;


import org.openqa.selenium.support.ui.Select;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ViewNBFL {

	public static void main(String[] args) throws InterruptedException {
		
		

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
		//For login button click
		driver.findElement(By.name("btnLogin")).click();
		Thread.sleep(500);
		//for new application
		WebElement management = driver.findElement(By.xpath("//a[text()='Management ']"));
		management.click();
		Thread.sleep(500);
		WebElement mgntool=driver.findElement(By.xpath("//*[@class='ls-menu-item'][contains(.,'Management Tools')]"));
		mgntool.click();
	    Actions a=new Actions(driver);
	    a.moveToElement(mgntool).perform();
		
	    WebElement ViewNBFL = driver.findElement(By.xpath("//a[text()='View NBFL']"));
	    ViewNBFL.click();
		Thread.sleep(100000);
	   
		WebElement SearchTrBar = driver.findElement(By.id("ContentPlaceHolder1_txtTransactionId"));
		SearchTrBar.sendKeys("456765");
		Thread.sleep(500);
		
		
		WebElement Distributor103 = driver.findElement(By.xpath("(//input[@name='ctl00$ContentPlaceHolder1$rblListNBfLType'])(8)"));
		Distributor103.click();
		Thread.sleep(1500);

		
		
		
		
		
		

		
		WebElement GetXML = driver.findElement(By.xpath("//input[@type='submit']"));
		GetXML.click();
		Thread.sleep(500);
		
		
		WebElement dataxml= driver.findElement(By.xpath("//textarea[@id='ContentPlaceHolder1_txtXmlArea']"));
		System.out.println(dataxml.getText());
		
		}

}
