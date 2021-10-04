package hotelTS;

import Base.base;
import hotel.*;
import url.Url;

import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

public class hotelTC extends base {
	@Test(priority = 1)
	public void Url1() {
		Url u = new Url(); 
		String site = u.getUrl(); //getting url
		driver.manage().window().maximize(); //maximizing window 

		driver.get(site);//visiting url
	}

	@Test(priority = 2, dependsOnMethods = "Url1", description = "Without entering Location")
	public void withoutEnteringLocation() throws Exception {
		HotelElements e = new HotelElements(driver);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); //wait statement
		
		e.clickHotel(); //click on hotels tab
		e.searchHotels(); //click on search
		String a = e.checkErr1(); //checking error message
		Assert.assertEquals(a, "Enter Location"); //comparing statements
	}

	@Test(priority = 3, dependsOnMethods = "withoutEnteringLocation", description = "Testing Search page with no rooms")
	public void testingSearchPageWithNoRooms() throws Exception {
		HotelElements e = new HotelElements(driver);
		HotelCredentials cred = new HotelCredentials();
		String num = cred.location();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		e.setLocation(num); //setting destination

		e.clickGuest(); //enter number of guests
		String s = e.roomMin(); //checking error message
		Assert.assertEquals(s, "Minimum 1 is required"); //comparing status

	}

	@Test(priority = 4, dependsOnMethods = "testingSearchPageWithNoRooms", description = "Testing Search page with no adults")
	public void testingSearchPageWithNoAdults() throws Exception {
		HotelElements e = new HotelElements(driver);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String s = e.adultMin(); //checking error message
		Assert.assertEquals(s, "Minimum 1 adult is required"); //comparing statements

	}

	@Test(priority = 5, dependsOnMethods = "testingSearchPageWithNoAdults", description = "Testing Search page with max rooms")
	public void testingSearchPageWithMaxRooms() throws Exception {
		HotelElements e = new HotelElements(driver);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String s = e.roomMax(); //checking error message
		Thread.sleep(2000); //pausing thread
		Assert.assertEquals(s, "Max allowed upto 8"); //comparing statements
	}

	@Test(priority = 6, dependsOnMethods = "testingSearchPageWithMaxRooms", description = "Testing Search page with more than 16 adults")
	public void testingSearchPageWithMoreThan16Adults() throws Exception {
		HotelElements e = new HotelElements(driver);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String s = e.adultMax(); //checking error message
		Assert.assertEquals(s, "Max allowed upto 16"); //comparing statements

	}

	@Test(priority = 7, dependsOnMethods = "testingSearchPageWithMoreThan16Adults", description = "child limit")
	public void childLimit() throws Exception {
		HotelElements e = new HotelElements(driver);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String s = e.childMin(); //checking error message
		Assert.assertEquals(s, "Not allowed"); //comparing statements
	}

	@Test(priority = 8, dependsOnMethods = "childLimit", description = "Testing Search page with valid details")
	public void testingSearchPageWithValidDetails() throws Exception {

		HotelElements e = new HotelElements(driver);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		e.clickDate(); //select date button
		e.enterCheckInDate(); //enter check in date
		e.enterCheckOutDate(); //enter checkout date
		e.clickGuest(); //enter number of guests
		e.clickDone(); //click done button
		e.searchHotels(); //click on search hotels button
		Assert.assertTrue(driver.getCurrentUrl().contains("find-hotels-in"));
	}

	@Test(priority = 9, dependsOnMethods = "testingSearchPageWithValidDetails", description = "Booking Hotel without entering First or Middle Name")
	public void bookHotelWithNoName() throws Exception {

		HotelElements e = new HotelElements(driver);
		HotelBooking n = new HotelBooking(driver);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		e.selectHotel(); //select hotel
		e.clickviewroomopt(); //click on view room
		e.clickSelectHotel(); //click on select button
		n.clickproceed(); //click on proceed button
		boolean x = e.checkErr2(); //check error
		Assert.assertTrue(x); //verifying presence

	}

	@Test(priority = 10, dependsOnMethods = "bookHotelWithNoName", description = "Booking Hotel with invalid email")
	public void bookHotelWithInvalidEmail() throws Exception {

		HotelElements e = new HotelElements(driver);
		HotelBooking n = new HotelBooking(driver);
		HotelCredentials cred = new HotelCredentials();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		n.enterFName(cred.validFirstName()); //enter first name
		n.enterLName(cred.validLastName()); //enter last name
		n.enterMobileNo(cred.validNumber()); //enter mobile number
		n.enterEmail(cred.invalidEmail()); //enter email id
		n.dropdown(cred.selectTitle()); //select title from dropdown
		n.clickproceed(); //click on proceed button

		boolean x = e.checkErr3(); //checking error element 
		Assert.assertTrue(x); //verifying presence

	}

	@Test(priority = 11, dependsOnMethods = "bookHotelWithInvalidEmail", description = "Booking Hotel with invalid Number")
	public void bookHotelWithValidEmail() throws Exception {

		HotelElements e = new HotelElements(driver);
		HotelBooking n = new HotelBooking(driver);
		HotelCredentials cred = new HotelCredentials();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		n.enterFName(cred.validFirstName()); //enter first name
		n.enterLName(cred.validLastName());//enter last name
		n.enterMobileNo(cred.invalidLongNum());//enter number
		n.enterEmail(cred.validEmail());//enter email id
		n.dropdown(cred.selectTitle());//select title from dropdown 
		n.clickproceed(); //click on proceed
		boolean x = e.checkErr4(); //checking error statement
		Assert.assertTrue(x); //verifying presence

	}

	@Test(priority = 12, dependsOnMethods = "bookHotelWithValidEmail", description = "Booking Hotel with all valid details")
	public void bookHotelWithValidDetails() throws Exception {

		HotelBooking n = new HotelBooking(driver);
		HotelCredentials cred = new HotelCredentials();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		n.enterFName(cred.validFirstName()); //enter first name
		n.enterLName(cred.validLastName()); //enter last name
		n.enterMobileNo(cred.validNumber()); //enter mobile number
		n.enterEmail(cred.validEmail()); //enter email id
		n.dropdown(cred.selectTitle());//selecct title from dropdown
		n.clickproceed(); //click on proceed
		Assert.assertTrue(driver.getCurrentUrl().contains("nhotel-booking")); //verifying webpage
	}

}
