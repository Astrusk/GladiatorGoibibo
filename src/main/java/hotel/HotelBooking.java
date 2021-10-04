package hotel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HotelBooking {

	WebDriver driver;
	public HotelBooking(WebDriver driver) {
		this.driver = driver;
	}
	
	By b_proceed = By.xpath("(//span[@class='GuestDetailsBlockstyles__ButtonSpan-sc-1rzm4ar-7 cpurHQ'])[1]");
	By b_fname = By.xpath("//input[@data-guestdetailsinnerwrapid='first-name']");
	By b_lname = By.xpath("//input[@data-guestdetailsinnerwrapid='last-name']");
	By b_email = By.xpath("//input[@data-guestdetailsinnerwrapid='guest-details-email']");
	By b_mobno = By.xpath("//input[@data-guestdetailsinnerwrapid='guest-details-phone']");
	By b_title = By.xpath("(//select[@class='PersonalProfile__NameEnterSelect-sc-1r9ak8b-7 hkMeMW'])[1]");
	
	public void clickproceed() {							//For clicking on proceed button 
		driver.findElement(b_proceed).click();
	}

	public void enterFName(String fname) {					//For entering first name
		driver.findElement(b_fname).clear();
		driver.findElement(b_fname).sendKeys(fname);
	}
	public void enterLName(String lname) {					//For entering last name
		driver.findElement(b_lname).clear();
		driver.findElement(b_lname).sendKeys(lname);
	}
	
	public void enterEmail(String email) {					//For entering email id
		driver.findElement(b_email).clear();
		driver.findElement(b_email).sendKeys(email);
	}
	
	public void enterMobileNo(String num) {					//For entering mobile number
		driver.findElement(b_mobno).clear();
		driver.findElement(b_mobno).sendKeys(num);
	}
	
	public void dropdown(String data) {						//For selecting title from dropdown
		WebElement a=null;
		a= driver.findElement(b_title);
		
		Select s1=new Select(a);
		s1.selectByVisibleText(data);
	}
	
}
