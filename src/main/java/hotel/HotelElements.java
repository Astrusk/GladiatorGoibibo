package hotel;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HotelElements {

	WebDriver driver;

	public HotelElements(WebDriver driver) {
		this.driver = driver;
	}

	By e_hotel = By.linkText("Hotels");
	By e_loc = By.id("downshift-1-input");
	By e_daten = By.cssSelector(
			"div[data-testid='openCheckinCalendar'] h4[class='dwebCommonstyles__SmallSectionHeader-sc-112ty3f-7 SearchBlockUIstyles__CheckInDateWrapDiv-sc-fity7j-14 hAEfdZ kSflmU']");
	By e_in = By.cssSelector("span[data-testid='date_10_9_2021']");
	By e_out = By.cssSelector("span[data-testid='date_12_9_2021']");
	By e_guest = By.cssSelector("input[value='2 Guests in 1 Room ']");
	By e_done = By.xpath("//button[normalize-space()='Done']");
	By e_search = By.xpath("//button[normalize-space()='Search Hotels']");
	By e_hotelSelect = By.xpath("(//div[@itemtype='http://schema.org/Hotel'])[1]");
	By e_viewRoomOpt = By.xpath(
			"(//button[@class='dwebCommonstyles__ButtonBase-sc-112ty3f-10 BookingWidgetstyles__ViewRoomOptionsButton-sc-1tsb1-10 KETBj iiLDmi'])[1]");
	By e_hotelClick = By.xpath("(//button[@data-testid='selectRoomBtn'])[1]");
	By r_min = By.xpath(
			"(//span[@class='PaxWidgetstyles__ContentActionIconWrapperSpan-sc-gv3w6r-8 dxKRvV'][normalize-space()='-'])[1]");
	By r_max = By.xpath(
			"(//span[@class='PaxWidgetstyles__ContentActionIconWrapperSpan-sc-gv3w6r-8 dxKRvV'][normalize-space()='+'])[1]");
	By a_min = By.xpath(
			"(//span[@class='PaxWidgetstyles__ContentActionIconWrapperSpan-sc-gv3w6r-8 dxKRvV'][normalize-space()='-'])[2]");
	By a_max = By.xpath(
			"(//span[@class='PaxWidgetstyles__ContentActionIconWrapperSpan-sc-gv3w6r-8 dxKRvV'][normalize-space()='+'])[2]");
	By c_min = By.xpath(
			"(//span[@class='PaxWidgetstyles__ContentActionIconWrapperSpan-sc-gv3w6r-8 dxKRvV'][normalize-space()='-'])[3]");
	By c_max = By.xpath(
			"(//span[@class='PaxWidgetstyles__ContentActionIconWrapperSpan-sc-gv3w6r-8 dxKRvV'][normalize-space()='+'])[3]");
	By e_err1 = By.xpath("//*[text()='Enter Location']");
	By e_err2 = By.xpath("//div[normalize-space()='Please enter a valid first name']");
	By e_err3 = By.xpath("//div[normalize-space()='Please enter a valid email']");
	By e_err4 = By.xpath("//div[normalize-space()='Please enter a valid phone number']");

	public void clickHotel() {
		driver.findElement(e_hotel).click(); // for opening hotels page
	}

	public void setLocation(String loca) throws Exception {
		driver.findElement(e_loc).sendKeys(loca);
		Thread.sleep(1000); // for setting the location
		driver.findElement(e_loc).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		driver.findElement(e_loc).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
	}

	public void clickDate() {
		driver.findElement(e_daten).click(); // for clicking on date button
	}

	public void enterCheckInDate() {
		driver.findElement(e_in).click(); // for selecting check-in date
	}

	public void enterCheckOutDate() {
		driver.findElement(e_out).click(); // for selecting check-out date
	}

	public void clickGuest() {
		driver.findElement(e_guest).click(); // for clicking on Guest button
	}

	public void clickDone() {
		driver.findElement(e_done).click(); // for clicking on done button
	}

	public void searchHotels() {
		driver.findElement(e_search).click(); // for clicking on search hotels button
	}

	public void selectHotel() {
		driver.findElement(e_hotelSelect).click(); // for selecting the hotel
		String s = driver.getWindowHandle();
		System.out.println(s);

		Set<String> alwin = driver.getWindowHandles();

		for (String i : alwin) {
			System.out.println(i);

			if (!i.equals(s)) {
				driver.switchTo().window(i); // for switching to newly opened tab
				System.out.println(driver.getTitle());

			}
		}
	}

	public void clickviewroomopt() {
		driver.findElement(e_viewRoomOpt).click(); // for clicking 'view room options'
	}

	public void clickSelectHotel() {
		driver.findElement(e_hotelClick).click(); // for finally selecting the hotel
	}

	public String roomMin() throws Exception {
		driver.findElement(r_min).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		Alert aa = driver.switchTo().alert(); // Switching to the alert
		String j = aa.getText();
		aa.accept();
		return j;

	}

	public String roomMax() throws Exception {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		WebElement a = null;
		a = driver.findElement(r_max);
		WebElement b = driver.findElement(r_min);
		WebElement c = driver.findElement(a_min);
		for (int i = 0; i < 8; i++) { // for setting number of rooms to 9
			a.click();
		}

		Alert aa = driver.switchTo().alert(); // Switching to the alert
		String j = aa.getText();
		aa.accept();

		for (int i = 0; i < 7; i++) {
			b.click();
			c.click();

		}
		return j;

	}

	public String adultMin() throws Exception {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		WebElement E = null;
		E = driver.findElement(a_min);
		E.click();
		E.click();

		Alert aa = driver.switchTo().alert(); // Switching to the alert
		String j = aa.getText();
		aa.accept();
		return j;

	}

	public String adultMax() throws Exception {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		WebElement a = null;
		a = driver.findElement(a_max);

		for (int i = 0; i < 16; i++) { // for setting number of adult to 17
			a.click();
		}

		Alert aa = driver.switchTo().alert(); // Switching to the alert
		String j = aa.getText();
		aa.accept();
		return j;
	}

	public String childMin() throws Exception {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(c_min).click();

		Alert aa = driver.switchTo().alert(); // Switching to the alert
		String j = aa.getText();
		aa.accept();
		return j;
	}

	public String checkErr1() {
		String x;
		x = driver.findElement(e_err1).getText();
		return x;
	}

	public boolean checkErr2() {
		boolean x;
		x = driver.findElement(e_err2).isDisplayed();
		return x;
	}
	
	public boolean checkErr3() {
		boolean x;
		x = driver.findElement(e_err3).isDisplayed();
		return x;
	}

	public boolean checkErr4() {
		boolean x;
		x = driver.findElement(e_err4).isDisplayed();
		return x;
	}
}
