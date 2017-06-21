package model;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class UserPrefsSharingSection extends Section {

	@FindBy(xpath="./*/*/button")
	private MldLock lock;
	
	@FindBy(xpath=".//input")
	private TextField input;
	
	@FindBy(xpath=".//ul[@class='suggest-list']/li/span[2]")
	private Elements<Text> users;
	
	public String getLockState() {
		return lock.getState();
	}

	public void unlockLock() {
		lock.unlock();
		Timer.waitCondition(() -> getLockState().equals("Unlocked"));
	}

	public UserPrefsSharingSection lockLock() {
		lock.lock();
		Timer.waitCondition(() -> getLockState().equals("Locked"));
		return this;
	}
	
	public Boolean isUserInList(String username) {
		return users.get(username) != null;
	}

	public UserPrefsSharingSection addUser(String username) {
		input.newInput(username);
		input.sendKeys(Keys.RETURN);
		return this;
	}
	
	public UserPrefsSharingSection addUsers(List<String> usernames) {
		for (String username : usernames) {
			this.addUser(username);
		}
		return this;
	}

	public UserPrefsSharingSection removeUser(String username) {
		try {
			users.get(username).get(By.xpath("./../span[@class='remove-item']")).click();
			Timer.waitCondition(() -> !isUserInList(username));
		} catch (NullPointerException npe) {
			System.out.println("User " + username + " was not found in the permissions list!");
		}
		return this;
	}
	
	public UserPrefsSharingSection removeUsers(List<String> usernames) {
		for (String username : usernames) {
			this.removeUser(username);
		}
		return this;
	}

	public UserPrefsSharingSection removeAllUsers() {
		for (Text username : users) {
			this.removeUser(username.getText());
		}
		return this;
	}
	
	public boolean isInputAvailable() {
		return input.getWebElement().isEnabled();
	}

}
