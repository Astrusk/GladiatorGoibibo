package loginTs;

import Base.base;
import login.*;
import url.Url;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import org.testng.annotations.Test;

public class loginTC extends base {

	@Test
	public void testUrl() {
		Url u = new Url(); 
		String site = u.getUrl();//getting url
		driver.get(site);//visiting url
	}

	@Test(dependsOnMethods = "testUrl", priority = 1, description = "Testing Login page with invalid number ")
	public void InvalidNumber() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getinValidtendnumber();
		e.clickLogin(); //clicking on login button
		e.clearNumber(); //clear element
		e.setNumber(num); //enter number
		e.clickCont(); //click on continue button
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")));
		
		String actual = e.checkErrStatus(); //check error status
		Assert.assertEquals(actual, "Please enter a valid mobile number"); //comparing error statement
		e.clickClose(); //click on close button

	}

	@Test(dependsOnMethods = "testUrl", priority = 2, description = "Testing Login page with invalid number containing alphabets")
	public void InvalidNumberAlphabets() throws Exception {
		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num1 = cred.getinValidNumAlpha();

		e.clickLogin(); //click on login button
		e.clearNumber(); //clear entry field
		e.setNumber(num1); //enter number
		e.clickCont(); //click on continue button
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")));
		
		String actual = e.checkErrStatus(); //check error status
		Assert.assertEquals(actual, "Alphabets and Special Characters are not allowed"); //checking error status
		e.clickClose(); //click on close button

	}

	@Test(dependsOnMethods = "testUrl", priority = 3, description = "Testing Login page with invalid number containing special characters")
	public void InvalidNumberSpecialChar() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getinValidNumSpecialChar();
		e.clickLogin(); //click on login button
		e.setNumber(num); //set number
		e.clickCont(); //click on continue
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")));

		String actual = e.checkErrStatus(); //checking error status
		Assert.assertEquals(actual, "Alphabets and Special Characters are not allowed"); //comparing error statements
		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 4, description = "Testing Login page without entering a number ")
	public void NoNumber() throws Exception {

		loginElements e = new loginElements(driver);

		e.clickLogin(); //click on login button
		boolean x = e.checkErr1(); //check error statement
		Assert.assertTrue(x); //verifying presence
		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 5, description = "Testing Login page with invalid number ")
	public void InvalidNumber2() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getinValidNum();
		e.clickLogin(); //click on log in 
		e.setNumber(num); //enter number
		e.clickCont(); //click on continue
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[@class='gr-appendTop10 gr-font12 gr-redText']")));
		
		String actual = e.checkErrStatus(); //checking error status
		Assert.assertEquals(actual, "Please enter a 10 digit mobile number"); //compare error statement
		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 6, description = "Testing Login page with Valid number and invalid OTP")
	public void InvalidOTP() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getValidno();
		Scanner sc1 = new Scanner(System.in);

		e.clickLogin(); //click on login
		e.setNumber(num); //enter number
		
		e.clickCont(); //click on continue button
		Thread.sleep(2000);
		System.out.println("Enter the OTP:");
		String otp = "";
		System.out.println("Reading OTP");
		Thread.sleep(2000);
		otp = sc1.nextLine(); //scanning otp from user
		System.out.println("Entered OTP: " + otp);
		e.setOtp(otp); //enter otp
		Thread.sleep(5000);

		String actual = e.checkErr2(); //check error statement
		Assert.assertEquals(actual, "Please enter a valid OTP"); //verifying presence

		e.clickClose();

	}

	@Test(dependsOnMethods = "testUrl", priority = 7, description = "Testing Login page with Valid number and valid OTP")
	public void ValidOTP() throws Exception {

		loginElements e = new loginElements(driver);
		loginCredentials cred = new loginCredentials();
		String num = cred.getValidno();
		Scanner sc = new Scanner(System.in);

		e.clickLogin(); //click login
		e.setNumber(num); //enter number
		e.clickCont(); //click on continue
		System.out.println("Enter the OTP:");
		String otp = sc.next(); //scanning otp from user
		e.setOtp(otp); //entering otp
		Thread.sleep(5000); //pausing thread
		boolean x = e.checkErr3(); //checking page 
		Assert.assertTrue(x); //verifying presence 
		System.out.println("Login was successful");

		e.clickLogout(); //logging out
		e.clickClose(); //closing window

	}

}
