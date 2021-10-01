package loginTs;
import Base.base;
import login.*;
import url.Url;

import org.testng.annotations.Test;

public class loginTC extends base{
	//loginElements e = new loginElements(driver);
	
	@Test
	public void testUrl() {
		Url u = new Url();
		String site = u.getUrl();
		driver.get(site);
}
	
  @Test(dependsOnMethods = "testUrl")
  public void TC01() throws Exception {
	  driver.manage().window().maximize();
	  loginElements e = new loginElements(driver);
	  loginCredentials cred = new loginCredentials();
	  String num = cred.getValidno();
	  
	  e.clickLogin();
	  e.setNumber(num);
	  e.clickCont();
	  //Thread.sleep(1000);
  }
}
