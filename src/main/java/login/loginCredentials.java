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
	
}
