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
	public void NoLocations() throws Exception {
		driver.manage().window().maximize(); // Maximizing window

		flightElements e = new flightElements(driver);

		e.clickRound();// clicking round trip button
		e.clickSearch();// clicking search button

		String actual = e.errStatus();// getting error statement

		Assert.assertEquals(actual, "Please enter a valid Source"); // comparing error statemenrts
	}

	@Test(priority = 3, dependsOnMethods = "NoLocations", description = "Testing round trip Flight with Valid From and no Destination")
	public void NoDestination() throws Exception {
		driver.manage().window().maximize();

		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setFrom(cred.getFromCity());// selecting city

		e.clickSearch();// clicking search button

		String actual = e.errStatus();// getting error statement

		Assert.assertEquals(actual, "Please enter a valid Destination");// comparing errors
	}

	@Test(priority = 4, dependsOnMethods = "NoDestination", description = "Testing round trip Flight if no valid date")
	public void NoDate() throws Exception {

		driver.manage().window().maximize();

		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setTo(cred.getToCity());// enter source city

		e.clickSearch();// click search button

		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please enter a valid departure date"); // comparing error statement
	}

	@Test(priority = 5, dependsOnMethods = "NoDate", description = "Booking Flight without selecting Insurance options")
	public void NoInsuranceSelected() throws Exception {

		driver.manage().window().maximize();

		flightElements e = new flightElements(driver);

		e.setDep();// select departure date
		e.setRet();// select return date

		e.clickSearch();// click search button
		e.clickBook();// click book button
		e.clickProceed();// click proceed button

		boolean x = e.checkErr1();

		Assert.assertTrue(x);// verifying
	}

	@Test(priority = 6, dependsOnMethods = "NoInsuranceSelected", description = "Booking Flight without entering First or Middle Name")
	public void NoName() throws Exception {
		flightElements e = new flightElements(driver);

		e.clickIRisk();// click i'll risk it button
		e.clickProceed();// click on proceed button

		boolean x = e.checkErr2();

		Assert.assertTrue(x);// verifying
	}

	@Test(priority = 7, dependsOnMethods = "NoName", description = "Booking Flight without selecting Title")
	public void NoTitle() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setName(cred.getFirstName());// enter name
		e.clickProceed();// click on proceed button

		boolean x = e.checkErr3();

		Assert.assertTrue(x);// verifying
	}

	@Test(priority = 8, dependsOnMethods = "NoTitle", description = "Booking Flight without entering Email")
	public void NoEmail() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.selectTitle(cred.getTitledd());// selecting title from drop down
		e.clickProceed();// click on proceed button

		boolean x = e.checkErr4();

		Assert.assertTrue(x);// verifying
	}

	@Test(priority = 9, dependsOnMethods = "NoEmail", description = "Booking Flight with invalid email")
	public void InvalidEmail() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setMail(cred.getInvalidEmail());// entering email
		e.clickProceed();

		boolean x = e.checkErr5();

		Assert.assertTrue(x);// verifying
	}

	@Test(priority = 10, dependsOnMethods = "InvalidEmail", description = "Booking Flight with No Number")
	public void NoNumber() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setMail(cred.getValidEmail());
		e.clickProceed();

		boolean x = e.checkErr6();

		Assert.assertTrue(x);
	}

	@Test(priority = 11, dependsOnMethods = "NoNumber", description = "Booking Flight with invalid Number")
	public void InvalidNumber() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setNum(cred.getInvalidNo());// entering number
		e.clickProceed();

		String err = e.checkErr7();

		Assert.assertEquals(err, "Please enter a valid mobile number"); // comparing error statements
	}

	@Test(priority = 12, dependsOnMethods = "InvalidNumber", description = "Booking Flight with valid Number")
	public void ValidNumber() throws Exception {
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

		boolean x = e.checkVisible();// explicitly waiting

		Assert.assertTrue(x);// verifying if element is displayed
	}
}