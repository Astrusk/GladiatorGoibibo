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
		String site = u.getUrl();
		driver.get(site);

		driver.manage().window().maximize();

		flightElements e = new flightElements(driver);

		e.clickPax();
		e.setInfant(3);

		String actual = driver.findElement(By.xpath("//span[@class='status_cont']")).getText();

		Assert.assertEquals(actual, "Number of infants cannot be more than adults");
	}

}
