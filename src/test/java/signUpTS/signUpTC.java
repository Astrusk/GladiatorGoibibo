package signUpTS;

import Base.base;
import login.*;
import url.Url;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class signUpTC extends base {
	@Test
	public void testUrl() {
		Url u = new Url();
		String site = u.getUrl();//getting url
		driver.get(site);//visiting url
	}

	@Test(dependsOnMethods = "testUrl", priority = 1, description = "Testing Signup page without entering a number ")
	public void NoNumber() throws Exception {

		loginElements e = new loginElements(driver);

		e.clickLogin(); //click login button 
		boolean x = e.checkErr1();//checking error statement
		Assert.assertTrue(x);//verifying statement
		e.clickClose();//close button

	}

	@Test(dependsOnMethods = "NoNumber", priority = 2, description = "Testing Signup page with invalid non10digit number ")
	public void Invalid10Digit() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getinValidNum();
		e.clickLogin();//click login
		e.setNumber(num);//enter number
		e.clickCont();//click continue button
		String actual = e.checkErrStatus();//check error statement
		Assert.assertEquals(actual, "Please enter a 10 digit mobile number");//comparing error 
		e.clickClose();

	}

	@Test(dependsOnMethods = "Invalid10Digit", priority = 3, description = "Testing Signup page with invalid number containing alphabets")
	public void InvalidNumberAlphabets() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num1 = cred.getinValidNumAlpha();

		e.clickLogin();//click login
		e.clearNumber();//enter number
		e.setNumber(num1);//enter email
		e.clickCont();//click continue

		String actual = e.checkErrStatus();//check error statement
		Assert.assertEquals(actual, "Alphabets and Special Characters are not allowed");//compare errors
		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 4, description = "Testing Signup page with invalid number containing special characters")
	public void InvalidNumberSpecialChar() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getinValidNumSpecialChar();
		e.clickLogin();//click login
		e.setNumber(num);//enter number
		e.clickCont();//click continue
		String actual = e.checkErrStatus();//get error statement
		Assert.assertEquals(actual, "Alphabets and Special Characters are not allowed");//compare errors
		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 5, description = "Testing Signup page with invalid number ")
	public void InvalidNumber() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getinValidtendnumber();
		e.clickLogin();
		e.clearNumber();//clear field
		e.setNumber(num);
		e.clickCont();
		String actual = e.checkErrStatus();
		Assert.assertEquals(actual, "Please enter a valid mobile number");
		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 6, description = "Testing Sign up page with Valid number and invalid OTP")
	public void InvalidOTP() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getValidno();
		Scanner sc1 = new Scanner(System.in);//initiate scanner

		e.clickLogin();
		e.setNumber(num);
		
		e.clickCont();//pause thread
		Thread.sleep(3000);
		String otp = "";
		System.out.println("Reading OTP");
		Thread.sleep(5000);
		otp = sc1.nextLine();//get otp from user
		System.out.println("Entered OTP: " + otp);
		e.setOtp(otp);//enter otp
		Thread.sleep(5000);
		String actual = e.checkErr2();
		System.out.println(actual);
		Assert.assertEquals(actual, "Please enter a valid OTP");
		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 7, description = "Testing Sign up page with inValid number name and valid email(valid login credentials)")
	public void InvalidNameWithNumbers() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getValidno();
		String name = cred.getinValidName();
		String email = cred.getvalidEmail();
		Scanner sc = new Scanner(System.in);

		e.clickLogin();
		e.setNumber(num);
		e.clickCont();
		System.out.println("Enter a valid OTP:");
		String otp = sc.next();
		e.setOtp(otp);
		Thread.sleep(5000);
		e.setName(name);
		e.setEmail(email);
		e.clickSignup();//click on sign up button
	
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")));//wait explicitly for element to be visible

		String actual = e.checkErrStatus();

		Assert.assertEquals(actual, "Please enter valid name using only alphabets");

	}

	@Test(dependsOnMethods = "InvalidNameWithNumbers", priority = 8, description = "Testing Sign up page with inValid special character name and valid email(valid login credentials)")
	public void InvalidNameWithSpecialChar() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String name = cred.getinValidNameSpec();
		String email = cred.getvalidEmail();
		e.setName(name);
		e.setEmail(email);
		e.clickSignup();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")));

		String actual = e.checkErrStatus();
		Assert.assertEquals(actual, "Please enter valid name using only alphabets");

	}

	@Test(dependsOnMethods = "InvalidNameWithSpecialChar", priority = 9, description = "Testing Sign up page with Valid name and inValid email(valid login credentials)")
	public void InvalidEmail() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String name = cred.getName();
		String email = cred.getinValidEmail();
		e.setName(name);
		e.setEmail(email);
		e.clickSignup();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")));

		String actual = e.checkErrStatus();
		Assert.assertEquals(actual, "Enter a valid email");

	}

	@Test(dependsOnMethods = "InvalidEmail", priority = 10, description = "Testing Sign up page with Valid name and valid email(valid login credentials)")
	public void ValidCredentials() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String name = cred.getName();
		String email = cred.getvalidEmail();
		e.setName(name);
		e.setEmail(email);
		e.clickSignup();

		Thread.sleep(2000);
		boolean x = e.checkErr3();
		Assert.assertTrue(x);//check if sign up successful
		System.out.println("Login was successful");

		e.clickLogout();//logging out
		e.clickClose();//close

	}
}
