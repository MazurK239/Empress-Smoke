package sections.preferences;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import model.SharingPanel;

public class PermissionsTab extends Section {
	
	@FindBy(xpath="./*[1]/mld-sharing-panel")
	public SharingPanel protocolSharingPanel;

	@FindBy(xpath="./*[2]/mld-sharing-panel")
	public SharingPanel experimentSharingPanel;

	
}
