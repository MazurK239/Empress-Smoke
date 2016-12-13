package sections.acuisition;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import model.AcquisitionSummary;
import model.MldToggler;
import model.SharingPanel;

public class SaveTab extends Section {
	
	@FindBy(xpath="//input[contains(@class,'name-input')]")
	private TextField nameField;

	@FindBy(xpath="//textarea[contains(@class,'description-input')]")
	private TextField descriptionField;

	@FindBy(xpath="//button[@title='Save']")
	private Button saveButton;
	
	@FindBy(xpath="//button[@title='Lock Protocol']")
	private MldToggler lockProtocolToggler;
	
	@FindBy(xpath="//mld-user-sharing-panel")
	private SharingPanel protocolPermissionSection;
	
	@FindBy(css="mld-experiment-validation")
	private AcquisitionSummary validationSummary;
	
	public void saveProtocol() {
		saveButton.click();
	}

	public SaveTab setProtocolName(String name) {
		nameField.newInput(name);
		return this;
	}

	public SaveTab setProtocolName(String name, String description) {
		nameField.newInput(name);
		descriptionField.newInput(description);	
		return this;
	}

	public SharingPanel expandSharingPanel() {
		lockProtocolToggler.turnOn();
		return protocolPermissionSection;
	}

	public AcquisitionSummary getValidationSummary() {
		return validationSummary;
	}

}
