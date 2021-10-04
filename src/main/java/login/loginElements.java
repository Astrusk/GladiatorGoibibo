package login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class loginElements {
	WebDriver driver;
	public loginElements(WebDriver driver) {
		this.driver = driver;
	}
	// Below are Objects Property
	By e_num = By.cssSelector("input[name='phone']");
	By e_cont = By.cssSelector("input[value='Continue']");
	By e_otp1 = By.xpath("//input[@aria-label='Please enter verification code. Digit 1']");
	By e_loginButton = By.xpath("//span[@class='header-sprite user-icon gr-append-right5']");
	By e_close = By.xpath("//span[@class='logSprite icClose']");
	By e_logout= By.xpath("//b[normalize-space()='Logout']");
	By e_resend= By.xpath("//div[contains(@class,'lgRightSection')]//li[2]");
	By e_name = By.xpath("//input[@placeholder='Enter your full name']");
	By e_email = By.xpath("//input[@placeholder='Enter your email address']");
	By e_signup= By.xpath("//button[normalize-space()='Lets Go']");
	//By e_forget = By.linkText("Forgot your password?");

	public void clearNumber() {
		driver.findElement(e_num).clear();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void setNumber(String numb) {
		driver.findElement(e_num).sendKeys(numb);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public void clickCont() {
		driver.findElement(e_cont).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public void clickLogin() {
		driver.findElement(e_loginButton).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public void clickClose() {
		driver.findElement(e_close).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public void setOtp(String otp) {
		driver.findElement(e_otp1).sendKeys(otp);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public void clickResend() {
		driver.findElement(e_resend).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void clickLogout() {
		driver.get("https://www.goibibo.com/myaccount/settings/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(e_logout).click();
	}
	
	public void setName(String name) {
		driver.findElement(e_name).clear();
		driver.findElement(e_name).sendKeys(name);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	public void setEmail(String email) {
		driver.findElement(e_email).clear();
		driver.findElement(e_email).sendKeys(email);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void clickSignup() {
		driver.findElement(e_signup).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
		
		
	}

