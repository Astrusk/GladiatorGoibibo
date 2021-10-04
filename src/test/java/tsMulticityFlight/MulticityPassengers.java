package tsMulticityFlight;

import Base.*;
import flightMulticity.FlightElements;
import url.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MulticityPassengers extends base {

	@Test(description = "Testing with more than 9 passengers")
	public void PassengerLimit() throws Exception {

		Url u = new Url();
		String site = u.getUrl();//get url 
		driver.get(site);//visit url

		driver.manage().window().maximize();

		FlightElements e = new FlightElements(driver);
		e.select();
		e.clickPax();//select passengers
		e.setAdult(9);//set number of adults
		e.setChild(2);//set number of children

		String actual = e.checkPassErr();//check error statement

		Assert.assertEquals(actual, "Maximum of 9 travellers allowed");//compare statement
	}
}