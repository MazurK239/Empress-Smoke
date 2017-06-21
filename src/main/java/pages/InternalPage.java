package pages;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;

import model.MldPopup;

import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;

import sites.EmpressSite;

public class InternalPage extends WebPage {
	
	@FindBy(xpath="//div[contains(@class, 'cg-busy ng-scope')]")
	public Element evilLoader;
	
	@FindBy(xpath="//mld-breadcrumb-menu/ul/li/*")
	public Elements<Button> breadcrumbsMenu;
	
	@FindBy(css=".navbar-link__logout")
	private Button logoutButton;

	@FindBy(xpath="//a[@ui-sref='configuration-settings']")
	private Button userPreferencesIcon;
	
	@FindBy(css=".modal-dialog")
	private MldPopup confirmation;
	
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
	
}
