package model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class SharingPanel extends Section {

	@FindBy(css="div > label > input")
	public MldCheckbox mainCheckbox;
	
	@FindBy(css=".suggest-list-input")
	private TextField input;
	
	@FindBy(xpath=".//ul[@class='suggest-list']/li/span[2]")
	private Elements<Text> users;

	public Boolean isUserInList(String username) {
		return users.get(username) != null;
	}
	
	public SharingPanel addUser(String username) {
		input.newInput(username);
		input.sendKeys(Keys.RETURN);
		return this;
	}

	public SharingPanel removeUser(String username) {
		try {
			users.get(username).get(By.xpath("./../span[@class='remove-item']")).click();
			Timer.waitCondition(() -> !isUserInList(username));
		} catch (NullPointerException npe) {
			System.out.println("User " + username + " was not found in the permissions list!");
		}
		return this;
	}
	
	public SharingPanel removeAllUsers() {
		for (Text username : users) {
			this.removeUser(username.getText());
		}
		return this;
	}
	
	public boolean isInputAvailable() {
		return input.getWebElement().isEnabled();
	}
}
