package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginElements {
	WebDriver driver;
	public loginElements(WebDriver driver) {
		this.driver = driver;
	}
	// Below are Objects Property
	By e_num = By.cssSelector("input[name='phone']");
	By e_cont = By.cssSelector("input[value='Continue']");
	By e_loginButton = By.xpath("//span[@class='header-sprite user-icon gr-append-right5']");
	//By e_forget = By.linkText("Forgot your password?");

	public void setNumber(String numb) {
		driver.findElement(e_num).sendKeys(numb);
	}
	public void clickCont() {
		driver.findElement(e_cont).click();
	}
	public void clickLogin() {
		driver.findElement(e_loginButton).click();
	}
	/*public void doLogin(String user,String pwd) {
		setUserName(user);
		setPassword(pwd);
		clickLogin();
	}
	public void clickForget() {
		driver.findElement(e_forget).click();
		*/
	}

