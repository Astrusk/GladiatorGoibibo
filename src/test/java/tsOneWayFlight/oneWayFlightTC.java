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
	public void TC18() throws Exception {
		driver.manage().window().maximize(); // maximizing window

		flightElements e = new flightElements(driver);

		e.clickSearch(); // click search button

		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please enter a valid Source"); // comparing error
	}

	@Test(priority = 3, dependsOnMethods = "TC18", description = "Testing One Way Flight with Valid From and no Destination")
	public void TC19() throws Exception {
		driver.manage().window().maximize(); // maximizing window

		flightElements e = new flightElements(driver); /* creating class objects */
		flightCredentials cred = new flightCredentials(); /* creating class objects */

		e.setFrom(cred.getFromCity()); // enter from city

		e.clickSearch(); // click search button

		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please enter a valid Destination"); // comparing error
	}

	@Test(priority = 4, dependsOnMethods = "TC19", description = "Testing One Way Flight if no valid date")
	public void TC20() throws Exception {

		driver.manage().window().maximize();

		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setTo(cred.getToCity()); // entering destination city

		e.clickSearch(); // click search button

		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please enter a valid departure date"); // comparing error
	}

	@Test(priority = 5, dependsOnMethods = "TC20", description = "Booking Flight without selecting Insurance options")
	public void TC23() throws Exception {

		driver.manage().window().maximize();

		flightElements e = new flightElements(driver);

		e.setDep(); // selecting departure date

		e.clickSearch(); // clicking search button
		e.clickBook(); // clicking book button
		e.clickProceed(); // clicking proceed button

		boolean x = driver
				.findElement(By.xpath("//div[contains(text(),'Please make sure you enter the Name as per your go')]"))
				.isDisplayed();

		Assert.assertTrue(x); // verifying if element is displayed
	}

	@Test(priority = 6, dependsOnMethods = "TC23", description = "Booking Flight without entering First or Middle Name")
	public void TC24() throws Exception {
		flightElements e = new flightElements(driver);

		e.clickIRisk(); // clicking i'll risk it button
		e.clickProceed(); // clicking proceed button

		boolean x = driver.findElement(By.xpath("//span[normalize-space()='First & Middle Name is mandatory']"))
				.isDisplayed();

		Assert.assertTrue(x); // verifying
	}

	@Test(priority = 7, dependsOnMethods = "TC24", description = "Booking Flight without selecting Title")
	public void TC25() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setName(cred.getFirstName()); // entering name
		e.clickProceed(); // clicking on proceed

		boolean x = driver.findElement(By.xpath("//div[normalize-space()='Title is mandatory']")).isDisplayed();

		Assert.assertTrue(x); // verifying
	}

	@Test(priority = 7, dependsOnMethods = "TC25", description = "Booking Flight without entering Email")
	public void TC26() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.selectTitle(cred.getTitledd()); // selecting title from drop down
		e.clickProceed(); // clicking proceed

		boolean x = driver.findElement(By.xpath("//span[normalize-space()='Email ID cannot be empty']")).isDisplayed();

		Assert.assertTrue(x); // verifying
	}

	@Test(priority = 8, dependsOnMethods = "TC26", description = "Booking Flight with invalid email")
	public void TC27() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setMail(cred.getInvalidEmail()); // entering e-mail
		e.clickProceed(); // clicking proceed button

		boolean x = driver.findElement(By.xpath("//span[normalize-space()='Please enter a valid Email ID']"))
				.isDisplayed();

		Assert.assertTrue(x); // verifying
	}

	@Test(priority = 9, dependsOnMethods = "TC27", description = "Booking Flight with No Number")
	public void TC28() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setMail(cred.getValidEmail());
		e.clickProceed();

		boolean x = driver.findElement(By.xpath("//span[normalize-space()='Mobile Number cannot be empty']"))
				.isDisplayed();

		Assert.assertTrue(x);
	}

	@Test(priority = 10, dependsOnMethods = "TC28", description = "Booking Flight with invalid Number")
	public void TC29() throws Exception {
		flightElements e = new flightElements(driver);
		flightCredentials cred = new flightCredentials();

		e.setNum(cred.getInvalidNo()); // entering number
		e.clickProceed(); // clicking proceed button

		String err = driver.findElement(By.xpath("//span[@class='red width100 padT3']")).getText();

		Assert.assertEquals(err, "Please enter a valid mobile number"); // comparing error
	}

	@Test(priority = 11, dependsOnMethods = "TC29", description = "Booking Flight with valid Number")
	public void TC30() throws Exception {
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

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Fee & Surcharge']"))); // explicit
																															// wait
		boolean x = driver.findElement(By.xpath("//span[@class='alertText padL5']//strong[contains(text(),'NOTE:')]"))
				.isDisplayed();

		Assert.assertTrue(x); // verifying presence of element
	}
}
