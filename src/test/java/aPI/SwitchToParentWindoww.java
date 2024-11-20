package aPI;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SwitchToParentWindoww {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.firefoxdriver().setup();
		
		WebDriver driver = new FirefoxDriver();
		 // Set the path of the ChromeDriver (update this to your local path)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Instantiate the WebDriver (e.g., ChromeDriver)
       

        // Open a website
        driver.get("https://example.com");

        // Store the parent window's handle
        String parentWindowHandle = driver.getWindowHandle();

        // Click on a link that opens a new window (adjust the locator accordingly)
        driver.findElement(By.linkText("Open new window")).click();
        
        // Get all window handles
        Set<String> allWindowHandles = driver.getWindowHandles();

        // Iterator to loop through all the window handles
        Iterator<String> windowIterator = allWindowHandles.iterator();

        // Switch to the new window (child window)
        String childWindowHandle = "";
        while (windowIterator.hasNext()) {
            String windowHandle = windowIterator.next();
            if (!windowHandle.equals(parentWindowHandle)) {
                childWindowHandle = windowHandle;
                driver.switchTo().window(childWindowHandle);
                break;
            }
        }

        // Perform actions on the child window if needed
        System.out.println("Currently on child window with title: " + driver.getTitle());

        // Now switch back to the parent window
        driver.switchTo().window(parentWindowHandle);
		
		
        // Perform actions on the parent window
        System.out.println("Currently on parent window with title: " + driver.getTitle());

        // Close the driver
        driver.quit();
		
		
		
		
		

	}

}
