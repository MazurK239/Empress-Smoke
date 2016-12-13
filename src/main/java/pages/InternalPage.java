package pages;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;

import sites.EmpressSite;

public class InternalPage extends WebPage {
	
	@FindBy(xpath="//div[contains(@class, 'cg-busy ng-scope')]")
	public Element evilLoader;
	
	@FindBy(xpath="//mld-breadcrumb-menu/ul/li/*")
	public Elements<Button> breadcrumbsMenu;
	
	@FindBy(xpath="//a[@title='Log out']")
	private Button logoutButton;

	@FindBy(xpath="//a[@ui-sref='configuration-settings']")
	private Button userPreferencesIcon;
	
	public LandingPage goHome () {
		breadcrumbsMenu.get("Home").click();
		EmpressSite.landingPage.checkOpened();
		return EmpressSite.landingPage;
	}
	
	public void goToDashboard() {
		breadcrumbsMenu.get(breadcrumbsMenu.size() - 2).click();
	}

	public void logout() {
		logoutButton.click();
	}
	
	public void openUserPrefs() {
		userPreferencesIcon.click();
	}
	
}
