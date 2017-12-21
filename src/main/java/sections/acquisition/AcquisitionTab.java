package sections.acquisition;

import java.util.List;
//import org.sikuli.script.*;
import org.openqa.selenium.support.FindBy;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

//import static sites.EmpressSite.screen;
import model.MldToggler;
import model.OSD;
import model.StainDropdownSection;

public class AcquisitionTab extends Section {

	@FindBy(css=".general-button-panel_right button:nth-of-type(1)")
	private MldToggler labwareToggler;

	@FindBy(css=".labware-settings-list-item__value")
	private Elements<Clickable> labwareList;
	
	@FindBy(css=".labware-settings-list-item.selected .labware-settings-list-item__value")
	private Clickable selectedLabware;
	
	@FindBy(css=".general-button-panel_right button:nth-of-type(3)")
	private MldToggler objectiveToggler;

	@FindBy(css=".objectives-settings-list-item__data > strong")
	private Elements<Clickable> objectiveList;
	
	@FindBy(css=".general-button-panel_right button:nth-of-type(2)")
	private MldToggler stainsToggler;

	@FindBy(xpath="//mld-stains-panel//select/..")
	private Elements<StainDropdownSection> dropdowns;
	
	@FindBy(css=".general-button-panel_right button:nth-of-type(4)")
	private MldToggler compareToggler;

	@FindBy(css=".general-panel-button-acq-capture")
	private Clickable captureButton;

	@FindBy(css="canvas")
	public OSD canvas;



	public AcquisitionTab setLabware(String labware) {
		labwareToggler.turnOn();
		Timer.waitCondition(() -> labwareList.get(labware) != null);
		labwareList.get(labware).click();
		return this;
	}

	public AcquisitionTab setStains(List<String> stains) {
		stainsToggler.turnOn();
		int i = 0;
		for (StainDropdownSection dropdown : dropdowns) {
			dropdown.expand();
			dropdown.selectStain(stains.get(i++));
		}
		return this;
	}
	
	public List<String> getUnavailableStains() {
		stainsToggler.turnOn();
		return dropdowns.get(0).getUnavailableStains();
	}

	public List<String> getAvailableStains() {
		stainsToggler.turnOn();
		return dropdowns.get(0).getAvailableStains();
	}
	
	public List<String> getTLOptions() {
		stainsToggler.turnOn();
		return dropdowns.get(dropdowns.size() - 1).getAllOptions();
	}

	public AcquisitionTab setObjective(String objective) {
		objectiveToggler.turnOn();
		objectiveList.get(objective).click();
		return this;
	}

	public void capture() {
		objectiveToggler.turnOff();
		labwareToggler.turnOff();
		stainsToggler.turnOff();
		compareToggler.turnOff();
		captureButton.click();
	}

	public String getSelectedLabwareName() {
		labwareToggler.turnOn();
		return selectedLabware.getWebElement().getText();
	}
	
}
