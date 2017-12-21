package sections.acquisition;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import model.AcquisitionSummary;
import model.MldToggler;
import model.SharingPanel;

public class RunTab extends Section {

	@FindBy(css=".general-button-panel_right button:last-child")
	private Button runButton;

	@FindBy(xpath="//input[contains(@class,'name-input')]")
	private TextField experimentNameField;

	@FindBy(xpath="//textarea[contains(@class,'description-input')]")
	private TextField experimentDescriptionField;
	
	@FindBy(css=".general-button-panel_right button:nth-child(3)")
	private MldToggler shareExperimentToggler;
	
	@FindBy(xpath="//mld-user-sharing-panel")
	private SharingPanel experimentPermissionSection;

	@FindBy(css="mld-experiment-validation")
	public AcquisitionSummary validationSummary;

	@FindBy(css=".asterisk")
	private Text nameInputTitle;
	
	
	public RunTab setRunName(String experimentName, String description) {
		experimentNameField.newInput(experimentName);
		experimentDescriptionField.newInput(description);
		return this;
	}

	public RunTab setRunName(String experimentName) {
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
	
	public Boolean isNameWarningPresent() {
		return nameInputTitle.getAttribute("class").contains("unfilled") ? true : false;
	}
}
