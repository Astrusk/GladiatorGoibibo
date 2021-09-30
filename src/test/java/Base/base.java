package Base;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

	public class base {
		public WebDriver driver;
		
		 @BeforeTest
		  public void beforeTest() {
			  System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
			  driver = new ChromeDriver();  
		  }

		  @AfterTest
		  public void afterTest() {
			  driver.quit();
		  }
	}

