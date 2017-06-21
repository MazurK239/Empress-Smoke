package sections.acquisition;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import model.AcquisitionSummary;
import model.MldToggler;
import model.SharingPanel;
import static sites.EmpressSite.*;

public class RunTab extends Section {

	@FindBy(css=".general-button-panel-right button:last-child")
	private Button runButton;

	@FindBy(xpath="//input[contains(@class,'name-input')]")
	private TextField experimentNameField;

	@FindBy(xpath="//textarea[contains(@class,'description-input')]")
	private TextField experimentDescriptionField;
	
	@FindBy(css=".general-button-panel-right button:nth-child(3)")
	private MldToggler shareExperimentToggler;
	
	@FindBy(xpath="//mld-user-sharing-panel")
	private SharingPanel experimentPermissionSection;

	@FindBy(css="mld-experiment-validation")
	public AcquisitionSummary validationSummary;
	
	
	public RunTab setExperimentName(String experimentName, String description) {
		experimentNameField.newInput(experimentName);
		experimentDescriptionField.newInput(description);
		return this;
	}

	public RunTab setExperimentName(String experimentName) {
		experimentNameField.newInput(experimentName);
		return this;
	}

	public void run() {
		runButton.click();
	}
	
	public SharingPanel expandSharingPanel() {
		shareExperimentToggler.turnOn();
		return experimentPermissionSection;
	}
	
	public boolean cannotRun() {
		return experimentPage.hasMessage();
	}
}
