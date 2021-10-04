package tsMulticityFlight;

import Base.*;
import flightMulticity.FlightCredentials;
import flightMulticity.FlightElements;
import url.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MulticityFlightTC extends base {

	@Test(priority = 1)
	public void testUrl() {
		Url u = new Url();
		String site = u.getUrl();//get url
		driver.get(site);//visit url
	}

	@Test(priority = 3, dependsOnMethods = "testUrl", description = "Testing by giving only from city without destination")
	public void NoDestination() throws Exception {
		driver.manage().window().maximize();

		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.select();
		e.setFrom(cred.getFromCity());//enter city

		e.clickSearch();//click search button
		String actual = e.errStatus();//check error status

		Assert.assertEquals(actual, "Please add at least one more sector/city to continue");//compare errors
	}

	@Test(priority = 4, dependsOnMethods = "NoDestination", description = "Testing multicity trip Flight with Valid From and Destination with no valid Date for Travel 1")
	public void NoValidDateForTravel1() throws Exception {
		driver.manage().window().maximize();//maximize window
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.setTo(cred.getToCity());//enter city
		e.clickSearch();//click search
		String actual = e.errStatus();//check error status

		Assert.assertEquals(actual, "Please provide a valid Date for Travel 1");//compare error statements
	}

	@Test(priority = 5, dependsOnMethods = "NoValidDateForTravel1", description = "Testing the multicity trip without giving the destination city")
	public void NoDestinationCity2() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		e.setDep();//set departure date
		e.clickSearch();
		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please provide a valid Destination city for Travel 2");
	}

	@Test(priority = 6, dependsOnMethods = "NoDestinationCity2", description = "Testing multicity trip Flight with Valid From and Destination with no valid Date for Travel 2")
	public void NoValidDateForTravel2() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.setTo2(cred.getToCity2());//enter city 2
		e.clickSearch();
		String actual = e.errStatus();

		Assert.assertEquals(actual, "Please provide a valid Date for Travel 2");
	}

	@Test(priority = 7, dependsOnMethods = "NoValidDateForTravel2", description = "Booking Multicity Flight without Title")
	public void NoTitle() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		e.setDep2();//set departure 2 date
		e.clickSearch();//click search
		e.clickBook();//click on book 
		e.clickProceed();//click on proceed

		boolean x = e.checkErr1();

		Assert.assertTrue(x);
	}

	@Test(priority = 8, dependsOnMethods = "NoTitle", description = "Booking Multicity Flight without First Name")
	public void NoFirstName() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.selectTitle(cred.getTitledd());//select title from dropdown
		e.clickProceed();//click on proceed

		String x = e.checkErr2();

		Assert.assertEquals(x, "First Name is required");
	}

	@Test(priority = 9, dependsOnMethods = "NoFirstName", description = "Booking Multicity Flight without Last Name")
	public void NoLastName() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.setName(cred.getFirstName());//enter first name 
		e.clickProceed();//click on proceed
		
		String actual = e.checkErr2();
		Assert.assertEquals(actual, "Last Name is required");
	}

	@Test(priority = 10, dependsOnMethods = "NoLastName", description = "Booking Multicity Flight with same First Name and Last Name")
	public void SameFirstAndLastName() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.setlastName(cred.getFirstName());//enter last name
		e.clickProceed();//click on proceed
		
		String actual = e.checkErr2();

		Assert.assertEquals(actual, "First Name and Last Name cannot be same");
	}

	@Test(priority = 11, dependsOnMethods = "SameFirstAndLastName", description = "Booking Multicity Flight without Email")
	public void NoEmail() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();

		e.setlastName(cred.getLastName());
		e.clickProceed();
		String actual = e.checkErr2();

		Assert.assertEquals(actual, "Email is required");
	}

	@Test(priority = 12, dependsOnMethods = "NoEmail", description = "Booking Multicity Flight with inValidEmail")
	public void InvalidEmail() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();

		e.setMail(cred.getInvalidEmail());//enter mail id
		e.clickProceed();//click proceed
		String actual = e.checkErr2();

		Assert.assertEquals(actual, "Please provide a valid e-mail id");
	}

	@Test(priority = 13, dependsOnMethods = "InvalidEmail", description = "Booking Multicity Flight without Mobile number")
	public void NoNumber() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();

		e.setMail(cred.getValidEmail());
		e.clickProceed();
		String actual = e.checkErr2();

		Assert.assertEquals(actual, "Mobile Number is required");
	}

	@Test(priority = 13, dependsOnMethods = "NoNumber", description = "Booking Multicity Flight valid credentials")
	public void ValidCredentials() throws Exception {
		driver.manage().window().maximize();
		FlightElements e = new FlightElements(driver);
		FlightCredentials cred = new FlightCredentials();
		e.setNum(cred.getValidNo());
		
		e.clickProceed();
		e.clickOk();//click on ok
		e.clickPtopay();//click on proceed to pay
	}
}
