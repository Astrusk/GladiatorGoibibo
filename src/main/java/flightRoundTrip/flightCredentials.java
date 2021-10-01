package flightRoundTrip;

import java.util.*;
import java.io.*;

public class flightCredentials {

	public String getToCity() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("toCity");
		return n;
	}

	public String getFromCity() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("fromCity");
		return n;
	}

	public String getFirstName() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("Name");
		return n;
	}

	public String getValidEmail() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("validEmail");
		return n;
	}

	public String getInvalidEmail() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("inValidEmail");
		return n;
	}

	public String getValidNo() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("validNumber");
		return n;
	}

	public String getInvalidNo() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("inValidLongNum");
		return n;
	}

	public String getTitledd() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/credentials.property"));
		String n = prop.getProperty("title");
		return n;
	}
}
