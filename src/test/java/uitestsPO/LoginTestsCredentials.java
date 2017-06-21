package uitestsPO;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.web.matcher.testng.Assert;

import util.UsersProvider;

import static com.epam.web.matcher.testng.Assert.*;

import static sites.EmpressSite.*;

public class LoginTestsCredentials extends InitTest{

	@BeforeMethod
	public void initTest() {		
		loginPage.open();
		loginPage.checkOpened();
	}
	
	@Test
	public void initialState() {
		Assert.isTrue(loginPage.getEnteredUser().equals(""));
	}
	
	@Test
	public void bothEmpty() {
		loginPage.loginAs("", "");
		assertTrue(() -> loginPage.loginErrorPresent());
		assertTrue(() -> loginPage.passwordErrorPresent());
	}
	
	@Test
	public void loginEmpty() {
		loginPage.loginAs("", "p@ss");
		assertTrue(() -> loginPage.loginErrorPresent());
	}
	
	@Test
	public void passwordEmpty() {
		loginPage.loginAs("login", "");
		assertTrue(() -> loginPage.passwordErrorPresent());
	}
	
	@Test(dataProvider="users", dataProviderClass=UsersProvider.class)
	public void incorrectCredentials(String login, String password) {
		loginPage.loginAs(login, password);
		assertTrue(() -> loginPage.incorrectErrorPresent());
	}
	
	@Test
	public void successfulLoginSubmit() {
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
		landingPage.logout();
		loginPage.checkOpened();
	}
	
	@Test
	public void successfulLoginEnter() {
		loginPage.loginAsReturn(USERNAME, PASSWORD);
		landingPage.checkOpened();
		landingPage.logout();
		loginPage.checkOpened();
	}
	
	
}
