package tsMulticityFlight;

import Base.*;
import flightMulticity.FlightCredentials;
import flightMulticity.FlightElements;
import url.*;
import flightMulticity.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MulticityFlightTC extends base {

	@Test(priority = 1)
	public void testUrl() {
		Url u = new Url();
		String site = u.getUrl();
		driver.get(site);
	}

	@Test(priority = 3, dependsOnMethods = "testUrl", description = "Testing by giving only from city without destination")
	public void TC44() throws Exception {
		driver.manage().window().maximize();

		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.select();
		e.setFrom(cred.getFromCity());

		e.clickSearch();
		// Thread.sleep(3000);
		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please add at least one more sector/city to continue");
	}

	@Test(priority = 4, dependsOnMethods = "TC44", description = "Testing multicity trip Flight with Valid From and Destination with no valid Date for Travel 1")
	public void TC45() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.setTo(cred.getToCity());
		e.clickSearch();
		// Thread.sleep(3000);
		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please provide a valid Date for Travel 1");
	}

	@Test(priority = 5, dependsOnMethods = "TC45", description = "Testing the multicity trip without giving the destination city")
	public void TC46() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.setDep();
		e.clickSearch();
		// Thread.sleep(3000);
		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please provide a valid Destination city for Travel 2");
	}

	@Test(priority = 6, dependsOnMethods = "TC46", description = "Testing multicity trip Flight with Valid From and Destination with no valid Date for Travel 2")
	public void TC47() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.setTo2(cred.getToCity2());
		e.clickSearch();
		// Thread.sleep(3000);
		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please provide a valid Date for Travel 2");
	}

	@Test(priority = 7, dependsOnMethods = "TC47", description = "Booking Multicity Flight without Title")
	public void TC53() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		e.setDep2();
		e.clickSearch();
		e.clickBook();
		e.clickProceed();

		boolean x = driver.findElement(By.xpath("//b[normalize-space()='ERROR:']")).isDisplayed();

		Assert.assertTrue(x);
	}

	@Test(priority = 8, dependsOnMethods = "TC53", description = "Booking Multicity Flight without First Name")
	public void TC50() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.selectTitle(cred.getTitledd());
		e.clickProceed();

		String x = driver.findElement(By.xpath("//span[@class='InvalidError fl width100 padT2']")).getText();

		Assert.assertEquals(x, "First Name is required");
	}

	@Test(priority = 9, dependsOnMethods = "TC50", description = "Booking Multicity Flight without Last Name")
	public void TC51() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.setName(cred.getFirstName());
		e.clickProceed();
		String actual = driver.findElement(By.xpath("//span[@class='InvalidError fl width100 padT2']")).getText();
		Assert.assertEquals(actual, "Last Name is required");
	}

	@Test(priority = 10, dependsOnMethods = "TC51", description = "Booking Multicity Flight with same First Name and Last Name")
	public void TC52() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		// e.setName(cred.getFirstName());
		e.setlastName(cred.getFirstName());
		e.clickProceed();
		String actual = driver.findElement(By.xpath("//span[@class='InvalidError fl width100 padT2']")).getText();

		Assert.assertEquals(actual, "First Name and Last Name cannot be same");
	}

	@Test(priority = 11, dependsOnMethods = "TC52", description = "Booking Multicity Flight without Email")
	public void TC54() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();

		e.setlastName(cred.getLastName());
		e.clickProceed();
		String actual = driver.findElement(By.xpath("//span[@class='InvalidError fl width100 padT2']")).getText();

		Assert.assertEquals(actual, "Email is required");
	}

	@Test(priority = 12, dependsOnMethods = "TC54", description = "Booking Multicity Flight with inValidEmail")
	public void TC55() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();

		e.setMail(cred.getInvalidEmail());
		// e.setlastName(cred.getLastName());
		e.clickProceed();
		String actual = driver.findElement(By.xpath("//span[@class='InvalidError fl width100 padT2']")).getText();

		Assert.assertEquals(actual, "Please provide a valid e-mail id");
	}

	@Test(priority = 13, dependsOnMethods = "TC55", description = "Booking Multicity Flight without Mobile number")
	public void TC56() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();

		e.setMail(cred.getValidEmail());
		// e.setlastName(cred.getLastName());
		e.clickProceed();
		String actual = driver.findElement(By.xpath("//span[@class='InvalidError fl width100 padT2']")).getText();

		Assert.assertEquals(actual, "Mobile Number is required");
	}

	@Test(priority = 13, dependsOnMethods = "TC56", description = "Booking Multicity Flight valid credentials")
	public void TC57() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.setNum(cred.getValidNo());
		// e.setMail(cred.getValidEmail());
		// e.setlastName(cred.getLastName());
		e.clickProceed();
		e.clickOk();
		e.clickPtopay();
	}
}
