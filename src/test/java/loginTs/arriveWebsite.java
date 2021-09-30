package loginTs;
import Base.base;
import url.Url;
import org.testng.annotations.Test;


public class arriveWebsite extends base{
		
  @Test
  public void Test1() {
	  Url u = new Url();
	  String site = u.getUrl();
	  driver.get(site);
  }

}
