package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.web.matcher.testng.Assert;

import model.MldCheckbox;

public class LoginPage extends WebPage {

	@FindBy(name="user")
	public TextField usernameField;
	
	@FindBy(name="password")
	public TextField passwordField;
	
	@FindBy(xpath="//button[@type='submit']")
	public Button submitButton;

	@FindBy(xpath="//div[contains(@class, 'error-popover')][text()='Please type login']")
	private Text loginError;
	
	@FindBy(xpath="//div[contains(@class, 'error-popover')][text()='Please type password']")
	private Text passwordError;

	@FindBy(xpath="//span[contains(@class, 'error')][text()='Login or password is incorrect']")
	private Text incorrectError;

	@FindBy(xpath="//*[@type='checkbox']")
	public MldCheckbox checkbox;

	@FindBy(css=".login-form")
	private Section loginForm;
	
	public void loginAs(String username, String password) {
		usernameField.newInput(username);
		passwordField.newInput(password);
		submitButton.click();
	}

	public Boolean loginErrorPresent() {
		return loginError.isDisplayed();
	}

	public Boolean passwordErrorPresent() {
		return passwordError.isDisplayed();
	}

	public Boolean incorrectErrorPresent() {
		return incorrectError.isDisplayed();
	}

	public void checkCheckbox() {
		checkbox.checkIt();		
	}

	public void uncheckCheckbox() {
		checkbox.uncheckIt();		
	}
	

	public String getEnteredUser() {
		return usernameField.getAttribute("value");
	}

	public void loginAsReturn(String username, String password) {
		usernameField.newInput(username);
		passwordField.newInput(password + Keys.RETURN);
	}

	@Override
	public void checkOpened() {
		super.checkOpened();
		Assert.isTrue(() -> loginForm.isDisplayed());
	}

}
