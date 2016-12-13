package util;

import org.testng.annotations.DataProvider;

public class UsersProvider {
	
	@DataProvider(name="users")
	public static Object[][] provideUsers() {
		return new Object[][]{
			{"login", "p@ssw0rd"},
			{"user1", "password"},
			{"user239", "password"}
		};
	}

}
