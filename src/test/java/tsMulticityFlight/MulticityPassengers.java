package tsMulticityFlight;

import Base.*;
import flightMulticity.FlightElements;
import url.*;
import flightOneWay.*;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MulticityPassengers extends base {

	@Test(description = "Testing with more than 9 passengers")
	public void TC48() throws Exception {

		Url u = new Url();
		String site = u.getUrl();
		driver.get(site);

		driver.manage().window().maximize();

		FlightElements e = new FlightElements(driver);
		e.select();
		e.clickPax();
		e.setAdult(9);
		e.setChild(2);

		String actual = driver.findElement(By.xpath("//span[@class='status_cont']")).getText();

		Assert.assertEquals(actual, "Maximum of 9 travellers allowed");
		Thread.sleep(2000);
	}
}