package tsOneWayFlight;

import Base.*;
import url.*;
import flightOneWay.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class oneWayFlightTC extends base {

	@Test(priority = 1)
	public void testUrl() {
		Url u = new Url(); // getting url
		String site = u.getUrl();
		driver.get(site); // visiting url
	}

	@Test(priority = 2, dependsOnMethods = "testUrl", description = "Testing One Way Flight with no From and no Destination")
	public void NoFromAndNoTo() throws Exception {
		driver.manage().window().maximize(); // maximizing window

		flightElements e = new flightElements(driver);

		e.clickSearch(); // click search button

		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please enter a valid Source"); // comparing error
	}

	@Test(priority = 3, dependsOnMethods = "NoFromAndNoTo", description = "Testing One Way Flight with Valid From and no Destination")
	public void NoDestination() throws Exception {
		driver.manage().window().maximize(); // maximizing window

		flightElements e = new flightElements(driver); /* creating class objects */
		flightCredentials cred = new flightCredentials(); /* creating class objects */

		e.setFrom(cred.getFromCity()); // enter from city

		e.clickSearch(); // click search button

		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please enter a valid Destination"); // comparing error
	}

	@Test(priority = 4, dependsOnMethods = "NoDestination", description = "Testing One Way Flight if no valid date")
	public void NoDate() throws Exception {

		driver.manage().window().maximize();

		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setTo(cred.getToCity()); // entering destination city

		e.clickSearch(); // click search button

		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please enter a valid departure date"); // comparing error
	}

	@Test(priority = 5, dependsOnMethods = "NoDate", description = "Booking Flight without selecting Insurance options")
	public void NoInsuranceSelected() throws Exception {

		driver.manage().window().maximize();

		flightElements e = new flightElements(driver);

		e.setDep(); // selecting departure date

		e.clickSearch(); // clicking search button
		e.clickBook(); // clicking book button
		e.clickProceed(); // clicking proceed button

		boolean x = e.checkErr1();

		Assert.assertTrue(x); // verifying if element is displayed
	}

	@Test(priority = 6, dependsOnMethods = "NoInsuranceSelected", description = "Booking Flight without entering First or Middle Name")
	public void NoName() throws Exception {
		flightElements e = new flightElements(driver);

		e.clickIRisk(); // clicking i'll risk it button
		e.clickProceed(); // clicking proceed button

		boolean x = e.checkErr2();

		Assert.assertTrue(x); // verifying
	}

	@Test(priority = 7, dependsOnMethods = "NoName", description = "Booking Flight without selecting Title")
	public void NoTitle() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setName(cred.getFirstName()); // entering name
		e.clickProceed(); // clicking on proceed

		boolean x = e.checkErr3();

		Assert.assertTrue(x); // verifying
	}

	@Test(priority = 7, dependsOnMethods = "NoTitle", description = "Booking Flight without entering Email")
	public void NoEmail() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.selectTitle(cred.getTitledd()); // selecting title from drop down
		e.clickProceed(); // clicking proceed

		boolean x = e.checkErr4();

		Assert.assertTrue(x); // verifying
	}

	@Test(priority = 8, dependsOnMethods = "NoEmail", description = "Booking Flight with invalid email")
	public void InvalidEmail() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setMail(cred.getInvalidEmail()); // entering e-mail
		e.clickProceed(); // clicking proceed button

		boolean x = e.checkErr5();

		Assert.assertTrue(x); // verifying
	}

	@Test(priority = 9, dependsOnMethods = "InvalidEmail", description = "Booking Flight with No Number")
	public void NoNumber() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setMail(cred.getValidEmail());
		e.clickProceed();

		boolean x = e.checkErr6();

		Assert.assertTrue(x);
	}

	@Test(priority = 10, dependsOnMethods = "NoNumber", description = "Booking Flight with invalid Number")
	public void InvalidNumber() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setNum(cred.getInvalidNo()); // entering number
		e.clickProceed(); // clicking proceed button

		String err = e.checkErr7();

		Assert.assertEquals(err, "Please enter a valid mobile number"); // comparing error
	}

	@Test(priority = 11, dependsOnMethods = "InvalidNumber", description = "Booking Flight with valid Number")
	public void ValidNumber() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setNum(cred.getValidNo());

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Fee & Surcharge']"))); // explicitly
																															// waiting
																															// for
																															// element
																															// to
																															// be
																															// visible
		Thread.sleep(1000); // pausing thread execution

		e.clickProceed(); // clicking proceed button
		e.clickPtopay(); // clicking proceed to pay button

		boolean x = e.checkVisible();

		Assert.assertTrue(x); // verifying presence of element
	}
}
