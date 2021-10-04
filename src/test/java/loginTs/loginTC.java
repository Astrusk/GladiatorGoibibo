package loginTs;

import Base.base;
import login.*;
import url.Url;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginTC extends base {

	@Test
	public void testUrl() {
		Url u = new Url();
		String site = u.getUrl();
		driver.get(site);
	}

	@Test(dependsOnMethods = "testUrl", priority = 1, description = "Testing Login page with invalid number ")
	public void TC03() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getinValidtendnumber();
		e.clickLogin();
		e.clearNumber();
		e.setNumber(num);
		e.clickCont();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")));
		String actual = driver.findElement(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")).getText();
		Assert.assertEquals(actual, "Please enter a valid mobile number");
		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 2, description = "Testing Login page with invalid number containing alphabets")
	public void TC04() throws Exception {
		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num1 = cred.getinValidNumAlpha();

		e.clickLogin();
		e.clearNumber();
		e.setNumber(num1);
		e.clickCont();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")));
		String actual = driver.findElement(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")).getText();
		Assert.assertEquals(actual, "Alphabets and Special Characters are not allowed");
		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 3, description = "Testing Login page with invalid number containing special characters")
	public void TC05() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getinValidNumSpecialChar();
		e.clickLogin();
		e.setNumber(num);
		e.clickCont();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")));

		String actual = driver.findElement(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")).getText();
		Assert.assertEquals(actual, "Alphabets and Special Characters are not allowed");
		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 4, description = "Testing Login page without entering a number ")
	public void TC06() throws Exception {

		loginElements e = new loginElements(driver);

		e.clickLogin();
		boolean x = driver.findElement(By.xpath("//span[@class='header-sprite user-icon gr-append-right5']"))
				.isDisplayed();
		Assert.assertTrue(x);
		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 5, description = "Testing Login page with invalid number ")
	public void TC07() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getinValidNum();
		e.clickLogin();
		e.setNumber(num);
		e.clickCont();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")));
		String actual = driver.findElement(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")).getText();
		Assert.assertEquals(actual, "Please enter a 10 digit mobile number");
		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 6, description = "Testing Login page with Valid number and invalid OTP")
	public void TC02() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getValidno();
		Scanner sc1 = new Scanner(System.in);

		e.clickLogin();
		e.setNumber(num);
		Thread.sleep(1000);
		e.clickCont();
		Thread.sleep(3000);
		System.out.println("Enter the OTP:");
		String otp = "";
		System.out.println("Reading OTP");
		Thread.sleep(5000);
		otp = sc1.nextLine();
		System.out.println("Entered OTP: " + otp);
		e.setOtp(otp);
		Thread.sleep(5000);

		String actual = driver.findElement(By.xpath("//span[@class='robotoRegular gr-font12 gr-redText']")).getText();
		System.out.println(actual);
		Assert.assertEquals(actual, "Please enter a valid OTP");

		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 7, description = "Testing Login page with Valid number and valid OTP")
	public void TC01() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getValidno();
		Scanner sc = new Scanner(System.in);

		e.clickLogin();
		e.setNumber(num);
		e.clickCont();
		System.out.println("Enter the OTP:");
		String otp = sc.next();
		e.setOtp(otp);
		Thread.sleep(5000);
		boolean x = driver.findElement(By.xpath("//p[contains(@class,'gr-font10 gr-blue-text gr-light gr-helvetica')]"))
				.isDisplayed();
		Assert.assertTrue(x);
		System.out.println("Login was successful");

		e.clickLogout();
		e.clickClose();

	}

}
