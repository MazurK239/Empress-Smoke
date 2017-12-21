package sections.acquisition;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;

import model.MldCheckbox;
import model.MldToggler;
import model.ZSSliderPanel;

public class AnZoneSelectionTab extends ZoneSelectionTab {
	
	@FindBy(css=".toggle-switch-label input")
	public MldCheckbox analyzeAllToggler;
	
	@FindBy(css=".general-button-panel_right button:nth-of-type(2)")
	private MldToggler manualModeToggler;
	
	@FindBy(css=".general-button-panel_right button:nth-of-type(3)")
	public Clickable addZoneButton;

	@FindBy(css=".general-button-panel_right button:nth-of-type(5)")
	public Clickable clearAllButton;
	
	@FindBy(css=".info-zone-selection div:first-child span")
	protected Text acquiredArea;

	@FindBy(css=".info-zone-selection div:nth-child(2) span")
	protected Text analyzedArea;

	@FindBy(css=".pinned-panel_bottom")
	public ZSSliderPanel bottomPanel;


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

	public void useMaxCoverage() {
		analyzeAllToggler.checkIt();
	}

	public ZSSliderPanel openAutoPanel() {
		autoToggler.turnOn();
		return bottomPanel;
	}

	public AnZoneSelectionTab switchToManualMode() {
		manualModeToggler.turnOn();
		return this;
	}
	
	public String getAcquiredArea() {
		return acquiredArea.getText();
	}

	public String getAnalyzedArea() {
		return analyzedArea.getText();
	}

}
