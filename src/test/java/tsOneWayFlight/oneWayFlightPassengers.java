package tsOneWayFlight;

import Base.*;
import url.*;
import flightOneWay.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class oneWayFlightPassengers extends base {

	@Test(description = "Testing with more than 9 passengers")
	public void PassegerLimit() throws Exception {

		Url u = new Url();
		String site = u.getUrl();// getting url
		driver.get(site);// visiting url

		driver.manage().window().maximize();// maximizing window

		flightElements e = new flightElements(driver);

		e.clickPax();// clicking passenger button
		e.setAdult(9);// selecting number of adults
		e.setChild(2);// selecting number of children

		String actual = e.checkErr8();// getting text from
										// element

		Assert.assertEquals(actual, "Maximum of 9 travellers allowed");// comparing error statements
	}
}
