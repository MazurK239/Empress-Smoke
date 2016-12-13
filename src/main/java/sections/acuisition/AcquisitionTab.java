package sections.acuisition;

import java.util.List;
import org.sikuli.script.*;
import org.openqa.selenium.support.FindBy;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import static sites.EmpressSite.screen;
import model.MldToggler;
import model.OSD;
import model.StainDropdownSection;

public class AcquisitionTab extends Section {

	@FindBy(css=".general-button-panel-right button:nth-child(2).panel-button-acq")
	private MldToggler labwareToggler;

	@FindBy(css=".labware-settings-list-item__value")
	private Elements<Clickable> labwareList;
	
	@FindBy(css=".general-button-panel-right button:nth-child(4)")
	private MldToggler objectiveToggler;

	@FindBy(css=".objectives-settings-list-item__data > strong")
	private Elements<Clickable> objectiveList;
	
	@FindBy(css=".general-button-panel-right button:nth-child(3)")
	private MldToggler stainsToggler;

	@FindBy(xpath="//mld-stains-panel//select/..")
	private Elements<StainDropdownSection> dropdowns;

	@FindBy(css=".general-button-panel-right div:nth-child(6) label")
	private MldToggler hdrToggler;
	
	@FindBy(css=".general-button-panel-right div:nth-child(7) label")
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
		return dropdowns.get(1).getUnavailableStains();
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
		hdrToggler.turnOff();
		compareToggler.turnOff();
		captureButton.click();
	}

	public void waitImageLoaded(String device) {
		try{
			screen.setAutoWaitTimeout(10);
			switch (device) {
			case "sila":				
				screen.wait("skl_img/sila_snap.png");
				break;
			case "demo":				
				screen.wait("skl_img/demo_snap.png");
				break;
			default:
				break;
			}
		} catch(FindFailed e){
            e.printStackTrace();
		}
	}

	public String getSelectedLabwareName() {
		labwareToggler.turnOn();
		return labwareList.get(0).getWebElement().getText();
	}
	
}
