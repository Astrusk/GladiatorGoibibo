package hotel;

import java.io.FileInputStream;
import java.util.Properties;

public class HotelCredentials {
	Properties prop;
	public String location() throws Exception
	{
		prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));			//for getting the value of location from property file
		String loc = prop.getProperty("toCity");
		return loc;
	}
	
	public String validFirstName() throws Exception
	{
		prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));			//for getting the value of first name from property file
		String fname = prop.getProperty("Name");
		return fname;
	}
	public String validLastName() throws Exception
	{
		prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));			//for getting the value of last name from property file
		String lname = prop.getProperty("validLastName");
		return lname;
	}
	
	public String validNumber() throws Exception
	{
		prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));			//for getting the value of mobile number from property file
		String numv = prop.getProperty("validNumber");
		return numv;
	}
	public String invalidLongNum() throws Exception
	{
		prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));			//for getting the value of invalid mobile Number from property file
		String wnum = prop.getProperty("inValidLongNum");
		return wnum;
	}
	
	public String validEmail() throws Exception
	{
		prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));			//for getting the value of valid Email ID from property file
		String vemail = prop.getProperty("validEmail");
		return vemail;
	}
	public String invalidEmail() throws Exception
	{
		prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));			//for getting the value of invalid EmailId from property file
		String iemail = prop.getProperty("inValidEmail");
		return iemail;
	}
	
	public String selectTitle() throws Exception
	{
		prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));			//for getting the value of title from property file
		String a = prop.getProperty("title");
		return a;
	}


}
