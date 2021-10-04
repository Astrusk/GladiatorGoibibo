package flightMulticity;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FlightElements {

	WebDriver driver;

	public FlightElements(WebDriver driver) {
		this.driver = driver;
	}

	By e_select = By.xpath("//span[@id='multiCity']");
	By e_from = By.xpath(
			"//div[@class='col-md-12 pad0 dF flexWrap brdrRd5 marginB10 fltHpySrchWrp whiteBg']//input[@id='gosuggest_inputSrc']");
	By e_to = By.xpath(
			"//div[@class='col-md-12 pad0 dF flexWrap brdrRd5 marginB10 fltHpySrchWrp whiteBg']//input[@id='gosuggest_inputDest']");
	By e_from2 = By.xpath("//div[contains(@class,'multiShdw')]//input[@id='gosuggest_inputSrc']");
	By e_to2 = By.xpath("//div[contains(@class,'multiShdw')]//input[@id='gosuggest_inputDest']");
	By e_search = By.xpath("//button[normalize-space()='SEARCH']");
	By e_dep = By.xpath("//div[@id='fare_20211017']");
	By e_dep2 = By.xpath("//div[@id='fare_20211022']");
	By e_pax = By.xpath("//div[@id='pax_link_common']");
	By e_adult = By.xpath("//button[@id='adultPaxPlus']");
	By e_child = By.xpath("//button[@id='childPaxPlus']");
	By e_infant = By.xpath("//button[@id='infantPaxPlus']");
	By e_proceed = By.xpath("//div[contains(text(),'Proceed')]");
	By e_book = By.xpath("//button[normalize-space()='BOOK']");
	By e_flightErrStatus = By.xpath("(//span[@class='status_cont red ico13'])[1]");
	By e_risk = By.cssSelector(
			".dweb-commonstyles__ButtonBase-sc-13fxsy5-4.dweb-commonstyles__InsuranceButton-sc-13fxsy5-5.dweb-commonstyles__InsuranceUnselectButton-sc-13fxsy5-7.ggCItP.NqvXb.jpQHoV");
	By e_fname = By.xpath("//input[@id='AdultfirstName1']");
	By e_lname = By.xpath("//input[@id='AdultlastName1']");
	By e_mail = By.xpath("//input[@id='email']");
	By e_num = By.xpath("//input[@id='mobile']");
	By e_ok = By.xpath("//button[normalize-space()='OK']");
	By e_pay = By.xpath(
			"//button[@class='width100 ico14 padLR20 white button hpyOrangeBg large txtCenter marginT15 brdrTLR0 padTB5 bkPgPrcd']");
	By e_passErr = By.xpath("//span[@class='status_cont']");
	By e_err1 = By.xpath("//b[normalize-space()='ERROR:']");
	By e_err2 = By.xpath("//span[@class='InvalidError fl width100 padT2']");

	public void select() {
		driver.findElement(e_select).click();
	}

	public void setFrom(String from) {
		driver.findElement(e_from).clear();
		driver.findElement(e_from).sendKeys(from);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(e_from).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(e_from).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(e_from).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void setTo(String to) {
		driver.findElement(e_to).clear();
		driver.findElement(e_to).sendKeys(to);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(e_to).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(e_to).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(e_to).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void setFrom2(String from) {
		driver.findElement(e_from2).clear();
		driver.findElement(e_from2).sendKeys(from);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(e_from2).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(e_from2).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(e_from2).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void setTo2(String to) {
		driver.findElement(e_to2).clear();
		driver.findElement(e_to2).sendKeys(to);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(e_to2).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(e_to2).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(e_to2).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void clickSearch() throws Exception {
		driver.findElement(e_search).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public String errStatus() {
		String n = driver.findElement(e_flightErrStatus).getText();
		return n;
	}

	public void setDep() {
		List<WebElement> ls = driver.findElements(By.id("departureCalendar"));
		ls.get(0).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(e_dep).click();
	}

	public void setDep2() {
		List<WebElement> ls = driver.findElements(By.id("departureCalendar"));
		ls.get(1).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(e_dep2).click();
	}

	public void clickPax() {
		driver.findElement(e_pax).click();
	}

	public void setAdult(int num) {
		for (int i = 0; i < (num); i++) {
			driver.findElement(e_adult).click();
		}
	}

	public void setChild(int num) {
		for (int i = 0; i < (num); i++) {
			driver.findElement(e_child).click();
		}
	}

	public void setInfant(int num) {
		for (int i = 0; i < (num); i++) {
			driver.findElement(e_infant).click();
		}
	}

	public void clickProceed() {
		driver.findElement(e_proceed).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// span[normalize-space()='Base Fare']
	public void clickBook() {
		driver.findElement(e_book).click();
	}

	public void clickIRisk() {
		driver.findElement(e_risk).click();
	}

	public void setName(String name) {
		driver.findElement(e_fname).sendKeys(name);
	}

	public void setlastName(String lastname) {
		driver.findElement(e_lname).sendKeys(lastname);
	}

	public void selectTitle(String tt) {
		WebElement E_titledd = driver.findElement(By.xpath("//select[@id='Adulttitle1']"));
		Select s1 = new Select(E_titledd);
		s1.selectByVisibleText(tt);
	}

	public void setMail(String mail) {
		driver.findElement(e_mail).clear();
		driver.findElement(e_mail).sendKeys(mail);
	}

	public void setNum(String num) {
		driver.findElement(e_num).clear();
		driver.findElement(e_num).sendKeys(num);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

	}

	public void clickOk() {
		driver.findElement(e_ok).click();
	}

	public void clickPtopay() {
		driver.findElement(e_pay).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public String checkPassErr() {
		String x = driver.findElement(e_passErr).getText();
		return x;
	}
	
	public boolean checkErr1() {
		boolean x;
		x = driver.findElement(e_err1)
		.isDisplayed();
		return x;
	}
	
	public String checkErr2() {
		String x = driver.findElement(e_err2).getText();
		return x;
	}
}
