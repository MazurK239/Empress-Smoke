package uitestsPO;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static sites.EmpressSite.*;

public class DashboardFilterTests extends InitTest{

	@BeforeClass
	public void initHere() {
		loginPage.open();
		loginPage.checkOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
	}
	
	@Test
	public void recentFilters() {
		
	}
	
	
}
