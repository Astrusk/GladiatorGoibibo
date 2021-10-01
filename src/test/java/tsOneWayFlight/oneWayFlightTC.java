package tsOneWayFlight;

import Base.*;
import url.*;
import flightOneWay.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class oneWayFlightTC extends base {
	
  @Test(priority = 1)
  public void testUrl() {
	  	Url u = new Url();
		String site = u.getUrl();
		driver.get(site);
  }
  
  @Test(priority = 2, dependsOnMethods="testUrl")
  public void TC18() throws Exception{
	  driver.manage().window().maximize();
	  
	  flightElements e = new flightElements(driver);
	  
	  e.clickSearch();
	  
	  String actual = e.errStatus();
	  
	  Assert.assertEquals(actual, "Please enter a valid Source");
  }
  
  @Test(priority = 3, dependsOnMethods="TC18")
  public void TC19() throws Exception{
	  driver.manage().window().maximize();
	  
	  flightElements e = new flightElements(driver);
	  flightCredentials cred = new flightCredentials();
	  
	  e.setFrom(cred.getFromCity());
	  
	  e.clickSearch();
	  
	  String actual = e.errStatus();
	  
	  Assert.assertEquals(actual, "Please enter a valid Destination");
  }
  
  @Test(priority = 4, dependsOnMethods="TC19")
  public void TC20() throws Exception{
	  
	  driver.manage().window().maximize();
	  
	  flightElements e = new flightElements(driver);
	  flightCredentials cred = new flightCredentials();
	  
	  e.setTo(cred.getToCity());
	  
	  e.clickSearch();
	  
	  String actual= e.errStatus();
	  
	  Assert.assertEquals(actual, "Please enter a valid departure date");
  }
  
  @Test(priority=5, dependsOnMethods="TC20")
  public void TC23() throws Exception{

	  driver.manage().window().maximize();
	  
	  flightElements e = new flightElements(driver);
	 
	  e.setDep();
	  
	  e.clickSearch();  
	  e.clickBook();
	  e.clickProceed();
	  
	  boolean x = driver.findElement(By.xpath("//div[contains(text(),'Please make sure you enter the Name as per your go')]")).isDisplayed();
	  
	  Assert.assertTrue(x);
  }
  
  @Test(priority=6, dependsOnMethods="TC23")
  public void TC24() throws Exception{
	  flightElements e = new flightElements(driver);
		 
	  e.clickIRisk();
	  e.clickProceed();
	  
	  boolean x = driver.findElement(By.xpath("//span[normalize-space()='First & Middle Name is mandatory']")).isDisplayed();
	  
	  Assert.assertTrue(x);
  }
  
  @Test(priority=7, dependsOnMethods="TC24")
  public void TC25() throws Exception{
	  flightElements e = new flightElements(driver);
	  flightCredentials cred = new flightCredentials();
	
	  e.setName(cred.getFirstName());
	  e.clickProceed();
	  
	  boolean x = driver.findElement(By.xpath("//div[normalize-space()='Title is mandatory']")).isDisplayed();
	  
	  Assert.assertTrue(x);
  }
  
  @Test(priority=7, dependsOnMethods="TC25")
  public void TC26() throws Exception{
	  flightElements e = new flightElements(driver);
	  flightCredentials cred = new flightCredentials();
	
	  e.selectTitle(cred.getTitledd());
	  e.clickProceed();
	  
	  boolean x = driver.findElement(By.xpath("//span[normalize-space()='Email ID cannot be empty']")).isDisplayed();
	  
	  Assert.assertTrue(x);
  }
  
  @Test(priority=8, dependsOnMethods="TC26")
  public void TC27() throws Exception{
	  flightElements e = new flightElements(driver);
	  flightCredentials cred = new flightCredentials();
	
	  e.setMail(cred.getInvalidEmail());
	  e.clickProceed();
	  
	  boolean x = driver.findElement(By.xpath("//span[normalize-space()='Please enter a valid Email ID']")).isDisplayed();
	  
	  Assert.assertTrue(x);
  }
  
  @Test(priority=9, dependsOnMethods="TC27")
  public void TC28() throws Exception{
	  flightElements e = new flightElements(driver);
	  flightCredentials cred = new flightCredentials();
	
	  e.setMail(cred.getValidEmail());
	  e.clickProceed();
	  
	  boolean x = driver.findElement(By.xpath("//span[normalize-space()='Mobile Number cannot be empty']")).isDisplayed();
	  
	  Assert.assertTrue(x);
  }
  
  @Test(priority=10, dependsOnMethods="TC28")
  public void TC29() throws Exception{
	  flightElements e = new flightElements(driver);
	  flightCredentials cred = new flightCredentials();
	
	  e.setNum(cred.getInvalidNo());
	  e.clickProceed();
	  
	  String err = driver.findElement(By.xpath("//span[@class='red width100 padT3']")).getText();
	  
	  Assert.assertEquals(err, "Please enter a valid mobile number");
  }
  
  @Test(priority=11, dependsOnMethods="TC29")
  public void TC30() throws Exception{
	  flightElements e = new flightElements(driver);
	  flightCredentials cred = new flightCredentials();
	
	  e.setNum(cred.getValidNo());
	  
	  WebDriverWait wait = new WebDriverWait(driver,30);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Base Fare']")));
	  
	  //Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Base Fare']")).isDisplayed());;
	  
	  e.clickProceed();
	  e.clickPtopay();
	  
	  boolean x = driver.findElement(By.xpath("//div[contains(@class,'fr mobdn')]//img")).isDisplayed();
	  
	  Assert.assertTrue(x);
	  
  }
  
}
