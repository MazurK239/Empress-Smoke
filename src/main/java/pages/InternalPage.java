package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;

import model.MldPopup;

import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;

import sites.EmpressSite;

public class InternalPage extends WebPage {
	
	@FindBy(xpath="//div[contains(@class, 'cg-busy ng-scope')]")
	public Element evilLoader;
	
	@FindBy(xpath="//mld-breadcrumb-menu/ul/li/*")
	public Elements<Button> breadcrumbsMenu;
	
	@FindBy(css=".navbar-link__logout")
	protected Button logoutButton;

	@FindBy(xpath="//a[@ui-sref='configuration-settings']")
	protected Button userPreferencesIcon;
	
	@FindBy(css=".modal-dialog")
	public MldPopup confirmation;
	
	@FindBy(css=".global-message")
	public Text alert;
	
	public LandingPage goHome () {
		breadcrumbsMenu.get(breadcrumbsMenu.size() - 1).click();
		EmpressSite.landingPage.checkOpened();
		return EmpressSite.landingPage;
	}
	
	public void goToDashboard() {
		breadcrumbsMenu.get(breadcrumbsMenu.size() - 2).click();
		EmpressSite.experimentTemplatesPage.checkOpened();
	}

	public void logout() {
		logoutButton.click();
		confirmation.ok();
	}
	
	public void openUserPrefs() {
		userPreferencesIcon.click();
	}

	public String getMessage() {
		return alert.get(By.cssSelector(".global-message__wrapper")).getText();
	}

	public boolean hasMessage() {
		return alert.isDisplayed();
	}
	
}
