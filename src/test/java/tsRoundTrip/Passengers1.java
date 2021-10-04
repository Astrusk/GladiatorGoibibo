package tsRoundTrip;

import Base.*;
import url.*;
import flightRoundTrip.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Passengers1 extends base {

	@Test(description = "Testing with more than 9 passengers")
	public void PassengerLimit() throws Exception {

		Url u = new Url();
		String site = u.getUrl();// getting url
		driver.get(site);// visiting url

		driver.manage().window().maximize();// maximizing window

		flightElements e = new flightElements(driver);

		e.clickRound();// clicking round trip button
		e.clickPax();// click passengers button
		e.setAdult(9);// select number of adults
		e.setChild(2);// select number of children

		String actual = e.checkErr8();// getting text from web element

		Assert.assertEquals(actual, "Maximum of 9 travellers allowed");// comparing error statements
	}
}
