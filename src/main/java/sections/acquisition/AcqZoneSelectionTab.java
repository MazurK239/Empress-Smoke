package sections.acquisition;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;

import model.ZSAutoPanel;

public class AcqZoneSelectionTab extends ZoneSelectionTab {


	@FindBy(css=".general-button-panel_right > div > button:nth-of-type(2)")
	public Clickable addZoneButton;

	@FindBy(css=".general-button-panel_right > div > button:nth-of-type(4)")
	public Clickable clearAllButton;

	@FindBy(css=".right-zones-panel")
	protected ZSAutoPanel autoCenteredPanel;	

	public void addAutoCenteredZone(int horSites, int vertSites) {
		autoToggler.turnOn();
		autoCenteredPanel.setSites(horSites, vertSites);
	}
	
	public void addMinZone(float x, float y) {
		clearAllButton.click();
		addZoneButton.click();
		moveToPosition(x, y);
	}

	public void addMaxZone() {
		clearAllButton.click();
		addZoneButton.click();
		resizeToMaxZone();
	}
	
	public void clearAll() {
		clearAllButton.click();
	}
	
}
