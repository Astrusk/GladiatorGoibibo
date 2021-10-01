package tsOneWayFlight;

import Base.*;
import url.*;
import flightOneWay.*;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class oneWayFlightPassengers extends base {

	@Test(description = "Testing with more than 9 passengers")
	public void TC21() throws Exception {

		Url u = new Url();
		String site = u.getUrl();
		driver.get(site);

		driver.manage().window().maximize();

		flightElements e = new flightElements(driver);

		e.clickPax();
		e.setAdult(9);
		e.setChild(2);

		String actual = driver.findElement(By.xpath("//span[@class='status_cont']")).getText();

		Assert.assertEquals(actual, "Maximum of 9 travellers allowed");
	}
}
