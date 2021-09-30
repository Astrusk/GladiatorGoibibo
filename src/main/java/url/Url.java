package url;

import java.io.*;
import java.util.*;

public class Url {
	public String getUrl() {
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("src/main/resources/credentials.property"));
		}
		
		catch (Exception e){
			System.out.println("Error in file reading");
		}
		String weburl = prop.getProperty("url");
		return weburl;	
	}
}
