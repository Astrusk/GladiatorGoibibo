package login;
import java.io.*;
import java.util.*;

public class loginCredentials {

	public String getValidno() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("validNumber");
		return n;
	}
	
	public String getinValidLongNum() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("inValidLongNum");
		return n;
	}
	
	public String getinValidtendnumber() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("inValidtendnumber");
		return n;
	}
	
	public String getinValidNum() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("inValidNum");
		return n;
	}
	
	public String getinValidNumAlpha() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("inValidNumAlpha");
		return n;
	}
	
	public String getinValidNumSpecialChar() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("inValidNumSpecialChar");
		return n;
	}
	
	public String getvalidEmail() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("validEmail");
		return n;
	}
	
	public String getinValidEmail() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("inValidEmail");
		return n;
	}
	
	public String getName() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("Name");
		return n;
	}
	
	public String getinValidName() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("inValidName");
		return n;
	}
	
	public String getinValidNameSpec() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("inValidNameSpec");
		return n;
	}
	
	public String getinValidOtp() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("getinValidOtp");
		return n;
	}
		
	
}
