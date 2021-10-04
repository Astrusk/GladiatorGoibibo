package tsRoundTrip;

import Base.*;
import url.*;
import flightRoundTrip.*;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Passengers2 extends base {

	@Test(description = "Testing with Infants more than Adults")
	public void TC35() throws Exception {

		Url u = new Url();
		String site = u.getUrl();// getting url
		driver.get(site);// visiting url

		driver.manage().window().maximize();// maximizing window

		flightElements e = new flightElements(driver);

		e.clickRound();// click round trip button
		e.clickPax();// click passengers button
		e.setInfant(3);// select number of infants

		String actual = driver.findElement(By.xpath("//span[@class='status_cont']")).getText();// getting text from web
																								// elemnent

		Assert.assertEquals(actual, "Number of infants cannot be more than adults");// comparing error statements
	}
}