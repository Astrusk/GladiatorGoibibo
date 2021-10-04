package tsMulticityFlight;

import Base.*;
import flightMulticity.FlightElements;
import url.*;
import flightOneWay.*;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Passengers2 extends base {

	@Test(description = "Testing with Infants more than Adults")
	public void TC49() throws Exception {

		Url u = new Url();
		String site = u.getUrl();
		driver.get(site);

		driver.manage().window().maximize();

		FlightElements e = new FlightElements(driver);
		e.select();
		e.clickPax();
		e.setInfant(3);

		String actual = driver.findElement(By.xpath("//span[@class='status_cont']")).getText();
		Thread.sleep(3000);
		Assert.assertEquals(actual, "Number of infants cannot be more than adults");
	}

}
