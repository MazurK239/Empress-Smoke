package uitestsPO;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.epam.web.matcher.testng.Assert.*;

import static sites.EmpressSite.*;

public class LoginTestsCheckbox extends InitTest {

	@BeforeMethod
	public void initTest() {		
		loginPage.isOpened();
	}
	
	@Test
	public void checkedCheckbox() {
		loginPage.checkCheckbox();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
		landingPage.logout();
		assertTrue(loginPage.getEnteredUser().equals(USERNAME));
	}

	@Test
	public void uncheckedCheckbox() {
		loginPage.uncheckCheckbox();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
		landingPage.logout();
		assertTrue(loginPage.getEnteredUser().equals(""));
	}
	
}
