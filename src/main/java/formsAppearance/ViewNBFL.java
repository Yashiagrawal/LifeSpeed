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

		String baseUrl = "https://lifespeed.uat.ebixexchange.com/lifespeedplus/login.aspx";
		driver.get(baseUrl);
		

		Thread.sleep(500);
		//for partner code text field
		//driver.findElement(By.className("form-control input-sm")).sendKeys("EBIX");
		//Thread.sleep(500);
		//for user id text field
		driver.findElement(By.id("txtUserID")).sendKeys("Yashig");
		Thread.sleep(500);
		//for password text field
		driver.findElement(By.name("txtPassword")).sendKeys("p");
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
		
	    WebElement ViewNBFL = driver.findElement(By.xpath("//a[text()='Feed Management']"));
	    ViewNBFL.click();
		Thread.sleep(10000);
	   
		
		
		WebElement Search1Bar = driver.findElement(By.xpath("//a[@class='btn btn-success']"));
		Search1Bar.click();
		Thread.sleep(500);
		
		
		
		Select TrID = new Select(driver.findElement(By.xpath("//select[@class='search-panel-ctrl-fields']")));
		TrID.selectByValue("AppID");
		Thread.sleep(500);

		
		WebElement Search2Bar = driver.findElement(By.xpath("//input[@type='text']"));
		Search2Bar.sendKeys("456765");
		Thread.sleep(500);
		
		
		
		//SearchButton
		
		WebElement SearchButton = driver.findElement(By.xpath("//a[@class='btn btn-success btn-sm search-panel-btn-submit']"));
		SearchButton.click();
		Thread.sleep(1500);
		
		
		//WebElement RowResult = driver.findElement(By.className("grid-row-inner-cell"));
		
		WebElement RowResult = driver.findElement(By.className("grid-row-inner-cell"));
		RowResult.click();
		
		Thread.sleep(1000);

		
		//WebElement TransXmlbutton = driver.findElement(By.xpath("//button[@cmd='RTSM']"));

		WebElement TransXmlbutton = driver.findElement(By.xpath("//button[@cmd='Transaction_XML']"));
		TransXmlbutton.click();
		Thread.sleep(1500);

	WebElement TransResponseXML = driver.findElement(By.id("txt_Transaction_XML"));

		//WebElement TransResponseXML = driver.findElement(By.id("txt_ResponseXML"));
		TransResponseXML.click();
		//TransResponseXML.getText();
		System.out.println(TransResponseXML.getAttribute("value"));
		//System.out.println("TheData =="+TransResponseXML.click());
	}

}
