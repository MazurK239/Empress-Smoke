package pages;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;

import sections.preferences.MiscTab;
import sections.preferences.PermissionsTab;

public class UserSettingsPage extends InternalPage {
	
	@FindBy(xpath="//button[@title='Miscellaneous']")
	private Button miscTabIcon;

	@FindBy(css="mld-configuration-settings-misc")
	private MiscTab miscTab;
	
	@FindBy(xpath="//button[@title='Sharing Permissions']")
	private Button permissionsTabIcon;

	@FindBy(css="mld-configuration-settings-sharing-permissions")
	private PermissionsTab permissionsTab;

	@FindBy(xpath="//button[@ng-click='vm.back()']")
	private Button backButton;
	
	
	public void goBack() {
		backButton.click();
	}
	
	public MiscTab openMiscTab() {
		miscTabIcon.click();
		return miscTab;
	}
	
	public PermissionsTab openPermissionsTab() {
		permissionsTabIcon.click();
		return permissionsTab;
	}

}
