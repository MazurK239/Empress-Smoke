package sections.acquisition;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import model.MldCheckbox;

public class WorkflowTab extends Section {

	@FindBy(css=".exp-workflow-panel__disabler input")
	private MldCheckbox enableCheckbox;
	
	@FindBy(css=".mld-workflow-factors > div:nth-child(1) .workflow-settings-input")
	private TextField durationInput;
	
	@FindBy(css=".mld-workflow-factors > div:nth-child(2) .workflow-settings-input")
	private TextField intervalInput;

	public WorkflowTab enableWorkflow() {
		enableCheckbox.uncheckIt();
		return this;
	}

	public WorkflowTab setParameters(int[] workflowParameters) {
		durationInput.sendKeys("\b" + String.valueOf(workflowParameters[0]));
		intervalInput.sendKeys("\b" + String.valueOf(workflowParameters[1]));
		return this;
	}

}
