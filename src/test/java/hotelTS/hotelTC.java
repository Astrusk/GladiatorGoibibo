package hotelTS;

import Base.base;
import hotel.*;
import url.Url;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class hotelTC extends base {
	@Test(priority = 1)
	public void Url1() {
		Url u = new Url();
		String site = u.getUrl();
		driver.manage().window().maximize();

		driver.get(site);
	}

	@Test(priority = 2, dependsOnMethods = "Url1", description = "Without entering Location")
	public void withoutEnteringLocation() throws Exception {
		HotelElements e = new HotelElements(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		e.clickHotel();
		e.searchHotels();
		String a = driver.findElement(By.xpath("//*[text()='Enter Location']")).getText();
		Assert.assertEquals(a, "Enter Location");

	}

	@Test(priority = 3, dependsOnMethods = "withoutEnteringLocation", description = "Testing Search page with no rooms")
	public void testingSearchPageWithNoRooms() throws Exception {
		HotelElements e = new HotelElements(driver);
		HotelCredentials cred = new HotelCredentials();
		String num = cred.location();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		e.setLocation(num);

		e.clickGuest();
		String s = e.roomMin();
		Assert.assertEquals(s, "Minimum 1 is required");

	}

	@Test(priority = 4, dependsOnMethods = "testingSearchPageWithNoRooms", description = "Testing Search page with no adults")
	public void testingSearchPageWithNoAdults() throws Exception {
		HotelElements e = new HotelElements(driver);
		HotelCredentials cred = new HotelCredentials();
		String num = cred.location();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String s = e.adultMin();
		Assert.assertEquals(s, "Minimum 1 adult is required");

	}

	@Test(priority = 5, dependsOnMethods = "testingSearchPageWithNoAdults", description = "Testing Search page with max rooms")
	public void testingSearchPageWithMaxRooms() throws Exception {
		HotelElements e = new HotelElements(driver);
		HotelCredentials cred = new HotelCredentials();
		String num = cred.location();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String s = e.roomMax();
		Thread.sleep(2000);
		Assert.assertEquals(s, "Max allowed upto 8");
	}

	@Test(priority = 6, dependsOnMethods = "testingSearchPageWithMaxRooms", description = "Testing Search page with more than 16 adults")
	public void testingSearchPageWithMoreThan16Adults() throws Exception {
		HotelElements e = new HotelElements(driver);
		HotelCredentials cred = new HotelCredentials();
		String num = cred.location();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String s = e.adultMax();
		Assert.assertEquals(s, "Max allowed upto 16");

	}

	@Test(priority = 7, dependsOnMethods = "testingSearchPageWithMoreThan16Adults", description = "child limit")
	public void childLimit() throws Exception {
		HotelElements e = new HotelElements(driver);
		HotelCredentials cred = new HotelCredentials();
		String num = cred.location();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String s = e.childMin();
		Assert.assertEquals(s, "Not allowed");
	}

	@Test(priority = 8, dependsOnMethods = "childLimit", description = "Testing Search page with valid details")
	public void testingSearchPageWithValidDetails() throws Exception {

		HotelElements e = new HotelElements(driver);
		HotelCredentials cred = new HotelCredentials();
		String num = cred.location();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		e.clickDate();
		e.enterCheckInDate();
		e.enterCheckOutDate();
		e.clickGuest();
		e.clickDone();
		e.searchHotels();
		Assert.assertTrue(driver.getCurrentUrl().contains("find-hotels-in"));
	}

	@Test(priority = 9, dependsOnMethods = "testingSearchPageWithValidDetails", description = "Booking Hotel without entering First or Middle Name")
	public void bookHotelWithNoName() throws Exception {

		HotelElements e = new HotelElements(driver);
		HotelBooking n = new HotelBooking(driver);
		HotelCredentials cred = new HotelCredentials();
		String num = cred.location();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		e.selectHotel();
		e.clickviewroomopt();
		e.clickSelectHotel();
		n.clickproceed();
		boolean x = driver.findElement(By.xpath("//div[normalize-space()='Please enter a valid first name']"))
				.isDisplayed();
		Assert.assertTrue(x);

	}

	@Test(priority = 10, dependsOnMethods = "bookHotelWithNoName", description = "Booking Hotel with invalid email")
	public void bookHotelWithInvalidEmail() throws Exception {

		HotelElements e = new HotelElements(driver);
		HotelBooking n = new HotelBooking(driver);
		HotelCredentials cred = new HotelCredentials();
		String num = cred.location();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		n.enterFName(cred.validFirstName());
		n.enterLName(cred.validLastName());
		n.enterMobileNo(cred.validNumber());
		n.enterEmail(cred.invalidEmail());
		n.dropdown(cred.selectTitle());
		n.clickproceed();

		boolean x = driver.findElement(By.xpath("	//div[normalize-space()='Please enter a valid email']"))
				.isDisplayed();
		Assert.assertTrue(x);

	}

	@Test(priority = 11, dependsOnMethods = "bookHotelWithInvalidEmail", description = "Booking Hotel with invalid Number")
	public void bookHotelWithValidEmail() throws Exception {

		HotelElements e = new HotelElements(driver);
		HotelBooking n = new HotelBooking(driver);
		HotelCredentials cred = new HotelCredentials();
		String num = cred.location();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		n.enterFName(cred.validFirstName());
		n.enterLName(cred.validLastName());
		n.enterMobileNo(cred.invalidLongNum());
		n.enterEmail(cred.validEmail());
		n.dropdown(cred.selectTitle());
		n.clickproceed();
		boolean x = driver.findElement(By.xpath("//div[normalize-space()='Please enter a valid phone number']"))
				.isDisplayed();
		Assert.assertTrue(x);

	}

	@Test(priority = 12, dependsOnMethods = "bookHotelWithValidEmail", description = "Booking Hotel with all valid details")
	public void bookHotelWithValidDetails() throws Exception {

		HotelElements e = new HotelElements(driver);
		HotelBooking n = new HotelBooking(driver);
		HotelCredentials cred = new HotelCredentials();
		String num = cred.location();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		n.enterFName(cred.validFirstName());
		n.enterLName(cred.validLastName());
		n.enterMobileNo(cred.validNumber());
		n.enterEmail(cred.validEmail());
		n.dropdown(cred.selectTitle());
		n.clickproceed();
		Assert.assertTrue(driver.getCurrentUrl().contains("nhotel-booking"));
	}

}
