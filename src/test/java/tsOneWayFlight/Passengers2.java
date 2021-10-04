package tsOneWayFlight;

import Base.*;
import url.*;
import flightOneWay.*;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Passengers2 extends base {

	@Test(description = "Testing with Infants more than Adults")
	public void TC22() throws Exception {

		Url u = new Url();
		String site = u.getUrl();// getting url
		driver.get(site);// visiting url

		driver.manage().window().maximize();// maximizing window

		flightElements e = new flightElements(driver);

		e.clickPax();// clicking passengers button
		e.setInfant(3);// entering number of infants

		String actual = driver.findElement(By.xpath("//span[@class='status_cont']")).getText(); // getting text from
																								// element

		Assert.assertEquals(actual, "Number of infants cannot be more than adults"); // comparing errors
	}

}
