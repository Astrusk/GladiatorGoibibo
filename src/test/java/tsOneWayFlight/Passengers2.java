package tsOneWayFlight;

import Base.*;
import url.*;
import flightOneWay.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Passengers2 extends base {

	@Test(description = "Testing with Infants more than Adults")
	public void MoreInfantsThanAdults() throws Exception {

		Url u = new Url();
		String site = u.getUrl();// getting url
		driver.get(site);// visiting url

		driver.manage().window().maximize();// maximizing window

		flightElements e = new flightElements(driver);

		e.clickPax();// clicking passengers button
		e.setInfant(3);// entering number of infants

		String actual = e.checkErr8(); // getting text from
										// element

		Assert.assertEquals(actual, "Number of infants cannot be more than adults"); // comparing errors
	}

}
