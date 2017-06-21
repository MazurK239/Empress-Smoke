package uitestsPO;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.web.matcher.testng.Assert;

import static com.epam.web.matcher.testng.Assert.*;

import static sites.EmpressSite.*;

public class LoginTestsCheckbox extends InitTest {

	@BeforeMethod
	public void initTest() {
		loginPage.open();
		loginPage.checkOpened();
	}
	
	@Test (priority=0)
	public void initialState() {
		Assert.isTrue(() -> !loginPage.checkbox.wetherChecked());
	}
	
	@Test (priority=1)
	public void checkedCheckbox() {
		loginPage.checkCheckbox();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
		landingPage.logout();
		assertTrue(loginPage.getEnteredUser().equals(USERNAME));
	}

	@Test (priority=1)
	public void uncheckedCheckbox() {
		loginPage.uncheckCheckbox();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
		landingPage.logout();
		assertTrue(loginPage.getEnteredUser().equals(""));
	}
	
}
