package tsRoundTrip;

import Base.*;
import url.*;
import flightRoundTrip.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class roundTripTC extends base {

	@Test(priority = 1)
	public void testUrl() {
		Url u = new Url();
		String site = u.getUrl();// getting url
		driver.get(site);// visiting url
	}

	@Test(priority = 2, dependsOnMethods = "testUrl", description = "Testing round trip Flight with no From and no Destination")
	public void TC31() throws Exception {
		driver.manage().window().maximize(); // Maximizing window

		flightElements e = new flightElements(driver);

		e.clickRound();// clicking round trip button
		e.clickSearch();// clicking search button

		String actual = e.errStatus();// getting error statement

		Assert.assertEquals(actual, "Please enter a valid Source"); // comparing error statemenrts
	}

	@Test(priority = 3, dependsOnMethods = "TC31", description = "Testing round trip Flight with Valid From and no Destination")
	public void TC32() throws Exception {
		driver.manage().window().maximize();

		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setFrom(cred.getFromCity());// selecting city

		e.clickSearch();// clicking search button

		String actual = e.errStatus();// getting error statement

		Assert.assertEquals(actual, "Please enter a valid Destination");// comparing errors
	}

	@Test(priority = 4, dependsOnMethods = "TC32", description = "Testing round trip Flight if no valid date")
	public void TC33() throws Exception {

		driver.manage().window().maximize();

		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setTo(cred.getToCity());// enter source city

		e.clickSearch();// click search button

		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please enter a valid departure date"); // comparing error statement
	}

	@Test(priority = 5, dependsOnMethods = "TC33", description = "Booking Flight without selecting Insurance options")
	public void TC36() throws Exception {

		driver.manage().window().maximize();

		flightElements e = new flightElements(driver);

		e.setDep();// select departure date
		e.setRet();// select return date

		e.clickSearch();// click search button
		e.clickBook();// click book button
		e.clickProceed();// click proceed button

		boolean x = driver
				.findElement(By.xpath("//div[contains(text(),'Please make sure you enter the Name as per your go')]"))
				.isDisplayed();

		Assert.assertTrue(x);// verifying
	}

	@Test(priority = 6, dependsOnMethods = "TC36", description = "Booking Flight without entering First or Middle Name")
	public void TC37() throws Exception {
		flightElements e = new flightElements(driver);

		e.clickIRisk();// click i'll risk it button
		e.clickProceed();// click on proceed button

		boolean x = driver.findElement(By.xpath("//span[normalize-space()='First & Middle Name is mandatory']"))
				.isDisplayed();

		Assert.assertTrue(x);// verifying
	}

	@Test(priority = 7, dependsOnMethods = "TC37", description = "Booking Flight without selecting Title")
	public void TC38() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setName(cred.getFirstName());// enter name
		e.clickProceed();// click on proceed button

		boolean x = driver.findElement(By.xpath("//div[normalize-space()='Title is mandatory']")).isDisplayed();

		Assert.assertTrue(x);// verifying
	}

	@Test(priority = 8, dependsOnMethods = "TC38", description = "Booking Flight without entering Email")
	public void TC39() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.selectTitle(cred.getTitledd());// selecting title from drop down
		e.clickProceed();// click on proceed button

		boolean x = driver.findElement(By.xpath("//span[normalize-space()='Email ID cannot be empty']")).isDisplayed();

		Assert.assertTrue(x);// verifying
	}

	@Test(priority = 9, dependsOnMethods = "TC39", description = "Booking Flight with invalid email")
	public void TC40() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setMail(cred.getInvalidEmail());// entering email
		e.clickProceed();

		boolean x = driver.findElement(By.xpath("//span[normalize-space()='Please enter a valid Email ID']"))
				.isDisplayed();

		Assert.assertTrue(x);// verifying
	}

	@Test(priority = 10, dependsOnMethods = "TC40", description = "Booking Flight with No Number")
	public void TC41() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setMail(cred.getValidEmail());
		e.clickProceed();

		boolean x = driver.findElement(By.xpath("//span[normalize-space()='Mobile Number cannot be empty']"))
				.isDisplayed();

		Assert.assertTrue(x);
	}

	@Test(priority = 11, dependsOnMethods = "TC41", description = "Booking Flight with invalid Number")
	public void TC42() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setNum(cred.getInvalidNo());// entering number
		e.clickProceed();

		String err = driver.findElement(By.xpath("//span[@class='red width100 padT3']")).getText();

		Assert.assertEquals(err, "Please enter a valid mobile number"); // comparing error statements
	}

	@Test(priority = 12, dependsOnMethods = "TC42", description = "Booking Flight with valid Number")
	public void TC43() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setNum(cred.getValidNo());

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Fee & Surcharge']")));// waiting
																														// explicitly
																														// for
																														// element
																														// to
																														// be
																														// visible
		Thread.sleep(1000);// pausing thread

		e.clickProceed();// click on proceed
		e.clickPtopay();// click on proceed to pay button

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Fee & Surcharge']")));
		boolean x = driver.findElement(By.xpath("//span[@class='alertText padL5']//strong[contains(text(),'NOTE:')]"))
				.isDisplayed();// explicitly waiting

		Assert.assertTrue(x);// verifying if element is displayed
	}
}